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

public class UserSignUp extends JFrame {

	classes.User user = new classes.User();
	
	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField studentNumField;
	private JTextField contactNumField;
	private JTextField emailField;
	private JTextField addressField;
	private JComboBox yearLevelChoice;
	private JComboBox courseChoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignUp frame = new UserSignUp();
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
	public UserSignUp() {
		setTitle("NEUST LIBRARY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 50, 510, 650);
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
		panel.setBounds(0, 220, 495, 395);
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
		
		JLabel yearLevel = new JLabel("YEAR LEVEL:");
		yearLevel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		yearLevel.setBounds(40, 280, 125, 30);
		panel.add(yearLevel);
		
		JLabel course = new JLabel("COURSE:");
		course.setFont(new Font("Times New Roman", Font.BOLD, 16));
		course.setBounds(280, 280, 125, 30);
		panel.add(course);
		
		JButton cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel.setBackground(new Color(204, 204, 204));
		cancel.setBounds(50, 350, 100, 30);
		panel.add(cancel);
		
		JButton login = new JButton("SUBMIT");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String studentNum = studentNumField.getText();
				String contactNum = contactNumField.getText();
				String email = emailField.getText();
				String address = addressField.getText();
				String yearlvl = yearLevelChoice.getSelectedItem().toString();;
				String course = courseChoice.getSelectedItem().toString();;
				
				ResultSet rs;
				PreparedStatement ps;
				
				String name = "" + firstName + " " + lastName;
				if(firstName.equals("") || lastName.equals("") || contactNum.equals("") || email.equals("") || address.equals("") || 
						studentNumField.equals("")) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE FORM","EMPTY FIELDS", JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					user.addUser(firstName, lastName, studentNum, contactNum, email, address, yearlvl, course);
					
					firstNameField.setText("");
					lastNameField.setText("");
					studentNumField.setText("");
					contactNumField.setText("");
					emailField.setText("");
					addressField.setText("");
					yearLevelChoice.setSelectedIndex(0);
					courseChoice.setSelectedIndex(0);
					
					 JOptionPane.showMessageDialog(null, "Welcome, " + name + "\n Your account is successfully created");
						
					dispose();
					
				}
				
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 16));
		login.setBackground(new Color(204, 204, 204));
		login.setBounds(340, 350, 100, 30);
		panel.add(login);
		
		studentNumField = new JTextField();
		studentNumField.setColumns(10);
		studentNumField.setBounds(30, 110, 200, 25);
		panel.add(studentNumField);
		
		contactNumField = new JTextField();
		contactNumField.setColumns(10);
		contactNumField.setBounds(270, 110, 200, 25);
		panel.add(contactNumField);
		
		JLabel contactNumber = new JLabel("CONTACT NUMBER:");
		contactNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contactNumber.setBounds(280, 140, 160, 30);
		panel.add(contactNumber);
		
		JLabel studentNum = new JLabel("STUDENT NO.:");
		studentNum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		studentNum.setBounds(40, 140, 150, 30);
		panel.add(studentNum);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(30, 180, 200, 25);
		panel.add(emailField);
		
		JLabel emaill = new JLabel("EMAIL:");
		emaill.setFont(new Font("Times New Roman", Font.BOLD, 16));
		emaill.setBounds(40, 210, 100, 30);
		panel.add(emaill);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(270, 180, 200, 25);
		panel.add(addressField);
		
		JLabel address = new JLabel("ADDRESS:");
		address.setFont(new Font("Times New Roman", Font.BOLD, 16));
		address.setBounds(280, 210, 100, 30);
		panel.add(address);
		
		yearLevelChoice = new JComboBox(new Object[]{});
		yearLevelChoice.setModel(new DefaultComboBoxModel(new String[] {"First Year", "Second Year", "Third Year", "Fourth Year"}));
		yearLevelChoice.setBounds(30, 250, 200, 30);
		panel.add(yearLevelChoice);
		
		courseChoice = new JComboBox(new Object[]{});
		courseChoice.setModel(new DefaultComboBoxModel(new String[] {"Laboratory High", "BSIT", "BSED", "BSBA"}));
		courseChoice.setBounds(270, 250, 200, 30);
		panel.add(courseChoice);
		
		String choice[] = {"Admin", "User"};
		
	}
}
