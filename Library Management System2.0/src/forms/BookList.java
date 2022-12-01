package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import classes.Author;
import classes.Book;
import classes.database;
import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class BookList extends JFrame {

	private JPanel contentPane;
	private JLabel emptyField2;
	private JLabel emptyField1;
	private JLabel emptyField3;
	classes.Book book = new classes.Book();
	classes.Author author = new classes.Author();
	classes.Subject subject = new classes.Subject();
	classes.Publisher publisher = new classes.Publisher();
	classes.Function func = new classes.Function();
	public String formType = "";
	private JTextField textField;
	private JTable table;
	private JLabel imgTxt;
	private JLabel isbnData;
	private JLabel accessNumData;
	private JLabel titleData;
	private JLabel authorData;
	private JLabel subjectData;
	private JLabel publisherData;
	private JLabel copyrightData;
	private JLabel price;
	private JLabel priceData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookList frame = new BookList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookList() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(340, 90, 715, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(0, 0, 715, 90);
		contentPane.add(panel);
		Image logo = new ImageIcon(this.getClass().getResource("/Images/author.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		JPanel closePanel = new JPanel();
		closePanel.setBackground(Color.RED);
		closePanel.setBounds(645, 0, 70, 70);
		panel.add(closePanel);
		closePanel.setLayout(null);
		
		JLabel close = new JLabel("X");
		close.setBounds(0, 0, 70, 70);
		closePanel.add(close);
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
		});
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setFont(new Font("Tahoma", Font.BOLD, 60));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 255));
		panel_1.setBounds(0, 70, 715, 10);
		panel.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 255, 0));
		panel_1_1.setBounds(0, 80, 715, 10);
		panel.add(panel_1_1);
		
		JLabel lblAuthorList = new JLabel("BOOK LIST");
		lblAuthorList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorList.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAuthorList.setBounds(200, 10, 300, 50);
		panel.add(lblAuthorList);
		
		JButton selectBtn = new JButton("SELECT");
		selectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String title = table.getValueAt(index, 3).toString();
				//byte image = Byte.parseByte(table.getValueAt(index, 0).toString());
								
				if(formType.equals("edit")) 
					
					Home.displayBookData(id, title);
					
				else 
				
					Home.displayBookData(id, title);
				
				dispose();
				
			}
		});
		selectBtn.setBounds(250, 560, 200, 25);
		contentPane.add(selectBtn);
		
		JLabel id = new JLabel("VALUE TO SEARCH:");
		id.setFont(new Font("Times New Roman", Font.BOLD, 16));
		id.setBounds(10, 126, 170, 30);
		contentPane.add(id);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(195, 126, 200, 30);
		contentPane.add(textField);
		
		JButton searchField = new JButton("SEARCH");
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String value = textField.getText();
				String query = "SELECT * FROM `book` WHERE `title` LIKE '%" + value + "%'";
				
				if(value.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "PLEASE FILL-UP THE EMPTY FIELD","MISSING", 3);
					
				}else {
					
					showTable(query);
					
				}
				
			}
		});
		searchField.setFont(new Font("Times New Roman", Font.BOLD, 11));
		searchField.setBounds(405, 129, 120, 25);
		contentPane.add(searchField);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(Color.GRAY);
		panel_1_2.setBounds(500, 190, 100, 100);
		contentPane.add(panel_1_2);
		
		imgTxt = new JLabel("");
		imgTxt.setHorizontalAlignment(SwingConstants.CENTER);
		imgTxt.setBounds(0, 0, 100, 100);
		panel_1_2.add(imgTxt);
		
		JLabel isbn = new JLabel("ISBN:");
		isbn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		isbn.setBounds(500, 305, 40, 20);
		contentPane.add(isbn);
		
		JLabel accessNum = new JLabel("Accession No.:");
		accessNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
		accessNum.setBounds(500, 335, 80, 20);
		contentPane.add(accessNum);
		
		JLabel title = new JLabel("Title:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(500, 365, 30, 20);
		contentPane.add(title);
		
		JLabel authorTxt = new JLabel("Author:");
		authorTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		authorTxt.setBounds(500, 395, 50, 20);
		contentPane.add(authorTxt);
		
		JLabel subjectTxt = new JLabel("Subject:");
		subjectTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		subjectTxt.setBounds(500, 425, 50, 20);
		contentPane.add(subjectTxt);
		
		JLabel publisherTxt = new JLabel("Publisher:");
		publisherTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		publisherTxt.setBounds(500, 455, 60, 20);
		contentPane.add(publisherTxt);
		
		JLabel copyright = new JLabel("Copyright:");
		copyright.setFont(new Font("Times New Roman", Font.BOLD, 12));
		copyright.setBounds(500, 485, 60, 20);
		contentPane.add(copyright);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isbnData.setText("");
				accessNumData.setText("");
				titleData.setText("");
				authorData.setText("");
				subjectData.setText("");
				publisherData.setText("");
				copyrightData.setText("");
				imgTxt.setIcon(null);
				
			}
		});
		btnRefresh.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnRefresh.setBounds(545, 129, 120, 25);
		contentPane.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 480, 340);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setGridColor(Color.ORANGE);
		table.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(255, 255, 0));
		table.setSelectionForeground(Color.black);
		table.getTableHeader().setBackground(Color.blue);
		table.getTableHeader().setForeground(Color.black);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Book SelectedBook;
				
				try {
					
					Integer rowIndex = table.getSelectedRow();
					Integer id = Integer.parseInt(table.getModel().getValueAt(rowIndex, 0).toString());
					
					SelectedBook = book.getBookByID(id);
					
					if(SelectedBook != null) {
						
						isbnData.setText(String.valueOf(SelectedBook.getIsbn()));
						accessNumData.setText(String.valueOf(SelectedBook.getAccessionNum()));
						titleData.setText(String.valueOf(SelectedBook.getTitle()));
						copyrightData.setText(SelectedBook.getCopyright());
						priceData.setText(String.valueOf(SelectedBook.getPrice()));
						byte[] image = SelectedBook.getImage();
						
						String fullname = (author.getAuthorById(SelectedBook.getAuthorID())).getFirstName() + " " +
								  (author.getAuthorById(SelectedBook.getAuthorID())).getLastName();
						authorData.setText(fullname);
						String genre = (subject.getSubjectById(SelectedBook.getPublisherID())).getName();
						subjectData.setText(genre);
						String name = (publisher.getPublisherById(SelectedBook.getPublisherID())).getName();
						publisherData.setText(name);
						
						func.displayImage(90, 90, image, "", imgTxt);
						
					}else {
						
						JOptionPane.showMessageDialog(null, "MEMBER NOT FOUND","INVALID ID", 2);
						
					}
										
				}catch(NumberFormatException ex) {
					
					JOptionPane.showMessageDialog(null, "INVALID MEMBER ID","ERROR", 3);
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "THIS BOOK DOES NOT EXISTS","BOOK NOT FOUND", 2);
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		isbnData = new JLabel("");
		isbnData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		isbnData.setBounds(540, 305, 150, 20);
		contentPane.add(isbnData);
		
		accessNumData = new JLabel("");
		accessNumData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		accessNumData.setBounds(585, 335, 100, 20);
		contentPane.add(accessNumData);
		
		titleData = new JLabel("");
		titleData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		titleData.setBounds(540, 365, 150, 20);
		contentPane.add(titleData);
		
		authorData = new JLabel("");
		authorData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		authorData.setBounds(550, 395, 150, 20);
		contentPane.add(authorData);
		
		subjectData = new JLabel("");
		subjectData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		subjectData.setBounds(550, 425, 150, 20);
		contentPane.add(subjectData);
		
		publisherData = new JLabel("");
		publisherData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		publisherData.setBounds(560, 455, 150, 20);
		contentPane.add(publisherData);
		
		copyrightData = new JLabel("");
		copyrightData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		copyrightData.setBounds(560, 485, 150, 20);
		contentPane.add(copyrightData);
		
		price = new JLabel("Price:");
		price.setFont(new Font("Times New Roman", Font.BOLD, 12));
		price.setBounds(500, 515, 40, 20);
		contentPane.add(price);
		
		priceData = new JLabel("");
		priceData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		priceData.setBounds(540, 515, 150, 20);
		contentPane.add(priceData);
		
		showTable("");
		
	}
	
	public void showTable(String query) {
		
		ArrayList<classes.Book> bookList = book.booksList(query);
		
		String[] colNames = {"ID", "ISBN", "ACCESSION NO.", "TITLE", "AUTHOR", "SUBJECT", "PUBLISHER", "COPYRIGHT", "PRICE"};
		
		Object[][] rows = new Object[bookList.size()][colNames.length];
		
		for(int i = 0; i < bookList.size(); i++) {
			
			rows[i][0] = bookList.get(i).getId();
			rows[i][1] = bookList.get(i).getIsbn();
			rows[i][2] = bookList.get(i).getAccessionNum();
			rows[i][3] = bookList.get(i).getTitle();
			rows[i][4] = bookList.get(i).getAuthorID();
			rows[i][5] = bookList.get(i).getSubjectID();
			rows[i][6] = bookList.get(i).getPublisherID();
			rows[i][7] = bookList.get(i).getCopyright();
			rows[i][8] = bookList.get(i).getPrice();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		table.setModel(model);
		
	}
}
