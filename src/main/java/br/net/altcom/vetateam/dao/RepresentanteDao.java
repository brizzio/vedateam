package br.net.altcom.vetateam.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Representante;

public class RepresentanteDao {

	@Inject
	private EntityManager manager;

	public void adiciona(Representante representante) {
		manager.persist(representante);
	}

	public Representante buscaPeloId(Representante representante) {
		String jpql = "Select r from Representante r where r.id = :id";
		TypedQuery<Representante> query = manager.createQuery(jpql, Representante.class);
		try {
			return query.setParameter("id", representante.getId()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
}
