package com.rsystems.ayush;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.rsystems.ayush.excel.ReadingExcel;
import com.rsystems.ayush.excel.WriteExcel;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Test03_Post {
	
	@Test
	public void Test03() throws FileNotFoundException, IOException{
	ReadingExcel rc=new ReadingExcel();   //object of the class  
	//reading the value of 2nd row and 2nd column  
	String vOutput=rc.ReadCell(3, 2);
	
	String requestBody = "{\n" +
            "  \"id\": \"999\",\n" +
            "  \"companyName\": \"Adobe\",\n" +
            "  \"companyLocation\": \"pune\"\n" +
            "}";
	
	Response response = null;
	 
    try {
        response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .post(vOutput);
    } catch (Exception e) {
        e.printStackTrace();
    }
	
	System.out.println(response.getStatusCode());
	 System.out.println(response.asString());
	 String output=response.asString();
	 WriteExcel write=new WriteExcel();
		write.WriteCell(3,3,output);
		
		if(response.getStatusCode()==201)
		{
			write.WriteCell(3,4,"Pass");
		}
		else
		{
			write.WriteCell(3,4,"Fail");
		}
}
}
