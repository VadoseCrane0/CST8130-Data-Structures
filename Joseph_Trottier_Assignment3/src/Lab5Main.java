import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class is the method main for Assignment3 
Author:  Linda Crane and Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
                                             
         

*************************************************************************************************************/
public class Lab5Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		College ac = new College("Algonquin");
		String menuChoice = "a";
		
		while (menuChoice.charAt(0) != '6') {
			System.out.println ("\nEnter 1 to display the college courses: ");
			System.out.println ("2 to add a new course: ");
			System.out.println ("3 to add a block: ");
			System.out.println ("4 to verify chain: ");
			System.out.println ("5 to fix a chain: ");
			System.out.println ("6 to quit: ");
			menuChoice = keyboard.next();
			
			switch (menuChoice.charAt(0)) {
			case '1': ac.printArray();
					  break;
			case '2': ac.addCourse(keyboard);
			          break;
			case '3': ac.addBlock(keyboard);
			  		  break;
			case '4': ac.verifychains();
	          		  break;
			case '5': ac.fixChain(keyboard);
					  break;
			case '6': System.out.println("Goodbye");
			  break;
			default:  System.out.println ("Invalid choice...");
			}
		}
		

	}

}

