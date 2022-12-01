package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DashboardAuthorList extends JFrame {

	DefaultTableModel model;
	
	private JPanel contentPane;
	private JLabel emptyField2;
	private JLabel emptyField1;
	private JLabel emptyField3;
	classes.Book book = new classes.Book();
	classes.Author author = new classes.Author();
	classes.Subject subject = new classes.Subject();
	classes.Publisher publisher = new classes.Publisher();
	classes.Function func = new classes.Function();
	classes.Issue issue = new classes.Issue();
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
	private JLabel availableYesNo;
	private JLabel bookID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardAuthorList frame = new DashboardAuthorList();
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
	public DashboardAuthorList() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(0, 0, 900, 90);
		contentPane.add(panel);
		Image logo = new ImageIcon(this.getClass().getResource("/Images/author.png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		
		JPanel closePanel = new JPanel();
		closePanel.setBackground(Color.RED);
		closePanel.setBounds(830, 0, 70, 70);
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
		panel_1.setBounds(0, 70, 900, 10);
		panel.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 255, 0));
		panel_1_1.setBounds(0, 80, 900, 10);
		panel.add(panel_1_1);
		
		JLabel lblAuthorList = new JLabel("AUTHOR LIST");
		lblAuthorList.setIcon(new ImageIcon(DashboardAuthorList.class.getResource("/Images/icons8-document-writer-50.png")));
		lblAuthorList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorList.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAuthorList.setBounds(300, 10, 300, 50);
		panel.add(lblAuthorList);
		
		JLabel id = new JLabel("SEARCH AUTHOR:");
		id.setFont(new Font("Times New Roman", Font.BOLD, 16));
		id.setBounds(20, 100, 150, 30);
		contentPane.add(id);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(textField.getText().trim()));
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(20, 130, 200, 30);
		contentPane.add(textField);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(Color.GRAY);
		panel_1_2.setBounds(650, 190, 100, 100);
		contentPane.add(panel_1_2);
		
		imgTxt = new JLabel("");
		imgTxt.setHorizontalAlignment(SwingConstants.CENTER);
		imgTxt.setBounds(0, 0, 100, 100);
		panel_1_2.add(imgTxt);
		
		JLabel isbn = new JLabel("ISBN:");
		isbn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		isbn.setBounds(650, 305, 40, 20);
		contentPane.add(isbn);
		
		JLabel accessNum = new JLabel("Accession No.:");
		accessNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
		accessNum.setBounds(650, 335, 80, 20);
		contentPane.add(accessNum);
		
		JLabel title = new JLabel("Title:");
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(650, 365, 30, 20);
		contentPane.add(title);
		
		JLabel authorTxt = new JLabel("Author:");
		authorTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		authorTxt.setBounds(650, 395, 50, 20);
		contentPane.add(authorTxt);
		
		JLabel subjectTxt = new JLabel("Subject:");
		subjectTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		subjectTxt.setBounds(650, 425, 50, 20);
		contentPane.add(subjectTxt);
		
		JLabel publisherTxt = new JLabel("Publisher:");
		publisherTxt.setFont(new Font("Times New Roman", Font.BOLD, 12));
		publisherTxt.setBounds(650, 455, 60, 20);
		contentPane.add(publisherTxt);
		
		JLabel copyright = new JLabel("Copyright:");
		copyright.setFont(new Font("Times New Roman", Font.BOLD, 12));
		copyright.setBounds(650, 485, 60, 20);
		contentPane.add(copyright);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showTable("");
				textField.setText("");
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
		btnRefresh.setBounds(500, 130, 120, 30);
		contentPane.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 190, 620, 340);
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
						
						bookID.setText(String.valueOf(SelectedBook.getId()));
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
						
						if(issue.checkBookAvailability(id)) {
							
							availableYesNo.setText("YES");
							
						}else {
							
							availableYesNo.setText("NO");
							
						}
						
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
		isbnData.setBounds(690, 305, 150, 20);
		contentPane.add(isbnData);
		
		accessNumData = new JLabel("");
		accessNumData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		accessNumData.setBounds(735, 335, 100, 20);
		contentPane.add(accessNumData);
		
		titleData = new JLabel("");
		titleData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		titleData.setBounds(690, 365, 150, 20);
		contentPane.add(titleData);
		
		authorData = new JLabel("");
		authorData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		authorData.setBounds(700, 395, 150, 20);
		contentPane.add(authorData);
		
		subjectData = new JLabel("");
		subjectData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		subjectData.setBounds(700, 425, 150, 20);
		contentPane.add(subjectData);
		
		publisherData = new JLabel("");
		publisherData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		publisherData.setBounds(710, 455, 150, 20);
		contentPane.add(publisherData);
		
		copyrightData = new JLabel("");
		copyrightData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		copyrightData.setBounds(710, 485, 150, 20);
		contentPane.add(copyrightData);
		
		price = new JLabel("Price:");
		price.setFont(new Font("Times New Roman", Font.BOLD, 12));
		price.setBounds(650, 515, 40, 20);
		contentPane.add(price);
		
		priceData = new JLabel("");
		priceData.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		priceData.setBounds(690, 515, 150, 20);
		contentPane.add(priceData);
		
		JLabel available = new JLabel("Available:");
		available.setFont(new Font("Times New Roman", Font.BOLD, 12));
		available.setBounds(760, 190, 60, 20);
		contentPane.add(available);
		
		availableYesNo = new JLabel("YES OR NO");
		availableYesNo.setForeground(Color.BLUE);
		availableYesNo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		availableYesNo.setBounds(821, 190, 70, 20);
		contentPane.add(availableYesNo);
		
		bookID = new JLabel("ID:");
		bookID.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bookID.setBounds(650, 171, 40, 20);
		contentPane.add(bookID);
		
		showTable("");
		
	}
	
	public void showTable(String _book) {
		
		ArrayList<classes.Book> bookList = book.BookList(_book, _book);
		ArrayList<classes.Author> authorList = author.authorList();
		
		String[] colNames = {"ID", "ISBN", "ACCESSION NO.", "TITLE", "AID", "NAME"};
		
		Object[][] rows = new Object[bookList.size()][colNames.length];
		
		for(int i = 0; i < bookList.size(); i++) {
			
			rows[i][0] = bookList.get(i).getId();
			rows[i][1] = bookList.get(i).getIsbn();
			rows[i][2] = bookList.get(i).getAccessionNum();
			rows[i][3] = bookList.get(i).getTitle();
			rows[i][4] = bookList.get(i).getAuthorID();
			rows[i][5] = authorList.get(i).getFirstName()+ " " +authorList.get(i).getLastName();
			
		}
		
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		table.setModel(model);
		
	}
	
	private void filter(String query) {
		
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		
		tr.setRowFilter(RowFilter.regexFilter(query));
		
	}
}
