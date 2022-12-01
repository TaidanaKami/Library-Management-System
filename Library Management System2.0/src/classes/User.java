package classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String studentNum;
	private String contact;
	private String email;
	private String address;
	private String yearlvl;
	private String course;
	private double payment;
	
	public User() {};
	
	public User(int _id, String _firstName, String _lastName, String _studentNum, String _cotactNum, String _email, String _address
			,String _yearlvl, String _course, double _payment) {
		
		this.id = _id;
		this.firstName = _firstName;
		this.lastName = _lastName;
		this.studentNum = _studentNum;
		this.contact = _cotactNum;
		this.email = _email;
		this.address = _address ;
		this.yearlvl = _yearlvl;
		this.course = _course;
		this.payment = _payment;
		
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

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getYearlvl() {
		return yearlvl;
	}

	public void setYearlvl(String yearlvl) {
		this.yearlvl = yearlvl;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public void addUser(String _firstName, String _lastName, String _studentNum, String _cotactNum, String _email, String _address
			,String _yearlvl, String _course, double _payment) {
			
			String addQuery = "INSERT INTO `user`(`firstName`, `lastName`, `studentNum`, `contact`, `email`, `address`, `yearlvl`, `course`, `payment`) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				
				PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
				
				ps.setString(1, _firstName);
				ps.setString(2, _lastName);
				ps.setString(3, _studentNum);
				ps.setString(4, _cotactNum);
				ps.setString(5, _email);
				ps.setString(6, _address);
				ps.setString(7, _yearlvl);
				ps.setString(8, _course);
				ps.setDouble(9, _payment);
				
				if(ps.executeUpdate() != 0) {
					
					JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
					
				}
				
			} catch (SQLException ex) {
				
				Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			
		}
	
		public User getUserById(Integer _id) throws SQLException {
			
			Function func = new Function();
			
			String editQuery = "SELECT * FROM `user` WHERE `id` = "+ _id;
			
			ResultSet rs = func.getData(editQuery);
			
			if(rs.next()) {
				
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10));
				
			}else {
				
				return null;
				
			}
						
		}
		
		public User getUserData() throws SQLException {
			
			Function func = new Function();
			
			String editQuery = "SELECT * FROM `user` WHERE 1";
			
			ResultSet rs = func.getData(editQuery);
			
			if(rs.next()) {
				
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10));
				
			}else {
				
				return null;
				
			}
						
		}
		
		public User getUserByLibraryNum(String _libraryNum) throws SQLException {
			
			Function func = new Function();
			
			String editQuery = "SELECT * FROM `user` WHERE `libraryNum` = "+ _libraryNum;
			
			ResultSet rs = func.getData(editQuery);
			
			if(rs.next()) {
				
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10));
				
			}else {
				
				return null;
				
			}
						
		}
		
		public void editUser(int _id, String _firstName, String _lastName, String _studentNum, String _cotactNum, String _email, String _address
				,String _yearlvl, String _course, double _payment) {
			
			String editQuery = "UPDATE `user` SET `firstName`=?,`lastName`=?,`studentNum`=?,`contact`=?,`email`=?,`address`=?,`yearlvl`=?,`course`=?, `payment`=? WHERE `id` = ?";
			try {
				
				PreparedStatement ps = database.getConnection().prepareStatement(editQuery);
				
				ps.setString(1, _firstName);
				ps.setString(2, _lastName);
				ps.setString(3, _studentNum);
				ps.setString(4, _cotactNum);
				ps.setString(5, _email);
				ps.setString(6, _address);
				ps.setString(7, _yearlvl);
				ps.setString(8, _course);
				ps.setDouble(9, _payment);
				ps.setInt(10, _id);
				
				if(ps.executeUpdate() != 0) {
					
					JOptionPane.showMessageDialog(null, "UPDATED SUCCESFULLY","MESSAGE", 1);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
					
				}
				
			} catch (SQLException ex) {
				
				Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			
		}
		
		public void updateUser(int _id, double _payment) {
			
			String editQuery = "UPDATE `user` SET `payment`=? WHERE `id` = ?";
			try {
				
				PreparedStatement ps = database.getConnection().prepareStatement(editQuery);
				
				ps.setDouble(1, _payment);
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
		
		public void deleteUser(int _id) {
			
			String deleteQuery = "DELETE FROM `user` WHERE `id` = ?";
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
		
		public ArrayList<User> userList() {
			
			ArrayList<User> list = new ArrayList<>();
			String selectQuery = "SELECT * FROM `user`";
			
			ResultSet rs;
			PreparedStatement ps;
			
			try {
				
				ps = database.getConnection().prepareStatement(selectQuery);
				rs = ps.executeQuery();
				
				User user;
				
				while(rs.next()) {
					
					user = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("studentNum")
							, rs.getString("contact"), rs.getString("email"), rs.getString("address"), rs.getString("yearlvl"), rs.getString("course")
							, rs.getDouble("payment"));
					list.add(user);
					
				}
				
			} catch (SQLException ex) {
				
				Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			return list;
			
		}
		
		public void setPenalty(int _id, double _payment) {
			
			String quantityQuery;
			PreparedStatement ps;
			
			try {
				
				quantityQuery = "UPDATE `user` SET `payment`=? WHERE `id` = ?";
				
				ps = database.getConnection().prepareStatement(quantityQuery);
				
				
				ps.setDouble(1, _payment);
				ps.setInt(2, _id);	
				
				if(ps.executeUpdate() != 0) {
				
					JOptionPane.showMessageDialog(null, "PENALTY","MESSAGE", 1);
					
				}else {
					
					JOptionPane.showMessageDialog(null, "NO PENALTY","MESSAGE", 2);
					
				}
				
			} catch (SQLException ex) {
				
				Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
				
			}
			
		}
	
}
