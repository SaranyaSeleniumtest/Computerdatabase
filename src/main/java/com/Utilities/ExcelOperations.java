package com.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
	static XSSFWorkbook wb;
	static XSSFSheet sheetAt;
	
	public ExcelOperations() throws IOException {
		String file= System.getProperty("user.dir")+"//testdata//sample.xlsx";
		FileInputStream fis= new FileInputStream(file);
		wb= new XSSFWorkbook(file);
		sheetAt = wb.getSheetAt(0);

	}

	//method to get row count of sheet
	public int getrowcnt() throws IOException {
		return sheetAt.getLastRowNum();
	}

	//method to get col count of sheet
	public int getcolcnt() throws IOException {
		return sheetAt.getRow(0).getLastCellNum();
	}

	public static void main(String[] args) throws IOException  {
		//		Excelutil excel= new Excelutil();
		//		System.out.println(excel.getrowcnt());
		//		System.out.println(excel.getcolcnt());
		System.out.println(exceloperations(2));
	}
	public static HashMap<String, String> exceloperations(int rownum) throws IOException {
		ExcelOperations excel= new ExcelOperations();
		HashMap<String, String> hm= new HashMap<String, String>();

		for(int j=0;j<excel.getcolcnt();j++) {
			sheetAt.getRow(rownum).getCell(j).setCellType(CellType.STRING);
			hm.put(sheetAt.getRow(0).getCell(j).toString(), sheetAt.getRow(rownum).getCell(j).toString());


		}
		return hm;

	}

}

