package com.rsystems.ayush;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.rsystems.ayush.excel.ReadingExcel;
import com.rsystems.ayush.excel.WriteExcel;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test04_Put {
	@Test
	public void test04() throws FileNotFoundException, IOException
	{
		ReadingExcel rc=new ReadingExcel();   //object of the class  
		//reading the value of 2nd row and 2nd column  
		String vOutput=rc.ReadCell(4, 2);
		
		String requestBody = "{\n" +
	            "  \"id\": \"999\",\n" +
	            "  \"companyName\": \"Nucleus\",\n" +
	            "  \"companyLocation\": \"pune\"\n" +
	            "}";
		
		Response response = null;
		 
        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(vOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(response.getStatusCode());
   	 System.out.println(response.asString());
   	String output=response.asString();
	 WriteExcel write=new WriteExcel();
		write.WriteCell(4,3,output);
		if(response.getStatusCode()==200)
		{
			write.WriteCell(4,4,"Pass");
		}
		else
		{
			write.WriteCell(4,4,"Fail");
		}
	}

}
