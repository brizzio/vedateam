package br.net.altcom.vetateam.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import br.net.altcom.vetateam.modelo.Lancamento;

@Named
public class LancamentoDao {

	@PersistenceUnit(unitName="fly")
	private EntityManager manager;

	public void adiciona(Lancamento lancamento) {
		manager.persist(lancamento);
	}
}
