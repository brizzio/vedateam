package br.net.altcom.vetateam.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.net.altcom.vetateam.modelo.Lancamento;

@Stateless
public class LancamentoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Lancamento lancamento) {
		manager.persist(lancamento);
	}
	
	public void adicionaLista(List<Lancamento> lancamentos){
		lancamentos.forEach(lancamento -> manager.persist(lancamento));
	}
}
