package br.net.altcom.vetateam.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.net.altcom.vetateam.modelo.Produto;

@Stateless
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Produto produto){
		manager.persist(produto);
	}

}
