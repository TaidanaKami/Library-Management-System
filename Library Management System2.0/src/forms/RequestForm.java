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

public class RequestForm extends JFrame {

	classes.User user = new classes.User();
	classes.Book book = new classes.Book();
	classes.Issue issue = new classes.Issue();
	
	private JPanel contentPane;
	private JTextField userNameField;
	private JLabel userID;
	private JLabel userNameData;
	private static JLabel bookID;
	private static JLabel bookTitleData;
	private JTextField bookTitleField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestForm frame = new RequestForm();
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
	public RequestForm() {
		setTitle("NEUST LIBRARY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 50, 510, 600);
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
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(20, 40, 150, 25);
		panel.add(userNameField);
		
		JLabel userName = new JLabel("USER NAME:");
		userName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userName.setBounds(30, 70, 150, 30);
		panel.add(userName);
		
		JLabel lblNewLabel_1 = new JLabel("ISSUE DATE:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 200, 100, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DUE DATE:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(270, 200, 100, 25);
		panel.add(lblNewLabel_1_1);
		
		JDateChooser dateReturn = new JDateChooser();
		dateReturn.setBounds(370, 200, 100, 30);
		panel.add(dateReturn);
		
		JDateChooser dateIssue = new JDateChooser();
		dateIssue.setBounds(120, 200, 100, 30);
		panel.add(dateIssue);
		
		JButton cancel = new JButton("BACK");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel.setBackground(new Color(204, 204, 204));
		cancel.setBounds(50, 300, 100, 30);
		panel.add(cancel);
		
		JButton submit = new JButton("SUBMIT");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userNameField.equals("")) {
					
					JOptionPane.showMessageDialog(null, "YOU NEED TO CHECK IF THE USER IS EXIST BY FINDING USER NAME","USER NOT FOUND", 2);
					
				}else {
					
					try {
						
						int id;
						//double issId = new classes.Issue().getLoanById(id).getPayment();
						Integer userId = Integer.parseInt(userID.getText());
						Integer bookId = Integer.parseInt(bookID.getText());
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String issueDate = dateFormat.format(dateIssue.getDate());
						String returnDate = dateFormat.format(dateReturn.getDate());
						
						Date issDate = dateFormat.parse(issueDate);
						Date rtnDate = dateFormat.parse(returnDate);
						
						//checkOutPayment
						
						double cost = 0;
						
						if(rtnDate.before(issDate)) {
							
							JOptionPane.showMessageDialog(null, "RETURN DATE MUST BE AFTER THE ISSUE DATE","WRONG RETURN DATE", 2);
							
						}else {
							
							int quantity = new classes.Book().getBookId(bookId).getQuantity();
							book.setQuantity_Minus_One(bookId, quantity-1);
							
							issue.addIssue(userId, bookId, issueDate, returnDate, "issued", cost);
							
							userNameData.setText("");
							bookTitleData.setText("");
							userID.setText("ID");
							bookID.setText("ID");
							userNameField.setText("");
							dateIssue.setDate(new Date());
							dateReturn.setDate(new Date());
							
						}
						
					}catch (NullPointerException | ParseException ex) {
						
						JOptionPane.showMessageDialog(null, "SELECT ISSUE & RETURN DATE","SELECT DATE", 2);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, e1);
					}
					
				}
				
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		submit.setBackground(new Color(204, 204, 204));
		submit.setBounds(340, 300, 100, 30);
		panel.add(submit);
		
		JButton btnNewButton = new JButton("Find");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userNameField.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
					
				}else {

					try {
						
						classes.User checkOutUserFind = null;
						int id = Integer.parseInt(userNameField.getText());
						
						checkOutUserFind = user.getUserById(id);
							
						String fullname = (user.getUserById(checkOutUserFind.getId())).getFirstName() + " " +
								  (user.getUserById(checkOutUserFind.getId())).getLastName();
							
						if(checkOutUserFind != null) {
							
							userID.setText(String.valueOf(checkOutUserFind.getId()));
							userNameData.setText(fullname);
							
							
						}else{
						
							JOptionPane.showMessageDialog(null, "THIS USER DOES NOT EXIST","USER NOT FOUND", 2);
							
						}
						
					}catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID LIBRARY NUMBER","ERROR", 3);
						
					}
					
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton.setBounds(170, 41, 70, 25);
		panel.add(btnNewButton);
		
		JLabel userNameText = new JLabel("NAME:");
		userNameText.setFont(new Font("Times New Roman", Font.BOLD, 15));
		userNameText.setBounds(20, 160, 60, 20);
		panel.add(userNameText);
		
		userID = new JLabel("ID");
		userID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		userID.setBounds(20, 120, 30, 20);
		panel.add(userID);
		
		userNameData = new JLabel("");
		userNameData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userNameData.setBounds(80, 160, 150, 20);
		panel.add(userNameData);
		
		bookID = new JLabel("ID");
		bookID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bookID.setBounds(270, 120, 30, 20);
		panel.add(bookID);
		
		JLabel bookTitleText = new JLabel("TITLE:");
		bookTitleText.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bookTitleText.setBounds(270, 160, 60, 20);
		panel.add(bookTitleText);
		
		bookTitleData = new JLabel();
		bookTitleData.setBackground(new Color(255, 255, 204));
		bookTitleData.setBounds(330, 160, 150, 20);
		panel.add(bookTitleData);
		
		JLabel Book = new JLabel("BOOK:");
		Book.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Book.setBounds(280, 70, 150, 30);
		panel.add(Book);
		
		bookTitleField = new JTextField();
		bookTitleField.setColumns(10);
		bookTitleField.setBounds(270, 40, 150, 25);
		panel.add(bookTitleField);
		
		JButton btnNewButton_1 = new JButton("Find");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(bookTitleField.getText());
				
				if(bookTitleField.equals("")) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					classes.Book bookFind;
					try {
						
						bookFind = book.getBookId(id);
						bookID.setText(String.valueOf(bookFind.getId()));
						bookTitleData.setText(String.valueOf(bookFind.getTitle()));
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "INVALID BOOK ID","ERROR", 2);
					}
					
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton_1.setBounds(420, 40, 70, 25);
		panel.add(btnNewButton_1);
		
	}
	
	public static void displayBookData(int id, String title) {
		
		bookID.setText(String.valueOf(id));
		bookTitleData.setText(String.valueOf(title));
		
	}
}
