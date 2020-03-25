import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class holds the main and initiates the menu
Author: Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  lib : Library - instance of library class 
               today: MyDate - stores todays date
Methods: main(): - starts the menu
         menu (): Displays a menu and handles the inputs                                              
         

*************************************************************************************************************/


public class Assign1 {
	static Library lib = new Library();
	static MyDate today = new MyDate();

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		menu(keyboard);
	}

	public static void menu(Scanner in) {
		int choice;
		boolean done=false;
		while(!done) {
		System.out.println("Enter 1 to add to resources borrowed,\n2 to display overdue items,\n3 to display all resources borrowed,\n4 to delete a resource,\n5 to change today date,\n6 to quit:\n ");
		if (in.hasNextInt()) {
			choice = in.nextInt();
				if (choice > 0 && choice < 7) {
					switch(choice) {
					case 1:
						lib.inputResource(in, today);
						break;
					case 2:
						lib.resourcesOverDue(today);
						break;
					case 3:
						lib.toString();
						System.out.println(today);
						break;
					case 4:
						lib.deleteResource(in,today);
						break;
					case 5:
						today.inputDate(in);
						break;
					case 6:
						done = true;
						break;
					}
				}
			}
			else {
				System.out.println ("Invalid choice.");
				in.next();
			}
		}
	}
}
