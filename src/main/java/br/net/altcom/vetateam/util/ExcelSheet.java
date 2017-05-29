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
	private boolean finish;
	
	public ExcelSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	
	public void begin(){
		rowIterator = sheet.rowIterator();
		finish = false;
		if (rowIterator.hasNext())
			header = rowIterator.next();
		lastPositionOfTheRow = 1;
	}
	

	public synchronized List<Row> getPlusRow(int amount) {
		
		List<Row> rows = new ArrayList<>();
		amount += lastPositionOfTheRow;
		
		while (lastPositionOfTheRow < amount) {
			if (rowIterator.hasNext()) {
				Row rowNext = rowIterator.next();
				rows.add(rowNext);
				lastPositionOfTheRow++;
			}else{
				System.out.println("Finalizado");
				finish = true;
				break;
			}
		}
		return rows;
	}
	
	public boolean isFinish() {
		return finish;
	}

	public String getSheetName() {
		return sheet.getSheetName();
	}
	
	public Row getHeader() {
		return header;
	}
}
