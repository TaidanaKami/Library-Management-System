package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import classes.Admin;
import classes.Author;
import classes.Book;
import classes.Function;
import classes.Issue;
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
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.Point;

public class Home extends JFrame {

	classes.Author author = new classes.Author();
	classes.Subject subject = new classes.Subject();
	classes.Publisher publisher = new classes.Publisher();
	classes.User user = new classes.User();
	classes.Admin admin = new classes.Admin();
	classes.Book book = new classes.Book();
	HashMap<String, Integer> subjectMap = subject.getSubjectMap();
	static classes.Function func = new classes.Function();
	classes.Issue issue = new classes.Issue();
	private JPanel contentPane;
	JLabel[] covers = new JLabel[3];
	private JLabel dashboardBookNum;
	
	//book
	
	private JTextField bookIDField;
	private JTextField bookAccessionNoField;
	private JTextField isbnField;
	private JTextField bookTitleField;
	private static JLabel AuthorID;
	private static JTextField bookAuthorField;
	private static JLabel SubjectID;
	private JComboBox<String> chooseSubject;
	private static JLabel PublisherID;
	private static JTextField bookPublisherField;
	private static JSpinner quantitySpinner;
	private JComboBox chooseDate;
	private JTextField bookFindField;
	private JTextField bookPriceField;
	private JLabel bookImage;
	private JLabel bookChoosePicture;
	private JTable bookTable;
	String imagePath = null;
	
	//author
	
	private JTextField authorIDField;
	private JTextField authorFirstNameField;
	private JTextField authorLastNameField;
	private JTextField expertiseField;
	private JTable authorTable;
	
	//subject
	
	private JTextField subjectIDField;
	private JTextField subjectNameField;
	private JTable subjectTable;
	
	//publisher
	
	private JTextField publisherIDField;
	private JTextField publisherNameField;
	private JTextField publisherCityField;
	private JTextField publisherStreetField;
	private JTextField publisherZipCodeField;
	private JTable publisherTable;
	
	//check out
	
	private static JLabel checkOutUserName;
	private static JLabel checkOutPayment;
	private static JTextField checkOutUserField;
	private JTextField checkOutBookField;
	private static JTextPane checkOutBookTitleData;
	private static JLabel checkOutBookCover;
	private static JLabel CheckOutUserID;
	private static JLabel checkOutBookID;
	private static JLabel availableYesNo;
	private JLabel checkOutBookNum;
	
	boolean bookExist = false;
	boolean userExist = false;
	
	//check in
	
	private JTable checkInTable;
	private JTextPane checkInBookTitleData;
	private JLabel checkInUserNameData;
	private JLabel checkInPaymentData;
	private JDateChooser checkInDateIssue;
	private JLabel statusData;
	private JDateChooser checkInDateReturn;
	private JLabel checkInUserID;
	private JLabel checkInBookID;
	private JLabel checkInIssuedIDData;
	private JLabel checkInBookNum;
	private JTable historyTable;
	private JTextField findBorrowerField;
	private JLabel checkInBookCover;
	
	//user status
	
	private JTable table;
	private JLabel borrowerID;
	private JLabel borrowerNameData;
	private JLabel borrowerLoanData;
	private JTextArea receipt;
	private JLabel borrowerTitleData;
	private JLabel borrowerBookID;
	
	//book history
	
	private JTextArea history;
	
	//use account
	
	private JTextField userIDField;
	private JTextField userFirstNameField;
	private JTextField useLastNameField;
	private JTextField studentNoField;
	private JTextField userContactField;
	private JTextField userEmailField;
	private JTextField userAddressField;
	private JComboBox yearLevelChoice;
	private JComboBox courseChoice;
	private JTable userTable;
	private JTextField adminFirstNameField;
	private JTextField adminLastNameField;
	private JTextField adminEmailField;
	private JTextField adminContactNumField;
	private JTextField adminAddressField;
	private JTextField adminUsernameField;
	private JPasswordField adminPasswordField;
	private JTable adminTable;
	private JTextField adminIDField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
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
				
				int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Log Out?","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
				
