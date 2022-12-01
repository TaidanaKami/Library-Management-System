package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Subject {
	
	private int id;
	private String name;
	
	public Subject() {};
	
	public Subject(int _id, String _name) {
		
		this.id = _id;
		this.name = _name;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int _id) {
		this.id = _id;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	
	classes.Function func = new classes.Function();	
	
	public void addSubject(String _name) {
		
		String addQuery = "INSERT INTO `subject`(`name`) VALUES (?)";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _name);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void editSubject(int _id, String _name) {
		
		String editQuery = "UPDATE `subject` SET `name` = ? WHERE `id` = ?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(editQuery);
			
			ps.setString(1, _name);
			ps.setInt(2, _id);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "UPDATED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void deleteSubject(int _id) {
		
		String deleteQuery = "DELETE FROM `subject` WHERE `id` = ?";
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
	
	public Subject getSubjectById(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `subject` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Subject(rs.getInt(1), rs.getString(2));
			
		}else {
			
			return null;
			
		}
					
	}
	
	public ArrayList<Subject> genreList() {
		
		ArrayList<Subject> gList = new ArrayList<>();
		String selectQuery = "SELECT * FROM `subject`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Subject genre;
			
			while(rs.next()) {
				
				genre = new Subject(rs.getInt("id"), rs.getString("name"));
				gList.add(genre);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return gList;
		
	}
	
	public ArrayList<Subject> subjectList(String name) {
		
		ArrayList<Subject> list = new ArrayList<>();
		
		if(name.equals("")) {
			
			name = "SELECT * FROM `subject`";
			
		}else {
			
			name = "SELECT * FROM `subject` WHERE `name`= '"+ name +"'";
			
		}
		
		try {
			
			ResultSet rs = func.getData(name);
			
			Subject subject;
			
			while(rs.next()) {
				
				subject = new Subject(rs.getInt(1), rs.getString(2));
				list.add(subject);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public HashMap<String, Integer> getSubjectMap(){
		
		HashMap<String, Integer> map = new HashMap();
		String selectQuery = "SELECT * FROM `subject`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Subject subject;
			
			while(rs.next()) {
				
				subject = new Subject(rs.getInt(1), rs.getString(2));
				map.put(subject.getName(), subject.getId());
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return map;
		
	}

}
