package br.net.altcom.vetateam.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.net.altcom.vetateam.modelo.Lancamento;

@Named
public class LancamentoDao {

	@PersistenceContext(unitName="fly")
	private EntityManager manager;

	public void adiciona(Lancamento lancamento) {
		manager.persist(lancamento);
	}
}
