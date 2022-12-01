package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class Author {
	
	private int id;
	private String firstName;
	private String lastName;
	private String expertise;
	
	public Author() {};
	
	public Author(int _id, String _firstName, String _lastName, String _expertise) {
		
		this.id = _id;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.expertise = _expertise;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	public void addAuthor(String _firstName, String _lastName, String _expertise) {
		
		String addQuery = "INSERT INTO `author`(`firstName`, `lastName`, `expertise`) VALUES (?,?,?)";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _firstName);
			ps.setString(2, _lastName);
			ps.setString(3, _expertise);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void editAuthor(int _id, String _firstName, String _lastName, String _expertise) {
		
		String editQuery = "UPDATE `author` SET `firstName`=?,`lastName`=?,`expertise`=? WHERE `id` = ?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(editQuery);
			
			ps.setString(1, _firstName);
			ps.setString(2, _lastName);
			ps.setString(3, _expertise);
			ps.setInt(4, _id);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "UPDATED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void deleteAuthor(int _id) {
		
		String deleteQuery = "DELETE FROM `author` WHERE `id` = ?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(deleteQuery);
			
			ps.setInt(1, _id);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "DELETED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "DELETION FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public boolean findAuthor(String _id) {
		
		String findQuery = "SELECT * FROM `author` WHERE `id`=?";
		Function func = new Function();
		ResultSet rs = func.getData(findQuery);
		try {
			
			if(rs.next()){
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return true;
		
	}
	
	public Author getAuthorById(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `author` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Author(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
		}else {
			
			return null;
			
		}
					
	}
	
	public ArrayList<Author> authorList() {
		
		ArrayList<Author> aList = new ArrayList<>();
		String selectQuery = "SELECT * FROM `author`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Author author;
			
			while(rs.next()) {
				
				author = new Author(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("expertise"));
				aList.add(author);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return aList;
		
	}

}
