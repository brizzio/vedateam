package br.net.altcom.vetateam.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Cliente;

public class ClienteDao {

	@Inject
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
}
