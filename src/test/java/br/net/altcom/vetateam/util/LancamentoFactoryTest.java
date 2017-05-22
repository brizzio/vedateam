package br.net.altcom.vetateam.util;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.net.altcom.vetateam.modelo.Lancamento;

public class LancamentoFactoryTest {

	private static ExcelSheet excelSheet;

	@BeforeClass
	public static void pegaArquivoExcel() throws IOException{
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		excelSheet = new ExcelFactory(stream).getExcel().getSheetByName("Planilha1");
	}
	
	@Test
	public void devePegarTodosOsLancamentosDeUmaPlanilhaDoExcel() {
		LancamentoFactory lancamentoFactory = new LancamentoFactory(excelSheet);
		List<Lancamento> getlancamentos = lancamentoFactory.getlancamentos();
		assertEquals(excelSheet.getRowSize()-1, getlancamentos.size());
	}

}
