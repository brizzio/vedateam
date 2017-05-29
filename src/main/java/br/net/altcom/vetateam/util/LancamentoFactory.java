package br.net.altcom.vetateam.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import br.net.altcom.vetateam.modelo.Lancamento;

public class LancamentoFactory {
	
	public Lancamento getLancamento(Map<String, String> rowMap){
		
		Lancamento lancamento = new Lancamento();
		
		String preco = rowMap.get(LancamentoHeader.FATURA.getHeaderName());
		String vdaFisica = rowMap.get(LancamentoHeader.VENDA_FISICA.getHeaderName());
		
		preco = preco.replace(",", ".");	
		vdaFisica = vdaFisica.replace(",", ".");
		
		BigDecimal faturamento = new BigDecimal(preco);
		lancamento.setFaturamento(faturamento);
		
		BigDecimal vendaFisica = new BigDecimal(vdaFisica);
		lancamento.setVendaFisica(vendaFisica);

		int ano = Integer.parseInt(rowMap.get(LancamentoHeader.ANO.getHeaderName()));
		int mes = Integer.parseInt(rowMap.get(LancamentoHeader.MES.getHeaderName()));
		int dia = Integer.parseInt(rowMap.get(LancamentoHeader.DIA.getHeaderName()));
		LocalDate localDate = LocalDate.of(ano, mes, dia);
		
		lancamento.setDate(localDate);
		
		
		
		return lancamento;
	}
}
