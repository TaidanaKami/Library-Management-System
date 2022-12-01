package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Admin {
	
	private int id;
	private String firstName;
	private String lastName;
	private String contact;
	private String address;
	private String email;
	private String username;
	private String password;
	
	public Admin() {};
	
	public Admin(int _id, String _firstName, String _lastName, String _contact, String _email, String _address, String _username, String _password) {
		
		this.id = _id;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.contact = _contact;
		this.email = _email;
		this.address = _address ;
		this.username = _username;
		this.password = _password ;
		
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addAdmin(String _firstName, String _lastName, String _contact, String _email, String _address, String _username, String _password) {
			
			String addQuery = "INSERT INTO `admin`(`firstName`, `lastName`, `contactNum`, `email`, `address`, `username`, `password`) VALUES (?,?,?,?,?,?,?)";
			try {
				
				PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
				
				ps.setString(1, _firstName);
				ps.setString(2, _lastName);
				ps.setString(3, _contact);
				ps.setString(4, _email);
				ps.setString(5, _address);
				ps.setString(6, _username);
				ps.setString(7, _password);
				
				if(ps.executeUpdate() != 0) {
					
					JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
					
				}
				
			} catch (SQLException ex) {
				
				Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			
		}
	
	public void editAdmin(int _id, String _firstName, String _lastName, String _contact, String _email, String _address, String _username, String _password) {
		
		String addQuery = "UPDATE `admin` SET `firstName`=?,`lastName`=?,`contactNum`=?,`email`=?,`address`=?,`username`=?,`password`=? WHERE `id`=?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _firstName);
			ps.setString(2, _lastName);
			ps.setString(3, _contact);
			ps.setString(4, _email);
			ps.setString(5, _address);
			ps.setString(6, _username);
			ps.setString(7, _password);
			ps.setInt(8, _id);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "UPDATE SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void deleteAdmin (int _id) {
		
		String deleteQuery = "DELETE FROM `admin` WHERE `id` = ?";
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
	
	public Admin getAdminById(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `admin` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8));
			
		}else {
			
			return null;
			
		}
					
	}
	
	public ArrayList<Admin> adminList() {
		
		ArrayList<Admin> list = new ArrayList<>();
		String selectQuery = "SELECT * FROM `admin`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Admin admin;
			
			while(rs.next()) {
				
				admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(admin);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}

}
