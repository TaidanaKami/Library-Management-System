package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Issue {
	
	private int id;
	private int userID;
	private int bookID;
	private String issueDate;
	private String returnDate;
	private String status;
	
	Book book = new Book();
	Function func = new Function();
	
	public Issue() {};
	
	public Issue(int _id, int _userID, int _bookID, String _issueDate, String _returnDate, String _status) {
		
		this.id = _id;
		this.userID = _userID;
		this.bookID = _bookID;
		this.issueDate = _issueDate;
		this.returnDate = _returnDate;
		this.status = _status;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addIssue(int _userID, int _bookID, String _issueDate, String _returnDate, String _status) {
		
		String addQuery = "INSERT INTO `issue`(`userID`, `bookID`, `issueDate`, `returnDate`, `status`) VALUES (?,?,?,?,?)";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setInt(1, _userID);
			ps.setInt(2, _bookID);
			ps.setString(3, _issueDate);
			ps.setString(4, _returnDate);
			ps.setString(5, _status);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "BOOK ISSUED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "BOOK ISSUED FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void updateIssue(int _id, int _userID, int _bookID, String _issueDate, String _returnDate, String _status) {
		
		String addQuery = "UPDATE `issue` SET `issueDate`=?,`returnDate`=?,`status`=? WHERE `id`=? AND `userID`=? AND `bookID`=?";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _issueDate);
			ps.setString(2, _returnDate);
			ps.setString(3, _status);
			ps.setInt(4, _id);
			ps.setInt(5, _userID);
			ps.setInt(6, _bookID);
			
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "STATUS UPDATED","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "UPDATE FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public boolean checkBookAvailability(int _bookID){
		
		boolean availability = false;
		
		try {
			
			Book selectedBook = book.getBookId(_bookID);
			int quantity = selectedBook.getQuantity();
			int issuedBookCount = countData(_bookID);
			
			if(quantity > issuedBookCount) {
				
				availability = true;
				
			}else {
				
				availability = false;
				
			}
			
		} catch (SQLException e) {
			
			Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, e);
			
		}
		
		return availability;
			
	}
	
	public int countData(int _bookID) {
		
		int total = 0;
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement("SELECT COUNT(*) as total FROM `issue` WHERE `bookID` = ? AND `status` = 'issued'");
			ps.setInt(1, _bookID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				total = rs.getInt("total");
				
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return total;
		
	}
	
	public ArrayList<Issue> issuedBookList(String status) {
		
		ArrayList<Issue> list = new ArrayList<>();
		String query = "";
		
		if(status.equals("")) {
			
			query = "SELECT * FROM `issue`";
			
		}else {
			
			query = "SELECT * FROM `issue` WHERE `status` = '" + status + "'";
			
		}
		
		try {
			
			ResultSet rs = func.getData(query);
			
			Issue issuedBook;
			
			while(rs.next()) {
				
				issuedBook = new Issue(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(issuedBook);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public ArrayList<Issue> issueList() {
		
		ArrayList<Issue> list = new ArrayList<>();
		String selectQuery = "SELECT * FROM `issue`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Issue issue;
			
			while(rs.next()) {
				
				issue = new Issue(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				list.add(issue);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public void setPenaltyCost(int _id, double _payment) {
		
		String quantityQuery;
		PreparedStatement ps;
		
		try {
			
			quantityQuery = "UPDATE `issue` SET `payment`=? WHERE `id` = ?";
			
			ps = database.getConnection().prepareStatement(quantityQuery);
			
			
			ps.setDouble(1, _payment);
			ps.setInt(2, _id);	
			
			if(ps.executeUpdate() != 0) {
			
				JOptionPane.showMessageDialog(null, "BOOK DUE DATE","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "NO PENALTY","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}

	}
	
	public Issue getIssueId(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `issue` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Issue(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
			
		}else {
			
			return null;
			
		}
					
	}
		
}
