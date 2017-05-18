package br.net.altcom.vetateam.util;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Row;

import br.net.altcom.vetateam.dao.ProdutoDao;
import br.net.altcom.vetateam.modelo.Cliente;
import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.modelo.Produto;

public class LancamentoFactory {

	@Inject
	private ProdutoDao produtoDao;
	
	public Lancamento controiLancamento(Row linha){
		
		List<String> celulasDeUmaLinha = ExcelFactory.getCelulasDeUmaLinha(linha);
		
		Produto produto = new Produto();
		produto.setProduto(celulasDeUmaLinha.get(3));
		produto.setFamilia(celulasDeUmaLinha.get(4));
		produto.setItem(celulasDeUmaLinha.get(6));
		
		produtoDao.adiciona(produto);
		
		Cliente cliente = new Cliente();
		cliente.setNome(celulasDeUmaLinha.get(7));
		
		Lancamento lancamento = new Lancamento();
		lancamento.setCliente(cliente);
		lancamento.setProduto(produto);
		lancamento.setDate(LocalDate.now());
		
		return lancamento;
	}
}
