package br.net.altcom.vetateam.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.net.altcom.vetateam.modelo.Cliente;

@Stateless
public class ClienteDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Cliente cliente) {
		manager.persist(cliente);
	}
}
