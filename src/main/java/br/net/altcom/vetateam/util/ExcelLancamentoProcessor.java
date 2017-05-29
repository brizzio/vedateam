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
import br.net.altcom.vetateam.modelo.Produto;
import br.net.altcom.vetateam.modelo.Representante;

public class ExcelLancamentoProcessor implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int progress;
	private InputStream file;
	private String sheetName;
	
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

		try (ExcelFactory excelFactory = new ExcelFactory(file)) {

			Excel excel = excelFactory.getExcel();
			ExcelSheet excelSheet = excel.getSheetByName(sheetName);
			excelSheet.begin();
			Row header = excelSheet.getHeader();

			//1
			new Thread(() -> {
				int index = 0;
				while(!excelSheet.isFinish()){
					List<Row> plusRow = excelSheet.getPlusRow(100);

					for (Row row : plusRow) {
						Map<String, String> rowMap = mapeiaLinha(header, row);
												
						Representante representante = new RepresentanteFactory().getRepresentante(rowMap);
						representanteDao.adicionaSeNaoExiste(representante);
						
						Cliente cliente = new ClienteFactory().getCliente(rowMap);
						cliente.setRepresentante(representante);
						
						Cliente clienteDoBanco = clienteDao.adicionaSeNaoExiste(cliente);						
						clienteDoBanco.setRepresentante(representante);
						
						Produto produto = new ProdutoFactory().getProduto(rowMap);
						Produto produtoDoBanco = produtoDao.adicionaSeNaoExiste(produto);
						
						Lancamento lancamento = new LancamentoFactory().getLancamento(rowMap);
						lancamento.setCliente(clienteDoBanco);
						lancamento.setProduto(produtoDoBanco);
						lancamentoDao.adiciona(lancamento);
						
						System.out.println("Adicionado: " + index++);
					}
				}
			}).start();


		} catch (NotOfficeXmlFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		progress = 100;
	}

	private Map<String, String> mapeiaLinha(Row header, Row row) {
		Map<String, String> rowMap = new HashMap<>();

		row.forEach(
				cell -> rowMap.put(header.getCell(cell.getColumnIndex()).getStringCellValue().toLowerCase(),
						cell.getStringCellValue()));
		return rowMap;
	}
	
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getProgress() {
		return progress;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
}
