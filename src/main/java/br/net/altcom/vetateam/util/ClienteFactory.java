package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Cliente;

public class ClienteFactory {

	public Cliente getCliente(Map<String, String> rowMap){
		
		Cliente cliente = new Cliente();		
		cliente.setNome(rowMap.get(LancamentoHeader.CLIENTE.getHeaderName()));
		
		return cliente;
	}
}
