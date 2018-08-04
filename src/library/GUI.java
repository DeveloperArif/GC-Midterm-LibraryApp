package library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
		/*frame.add(new JButton("List Books"));
		frame.add(new JButton("Search Catalog"));
		frame.add(new JButton("Checkout Books"));
		frame.add(new JButton("Return Books"));
		frame.add(new JButton("Donate Books"));
		frame.add(new JButton("Exit"));*/


	
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(ClassNotFoundException | IllegalAccessException 
				| InstantiationException | UnsupportedLookAndFeelException ex) {
			System.err.println("Unsupported look and feel");
		}
		frame.setVisible(true);
	}

}
