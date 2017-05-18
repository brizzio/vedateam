package br.net.altcom.vetateam.mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.UploadedFile;

import br.net.altcom.vetateam.dao.LancamentoDao;
import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.util.ExcelFactory;
import br.net.altcom.vetateam.util.ExcelSheet;
import br.net.altcom.vetateam.util.LancamentoFactory;

@ManagedBean
public class FileUploadBean {

	private UploadedFile file;

	@Inject
	private LancamentoDao lancamentoDao;

	public void upload() throws IOException {
		ExcelSheet excel = new ExcelFactory(file.getInputstream()).controi();
		LancamentoFactory lancamentoFactory = new LancamentoFactory();
		
		for (Row linha : excel.getConteudo()) {
			Lancamento lancamento = lancamentoFactory.controiLancamento(linha);
			System.out.println(lancamento);
		}
		
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
