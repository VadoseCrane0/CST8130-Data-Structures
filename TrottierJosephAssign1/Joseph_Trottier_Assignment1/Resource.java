import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will keep data for Book extends resources
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  protected title : String - value of title
		 protected borrower : String - value of borrower
		 protected dueDate : MyDate - holds a MyDate value for the dueDate
		 protected overdueCost : float - float number for cost of resource when overdue
Methods: default constructor - does nothing
         inputResource(Scanner in, MyDate date): boolean - refer to Magazine, DVD, Or Book
         toString(): boolean - refer to Magazine, DVD, Or Book
         isOverDue(MyDate today) - returns true if overdue returns false if not overdue
         


*************************************************************************************************************/


public class Resource {
	protected String title, borrower;
	protected MyDate dueDate;
	protected float overdueCost;
	
	public Resource() {
	}
	
	public boolean inputResource(Scanner in, MyDate date) {
		return true;
	}
	
	public String toString() {
		return "";
	}
	
	public boolean isOverDue(MyDate today) {
		if (dueDate.isGreaterThan(today)) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
