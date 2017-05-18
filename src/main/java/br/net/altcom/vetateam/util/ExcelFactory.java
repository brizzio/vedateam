package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFactory {

	private InputStream inputStream;
	private List<String> cabecalho;

	public ExcelFactory(InputStream stream) {
		this.inputStream = stream;
	}

	public ExcelSheet controi() {
		ExcelSheet excelSheet = new ExcelSheet();
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.rowIterator();

			getHeader(rowIterator);
			excelSheet.setConteudo(getConteudo(rowIterator));

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return excelSheet;
	}

	private List<Row> getConteudo(Iterator<Row> rowIterator) {
		
		List<Row> conteudo = new ArrayList<>();
		
		while (rowIterator.hasNext()) {
			Row row = (Row) rowIterator.next();
			conteudo.add(row);
		}
		
		return conteudo;
	}

	private void getHeader(Iterator<Row> rowIterator) {
		if (rowIterator.hasNext()) {
			Row row = (Row) rowIterator.next();
			cabecalho = getCelulasDeUmaLinha(row);
		}
	}

	public static List<String> getCelulasDeUmaLinha(Row linha) {

		List<String> todasCelulasDeUmaLinha = new ArrayList<>();

		Iterator<Cell> cellIterator = linha.cellIterator();
		while (cellIterator.hasNext()) {

			Cell cell = (Cell) cellIterator.next();

			switch (cell.getCellTypeEnum()) {
			case STRING:
				todasCelulasDeUmaLinha.add(cell.getStringCellValue());
				break;
			case NUMERIC:
				todasCelulasDeUmaLinha.add(Double.toString(cell.getNumericCellValue()));
				break;
			default:
				break;
			}

		}

		return todasCelulasDeUmaLinha;
	}

	public List<String> getCabecalho() {
		return cabecalho;
	}
}
