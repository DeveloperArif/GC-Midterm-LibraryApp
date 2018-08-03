package library;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

	
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException {
		
		int menuOption;
		String searchOption;
		boolean isValid=false;
		ArrayList<Book> books = new ArrayList<>();
//		Status bookStatus;
				
		System.out.println("Welcome to the Grand Circus Library.\n");
		
		do {
		displayMenu();
		menuOption = Validator.getInteger(scnr, "Please enter a number to select from the menu options listed above. (1, 2, 3, 4, 5, 6): ", 1, 6);
		scnr.nextLine();
		
			if (menuOption == 1) {
				bookList(books);
				scnr.nextLine();
				isValid=false;
			} else if (menuOption == 2) {
				System.out.println();
				searchOption = Validator.getRegExString(scnr, "Would you like to search by author or keyword? (Enter author or keyword): ", "author|keyword");
//				scnr.nextLine();
				if (searchOption.equalsIgnoreCase("author")) {
					authorList(books);
				} else { 
					keywordList(books);
				}
				scnr.nextLine();
				isValid=false;
//				scnr.nextLine();

			} else if (menuOption == 3) {
				//checkoutBook();
				isValid=false;

			} else if(menuOption == 4) {
				//returnBook();
				isValid=false;

			} else if(menuOption ==5) {
				
				donateBook(books);
				isValid=false;

			}else 
				isValid=true;

		} while(!isValid);
		

		
		
		
	} // end of main method
	
	private static void donateBook(ArrayList<Book> books) {
		System.out.println("\nYou are awesome! Please enter the details of the book you are donating.");
		String bookTitle, bookAuthor;
		System.out.println("\nTitle: ");
		bookTitle=scnr.nextLine();
				//"\nTitle   : ");
		bookAuthor=Validator.getString(scnr, "\nAuthor  : ");
		//SimpleDateFormat dateDonated= new SimpleDateFormat("MM/dd/yyyy");
		Book newBook = new Book(bookTitle,bookAuthor,Status.ONSHELF,null);
		books.add(newBook);
		LibraryTextFile.appendLine(newBook);
		
		System.out.println("Thanks for donating a book!\nThe book "+ bookTitle + " by " + bookAuthor +" has been added to our Library.");
		
	}
	
	
	// get keyword from the user and search if any of the titles contains the keyword matches
	// if so, then print the Title along with the Author name
	
	public static void keywordList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		System.out.print("Enter the search keyword: ");
		String keyword = scnr.next();
		for (Book book : books) {
			if (book.getBookTitle().contains(keyword)) {
				System.out.println(book.getBookTitle() +  " by " + book.getBookAuthor());
			}
		}
		scnr.nextLine();
	}
	
	// get an author name from the user and search if it is contained in the file
	// if so, then print the Title along with the Author name
	public static void authorList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		System.out.print("Please enter the author you wish to search for: ");
		String authorName = scnr.next();
		for (int i=0; i < books.size();i++) {
			//for(Book book : books) {
			if (books.get(i).getBookAuthor().contains(authorName)) {
				System.out.println(books.get(i).getBookTitle() +  " by " + books.get(i).getBookAuthor());
			}
		}
		scnr.nextLine();
	}
	
	// list the books by title
	public static void bookList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		for (Book book : books) {
			System.out.println(book.getBookTitle());
		}
	}
	
	// display the main menu
	public static void  displayMenu() {
		System.out.println("     Main Menu     ");
		System.out.println("===================");
		System.out.println("1. List Books\n2. Search\n3. Checkout Book\n4. Return Book\n5. Donate a book\n6. Quit ");
	}
	
} // end of class

