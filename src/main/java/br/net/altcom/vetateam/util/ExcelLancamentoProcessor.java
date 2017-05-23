package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.net.altcom.vetateam.dao.LancamentoDao;
import br.net.altcom.vetateam.dao.ProdutoDao;
import br.net.altcom.vetateam.modelo.Lancamento;

@Named
public class SalvaLancamentosNoBanco implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int progress;
	private InputStream file;
	@Inject
	private LancamentoDao lancamentoDao; 
	@Inject 
	private ProdutoDao produtoDao;
	
	@Override
	public void run() {
		System.out.println("Começou a Thread");
		ExcelSheet sheet = null;
		try {
			sheet = new ExcelFactory(file).getSheetByName("planilha1");
		} catch (IOException e) {
			System.out.println("Ronaldo");
			return;
		}

		progress = 40;
		List<Lancamento> lancamentos = new LancamentoFactory(sheet).getlancamentos();
		progress = 70;
		lancamentos.forEach(lancamento -> 
		{
			produtoDao.adiciona(lancamento.getProduto());
			lancamentoDao.adiciona(lancamento);
		});
		
		progress = 100;
		System.out.println("Terminou a thread");
	}

	public int getProgress() {
		return progress;
	}
	
	public void setFile(InputStream file) {
		this.file = file;
	}
}
