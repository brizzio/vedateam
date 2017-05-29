package br.net.altcom.vetateam.dao;

import java.time.YearMonth;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.modelo.Representante;

@Stateless
public class LancamentoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Lancamento lancamento) {
		manager.persist(lancamento);
	}
	
	public List<Lancamento> buscaLancamentoPorMesEAno(Representante representante, YearMonth yearMonth){
		String jpql = "Select l from Lancamento l where l.cliente.representante = :representante and Month(l.date) = :month and YEAR(l.date) = :year";
		TypedQuery<Lancamento> query = manager.createQuery(jpql, Lancamento.class)
				.setParameter("representante", representante)
				.setParameter("month", yearMonth.getMonthValue())
				.setParameter("year", yearMonth.getYear());
		List<Lancamento> resultList = query.getResultList();
		return resultList;
	}
}
