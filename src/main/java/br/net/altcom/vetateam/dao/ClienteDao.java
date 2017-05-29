package br.net.altcom.vetateam.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Cliente;

@Stateless
public class ClienteDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Cliente cliente) {
		manager.persist(cliente);
	}
	
	public Cliente buscaPeloNome(Cliente cliente) {
		String jpql = "Select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = manager.createQuery(jpql, Cliente.class);
		try {
			return query.setParameter("nome", cliente.getNome()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
	
	public boolean isExiste(Cliente cliente) {
		return (buscaPeloNome(cliente) != null);
	}

	public synchronized Cliente adicionaSeNaoExiste(Cliente cliente) {
		if (isExiste(cliente)) {
			cliente = buscaPeloNome(cliente);
		} else {
			adiciona(cliente);
		}
		return cliente;
	}
}
