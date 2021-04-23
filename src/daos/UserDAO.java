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
import models.Song;
import models.User;
import models.UserRole;
import utils.ConnectDBUtil;

public class UserDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<User> getUser() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT u.*, r.role AS rolename FROM users AS u INNER JOIN user_role AS r ON u.id_role = r.id ORDER BY u.id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				int id_role = rs.getInt("id_role");
				String rolename = rs.getString("rolename");
				User user = new User(id, username, password, fullname, new UserRole(id_role, rolename));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return users;
	}

	public int add(User user) {
		String sql = "INSERT INTO users(username, password, fullname, id_role) VALUES(?, ?, ?, ?)";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullName());
			pst.setInt(4, user.getUserRole().getId());
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
		String sql = "DELETE FROM users WHERE id = ?";
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

	public User getById(int id) {
		User users = null;
		String sql = "SELECT  u.*, r.role AS rolename FROM users AS u INNER JOIN user_role AS r ON u.id_role = r.id WHERE u.id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				users = new User(id, rs.getString("username"), rs.getString("password"), rs.getString("fullname"), new UserRole(rs.getInt("id_role"),rs.getString("rolename")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return users;
	}

	public int edit(User user) {
		String sql = "UPDATE users SET username = ?, password = ?, fullname = ?, id_role = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullName());
			pst.setInt(4, user.getUserRole().getId());
			pst.setInt(5, user.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return result;
	}

	public User findByUserAndPassword(String username, String password) {
		User users = null;
		String sql = "SELECT id, username, password, fullname, id_role FROM users WHERE username = ? AND password = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				users = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"), new UserRole(rs.getInt("id_role")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return users;
	}

	public int numerOfItems() {
		String sql = "SELECT COUNT(*) AS count FROM users";
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

	public List<User> getAllPagination(int offset) {
		List<User> listuser = new ArrayList<>();
		String sql = "SELECT u.*, r.role AS rolename FROM users AS u INNER JOIN user_role AS r ON u.id_role = r.id LIMIT ?, ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, GlobalConstants.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				int id_role = rs.getInt("id_role");
				String rolename = rs.getString("rolename");
				User user = new User(id, username, password, fullname, new UserRole(id_role, rolename));
				listuser.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return listuser;
	}

}
