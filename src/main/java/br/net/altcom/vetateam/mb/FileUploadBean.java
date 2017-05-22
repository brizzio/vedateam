package br.net.altcom.vetateam.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.net.altcom.vetateam.util.SalvaLancamentosNoBanco;

@Named
@ViewScoped
public class FileUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UploadedFile file;
	@Inject
	private SalvaLancamentosNoBanco banco;

	public void upload() throws IOException {
		banco.setFile(file.getInputstream());
		new Thread(banco).start();

		System.out.println("Funcionou");
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public int getProgress() {
		if (banco == null)
			return 0;
		return banco.getProgress();
	}
}
