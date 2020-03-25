import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will extend a Resource for a Magazine resource
Author:  Linda Crane and Joseph Trottier
Course: F2018 - CST8130
Lab Section: 303
Data members:  edition: MyDate
Methods: default constructor - uses intitialization 
         toString (): String - returns this resources' info
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not.  Calls the 
                           superclass version - then fills in dueDate and cost to reflect values for magazine
         
*************************************************************************************************************/

public class Magazine extends Resource{
	private MyDate edition = new MyDate();
	
	public Magazine() {
		
	}
	
	public String toString() {
		return " edition " + edition.toString() + " " + super.toString();
	}
	
	public boolean inputResource(Scanner in, MyDate today) {
		if (!super.inputResource(in, today))
			return false;
		
		System.out.print ("Enter the edition date: ");
		if (!edition.inputDate(in))
			return false;
		
		// due date for magazine is 7 days from "today"
		for (int i=0; i<7; i++)
			dueDate.addOne();
		
		// late cost is flat $1
		cost = 1.00f;
		
		
		return true;
	}
	
	public String toFile() {
		return "m " + super.toFile() + " " + edition.toFile() + "\n";
	}
	
	public boolean inputResourceFromFile(Scanner in) {
		if (!super.inputResourceFromFile(in)) {
			return false;
		}
		return edition.inputDateFromFile(in);
	}

}

