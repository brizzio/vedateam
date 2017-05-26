package br.net.altcom.vetateam.mb;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

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
	
	public void upload() throws IOException{
		if (file != null) {
			InputStream inputstream = file.getInputstream();
			processor.setFile(inputstream);
			new Thread(processor).start();
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
