//It's for testing everything really

package com.project0.test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//test imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


public class CustomerTest {
	
	//Declare a few things I need to run this application
	static String accountFile[] = new String[5];
	private ArrayList<String> records = new ArrayList<String>();
	private File recordReader = new File("Records.txt");
	private int i = 3;
	static StringBuilder modifiedData = new StringBuilder("");
	
	
	@Test
	@DisplayName("writing to the applicants file should not have trouble")
	public static void applyForAccount() throws IOException {
		accountFile[0] = String.valueOf(0);
		System.out.println("Please enter primary user name:");
		accountFile[1] = "Mr Cheese";
		System.out.println("Please enter secondary user name:");
		accountFile[2] = "Mrs Cheese";
		System.out.println("Please enter Initial deposit amount:");
		accountFile[3] = "30.75";
		System.out.println("Please enter a password:");
		accountFile[4] = "password";
		
		System.out.println("Your requested account details are:");
		for(int j = 0; j < accountFile.length; j++) {
			System.out.print(accountFile[j] + ",");
		}
		System.out.println("\n");
		
		//Write to the NewApplications File
		System.out.println("Writing your changes to file");
		
		//change data back to comma separated
		for(int l = 0; l<accountFile.length; l++) {modifiedData.append(accountFile[l]).append(",");}
		FileWriter recordWriter = new FileWriter ("NewApplications.txt");
		//Store for later
		recordWriter.append(modifiedData.toString());
		recordWriter.close();
		
		System.out.println("Thanks for applying!  An administrator will review your details and let you know if you are approved.");
	}
	
	
	

	

}
