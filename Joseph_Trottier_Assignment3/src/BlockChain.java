import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a blockChain to keep track of class stuff
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
public class BlockChain {
	private LinkedList <Block> blockChain;
	
	Block gen = new Block();
	private String courseName = "NotEntered";
	
	public BlockChain ( String courseName) {
		this.courseName = new String (courseName);
		blockChain = new LinkedList<Block>();
		blockChain.add(gen);
	}
	
	public void printBlockChain() {
		if (blockChain.size()!=0) {
			System.out.println ("Chain for " + courseName);
			for(int i=0;i<blockChain.size();i++)
				System.out.println (blockChain.get(i));
		}else 
			System.out.println ("Nothing in chain");
		
		/*while (temp != null) {
			System.out.println (temp);
			temp = temp.next();
		}*/
	}
	
	public boolean verifyChain() {
			
		Block previousBlock = blockChain.get(0);
		int i=1;
		while (i < blockChain.size()) {
			if (blockChain.get(i).isEqual(previousBlock)) {
				previousBlock = blockChain.get(i);
				i++;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public void addBlock(Scanner keyboard) {
		Block newOne = new Block();
		if (blockChain.size()!=0) {
			if (newOne.addInfoToBlock(keyboard, blockChain.getLast().getCurrentHash())){
				// add to chain at tail
				blockChain.add(newOne);
				blockChain.get(blockChain.indexOf(newOne)-1).updateNext(newOne);
			}
		}
	}
	public void addBadBlock(Scanner keyboard) {
		Random random = new Random();
		Block newOne = new Block();
		if (newOne.addInfoToBlock(keyboard, random.nextFloat())){
			// add to chain at tail
			blockChain.add(newOne);
			blockChain.get(blockChain.indexOf(newOne)-1).updateNext(newOne);
		}
		
	}
	
	/*	public boolean verifyChain() {
			
		Block previousBlock = blockChain.get(0);
		int i=1;
		while (i < blockChain.size()) {
			if (blockChain.get(i).isEqual(previousBlock)) {
				previousBlock = blockChain.get(i);
				i++;
			}
			else {
				return false;
			}
		}
		return true;
	}*/
	public String toString() {
		
		return courseName;
	}
	
	public void removeBadBlocks() {
		Block previousBlock = blockChain.getFirst();
		Block currentBlock = blockChain.get(1);
		while (currentBlock != null) {
			int cur = blockChain.indexOf(currentBlock);
			int prev = blockChain.indexOf(previousBlock);
			if (!currentBlock.isEqual(previousBlock)) {
				// bad block  remove
				previousBlock.updateNext(currentBlock.next());
				blockChain.set(prev, previousBlock);
				if (currentBlock.next() != null) {  // if one we're moving is not last in chain
					currentBlock.next().updatePreviousHash(previousBlock.getCurrentHash());
				}else blockChain.removeLast();  // we are removing last one so update tail
				// move to next block
				blockChain.remove(currentBlock);
				currentBlock = currentBlock.next();
			}
			else {
				// move to next block
				previousBlock = currentBlock;
				currentBlock = currentBlock.next();
			}
			
		}
			
	}
}
