package br.net.altcom.vetateam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.net.altcom.vetateam.modelo.Produto;

public class ProdutoDao {
	
	@PersistenceContext(unitName="fly")
	private EntityManager manager;
	
	public void adiciona(Produto produto){
		manager.persist(produto);
	}

}
