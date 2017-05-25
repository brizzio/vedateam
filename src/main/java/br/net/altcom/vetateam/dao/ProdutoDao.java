package br.net.altcom.vetateam.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Produto;

public class ProdutoDao {

	@Inject
	private EntityManager manager;

	public void adiciona(Produto produto) {
		manager.persist(produto);
	}

	public Produto buscaPeloId(Produto produto) {
		String jpql = "Select p from Produto p where p.id = :id";
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		try {
			return query.setParameter("id", produto.getId()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
	
	public boolean isExiste(Produto produto){
		return (buscaPeloId(produto) == null);
	}
	
	public synchronized void adicionaSeNaoExiste(Produto produto){
		if (isExiste(produto)) {
			produto = buscaPeloId(produto);
		}else{
			adiciona(produto);
		}
	}
}
