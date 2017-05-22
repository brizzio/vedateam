package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Representante;

public class RepresentanteFactory {

	public Representante getRepresentante(Map<String, String> rowMap) {
		Representante representante = new Representante();
		
		String idString = rowMap.get(LancamentoHeader.ID_REPRESENTANTE.getHeaderName()).replace(".0", "");
		Integer id = new Integer(Integer.parseInt(idString));
		representante.setId(id);
		
		representante.setNome(rowMap.get(LancamentoHeader.REPRESENTANTE.getHeaderName()));
		
		return representante;
	}

}
