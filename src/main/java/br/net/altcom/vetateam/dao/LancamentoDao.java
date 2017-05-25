package br.net.altcom.vetateam.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.net.altcom.vetateam.modelo.Lancamento;

public class LancamentoDao {

	@Inject
	private EntityManager manager;

	public void adiciona(Lancamento lancamento) {
		manager.persist(lancamento);
	}
	
	public void adicionaLista(List<Lancamento> lancamentos){
		lancamentos.forEach(lancamento -> manager.persist(lancamento));
	}
}
