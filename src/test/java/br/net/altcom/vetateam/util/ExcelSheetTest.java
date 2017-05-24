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
	
}
