package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class Publisher {
	
	private int id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	public Publisher() {};
	
	public Publisher(int _id, String _name, String _city, String _street, String _zipcode) {
		
		this.id = _id;
		this.name = _name;
		this.city = _city;
		this.street = _street;
		this.zipcode = _zipcode;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void addPublisher(String _name, String _city, String _street, String _zipcode) {
		
		String addQuery = "INSERT INTO `publisher`(`name`, `city`, `street`, `zipcode`) VALUES (?,?,?,?)";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _name);
			ps.setString(2, _city);
			ps.setString(3, _street);
			ps.setString(4, _zipcode);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void editPublisher(int _id, String _name, String _city, String _street, String _zipcode) {
		
		String editQuery = "UPDATE `publisher` SET `name`=?,`city`=?,`street`=?,`zipcode`=? WHERE `id` = ?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(editQuery);
			
			ps.setString(1, _name);
			ps.setString(2, _city);
			ps.setString(3, _street);
			ps.setString(4, _zipcode);
			ps.setInt(5, _id);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "UPDATED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void deletePublisher(int _id) {
		
		String deleteQuery = "DELETE FROM `publisher` WHERE `id` = ?";
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
	
	public Publisher getPublisherById(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `publisher` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Publisher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			
		}else {
			
			return null;
			
		}
					
	}
	
	public ArrayList<Publisher> publisherList() {
		
		ArrayList<Publisher> aList = new ArrayList<>();
		String selectQuery = "SELECT * FROM `publisher`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Publisher publisher;
			
			while(rs.next()) {
				
				publisher = new Publisher(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("street"), rs.getString("zipcode"));
				aList.add(publisher);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return aList;
		
	}

}
