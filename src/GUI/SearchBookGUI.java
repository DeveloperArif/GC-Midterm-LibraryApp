//@ Sasi, Anesha and Arif

package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.Book;
import library.LibraryTextFile;
import library.Status;

public class SearchBookGUI {
	private static JFrame frame;
	private static JTextField keywordField;
	private static JTextArea resultField;
	private static HashMap<Integer, Book> matchingBooks = new HashMap<>();
		
	
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
					    	
						SearchBookGUI window = new SearchBookGUI(books);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public SearchBookGUI(ArrayList<Book> books) {
			SrchBookGUI(books);
		}

		private static void SrchBookGUI(ArrayList<Book> books) {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Grand Circus Library - Return a book");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setBounds(200, 8, 397, 29);
		frame.getContentPane().add(label);
		JButton btnForSearchBook = new JButton("Search");
		
		btnForSearchBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			    String keyword = keywordField.getText().trim().toUpperCase();
				int bookNum;
		    	
			    	if(keywordField.getText().isEmpty() )
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
				}
				
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
		
	
		keywordField = new JTextField();
		keywordField.setBounds(120, 50, 178, 20);
		frame.getContentPane().add(keywordField);
		keywordField.setColumns(10);
		
		resultField = new JTextArea();
		resultField.setEditable(false);
		resultField.setBounds(120, 80, 600, 250);
		resultField.setLineWrap(true);
		resultField.setWrapStyleWord(true);
		frame.getContentPane().add(resultField);


	}


}
