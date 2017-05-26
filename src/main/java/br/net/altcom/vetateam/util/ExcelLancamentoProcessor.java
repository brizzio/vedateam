package br.net.altcom.vetateam.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;

import br.net.altcom.vetateam.dao.ClienteDao;
import br.net.altcom.vetateam.dao.LancamentoDao;
import br.net.altcom.vetateam.dao.ProdutoDao;
import br.net.altcom.vetateam.dao.RepresentanteDao;
import br.net.altcom.vetateam.modelo.Cliente;
import br.net.altcom.vetateam.modelo.Excel;
import br.net.altcom.vetateam.modelo.Lancamento;

public class ExcelLancamentoProcessor implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int progress;
	private InputStream file;
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private RepresentanteDao representanteDao;
	@Inject
	private ClienteDao clienteDao;
	@Inject
	private LancamentoDao lancamentoDao;

	@Override
	public void run() {
		String sheetName = "BI_ABr";

		try (ExcelFactory excelFactory = new ExcelFactory(file)) {

			Excel excel = excelFactory.getExcel();
			ExcelSheet excelSheet = excel.getSheetByName(sheetName);
			excelSheet.begin();
			Row header = excelSheet.getHeader();

			new Thread(() -> {

				while(!excelSheet.isFinish()){
					List<Row> plusRow = excelSheet.getPlusRow(100);

					for (Row row : plusRow) {
						Map<String, String> rowMap = mapeiaLinha(header, row);
						Lancamento lancamento = new LancamentoFactory().getLancamento(rowMap);
						salvaNoBanco(lancamento);
					}
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
	}
	/*
	 * private ExcelSheet getSheet(String sheetName) throws
	 * NotOfficeXmlFileException, IOException { return new
	 * ExcelFactory(file).getExcel().getSheetByName(sheetName); }
	 */

	private Map<String, String> mapeiaLinha(Row header, Row row) {
		Map<String, String> rowMap = new HashMap<>();

		row.forEach(
				cell -> rowMap.put(header.getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase(),
						cell.getStringCellValue()));
		return rowMap;
	}

	private void salvaNoBanco(Lancamento lancamento) {
		produtoDao.adicionaSeNaoExiste(lancamento.getProduto());
		representanteDao.adicionaSeNaoExiste(lancamento.getCliente().getRepresentante());
		
		Cliente clienteDoBanco = clienteDao.adicionaSeNaoExiste(lancamento.getCliente());
		lancamento.setCliente(clienteDoBanco);
		
		
		lancamentoDao.adiciona(lancamento);
	}

	public int getProgress() {
		return progress;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
}
