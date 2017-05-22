package br.net.altcom.vetateam.util;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ExcelFactoryTest {

	@Test
	public void devePegarUmaPlanilhaPeloNome() throws IOException {
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		ExcelFactory excelFactory = new ExcelFactory(stream);
				
		ExcelSheet excelSheet = excelFactory.getSheetByName("Planilha1");
		assertEquals("Planilha1", excelSheet.getSheetName());
	}
	
	@Test(expected = NullPointerException.class)
	public void NaoDevePegarUmaPlanilhaComUmNomeInvalido() throws IOException {
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		ExcelFactory excelFactory = new ExcelFactory(stream);
				
		ExcelSheet excelSheet = excelFactory.getSheetByName("PlanilhaComNomeInvalido");
		assertEquals("Planilha1", excelSheet.getSheetName());
	}

}
