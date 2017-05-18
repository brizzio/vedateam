package br.net.altcom.vetateam.mb;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadBean {

	private UploadedFile file;
	private String fileName = "";

	public void upload() {
		if (file == null)
			return;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			fileName.re
			Iterator<Row> rowIterator = sheet.rowIterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellTypeEnum()) {
					case STRING:
						fileName += cell.getStringCellValue();
						break;
					case NUMERIC:
						fileName += cell.getNumericCellValue();
						break;
					default:
						break;
					}

				}
			}

			workbook.close();
		} catch (IOException e) {
			System.out.println("Erro ao abrir o arquivo");
			return;
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
