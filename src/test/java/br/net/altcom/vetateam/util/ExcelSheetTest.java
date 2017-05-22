package br.net.altcom.vetateam.util;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelSheetTest {

	private static ExcelFactory excelFactory;

	@BeforeClass
	public static void pegaArquivoExcel() throws IOException{
		InputStream stream = new FileInputStream("./FileTest/modeloDados.xlsx");
		excelFactory = new ExcelFactory(stream);		
	}
	
	@Test
	public void devePegarALinhaUmDaPlanilha() throws IOException {
				
		ExcelSheet excelSheet = excelFactory.getSheetByName("Planilha1");
		Map<String, String> row = excelSheet.getRowAt(1);
		assertNotNull(row);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePegarUmaLinhaQueSejaMaiorOuigualQueAQuantidadeTotalDeLinhasDaTabela() throws IOException {
				
		ExcelSheet excelSheet = excelFactory.getSheetByName("Planilha1");
		excelSheet.getRowAt(excelSheet.getRowSize());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDevePegarUmaLinhaQueSejaNegativa() throws IOException {
				
		ExcelSheet excelSheet = excelFactory.getSheetByName("Planilha1");
		excelSheet.getRowAt(-4);
		
	}

}
