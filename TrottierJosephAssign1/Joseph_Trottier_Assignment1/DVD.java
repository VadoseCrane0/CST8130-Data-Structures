import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will keep data for DVD extends resources
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  protected type : String - value of type of dvd
         Methods: default constructor - does nothing
         toString (): String - prints attributes of a DVD
         inputResources(Scanner MyDate): boolean - inputs data for resource
                                       boolean success or not


*************************************************************************************************************/


public class DVD extends Resource{
	protected String type;
	
	public DVD() {
	}
	
	public boolean inputResource(Scanner in, MyDate date){
		dueDate= new MyDate(date);
		System.out.println("Enter the type of DVD (no spaces): ");
		type = in.next();
		for (int i = 0; i<3; i++) {
			dueDate.addOne();
		}
		System.out.print(date + " " + dueDate);
		overdueCost=1;
		return true;
	}
	
	public String toString() {
		
		return " type of DVD: " + type+ "\n" + borrower + " has " + title+ " due on \n" + dueDate+ " and if late"+ overdueCost+ "\n";
	}
}