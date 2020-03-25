import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a dynamically allocated array of Resource objects to represent the resources
           that have been borrowed from a Library
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members:  resourcesBorrowed: Resource[] - the array
               numResources: int - how many resources are currently stored in array (borrowed)
               max: int - maximum number of resources in the array (Optional - could just use array length)
Methods: default constructor - uses intitialization at declaration of 1000 spaces in array
         initial constructor (int) - initializes array to size in parameter 
         toString (): String - contents of array
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not
         itemsOverdue (MyDate): String - displays resources that are overdue
         deleteResource (Scanner, MyDate): - displays a list of resources and prompts user to select which to delete, then deletes it

*************************************************************************************************************/

public class Library {
	private ArrayList<Resource> resourcesBorrowed = new ArrayList<Resource>(1000);
	private Resource temp;
	private int max = 1000;
	private int numResources = 0;
	
	public Library () {
	}
	
	public Library (int max) {
		if (max > 0) {
			this.max = max;
			resourcesBorrowed = new ArrayList<Resource>(max);
		}
		// note this defaults to 1000 by initialization if paramter max <= 0
	}
	
	public String toString() {
		String out = "\nItems currently borrowed from library are:\n";
		for (int i = 0; i < numResources; i++)
			out += (i+1) + ": " + resourcesBorrowed.get(i).toString();
		return out;
	}
	
	public boolean inputResource(Scanner in, MyDate today) {
		String type = new String();
		
		if (numResources == max) {
			System.out.println ("No more room in data base");
			return false;
		}
		/*code to resize - not necessary for assignment grades.....
		 if (numResources == max) {
		 	Resource [] temp = new Resource[2*max];
		 	for (int i = 0; i < max; i++)
		 		temp[i] = resourcesBorrowed[i];
		 	resourcesBorrowed = temp;
		 	max = 2*max;
		 }
		 */
		char choice = 'k';
		while (! (choice == 'D' || choice == 'M' || choice == 'B')) {
			System.out.print ("Enter type of resource being borrowed - D for DVD, M for Magazine and B for book:");
			choice = in.next().toUpperCase().charAt(0);
		}
		if (choice == 'D')
			temp = new DVD();
		else if (choice == 'M')
			temp = new Magazine();
		else 
			temp = new Book();
		temp.inputResource(in, today);
		binarySearch(temp);
		return true;
	}
	
	public String itemsOverDue(MyDate todayDate) {
		String out = "Items currently borrowed from library that are overdue as of " + todayDate.toString() + " are:\n";
		for (int i = 0; i < numResources; i++)
			if (resourcesBorrowed.get(i).isOverDue(todayDate))
				out += (i+1) + ": " + resourcesBorrowed.get(i).toString();
		return out;
	}
	
	public void deleteResource (Scanner in, MyDate today) {
		if (numResources == 0) {
				System.out.println ("No resources to delete\n");
				return;
		}
		
		System.out.println ("List of resources checked out in the library: ");
		for (int i=0; i < numResources; i++) {
			System.out.print("[" + (i+1) + "]: " + resourcesBorrowed.get(i).toString());
		}
		
	
		System.out.println ("Which resource to delete: ");
		int numToDelete = 0;
		boolean reEnter = true;
		do {
			if (in.hasNextInt()) {
				numToDelete = in.nextInt();
				if (numToDelete < 0 || numToDelete > numResources) {
					System.out.println ("Invalid ...  please reenter number between 0 and " + numResources);
				}
				else reEnter = false;
			}
			else {
				System.out.println ("Invalid...please reenter valid integer");
				in.next();
			}
			
		} while (reEnter);
		
		if (resourcesBorrowed.get(numToDelete-1).isOverDue(today)) {
			resourcesBorrowed.get(numToDelete-1).displayOverDue();
		}
		resourcesBorrowed.remove(numToDelete);
		numResources --;
	}
	public String searchResourceTitle(Scanner in) {
		String temp;
		String out = "";
		if (numResources>0) {
		System.out.print ("Enter the title to search for: ");
		temp = in.next();
		for (int i = 0; i < numResources; i++)
			if (resourcesBorrowed.get(i).title.equals(temp))
				out += (i+1) + ": " + resourcesBorrowed.get(i).toString();
		if(out.equals(""))
			out = "Resource with this title is not found";
		}
		else {
			out = "No resource to view";
		}
		return out;
		
	}
	public void loadData(Scanner in) {
		char choice = in.next().toUpperCase().charAt(0);
		if (choice == 'D') {
			temp=new DVD();
		}
		else if (choice == 'M') {
			temp=new Magazine();
		}
		else {
			temp=new Book();
		}
		temp.loadData(in);
		binarySearch(temp);
		
	}
	public String savefile() {
		String temp="";
		for (int i = 0; i < numResources; i++) 
			temp += resourcesBorrowed.get(i).savefile() + "\n";
		return temp;
	}	
	public int binarySearch(Resource temp) {
		int low=0,high=resourcesBorrowed.size()-1,middle=0;
		while(low<=high) {
			middle =(low+high)/2;
			if(resourcesBorrowed.get(middle).title.compareToIgnoreCase(temp.title)>0) {
				high=middle-1;
			}else {
				middle++;
				low=middle;
			}
		}
		resourcesBorrowed.add(middle, temp);
		numResources++;
		return middle;
	}
	
}
