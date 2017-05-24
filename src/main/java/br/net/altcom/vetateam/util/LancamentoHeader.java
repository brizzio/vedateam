package br.net.altcom.vetateam.util;

public enum LancamentoHeader {

	REGIONAL("regional"),ID_REPRESENTANTE("cod. repres."),REPRESENTANTE("representante"),
	PRODUTO("produto"),FAMILIA("familia"),ID_ITEM("cod.item"),ITEM("item"),CLIENTE("cliente"),DATA("data"),
	DIA("dia"),MES("mês"),ANO("ano"),FATURA("faturamento mercadoria - r$"),VENDA_FISICA("venda fisica");
	
	private String header;

	private LancamentoHeader(String header) {
		this.header = header;
	}
	
	@Override
	public String toString() {
		return header;
	}
	
	public String getHeaderName(){
		return this.header;
	}
}
