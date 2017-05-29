package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

import br.net.altcom.vetateam.modelo.Excel;

public class ExcelFactory implements AutoCloseable {

	private InputStream inputStream;
	private Workbook workbook;

	public ExcelFactory(InputStream stream) {
		this.inputStream = stream;
	}

	public Excel getExcel() throws IOException, NotOfficeXmlFileException {

		workbook = StreamingReader.builder().rowCacheSize(50000).bufferSize(6000).open(inputStream);
		
		Map<String, Sheet> sheets = new HashMap<>();
		workbook.forEach(sheet -> sheets.put(sheet.getSheetName().toLowerCase(), sheet));

		return new Excel(sheets);
	}

	public List<String> getSheetName(){
		List<String> sheetName = new ArrayList<>();
		workbook = StreamingReader.builder().open(inputStream);
		workbook.forEach(sheet -> sheetName.add(sheet.getSheetName()));
		return sheetName;
	}
	
	@Override
	public void close() throws Exception {
		workbook.close();
	}
}