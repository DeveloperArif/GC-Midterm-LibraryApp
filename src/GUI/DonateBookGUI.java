package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.Book;
import library.LibraryTextFile;
import library.Status;

public class DonateBookGUI {
	private JFrame frame;
	private JTextField titleField;
	private JTextField authorField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonateBookGUI window = new DonateBookGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public DonateBookGUI() {
		AddBookGUI();
	}

	private void AddBookGUI() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel label = new JLabel("           Welcome To Grand Circus Library");
	label.setFont(new Font("Times New Roman", Font.BOLD, 18));
	label.setBounds(27, 26, 397, 29);
	frame.getContentPane().add(label);
	JButton btnAddForBook = new JButton("Donate Book");
	
	btnAddForBook.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			File fileName = new File("book.txt");
			
			
		    String bookTitle = titleField.getText().trim().toUpperCase();
		    String bookAuthor = authorField.getText().trim().toUpperCase();
		    
		    try {
		    	@SuppressWarnings("resource")
				BufferedReader Reader = new BufferedReader(new FileReader("book.txt"));
		    	
		    	if(titleField.getText().isEmpty() || authorField.getText().isEmpty() )
		    	{
		    		JOptionPane.showMessageDialog(frame,"Invalid Input!","Error!",JOptionPane.ERROR_MESSAGE);
		    		return;
		    	}
		    	
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		    
					if(!fileName.exists()){
					    try {
							fileName.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					Book book = new Book();
					book.setBookTitle(bookTitle);
					book.setBookAuthor(bookAuthor);
					book.setBookStatus(Status.ONSHELF);
					book.setDueDate(null);
					LibraryTextFile.appendLine(book);
					JOptionPane.showMessageDialog(frame,"Thanks for donating the book "+ bookTitle+"!");
		}
	});
	btnAddForBook.setFont(new Font("Times New Roman", Font.BOLD, 15));
	btnAddForBook.setBounds(74, 215, 148, 23);
	frame.getContentPane().add(btnAddForBook);
	
	JButton btnBack = new JButton("Back");
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			GUI.main(null);
		}
	});
	btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
	btnBack.setBounds(232, 215, 123, 23);
	frame.getContentPane().add(btnBack);
	
	JLabel lblUserPage = new JLabel("Donate a Book");
	lblUserPage.setFont(new Font("Times New Roman", Font.BOLD, 18));
	lblUserPage.setBounds(165, 66, 178, 29);
	frame.getContentPane().add(lblUserPage);
	
	titleField = new JTextField();
	titleField.setBounds(130, 107, 178, 20);
	frame.getContentPane().add(titleField);
	titleField.setColumns(10);
	
	authorField = new JTextField();
	authorField.setColumns(10);
	authorField.setBounds(130, 141, 178, 20);
	frame.getContentPane().add(authorField);
	
	
	JLabel lblTitle = new JLabel("Title");
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblTitle.setBounds(74, 101, 46, 29);
	frame.getContentPane().add(lblTitle);
	
	JLabel lblAuthor = new JLabel("Author");
	lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
	lblAuthor.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	lblAuthor.setBounds(74, 135, 55, 29);
	frame.getContentPane().add(lblAuthor);
	
}


}

