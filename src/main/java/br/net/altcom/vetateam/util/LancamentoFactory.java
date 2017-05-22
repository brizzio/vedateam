package br.net.altcom.vetateam.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.modelo.Produto;

public class LancamentoFactory {
	
	private ExcelSheet sheet;

	public LancamentoFactory(ExcelSheet sheet) {
		this.sheet = sheet;
	}
	
	public List<Lancamento> getlancamentos(){
		
		List<Lancamento> lancamentos = new ArrayList<>();
		
		for (int row = 1; row < sheet.getRowSize(); row++)
			lancamentos.add(getLancamentoAt(row));
		
		return lancamentos;
	}
	
	public Lancamento getLancamentoAt(int row){
		
		Map<String, String> rowMap = sheet.getRowAt(row);
		Produto produto = new ProdutoFactory().getProduto(rowMap);
		
		Lancamento lancamento = new Lancamento();
		lancamento.setProduto(produto);
		
		BigDecimal faturamento = new BigDecimal(rowMap.get(LancamentoHeader.FATURA.getHeaderName()));
		lancamento.setFaturamento(faturamento);
		
		String vendaFisicaString = rowMap.get(LancamentoHeader.VENDA_FISICA.getHeaderName());
		vendaFisicaString = vendaFisicaString.replace(".0", "");
		int vendaFisica = Integer.parseInt(vendaFisicaString);
		
		lancamento.setVendaFisica(vendaFisica);
		
		//falta data, representante;
		
		return lancamento;
	}
}
