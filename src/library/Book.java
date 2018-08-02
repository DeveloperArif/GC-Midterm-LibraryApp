package library;

// Book class that contains private fields, getters and setters for the same and 
// a default constructor and a parameterized constructor


import java.text.SimpleDateFormat;
//import java.util.Date;

public class Book {
	private String bookTitle;
	private String bookAuthor;
	private SimpleDateFormat dueDate;
	
	// default constructor
	public Book() {
		
	}
	// parameterized constructor
	public Book(String bookTitle, String bookAuthor, SimpleDateFormat dueDate) {
		super();
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.dueDate = dueDate;
	}
	
	// getters and setters for the private variables
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public SimpleDateFormat getDueDate() {
		return dueDate;
	}
	public void setDueDate(SimpleDateFormat dueDate) {
		this.dueDate = dueDate;
	}
	

}
