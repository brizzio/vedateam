package br.net.altcom.vetateam.util;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.net.altcom.vetateam.modelo.Lancamento;

public class LancamentoFactoryTest {

	private static ExcelFactory excelFactory;
	private static ExcelSheet excelSheet;

	@BeforeClass
	public static void pegaArquivoExcel() throws IOException{
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		excelFactory = new ExcelFactory(stream);
	}
	
	@Before
	public void pegaPlanilhaDoExcel(){
		excelSheet = excelFactory.getSheetByName("Planilha1");
	}
	
	@Test
	public void devePegarTodosOsLancamentosDeUmaPlanilhaDoExcel() {
		LancamentoFactory lancamentoFactory = new LancamentoFactory(excelSheet);
		List<Lancamento> getlancamentos = lancamentoFactory.getlancamentos();
		assertEquals(excelSheet.getRowSize()-1, getlancamentos.size());
	}

}
