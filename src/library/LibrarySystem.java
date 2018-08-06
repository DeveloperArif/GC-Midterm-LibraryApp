package library;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.prism.MaskTextureGraphics;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibrarySystem {
	
	static Scanner scnr = new Scanner(System.in);
	
	public static void checkoutBook(ArrayList<Book> books) throws Exception {
		books = LibraryTextFile.readFile();
		String bookTitle;
//		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
		LocalDate date = LocalDate.now();
		date = date.plusDays(14);
		System.out.print("Enter the title of the book you are checking out: ");
		bookTitle = scnr.next().toUpperCase();
		int bookNum=0, checkoutNum;
		boolean isValid = false;
		HashMap<Integer, Book> matchingBooks = new HashMap<>();
		HashMap<Integer, Book> checkedOutBooks = new HashMap<>();
		HashMap<Integer, Book> availableBooks = new HashMap<>();
		
		for(int i=0; i<books.size();i++) {
			if(books.get(i).getBookTitle().contains(bookTitle)) {
				bookNum=i+1;
				matchingBooks.put(bookNum,books.get(i));
				System.out.println((i+1)+". "+books.get(i).getBookTitle()+ " by" +books.get(i).getBookAuthor());
			} 
		} 
	
//		System.out.println("Matching books : "+ matchingBooks.keySet());
		do {
		if(matchingBooks.isEmpty()) {
			System.out.println("Sorry we don't have any matching books!");
			break;			
		}else {
			for(HashMap.Entry<Integer, Book> bookEntry:matchingBooks.entrySet()) {
				if(bookEntry.getValue().getBookStatus().equals(Status.ONSHELF)) {
					availableBooks.put(bookEntry.getKey(), bookEntry.getValue());	
				}else if(bookEntry.getValue().getBookStatus().equals(Status.CHECKED_OUT)) {
					checkedOutBooks.put(bookEntry.getKey(), bookEntry.getValue());	
				}
			}
		}   // end of match search
		
		if(matchingBooks.size() == checkedOutBooks.size()) {
			System.out.println("Sorry all the books are out at this time. Please check back later!");
			isValid = true;
		} 

		System.out.println("Avaiable book numbers - "+availableBooks.keySet());
		do {
		if(!availableBooks.isEmpty()) {
			checkoutNum = Validator.getInteger(scnr, "Enter a book number: " );
			if(availableBooks.containsKey(checkoutNum)) {
				for(HashMap.Entry<Integer, Book> bookEntry:availableBooks.entrySet()) {
					if(bookEntry.getKey() == checkoutNum) {
						bookEntry.getValue().setBookStatus(Status.CHECKED_OUT);	
						bookEntry.getValue().setDueDate(date);
						System.out.println("The book " +bookEntry.getValue().getBookTitle()+" is checked out and is due on "+date);
						LibraryTextFile.writeFile(books);
						isValid = true;
					}				
				}
			} else {
				System.out.println("Sorry the book you requested is not available!");
				continue;
			}
		}else
			System.out.println("All books are checked out ! Please visit later");
		}while(!isValid);
		System.out.println("Thanks for checking out! Enjoy reading");
		}while(!isValid);
		scnr.nextLine();

	}
	
	public static void returnBook(ArrayList<Book> books) throws Exception {
		books = LibraryTextFile.readFile();
		String bookTitle;
		System.out.println("\nHope you enjoyed the book!");
		System.out.print("Please enter the title of the book you are returning: ");
		bookTitle=scnr.nextLine().toUpperCase();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
		LocalDate date = LocalDate.now();
		date = date.plusDays(7);
		int bookNum, checkInNum;
		boolean isValid=false;
		HashMap<Integer, Book> matchingBooks = new HashMap<>();
		HashMap<Integer, Book> checkedOutBooks = new HashMap<>();
		HashMap<Integer, Book> availableBooks = new HashMap<>();
				
		for(int i=0; i<books.size();i++) {
			if(books.get(i).getBookTitle().contains(bookTitle)) {
				bookNum=i+1;
				matchingBooks.put(bookNum,books.get(i));
				System.out.println((i+1)+". "+books.get(i).getBookTitle()+ " by" +books.get(i).getBookAuthor());
			} 
		} 
		do {
//		System.out.println("Matching books : "+ matchingBooks.keySet());
		if(matchingBooks.isEmpty()) {
			System.out.println("Sorry we don't have any matching books!");
			break;
		}else {
			for(HashMap.Entry<Integer, Book> bookEntry:matchingBooks.entrySet()) {
				if(bookEntry.getValue().getBookStatus().equals(Status.ONSHELF)) {
					availableBooks.put(bookEntry.getKey(), bookEntry.getValue());	
				}else if(bookEntry.getValue().getBookStatus().equals(Status.CHECKED_OUT)) {
					checkedOutBooks.put(bookEntry.getKey(), bookEntry.getValue());	
				}
			}
		}   // end of match search
		
		if(matchingBooks.size() == availableBooks.size()) {
			System.out.println("Sorry all the books are out at this time. Please check back later!");
		}
		
//		System.out.println("Avaiable book numbers - "+availableBooks.keySet());
//		System.out.println("checkedout books - "+checkedOutBooks.keySet());
		do {
		if(!checkedOutBooks.isEmpty()) {
			checkInNum = Validator.getInteger(scnr, "Enter the book number: ");
			if(checkedOutBooks.containsKey(checkInNum)) {
				for(HashMap.Entry<Integer, Book> bookEntry:checkedOutBooks.entrySet()) {
					if(bookEntry.getKey() == checkInNum) {
						if(LocalDate.now().isAfter(bookEntry.getValue().getDueDate()))	{
							long daysLate = (ChronoUnit.DAYS.between(LocalDate.now(), bookEntry.getValue().getDueDate()));
								bookEntry.getValue().setBookStatus(Status.ONSHELF);
								bookEntry.getValue().setDueDate(null);
								LibraryTextFile.writeFile(books);
								System.out.println("This book was overdue by " + Math.abs(daysLate) + " days. Thank you for returning.");
							} else  {
								bookEntry.getValue().setBookStatus(Status.ONSHELF);
								bookEntry.getValue().setDueDate(null);
								LibraryTextFile.writeFile(books);
								System.out.println("Thank you! Come back soon.");
							}
					}
					isValid=true;
				}
			} else {
				System.out.println("Sorry the book is already on the shelf!");
				isValid=false;
			}
		}else
			System.out.println("All books are on shelf! Please checkout book of your choice.");
		}while(!isValid);
		LibraryTextFile.writeFile(books);
		}while(!isValid);
//		scnr.nextLine();
		
	}
	
	public static void donateBook(ArrayList<Book> books) {
		System.out.println("\nYou are awesome! Please enter the details of the book you are donating.");
		String bookTitle, bookAuthor;
		System.out.println("\nTitle: ");
		bookTitle=scnr.nextLine().toUpperCase();
		bookAuthor=Validator.getString(scnr, "\nAuthor  : ").toUpperCase();
		Book newBook = new Book(bookTitle,bookAuthor,Status.ONSHELF,null);
		books.add(newBook);
		LibraryTextFile.appendLine(newBook);
		
		System.out.println("Thanks for donating a book!\nThe book "+ bookTitle + " by " + bookAuthor +" has been added to our Library.");
		scnr.nextLine();
	}
	
	
	// get keyword from the user and search if any of the titles contains the keyword matches
	// if so, then print the Title along with the Author name
	
	public static void keywordSearch(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		HashMap<Integer, Book> matchingBooks = new HashMap<>();
		String keyword;
		int bookNum;
		keyword = Validator.getString(scnr, "Enter the keyword: ").toUpperCase();
		for(int i=0; i<books.size();i++) {
			if(books.get(i).getBookTitle().contains(keyword)) {
				bookNum=i+1;
				matchingBooks.put(bookNum,books.get(i));
				System.out.println((i+1)+". "+books.get(i).getBookTitle()+ " by" +books.get(i).getBookAuthor());
			} 
		}
		if(matchingBooks.isEmpty()) {
			System.out.println("Sorry there are no books in that search category.");
		} 
		scnr.nextLine();
	}
	// get an author name from the user and search if it is contained in the file
	// if so, then print the Title along with the Author name
	public static void authorList(ArrayList<Book> books) throws ParseException {
		books = LibraryTextFile.readFile();
		String authorName;
		authorName = Validator.getString(scnr, "Please enter the author you wish to search for: ").toUpperCase();
		for (int i=0; i < books.size();i++) {
			if (books.get(i).getBookAuthor().contains(authorName)) {
				System.out.printf("%d%-10s%-10s\n",(i+1),") "+books.get(i).getBookTitle() ,  " by " + books.get(i).getBookAuthor());
			}
		}
		scnr.nextLine();
	}
	
	// list the books by title
	public static void bookList(ArrayList<Book> books) throws ParseException {
		System.out.println("\nList of books in our Library!\n---- -- ----- -- --- --------");
		books = LibraryTextFile.readFile();
		for (int i=0;i<books.size();i++) {
			System.out.println(i+1+") "+books.get(i).getBookTitle()+"\t ("+books.get(i).getBookStatus()+")");
		}
		scnr.nextLine();
	}
	
	// display the main menu
	public static void  displayMenu() {
		System.out.println("     Main Menu     ");
		System.out.println("===================");
		System.out.println("1. List Books\n2. Search\n3. Checkout Book\n4. Return Book\n5. Donate a book\n6. Quit ");
	}
	
} // end of class

