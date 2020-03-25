import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
/************************************************************************************************************
Purpose:  This class will model a dynamically allocated array of Resource objects to represent the resources
           that have been borrowed from a Library
Author:  Linda Crane and Joseph Trottier
Course: F2018 - CST8130
Lab Section: 303
Data members:  resourcesBorrowed: ArrayList<Resource> - the array
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
	private ArrayList<LinkedList<Resource>> resourcesBorrowed = new ArrayList<LinkedList<Resource>>(1000);
	public Library () {
		for(int i=0;i<1000;i++)
			resourcesBorrowed.add(null);
	}
	
	public String toString() {
		String out = "\nItems currently borrowed from library are:\n";
		for (int i = 0; i < resourcesBorrowed.size(); i++) {
			if(resourcesBorrowed.get(i) != null) {
				out += (i+1) + ": [";
				for(int j=0;j<resourcesBorrowed.get(i).size();j++)
					out += " " + resourcesBorrowed.get(i).get(j).toString() + "\n";
				out += "]";
			}
		}
		return out;
	}
	
	public int findIndex(Resource toFind) {
		int x =toFind.createHash(resourcesBorrowed.size());
		return x;   // this is where to insert
	}
	
	public boolean inputResource(Scanner in, MyDate today) {
		
		String type = new String();
		char choice = 'k';
		while (! (choice == 'D' || choice == 'M' || choice == 'B')) {
			System.out.print ("Enter type of resource being borrowed - D for DVD, M for Magazine and B for book:");
			type  = in.next();
			type = type.toUpperCase();
			choice = type.charAt(0);
		}
		
		Resource temp;
		if (choice == 'D')
			temp= new DVD();
		else if (type.charAt(0) == 'M')
			temp = new Magazine();
		else temp = new Book();
		
		temp.inputResource(in, today);
		
		int index = temp.createHash(resourcesBorrowed.size());
		if (resourcesBorrowed.get(index)== null)
			resourcesBorrowed.set(index, new LinkedList<Resource>());
		resourcesBorrowed.get(index).add(temp);
		return true;
			
	}
	
/*public boolean addResourceFromFile(Scanner in) {
		
		if (!in.hasNext())
			return false;
		String type  = in.next();
		type = type.toUpperCase();
		char choice = type.charAt(0);
		
		Resource temp;
		if (choice == 'D')
			temp= new DVD();
		else if (type.charAt(0) == 'M')
			temp = new Magazine();
		else temp = new Book();
		
		if (!temp.inputResourceFromFile(in))
			return false;
		
		int indexToAdd = findIndex(temp);
		resourcesBorrowed.add(indexToAdd, temp);
		return true;
			
	}*/
	
	public String itemsOverDue(MyDate todayDate) {
//		if(num==0) {
//			return "";
//		}else {
		String out = "Items currently borrowed from library that are overdue as of " + todayDate.toString() + " are:\n";
		for (int i = 0; i < resourcesBorrowed.size(); i++)
			if(resourcesBorrowed.get(i) != null) {
				out += (i+1) + ": [";
				for(int j=0;j<resourcesBorrowed.get(i).size();j++)
					if(resourcesBorrowed.get(i).get(j).isOverDue(todayDate))
						out += " " + resourcesBorrowed.get(i).get(j).toString() + "\n";
				out += "]";
			}
		return out;
//		}
	}
	
	public void deleteResource (Scanner in, MyDate today) {
		if (resourcesBorrowed.size() == 0) {
			System.out.println ("No resources to view\n");
			return;
		}
		
		System.out.print ("Enter the title to search for: ");
		String title = in.next();
		
		Resource temp = new Resource (title);
		int foundIndex = findIndex(temp) ;
		if(resourcesBorrowed.get(foundIndex) == null)
		{
			System.out.println("Data Not Found Message");
			return;
		}
		for(int i =0;i<resourcesBorrowed.get(foundIndex).size();i++)
		{
			if (resourcesBorrowed.get(foundIndex).get(i).isEqual(temp))
				if(resourcesBorrowed.get(foundIndex).get(i).isOverDue(today)) 
				{
					System.out.println("his resource is overdue  Borrower owes" + resourcesBorrowed.get(foundIndex).get(i).cost);
					System.out.println (resourcesBorrowed.get(foundIndex).remove(i));
					System.out.println("Resource deleted");
				}else System.out.println ("Resource with this title is not found");
		}
		
	}
	
	public void displaySpecific (Scanner in) {
		if (resourcesBorrowed.size() == 0) {
			System.out.println ("No resources to view\n");
			return;
		}
		
		System.out.print ("Enter the title to search for: ");
		String title = in.next();
		
		Resource temp = new Resource (title);
		int foundIndex = findIndex(temp) ;
		if(resourcesBorrowed.get(foundIndex) == null)
		{
			System.out.println("Data Not Found Message");
			return;
		}
		for(int i =0;i<resourcesBorrowed.get(foundIndex).size();i++)
			if (resourcesBorrowed.get(foundIndex).get(i).isEqual(temp))
				System.out.println (resourcesBorrowed.get(foundIndex).get(i));
			else System.out.println ("Resource with this title is not found");
			
		
	}
	
	/*public void writeToFile(FileWriter outFile) {
		try {
			for (int i =0; i < resourcesBorrowed.size(); i++) {
				outFile.append (resourcesBorrowed.get(i).toFile());
			}
			outFile.close();
		}
		catch (IOException e) {
			System.out.println ("Error writing to file");
		}
		
	}*/

}
