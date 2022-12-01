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

public class Login extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox choices;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
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
		
		JLabel username = new JLabel("USERNAME:");
		username.setFont(new Font("Times New Roman", Font.BOLD, 16));
		username.setBounds(90, 70, 125, 30);
		panel.add(username);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 70, 200, 25);
		panel.add(textField);
		
		JLabel password = new JLabel("PASSWORD:");
		password.setFont(new Font("Times New Roman", Font.BOLD, 16));
		password.setBounds(90, 130, 125, 30);
		panel.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 130, 200, 25);
		panel.add(passwordField);
		
		JLabel userType = new JLabel("USER TYPE:");
		userType.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userType.setBounds(90, 190, 125, 30);
		panel.add(userType);
		
		String choice[] = {"Admin", "User"};
		choices = new JComboBox(choice);
		choices.setBounds(200, 190, 200, 25);
		panel.add(choices);
		
		JLabel register = new JLabel("Click here to register:");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SignIn home = new SignIn();
				home.setVisible(true);
				
			}
		});
		register.setHorizontalAlignment(SwingConstants.CENTER);
		register.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		register.setBounds(150, 250, 200, 15);
		panel.add(register);
		
		JButton login = new JButton("LOGIN");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = passwordField.getText();
				String userType = choices.getSelectedItem().toString();;
				
				ResultSet rs;
				PreparedStatement ps;
				
				String query = "SELECT * FROM `signin` WHERE `username` = ? AND `password` = ? AND `userType` = ?";
				
				if(username.trim().equals("") || password.trim().equals("")) {
					
					JOptionPane.showMessageDialog(login, "PLEASE FILL-UP THE FORM","EMPTY FIELDS", JOptionPane.ERROR_MESSAGE);
					
				}else {
					
					try {
						
						ps = database.getConnection().prepareStatement(query);
						ps.setString(1, username);
						ps.setString(2, password);
						ps.setString(3, userType);
						
						rs = ps.executeQuery();
						
						if(rs.next() && userType.equals("Admin")) {
							
							Home home = new Home();
							home.show();
							
							dispose();
							
						}else if(userType.equals("User")) {
							
							UserDashboard home = new UserDashboard();
							home.show();
							
							dispose();
							
						}else {
							
							JOptionPane.showMessageDialog(login, "INVALID USERNAME OR PASSWORD","ERROR", JOptionPane.ERROR_MESSAGE);
							
						}
						
					}catch(SQLException ex){
						
						Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
						
					}
					
				}
				
			}
				
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 16));
		login.setBackground(new Color(204, 204, 204));
		login.setBounds(200, 290, 100, 30);
		panel.add(login);
		
	}
	
}
