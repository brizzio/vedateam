package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFactory {

	private InputStream inputStream;
	private Map<String, Sheet> sheets = new HashMap<>();

	public ExcelFactory(InputStream stream) throws IOException {
		this.inputStream = stream;
		setSheets();
	}

	private void setSheets() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		workbook.forEach(sheet -> sheets.put(sheet.getSheetName().toLowerCase(), sheet));
		
		workbook.close();
	}
	
	public ExcelSheet getSheetByName(String name){
		
		Sheet sheet = this.sheets.get(name.toLowerCase());
		
		if(sheet == null)
			throw new NullPointerException("Sheet name is invalid");
		
		return new ExcelSheet(sheet);
	}

}
