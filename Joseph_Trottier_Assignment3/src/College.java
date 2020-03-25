import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a College to keep track of class stuff
Author:  Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  
			   college : ArrayList<BlockChain> - arraylist for blockchain
               collegeName: String - Name of college

Methods:                                             
         College ( String collegeName)- constructor
         printArray (): void - prints college array
         addCourse(Scanner): boolean - reads a coursename from the Scanner parameter and creates a blockchain for it
         addBlock(Scanner): void - adds one block to the blockChain for a course.
         verifychains(): void - checks blockchains for bad blocks.
         fixChain(Scanner): void - Deletes bad block from selected course.
         
         

*************************************************************************************************************/
public class College {
		private ArrayList<BlockChain> college = new ArrayList<BlockChain>();
	    
		private String collegeName = "NotEntered";
		
		public College ( String collegeName) {
			this.collegeName = new String (collegeName);
		}
		
		public void printArray() {
			System.out.println("For College: "+collegeName);
			for (BlockChain print: college) {
				print.printBlockChain();
			}
		}
		public void addCourse(Scanner in) {
			System.out.println("Enter name of course to add: ");
			String courseName=in.next();
			college.add(new BlockChain(courseName));
		}
		public void addBlock(Scanner in) {
				int temp=-1;
				System.out.println("Enter which course to fix?");
				for (int i=0; i<college.size(); i++) {
					System.out.println(i+" "+college.get(i).toString());
				}
				while(temp<0 || temp>=college.size()) {
					if (in.hasNextInt()) {
						temp = in.nextInt();
					}
					else {
						System.out.println("invalid input");
						in.next();
					}
				}
				System.out.println("Add good block or bad? g for good, anything else is bad");
				String g = in.next();
				if(g.equals("g"))
					college.get(temp).addBlock(in);
				else
					college.get(temp).addBadBlock(in);
		}
		public void verifychains() {
			for (BlockChain print: college) {
				if (print.verifyChain())
					System.out.println ("Chain for "+print.toString()+ " is verified");
			   else  
				   System.out.println ("Chain for "+print.toString()+" is broken");
			}
		}
		public void fixChain(Scanner in) {
			int temp=-1;
			System.out.println("Enter which course to fix?");
			for (int i=0; i<college.size(); i++) {
				System.out.println(i+" "+college.get(i).toString());
			}
			while(temp<0 || temp>=college.size()) {
				if (in.hasNextInt()) {
					temp = in.nextInt();
				}
				else {
					System.out.println("invalid input");
					in.next();
				}
			}
			college.get(temp).removeBadBlocks();
		}
}
