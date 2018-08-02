package library;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

	
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException {
		
		int menuOption;
		String searchOption;
		
		ArrayList<Book> books = new ArrayList<>();
		
		
		
		
		
		
		
		System.out.println("Welcome to the Grand Circus Library.\n");
		displayMenu();
		menuOption = Validator.getInteger(scnr, "Please enter a number to select from the menu options listed above. (1, 2, 3, 4, 5, 6): ", 1, 6);
	
		if (menuOption == 1) {
			bookList(books);	
		}
		else if (menuOption == 2) {
			System.out.println();
			searchOption = Validator.getRegExString(scnr, "Would you like to search by author or keyword? (Enter author or keyword): ", "author|keyword");
			if (searchOption.equalsIgnoreCase("author")) {
				authorList(books);
			} else { 
				keywordList(books);
			}
		}
		else if (menuOption == 3) {
		
			
		}
		

		
		
		
	}
	public static void keywordList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		System.out.println("Enter the search keyword: ");
		String keyword = scnr.nextLine();
		for (Book book : books) {
			if (book.getBookTitle().contains(keyword)) {
				System.out.println(book.getBookTitle() +  " by " + book.getBookAuthor());
				
				
			}
		}
	}
	
	public static void authorList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		System.out.println("Please enter the author you wish to search for: ");
		String authorName = scnr.nextLine();
		for (Book book : books) {
			if (book.getBookAuthor().contains(authorName)) {
				System.out.println(book.getBookTitle() +  " by " + book.getBookAuthor());
			}
			
		}
	}
	
	public static void bookList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		for (Book book : books) {
			System.out.println(book.getBookTitle());
			
		}
	
}
	
	public static void  displayMenu() {
		
		System.out.println("     Main Menu     ");
		System.out.println("===================");
		System.out.println("1. List Books\n2. Search\n3. Checkout Book\n4. Return Book\n5. Donate a book\n6. Quit ");
	}
}

