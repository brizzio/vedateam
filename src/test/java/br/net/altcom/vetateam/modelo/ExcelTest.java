package br.net.altcom.vetateam.modelo;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.BeforeClass;
import org.junit.Test;

import br.net.altcom.vetateam.util.ExcelFactory;
import br.net.altcom.vetateam.util.ExcelSheet;

public class ExcelTest {

	private static Excel excel;

	@BeforeClass
	public static void pegaArquivoParaTeste() throws NotOfficeXmlFileException, IOException {
		FileInputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		excel = new ExcelFactory(stream).getExcel();
	}

	@Test
	public void devePegarUmaPlanilhaPeloNome() throws NotOfficeXmlFileException, IOException {
		
		ExcelSheet excelSheet = excel.getSheetByName("Planilha1");
		assertEquals("Planilha1", excelSheet.getSheetName());
	}

	@Test(expected = NullPointerException.class)
	public void NaoDevePegarUmaPlanilhaComUmNomeInvalido() throws NotOfficeXmlFileException, IOException {
		
		@SuppressWarnings("unused")
		ExcelSheet excelSheet = excel.getSheetByName("PlanilhaComNomeInvalido");
	}
}
