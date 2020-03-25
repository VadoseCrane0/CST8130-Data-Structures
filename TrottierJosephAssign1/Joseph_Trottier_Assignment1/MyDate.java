import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a simple date by keeping day, month and year information.   Leap years are NOT
               accommodated in this class.
Author:  Linda Crane and Joseph Trottier
Course: F2018 - CST8130
Lab Section: CST8130-303
Data members:  day : int - value between 1 and 31 inclusive (depending on value in month)
               month: int - value between 1 and 12 inclusive
               year: int - positive value
Methods: default constructor - sets date to Jan 1, 2018
         toString (): String - prints date in year/month/day format
         inputDate(Scanner): boolean - reads a valid date from the Scanner parameter and returns through
                                       boolean success or not
         addOne(): void - adds one to the day field and then adjusts month and year as needed.
         isGreaterThan(MyDate date):  -                                              
         

*************************************************************************************************************/
public class MyDate {
		private int day = 1;
		private int month = 1;
		private int year = 2018;
		
		public MyDate() {
		}
		public MyDate(MyDate date) {
			day=date.day;
			month=date.month;
			year = date.year;
			
		}
		
		public String toString() {   return new String ("" + year + "/" + month + "/" + day);
		}
		
		public boolean inputDate(Scanner in) {
			month = 0;
			day = 0; 
			year = 0;
			do {
					
				System.out.print ("Enter month - between 1 and 12: ");
				if (in.hasNextInt())
					this.month = in.nextInt();
				else {
					System.out.println ("Invalid month input");
					in.next();
				}
			} while (this.month <= 0 || this.month > 12);
			
			do {
				if (this.month == 2)
					System.out.print ("Enter day - between 1 and 29: ");
				else if (this.month == 9 || this.month == 4 || this.month == 6 || this.month == 11 )
					System.out.print ("Enter day - between 1 and 30: ");
				else
					System.out.print ("Enter day - between 1 and 31: ");
				if (in.hasNextInt())
					this.day = in.nextInt();
				else {
					System.out.println ("Invalid day input");
					in.next();
				}
			} while (this.day <= 0 || this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) );
			
			do {
				System.out.print ("Enter year: ");
				if (in.hasNextInt())
					this.year = in.nextInt();
				else {
					System.out.println ("Invalid day input");
					in.next();
				}
			} while (this.year <= 0);
				
			return true;		
		}
		
		public void addOne (){
			// *********    write the code for this method here  **************
			if (this.month == 2 && this.day == 29) {
				this.day = 1;
				this.month++;
			}else if(this.day == 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ){
				this.day = 1;
				this.month++;
			}else if(this.day == 31 && this.month == 12) {
				this.day =1;
				this.month = 1;
				this.year++;
			}else if (this.day == 31) {
				this.day = 1;
				this.month++;
			}else {
				this.day++;
			}
//while (this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) );
			
		}
		public boolean isEqual(MyDate date) {
			if(this.day==date.day && this.year==date.year && this.month==date.month) {
			return true;
			}else {
				return false;
			}
		}
		public boolean isGreaterThan(MyDate date) {
			if((this.day<date.day && this.year==date.year && this.month==date.month)|| (this.year==date.year && this.month<date.month) || (this.year<date.year)) {
				return true;
			}else {
				return false;
			}
		}


}

