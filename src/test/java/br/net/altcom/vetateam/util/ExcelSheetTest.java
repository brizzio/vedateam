package br.net.altcom.vetateam.util;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelSheetTest {

	private static ExcelSheet excelSheet;

	@BeforeClass
	public static void pegaArquivoExcel() throws IOException{
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		excelSheet = new ExcelFactory(stream).getExcel().getSheetByName("Planilha1");
	}
	
	@Test
	public void devePegarALinhaUmDaPlanilha() throws IOException {
				
		Map<String, String> row = excelSheet.getRowAt(1);
		assertNotNull(row);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePegarUmaLinhaQueSejaMaiorOuigualQueAQuantidadeTotalDeLinhasDaTabela() throws IOException {
				
		excelSheet.getRowAt(excelSheet.getRowSize());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePegarUmaLinhaQueSejaNegativa() throws IOException {
				
		excelSheet.getRowAt(-4);		
	}

}
