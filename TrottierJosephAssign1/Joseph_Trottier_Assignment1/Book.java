import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will keep data for Book extends resources
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  protected author : String - value of author of book
         Methods: default constructor - does nothing
         toString (): String - prints attributes of a Book
         inputResources(Scanner MyDate): boolean - inputs data for resource


*************************************************************************************************************/


public class Book extends Resource {
	protected String author;
	
	public Book() {
	}
	
	public boolean inputResource(Scanner in, MyDate date){
		dueDate= new MyDate(date);
		System.out.println("Enter the author name (no spaces): ");
		author = in.next();
		for (int i = 0; i<14; i++) {
			dueDate.addOne();
		}
		overdueCost = 2;
		return true;
	}
	
	public String toString() {
		
		return " author of Book: " + author+ "\n" + borrower + " has " + title+ " due on \n" + dueDate+ " and if late "+ overdueCost+ " \n";
	}
}
