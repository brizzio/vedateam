package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.net.altcom.vetateam.modelo.Excel;

public class ExcelFactory {

	private InputStream inputStream;

	public ExcelFactory(InputStream stream) {
		this.inputStream = stream;
	}
	
	public Excel getExcel() throws IOException, NotOfficeXmlFileException{
		
		Map<String, Sheet> sheets = new HashMap<>();
		
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			workbook.forEach(sheet -> sheets.put(sheet.getSheetName().toLowerCase(), sheet));
			return new Excel(sheets);
		}		
	}
}