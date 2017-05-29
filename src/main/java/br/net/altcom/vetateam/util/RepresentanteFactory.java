package br.net.altcom.vetateam.util;

import java.util.Map;

import br.net.altcom.vetateam.modelo.Representante;

public class RepresentanteFactory {

	public Representante getRepresentante(Map<String, String> rowMap) {
		Representante representante = new Representante();

		String idString = rowMap.get(LancamentoHeader.ID_REPRESENTANTE.getHeaderName());

		if (idString.equals("-")) {
			representante.setId(0);
		}else{			
			try {
				representante.setId(new Integer(idString));
			} catch (NumberFormatException e) {
				System.out.println("Erro ao converter String para id: " + idString + " do representante: "
						+ rowMap.get(LancamentoHeader.REPRESENTANTE.getHeaderName()));
			}
		}

		representante.setNome(rowMap.get(LancamentoHeader.REPRESENTANTE.getHeaderName()));

		return representante;
	}

}
