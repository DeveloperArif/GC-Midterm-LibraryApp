package library;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;



public class LibrarySystem {

	
	static Scanner scnr = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
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
				checkoutBook(books);
				isValid=false;

			} else if(menuOption == 4) {
				returnBook(books);
				isValid=false;
				

			} else if(menuOption ==5) {
				
				donateBook(books);
				isValid=false;

			}else 
				isValid=true;

		} while(!isValid);
		

		
		
		
	} // end of main method
	
	private static void checkoutBook(ArrayList<Book> books) throws Exception {
		books = LibraryTextFile.readFile();
		String bookTitle;
		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
//		Date dateNow = Date.
		LocalDate date = LocalDate.now();
		date = date.plusDays(14);
		System.out.print("Enter the title : ");
		bookTitle = scnr.next().toUpperCase();
		int bookNum=0, checkoutNum;
		ArrayList<Integer> matchingBooks = new ArrayList<>();
		ArrayList<Integer> checkedOutBooks = new ArrayList<>();
		ArrayList<Integer> availableBooks = new ArrayList<>();


//		boolean isValid=false;
		
		for(int i=0; i<books.size();i++) {
			if(books.get(i).getBookTitle().contains(bookTitle)) {
				bookNum=i+1;
				matchingBooks.add(bookNum);
				System.out.println((i+1)+". "+books.get(i).getBookTitle()+ " by" +books.get(i).getBookAuthor());
			} 
		}
//		if(matchingBooks.isEmpty()) {
//			System.out.println("Sorry we don't have any matching books!");
//		} else {
//			System.out.print();
//			if(checkoutNum<bookNums.size()) {
		
				System.out.println("Matching books : "+ matchingBooks);
		if(!matchingBooks.isEmpty()) {
			for(int num=0;num<matchingBooks.size();num++) {
				if(books.get(matchingBooks.get(num)-1).getBookStatus().equals(Status.ONSHELF)) {
					availableBooks.add(matchingBooks.get(num));
					//System.out.println("Sorry the book "+books.get(num).getBookTitle()+" is already checked out");
				}else if(books.get(matchingBooks.get(num)-1).getBookStatus().equals(Status.CHECKED_OUT)) {
					checkedOutBooks.add(matchingBooks.get(num+1));
				}
			}
		} else if(matchingBooks.isEmpty()) {
			System.out.println("Sorry we don't have any matching books!");
		}
			System.out.println("Avaiable book numbers - "+availableBooks);
				System.out.println("checkedout books - "+checkedOutBooks);

			if(!availableBooks.isEmpty()) {
				checkoutNum = Validator.getInteger(scnr, "Enter the book number: ");
				for(int num=0;num<availableBooks.size();num++) {
					if(availableBooks.contains(checkoutNum)) {
						books.get(availableBooks.get(num)).setBookStatus(Status.CHECKED_OUT);
						books.get(availableBooks.get(num)).setDueDate(date);
						System.out.println("The book " +books.get(availableBooks.get(num)).getBookTitle()+" is due on "+date);
				}else {
					System.out.println("the book is not available");
				}
				}
			}
				if(matchingBooks.isEmpty()) {
				System.out.println("Sorry all the books are checkedout!");
				}
		
		
		LibraryTextFile.writeFile(books);
		
	}
	
	private static void returnBook(ArrayList<Book> books) throws Exception {
		books = LibraryTextFile.readFile();
		String bookTitle;
		System.out.println("\nHope you enjoyed the book!");
		System.out.print("Please enter the title of the book you are returning: ");
		bookTitle=scnr.nextLine().toUpperCase();
		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
//		Date dateNow = Date.
		Date dateToday = new Date();
		
		for (Book book : books) {
			if(book.getBookTitle().contains(bookTitle) && book.getBookStatus().equals(Status.CHECKED_OUT)) {
/*				int daysDue = book.getDueDate().compareTo(dateToday);
				if(daysDue<=0)	{
					book.setBookStatus(Status.ONSHELF);
					book.setDueDate(null);
				} else if (daysDue>0){
					book.setBookStatus(Status.ONSHELF);
					book.setDueDate(null);
					System.out.println("The book titled "+book.getBookTitle()+" is overdue by "+ daysDue+ " days");
				}*/
				book.setBookStatus(Status.ONSHELF);
				book.setDueDate(null);
				System.out.println("Thankyou! Come back soon.");
			}else
				System.out.println("You cannot return this book. Please see an associate for help.");
			}
			
		}
		
		//System.out.println("Thanks for donating a book!\nThe book "+ bookTitle + " by " + bookAuthor +" has been added to our Library.");
		
	
	
	private static void donateBook(ArrayList<Book> books) {
		System.out.println("\nYou are awesome! Please enter the details of the book you are donating.");
		String bookTitle, bookAuthor;
		System.out.println("\nTitle: ");
		bookTitle=scnr.nextLine().toUpperCase();
				//"\nTitle   : ");
		bookAuthor=Validator.getString(scnr, "\nAuthor  : ").toUpperCase();
		
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
		String keyword = scnr.next().toUpperCase();
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
		String authorName = scnr.next().toUpperCase();
		for (int i=0; i < books.size();i++) {
			if (books.get(i).getBookAuthor().contains(authorName)) {
				System.out.println(books.get(i).getBookTitle() +  " by " + books.get(i).getBookAuthor());
			}
		}
		scnr.nextLine();
	}
	
	// list the books by title
	public static void bookList(ArrayList<Book> books) throws ParseException {
		System.out.println("List of books in our Library!");
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

