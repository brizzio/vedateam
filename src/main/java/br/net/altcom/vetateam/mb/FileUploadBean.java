package br.net.altcom.vetateam.mb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.net.altcom.vetateam.util.ExcelFactory;
import br.net.altcom.vetateam.util.ExcelLancamentoProcessor;

@Named
@ViewScoped
public class FileUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadedFile file;

	@Inject
	private ExcelLancamentoProcessor processor;

	private List<String> sheetsName = new ArrayList<>();

	public List<String> getSheetsName() {
		return sheetsName;
	}

	public void processarExcel(String sheetName) throws IOException {
		if (file != null) {
			processor.setSheetName(sheetName);
			try (InputStream inputStream = new ByteArrayInputStream(file.getContents())){
				processor.setFile(inputStream);
				new Thread(processor).start();
				sheetsName.remove(sheetName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		file = event.getFile();
		try (InputStream inputStream = new ByteArrayInputStream(file.getContents());
				ExcelFactory excelFactory = new ExcelFactory(inputStream)){
			sheetsName = excelFactory.getSheetName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}