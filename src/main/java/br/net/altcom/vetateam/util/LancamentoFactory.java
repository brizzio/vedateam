package br.net.altcom.vetateam.util;

import java.math.BigDecimal;
import java.util.Map;

import br.net.altcom.vetateam.modelo.Cliente;
import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.modelo.Produto;

public class LancamentoFactory {
	
	public Lancamento getLancamento(Map<String, String> rowMap){
		
		Lancamento lancamento = new Lancamento();
		
		Produto produto = new ProdutoFactory().getProduto(rowMap);
		lancamento.setProduto(produto);
		
		Cliente cliente = new ClienteFactory().getCliente(rowMap);
		lancamento.setCliente(cliente);
		
		BigDecimal faturamento = new BigDecimal(rowMap.get(LancamentoHeader.FATURA.getHeaderName()));
		lancamento.setFaturamento(faturamento);
		
		BigDecimal vendaFisica = new BigDecimal(rowMap.get(LancamentoHeader.VENDA_FISICA.getHeaderName()));
		lancamento.setVendaFisica(vendaFisica);

		lancamento.setDate(rowMap.get(LancamentoHeader.DATA.getHeaderName()));
		
		return lancamento;
	}
}
