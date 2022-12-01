package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import classes.Author;
import classes.Book;
import classes.Function;
import classes.Publisher;
import classes.Subject;
import classes.User;
import classes.database;
import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class AdminSignUp extends JFrame {

	
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField ContactNumField;
	private JTextField emailField;
	private JTextField addressField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSignUp frame = new AdminSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Home Panel
	 */
	public AdminSignUp() {
		setTitle("NEUST LIBRARY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 70, 515, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image logo = new ImageIcon(this.getClass().getResource("/Images/nuestLogo.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		JPanel Headline = new JPanel();
		Headline.setBounds(0, 0, 500, 220);
		contentPane.add(Headline);
		Headline.setBackground(new Color(255, 255, 204));
		Headline.setLayout(null);
		
		JLabel neustLogo = new JLabel("");
		neustLogo.setBounds(200, 10, 100, 100);
		Headline.add(neustLogo);
		neustLogo.setIcon(new ImageIcon(logo));
		
		JLabel library = new JLabel("NUEVA ECIJA UNIVERSITY OF SCIENCE");
		library.setForeground(Color.BLUE);
		library.setFont(new Font("Times New Roman", Font.BOLD, 25));
		library.setHorizontalAlignment(SwingConstants.CENTER);
		library.setBounds(0, 120, 500, 30);
		Headline.add(library);
		
		JLabel lblNewLabel = new JLabel("SAN ISIDRO CAMPUS");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(120, 190, 235, 25);
		Headline.add(lblNewLabel);
		
		JLabel lblUniversityLibrary = new JLabel("AND TECHNOLOGY UNIVERSITY");
		lblUniversityLibrary.setForeground(Color.BLUE);
		lblUniversityLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUniversityLibrary.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUniversityLibrary.setBounds(0, 150, 450, 30);
		Headline.add(lblUniversityLibrary);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(0, 220, 500, 341);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 500, 20);
		panel_5.setBackground(Color.WHITE);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 0, 255));
		panel_6.setBounds(0, 0, 500, 10);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBackground(new Color(255, 255, 0));
		panel_6_1.setBounds(0, 10, 500, 10);
		panel_5.add(panel_6_1);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(30, 40, 200, 25);
		panel.add(firstNameField);
		
		JLabel firstName = new JLabel("FIRST NAME:");
		firstName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		firstName.setBounds(40, 70, 150, 30);
		panel.add(firstName);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(270, 40, 200, 25);
		panel.add(lastNameField);
		
		JLabel lastName = new JLabel("LAST NAME:");
		lastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lastName.setBounds(280, 70, 150, 30);
		panel.add(lastName);
		
		JLabel username = new JLabel("USERNAME:");
		username.setFont(new Font("Times New Roman", Font.BOLD, 16));
		username.setBounds(40, 260, 125, 30);
		panel.add(username);
		
		JLabel password = new JLabel("PASSWORD:");
		password.setFont(new Font("Times New Roman", Font.BOLD, 16));
		password.setBounds(280, 260, 125, 30);
		panel.add(password);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(30, 230, 200, 25);
		panel.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(270, 230, 200, 25);
		panel.add(passwordField);
		
		JButton cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminLogIn frame = new AdminLogIn();
				frame.show();
				
				dispose();
				
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel.setBackground(new Color(204, 204, 204));
		cancel.setBounds(50, 300, 100, 30);
		panel.add(cancel);
		
		JButton login = new JButton("SUBMIT");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String contactNum = ContactNumField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				ResultSet rs;
				PreparedStatement ps;
				
				String query = "INSERT INTO `admin`(`firstName`, `lastName`, `contactNum`, `email`, `address`, `username`, `password`) VALUES (?,?,?,?,?,?,?)";
				
				String name = "" + firstName + " " + lastName;
				if(firstName.trim().equals("") || lastName.trim().equals("") || contactNum.trim().equals("") 
						|| email.trim().equals("") || address.trim().equals("") ||  username.trim().equals("") 
						|| password.trim().equals("")) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE FORM","EMPTY FIELDS", JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					try {
						
						ps = database.getConnection().prepareStatement(query);
						ps.setString(1, firstName);
						ps.setString(2, lastName);
						ps.setString(3, contactNum);
						ps.setString(4, email);
						ps.setString(5, address);
						ps.setString(6, username);
						ps.setString(7, password);
						
						int x =ps.executeUpdate();
	                     JOptionPane.showMessageDialog(null, "Welcome, " + name + "\n Your account is successfully created");
	                     AdminLogIn frame = new AdminLogIn();
	                     frame.show();
							
						dispose();
						
					}catch(SQLException ex){
						
						JOptionPane.showMessageDialog(null, "PLEASE FILL THE FORM CORRECTLY","EMPTY FIELDS", JOptionPane.ERROR_MESSAGE);
						Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
						
					}
					
				}
				
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 16));
		login.setBackground(new Color(204, 204, 204));
		login.setBounds(340, 300, 100, 30);
		panel.add(login);
		
		ContactNumField = new JTextField();
		ContactNumField.setColumns(10);
		ContactNumField.setBounds(30, 110, 200, 25);
		panel.add(ContactNumField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(270, 110, 200, 25);
		panel.add(emailField);
		
		JLabel contactNumber = new JLabel("CONTACT NUMBER:");
		contactNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contactNumber.setBounds(40, 140, 160, 30);
		panel.add(contactNumber);
		
		JLabel email = new JLabel("EMAIL");
		email.setFont(new Font("Times New Roman", Font.BOLD, 16));
		email.setBounds(280, 140, 150, 30);
		panel.add(email);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(140, 181, 200, 25);
		panel.add(addressField);
		
		JLabel address = new JLabel("ADDRESS:");
		address.setFont(new Font("Times New Roman", Font.BOLD, 16));
		address.setBounds(40, 180, 100, 30);
		panel.add(address);
		
		String choice[] = {"Admin", "User"};
		
	}
}
