import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a resource in a library
Author:  Linda Crane and Joseph Trottier
Course: F2018 - CST8130
Lab Section: 303
Data members:  title: String
               borrower: String
               dueDate: MyDate
               cost: float - over due charge
Methods: default constructor - uses intitialization  
         toString (): String - returns contents of the resource
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not
         isOverdue (MyDate): boolean - returns true/false if resource is overdue
         displayOverdue : - displays an overdue message when resource is returned (deleted)
*************************************************************************************************************/

public class Resource {
	protected String title = new String();
	protected String borrower = new String();
	protected MyDate dueDate = new MyDate();
	protected float cost = 0.0f;
	protected int hash;
	
	public Resource() {
	}
	
	public Resource (String title) {
		this.title = new String (title);
	}
	
	public String toString() {
		return borrower + " has " + title+ " due on " + dueDate.toString() + " and if late " + cost + "\n";
	}
	
	public boolean inputResource(Scanner in, MyDate today) { 
		cost = 0.0f;
		dueDate = new MyDate(today);
		
		System.out.print ("Enter title being borrowed: ");
		title = in.next();
		
		System.out.print ("Enter borrower name (no spaces): ");
		borrower = in.next();
		
		
		
		return true;
	}
	
	public boolean isOverDue (MyDate todayDate) {
		return todayDate.isGreaterThan(dueDate);
			
	}
	
	public void displayOverDue() {
		System.out.print( " This resource is overdue...borrower owes "  + "$" + cost);
	}
	
	public boolean isGreater(Resource rhs){
		if (this.title.compareToIgnoreCase(rhs.title) > 0)
			return true;
		else return false;
	}
	
	public boolean isEqual(Resource rhs){
		if (this.title.compareToIgnoreCase(rhs.title) == 0)
			return true;
		else return false;
	}
	
	public String toFile() {
		return borrower + " " + title+ " " + dueDate.toFile() + " " + cost ; 
	}
	
	public boolean inputResourceFromFile(Scanner in) {
		borrower = in.next();
		title = in.next();
		if (!dueDate.inputDateFromFile(in))
			return false;
		if (in.hasNextFloat()) {
			cost = in.nextFloat();
			return true;
		}
		else return false;
		
	}
	public int createHash(int x) 
	{
		int temp=0;
		for(int i=0;i<title.length(); i++) 
			temp +=	(int) title.toUpperCase().charAt(i);
		temp = temp % 1000;
		hash = temp;
		return temp;
	}
		
	


}

