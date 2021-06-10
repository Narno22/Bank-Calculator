package com.project0.customer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Customer {
	
	private String accountFile[] = new String[5];
	private ArrayList<String> records = new ArrayList<String>();
	private File recordReader = new File("Records.txt");
	private Scanner scanner = new Scanner(recordReader); //reads the file
	private Scanner user = new Scanner(System.in); //reads user inputs
	private int i; //for the size of the existing accounts list
	//a StringBuilder to reformat and return the modified data to the arrayList
	StringBuilder modifiedData = new StringBuilder("");
	
	//constructor. Load first file array list (to get length and set proposed account number)  
	//Next, load the parameters into the recordWriter array using the constructor
	//Load number and rest of array into "NewApplications.txt"
	public Customer() throws IOException {
		//add file records to arrayList
		while(this.scanner.hasNextLine()) {
		this.records.add(scanner.nextLine());
				}
		this.i = records.size();
	}
	
	public void applyForAccount() throws IOException {
		accountFile[0] = String.valueOf(i);
		System.out.println("Please enter primary user name:");
		accountFile[1] = user.nextLine();
		System.out.println("Please enter secondary user name:");
		accountFile[2] = user.nextLine();
		System.out.println("Please enter Initial deposit amount:");
		accountFile[3] = user.nextLine();
		System.out.println("Please enter a password:");
		accountFile[4] = user.nextLine();
		
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
