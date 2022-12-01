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

public class UserDashboard extends JFrame {

	classes.Author author = new classes.Author();
	classes.Subject subject = new classes.Subject();
	classes.Publisher publisher = new classes.Publisher();
	classes.User user = new classes.User();
	classes.Book book = new classes.Book();
	HashMap<String, Integer> subjectMap = subject.getSubjectMap();
	static classes.Function func = new classes.Function();
	classes.Issue issue = new classes.Issue();
	private JPanel contentPane;
	String imagePath = null;
	JLabel[] covers = new JLabel[3];
	private JLabel dashboardBookNum;
	
	boolean bookExist = false;
	boolean userExist = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashboard frame = new UserDashboard();
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
	public UserDashboard() {
		setTitle("NEUST LIBRARY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 15, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image logo = new ImageIcon(this.getClass().getResource("/Images/nuestLogo.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		JPanel Headline = new JPanel();
		Headline.setBounds(0, 0, 884, 120);
		contentPane.add(Headline);
		Headline.setBackground(new Color(255, 255, 204));
		Headline.setLayout(null);
		
		JLabel neustLogo = new JLabel("");
		neustLogo.setBounds(10, 10, 100, 100);
		Headline.add(neustLogo);
		neustLogo.setIcon(new ImageIcon(logo));
		
		JLabel library = new JLabel("NUEVA ECIJA UNIVERSITY OF SCIENCE AND TECHNOLOGY");
		library.setForeground(Color.BLUE);
		library.setFont(new Font("Times New Roman", Font.BOLD, 25));
		library.setHorizontalAlignment(SwingConstants.RIGHT);
		library.setBounds(100, 15, 766, 30);
		Headline.add(library);
		
		JLabel lblNewLabel = new JLabel("SAN ISIDRO CAMPUS");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(100, 80, 235, 25);
		Headline.add(lblNewLabel);
		
		JLabel lblUniversityLibrary = new JLabel("UNIVERSITY LIBRARY SYSTEM");
		lblUniversityLibrary.setForeground(Color.BLUE);
		lblUniversityLibrary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUniversityLibrary.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblUniversityLibrary.setBounds(100, 48, 420, 30);
		Headline.add(lblUniversityLibrary);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.show();
				
				dispose();
				
			}
		});
		btnNewButton.setBounds(777, 87, 90, 25);
		Headline.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(0, 120, 884, 541);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 884, 20);
		panel_5.setBackground(Color.WHITE);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 0, 255));
		panel_6.setBounds(0, 0, 884, 10);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBackground(new Color(255, 255, 0));
		panel_6_1.setBounds(0, 10, 884, 10);
		panel_5.add(panel_6_1);
		
		JLabel lblNewLabel_3 = new JLabel("WELCOME DEAR USER");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Ink Free", Font.BOLD, 35));
		lblNewLabel_3.setBounds(140, 110, 500, 50);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("NEW BOOKS ADDED");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2.setBounds(20, 230, 400, 30);
		panel.add(lblNewLabel_2);
		
		JPanel bookCoverPage = new JPanel();
		bookCoverPage.setLayout(null);
		bookCoverPage.setBackground(new Color(255, 255, 153));
		bookCoverPage.setBounds(10, 270, 680, 240);
		panel.add(bookCoverPage);
		
		JPanel bookPanel_1 = new JPanel();
		bookPanel_1.setLayout(null);
		bookPanel_1.setBackground(new Color(255, 204, 153));
		bookPanel_1.setBounds(20, 20, 200, 200);
		bookCoverPage.add(bookPanel_1);
		
		JLabel bookCover_1 = new JLabel("");
		bookCover_1.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_1.setBounds(0, 0, 200, 200);
		bookPanel_1.add(bookCover_1);
		
		JPanel bookPanel_2 = new JPanel();
		bookPanel_2.setLayout(null);
		bookPanel_2.setBackground(new Color(255, 204, 153));
		bookPanel_2.setBounds(240, 20, 200, 200);
		bookCoverPage.add(bookPanel_2);
		
		JLabel bookCover_2 = new JLabel("");
		bookCover_2.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_2.setBounds(0, 0, 200, 200);
		bookPanel_2.add(bookCover_2);
		
		JPanel bookPanel_3 = new JPanel();
		bookPanel_3.setLayout(null);
		bookPanel_3.setBackground(new Color(255, 204, 153));
		bookPanel_3.setBounds(460, 20, 200, 200);
		bookCoverPage.add(bookPanel_3);
		
		JLabel bookCover_3 = new JLabel("");
		bookCover_3.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_3.setBounds(0, 0, 200, 200);
		bookPanel_3.add(bookCover_3);
		
		JButton checkOutSearchBtn_1 = new JButton("BOOK LIST");
		checkOutSearchBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardBookList list = new DashboardBookList();
				list.show();
				countBook();
				
			}
		});
		checkOutSearchBtn_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutSearchBtn_1.setBounds(710, 220, 150, 50);
		panel.add(checkOutSearchBtn_1);
		
		JButton checkInSubjectList_1 = new JButton("SUBJECT LIST");
		checkInSubjectList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardSubjectList list = new DashboardSubjectList();
				list.show();
				countBook();
				
			}
		});
		checkInSubjectList_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInSubjectList_1.setBounds(710, 300, 150, 50);
		panel.add(checkInSubjectList_1);
		
		JButton checkOutBtn_1 = new JButton("AUTHOR LIST");
		checkOutBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardAuthorList list = new DashboardAuthorList();
				list.show();
				countBook();
				
			}
		});
		checkOutBtn_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBtn_1.setBounds(710, 380, 150, 50);
		panel.add(checkOutBtn_1);
		
		JButton checkIn_1 = new JButton("REGISTER");
		checkIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserSignUp frame = new UserSignUp();
				 frame.show();
				 countBook();
				
			}
		});
		checkIn_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkIn_1.setBounds(710, 460, 150, 50);
		panel.add(checkIn_1);
		
		covers[0] = bookCover_1;
		covers[1] = bookCover_2;
		covers[2] = bookCover_3;
		
		JPanel bookAvailablePanel = new JPanel();
		bookAvailablePanel.setLayout(null);
		bookAvailablePanel.setBackground(Color.YELLOW);
		bookAvailablePanel.setBounds(730, 81, 100, 100);
		panel.add(bookAvailablePanel);
		
		dashboardBookNum = new JLabel("13");
		dashboardBookNum.setHorizontalAlignment(SwingConstants.CENTER);
		dashboardBookNum.setFont(new Font("Tahoma", Font.BOLD, 20));
		dashboardBookNum.setBounds(0, 25, 100, 50);
		bookAvailablePanel.add(dashboardBookNum);
		
		JLabel checkOutBookAvailable = new JLabel("BOOK AVAILABLE:");
		checkOutBookAvailable.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBookAvailable.setBounds(710, 31, 150, 30);
		panel.add(checkOutBookAvailable);
		
		book.displayBookCover(covers);
		
		/**
		 * To View the Data in all the Tables
		 */	
		
		countBook();
		
	}
	
	private void formComponentMoved(java.awt.event.ComponentEvent evt) {   
		
	       this.setLocationRelativeTo(null);
	       
	    }   
	
	public void countBook() {
		
		int total = func.countBook("book");
		dashboardBookNum.setText(String.valueOf(total));
		
	}
	
}

