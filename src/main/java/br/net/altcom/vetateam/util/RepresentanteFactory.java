package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Representante;

public class RepresentanteFactory {

	public Representante getRepresentante(Map<String, String> rowMap) {
		Representante representante = new Representante();
		
		String idString = rowMap.get(LancamentoHeader.ID_REPRESENTANTE.getHeaderName());
		representante.setId(new Integer(idString));
		
		representante.setNome(rowMap.get(LancamentoHeader.REPRESENTANTE.getHeaderName()));
		
		return representante;
	}

}
