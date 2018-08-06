package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import library.Book;
import library.LibraryTextFile;

public class ListBooksGUI {

	//private JPanel contentPane;
	private  JFrame frame;
	private  JTextArea resultField;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Book> books = new ArrayList<>();
					try {
						books = LibraryTextFile.readFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ListBooksGUI window = new ListBooksGUI(books);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ListBooksGUI(ArrayList<Book> books) {
		LstBooksGUI(books);
	}

	private void LstBooksGUI(ArrayList<Book> books) {
//				ArrayList<Book> books = new ArrayList<>(); 
//				try {
//					books = LibraryTextFile.readFile();
//				} catch (ParseException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

					frame = new JFrame();
					frame.setTitle("Grand Circus Library Book List");
					frame.setBounds(100, 100, 800, 500);
					//frame.setLocationByPlatform(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					frame.setVisible(true);

					JLabel label = new JLabel("Current Library Book List");
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setFont(new Font("Times New Roman", Font.BOLD, 20));
					label.setBounds(230, 8, 390, 29);
					frame.getContentPane().add(label);
				
					System.out.println(books);
					resultField = new JTextArea();
					resultField.setEditable(false);
					resultField.setBounds(120, 80, 600, 300);
					resultField.setLineWrap(true);
					resultField.setWrapStyleWord(true);
					
					// resultField.setText(null);
					    for (int i=0; i<books.size();i++) {
					            resultField.append((i+1)+". "+books.get(i).getBookTitle()+"("+books.get(i).getBookStatus()+")\n");
					        } 
					    

						frame.getContentPane().add(resultField);
						
					
					JButton btnBack = new JButton("Back");
					btnBack.setBounds(555, 415, 120, 25);
					//panel.setComponentZOrder(comp, index);
					frame.getContentPane().add(btnBack);
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.dispose();
							GUI.main(null);
						}
					});
				
			
			}

   
   
}	
	/*protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	*//**
	 * Create the frame.
	 * @param <JLabel>
	 *//*

	public  ListBooksGUI() {

	}
	*/


