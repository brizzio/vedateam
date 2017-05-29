package br.net.altcom.vetateam.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.net.altcom.vetateam.modelo.Produto;

@Stateless
public class ProdutoDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Produto produto) {
		manager.persist(produto);
	}

	public Produto buscaPeloIdProduto(Produto produto) {
		String jpql = "Select p from Produto p where p.idProduto = :id";
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		try {
			return query.setParameter("id", produto.getIdProduto()).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	public boolean isExiste(Produto produto) {
		return (buscaPeloIdProduto(produto) != null);
	}

	public synchronized Produto adicionaSeNaoExiste(Produto produto) {
		if (isExiste(produto)) {
			produto = buscaPeloIdProduto(produto);
		} else {
			adiciona(produto);
		}
		return produto;
	}
}
