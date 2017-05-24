package br.net.altcom.vetateam.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheet {

	public Sheet sheet;
	private int lastPositionOfTheRow;
	private Iterator<Row> rowIterator;
	private Row header;
	
	public ExcelSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	
	public void begin(){
		rowIterator = sheet.rowIterator();
		lastPositionOfTheRow = 0;
		if (rowIterator.hasNext())
			header = rowIterator.next();
	}
	

	public synchronized List<Row> getPlusRow(int amount) {
		
		List<Row> rows = new ArrayList<>();
		amount += lastPositionOfTheRow;
		
		while (rowIterator.hasNext() && lastPositionOfTheRow < amount) {
			Row rowNext = rowIterator.next();
			rows.add(rowNext);
			lastPositionOfTheRow++;
		}
		
		return rows;
	}
	
	public String getSheetName() {
		return sheet.getSheetName();
	}
	
	public Row getHeader() {
		return header;
	}
}
