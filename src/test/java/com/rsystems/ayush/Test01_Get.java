package com.rsystems.ayush;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.rsystems.ayush.excel.ReadingExcel;
import com.rsystems.ayush.excel.WriteExcel;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test01_Get {
	
	@Test
	public void test01() throws InvalidFormatException, IOException,FileNotFoundException
    {
		
		ReadingExcel rc=new ReadingExcel();   //object of the class  
			//reading the value of 2nd row and 2nd column  
			String vOutput=rc.ReadCell(1, 2);   
		Response response=RestAssured.get(vOutput);
				
		System.out.println(response.asString());
		String output=response.asString();
		System.out.println("actual output"+output);
		System.out.println(response.getStatusCode());
		

		WriteExcel write=new WriteExcel();
		write.WriteCell(1,3,output);
		if(response.getStatusCode()==200)
		{
			write.WriteCell(1,4,"Pass");
		}
		else
		{
			write.WriteCell(1,4,"Fail");
		}
    }
	
	
	
}
