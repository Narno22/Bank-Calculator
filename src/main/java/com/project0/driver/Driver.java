package com.project0.driver;
import java.io.IOException;
import java.util.Scanner;
import com.project0.employee.Employee;
import com.project0.accounts.*;
import com.project0.customer.Customer;

public class Driver {
	
	public static void main(String[] args) throws IOException {
while(true) {
	//Ask if user is logging in as employee (y/n)
	Scanner sc = new Scanner(System.in);
	System.out.println("are you logging in as an administrator?\n type y or n");
	String answer = sc.nextLine();
	
	//if yes, ask for admin password.
	if (answer.equals("y")) {
		Employee admin = new Employee(); //password is private variable in Employee with an immutable setter
		System.out.println("please enter administrative password:\n");
		String pswd = sc.nextLine(); 
		if(pswd.equals(admin.getPassword())){ //compare the applicant's answer to the password
			System.out.println("You're in!");
			Accounts accountList = new Accounts();
			accountList.approveAccount();
			accountList.manageAccounts();
			System.out.println("\n Give the number of the account you want to manage");
			int index = Integer.parseInt(sc.nextLine());
			accountList.editAccount(index);
			
			/*
			 * DO ADMIN STUFF HERE!!!
			 */
		}  //in event of password failure, generate an error message and close program
		else {System.out.println("Sorry mac.  You ain't no administrator! \nTry again!");}
		System.exit(0);
	}
	//if no, ask if they have an account 
	else if(answer.equals("n")) {
		System.out.println("Would you like to apply for an account? Press y for yes or any other key to continue\n");
		if(sc.nextLine().equals("y")) {
			Customer newCustomer = new Customer();
			newCustomer.applyForAccount();
			System.exit(0);
		}
		Accounts accountList = new Accounts();
		System.out.println("please provide an account number:\n");
		accountList.editAccount(accountList.logIn(Integer.parseInt(sc.nextLine())));
		
		
		System.exit(0); 
	}
	
	else {System.out.println("You'll have to pick eventually!");}
}//end while
	
	}//end main
	
}//end class
