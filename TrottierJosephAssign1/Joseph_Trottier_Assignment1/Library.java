import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a simple Library by keeping track of borrowed and overdue resources
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  resourcesBorrowed : Resource[] - Array of borrowed resources(private)
               numResources: int - Holds the current value of the last element of the array
               max: int - max fill of array
Methods: default constructor - sets resourceBorrowed array to be of value max
         toString (): String - prints items currently borrowed from library
         inputResource(Scanner in, MyDate date): deals with inputting, creates resources and enters the variables
         resourcesOverDue(MyDate today): String - prints overdue resources.
         deleteResource(Scanner in, MyDate date): void - Deletes data from array
                                                     
         

*************************************************************************************************************/


public class Library {
	private Resource[] resourcesBorrowed;
	private int numResources = 0;
	private int max=1000;
	
	public Library() {
		this.resourcesBorrowed = new Resource[max];
	}
	
	public 	void inputResource(Scanner in, MyDate date) {
		if (numResources == max) {
			System.out.println("Error – array is full Back to menu");
		}else {
			String choice = "";
			System.out.println("Enter type of resource being borrowed - D for DVD, M for Magazine and B for book: ");
			choice = in.next();
			if (choice.equals("b") || choice.equals("b")) {
				resourcesBorrowed[numResources] = new Book();
				System.out.println("Enter title being borrowed(No Spaces): ");
				resourcesBorrowed[numResources].title = in.next();
				System.out.println("Enter borrower name (no spaces): ");
				resourcesBorrowed[numResources].borrower = in.next();
				
			}else if(choice.equals("D") || choice.equals("d")) {
				resourcesBorrowed[numResources] = new DVD();
				System.out.println("Enter title being borrowed(No Spaces): ");
				resourcesBorrowed[numResources].title = in.next();
				System.out.println("Enter borrower name (no spaces): ");
				resourcesBorrowed[numResources].borrower = in.next();
		
			}else if(choice.equals("M") || choice.equals("m")) {
				resourcesBorrowed[numResources] = new Magazine();
				System.out.println("Enter title being borrowed(No Spaces): ");
				resourcesBorrowed[numResources].title = in.next();
				System.out.println("Enter borrower name (no spaces): ");
				resourcesBorrowed[numResources].borrower = in.next();
				
			}else {
				System.out.println("Input invalid. Default to add book");
				resourcesBorrowed[numResources] = new Book();
				System.out.println("Enter title being borrowed(No Spaces): ");
				resourcesBorrowed[numResources].title = in.next();
				System.out.println("Enter borrower name (no spaces): ");
				resourcesBorrowed[numResources].borrower = in.next();
				
			}
			resourcesBorrowed[numResources].inputResource(in, date);
		numResources++;
		}
	}
	
	public String toString() {
		System.out.println("Items currently borrowed from library that are");
		for (int i=0;i<numResources;i++) {
			System.out.println((i+1)+":" + resourcesBorrowed[i].toString());
		}
		return "";
	}
	
	public String resourcesOverDue(MyDate today) {
		//FOR LOOP TO TEST EACH VALUE OF ARRAY
		System.out.println("Items currently borrowed from library that are overdue as of " + today +" are:");
		String string = "";
		for(int i = 0;i<numResources;i++) {
			if (resourcesBorrowed[i].isOverDue(today)==true) {
				System.out.println (i + resourcesBorrowed[i].toString());
				
			}else {
				//SET LOSE CONDITION
			}
			
			
		}
		return "";
	}
	
	public void deleteResource(Scanner in, MyDate date) {
		boolean done = false;
		int choice;
		System.out.println("List of resources checked out in the library: ");
		for (int i=0;i<numResources;i++) {
			System.out.println((i+1)+":" + resourcesBorrowed[i].toString());
		}
		do {
			System.out.print("Which resource to delete: ");
			if (in.hasNextInt()) {
				choice = in.nextInt();
				if (choice > numResources || choice <= 0) {
					System.out.print("invalid choice");
				}else if(resourcesBorrowed[choice-1].isOverDue(date)==true) {
					System.out.println("this is overdue and will cost "+ resourcesBorrowed[choice-1].overdueCost);
				}else {
					for (int i=choice;i<numResources;i++) {
						resourcesBorrowed[i-1]= resourcesBorrowed[i];
					}
					numResources--;
					done=true;
					
				}
			}else {
				System.out.println ("Invalid choice.");
				in.next();
			}
		}while(!done);
	}
}
