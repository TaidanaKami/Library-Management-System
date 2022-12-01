package classes;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Function {
	
	public void displayImage(int width, int height, byte[] imagebyte, String imagePath, JLabel bookImage) {
		
		ImageIcon imgIcon;
		
		if(imagebyte != null) {
			
			imgIcon = new ImageIcon(imagebyte);
			
		}else {
						
			try {
				
				imgIcon = new ImageIcon(getClass().getResource(imagePath));
				
			}catch(Exception e) {
				
				imgIcon = new ImageIcon(imagePath);
				
			}
			
		}
						
		Image img = imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		bookImage.setIcon(new ImageIcon(img));
		
	}
	    
	public String selectImage() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Profile Picture");
		fileChooser.setCurrentDirectory(new File("C:\\Users\\Gian\\Pictures\\Profiles"));
		
		FileNameExtensionFilter extensionFiter = new FileNameExtensionFilter("Image", "png", "jpg", "jpeg");
		fileChooser.addChoosableFileFilter(extensionFiter);
		
		int fileState = fileChooser.showSaveDialog(null);
		String path = "";
		
		if(fileState == JFileChooser.APPROVE_OPTION) {
			
			path = fileChooser.getSelectedFile().getAbsolutePath();
			
			
		}
		return path;
		
	}
	
	public ResultSet getData(String query) {
		
		PreparedStatement ps;
		ResultSet rs = null;
		
		try {
			
			ps = database.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
		}catch(SQLException ex) {
			
			Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return rs;
		
	}
	
	public int countData(String tableName) {
		
		int total = 0;
		ResultSet rs;
		Statement st;
		
		try {
			
			st = database.getConnection().createStatement();
			rs = st.executeQuery("SELECT COUNT(*) as total FROM "+ tableName +"");
			if(rs.next()) {
				
				total = rs.getInt("total");
				
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return total;
		
	}
	
	public int countBook(String tableName) {
		
		int total = 0;
		ResultSet rs;
		Statement st;
		
		try {
			
			st = database.getConnection().createStatement();
			rs = st.executeQuery("SELECT SUM(quantity) as total FROM "+ tableName +"");
			if(rs.next()) {
				
				total = rs.getInt("total");
				
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return total;
		
	}

}
