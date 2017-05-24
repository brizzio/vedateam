package br.net.altcom.vetateam.modelo;

import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import br.net.altcom.vetateam.util.ExcelSheet;

public class Excel {

	private Map<String, Sheet> sheets;

	public Excel(Map<String, Sheet> sheets) {
		this.sheets = sheets;
	}

	public ExcelSheet getSheetByName(String name) {
		
		Sheet sheet = this.sheets.get(name.toLowerCase());

		if (sheet == null)
			throw new NullPointerException("Sheet name is invalid");

		return new ExcelSheet(sheet);
	}
}
