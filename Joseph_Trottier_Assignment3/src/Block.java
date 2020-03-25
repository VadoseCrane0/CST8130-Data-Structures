import java.util.Scanner;

public class Block {
	private MyDate date;  // part of data - in month day year format  (eg) 2152018
	private int studentNumber; // part of data
	private int grade;  // part of data
	private float previousHash;
	private float currentHash;
	private Block nextOne;
	/************************************************************************************************************
	Purpose:  This class will model a College to keep track of class stuff
	Author:  Linda Crane and Joseph Trottier
	Course: F2018 - CST8130
	Lab Section: CST8130-303
	Data members:  
				   blockChain : LinkedList<Block> - linkedlist holding blocks
	               courseName: String - Name of course
	               gen : Block - Generation 0 block

	Methods:                                             
	         BlockChain ( String courseName)- constructor
	         printBlockChain (): void - prints blockChain
	         addCourse(Scanner): boolean - reads a coursename from the Scanner parameter and creates a blockchain for it
	         addBlock(Scanner): void - adds one block to the blockChain for a course.
	         addBadBlock(Scanner): void - adds one badblock block to the blockChain for a course.
	         verifyChain(): void - checks blockchain for bad blocks.
	         removeBadBlocks(Scanner): void - Deletes bad block from selected course.
	         toString(): String - Returns courseName
	         
	         

	*************************************************************************************************************/
	public Block() {
		// create the Genesis block
		date = new MyDate(11,11,2018);
		studentNumber = 0;
		grade = 100;
		previousHash = 0f;
		currentHash = calculateHash();
		nextOne = null;
		
	}
	
	public float calculateHash() {
		return (date.toInt()+studentNumber+grade)/88;   
	}
	
	public String toString() {
		return "" + studentNumber + " " + grade + " " + date +  " current: " + currentHash + " previous: " + previousHash ;
	}
	
	public Block next() {
		return nextOne;
	}
	
	public float getCurrentHash() {
		return currentHash;
	}
	
	public boolean isEqual (Block temp) {
		return (previousHash == temp.currentHash);
	}
	public void updateNext(Block newOne) {
		nextOne = newOne;
	}
	
	public void updatePreviousHash(float prevHash) {
		previousHash = prevHash;
	}
	
	public boolean addInfoToBlock(Scanner keyboard, float previousHash) {
		System.out.print ("Enter date: ");
		date.inputDate(keyboard);
		
		System.out.print ("Enter student number: ");
		while (!keyboard.hasNextInt())  {
			System.out.print ("Invalid...enter an int for student number: ");
			keyboard.next();
		}
		studentNumber = keyboard.nextInt();
		
		
		System.out.println ("Enter grade: ");
		while (!keyboard.hasNextInt())  {
			System.out.print ("Invalid...enter an int for grade: ");
			keyboard.next();
		}
		grade = keyboard.nextInt();
		
		currentHash = calculateHash();
		this.previousHash = previousHash;
		return true;
	}
	
}
