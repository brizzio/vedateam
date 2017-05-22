package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Produto;

public class ProdutoFactory {

	public Produto getProduto(Map<String, String> rowMap){
		Produto produto = new Produto();
		
		String idString = rowMap.get(LancamentoHeader.ID_ITEM.getHeaderName());
		idString = idString.replace(".0", "");
		Integer id = new Integer(idString);
		produto.setId(id);
		
		produto.setProduto(rowMap.get(LancamentoHeader.PRODUTO.getHeaderName()));
		produto.setItem(rowMap.get(LancamentoHeader.ITEM.getHeaderName()));
		produto.setFamilia(rowMap.get(LancamentoHeader.FAMILIA.getHeaderName()));
		
		return produto;
	}
}
