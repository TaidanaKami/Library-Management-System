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
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class AuthorList extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JLabel emptyField2;
	private JLabel emptyField1;
	private JLabel emptyField3;
	classes.Author author = new classes.Author();
	public String formType = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthorList frame = new AuthorList();
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
	public AuthorList() {
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
		
		JLabel lblAuthorList = new JLabel("AUTHOR LIST");
		lblAuthorList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorList.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAuthorList.setBounds(200, 10, 300, 50);
		panel.add(lblAuthorList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 695, 450);
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
				
				int index = table.getSelectedRow();
				
				String id = table.getValueAt(index, 0).toString();
				String fname = table.getValueAt(index, 1).toString();
				String lname = table.getValueAt(index, 2).toString();
				String expert = table.getValueAt(index, 3).toString();
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton selectBtn = new JButton("SELECT");
		selectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String firstName = table.getValueAt(index, 1).toString();
				String lastName = table.getValueAt(index, 2).toString();
				
				String fullName = firstName + " " + lastName;
				
				if(formType.equals("edit")) 
				Home.displayAuthorData(id, fullName);
					
				else 
				
				Home.displayAuthorData(id, fullName);
				
				dispose();
				
			}
		});
		selectBtn.setBounds(250, 560, 200, 25);
		contentPane.add(selectBtn);
		showTable();
		
	}
	
	public void showTable() {
		
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
		table.setModel(model);
		
	}
}
