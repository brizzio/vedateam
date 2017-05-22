package br.net.altcom.vetateam.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheet {

	private Sheet sheet;
	private List<Row> rows = new ArrayList<>();

	public ExcelSheet(Sheet sheet) {
		this.sheet = sheet;
		setRows();
	}


	public Map<String, String> getRowAt(int index){

		if(index < 0 || index >= rows.size())
			throw new IllegalArgumentException();
		
		Map<String, String> mapRow = new HashMap<>();
		
		Row cabecalho = rows.get(0);
		Row row = rows.get(index);

		Iterator<Cell> cellIterator = cabecalho.cellIterator();
		
		while (cellIterator.hasNext()) {
			Cell cell = (Cell) cellIterator.next();
			
			mapRow.put(cellContentForString(cell).toLowerCase(), cellContentForString(row.getCell(cell.getColumnIndex())));
		}
		
		return mapRow;
	}


	public String cellContentForString(Cell cell) {
		return cell.toString();
	}
	
	public String getSheetName() {
		return sheet.getSheetName();
	}
	
	public int getRowSize(){
		return rows.size();
	}

	public void setRows() {
		sheet.forEach(row -> rows.add(row));
	}
}