				if(confirm == JOptionPane.YES_OPTION) {
					
					Dashboard dashboard = new Dashboard();
					dashboard.show();
					
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Log Out Faild!","CANCEL DELETION", 2);
				}
				
			}
		});
		btnNewButton.setBounds(784, 83, 90, 25);
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
		
		JTabbedPane homeTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		homeTabbedPane.setForeground(new Color(255, 255, 255));
		homeTabbedPane.setBackground(new Color(0, 0, 255));
		homeTabbedPane.setBounds(0, 20, 884, 521);
		panel.add(homeTabbedPane);
		
		JPanel homePanel = new JPanel();
		homePanel.setForeground(new Color(0, 0, 0));
		homePanel.setBackground(new Color(255, 255, 204));
		homeTabbedPane.addTab("Dashboard", new ImageIcon(Home.class.getResource("/Images/icons8-dashboard-16.png")), homePanel, null);
		homeTabbedPane.setForegroundAt(0, new Color(255, 255, 255));
		homeTabbedPane.setBackgroundAt(0, new Color(0, 0, 255));
		homePanel.setLayout(null);
		
		JButton checkOutSearchBtn_1 = new JButton("BOOK LIST");
		checkOutSearchBtn_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-open-book-30.png")));
		checkOutSearchBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardBookList list = new DashboardBookList();
				list.show();
				
			}
		});
		checkOutSearchBtn_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		checkOutSearchBtn_1.setBounds(720, 230, 150, 50);
		homePanel.add(checkOutSearchBtn_1);
		
		JButton checkInSubjectList_1 = new JButton("SUBJECT LIST");
		checkInSubjectList_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-list-view-30.png")));
		checkInSubjectList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardSubjectList list = new DashboardSubjectList();
				list.show();
				countBook();
				
			}
		});
		checkInSubjectList_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		checkInSubjectList_1.setBounds(720, 315, 150, 50);
		homePanel.add(checkInSubjectList_1);
		
		JButton checkIn_1 = new JButton("AUTHOR LIST");
		checkIn_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-document-writer-30.png")));
		checkIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DashboardAuthorList list = new DashboardAuthorList();
				list.show();
				countBook();
				
			}
		});
		checkIn_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkIn_1.setBounds(720, 400, 150, 50);
		homePanel.add(checkIn_1);
		
		JPanel bookCoverPage = new JPanel();
		bookCoverPage.setBackground(new Color(255, 255, 153));
		bookCoverPage.setBounds(20, 220, 680, 240);
		homePanel.add(bookCoverPage);
		bookCoverPage.setLayout(null);
		
		JPanel bookPanel_1 = new JPanel();
		bookPanel_1.setBackground(new Color(255, 204, 153));
		bookPanel_1.setBounds(20, 20, 200, 200);
		bookCoverPage.add(bookPanel_1);
		bookPanel_1.setLayout(null);
		
		JLabel bookCover_1 = new JLabel("");
		bookCover_1.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_1.setBounds(0, 0, 200, 200);
		bookPanel_1.add(bookCover_1);
		
		JPanel bookPanel_2 = new JPanel();
		bookPanel_2.setBackground(new Color(255, 204, 153));
		bookPanel_2.setBounds(240, 20, 200, 200);
		bookCoverPage.add(bookPanel_2);
		bookPanel_2.setLayout(null);
		
		JLabel bookCover_2 = new JLabel("");
		bookCover_2.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_2.setBounds(0, 0, 200, 200);
		bookPanel_2.add(bookCover_2);
		
		JPanel bookPanel_3 = new JPanel();
		bookPanel_3.setBackground(new Color(255, 204, 153));
		bookPanel_3.setBounds(460, 20, 200, 200);
		bookCoverPage.add(bookPanel_3);
		bookPanel_3.setLayout(null);
		
		JLabel bookCover_3 = new JLabel("");
		bookCover_3.setHorizontalAlignment(SwingConstants.CENTER);
		bookCover_3.setBounds(0, 0, 200, 200);
		bookPanel_3.add(bookCover_3);
		
		JLabel lblNewLabel_2 = new JLabel("NEW BOOKS ADDED");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_2.setBounds(30, 180, 400, 30);
		homePanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("WELCOME DEAR ADMIN");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_3.setBounds(150, 60, 500, 50);
		homePanel.add(lblNewLabel_3);
		
		covers[0] = bookCover_1;
		covers[1] = bookCover_2;
		covers[2] = bookCover_3;
		
		JPanel bookAvailablePanel_1 = new JPanel();
		bookAvailablePanel_1.setLayout(null);
		bookAvailablePanel_1.setBackground(Color.YELLOW);
		bookAvailablePanel_1.setBounds(740, 61, 100, 100);
		homePanel.add(bookAvailablePanel_1);
		
		dashboardBookNum = new JLabel("13");
		dashboardBookNum.setHorizontalAlignment(SwingConstants.CENTER);
		dashboardBookNum.setFont(new Font("Tahoma", Font.BOLD, 20));
		dashboardBookNum.setBounds(0, 25, 100, 50);
		bookAvailablePanel_1.add(dashboardBookNum);
		
		JLabel checkOutBookAvailable_1 = new JLabel("BOOK AVAILABLE:");
		checkOutBookAvailable_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBookAvailable_1.setBounds(720, 11, 150, 30);
		homePanel.add(checkOutBookAvailable_1);
		
		JPanel bookPanel = new JPanel();
		bookPanel.setForeground(Color.WHITE);
		bookPanel.setBackground(new Color(255, 255, 204));
		homeTabbedPane.addTab("Book", new ImageIcon(Home.class.getResource("/Images/icons8-open-book-16.png")), bookPanel, null);
		homeTabbedPane.setForegroundAt(1, new Color(255, 255, 255));
		homeTabbedPane.setBackgroundAt(1, new Color(0, 0, 255));
		bookPanel.setLayout(null);
		
		JTabbedPane bookTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		bookTabbedPane.setBackground(new Color(255, 255, 204));
		bookTabbedPane.setBounds(0, 0, 879, 493);
		bookPanel.add(bookTabbedPane);
		
		String[] date = {"", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", 
				"2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995",
				"1994", "1993", "1992", "1991", "1990"};
		
		/**
		 * Book Page Frame
		 */
		
		JPanel manageBookPanel = new JPanel();
		manageBookPanel.setForeground(Color.WHITE);
		manageBookPanel.setBackground(new Color(255, 255, 204));
		bookTabbedPane.addTab("Book", new ImageIcon(Home.class.getResource("/Images/icons8-literature-16.png")), manageBookPanel, null);
		bookTabbedPane.setForegroundAt(0, new Color(255, 255, 255));
		bookTabbedPane.setBackgroundAt(0, new Color(0, 0, 255));
		manageBookPanel.setLayout(null);
		
		JLabel bookID_1 = new JLabel("ID:");
		bookID_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookID_1.setBounds(10, 10, 60, 30);
		manageBookPanel.add(bookID_1);
		
		bookIDField = new JTextField();
		bookIDField.setEnabled(false);
		bookIDField.setColumns(10);
		bookIDField.setBounds(90, 10, 150, 25);
		manageBookPanel.add(bookIDField);
		
		JLabel isbn_1 = new JLabel("ISBN:");
		isbn_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		isbn_1.setBounds(290, 10, 60, 30);
		manageBookPanel.add(isbn_1);
		
		isbnField = new JTextField();
		isbnField.setColumns(10);
		isbnField.setBounds(390, 10, 150, 25);
		manageBookPanel.add(isbnField);
		
		JLabel bookAccessionNo = new JLabel("ACCESSION NO.:");
		bookAccessionNo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookAccessionNo.setBounds(580, 10, 130, 30);
		manageBookPanel.add(bookAccessionNo);
		
		bookAccessionNoField = new JTextField();
		bookAccessionNoField.setColumns(10);
		bookAccessionNoField.setBounds(720, 10, 150, 25);
		manageBookPanel.add(bookAccessionNoField);
		
		JLabel bookTitle = new JLabel("TITLE:");
		bookTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookTitle.setBounds(10, 60, 60, 30);
		manageBookPanel.add(bookTitle);
		
		bookTitleField = new JTextField();
		bookTitleField.setColumns(10);
		bookTitleField.setBounds(90, 60, 150, 25);
		manageBookPanel.add(bookTitleField);
		
		JLabel bookAuthor = new JLabel("AUTHOR:");
		bookAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookAuthor.setBounds(290, 60, 100, 30);
		manageBookPanel.add(bookAuthor);
		
		bookAuthorField = new JTextField();
		bookAuthorField.setColumns(10);
		bookAuthorField.setBounds(390, 60, 150, 25);
		bookAuthorField.setEditable(false);
		manageBookPanel.add(bookAuthorField);
		
		AuthorID = new JLabel("ID:");
		AuthorID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		AuthorID.setBounds(550, 60, 30, 30);
		manageBookPanel.add(AuthorID);
		
		JButton authorBtn = new JButton("FIND");
		authorBtn.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		authorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuthorList alist = new AuthorList();
				alist.formType = "edit";
				alist.setVisible(true);
				
			}
		});
		authorBtn.setFont(new Font("Times New Roman", Font.BOLD, 10));
		authorBtn.setBounds(580, 60, 80, 25);
		manageBookPanel.add(authorBtn);
		
		JLabel bookSubject = new JLabel("SUBJECT:");
		bookSubject.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookSubject.setBounds(10, 110, 75, 30);
		manageBookPanel.add(bookSubject);
		
		chooseSubject = new JComboBox();
		chooseSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int subId = subjectMap.get(chooseSubject.getSelectedItem().toString());
					SubjectID.setText(String.valueOf(subId));
					
					
				}catch(Exception ex) {
					
					JOptionPane.showMessageDialog(null, "GENRE ERROR","ERROR", 1);
					
				}
				
			}
				
		});
		chooseSubject.setBounds(90, 110, 150, 25);
		manageBookPanel.add(chooseSubject);
		
		SubjectID = new JLabel("ID:");
		SubjectID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		SubjectID.setBounds(250, 110, 30, 30);
		manageBookPanel.add(SubjectID);
		
		JLabel bookPublisher = new JLabel("PUBLISHER:");
		bookPublisher.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookPublisher.setBounds(290, 110, 100, 30);
		manageBookPanel.add(bookPublisher);
		
		bookPublisherField = new JTextField();
		bookPublisherField.setColumns(10);
		bookPublisherField.setBounds(390, 110, 150, 25);
		bookPublisherField.setEditable(false);
		manageBookPanel.add(bookPublisherField);
		
		PublisherID = new JLabel("ID:");
		PublisherID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		PublisherID.setBounds(550, 110, 30, 30);
		manageBookPanel.add(PublisherID);
		
		JButton publisherBtn = new JButton("FIND");
		publisherBtn.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		publisherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PublisherList alist = new PublisherList();
				alist.setVisible(true);
				
			}
		});
		publisherBtn.setFont(new Font("Times New Roman", Font.BOLD, 10));
		publisherBtn.setBounds(580, 110, 80, 25);
		manageBookPanel.add(publisherBtn);
		
		JLabel bookQuantity = new JLabel("QUANTITY:");
		bookQuantity.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookQuantity.setBounds(10, 160, 110, 30);
		manageBookPanel.add(bookQuantity);
		
		quantitySpinner = new JSpinner();
		quantitySpinner.setBounds(100, 160, 100, 25);
		manageBookPanel.add(quantitySpinner);
		
		JLabel bookCopyright = new JLabel("COPYRIGHT:");
		bookCopyright.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookCopyright.setBounds(230, 160, 110, 30);
		manageBookPanel.add(bookCopyright);
		chooseDate = new JComboBox(date);
		chooseDate.setBounds(340, 160, 120, 25);
		manageBookPanel.add(chooseDate);
		
		JLabel bookPrice = new JLabel("PRICE:");
		bookPrice.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookPrice.setBounds(500, 160, 60, 30);
		manageBookPanel.add(bookPrice);
		
		bookPriceField = new JTextField();
		bookPriceField.setColumns(10);
		bookPriceField.setBounds(560, 160, 100, 25);
		manageBookPanel.add(bookPriceField);
		
		JPanel bookImagePanel = new JPanel();
		bookImagePanel.setBackground(new Color(255, 255, 0));
		bookImagePanel.setBounds(680, 80, 100, 100);
		manageBookPanel.add(bookImagePanel);
		bookImagePanel.setLayout(null);
		
		bookImage = new JLabel("");
		bookImage.setBounds(0, 0, 100, 100);
		bookImagePanel.add(bookImage);
		
		JLabel cover_1 = new JLabel("BOOK COVER:");
		cover_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cover_1.setBounds(680, 45, 120, 30);
		manageBookPanel.add(cover_1);
		
		bookChoosePicture = new JLabel("choose picture....");
		bookChoosePicture.setForeground(new Color(51, 153, 204));
		bookChoosePicture.setBounds(790, 80, 80, 15);
		manageBookPanel.add(bookChoosePicture);
		
		JButton bookSelect = new JButton("SELECT");
		bookSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select Profile Picture");
				fileChooser.setCurrentDirectory(new File("C:\\Users\\Gian\\Pictures\\books"));
				
				FileNameExtensionFilter extensionFiter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");
				fileChooser.addChoosableFileFilter(extensionFiter);
				
				int fileState = fileChooser.showSaveDialog(null);
				
				if(fileState == JFileChooser.APPROVE_OPTION) {
					
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					bookChoosePicture.setText(path);
					imagePath = path;
					
					func.displayImage(100, 100, null, imagePath, bookImage);
					
					
				}
				
			}
		});
		bookSelect.setFont(new Font("Times New Roman", Font.BOLD, 10));
		bookSelect.setBounds(784, 155, 80, 25);
		manageBookPanel.add(bookSelect);
		
		bookFindField = new JTextField();
		bookFindField.setColumns(10);
		bookFindField.setBounds(120, 220, 100, 25);
		manageBookPanel.add(bookFindField);
		
		JLabel bookFind = new JLabel("FIND BOOK:");
		bookFind.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookFind.setBounds(10, 220, 110, 30);
		manageBookPanel.add(bookFind);
		
		JButton bookFindBtn = new JButton("FIND");
		bookFindBtn.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		bookFindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Book findID_Isbn;
				
				if(bookFindField.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "ENTER THE ID OR ISBN","MISSING FIELD", 2);
					
				}else {

					try {
						
						int id = 0;
						String isbn = bookFindField.getText();
						String accessionNum = bookFindField.getText();
						classes.Book findBook = null;
						
						try {
							
							id = Integer.parseInt(bookFindField.getText());
							findBook = book.searchBookByID_ISBN_ACCESSIONNUM(id, isbn, accessionNum);
							
						}catch(NumberFormatException ex) {
							
							findBook = book.searchBookByID_ISBN_ACCESSIONNUM(id, isbn, accessionNum);
						}
						
						bookIDField.setText(String.valueOf(findBook.getId()));
						isbnField.setText(findBook.getIsbn());
						bookAccessionNoField.setText(findBook.getAccessionNum());
						bookTitleField.setText(findBook.getTitle());
						AuthorID.setText(String.valueOf(findBook.getAuthorID()));
						chooseSubject.setSelectedItem(String.valueOf(findBook.getSubjectID()));
						PublisherID.setText(String.valueOf(findBook.getPublisherID()));
						quantitySpinner.setValue(findBook.getQuantity());
						chooseDate.setSelectedItem(findBook.getCopyright());
						bookPriceField.setText(String.valueOf(findBook.getPrice()));
						
						String fullname = (author.getAuthorById(findBook.getAuthorID())).getFirstName() + " " +
								  (author.getAuthorById(findBook.getAuthorID())).getLastName();
						bookAuthorField.setText(fullname);
				
						String name = (publisher.getPublisherById(findBook.getPublisherID())).getName();
						bookPublisherField.setText(name);
				
						for(Map.Entry<String, Integer> entry : subjectMap.entrySet()) {
					
							if(Objects.equals(findBook.getSubjectID(), entry.getValue())) {
						
								chooseSubject.setSelectedItem(entry.getKey());
						
							}
					
						}
						
						byte[] cover = findBook.getImage();
						
						func.displayImage(100, 100, cover, "", bookImage);
						bookChoosePicture.setText(String.valueOf(findBook.getImage()));
						
					}catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, "THIS BOOK DOES NOT EXISTS","BOOK NOT FOUND", 2);
						
					}
					
				}
				
			}
		});
		bookFindBtn.setFont(new Font("Times New Roman", Font.BOLD, 10));
		bookFindBtn.setBounds(220, 220, 100, 25);
		manageBookPanel.add(bookFindBtn);
		
		JButton addBook = new JButton("ADD");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String isbn = isbnField.getText();
								
				if(!error()) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 2);
					
				}else if(book.isISBNExist(isbn)){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 2);
					
				}
				else {
					
					String accessionNum = bookAccessionNoField.getText();
					String title = bookTitleField.getText();
					String author = bookAuthorField.getText();
					Integer authorID = Integer.parseInt(AuthorID.getText());
					Integer genreID = Integer.parseInt(SubjectID.getText());
					String publisher = bookPublisherField.getText();
					Integer publisherID = Integer.parseInt(PublisherID.getText());
					Integer quantity = Integer.parseInt(quantitySpinner.getValue().toString());
					String copyright = chooseDate.getSelectedItem().toString();;
					Double price =Double.parseDouble(bookPriceField.getText());
					byte[] cover = null;
					Path path = Paths.get(imagePath);
					
					try {
						
						cover = Files.readAllBytes(path);
						book.addBook(isbn, accessionNum, title, authorID, genreID, publisherID, quantity, copyright, price, cover);
						showTable_1();
						
						bookIDField.setText("");
						isbnField.setText("");
						bookAccessionNoField.setText("");
						bookTitleField.setText("");
						bookAuthorField.setText("");
						AuthorID.setText("ID");
						chooseSubject.setSelectedIndex(0);
						SubjectID.setText("ID");
						bookPublisherField.setText("");
						PublisherID.setText("ID");
						chooseDate.setSelectedIndex(0);
						bookPriceField.setText("");
						bookImage.setIcon(null);
						bookChoosePicture.setText("choose picture....");
						
					} catch (NumberFormatException ex) {

						JOptionPane.showMessageDialog(null, "ONE OR MORE FIELD ARE EMPTY","EMPTY FIELD", 2);
						
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
						JOptionPane.showMessageDialog(null, "SELECT A PROFILE PICTURE","NO PICTURE SELECTED", 2);
					}catch (NullPointerException ex) {
						// TODO Auto-generated catch block
						Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
						JOptionPane.showMessageDialog(null, "ONE OR MORE FIELD ARE EMPTY","EMPTY FIELD", 2);
					}
					
				}
				
			}
		});
		addBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addBook.setBounds(330, 220, 100, 30);
		manageBookPanel.add(addBook);
		
		JButton editBook = new JButton("EDIT");
		editBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!error()) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 2);
					
				}else {
										
					try {
						
						int id = Integer.valueOf(bookIDField.getText());
						String isbn = isbnField.getText();
						String accessionNum = bookAccessionNoField.getText();
						String title = bookTitleField.getText();
						String author = bookAuthorField.getText();
						Integer authorID = Integer.parseInt(AuthorID.getText());
						Integer genreID = Integer.parseInt(SubjectID.getText());
						String publisher = bookPublisherField.getText();
						Integer publisherID = Integer.parseInt(PublisherID.getText());
						Integer quantity = Integer.parseInt(quantitySpinner.getValue().toString());
						String copyright = chooseDate.getSelectedItem().toString();;
						Double price =Double.parseDouble(bookPriceField.getText());
						Path path = Paths.get(imagePath);
						
						try {
							
							byte[] cover = Files.readAllBytes(path);
							
							book.editBook(id, isbn, accessionNum, title, authorID, genreID, publisherID, quantity, copyright, price, cover);
							showTable_1();
							bookIDField.setText("");
							isbnField.setText("");
							bookAccessionNoField.setText("");
							bookTitleField.setText("");
							bookAuthorField.setText("");
							AuthorID.setText("ID");
							chooseSubject.setSelectedIndex(0);
							SubjectID.setText("ID");
							bookPublisherField.setText("");
							PublisherID.setText("ID");
							chooseDate.setSelectedIndex(0);
							bookPriceField.setText("");
							bookImage.setIcon(null);
							bookChoosePicture.setText("choose picture....");
							
						}catch(IOException ex) {
							
							book.editBook(id, isbn, accessionNum, title, authorID, genreID, publisherID, quantity, copyright, price, null);
							showTable_1();
							bookIDField.setText("");
							isbnField.setText("");
							bookAccessionNoField.setText("");
							bookTitleField.setText("");
							bookAuthorField.setText("");
							AuthorID.setText("ID");
							chooseSubject.setSelectedIndex(0);
							SubjectID.setText("ID");
							bookPublisherField.setText("");
							PublisherID.setText("ID");
							chooseDate.setSelectedIndex(0);
							bookPriceField.setText("");
							bookImage.setIcon(null);
							bookChoosePicture.setText("choose picture....");
														
						}
						
					} catch (NumberFormatException ex) {

						JOptionPane.showMessageDialog(null, "INVALID KEY IN NUMBER FIELD","INVALID KEY", 2);
						
					}catch (NullPointerException ex) {

						JOptionPane.showMessageDialog(null, "ONE OR MORE FIELD ARE EMPTY","EMPTY FIELD", 2);
					}
					
				}
				
			}
				
		});
		editBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		editBook.setBounds(440, 220, 100, 30);
		manageBookPanel.add(editBook);
		
		JButton clearBook = new JButton("CLEAR");
		clearBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bookIDField.setText("");
				isbnField.setText("");
				bookAccessionNoField.setText("");
				bookTitleField.setText("");
				bookAuthorField.setText("");
				AuthorID.setText("ID");
				chooseSubject.setSelectedIndex(0);
				SubjectID.setText("ID");
				bookPublisherField.setText("");
				PublisherID.setText("ID");
				chooseDate.setSelectedIndex(0);
				bookPriceField.setText("");
				bookImage.setIcon(null);
				bookChoosePicture.setText("choose picture....");
				
			}
		});
		clearBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		clearBook.setBounds(550, 220, 100, 30);
		manageBookPanel.add(clearBook);
		
		JButton deleteBook = new JButton("DELETE");
		deleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(bookIDField.getText());
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						book.deleteBook(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "MEMBER NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_1();
					
					bookIDField.setText("");
					isbnField.setText("");
					bookAccessionNoField.setText("");
					bookTitleField.setText("");
					bookAuthorField.setText("");
					AuthorID.setText("ID");
					chooseSubject.setSelectedIndex(0);
					SubjectID.setText("ID");
					bookPublisherField.setText("");
					PublisherID.setText("ID");
					chooseDate.setSelectedIndex(0);
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 3);
					
				}
				
			}
		});
		deleteBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteBook.setBounds(660, 220, 100, 30);
		manageBookPanel.add(deleteBook);
		
		JScrollPane bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(10, 260, 855, 200);
		manageBookPanel.add(bookScrollPane);
		
		bookTable = new JTable();
		bookTable.setGridColor(Color.ORANGE);
		bookTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		bookTable.setBackground(new Color(255, 255, 255));
		bookTable.setSelectionBackground(new Color(255, 255, 0));
		bookTable.setSelectionForeground(Color.black);
		bookTable.getTableHeader().setBackground(Color.blue);
		bookTable.getTableHeader().setForeground(Color.black);
		bookTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		bookTable.setFillsViewportHeight(true);
		bookScrollPane.setViewportView(bookTable);
		
		JButton bookExport = new JButton("EXPORT");
		bookExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < bookTable.getRowCount(); i++) {
							for(int j = 0; j < bookTable.getRowCount(); j++) {
								
								bw.write(bookTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		bookExport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookExport.setBounds(770, 220, 100, 30);
		manageBookPanel.add(bookExport);
		
		/*
		 * Author Frame
		 */
		
		JPanel authorPanel = new JPanel();
		authorPanel.setForeground(Color.WHITE);
		authorPanel.setBackground(new Color(255, 255, 204));
		bookTabbedPane.addTab("Author", new ImageIcon(Home.class.getResource("/Images/icons8-writer-male-16.png")), authorPanel, null);
		bookTabbedPane.setForegroundAt(1, new Color(255, 255, 255));
		bookTabbedPane.setBackgroundAt(1, new Color(0, 0, 255));
		authorPanel.setLayout(null);
		
		JLabel authorID = new JLabel("ID:");
		authorID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		authorID.setBounds(10, 10, 60, 30);
		authorPanel.add(authorID);
		
		authorIDField = new JTextField();
		authorIDField.setColumns(10);
		authorIDField.setBounds(130, 10, 100, 30);
		authorPanel.add(authorIDField);
		
		JButton authorFind = new JButton("FIND");
		authorFind.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		authorFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Author authorFind;
				
				try {
					
					Integer id = Integer.parseInt(authorIDField.getText());
					
					authorFind = author.getAuthorById(id);
					
					if(authorFind != null) {
						
						authorIDField.setText(String.valueOf(authorFind.getId()));
						authorFirstNameField.setText(String.valueOf(authorFind.getFirstName()));
						authorLastNameField.setText(String.valueOf(authorFind.getLastName()));
						expertiseField.setText(String.valueOf(authorFind.getExpertise()));
						
					}else {
						
						JOptionPane.showMessageDialog(null, "AUTHOR NOT FOUND","INVALID ID", 2);
						
					}
										
				}catch(SQLException | NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID AUTHOR ID","ERROR", 3);
					
				}
				
			}
		});
		authorFind.setFont(new Font("Times New Roman", Font.BOLD, 10));
		authorFind.setBounds(250, 10, 100, 30);
		authorPanel.add(authorFind);
		
		JLabel authorFirstName = new JLabel("FIRST NAME:");
		authorFirstName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		authorFirstName.setBounds(10, 60, 150, 30);
		authorPanel.add(authorFirstName);
		
		authorFirstNameField = new JTextField();
		authorFirstNameField.setColumns(10);
		authorFirstNameField.setBounds(130, 60, 255, 30);
		authorPanel.add(authorFirstNameField);
					
		JLabel authorLastName = new JLabel("LAST NAME:");
		authorLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		authorLastName.setBounds(10, 110, 150, 30);
		authorPanel.add(authorLastName);
		
		authorLastNameField = new JTextField();
		authorLastNameField.setColumns(10);
		authorLastNameField.setBounds(130, 110, 255, 30);
		authorPanel.add(authorLastNameField);
		
		JLabel expertise = new JLabel("EXPERTISE:");
		expertise.setFont(new Font("Times New Roman", Font.BOLD, 16));
		expertise.setBounds(10, 160, 150, 30);
		authorPanel.add(expertise);		
		
		expertiseField = new JTextField();
		expertiseField.setColumns(10);
		expertiseField.setBounds(130, 160, 255, 30);
		authorPanel.add(expertiseField);
		
		JButton addAuthor = new JButton("ADD");
		addAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = authorFirstNameField.getText();
				String lname = authorLastNameField.getText();
				String expert = expertiseField.getText();
				
				if(fname.isEmpty() || lname.isEmpty() || expert.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					author.addAuthor(fname, lname, expert);
					showTable_2();
					
					authorIDField.setText("");
					authorFirstNameField.setText("");
					authorLastNameField.setText("");
					expertiseField.setText("");
					
				}
				
			}
		});
		addAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addAuthor.setBounds(50, 350, 100, 30);
		authorPanel.add(addAuthor);
		
		JButton editAuthor = new JButton("EDIT");
		editAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = authorFirstNameField.getText();
				String lname = authorLastNameField.getText();
				String expert = expertiseField.getText();
				
				if(fname.isEmpty() || lname.isEmpty() || expert.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					try {
						
						int id = Integer.valueOf(authorIDField.getText());
						author.editAuthor(id, fname, lname, expert);
						showTable_2();
						
						authorIDField.setText("");
						authorFirstNameField.setText("");
						authorLastNameField.setText("");
						expertiseField.setText("");
						
					}catch(NumberFormatException ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 4);
						
					}
					
				}
				
			}
		});
		editAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		editAuthor.setBounds(250, 350, 100, 30);
		authorPanel.add(editAuthor);
		
		JButton clearAuthor = new JButton("CLEAR");
		clearAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				authorIDField.setText("");
				authorFirstNameField.setText("");
				authorLastNameField.setText("");
				expertiseField.setText("");
				
			}
		});
		clearAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		clearAuthor.setBounds(50, 400, 100, 30);
		authorPanel.add(clearAuthor);
						
		JButton deleteAuthor = new JButton("DELETE");
		deleteAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(authorIDField.getText());
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						author.deleteAuthor(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "MEMBER NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_2();
					
					authorIDField.setText("");
					authorFirstNameField.setText("");
					authorLastNameField.setText("");
					expertiseField.setText("");
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 3);
					
				}

			}
		});
		deleteAuthor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteAuthor.setBounds(250, 400, 100, 30);
		authorPanel.add(deleteAuthor);
		
		JScrollPane authorScrollPane = new JScrollPane();
		authorScrollPane.setBounds(410, 50, 450, 400);
		authorPanel.add(authorScrollPane);
		
		authorTable = new JTable();
		authorTable.setGridColor(Color.ORANGE);
		authorTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		authorTable.setBackground(new Color(255, 255, 255));
		authorTable.setSelectionBackground(new Color(255, 255, 0));
		authorTable.setSelectionForeground(Color.black);
		authorTable.getTableHeader().setBackground(Color.blue);
		authorTable.getTableHeader().setForeground(Color.black);
		authorTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		authorTable.setFillsViewportHeight(true);
		authorTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = authorTable.getSelectedRow();
				
				String id = authorTable.getValueAt(index, 0).toString();
				String fname = authorTable.getValueAt(index, 1).toString();
				String lname = authorTable.getValueAt(index, 2).toString();
				String expert = authorTable.getValueAt(index, 3).toString();
				
				authorIDField.setText(id);
				authorFirstNameField.setText(fname);
				authorLastNameField.setText(lname);
				expertiseField.setText(expert);
				
			}
		});
		authorScrollPane.setViewportView(authorTable);
		
		JButton authorExport = new JButton("EXPORT");
		authorExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < authorTable.getRowCount(); i++) {
							for(int j = 0; j < authorTable.getRowCount(); j++) {
								
								bw.write(authorTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		authorExport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		authorExport.setBounds(760, 10, 100, 30);
		authorPanel.add(authorExport);
		
		/**
		 * Subject Page Frame
		 */
				
		JPanel subjectPanel = new JPanel();
		subjectPanel.setForeground(Color.WHITE);
		subjectPanel.setBackground(new Color(255, 255, 204));
		bookTabbedPane.addTab("Subject", new ImageIcon(Home.class.getResource("/Images/icons8-elective-16.png")), subjectPanel, null);
		bookTabbedPane.setForegroundAt(2, new Color(255, 255, 255));
		bookTabbedPane.setBackgroundAt(2, new Color(0, 0, 255));
		subjectPanel.setLayout(null);
		
		JLabel subjectID = new JLabel("ID:");
		subjectID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		subjectID.setBounds(10, 20, 25, 25);
		subjectPanel.add(subjectID);
		
		subjectIDField = new JTextField();
		subjectIDField.setColumns(10);
		subjectIDField.setBounds(80, 20, 200, 30);
		subjectPanel.add(subjectIDField);
		
		JButton subjectFind = new JButton("FIND");
		subjectFind.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		subjectFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Subject subjectFind;
				
				try {
					
					Integer id = Integer.parseInt(subjectIDField.getText());
					
					subjectFind = subject.getSubjectById(id);
					
					if(subjectFind != null) {
						
						subjectIDField.setText(String.valueOf(subjectFind.getId()));
						subjectNameField.setText(String.valueOf(subjectFind.getName()));
						
					}else {
						
						JOptionPane.showMessageDialog(null, "SUBJECT NOT FOUND","INVALID ID", 2);
						
					}
										
				}catch(SQLException | NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID SUBJECT ID","ERROR", 3);
					
				}
				
			}
		});
		subjectFind.setFont(new Font("Times New Roman", Font.BOLD, 10));
		subjectFind.setBounds(290, 20, 100, 30);
		subjectPanel.add(subjectFind);
		
		JLabel subjectName = new JLabel("NAME:");
		subjectName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		subjectName.setBounds(10, 90, 60, 30);
		subjectPanel.add(subjectName);
						
		subjectNameField = new JTextField();
		subjectNameField.setColumns(10);
		subjectNameField.setBounds(80, 90, 200, 30);
		subjectPanel.add(subjectNameField);
		
		JButton btnAdd_3 = new JButton("ADD");
		btnAdd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer id = Integer.parseInt(subjectIDField.getText());
				String name = subjectNameField.getText();
				
				if(name.isEmpty() || id.equals("")) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					subject.addSubject(name);
					showTable_3();
					subjectMap.put(name, id);
					fillJcomboboxWithSubject();
					
					subjectIDField.setText("");
					subjectNameField.setText("");
					
				}
				
			}
		});
		btnAdd_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAdd_3.setBounds(50, 350, 100, 30);
		subjectPanel.add(btnAdd_3);
		
		JButton btnEdit_3 = new JButton("EDIT");
		btnEdit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = subjectNameField.getText();
				
				if(name.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					try {
						
						int id = Integer.valueOf(subjectIDField.getText());
						subject.editSubject(id, name);
						showTable_3();
						subjectMap.put(name, id);
						fillJcomboboxWithSubject();
						
						subjectIDField.setText("");
						subjectNameField.setText("");
						
					}catch(NumberFormatException ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 3);
						
					}
					
				}
				
			}
		});
		btnEdit_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnEdit_3.setBounds(250, 350, 100, 30);
		subjectPanel.add(btnEdit_3);
		
		JButton btnClear_3 = new JButton("CLEAR");
		btnClear_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				subjectIDField.setText("");
				subjectNameField.setText("");
				
			}
		});
		btnClear_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnClear_3.setBounds(50, 400, 100, 30);
		subjectPanel.add(btnClear_3);
						
		JButton btnDelete_3 = new JButton("DELETE");
		btnDelete_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(subjectIDField.getText());
					String name = subjectNameField.getText();
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						subject.deleteSubject(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "MEMBER NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_3();
					subjectMap.remove(name, id);
					fillJcomboboxWithSubject();
					
					subjectIDField.setText("");
					subjectNameField.setText("");
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 3);
					
				}
				
			}
		});
		btnDelete_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete_3.setBounds(250, 400, 100, 30);
		subjectPanel.add(btnDelete_3);
		
		JScrollPane subjectScrollPane = new JScrollPane();
		subjectScrollPane.setBounds(410, 50, 450, 400);
		subjectPanel.add(subjectScrollPane);
		
		subjectTable = new JTable();
		subjectTable.setGridColor(Color.ORANGE);
		subjectTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		subjectTable.setBackground(new Color(255, 255, 255));
		subjectTable.setSelectionBackground(new Color(255, 255, 0));
		subjectTable.setSelectionForeground(Color.black);
		subjectTable.getTableHeader().setBackground(Color.blue);
		subjectTable.getTableHeader().setForeground(Color.black);
		subjectTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		subjectTable.setFillsViewportHeight(true);
		subjectTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = subjectTable.getSelectedRow();
				
				String id = subjectTable.getValueAt(index, 0).toString();
				String fname = subjectTable.getValueAt(index, 1).toString();
				
				subjectIDField.setText(id);
				subjectNameField.setText(fname);
				
			}
		});
		subjectScrollPane.setViewportView(subjectTable);
		
		JButton bookExport_2 = new JButton("EXPORT");
		bookExport_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < subjectTable.getRowCount(); i++) {
							for(int j = 0; j < subjectTable.getRowCount(); j++) {
								
								bw.write(subjectTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		bookExport_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookExport_2.setBounds(760, 10, 100, 30);
		subjectPanel.add(bookExport_2);
		
		/**
		 * Publisher Page Frame
		 */
		
		JPanel publisherPanel = new JPanel();
		publisherPanel.setForeground(Color.WHITE);
		publisherPanel.setBackground(new Color(255, 255, 204));
		bookTabbedPane.addTab("Publisher", new ImageIcon(Home.class.getResource("/Images/icons8-homework-16.png")), publisherPanel, null);
		bookTabbedPane.setForegroundAt(3, new Color(255, 255, 255));
		bookTabbedPane.setBackgroundAt(3, new Color(0, 0, 255));
		publisherPanel.setLayout(null);
		
		JLabel publisherID = new JLabel("ID:");
		publisherID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		publisherID.setBounds(10, 10, 150, 30);
		publisherPanel.add(publisherID);
		
		publisherIDField = new JTextField();
		publisherIDField.setColumns(10);
		publisherIDField.setBounds(150, 10, 130, 30);
		publisherPanel.add(publisherIDField);
		
		JButton publisherFind = new JButton("FIND");
		publisherFind.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		publisherFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Publisher publisherFind;
				
				try {
					
					Integer id = Integer.parseInt(publisherIDField.getText());
					
					publisherFind = publisher.getPublisherById(id);
					
					if(publisherFind != null) {
						
						publisherIDField.setText(String.valueOf(publisherFind.getId()));
						publisherNameField.setText(String.valueOf(publisherFind.getName()));
						publisherCityField.setText(String.valueOf(publisherFind.getCity()));
						publisherStreetField.setText(String.valueOf(publisherFind.getStreet()));
						publisherZipCodeField.setText(String.valueOf(publisherFind.getZipcode()));
						
					}else {
						
						JOptionPane.showMessageDialog(null, "PUBLISHER NOT FOUND","INVALID ID", 2);
						
					}
										
				}catch(SQLException | NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID PUBLISHER ID","ERROR", 3);
					
				}
				
			}
		});
		publisherFind.setFont(new Font("Times New Roman", Font.BOLD, 10));
		publisherFind.setBounds(290, 10, 100, 30);
		publisherPanel.add(publisherFind);
		
		JLabel publisherName = new JLabel("NAME:");
		publisherName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		publisherName.setBounds(10, 70, 150, 30);
		publisherPanel.add(publisherName);
		
		publisherNameField = new JTextField();
		publisherNameField.setColumns(10);
		publisherNameField.setBounds(150, 70, 200, 30);
		publisherPanel.add(publisherNameField);
		
		JLabel publisherCity = new JLabel("CITY:");
		publisherCity.setFont(new Font("Times New Roman", Font.BOLD, 16));
		publisherCity.setBounds(10, 130, 150, 30);
		publisherPanel.add(publisherCity);
		
		publisherCityField = new JTextField();
		publisherCityField.setColumns(10);
		publisherCityField.setBounds(150, 130, 200, 30);
		publisherPanel.add(publisherCityField);
		
		JLabel publisherStreet = new JLabel("STREET NAME:");
		publisherStreet.setFont(new Font("Times New Roman", Font.BOLD, 16));
		publisherStreet.setBounds(10, 190, 150, 30);
		publisherPanel.add(publisherStreet);
		
		publisherStreetField = new JTextField();
		publisherStreetField.setColumns(10);
		publisherStreetField.setBounds(150, 190, 200, 30);
		publisherPanel.add(publisherStreetField);
		
		JLabel publisherZipCode = new JLabel("ZIP CODE:");
		publisherZipCode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		publisherZipCode.setBounds(10, 250, 150, 30);
		publisherPanel.add(publisherZipCode);	
				
		publisherZipCodeField = new JTextField();
		publisherZipCodeField.setColumns(10);
		publisherZipCodeField.setBounds(150, 250, 200, 30);
		publisherPanel.add(publisherZipCodeField);			
				
		JButton btnAdd_4 = new JButton("ADD");
		btnAdd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = publisherNameField.getText();
				String city = publisherCityField.getText();
				String street = publisherStreetField.getText();
				String zipcode = publisherZipCodeField.getText();
				
				if(name.isEmpty() || city.isEmpty() || street.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					publisher.addPublisher(name, city, street, zipcode);
					showTable_4();
					
					publisherIDField.setText("");
					publisherNameField.setText("");
					publisherCityField.setText("");
					publisherStreetField.setText("");
					publisherZipCodeField.setText("");
					
				}
				
			}
		});
		btnAdd_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAdd_4.setBounds(50, 350, 100, 30);
		publisherPanel.add(btnAdd_4);
		
		JButton btnEdit_4 = new JButton("EDIT");
		btnEdit_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = publisherNameField.getText();
				String city = publisherCityField.getText();
				String street = publisherStreetField.getText();
				String zipcode = publisherZipCodeField.getText();
				
				if(name.isEmpty() || city.isEmpty() || street.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					int id = Integer.valueOf(publisherIDField.getText());
					publisher.editPublisher(id, name, city, street, zipcode);
					showTable_4();
					
					publisherIDField.setText("");
					publisherNameField.setText("");
					publisherCityField.setText("");
					publisherStreetField.setText("");
					publisherZipCodeField.setText("");
					
				}
				
			}
		});
		btnEdit_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnEdit_4.setBounds(250, 350, 100, 30);
		publisherPanel.add(btnEdit_4);
		
		JButton btnClear_4 = new JButton("CLEAR");
		btnClear_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				publisherIDField.setText("");
				publisherNameField.setText("");
				publisherCityField.setText("");
				publisherStreetField.setText("");
				publisherZipCodeField.setText("");
				
			}
		});
		btnClear_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnClear_4.setBounds(50, 400, 100, 30);
		publisherPanel.add(btnClear_4);
		
		JButton btnDelete_4 = new JButton("DELETE");
		btnDelete_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(publisherIDField.getText());
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						publisher.deletePublisher(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "MEMBER NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_4();
					
					publisherIDField.setText("");
					publisherNameField.setText("");
					publisherCityField.setText("");
					publisherStreetField.setText("");
					publisherZipCodeField.setText("");
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID GENRE ID","ERROR", 3);
					
				}
				
			}
		});
		btnDelete_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete_4.setBounds(250, 400, 100, 30);
		publisherPanel.add(btnDelete_4);
		
		JScrollPane publisherScrollPane = new JScrollPane();
		publisherScrollPane.setBounds(410, 50, 450, 400);
		publisherPanel.add(publisherScrollPane);
		
		publisherTable = new JTable();
		publisherTable.setGridColor(Color.ORANGE);
		publisherTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		publisherTable.setBackground(new Color(255, 255, 255));
		publisherTable.setSelectionBackground(new Color(255, 255, 0));
		publisherTable.setSelectionForeground(Color.black);
		publisherTable.getTableHeader().setBackground(Color.blue);
		publisherTable.getTableHeader().setForeground(Color.black);
		publisherTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		publisherTable.setFillsViewportHeight(true);
		publisherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = publisherTable.getSelectedRow();
				
				String id = publisherTable.getValueAt(index, 0).toString();
				String name = publisherTable.getValueAt(index, 1).toString();
				String city = publisherTable.getValueAt(index, 2).toString();
				String street = publisherTable.getValueAt(index, 3).toString();
				String zipcode = publisherTable.getValueAt(index, 4).toString();
				
				publisherIDField.setText(id);
				publisherNameField.setText(name);
				publisherCityField.setText(city);
				publisherStreetField.setText(street);
				publisherZipCodeField.setText(zipcode);
				
			}
		});
		publisherScrollPane.setViewportView(publisherTable);
		
		JButton bookExport_3 = new JButton("EXPORT");
		bookExport_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < publisherTable.getRowCount(); i++) {
							for(int j = 0; j < publisherTable.getRowCount(); j++) {
								
								bw.write(publisherTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		bookExport_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookExport_3.setBounds(760, 10, 100, 30);
		publisherPanel.add(bookExport_3);
		
		/**
		 * Circulation Desk Panel
		 */
		
		JPanel circulationPanel = new JPanel();
		circulationPanel.setForeground(new Color(255, 255, 255));
		circulationPanel.setBackground(new Color(255, 255, 204));
		homeTabbedPane.addTab("Circulation Desk", new ImageIcon(Home.class.getResource("/Images/icons8-teacher-desk-16.png")), circulationPanel, null);
		circulationPanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 879, 491);
		circulationPanel.add(tabbedPane);
		
		/**
		 * Check Out Frame
		 */
		
		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setBackground(new Color(255, 255, 204));
		tabbedPane.addTab("Check Out", new ImageIcon(Home.class.getResource("/Images/icons8-check-out-16.png")), checkOutPanel, null);
		tabbedPane.setForegroundAt(0, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(0, new Color(0, 0, 255));
		checkOutPanel.setLayout(null);
		
		JLabel checkOutUser = new JLabel("USE USER ID.");
		checkOutUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutUser.setBounds(20, 30, 150, 30);
		checkOutPanel.add(checkOutUser);
		
		JButton checkOutUserFind_1 = new JButton("FIND");
		checkOutUserFind_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkOutUserField.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
					
				}else {

					try {
						
						int id = Integer.parseInt(checkOutUserField.getText());
						classes.User checkOutUserFind = user.getUserById(id);
						
						//checkOutUserFind = user.getUserById(id);
							
						String fullname = (user.getUserById(checkOutUserFind.getId())).getFirstName() + " " +
								  (user.getUserById(checkOutUserFind.getId())).getLastName();
							
						if(checkOutUserFind != null) {
							
							CheckOutUserID.setText(String.valueOf(checkOutUserFind.getId()));
							checkOutUserField.setText(String.valueOf(checkOutUserFind.getId()));
							checkOutUserName.setText(fullname);
							checkOutPayment.setText(String.valueOf(checkOutUserFind.getPayment()));
							
						}else{
						
							JOptionPane.showMessageDialog(null, "THIS USER DOES NOT EXIST","USER NOT FOUND", 2);
							
						}
						
					}catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID LIBRARY NUMBER","ERROR", 3);
						
					}
					
				}
				
			}
		});
		checkOutUserFind_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		checkOutUserFind_1.setHorizontalAlignment(SwingConstants.LEFT);
		checkOutUserFind_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		checkOutUserFind_1.setBounds(230, 70, 80, 30);
		checkOutPanel.add(checkOutUserFind_1);
		
		checkOutUserField = new JTextField();
		checkOutUserField.setColumns(10);
		checkOutUserField.setBounds(20, 70, 200, 30);
		checkOutPanel.add(checkOutUserField);
		
		checkOutUserName = new JLabel("");
		checkOutUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutUserName.setBounds(80, 150, 150, 20);
		checkOutPanel.add(checkOutUserName);
		
		checkOutPayment = new JLabel("");
		checkOutPayment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutPayment.setBounds(120, 180, 150, 20);
		checkOutPanel.add(checkOutPayment);
		
		JLabel checkOutBook = new JLabel("IDENTIFY BOOK TO CHECK OUT:");
		checkOutBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBook.setBounds(20, 240, 300, 30);
		checkOutPanel.add(checkOutBook);
		
		checkOutBookField = new JTextField();
		checkOutBookField.setColumns(10);
		checkOutBookField.setBounds(20, 280, 250, 30);
		checkOutPanel.add(checkOutBookField);
		
		JButton checkOutBookFind_1 = new JButton("FIND");
		checkOutBookFind_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkOutBookField.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "FIll UP THE EMPTY FIELD","EMPTY FIELD", 2);
					
				}else {

					try {
						int id = Integer.parseInt(checkOutBookField.getText());
						classes.Book checkOutBookFind = book.getBookId(id);
						String isbn = checkOutBookField.getText();
						String accessionNum = checkOutBookField.getText();
						String title = checkOutBookField.getText();

						try {
							
							checkOutBookFind = book.searchBookByID_ISBN_ACCESSIONNUM(id, isbn, accessionNum);
							
						}catch(NumberFormatException ex) {
							
							checkOutBookFind = book.searchBookByID_ISBN_ACCESSIONNUM(id, isbn, accessionNum);
						}
						
						if(checkOutBookFind != null) {
							
							checkOutBookID.setText(String.valueOf(checkOutBookFind.getId()));
							checkOutBookTitleData.setText(String.valueOf(checkOutBookFind.getTitle()));
							byte[] cover = checkOutBookFind.getImage();
							
							func.displayImage(100, 100, cover, "", checkOutBookCover);
							
							if(issue.checkBookAvailability(id)) {
								
								availableYesNo.setText("YES");
								
							}else {
								
								availableYesNo.setText("NO");
								
							}
							
						}else{
						
							JOptionPane.showMessageDialog(null, "THIS BOOK DOES NOT EXIST","BOOK NOT FOUND", 2);
							
						}
						
					}catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID LIBRARY NUMBER","ERROR", 2);
						
					}
					
				}
				
			}
		});
		checkOutBookFind_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		checkOutBookFind_1.setHorizontalAlignment(SwingConstants.LEFT);
		checkOutBookFind_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		checkOutBookFind_1.setBounds(270, 280, 80, 30);
		checkOutPanel.add(checkOutBookFind_1);
		
		JLabel lblNewLabel_1 = new JLabel("ISSUE DATE:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(630, 200, 100, 25);
		checkOutPanel.add(lblNewLabel_1);
		
		JDateChooser checkOutDateIssue = new JDateChooser();
		checkOutDateIssue.setBounds(730, 200, 100, 30);
		checkOutPanel.add(checkOutDateIssue);
		
		JLabel lblNewLabel_1_1 = new JLabel("DUE DATE:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(659, 240, 71, 25);
		checkOutPanel.add(lblNewLabel_1_1);
		
		JDateChooser checkOutDateReturn = new JDateChooser();
		checkOutDateReturn.setBounds(730, 240, 100, 30);
		checkOutPanel.add(checkOutDateReturn);
		
		JButton checkOutSearchBtn = new JButton("BOOK LIST");
		checkOutSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BookList list = new BookList();
				list.formType = "edit";
				list.setVisible(true);
				
				
			}
		});
		checkOutSearchBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutSearchBtn.setBounds(680, 339, 150, 50);
		checkOutPanel.add(checkOutSearchBtn);
		
		JButton checkOutSubjectList = new JButton("REFRESH");
		checkOutSubjectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				countBook();
				checkOutUserField.setText("");
				checkOutBookField.setText("");
				checkOutPayment.setText("");
				CheckOutUserID.setText("ID");
				checkOutBookID.setText("ID");
				checkOutUserName.setText("");
				checkOutBookTitleData.setText("");
				checkOutBookCover.setIcon(null);
				checkOutDateIssue.setDate(new Date());
				checkOutDateReturn.setDate(new Date());
				availableYesNo.setText("YES OR NO");
				
			}
		});
		checkOutSubjectList.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutSubjectList.setBounds(680, 400, 150, 50);
		checkOutPanel.add(checkOutSubjectList);
		
		JButton checkOutBtn = new JButton("CHECK OUT");
		checkOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(!userExist()) {
					
					JOptionPane.showMessageDialog(null, "YOU NEED TO CHECK IF THE USER IS EXIST BY FINDING USER LIBRARY No.","USER NOT FOUND", 2);
					
				}else if(!bookExist()) {
					
					JOptionPane.showMessageDialog(null, "YOU NEED TO CHECK IF THE BOOK IS EXIST BY FINDING BOOK ID, ISBN OR ACCESSION NO.","BOOK NOT FOUND", 2);
					
				}else {
					
					try {
						
						int id;
						Integer userID = Integer.parseInt(CheckOutUserID.getText());
						Integer bookID = Integer.parseInt(checkOutBookID.getText());
						
						Double loan = Double.parseDouble(checkOutPayment.getText());
						
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String issueDate = dateFormat.format(checkOutDateIssue.getDate());
						String returnDate = dateFormat.format(checkOutDateReturn.getDate());
						
						Date issDate = dateFormat.parse(issueDate);
						Date rtnDate = dateFormat.parse(returnDate);
						
						String status = availableYesNo.getText();
						
						Double cost = new Double(0);
						
						if(rtnDate.before(issDate)) {
							
							JOptionPane.showMessageDialog(null, "RETURN DATE MUST BE AFTER THE ISSUE DATE","WRONG RETURN DATE", 2);
							
						}else if(status.equals("NO")) {
							
							JOptionPane.showMessageDialog(null, "BOOK IS NOT AVAILABLE","MESSAGE", 2);
							
						}else if(loan.equals(cost)) {
							
							int quantity = new classes.Book().getBookId(bookID).getQuantity();
							book.setQuantity_Minus_One(bookID, quantity-1);
							
							issue.addIssue(userID, bookID, issueDate, returnDate, "issued");
							countBook();
							checkOutUserField.setText("");
							checkOutBookField.setText("");
							checkOutPayment.setText("");
							CheckOutUserID.setText("ID");
							checkOutBookID.setText("ID");
							checkOutUserName.setText("");
							checkOutBookTitleData.setText("");
							checkOutBookCover.setIcon(null);
							checkOutDateIssue.setDate(new Date());
							checkOutDateReturn.setDate(new Date());
							availableYesNo.setText("YES OR NO");
							showTable_1();
							showTable_6("");
							showTable_7();
							
						}else {
							
							JOptionPane.showMessageDialog(null, "USER IS NOT ALLOWED TO BORROW BOOKS","PENALTY", 2);
							
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
		checkOutBtn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBtn.setBounds(680, 277, 150, 50);
		checkOutPanel.add(checkOutBtn);
		
		JPanel bookAvailablePanel = new JPanel();
		bookAvailablePanel.setBackground(new Color(255, 255, 0));
		bookAvailablePanel.setBounds(700, 70, 100, 100);
		checkOutPanel.add(bookAvailablePanel);
		bookAvailablePanel.setLayout(null);
		
		checkOutBookNum = new JLabel("000");
		checkOutBookNum.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkOutBookNum.setHorizontalAlignment(SwingConstants.CENTER);
		checkOutBookNum.setBounds(0, 25, 100, 50);
		bookAvailablePanel.add(checkOutBookNum);
		
		JLabel checkOutBookAvailable = new JLabel("BOOK AVAILABLE:");
		checkOutBookAvailable.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkOutBookAvailable.setBounds(680, 20, 150, 30);
		checkOutPanel.add(checkOutBookAvailable);
		
		JPanel bookCoverPanel = new JPanel();
		bookCoverPanel.setLayout(null);
		bookCoverPanel.setBackground(Color.YELLOW);
		bookCoverPanel.setBounds(20, 340, 100, 100);
		checkOutPanel.add(bookCoverPanel);
		
		checkOutBookCover = new JLabel("");
		checkOutBookCover.setHorizontalAlignment(SwingConstants.CENTER);
		checkOutBookCover.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkOutBookCover.setBounds(0, 0, 100, 100);
		bookCoverPanel.add(checkOutBookCover);
		
		checkOutBookTitleData = new JTextPane();
		checkOutBookTitleData.setBackground(new Color(255, 255, 204));
		checkOutBookTitleData.setBounds(190, 370, 150, 20);
		checkOutPanel.add(checkOutBookTitleData);
		
		CheckOutUserID = new JLabel("ID");
		CheckOutUserID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CheckOutUserID.setBounds(20, 120, 30, 20);
		checkOutPanel.add(CheckOutUserID);
		
		checkOutBookID = new JLabel("ID");
		checkOutBookID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkOutBookID.setBounds(130, 340, 30, 20);
		checkOutPanel.add(checkOutBookID);
		
		JLabel checkOutBookTitle = new JLabel("TITLE:");
		checkOutBookTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkOutBookTitle.setBounds(130, 370, 60, 20);
		checkOutPanel.add(checkOutBookTitle);
		
		JLabel CheckOutUserName = new JLabel("NAME:");
		CheckOutUserName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CheckOutUserName.setBounds(20, 150, 60, 20);
		checkOutPanel.add(CheckOutUserName);
		
		JLabel CheckOutUserLoanPayment = new JLabel("Loans To Pay:");
		CheckOutUserLoanPayment.setFont(new Font("Times New Roman", Font.BOLD, 15));
		CheckOutUserLoanPayment.setBounds(20, 180, 100, 20);
		checkOutPanel.add(CheckOutUserLoanPayment);
		
		JLabel bookAvailable = new JLabel("AVAILABLE:");
		bookAvailable.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bookAvailable.setBounds(130, 430, 100, 20);
		checkOutPanel.add(bookAvailable);
		
		availableYesNo = new JLabel("YES OR NO");
		availableYesNo.setForeground(Color.BLUE);
		availableYesNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		availableYesNo.setBounds(230, 430, 100, 20);
		checkOutPanel.add(availableYesNo);
		
		JLabel checkOutBookPrice = new JLabel("PRICE:");
		checkOutBookPrice.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkOutBookPrice.setBounds(130, 400, 60, 20);
		checkOutPanel.add(checkOutBookPrice);
		
		JTextPane checkOutBookPriceData = new JTextPane();
		checkOutBookPriceData.setBackground(new Color(255, 255, 204));
		checkOutBookPriceData.setBounds(190, 400, 150, 20);
		checkOutPanel.add(checkOutBookPriceData);
		
		/**
		 * Check In Frame
		 */
		
		JPanel checkInPanel = new JPanel();
		tabbedPane.addTab("Check In", new ImageIcon(Home.class.getResource("/Images/icons8-check-in-16.png")), checkInPanel, null);
		tabbedPane.setForegroundAt(1, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(1, new Color(0, 0, 255));
		checkInPanel.setLayout(null);
		
		JPanel checkInFrame = new JPanel();
		checkInFrame.setLayout(null);
		checkInFrame.setBackground(new Color(255, 255, 204));
		checkInFrame.setBounds(0, 0, 874, 463);
		checkInPanel.add(checkInFrame);
		
		JLabel checkInBook = new JLabel("IDENTIFY BOOK TO CHECK IN:");
		checkInBook.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInBook.setBounds(20, 210, 300, 30);
		checkInFrame.add(checkInBook);
		
		JButton checkInRefresh = new JButton("REFRESH");
		checkInRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkInIssuedIDData.setText("ID");
				checkInUserID.setText("ID");
				checkInBookID.setText("ID");
				checkInUserNameData.setText("");
				checkInBookTitleData.setText("");
				checkInPaymentData.setText("");
				checkInDateIssue.setDate(new Date());
				checkInDateReturn.setDate(new Date());
				statusData.setText("");
				checkInBookCover.setIcon(null);
				showTable_6("");
				
			}
		});
		checkInRefresh.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInRefresh.setBounds(680, 400, 150, 50);
		checkInFrame.add(checkInRefresh);
		
		JButton checkInSubjectList = new JButton("LOST");
		checkInSubjectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(checkInIssuedIDData.getText());
				Integer userID = Integer.parseInt(checkInUserID.getText());
				Integer bookID = Integer.parseInt(checkInBookID.getText());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					
					String issueDate = dateFormat.format(checkInDateIssue.getDate());
					String returnDate = dateFormat.format(checkInDateReturn.getDate());
					
					double cost = new classes.Book().getBookId(bookID).getPrice();
					book.bookLostPayment(bookID, cost);
					
					double penalty = new classes.User().getUserById(userID).getPayment();
					user.setPenalty(userID, penalty+cost);
					
					issue.updateIssue(id, userID, bookID, issueDate, returnDate, "lost");
					countBook();
					checkInIssuedIDData.setText("ID");
					checkInUserID.setText("ID");
					checkInBookID.setText("ID");
					checkInUserNameData.setText("");
					checkInBookTitleData.setText("");
					checkInPaymentData.setText("");
					checkInDateIssue.setDate(new Date());
					checkInDateReturn.setDate(new Date());
					statusData.setText("");
					checkInBookCover.setIcon(null);
					showTable_6("");
					
				}catch (NullPointerException ex) {
					
					JOptionPane.showMessageDialog(null, "SELECT AN ITEM FROM THE TABLE","SELECT ITEM", 2);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		checkInSubjectList.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInSubjectList.setBounds(680, 340, 150, 50);
		checkInFrame.add(checkInSubjectList);
		
		JButton checkIn = new JButton("CHECK IN"); 
		checkIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(checkInIssuedIDData.getText());
				Integer userID = Integer.parseInt(checkInUserID.getText());
				Integer bookID = Integer.parseInt(checkInBookID.getText());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					
					String issueDate = dateFormat.format(checkInDateIssue.getDate());
					String returnDate = dateFormat.format(checkInDateReturn.getDate());
					
					Date issDate = dateFormat.parse(issueDate);
					Date rtnDate = dateFormat.parse(returnDate);
					
					if(rtnDate.before(issDate)) {
						
						JOptionPane.showMessageDialog(null, "RETURN DATE MUST BE AFTER THE ISSUE DATE","WRONG RETURN DATE", 2);
						
					}else {
						
						double cost = 0;
						int quantity = new classes.Book().getBookId(bookID).getQuantity();
						book.setQuantity_Add(bookID, quantity+1);
						
						issue.updateIssue(id, userID, bookID, issueDate, returnDate, "returned");
						countBook();
						checkInIssuedIDData.setText("ID");
						checkInUserID.setText("ID");
						checkInBookID.setText("ID");
						checkInUserNameData.setText("");
						checkInBookTitleData.setText("");
						checkInPaymentData.setText("");
						checkInDateIssue.setDate(new Date());
						checkInDateReturn.setDate(new Date());
						statusData.setText("");
						checkInBookCover.setIcon(null);
						showTable_1();
						showTable_6("");
						
					}
					
				}catch (NullPointerException | ParseException ex) {
					
					JOptionPane.showMessageDialog(null, "SELECT ISSUE & RETURN DATE","SELECT DATE", 2);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, e1);
				}
				
			}
		});
		checkIn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkIn.setBounds(680, 280, 150, 50);
		checkInFrame.add(checkIn);
		
		JPanel checkInBookPanel = new JPanel();
		checkInBookPanel.setLayout(null);
		checkInBookPanel.setBackground(Color.YELLOW);
		checkInBookPanel.setBounds(700, 70, 100, 100);
		checkInFrame.add(checkInBookPanel);
		
		checkInBookNum = new JLabel("000");
		checkInBookNum.setHorizontalAlignment(SwingConstants.CENTER);
		checkInBookNum.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkInBookNum.setBounds(0, 25, 100, 50);
		checkInBookPanel.add(checkInBookNum);
		
		JLabel checkInBookAvailable = new JLabel("BOOK AVAILABLE:");
		checkInBookAvailable.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInBookAvailable.setBounds(680, 20, 150, 30);
		checkInFrame.add(checkInBookAvailable);
		
		checkInUserID = new JLabel("ID");
		checkInUserID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInUserID.setBounds(20, 50, 30, 20);
		checkInFrame.add(checkInUserID);
		
		JLabel checkInUserName = new JLabel("NAME:");
		checkInUserName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInUserName.setBounds(20, 90, 60, 20);
		checkInFrame.add(checkInUserName);
		
		JLabel checkInUserItemLoan = new JLabel("Loan To Pay:");
		checkInUserItemLoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInUserItemLoan.setBounds(20, 130, 100, 20);
		checkInFrame.add(checkInUserItemLoan);
		
		checkInPaymentData = new JLabel("");
		checkInPaymentData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInPaymentData.setBounds(110, 130, 150, 20);
		checkInFrame.add(checkInPaymentData);
		
		checkInUserNameData = new JLabel("");
		checkInUserNameData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInUserNameData.setBounds(80, 90, 150, 20);
		checkInFrame.add(checkInUserNameData);
		
		JPanel bookCoverPanel_1 = new JPanel();
		bookCoverPanel_1.setLayout(null);
		bookCoverPanel_1.setBackground(Color.YELLOW);
		bookCoverPanel_1.setBounds(325, 50, 100, 100);
		checkInFrame.add(bookCoverPanel_1);
		
		checkInBookCover = new JLabel("");
		checkInBookCover.setHorizontalAlignment(SwingConstants.CENTER);
		checkInBookCover.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkInBookCover.setBounds(0, 0, 100, 100);
		bookCoverPanel_1.add(checkInBookCover);
		
		checkInBookID = new JLabel("ID");
		checkInBookID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInBookID.setBounds(435, 50, 30, 20);
		checkInFrame.add(checkInBookID);
		
		JLabel checkInBookTitle = new JLabel("TITLE:");
		checkInBookTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInBookTitle.setBounds(435, 90, 60, 20);
		checkInFrame.add(checkInBookTitle);
		
		JLabel bookAvailable_1 = new JLabel("STATUS:");
		bookAvailable_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bookAvailable_1.setBounds(435, 130, 100, 20);
		checkInFrame.add(bookAvailable_1);
		
		statusData = new JLabel("");
		statusData.setForeground(Color.BLUE);
		statusData.setFont(new Font("Times New Roman", Font.BOLD, 15));
		statusData.setBounds(500, 130, 100, 20);
		checkInFrame.add(statusData);
		
		checkInBookTitleData = new JTextPane();
		checkInBookTitleData.setBackground(new Color(255, 255, 204));
		checkInBookTitleData.setBounds(495, 90, 150, 20);
		checkInFrame.add(checkInBookTitleData);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String status = comboBox.getSelectedItem().toString();
				
				if(status.equals("ALL")) {
					
					status = "";
					
				}
				
				showTable_6(status);
				
			}
		});
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ALL", "ISSUED", "RETURNED", "LOST"}));
		comboBox.setBounds(20, 240, 120, 25);
		checkInFrame.add(comboBox);
		
		JLabel lblNewLabel_1_2 = new JLabel("ISSUE DATE:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(630, 196, 100, 25);
		checkInFrame.add(lblNewLabel_1_2);
		
		checkInDateIssue = new JDateChooser();
		checkInDateIssue.setEnabled(false);
		checkInDateIssue.setBounds(730, 196, 100, 30);
		checkInFrame.add(checkInDateIssue);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("DUE DATE:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(659, 236, 71, 25);
		checkInFrame.add(lblNewLabel_1_1_1);
		
		checkInDateReturn = new JDateChooser();
		checkInDateReturn.setBounds(730, 236, 100, 30);
		checkInFrame.add(checkInDateReturn);
		
		JScrollPane checkInScrollPane = new JScrollPane();
		checkInScrollPane.setBounds(20, 280, 600, 170);
		checkInFrame.add(checkInScrollPane);
		
		checkInTable = new JTable();
		checkInTable.setGridColor(Color.ORANGE);
		checkInTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		checkInTable.setBackground(new Color(255, 255, 255));
		checkInTable.setSelectionBackground(new Color(255, 255, 0));
		checkInTable.setSelectionForeground(Color.black);
		checkInTable.getTableHeader().setBackground(Color.blue);
		checkInTable.getTableHeader().setForeground(Color.black);
		checkInTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		checkInTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = checkInTable.getSelectedRow();
				int id = Integer.parseInt(checkInTable.getValueAt(index, 0).toString());
				int bookID = Integer.parseInt(checkInTable.getValueAt(index, 1).toString());
				int userID = Integer.parseInt(checkInTable.getValueAt(index, 2).toString());
				String issuedDate = checkInTable.getValueAt(index, 3).toString();
				String returnDate = checkInTable.getValueAt(index, 4).toString();
				String status = checkInTable.getValueAt(index, 5).toString();
				
				classes.Book selectedBook;
				classes.User selectedUser;
				
				try {
					
					String issid = checkInTable.getValueAt(index, 0).toString();
					checkInIssuedIDData.setText(issid);
					selectedBook = book.getBookByID(bookID);
					String bookid = checkInTable.getValueAt(index, 1).toString();
					checkInBookID.setText(bookid);
					checkInBookTitleData.setText(selectedBook.getTitle());
					byte[] cover = selectedBook.getImage();
					func.displayImage(100, 100, cover, "", checkInBookCover);
					
					selectedUser = user.getUserById(userID);
					String userid = checkInTable.getValueAt(index, 2).toString();
					checkInUserID.setText(userid);
					checkInUserNameData.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());
					
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					Date issDate = new SimpleDateFormat("yyyy-MM-dd").parse(issuedDate);
					checkInDateIssue.setDate(issDate);
					Date rtnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDate);
					checkInDateReturn.setDate(rtnDate);
					
					statusData.setText(status);
					
					checkInPaymentData.setText(String.valueOf(selectedUser.getPayment()));
					
					String stats = comboBox.getSelectedItem().toString();
					
					Calendar cal = Calendar.getInstance();
					Date present = new Date();
					Date issDay = date.parse(issuedDate);
					Date returnDay = date.parse(returnDate);
										
					Home Days = new Home();
					long day = Days.daysBetween(present, rtnDate);
					Double pay = new Double(0);
					double penalty = new classes.User().getUserById(userID).getPayment();
					
					if(present.before(returnDay)) {
						
						JOptionPane.showMessageDialog(null, day + " Days Left");
						
					}else if(status.equals("returned")){
						
						JOptionPane.showMessageDialog(null, "Book has Been Returned");
						
					}else if(status.equals("lost")){
						
						JOptionPane.showMessageDialog(null, "Book has Been Lost");
						
					}else if(pay.equals(penalty)){
						
						user.setPenalty(userID, penalty+5);
						JOptionPane.showMessageDialog(null, "Penalty, Add 5 pesos");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "This Student Had Penalty");
						
					}
					
					
				}catch(SQLException | NumberFormatException ex) {
					
					Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
					
				}catch(NullPointerException | ParseException ex) {
					
					Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
					
				}
				
			}
		});
		checkInScrollPane.setViewportView(checkInTable);
		
		JLabel checkInIssuedID = new JLabel("ISSUED ID:");
		checkInIssuedID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		checkInIssuedID.setBounds(20, 20, 80, 20);
		checkInFrame.add(checkInIssuedID);
		
		checkInIssuedIDData = new JLabel("");
		checkInIssuedIDData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		checkInIssuedIDData.setBounds(110, 20, 150, 20);
		checkInFrame.add(checkInIssuedIDData);
		
		/**
		 * User Status Frame
		 */
		
		JPanel userStatusPanel = new JPanel();
		tabbedPane.addTab("Borrower Status", new ImageIcon(Home.class.getResource("/Images/icons8-information-16.png")), userStatusPanel, null);
		tabbedPane.setForegroundAt(2, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(2, new Color(0, 0, 255));
		userStatusPanel.setLayout(null);
		
		JPanel userStatusFrame = new JPanel();
		userStatusFrame.setLayout(null);
		userStatusFrame.setBackground(new Color(255, 255, 204));
		userStatusFrame.setBounds(0, 0, 874, 463);
		userStatusPanel.add(userStatusFrame);
		
		JButton userStatusHistory = new JButton("CLEAR");
		userStatusHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				findBorrowerField.setText("");
				borrowerID.setText("ID");
				borrowerNameData.setText("");
				borrowerLoanData.setText("");
				receipt.setText("");
				
			}
		});
		userStatusHistory.setFont(new Font("Times New Roman", Font.BOLD, 14));
		userStatusHistory.setBounds(370, 150, 100, 30);
		userStatusFrame.add(userStatusHistory);
		
		JButton userStatusRefresh = new JButton("PAID");
		userStatusRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(findBorrowerField.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
					
				}else if(borrowerID.getText().equals("ID")) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
					
				}else {
					
					Integer id = Integer.parseInt(borrowerID.getText());
					
					String status = availableYesNo.getText();
					
					Double cost = new Double(0);
					
					user.updateUser(id, cost);
					findBorrowerField.setText("");
					borrowerID.setText("ID");
					borrowerNameData.setText("");
					borrowerLoanData.setText("");
					receipt.setText("");
					showTable_6("");
					
				}
				
			}
		});
		userStatusRefresh.setFont(new Font("Times New Roman", Font.BOLD, 13));
		userStatusRefresh.setBounds(470, 150, 100, 30);
		userStatusFrame.add(userStatusRefresh);
		
		JButton userStatusInformation = new JButton("PRINT");
		userStatusInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					receipt.print();
					
				}catch(PrinterException ex) {
					
					Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
					
				}
				
			}
		});
		userStatusInformation.setFont(new Font("Times New Roman", Font.BOLD, 14));
		userStatusInformation.setBounds(680, 420, 100, 30);
		userStatusFrame.add(userStatusInformation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 550, 250);
		userStatusFrame.add(scrollPane);
		
		table = new JTable();
		table.setGridColor(Color.ORANGE);
		table.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(255, 255, 0));
		table.setSelectionForeground(Color.black);
		table.getTableHeader().setBackground(Color.blue);
		table.getTableHeader().setForeground(Color.black);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		JLabel lblFindBorrower = new JLabel("FIND BORROWER:");
		lblFindBorrower.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFindBorrower.setBounds(60, 11, 150, 30);
		userStatusFrame.add(lblFindBorrower);
		
		findBorrowerField = new JTextField();
		findBorrowerField.setColumns(10);
		findBorrowerField.setBounds(30, 41, 150, 25);
		userStatusFrame.add(findBorrowerField);
		
		JButton bookFindBtn_1 = new JButton("FIND");
		bookFindBtn_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookFindBtn_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		bookFindBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(findBorrowerField.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
					
				}else {

					try {
						
						classes.Book selectedBook;
						classes.User selectedUser;
						Issue findBorrower = null;
						int id = Integer.parseInt(findBorrowerField.getText());
						
						findBorrower = issue.getIssueId(id);
							
						if(findBorrower != null) {
							
							borrowerID.setText(String.valueOf(findBorrower.getUserID()));
							borrowerBookID.setText(String.valueOf(findBorrower.getBookID()));
							
							Integer userID = Integer.parseInt(borrowerID.getText());
							Integer bookID = Integer.parseInt(borrowerBookID.getText());
							selectedUser = user.getUserById(userID);
							selectedBook = book.getBookId(bookID);
							String fullname = (user.getUserById(selectedUser.getId())).getFirstName() + " " +
									  (user.getUserById(selectedUser.getId())).getLastName();
							double loan = (user.getUserById(selectedUser.getId())).getPayment();
							String payment = new Double(loan).toString();
							borrowerNameData.setText(fullname);
							String title = (book.getBookId(selectedBook.getId())).getTitle();
							borrowerTitleData.setText(title);
							borrowerLoanData.setText(payment);
							
						}else{
						
							JOptionPane.showMessageDialog(null, "THIS BORROWER DOES NOT EXIST","USER NOT FOUND", 2);
							
						}
						
					}catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, "INVALID BORROWER ID","ERROR", 3);
						
					}
					
				}
				
			}
		});
		bookFindBtn_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		bookFindBtn_1.setBounds(180, 41, 80, 25);
		userStatusFrame.add(bookFindBtn_1);
		
		borrowerID = new JLabel("ID");
		borrowerID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		borrowerID.setBounds(80, 70, 30, 20);
		userStatusFrame.add(borrowerID);
		
		JLabel borrowerName = new JLabel("NAME:");
		borrowerName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		borrowerName.setBounds(10, 100, 60, 20);
		userStatusFrame.add(borrowerName);
		
		JLabel borrowerLoan = new JLabel("Loan To Pay:");
		borrowerLoan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		borrowerLoan.setBounds(10, 160, 100, 20);
		userStatusFrame.add(borrowerLoan);
		
		borrowerNameData = new JLabel("");
		borrowerNameData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		borrowerNameData.setBounds(70, 100, 150, 20);
		userStatusFrame.add(borrowerNameData);
		
		borrowerLoanData = new JLabel("");
		borrowerLoanData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		borrowerLoanData.setBounds(100, 160, 150, 20);
		userStatusFrame.add(borrowerLoanData);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if(findBorrowerField.getText().equals("")) {
						
						JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
						
					}else {
						
						classes.Issue dateFind = null;
						classes.User userFind = null;
						classes.Book bookFind = null;
						int id = Integer.parseInt(findBorrowerField.getText());
						int userID = Integer.parseInt(borrowerID.getText());
						int bookID = Integer.parseInt(borrowerBookID.getText());
						dateFind = issue.getIssueId(id);
						userFind = user.getUserById(userID);
						bookFind = book.getBookId(bookID);
						
						receipt.append("\n\t NEUST LIBRARY\n\n"
								+ "======================================\n"
								+ "\t Borrower Payment\n\n"
								+ "======================================\n"
								+ "Name:\t"+ userFind.getFirstName() + " "+ userFind.getLastName() +"\n\n"
								+ "Course:\t"+ userFind.getCourse()+" Year Level: "+ userFind.getYearlvl() +"\n\n"
								+ "Book Title:\t"+ bookFind.getTitle()+"\n\n"
								+ "Issue Date:\t"+ dateFind.getIssueDate()+"\n\n"
								+ "Due Date:\t"+ dateFind.getReturnDate()+"\n\n"
								+ "\n======================================\n"
								+ "\t          Total Amount: "+ userFind.getPayment()+"\n\n");
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","EMPTY FIELD", 2);
				}
				
				
			}
		});
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnView.setBounds(270, 150, 100, 30);
		userStatusFrame.add(btnView);
		
		receipt = new JTextArea();
		receipt.setBounds(590, 20, 270, 380);
		userStatusFrame.add(receipt);
		
		JLabel lblBookTitle = new JLabel("BOOK TITLE:");
		lblBookTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBookTitle.setBounds(10, 130, 110, 20);
		userStatusFrame.add(lblBookTitle);
		
		borrowerTitleData = new JLabel("");
		borrowerTitleData.setFont(new Font("Times New Roman", Font.BOLD, 16));
		borrowerTitleData.setBounds(120, 130, 150, 20);
		userStatusFrame.add(borrowerTitleData);
		
		borrowerBookID = new JLabel("ID");
		borrowerBookID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		borrowerBookID.setBounds(230, 70, 30, 20);
		userStatusFrame.add(borrowerBookID);
		
		JLabel userID = new JLabel("USER ID:");
		userID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		userID.setBounds(10, 70, 70, 20);
		userStatusFrame.add(userID);
		
		JLabel bookID = new JLabel("BOOK ID:");
		bookID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bookID.setBounds(150, 70, 80, 20);
		userStatusFrame.add(bookID);
		
		/*
		 * Book Status Frame
		 */
		
		JPanel bookStatusPanel = new JPanel();
		tabbedPane.addTab("Book Status", new ImageIcon(Home.class.getResource("/Images/icons8-info-16.png")), bookStatusPanel, null);
		tabbedPane.setForegroundAt(3, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(3, new Color(0, 0, 255));
		bookStatusPanel.setLayout(null);
		
		JPanel bookStatusFrame = new JPanel();
		bookStatusFrame.setLayout(null);
		bookStatusFrame.setBackground(new Color(255, 255, 204));
		bookStatusFrame.setBounds(0, 0, 874, 463);
		bookStatusPanel.add(bookStatusFrame);
		
		JButton bookStatusHistory = new JButton("PRINT");
		bookStatusHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					history.print();
					
				}catch(PrinterException ex) {
					
					Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
					
				}
				
			}
		});
		bookStatusHistory.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookStatusHistory.setBounds(740, 250, 120, 30);
		bookStatusFrame.add(bookStatusHistory);
		
		JButton bookStatusExport = new JButton("EXPORT");
		bookStatusExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < historyTable.getRowCount(); i++) {
							for(int j = 0; j < historyTable.getRowCount(); j++) {
								
								bw.write(historyTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		bookStatusExport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookStatusExport.setBounds(740, 100, 120, 30);
		bookStatusFrame.add(bookStatusExport);
		
		JButton bookStatusView = new JButton("VIEW");
		bookStatusView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				history.append("\n\t\t\t NEUST Library \n\n"
						+ "====================================================================================================\n"
						+ "\t\t\t Transaction History \n"
						+ "====================================================================================================\n");
				DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
				history.append("\n ID "+" BID  "+"  TITLE\t"+"\t UID  "+"  NAME\t"+"\tISSUE DATE"+"\tDUE DATE"+"\tSTATUS\n\n");
				for(int i = 0; i < historyTable.getRowCount(); i++) {
					
					String id = historyTable.getValueAt(i, 0).toString();
					String bid = historyTable.getValueAt(i, 1).toString();
					String title = historyTable.getValueAt(i, 2).toString();
					String uid = historyTable.getValueAt(i, 3).toString();
					String name = historyTable.getValueAt(i, 4).toString();
					String issue = historyTable.getValueAt(i, 5).toString();
					String due = historyTable.getValueAt(i, 6).toString();
					String status = historyTable.getValueAt(i, 7).toString();
					history.append(id + "  " + bid + "     " + title + "\t" + uid + "     " + name + "\t" + issue + "\t" + due + "\t" + status + "\n");
					
				}
				history.append("\n====================================================================================================");
				
			}
		});
		bookStatusView.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookStatusView.setBounds(740, 30, 120, 30);
		bookStatusFrame.add(bookStatusView);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 10, 700, 220);
		bookStatusFrame.add(scrollPane_1);
		
		historyTable = new JTable();
		historyTable.setGridColor(Color.ORANGE);
		historyTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		historyTable.setBackground(new Color(255, 255, 255));
		historyTable.setSelectionBackground(new Color(255, 255, 0));
		historyTable.setSelectionForeground(Color.black);
		historyTable.getTableHeader().setBackground(Color.blue);
		historyTable.getTableHeader().setForeground(Color.black);
		historyTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane_1.setViewportView(historyTable);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(20, 250, 700, 200);
		bookStatusFrame.add(scrollPane_4);
		
		history = new JTextArea();
		scrollPane_4.setViewportView(history);
		
		JButton bookStatusClear = new JButton("CLEAR");
		bookStatusClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				history.setText("");
				
			}
		});
		bookStatusClear.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bookStatusClear.setBounds(740, 170, 120, 30);
		bookStatusFrame.add(bookStatusClear);
		homeTabbedPane.setForegroundAt(2, new Color(255, 255, 255));
		homeTabbedPane.setBackgroundAt(2, new Color(0, 0, 255));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(255, 255, 204));
		homeTabbedPane.addTab("Account", new ImageIcon(Home.class.getResource("/Images/icons8-merchant-account-16.png")), panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setForeground(new Color(255, 255, 255));
		tabbedPane_1.setBackground(new Color(0, 0, 255));
		tabbedPane_1.setBounds(0, 0, 880, 490);
		panel_1.add(tabbedPane_1);
		
		JPanel panel_2 =  new JPanel();
		panel_2.setBackground(new Color(255, 255, 204));
		tabbedPane_1.addTab("Admin", new ImageIcon(Home.class.getResource("/Images/icons8-admin-settings-male-16.png")), panel_2, null);
		panel_2.setLayout(null);
		
		adminFirstNameField = new JTextField();
		adminFirstNameField.setColumns(10);
		adminFirstNameField.setBounds(40, 20, 200, 25);
		panel_2.add(adminFirstNameField);
		
		JLabel adminFirstName = new JLabel("FIRST NAME:");
		adminFirstName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminFirstName.setBounds(50, 50, 150, 30);
		panel_2.add(adminFirstName);
		
		adminLastNameField = new JTextField();
		adminLastNameField.setColumns(10);
		adminLastNameField.setBounds(340, 20, 200, 25);
		panel_2.add(adminLastNameField);
		
		JLabel adminLastName = new JLabel("LAST NAME:");
		adminLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminLastName.setBounds(350, 50, 150, 30);
		panel_2.add(adminLastName);
		
		adminEmailField = new JTextField();
		adminEmailField.setColumns(10);
		adminEmailField.setBounds(270, 120, 200, 25);
		panel_2.add(adminEmailField);
		
		adminContactNumField = new JTextField();
		adminContactNumField.setColumns(10);
		adminContactNumField.setBounds(640, 20, 200, 25);
		panel_2.add(adminContactNumField);
		
		JLabel adminContactNumber = new JLabel("CONTACT NUMBER:");
		adminContactNumber.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminContactNumber.setBounds(650, 50, 160, 30);
		panel_2.add(adminContactNumber);
		
		JLabel adminEmail = new JLabel("EMAIL");
		adminEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminEmail.setBounds(280, 150, 150, 30);
		panel_2.add(adminEmail);
		
		adminAddressField = new JTextField();
		adminAddressField.setColumns(10);
		adminAddressField.setBounds(30, 120, 200, 25);
		panel_2.add(adminAddressField);
		
		JLabel adminAddress = new JLabel("ADDRESS:");
		adminAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminAddress.setBounds(40, 150, 100, 30);
		panel_2.add(adminAddress);
		
		adminUsernameField = new JTextField();
		adminUsernameField.setColumns(10);
		adminUsernameField.setBounds(500, 120, 150, 25);
		panel_2.add(adminUsernameField);
		
		adminPasswordField = new JPasswordField();
		adminPasswordField.setBounds(690, 120, 150, 25);
		panel_2.add(adminPasswordField);
		
		JLabel adminUsername = new JLabel("USERNAME:");
		adminUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminUsername.setBounds(510, 150, 125, 30);
		panel_2.add(adminUsername);
		
		JLabel adminPassword = new JLabel("PASSWORD:");
		adminPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminPassword.setBounds(700, 150, 125, 30);
		panel_2.add(adminPassword);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 250, 860, 200);
		panel_2.add(scrollPane_3);
		
		adminTable = new JTable();
		adminTable.setGridColor(Color.ORANGE);
		adminTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		adminTable.setBackground(new Color(255, 255, 255));
		adminTable.setSelectionBackground(new Color(255, 255, 0));
		adminTable.setSelectionForeground(Color.black);
		adminTable.getTableHeader().setBackground(Color.blue);
		adminTable.getTableHeader().setForeground(Color.black);
		adminTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		adminTable.setFillsViewportHeight(true);
		adminTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = adminTable.getSelectedRow();
				
				String id = adminTable.getValueAt(index, 0).toString();
				String firstName = adminTable.getValueAt(index, 1).toString();
				String lastName = adminTable.getValueAt(index, 2).toString();
				String contactNum = adminTable.getValueAt(index, 3).toString();
				String address = adminTable.getValueAt(index, 4).toString();
				String email = adminTable.getValueAt(index, 5).toString();
				String username = adminTable.getValueAt(index, 6).toString();
				String password = adminTable.getValueAt(index, 7).toString();
				
				adminIDField.setText(id);
				adminFirstNameField.setText(firstName);
				adminLastNameField.setText(lastName);
				adminContactNumField.setText(contactNum);
				adminAddressField.setText(address);
				adminEmailField.setText(email);
				adminUsernameField.setText(username);
				adminPasswordField.setText(password);
				
			}
		});
		scrollPane_3.setViewportView(adminTable);
		
		JLabel adminID = new JLabel("ADMIN ID:");
		adminID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		adminID.setBounds(10, 200, 100, 30);
		panel_2.add(adminID);
		
		adminIDField = new JTextField();
		adminIDField.setColumns(10);
		adminIDField.setBounds(100, 200, 100, 30);
		panel_2.add(adminIDField);
		
		JButton adminFind = new JButton("FIND");
		adminFind.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		adminFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin adminFind;
				
				try {
					
					Integer id = Integer.parseInt(adminIDField.getText());
					
					adminFind = admin.getAdminById(id);
					
					if(adminFind != null) {
						
						adminIDField.setText(String.valueOf(adminFind.getId()));
						adminFirstNameField.setText(String.valueOf(adminFind.getFirstName()));
						adminLastNameField.setText(String.valueOf(adminFind.getLastName()));
						adminContactNumField.setText(String.valueOf(adminFind.getContact()));
						adminAddressField.setText(String.valueOf(adminFind.getEmail()));
						adminEmailField.setText(String.valueOf(adminFind.getAddress()));
						adminUsernameField.setText(String.valueOf(adminFind.getUsername()));
						adminPasswordField.setText(String.valueOf(adminFind.getPassword()));
						
					}else {
						
						JOptionPane.showMessageDialog(null, "INVALID ADMIN ID","ERROR", 3);
						
					}
										
				}catch(SQLException | NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "ADMIN NOT FOUND","INVALID ID", 2);
					
				}
				
			}
		});
		adminFind.setHorizontalAlignment(SwingConstants.LEFT);
		adminFind.setFont(new Font("Times New Roman", Font.BOLD, 10));
		adminFind.setBounds(200, 200, 80, 30);
		panel_2.add(adminFind);
		
		JButton addAdmin = new JButton("ADD");
		addAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = adminFirstNameField.getText();
				String lastName = adminLastNameField.getText();
				String contactNum = adminContactNumField.getText();
				String address = adminAddressField.getText();
				String email = adminEmailField.getText();
				String username = adminUsernameField.getText();
				String password = adminPasswordField.getText();
				
				if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || contactNum.isEmpty() || email.isEmpty() 
						|| address.isEmpty() || password.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					admin.addAdmin(firstName, lastName, contactNum, email, address, username, password);
					showTable_8();
					
					adminIDField.setText("");
					adminFirstNameField.setText("");
					adminLastNameField.setText("");
					adminContactNumField.setText("");
					adminAddressField.setText("");
					adminEmailField.setText("");
					adminUsernameField.setText("");
					adminPasswordField.setText("");
					
				}
				
			}
		});
		addAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addAdmin.setBounds(330, 200, 100, 30);
		panel_2.add(addAdmin);
		
		JButton editAdmin = new JButton("EDIT");
		editAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(adminIDField.getText());
				String firstName = adminFirstNameField.getText();
				String lastName = adminLastNameField.getText();
				String contactNum = adminContactNumField.getText();
				String address = adminAddressField.getText();
				String email = adminEmailField.getText();
				String username = adminUsernameField.getText();
				String password = adminPasswordField.getText();
				double amount = 0;
				
				if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || contactNum.isEmpty() || email.isEmpty() 
						|| address.isEmpty() || password.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					admin.editAdmin(id, firstName, lastName, contactNum, email, address, username, password);
					showTable_8();
					
					adminIDField.setText("");
					adminFirstNameField.setText("");
					adminLastNameField.setText("");
					adminContactNumField.setText("");
					adminAddressField.setText("");
					adminEmailField.setText("");
					adminUsernameField.setText("");
					adminPasswordField.setText("");
					
				}
				
			}
		});
		editAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		editAdmin.setBounds(440, 200, 100, 30);
		panel_2.add(editAdmin);
		
		JButton clearAdmin = new JButton("CLEAR");
		clearAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showTable_8();
				
				adminIDField.setText("");
				adminFirstNameField.setText("");
				adminLastNameField.setText("");
				adminContactNumField.setText("");
				adminAddressField.setText("");
				adminEmailField.setText("");
				adminUsernameField.setText("");
				adminPasswordField.setText("");
				
			}
		});
		clearAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		clearAdmin.setBounds(550, 200, 100, 30);
		panel_2.add(clearAdmin);
		
		JButton deleteAdmin = new JButton("DELETE");
		deleteAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(adminIDField.getText());
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						admin.deleteAdmin(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "ADMIN NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_8();
					
					adminIDField.setText("");
					adminFirstNameField.setText("");
					adminLastNameField.setText("");
					adminContactNumField.setText("");
					adminAddressField.setText("");
					adminEmailField.setText("");
					adminUsernameField.setText("");
					adminPasswordField.setText("");
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID ADMIN ID","ERROR", 3);
					
				}
				
			}
		});
		deleteAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteAdmin.setBounds(660, 200, 100, 30);
		panel_2.add(deleteAdmin);
		
		JButton exportAdmin = new JButton("EXPORT");
		exportAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < adminTable.getRowCount(); i++) {
							for(int j = 0; j < adminTable.getRowCount(); j++) {
								
								bw.write(adminTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		exportAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		exportAdmin.setBounds(770, 200, 100, 30);
		panel_2.add(exportAdmin);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 204));
		tabbedPane_1.addTab("User", new ImageIcon(Home.class.getResource("/Images/icons8-user-16.png")), panel_3, null);
		panel_3.setLayout(null);
		
		JLabel userID_1 = new JLabel("USER ID:");
		userID_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userID_1.setBounds(20, 161, 70, 30);
		panel_3.add(userID_1);
		
		userIDField = new JTextField();
		userIDField.setColumns(10);
		userIDField.setBounds(100, 160, 100, 30);
		panel_3.add(userIDField);
		
		JButton userFind_1 = new JButton("FIND");
		userFind_1.setIcon(new ImageIcon(Home.class.getResource("/Images/icons8-find-16.png")));
		userFind_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User userFind;
				
				try {
					
					Integer id = Integer.parseInt(userIDField.getText());
					
					userFind = user.getUserById(id);
					
					if(userFind != null) {
						
						userIDField.setText(String.valueOf(userFind.getId()));
						userFirstNameField.setText(String.valueOf(userFind.getFirstName()));
						useLastNameField.setText(String.valueOf(userFind.getLastName()));
						studentNoField.setText(String.valueOf(userFind.getStudentNum()));
						userContactField.setText(String.valueOf(userFind.getContact()));
						userEmailField.setText(String.valueOf(userFind.getEmail()));
						userAddressField.setText(String.valueOf(userFind.getAddress()));
						yearLevelChoice.setSelectedItem(userFind.getYearlvl());
						courseChoice.setSelectedItem(userFind.getCourse());
						
					}else {
						
						JOptionPane.showMessageDialog(null, "INVALID USER ID","ERROR", 3);
						
					}
										
				}catch(SQLException | NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "USER NOT FOUND","INVALID ID", 2);
					
				}
				
			}
		});
		userFind_1.setHorizontalAlignment(SwingConstants.LEFT);
		userFind_1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		userFind_1.setBounds(200, 160, 80, 30);
		panel_3.add(userFind_1);
		
		JLabel userFirstName_1 = new JLabel("FIRST NAME:");
		userFirstName_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userFirstName_1.setBounds(20, 41, 120, 30);
		panel_3.add(userFirstName_1);
		
		userFirstNameField = new JTextField();
		userFirstNameField.setColumns(10);
		userFirstNameField.setBounds(20, 10, 150, 30);
		panel_3.add(userFirstNameField);
		
		JLabel userLastName_1 = new JLabel("LAST NAME:");
		userLastName_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userLastName_1.setBounds(240, 41, 120, 30);
		panel_3.add(userLastName_1);
		
		useLastNameField = new JTextField();
		useLastNameField.setColumns(10);
		useLastNameField.setBounds(240, 11, 150, 30);
		panel_3.add(useLastNameField);
		
		JLabel studentNo_1 = new JLabel("STUDENT NO.:");
		studentNo_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		studentNo_1.setBounds(460, 41, 150, 30);
		panel_3.add(studentNo_1);
		
		studentNoField = new JTextField();
		studentNoField.setColumns(10);
		studentNoField.setBounds(460, 11, 150, 30);
		panel_3.add(studentNoField);
		
		JLabel userContact_1 = new JLabel("CONTACT NO.:");
		userContact_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userContact_1.setBounds(680, 41, 150, 30);
		panel_3.add(userContact_1);
		
		userContactField = new JTextField();
		userContactField.setColumns(10);
		userContactField.setBounds(680, 11, 150, 30);
		panel_3.add(userContactField);
		
		JLabel userEmail_1 = new JLabel("EMAIL:");
		userEmail_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userEmail_1.setBounds(20, 121, 150, 30);
		panel_3.add(userEmail_1);
		
		userEmailField = new JTextField();
		userEmailField.setColumns(10);
		userEmailField.setBounds(20, 91, 150, 30);
		panel_3.add(userEmailField);
		
		JLabel userAddress_1 = new JLabel("ADDRESS:");
		userAddress_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userAddress_1.setBounds(240, 121, 150, 30);
		panel_3.add(userAddress_1);
		
		userAddressField = new JTextField();
		userAddressField.setColumns(10);
		userAddressField.setBounds(240, 91, 150, 30);
		panel_3.add(userAddressField);
		
		JLabel userYearLevel_1 = new JLabel("YEAR LEVEL:");
		userYearLevel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userYearLevel_1.setBounds(460, 121, 150, 30);
		panel_3.add(userYearLevel_1);
		
		yearLevelChoice = new JComboBox(new Object[]{"", "First Year", "Second Year", "Third Year", "Fourth Year"});
		yearLevelChoice.setBounds(460, 91, 150, 30);
		panel_3.add(yearLevelChoice);
		
		JLabel userCourse_1 = new JLabel("COURSE:");
		userCourse_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		userCourse_1.setBounds(680, 121, 150, 30);
		panel_3.add(userCourse_1);
		
		courseChoice = new JComboBox(new Object[]{"", "Laboratory High", "BSIT", "BSED", "BSBA"});
		courseChoice.setBounds(680, 91, 150, 30);
		panel_3.add(courseChoice);
		
		JButton addUser = new JButton("ADD");
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = userFirstNameField.getText();
				String lastName = useLastNameField.getText();
				String studentNum = studentNoField.getText();
				String contactNum = userContactField.getText();
				String email = userEmailField.getText();
				String address = userAddressField.getText();
				String yearlvl = yearLevelChoice.getSelectedItem().toString();;
				String course = courseChoice.getSelectedItem().toString();;
				double amount = 0;
				
				if(firstName.isEmpty() || lastName.isEmpty() || studentNum.isEmpty() || contactNum.isEmpty() || email.isEmpty() 
						|| address.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					user.addUser(firstName, lastName, studentNum, contactNum, email, address, yearlvl, course, amount);
					showTable_5();
					
					userIDField.setText("");
					userFirstNameField.setText("");
					useLastNameField.setText("");
					studentNoField.setText("");
					userContactField.setText("");
					userEmailField.setText("");
					userAddressField.setText("");
					yearLevelChoice.setSelectedIndex(0);
					courseChoice.setSelectedIndex(0);
					
				}
				
			}
		});
		addUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		addUser.setBounds(330, 160, 100, 30);
		panel_3.add(addUser);
		
		JButton editUser = new JButton("EDIT");
		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.valueOf(userIDField.getText());
				String firstName = userFirstNameField.getText();
				String lastName = useLastNameField.getText();
				String studentNum = studentNoField.getText();
				String contactNum = userContactField.getText();
				String email = userEmailField.getText();
				String address = userAddressField.getText();
				String yearlvl = yearLevelChoice.getSelectedItem().toString();;
				String course = courseChoice.getSelectedItem().toString();;
				double amount = 0;
				
				if(firstName.isEmpty() || lastName.isEmpty() || studentNum.isEmpty() || contactNum.isEmpty() || email.isEmpty() 
						|| address.isEmpty()){
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					user.editUser(id, firstName, lastName, studentNum, contactNum, email, address, yearlvl, course, amount);
					showTable_5();
					
					userIDField.setText("");
					userFirstNameField.setText("");
					useLastNameField.setText("");
					studentNoField.setText("");
					userContactField.setText("");
					userEmailField.setText("");
					userAddressField.setText("");
					yearLevelChoice.setSelectedIndex(0);
					courseChoice.setSelectedIndex(0);
					
				}
				
			}
		});
		editUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		editUser.setBounds(440, 160, 100, 30);
		panel_3.add(editUser);
		
		JButton clearUser = new JButton("CLEAR");
		clearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				userIDField.setText("");
				userFirstNameField.setText("");
				useLastNameField.setText("");
				studentNoField.setText("");
				userContactField.setText("");
				userEmailField.setText("");
				userAddressField.setText("");
				yearLevelChoice.setSelectedIndex(0);
				courseChoice.setSelectedIndex(0);
				
			}
		});
		clearUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		clearUser.setBounds(550, 160, 100, 30);
		panel_3.add(clearUser);
		
		JButton deleteUser = new JButton("DELETE");
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.valueOf(userIDField.getText());
					
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You Want to Delete This Member","CONFIRMATION BOX", JOptionPane.YES_NO_OPTION);
					
					if(confirm == JOptionPane.YES_OPTION) {
						
						user.deleteUser(id);
						
					}else {
						JOptionPane.showMessageDialog(null, "USER NOT DELETED","CANCEL DELETION", 2);
					}
					
					showTable_5();
					
					userIDField.setText("");
					userFirstNameField.setText("");
					useLastNameField.setText("");
					studentNoField.setText("");
					userContactField.setText("");
					userEmailField.setText("");
					userAddressField.setText("");
					yearLevelChoice.setSelectedIndex(0);
					courseChoice.setSelectedIndex(0);
					
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID USER ID","ERROR", 3);
					
				}
				
			}
		});
		deleteUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteUser.setBounds(660, 160, 100, 30);
		panel_3.add(deleteUser);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 200, 860, 260);
		panel_3.add(scrollPane_2);
		
		userTable = new JTable();
		userTable.setGridColor(Color.ORANGE);
		userTable.setFont(new Font("Times New Roman", Font.BOLD, 10));
		userTable.setBackground(new Color(255, 255, 255));
		userTable.setSelectionBackground(new Color(255, 255, 0));
		userTable.setSelectionForeground(Color.black);
		userTable.getTableHeader().setBackground(Color.blue);
		userTable.getTableHeader().setForeground(Color.black);
		userTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		userTable.setFillsViewportHeight(true);
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = userTable.getSelectedRow();
				
				String id = userTable.getValueAt(index, 0).toString();
				String firstName = userTable.getValueAt(index, 1).toString();
				String lastName = userTable.getValueAt(index, 2).toString();
				String studetNum = userTable.getValueAt(index, 3).toString();
				String contactNum = userTable.getValueAt(index, 4).toString();
				String email = userTable.getValueAt(index, 5).toString();
				String address = userTable.getValueAt(index, 6).toString();
				String yearlvl = userTable.getValueAt(index, 7).toString();
				String course = userTable.getValueAt(index, 8).toString();
				
				userIDField.setText(id);
				userFirstNameField.setText(firstName);
				useLastNameField.setText(lastName);
				studentNoField.setText(studetNum);
				userContactField.setText(contactNum);
				userEmailField.setText(email);
				userAddressField.setText(address);
				yearLevelChoice.setSelectedItem(yearlvl);
				courseChoice.setSelectedItem(course);
				
			}
		});
		scrollPane_2.setViewportView(userTable);
		
		JButton exportUser = new JButton("EXPORT");
		exportUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a File Save");
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File saveFile = fileChooser.getSelectedFile();
					
					try {
						
						FileWriter fw = new FileWriter(saveFile);
						BufferedWriter bw = new BufferedWriter(fw);
						for(int i = 0; i < userTable.getRowCount(); i++) {
							for(int j = 0; j < userTable.getRowCount(); j++) {
								
								bw.write(userTable.getValueAt(i, j).toString()+",");
								
							}
							bw.newLine();//record per line 
						}
						JOptionPane.showMessageDialog(fileChooser, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
		                bw.close();
		                fw.close();
						
					}catch(IOException ex) {
						
						JOptionPane.showMessageDialog(fileChooser, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						
					}
					
				}
				
			}
		});
		exportUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		exportUser.setBounds(770, 160, 100, 30);
		panel_3.add(exportUser);
		
		/**
		 * To View the Data in all the Tables
		 */
		
		showTable_1();
		showTable_2();
		showTable_3();
		showTable_4();
		showTable_5();
		showTable_6("");
		//showTable_7();
		showTable_8();
		
		book.displayBookCover(covers);
		fillJcomboboxWithSubject();
		
		countBook();
		
	}
	
	private void formComponentMoved(java.awt.event.ComponentEvent evt) {   
		
	       this.setLocationRelativeTo(null);
	       
	    }   
	
	public void showTable_1() {
		
		ArrayList<classes.Book> bookList = book.bookList();
		
		String[] colNames = {"ID", "ISBN", "ACCESSION NO.", "TITLE", "AUTHOR ID", "SUBJECT ID", "PUBLISHER", "QUANTITY", "COPYRIGHT", "PRICE"};
		
		Object[][] rows = new Object[bookList.size()][colNames.length];
		
		for(int i = 0; i < bookList.size(); i++) {
			
			rows[i][0] = bookList.get(i).getId();
			rows[i][1] = bookList.get(i).getIsbn();
			rows[i][2] = bookList.get(i).getAccessionNum();
			rows[i][3] = bookList.get(i).getTitle();
			rows[i][4] = bookList.get(i).getAuthorID();
			rows[i][5] = bookList.get(i).getSubjectID();
			rows[i][6] = bookList.get(i).getPublisherID();
			rows[i][7] = bookList.get(i).getQuantity();
			rows[i][8] = bookList.get(i).getCopyright();
			rows[i][9] = bookList.get(i).getPrice();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		bookTable.setModel(model);
		
	}
	
	public void showTable_2() {
		
		ArrayList<classes.Author> authorList = author.authorList();
		
		String[] colNames = {"ID", "FIRST NAME", "LAST NAME", "EXPERTISE"};
		
		Object[][] rows = new Object[authorList.size()][colNames.length];
		
		for(int i = 0; i < authorList.size(); i++) {
			
			rows[i][0] = authorList.get(i).getId();
			rows[i][1] = authorList.get(i).getFirstName();
			rows[i][2] = authorList.get(i).getLastName();
			rows[i][3] = authorList.get(i).getExpertise();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		authorTable.setModel(model);
		
	}
	
	public void showTable_3() {
		
		ArrayList<classes.Subject> subjectList = subject.genreList();
		
		String[] colNames = {"ID", "NAME"};
		
		Object[][] rows = new Object[subjectList.size()][colNames.length];
		
		for(int i = 0; i < subjectList.size(); i++) {
			
			rows[i][0] = subjectList.get(i).getId();
			rows[i][1] = subjectList.get(i).getName();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		subjectTable.setModel(model);
		
	}
	
	public void showTable_4() {
		
		ArrayList<classes.Publisher> publisherList = publisher.publisherList();
		
		String[] colNames = {"ID", "NAME", "CITY", "STREET", "ZIP CODE"};
		
		Object[][] rows = new Object[publisherList.size()][colNames.length];
		
		for(int i = 0; i < publisherList.size(); i++) {
			
			rows[i][0] = publisherList.get(i).getId();
			rows[i][1] = publisherList.get(i).getName();
			rows[i][2] = publisherList.get(i).getCity();
			rows[i][3] = publisherList.get(i).getStreet();
			rows[i][4] = publisherList.get(i).getZipcode();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		publisherTable.setModel(model);
		
	}
	
	public void showTable_5() {
		
		ArrayList<classes.User> userList = user.userList();
		
		String[] colNames = {"ID", "FIRST NAME", "LAST NAME", "STUDENT NO.", "CONTACT NO.", "EMAIL", "ADDRESS", "YEAR LEVEL", "COURSE"};
		
		Object[][] rows = new Object[userList.size()][colNames.length];
		
		for(int i = 0; i < userList.size(); i++) {
			
			rows[i][0] = userList.get(i).getId();
			rows[i][1] = userList.get(i).getFirstName();
			rows[i][2] = userList.get(i).getLastName();
			rows[i][3] = userList.get(i).getStudentNum();
			rows[i][4] = userList.get(i).getContact();
			rows[i][5] = userList.get(i).getEmail();
			rows[i][6] = userList.get(i).getAddress();
			rows[i][7] = userList.get(i).getYearlvl();
			rows[i][8] = userList.get(i).getCourse();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		userTable.setModel(model);
		
	}
	
	public void showTable_6(String status) {
		
		ArrayList<classes.Issue> issueLis = issue.issuedBookList(status);
		
		String[] colNames = {"ID", "BOOK ID", "USER ID", "ISSUED DATE", "DUE DATE", "STATUS"};
		
		Object[][] rows = new Object[issueLis.size()][colNames.length];
		
		for(int i = 0; i < issueLis.size(); i++) {
			
			rows[i][0] = issueLis.get(i).getId();
			rows[i][1] = issueLis.get(i).getBookID();
			rows[i][2] = issueLis.get(i).getUserID();
			rows[i][3] = issueLis.get(i).getIssueDate();
			rows[i][4] = issueLis.get(i).getReturnDate();
			rows[i][5] = issueLis.get(i).getStatus();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		checkInTable.setModel(model);
		table.setModel(model);
		
	}
	
	public void showTable_7() {
		
		ArrayList<classes.Issue> issueLis = issue.issueList();
		ArrayList<classes.Book> bookList = book.bookList();
		ArrayList<classes.User> userList = user.userList();
		
		String[] colNames = {"ID", "BID", "TITLE", "UID", "NAME", "DUE DATE", "RETURN DATE", "STATUS"};
		
		Object[][] rows = new Object[issueLis.size()][colNames.length];
		
		for(int i = 0; i < issueLis.size(); i++) {
			
			rows[i][0] = issueLis.get(i).getId();
			rows[i][1] = issueLis.get(i).getBookID();
			rows[i][2] = bookList.get(i).getTitle();
			rows[i][3] = issueLis.get(i).getUserID();
			rows[i][4] = userList.get(i).getFirstName()+ " " +userList.get(i).getLastName();
			rows[i][5] = issueLis.get(i).getIssueDate();
			rows[i][6] = issueLis.get(i).getReturnDate();
			rows[i][7] = issueLis.get(i).getStatus();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames); 
		historyTable.setModel(model);
		
	}
	
	public void showTable_8() {
		
		ArrayList<classes.Admin> adminList = admin.adminList();
		
		String[] colNames = {"ID", "FIRST NAME", "LAST NAME", "CONTACT NO.", "ADDRESS", "EMAIL", "USERNAME", "PASSWORD"};
		
		Object[][] rows = new Object[adminList.size()][colNames.length];
		
		for(int i = 0; i < adminList.size(); i++) {
			
			rows[i][0] = adminList.get(i).getId();
			rows[i][1] = adminList.get(i).getFirstName();
			rows[i][2] = adminList.get(i).getLastName();
			rows[i][3] = adminList.get(i).getContact();
			rows[i][4] = adminList.get(i).getAddress();
			rows[i][5] = adminList.get(i).getEmail();
			rows[i][6] = adminList.get(i).getUsername();
			rows[i][7] = adminList.get(i).getPassword();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		adminTable.setModel(model);
		
	}
	
	public static void displayAuthorData(int id, String fullName) {
		
		bookAuthorField.setText(fullName);
		AuthorID.setText(String.valueOf(id));
		
	}
	
	public void fillJcomboboxWithSubject() {
		
		for(String subjectName : subjectMap.keySet()) {
			
			chooseSubject.addItem(subjectName);
			
		}
		
	}
	
	public static void displayPublisherData(int id, String name) {
		
		bookPublisherField.setText(name);
		PublisherID.setText(String.valueOf(id));
		
	}
	
	public static void displayCheckOutUserData(int id, String name) {
		
		checkOutUserField.setText(name);
		checkOutUserName.setText(String.valueOf(id));
		
	}
	
	public static void displayBookData(int id, String title) {
		
		checkOutBookID.setText(String.valueOf(id));
		checkOutBookTitleData.setText(String.valueOf(title));
		//byte[] cover = checkOutBookFind.getImage();
		
		//func.displayImage(100, 100, cover, "", checkOutBookCover);
		
	}
	
	public boolean error() {
		
		if(isbnField.getText().equals("") || bookAccessionNoField.getText().equals("") || bookTitleField.getText().equals("") || bookAuthorField.getText().equals("") 
				|| AuthorID.getText().equals("ID") || SubjectID.getText().equals("ID") || bookPublisherField.getText().equals("") 
				|| PublisherID.getText().equals("ID") || chooseDate.getSelectedItem().equals("")) {
			
			return false;
			
		}else {
			
			return true;
			
		}
		
	}
	
	public boolean userExist() {
		
		if(checkOutUserField.getText().equals("") || CheckOutUserID.getText().equals("ID")) {
			
			return false;
			
		}else {
			
			return true;
			
		}
		
	}

	public boolean bookExist() {
	
	if(checkOutBookField.getText().equals("") || checkOutBookID.getText().equals("ID")) {
		
		return false;
		
	}else {
		
		return true;
		
		}
	
	}
	
	public void countBook() {
		
		int total = func.countBook("book");
		dashboardBookNum.setText(String.valueOf(total));
		checkOutBookNum.setText(String.valueOf(total));
		checkInBookNum.setText(String.valueOf(total));
		
	}
	
	public long daysBetween(Date one, Date two) {
		
		long diff = (one.getTime() - two.getTime())/86400000;
		
		return Math.abs(diff);
						
	}
}
