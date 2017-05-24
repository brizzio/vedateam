package br.net.altcom.vetateam.util;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.net.altcom.vetateam.modelo.Excel;

public class ExcelFactoryTest {

	private static InputStream arquivoExcel;
	private InputStream arquivoErrado;

	@BeforeClass
	public static void pegaArquivoParaTeste() throws FileNotFoundException {
		arquivoExcel = new FileInputStream("./FileTest/modeloDados.xlsx");
	}

	@Before
	public void pegaArquivoErrado() throws FileNotFoundException {
		arquivoErrado = new FileInputStream("./FileTest/texto.txt");
	}

	@Test(expected = NotOfficeXmlFileException.class)
	public void naoDeveExecutarCasoNaoSejaUmArquivoExcel() throws IOException {

		@SuppressWarnings("unused")
		Excel excel = new ExcelFactory(arquivoErrado).getExcel();
	}
	
	@Test
	public void deveExecutarCasoSejaUmArquivoExcel() throws NotOfficeXmlFileException, IOException{		
		Excel excel = new ExcelFactory(arquivoExcel).getExcel();
		assertNotNull(excel);
	}
}
