import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will keep data for Magazine extends resources
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  protected edition : MyDate - value of date the edition was published
         Methods: default constructor - does nothing
         toString (): String - prints attributes of a Magazine
         inputResources(Scanner MyDate): boolean - inputs data for resource


*************************************************************************************************************/

public class Magazine extends Resource{
	protected MyDate edition = new MyDate();
	
	public Magazine() {
	}
	
	public boolean inputResource(Scanner in, MyDate date){
		dueDate= new MyDate(date);
		System.out.println("Enter the edition date: ");
		edition.inputDate(in);
		for (int i = 0; i<7; i++) {
			dueDate.addOne();
		}
		overdueCost=1;
		return true;
	}
	
	public String toString() {
		
		return " edition of Magazine: " + edition + "\n" + borrower + " has " + title+ " due on \n" + dueDate+ " and if late"+ overdueCost+ "\n";
	}
}
