//@ Sasi, Anesha and Arif

package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.scene.paint.Color;

public class MainGUIApp {

	public static void main(String[] args) {

//		ImageIcon img = new ImageIcon("Desktop/logologon.png");
	
		   
		JFrame frame = new JFrame("Library Terminal");
		frame.setBounds(100, 100, 800, 500);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setIconImage(img);
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/users/sasi/Desktop/logologon.png"));
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Grand Circus Library - Main Menu");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 22));		
		label.setBounds(200, 8, 397, 29);
		frame.getContentPane().add(label);
	
		JButton listButton = new JButton("List Books");
		JButton searchButton = new JButton("Search Catalog");
		JButton checkoutButton = new JButton("Checkout Book");
		JButton returnButton = new JButton("Return Book");
		JButton donateButton = new JButton("Donate Book");
		JButton exitButton = new JButton("Exit");
		
		
		listButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		listButton.setBounds(320, 50, 180, 23);
		frame.getContentPane().add(listButton);

		frame.getContentPane().add(searchButton);
		searchButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchButton.setBounds(320, 100, 180, 23);

		frame.getContentPane().add(checkoutButton);
		checkoutButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		checkoutButton.setBounds(320, 150, 180, 23);

		frame.getContentPane().add(returnButton);
		returnButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		returnButton.setBounds(320, 200, 180, 23);

		frame.getContentPane().add(donateButton);
		donateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		donateButton.setBounds(320, 250, 180, 23);

		frame.getContentPane().add(exitButton);
		exitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		exitButton.setBounds(320, 300, 180, 23);
		
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ListBooksGUI.main(null);
			}
		});
		
		
		checkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CheckoutBookGUI.main(null);
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SearchBookGUI.main(null);
			}
		});
		
		
		donateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				DonateBookGUI.main(null);
			}
		});
		
		
	
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ReturnBookGUI.main(null);
			}
		});
		
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();				
			}
		});

		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(ClassNotFoundException | IllegalAccessException 
				| InstantiationException | UnsupportedLookAndFeelException ex) {
			System.err.println("Unsupported look and feel");
		}
		frame.setVisible(true);
	}

}
