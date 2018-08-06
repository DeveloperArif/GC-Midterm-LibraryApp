package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Library Terminal");
		frame.setSize(800, 600);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		
		JButton listButton = new JButton("List Books");
		JButton searchButton = new JButton("Search Catalog");
		JButton checkoutButton = new JButton("Checkout Book");
		JButton returnButton = new JButton("Return Book");
		JButton donateButton = new JButton("Donate Book");
		JButton exitButton = new JButton("Exit");
		panel.add(listButton);
		panel.add(searchButton);
		panel.add(checkoutButton);
		panel.add(returnButton);
		panel.add(donateButton);
		panel.add(exitButton);
		frame.add(panel,BorderLayout.CENTER);
		
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
