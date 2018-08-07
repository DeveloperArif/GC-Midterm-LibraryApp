//@ Sasi, Anesha and Arif

package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

import library.Book;
import library.LibraryTextFile;
import library.Status;
import library.Validator;

public class ReturnBookGUI {
	private static JFrame frame;
	private static JTextField keywordField;
	private static JTextField bookNumField;
	private static JTextArea resultField;
	private static HashMap<Integer, Book> matchingBooks = new HashMap<>();
	private static HashMap<Integer, Book> checkedOutBooks = new HashMap<>();
	private static HashMap<Integer, Book> availableBooks = new HashMap<>();
			
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ArrayList<Book> books = new ArrayList<>();
						try {
							books = LibraryTextFile.readFile();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					    	
						ReturnBookGUI window = new ReturnBookGUI(books);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public ReturnBookGUI(ArrayList<Book> books) {
			RtrnBookGUI(books);
		}

		private static void RtrnBookGUI(ArrayList<Book> books) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Grand Circus Library - Return a book");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBounds(200, 8, 397, 29);
		frame.getContentPane().add(label);
		
		JButton btnForReturnBook = new JButton("Return");
		JButton btnForSearchBook = new JButton("Search");
		
		btnForSearchBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    String keyword = keywordField.getText().trim().toUpperCase();
				int bookNum;
	    	
		    	if(keywordField.getText().isEmpty() || !bookNumField.getText().isEmpty() )
		    	{
		    		JOptionPane.showMessageDialog(frame,"Invalid Input!","Error!",JOptionPane.ERROR_MESSAGE);
		    		return;
		    	}

				resultField.setText(null);
				for(int i=0; i<books.size();i++) {
					if(books.get(i).getBookTitle().contains(keyword)) {
						bookNum=i+1;
						matchingBooks.put(bookNum,books.get(i));
						resultField.append((i+1)+". "+books.get(i).getBookTitle()+"("+books.get(i).getBookStatus()+")\n");
					} 
				}
				
				if(matchingBooks.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Sorry we don't have any matching books!", "No Match", JOptionPane.WARNING_MESSAGE);
				}else {
					for(HashMap.Entry<Integer, Book> bookEntry:matchingBooks.entrySet()) {
						if(bookEntry.getValue().getBookStatus().equals(Status.ONSHELF)) {
							availableBooks.put(bookEntry.getKey(), bookEntry.getValue());	
						}else if(bookEntry.getValue().getBookStatus().equals(Status.CHECKED_OUT)) {
							checkedOutBooks.put(bookEntry.getKey(), bookEntry.getValue());	
						}
					}
				}
				if(matchingBooks.size() == availableBooks.size()) {
					JOptionPane.showMessageDialog(frame, "Sorry all the books are on shelf!. Please checkout book f your choice!", "All Books are in!", JOptionPane.WARNING_MESSAGE);
				}
				btnForReturnBook.setEnabled(true);
				bookNumField.setEnabled(true);
				}
				});
			
		
		btnForReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean isValid = false;
				int bookNum = 0;
	     	
		    	if(bookNumField.getText().isEmpty())
		    	{
		    		JOptionPane.showMessageDialog(frame,"Invalid Input!","Error!",JOptionPane.ERROR_MESSAGE);
		    		return;
		    	}
//			System.out.println(books);
			do {
			if(!checkedOutBooks.isEmpty()) {
				try {
				bookNum = Integer.parseInt(bookNumField.getText());
				}catch(Exception ex) {
		    		JOptionPane.showMessageDialog(frame,"Invalid Input!","Error!",JOptionPane.ERROR_MESSAGE);
					isValid=false;
				}
				if(checkedOutBooks.containsKey(bookNum)) {
					for(HashMap.Entry<Integer, Book> bookEntry:checkedOutBooks.entrySet()) {
						if(bookEntry.getKey() == bookNum) {
							if((bookEntry.getValue().getDueDate() !=null) && (LocalDate.now().isAfter(bookEntry.getValue().getDueDate())))	{
								long daysLate = (ChronoUnit.DAYS.between(LocalDate.now(), bookEntry.getValue().getDueDate()));
									bookEntry.getValue().setBookStatus(Status.ONSHELF);
									bookEntry.getValue().setDueDate(null);
									JOptionPane.showMessageDialog(frame, "This book is overdue by "+daysLate+" days", "Overdue!", JOptionPane.WARNING_MESSAGE);
									LibraryTextFile.writeFile(books);
									isValid=true;
								} else  {
									bookEntry.getValue().setBookStatus(Status.ONSHELF);
									bookEntry.getValue().setDueDate(null);
									LibraryTextFile.writeFile(books);
									JOptionPane.showMessageDialog(frame, "Hope you enjoyed the book. Thanks", "Done!", JOptionPane.PLAIN_MESSAGE);
									isValid = true;
								}
						}
					}
				}else {
					JOptionPane.showMessageDialog(frame, "The book is already on the shelf!", "Already on Shelf", JOptionPane.WARNING_MESSAGE);
					isValid = true;
				}
					
			} else {
				JOptionPane.showMessageDialog(frame, "No book found!","No matching book", JOptionPane.OK_OPTION);
				isValid = true;
			}
					
		} while(!isValid);

		}
	});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				MainGUIApp.main(null);
			}
		});
		
		
		btnForSearchBook.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnForSearchBook.setBounds(150, 415, 123, 23);
		frame.getContentPane().add(btnForSearchBook);
		
		btnForReturnBook.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnForReturnBook.setBounds(350, 415, 123, 23);
		frame.getContentPane().add(btnForReturnBook);
		btnForReturnBook.setEnabled(false);
		
		

		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBack.setBounds(550, 415, 123, 23);
		frame.getContentPane().add(btnBack);
		

		
		JLabel lblTitle = new JLabel("Keyword");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblTitle.setBounds(10, 40, 155, 29);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblResults = new JLabel("Search Result");
		lblResults.setHorizontalAlignment(SwingConstants.LEFT);
		lblResults.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblResults.setBounds(10, 180, 155, 29);
		frame.getContentPane().add(lblResults);
		
		JLabel lblAuthor = new JLabel("Book Number");
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblAuthor.setBounds(10, 345, 155, 29);
		frame.getContentPane().add(lblAuthor);
		
		keywordField = new JTextField();
		keywordField.setBounds(120, 50, 178, 20);
		frame.getContentPane().add(keywordField);
		keywordField.setColumns(10);
		
		resultField = new JTextArea();
		resultField.setEditable(false);
//		resultField.setColumns(10);
		resultField.setBounds(120, 80, 600, 250);
//		DefaultCaret caret = (DefaultCaret)resultField.getCaret();
//		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		resultField.setLineWrap(true);
		resultField.setWrapStyleWord(true);
		frame.getContentPane().add(resultField);
		
		
		bookNumField = new JTextField();
		bookNumField.setColumns(10);
		bookNumField.setBounds(120, 350, 50, 20);
		frame.getContentPane().add(bookNumField);
		bookNumField.setEnabled(false);
		
		

		
		
	
		
	}


	}

