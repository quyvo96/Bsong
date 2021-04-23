package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constants.GlobalConstants;
import models.Category;
import models.Contact;
import models.User;
import models.UserRole;
import utils.ConnectDBUtil;

public class ContactDAO {

	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<Contact> getContact() {
		Contact contact = new Contact();
		List<Contact> contacts = new ArrayList<>();
		String sql = "SELECT id, name, email, message, website FROM contacts ORDER BY id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				contacts.add(contact);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return contacts;
	}
	
	public int submit(Contact contact) {
		String sql = "INSERT INTO contacts(name, email, website, message) VALUES(?, ?, ?, ?)";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getWebsite());
			pst.setString(4, contact.getMessage());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return result;
	}
	public int del(int id) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return result;
	}

	public int numerOfItems() {
		String sql = "SELECT COUNT(*) AS count FROM contacts";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return 0;
	}

	public List<Contact> getAllPagination(int offset) {
		Contact contact = new Contact();
		List<Contact> contacts = new ArrayList<>();
		String sql = "SELECT id, name, email, message, website FROM contacts ORDER BY id DESC LIMIT ?, ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, GlobalConstants.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				contacts.add(contact);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return contacts;
	}
}
