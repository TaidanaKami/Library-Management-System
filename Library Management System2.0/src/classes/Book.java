package classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Book {
	
	private int id;
	private String isbn;
	private String accessionNum;
	private String title;
	private Integer authorID;
	private Integer subjectID;
	private Integer publisherID;
	private Integer quantity;
	private String copyright;
	private double price;
	private byte[] image;
	
	Function func = new Function();
	
	public Book() {};
	
	public Book(int _id, String _isbn, String _accessionNum, String _title, Integer _authorID, Integer _subjectID, Integer _publisherID, 
			Integer _quantity, String _copyright, double _price, byte[] _image) {
		
		this.id = _id;
		this.isbn = _isbn;
		this.accessionNum = _accessionNum;
		this.title = _title;
		this.authorID = _authorID;
		this.subjectID = _subjectID;
		this.publisherID = _publisherID;
		this.quantity = _quantity;
		this.copyright = _copyright;
		this.price = _price;
		this.image = _image;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAccessionNum() {
		return accessionNum;
	}

	public void setAccessionNum(String accessionNum) {
		this.accessionNum = accessionNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	public Integer getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(Integer publisherID) {
		this.publisherID = publisherID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

public void addBook(String _isbn, String _accessionNum, String _title, Integer _authorID, Integer _subjectID, Integer _publisherID, 
		Integer _quantity, String _copyright, double _price, byte[] _image) {
		
		String addQuery = "INSERT INTO `book`(`isbn`, `accessionNum`, `title`, `authorID`, `subjectID`, `publisherID`, `quantity`, `copyright`, `price`, `image`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			
			PreparedStatement ps = database.getConnection().prepareStatement(addQuery);
			
			ps.setString(1, _isbn);
			ps.setString(2, _accessionNum);
			ps.setString(3, _title);
			ps.setInt(4, _authorID);
			ps.setInt(5, _subjectID);
			ps.setInt(6, _publisherID);
			ps.setInt(7, _quantity);
			ps.setString(8, _copyright);
			ps.setDouble(9, _price);
			ps.setBytes(10, _image);
			
			if(ps.executeUpdate() != 0) {
				
				JOptionPane.showMessageDialog(null, "ADDED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "ADD FAILED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void editBook(int _id, String _isbn, String _accessionNum, String _title, Integer _authorID, Integer _subjectID, Integer _publisherID, 
			Integer _quantity, String _copyright, double _price, byte[] _image) {
		
		String editQuery;
		PreparedStatement ps;
		
		try {
			
			if(_image != null){
				
				editQuery = "UPDATE `book` SET `isbn`=?,`accessionNum`=?,`title`=?,`authorID`=?,`subjectID`=?,`publisherID`=?,`quantity`=?,`copyright`=?,`price`=?,`image`=? WHERE `id` = ?";
				
				ps = database.getConnection().prepareStatement(editQuery);
				
				ps.setString(1, _isbn);
				ps.setString(2, _accessionNum);
				ps.setString(3, _title);
				ps.setInt(4, _authorID);
				ps.setInt(5, _subjectID);
				ps.setInt(6, _publisherID);
				ps.setInt(7, _quantity);
				ps.setString(8, _copyright);
				ps.setDouble(9, _price);
				ps.setBytes(10, _image);
				ps.setInt(11, _id);		
				
			}else {
				
				editQuery = "UPDATE `book` SET `isbn`=?,`accessionNum`=?,`title`=?,`authorID`=?,`subjectID`=?,`publisherID`=?,`quantity`=?,`copyright`=?,`price`=? WHERE `id` = ?";
				
				ps = database.getConnection().prepareStatement(editQuery);
				
				ps.setString(1, _isbn);
				ps.setString(2, _accessionNum);
				ps.setString(3, _title);
				ps.setInt(4, _authorID);
				ps.setInt(5, _subjectID);
				ps.setInt(6, _publisherID);
				ps.setInt(7, _quantity);
				ps.setString(8, _copyright);
				ps.setDouble(9, _price);
				//ps.setBytes(10, _image);
				ps.setInt(11, _id);		
								
			}
			
			if(ps.executeUpdate() != 0) {
			
				JOptionPane.showMessageDialog(null, "BOOK EDITED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "BOOK NOT EDITED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void deleteBook(int _id) {
		
		String deleteQuery = "DELETE FROM `book` WHERE `id` = ?";
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
	
	public ArrayList<Book> bookList() {
		
		ArrayList<Book> List = new ArrayList<>();
		String selectQuery = "SELECT * FROM `book`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Book book;
			
			while(rs.next()) {
				
				book = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("accessionNum"), rs.getString("title"), rs.getInt("authorID"), 
						rs.getInt("subjectID"), rs.getInt("publisherID"), rs.getInt("quantity"), rs.getString("copyright"), rs.getDouble("price"), 
						rs.getBytes("image"));
				List.add(book);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return List;
		
	}
	
	public ArrayList<Book> booksList(String query) {
		
		ArrayList<Book> list = new ArrayList<>();
		
		classes.Function func = new classes.Function();
		
		try {
			
			if(query.equals("")) {
				
				query = "SELECT * FROM `book`";
				
			}
			
			ResultSet rs = func.getData(query);
			
			Book books;
			
			while(rs.next()) {
				
				books = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("accessionNum"), rs.getString("title"), rs.getInt("authorID"), 
						rs.getInt("subjectID"), rs.getInt("publisherID"), rs.getInt("quantity"), rs.getString("copyright"), rs.getDouble("price"), 
						rs.getBytes("image"));
				list.add(books);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public ArrayList<Book> bookList1() {
		
		ArrayList<Book> list = new ArrayList<>();
		String selectQuery = "SELECT * FROM `book`";
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			
			ps = database.getConnection().prepareStatement(selectQuery);
			rs = ps.executeQuery();
			
			Book books;
			
			while(rs.next()) {
				
				books = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("accessionNum"), rs.getString("title"), rs.getInt("authorID"), 
						rs.getInt("subjectID"), rs.getInt("publisherID"), rs.getInt("quantity"), rs.getString("copyright"), rs.getDouble("price"), 
						rs.getBytes("image"));
				list.add(books);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public boolean titleSearch(String _title) {
		
		String query = "SELECT * FROM `book` WHERE `title` LIKE '%" + _title + "%'";
		Function func = new Function();
		ResultSet rs = func.getData(query);
		
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
	
	public boolean isISBNExist(String _id) {
		
		String query = "SELECT * FROM `book` WHERE isbn = '" + _id + "'";
		Function func = new Function();
		ResultSet rs = func.getData(query);
		
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
	
	public Book searchBookByID_ISBN(int _id, String _isbn) {
		
		String query = "SELECT * FROM `book` WHERE `id` = " +_id+ " or `isbn` = '" +_isbn +"'";
		Function func = new Function();
		
		ResultSet rs = func.getData(query);
		Book book = null;
		
		try {
			
			if(rs.next()){

				book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
				
			}else {
				return book;
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return book;
		
		
	}
	
	public Book searchBookByID_ISBN_ACCESSIONNUM(int _id, String _isbn, String _accessionNum) {
		
		String query = "SELECT * FROM `book` WHERE `id` = " +_id+ " or `isbn` = " +_isbn +" or `accessionNum` = " +_accessionNum +"";
		Function func = new Function();
		
		ResultSet rs = func.getData(query);
		Book book = null;
		
		try {
			
			if(rs.next()){

				book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
				
			}else {
				return book;
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return book;
		
		
	}
	
	public Book getBookByID(Integer _id) {
		
		String query = "SELECT * FROM `book` WHERE `id` = " +_id+ "";
		Function func = new Function();
		
		ResultSet rs = func.getData(query);
		Book book = null;
		
		try {
			
			if(rs.next()){

				book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
				
			}else {
				return null;
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return book;
		
		
	}
	
	public Book getBookId(Integer _id) throws SQLException {
		
		Function func = new Function();
		
		String editQuery = "SELECT * FROM `book` WHERE `id` = "+ _id;
		
		ResultSet rs = func.getData(editQuery);
		
		if(rs.next()) {
			
			return new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
					rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
			
		}else {
			
			return null;
			
		}
					
	}
	
	public Book totalBooks() {
		
		String query = "SELECT SUM(quantity) FROM book;";
		Function func = new Function();
		
		ResultSet rs = func.getData(query);
		Book book = null;
		
		try {
			
			if(rs.next()){

				book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
				
			}else {
				return book;
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
		return book;
		
		
	}
	
	public void displayBookCover(JLabel[] covers) {
		
		ResultSet rs;
		Statement st;
		
		try {
			
			st = database.getConnection().createStatement();
			rs = st.executeQuery("SELECT `image` FROM `book` LIMIT 3");
			byte[] image;
			int i = 0;
			while(rs.next()) {
				
				image = rs.getBytes("image");
				func.displayImage(covers[i].getWidth(), covers[i].getHeight(), image, title, covers[i]);
				i++;
				
			}
			
		}catch(SQLException ex) {
			
			Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public ArrayList<Book> BookList(String title, String date) {
		
		ArrayList<Book> list = new ArrayList<>();
		String query = "";
		
		if(title.equals("") && date.equals("")) {
			
			query = "SELECT * FROM `book`";
			
		}else if(date.equals(date)){
			
			query = "SELECT * FROM `book` WHERE `copyright` = '" + date + "'";
			
		}else {
			
			query = "SELECT * FROM `book` WHERE `title` = '" + title + "'";
			
		}
		
		try {
			
			ResultSet rs = func.getData(query);
			
			Book book;
			
			while(rs.next()) {
				
				book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getDouble(10), rs.getBytes(11));
				list.add(book);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return list;
		
	}
	
	public void setQuantity_Minus_One(int _id, int _quantity) {
		
		String quantityQuery;
		PreparedStatement ps;
		
		try {
			
			quantityQuery = "UPDATE `book` SET `quantity`=? WHERE `id` = ?";
			
			ps = database.getConnection().prepareStatement(quantityQuery);
			
			
			ps.setInt(1, _quantity);
			ps.setInt(2, _id);	
			
			if(ps.executeUpdate() != 0) {
			
				JOptionPane.showMessageDialog(null, "BOOK BORROWED SUCCESFULLY","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "BOOK NOT BORROWED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void setQuantity_Add(int _id, int _quantity) {
		
		String quantityQuery;
		PreparedStatement ps;
		
		try {
			
			quantityQuery = "UPDATE `book` SET `quantity`=? WHERE `id` = ?";
			
			ps = database.getConnection().prepareStatement(quantityQuery);
			
			
			ps.setInt(1, _quantity);
			ps.setInt(2, _id);	
			
			if(ps.executeUpdate() != 0) {
			
				JOptionPane.showMessageDialog(null, "BOOK HAS BEEN RETUREND","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "BOOK NOT RETURNED","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}
	
	public void bookLostPayment(int _id, double _price) {
		
		String quantityQuery;
		PreparedStatement ps;
		
		try {
			
			quantityQuery = "UPDATE `book` SET `price`=? WHERE `id` = ?";
			
			ps = database.getConnection().prepareStatement(quantityQuery);
			
			
			ps.setDouble(1, _price);
			ps.setInt(2, _id);	
			
			if(ps.executeUpdate() != 0) {
			
				JOptionPane.showMessageDialog(null, "BOOK HAS BEEN LOST","MESSAGE", 1);
				
			}else {
				
				JOptionPane.showMessageDialog(null, "BOOK NOT FOUND","MESSAGE", 2);
				
			}
			
		} catch (SQLException ex) {
			
			Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		
	}

}


