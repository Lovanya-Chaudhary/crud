package com.rsystems.ayush;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.rsystems.ayush.excel.ReadingExcel;
import com.rsystems.ayush.excel.WriteExcel;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test02_Delete {
	@Test
	public void test02() throws FileNotFoundException, IOException
    {
		ReadingExcel rc=new ReadingExcel();   //object of the class  
			//reading the value of 2nd row and 2nd column  
			String vOutput=rc.ReadCell(2, 2);   
		Response response=RestAssured.delete(vOutput);
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		String output=response.asString();
		WriteExcel write=new WriteExcel();
		write.WriteCell(2,3,output);
		if(response.getStatusCode()==200)
		{
			write.WriteCell(2,4,"Pass");
		}
		else
		{
			write.WriteCell(2,4,"Fail");
		}
    }
	
}
