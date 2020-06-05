package com.rsystems.ayush.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public void WriteCell(int vRow,int vColumn,String content)throws FileNotFoundException, IOException
	{ 
		//int vRow=0,vColumn=0;
		//content="abc";
		
	    final String location="F:\\Eclipse 2012\\automation-tool\\Automation-Poc\\util\\Output.xlsx";
	   // FileOutputStream out = new FileOutputStream(new File(location)); 
	    //XSSFWorkbook workbook = new XSSFWorkbook(); 
	    
	    File file = new File(location); 
	    FileInputStream fip = new FileInputStream(file); 
	    Workbook workbook = new XSSFWorkbook(fip);
	    
	   
	    Sheet sheet = workbook.getSheetAt(0);
	    Row rowId = sheet.getRow(vRow);
	    Cell cell = rowId.createCell(vColumn);
	    System.out.println("value of content is:"+content);
	    cell.setCellValue(content);
	    System.out.println("now writing");
	    FileOutputStream out = new FileOutputStream(new File(location));
	    workbook.write(out);
	    out.close(); 
	    workbook.close();
        System.out.println("output file written successfully on disk.");
	} 

}
