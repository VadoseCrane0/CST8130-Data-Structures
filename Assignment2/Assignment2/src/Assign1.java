import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/************************************************************************************************************
Purpose:  This Program implements a simple library resource management system.  The menu processing is in main
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members: todayDate : MyDate - represents today's date

*************************************************************************************************************/
public class Assign1 {

	public static void main(String[] args) {
		Library library = new Library();
		Scanner keyboard = new Scanner (System.in);
		MyDate todayDate = new MyDate(16,9,2018);
		
		String choice = new String("0");
		
		while (choice.charAt(0) != '9') {
			System.out.print ("\nEnter 1 to add to resources borrowed, \n    2 to display overdue items, \n    3 to display all resources borrowed, \n    4 to delete a resource, \n    5 to change today date, \n    6 to view a specific resource, \n    7 to read resources from a file, \n    8 to save the current resources to a file, \n    9 to quit:");
			choice = keyboard.next();
			
			switch (choice.charAt(0)) {
				case '1': 	library.inputResource(keyboard, todayDate);
							break;
				case '2': 	System.out.println (library.itemsOverDue(todayDate));
							break;
				case '3': 	System.out.println (library);
							break;
				case '4':   library.deleteResource(keyboard, todayDate);
							break;
				case '5':   System.out.println ("Enter a new date for today's date");
							todayDate.inputDate(keyboard);
							break;
				case '6':   System.out.println(library.searchResourceTitle(keyboard));
							break;
				case '7':   try {
								System.out.println("Enter name of file to import: ");
								String fileName = keyboard.next();
								Scanner in = new Scanner(new File("./"+fileName));
								int p=0;
								while (in.hasNext()) {
									library.loadData(in);
								}
							}catch(FileNotFoundException e) {
								System.out.println("Invalid file name");
							}
							break;
				case '8':   	library.savefile();
								System.out.println("Enter save file name without extention eg:saveFile");
								String saveFile=keyboard.next();
								saveFile+=".txt";
								try(PrintWriter writer = new PrintWriter(saveFile, "UTF-8")) {
									writer.print(library.savefile());
									writer.close();
								}catch(IOException e) {
									e.printStackTrace();
								}
							break;
				case '9': System.out.println ("goodbye");
			}
		}
		
	}
	public void readFile(Scanner in, MyDate today) {
		
	}

}
