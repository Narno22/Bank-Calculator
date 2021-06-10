package com.project0.accounts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// Accounts needs to be able to access the Records.text file and load it into an ArrayList
public class Accounts  { 
	
		//Declare file scanner and records arrayList
		private ArrayList<String> records = new ArrayList<String>();
		private ArrayList<String> pend = new ArrayList<String>();
		private File recordReader = new File("Records.txt"); //existing accounts
		private File pending = new File("NewApplications.txt"); //Reads NewApplications file
		private Scanner scanner = new Scanner(recordReader); //reads the existing accounts file
		private Scanner file2 = new Scanner(pending); //reads the pending accounts file
		private Scanner user = new Scanner(System.in); //reads user inputs
		//a StringBuilder to reformat and return the modified data to the arrayList
		StringBuilder modifiedData = new StringBuilder("");
		StringBuilder recip = new StringBuilder(""); //for transfers
		
				
		//constructor
		public Accounts() throws IOException {
		//add file records to arrayList
		while(this.scanner.hasNextLine()) {
		this.records.add(scanner.nextLine());
		}
		//add any pending accounts to "pend" arraylist
		while(this.file2.hasNextLine()) {
			this.pend.add(file2.nextLine());
			}
		
	}
		
		//List available accounts, and allow editing of a selected account for managers
		public void manageAccounts() {
		System.out.println("here is a list of bank accounts:");
		for(int i=0; i<records.size();i++)
		System.out.println(records.get(i));
	}
		
		//log in to your account 
		public int logIn(int index) {
			String yourAccount[] = records.get(index).split(",");
			while(true){
			System.out.println("Please enter your credentials");
			String credentials = user.nextLine();
			if(credentials.equals(yourAccount[4])) {System.out.println("Welcome!"); break;}
			else {System.out.println("Try Again");}
			}
			
			return index;
		}
	 	
		//manage an individual account
	 	public void editAccount(int i) throws IOException {
		//split up a single requested record into editable fields
		String singleRecord[] = records.get(i).split(",");
		//split and print the account information
		System.out.println("Here is the Account Info:");
		for(int j=0; j<singleRecord.length; j++) {System.out.println(singleRecord[j]);}
		
		//generate balance and make some changes
		float balance = Float.parseFloat(singleRecord[3]);
		float amount;
		float newBalance;
				
		while(true) {
		System.out.println("Would you like to do?\n"
				+ "+ = deposit; - = withdraw; d = delete account; c = commit changes; x = close program\n"
				+ "t = transfer");
		String order = user.nextLine();
		
		//deposits
		if(order.equals("+")) {
			while(true) {
			System.out.println("How much?");
			amount = user.nextFloat(); //modified.  Change back to parseFloat if needed
			newBalance = balance + amount;
			if(newBalance < 0) {System.out.println("Subzero error.  Try again.");}
			else {singleRecord[3] = String.valueOf(newBalance); 
			System.out.println("Your New Balance Is: " + singleRecord[3]);
			break; 	}
			}
			
		}
		
		//withdrawal
		else if(order.equals("-")) {
			while(true) {
			System.out.println("How much?");
			amount = user.nextFloat(); //modified. Change back to parseFloat if needed
			newBalance = balance - amount;
			if(newBalance < 0) {System.out.println("Subzero error.  Try again.");}
			else {singleRecord[3] = String.valueOf(newBalance); 
			System.out.println("Your New Balance Is: " + singleRecord[3]);
			break; 	}
			}
			
		}
		
		//Transfer 
		else if(order.equals("t")) {
			//load another account into an array (recipient)
			System.out.println("Account number?");
			int recipientNumber = user.nextInt();
			String recipient[] = records.get(recipientNumber).split(",");
			float recipientBalance = Float.parseFloat(recipient[3]);
			//perform a withdrawal on singleRecord, and a deposit on recipient
			while(true) {
			System.out.println("How much?");
			amount = user.nextFloat(); //modified. Change back to parseFloat if needed
			newBalance = balance - amount;
			if(newBalance < 0) {System.out.println("Subzero error.  Try again.");}
			else {
			recipientBalance = recipientBalance + amount;
			recipient[3] = String.valueOf(recipientBalance);
			singleRecord[3] = String.valueOf(newBalance); 
			System.out.println("Your New Balance Is: " + singleRecord[3]);
			System.out.println("And Your Friend's Balance is:" + recipient[3]);
			
			//change data back to comma separated
			for(int l = 0; l<singleRecord.length; l++) {modifiedData.append(singleRecord[l]).append(",");}
			for(int l = 0; l<recipient.length; l++) {recip.append(recipient[l]).append(",");}			
			
			//move modified data back to it's place in the list
			records.set(Integer.parseInt(singleRecord[0]),modifiedData.toString());
			records.set(Integer.parseInt(recipient[0]),recip.toString());
						
			//...and finally back into the text file
			FileWriter fw = new FileWriter("Records.txt");
			for(int m = 0; m < records.size(); m++) {fw.append(records.get(m) + "\n");}
			fw.close();
			
			
			break; 	}
			}
			
		}
		
		//commit changes to recipient
		
		
		//deletion
		else if(order.equals("d")) {
			//Change all but first value to null
			for(int k=1;k<singleRecord.length;k++) {
				if(k==3) {singleRecord[k] = "0.00";} //to prevent number conversion errors
				else singleRecord[k] = "NULL";}
			System.out.println("DUH-LEET-ED!");
		}
		
		//close program
		else if(order.equals("x")) {System.out.println("See you soon!"); System.exit(0);}
		
		//Commit Changes 
		else if(order.equals("c")) {
			System.out.println("Writing your changes to file");
			
			//change data back to comma separated
			for(int l = 0; l<singleRecord.length; l++) {modifiedData.append(singleRecord[l]).append(",");}
						
			//move modified data back to it's place in the list
			records.set(Integer.parseInt(singleRecord[0]),modifiedData.toString());
						
			//...and finally back into the text file
			FileWriter fw = new FileWriter("Records.txt");
			for(int m = 0; m < records.size(); m++) {fw.append(records.get(m) + "\n");}
			fw.close();
			
		}
		}
		
	 }
	 	
	 //Add account (called in employee)
	 public void approveAccount() throws IOException {
		 while(true) {
		 System.out.println("here is a list of pending accounts:");
		 for(int i=0; i<pend.size();i++) {
		 System.out.println(pend.get(i));
		 }
		 System.out.println("Approve?  y/n");
		 String userInput = user.nextLine();
		 if (userInput.equals("y") && pend.size() > 0) {
			//move modified data back to it's place in the list
			records.add(pend.get(0));
			//...and finally back into the text file
			FileWriter fw = new FileWriter("Records.txt");
			FileWriter fw2 = new FileWriter("NewApplications.txt");
			for(int m = 0; m < records.size(); m++) {fw.append(records.get(m) + "\n");}
			fw2.write(""); //delete file
			fw.close();
			fw2.close();
			System.out.println("We have a new account on the roster");
			break;
		 }
		 else if(userInput.equals("n")) { System.out.println("OK, moving on..." ); break; }
		 else {System.out.println("Interpreting vague answer as no.  Moving on..."); break; }
	 }
	 }

}//end of Accounts class
