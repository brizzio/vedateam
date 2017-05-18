package br.net.altcom.vetateam.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

public class ExcelSheet {

	private List<Row> conteudo;
	
	public void setConteudo(List<Row> conteudo) {
		this.conteudo = conteudo;
	}
	
	public List<Row> getConteudo() {
		return conteudo;
	}
}
