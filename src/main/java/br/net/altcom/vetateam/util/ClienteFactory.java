package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Cliente;
import br.net.altcom.vetateam.modelo.Representante;

public class ClienteFactory {

	public Cliente getCliente(Map<String, String> rowMap){
		
		Cliente cliente = new Cliente();
		Representante representante = new RepresentanteFactory().getRepresentante(rowMap);
		
		cliente.setNome(rowMap.get(LancamentoHeader.CLIENTE.getHeaderName()));
		cliente.setRepresentante(representante);
		
		return cliente;
	}
}
