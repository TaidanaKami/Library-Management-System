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

public class PublisherList extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JLabel emptyField2;
	private JLabel emptyField1;
	private JLabel emptyField3;
	classes.Publisher publisher = new classes.Publisher();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublisherList frame = new PublisherList();
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
	public PublisherList() {
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
		
		JLabel lblAuthorList = new JLabel("PUBLISHER LIST");
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
				String name = table.getValueAt(index, 1).toString();
				String city = table.getValueAt(index, 2).toString();
				String street = table.getValueAt(index, 3).toString();
				String zipcode = table.getValueAt(index, 4).toString();
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton selectBtn = new JButton("SELECT");
		selectBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				
				Home.displayPublisherData(id, name);
				
				dispose();
				
			}
		});
		selectBtn.setBounds(250, 560, 200, 25);
		contentPane.add(selectBtn);
		showTable();
		
	}
	
	public void showTable() {
		
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
		table.setModel(model);
		
	}
}
