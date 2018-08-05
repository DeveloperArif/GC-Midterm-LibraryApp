package library;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
	
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		int menuOption;
		String searchOption;
		boolean isValid=false;
		ArrayList<Book> books = new ArrayList<>();				
		System.out.println("Welcome to the Grand Circus Library!");
		System.out.println("~~~~~~~ ~~ ~~~ ~~~~~ ~~~~~~ ~~~~~~~~\n");
		
		do {
		LibrarySystem.displayMenu();
		menuOption = Validator.getInteger(scnr, "Please enter a number to select from the menu options listed above. (1, 2, 3, 4, 5, 6): ", 1, 6);
		scnr.nextLine();
		
			if (menuOption == 1) {
				LibrarySystem.bookList(books);
				isValid=false;
			} else if (menuOption == 2) {
				System.out.println();
				searchOption = Validator.getRegExString(scnr, "Would you like to search by author or keyword? (Enter author or keyword): ", "author|keyword");
				if (searchOption.equalsIgnoreCase("author")) {
					LibrarySystem.authorList(books);
				} else { 
					LibrarySystem.keywordList(books);
				}
				isValid=false;
			} else if (menuOption == 3) {
				LibrarySystem.checkoutBook(books);
				isValid=false;
			} else if(menuOption == 4) {
				LibrarySystem.returnBook(books);
				isValid=false;
			} else if(menuOption ==5) {
				LibrarySystem.donateBook(books);
				isValid=false;
			}else 
				isValid=true;
		} while(!isValid);
		
		System.out.println("\n\nThanks for visiting Grand Circus Library!");
		
	} // end of main method
	
}
