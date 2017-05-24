package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;

import br.net.altcom.vetateam.dao.ClienteDao;
import br.net.altcom.vetateam.dao.LancamentoDao;
import br.net.altcom.vetateam.dao.ProdutoDao;
import br.net.altcom.vetateam.dao.RepresentanteDao;
import br.net.altcom.vetateam.modelo.Cliente;
import br.net.altcom.vetateam.modelo.Excel;
import br.net.altcom.vetateam.modelo.Lancamento;
import br.net.altcom.vetateam.modelo.Produto;
import br.net.altcom.vetateam.modelo.Representante;

@Named
public class ExcelLancamentoProcessor implements Runnable, Serializable {

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
	@Inject
	private ClienteDao clienteDao;
	@Inject
	private RepresentanteDao representanteDao;

	@Override
	public void run() {
		System.out.println("Começou a Thread");
		String sheetName = "planilha1";

		try (ExcelFactory excelFactory = new ExcelFactory(file)) {

			Excel excel = excelFactory.getExcel();
			ExcelSheet excelSheet = excel.getSheetByName(sheetName);

			excelSheet.begin();

			new Thread(() -> {

				List<Row> plusRow = excelSheet.getPlusRow(100);
				Row header = excelSheet.getHeader();

				for (Row row : plusRow) {
					Map<String, String> rowMap = new HashMap<>();
					
					row.forEach(cell -> rowMap.put(header.getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase(),
							cell.getStringCellValue()));
					
					Lancamento lancamento = new LancamentoFactory().getLancamento(rowMap);

					
					Produto produto = produtoDao.buscaPeloId(lancamento.getProduto());

					if (produto == null) {
						produtoDao.adiciona(lancamento.getProduto());
					} else {
						lancamento.setProduto(produto);
					}

					Representante representanteDoCliente = lancamento.getCliente().getRepresentante();

					Representante ExisteRepresentante = representanteDao.buscaPeloId(representanteDoCliente);

					if (ExisteRepresentante == null) {
						representanteDao.adiciona(representanteDoCliente);
					} else {
						representanteDoCliente = ExisteRepresentante;
					}

					Cliente cliente = clienteDao.buscaPeloNome(lancamento.getCliente());

					if (cliente == null) {
						lancamento.getCliente().setRepresentante(representanteDoCliente);
						clienteDao.adiciona(lancamento.getCliente());
					} else {
						cliente.setRepresentante(representanteDoCliente);
						lancamento.setCliente(cliente);
					}

					lancamentoDao.adiciona(lancamento);
				}

			}).start();

			progress = 70;

		} catch (NotOfficeXmlFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		progress = 100;
		System.out.println("Terminou a thread");

	}
	/*
	 * private ExcelSheet getSheet(String sheetName) throws
	 * NotOfficeXmlFileException, IOException { return new
	 * ExcelFactory(file).getExcel().getSheetByName(sheetName); }
	 */

	public int getProgress() {
		return progress;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
}
