package library;
// @ Sasi, Anesha and Arif

import java.util.InputMismatchException;

// this program checks for validity of many types of data, each in a different method. 
// It even checks if the user input data is within range mentioned or not(where applicable)
import java.util.Scanner;

public class Validator {
	Scanner sc = new Scanner(System.in);
	
	// overloaded method with different set of parameters. 
	//This checks if the user has entered an integer or not. Returns the valid number.
	public static int getInteger(Scanner sc, String prompt) {
		try {
			System.out.print(prompt);
			return sc.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid number. Please enter a valid integer!");
			sc.nextLine();
			return getInteger(sc,prompt);
		}
	}

	// overloaded method with different set of parameters. 
	//This checks if the user has entered an integer within the range mentioned.

	public static int getInteger(Scanner sc, String prompt,int min,int max) {
		boolean isValid = false;
		int number = 0;
		while(!isValid) {
			number = getInteger(sc,prompt);
			if(number<min) {
				System.out.println("The number has to be greater than "+min);
				
			}
			else if(number >max) {
				System.out.println("The number has to be less than "+max);
			}
			else {
				isValid = true;
			}
				
		} 
		return number;
	
	}
	
	
	// prints the prompt and returns the user entered string
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		return sc.nextLine();
	}
		
	// checks the user input string matches the RegEx pattern or not. 
	// It keeps asking until valid entry is made.
	public static String getRegExString(Scanner sc,String prompt,String regExPattern ) {
		boolean isValid = false;
		String userInput="";
		while(!isValid) {
			userInput = getString(sc, prompt);
			if (!(userInput.matches(regExPattern))) 
				System.out.println("Invalid entry! Please try again.");
			else
				isValid = true;
			
		}	
		return userInput;
	}
		
	

}
