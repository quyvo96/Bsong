package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Song;
import utils.ConnectDBUtil;

public class CategoryDAO {
	
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;
	
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT id, name FROM categories ORDER BY id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Category category = new Category(id, name);
				categories.add(category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return categories;
	}
	public int add(Category cat) {
		String sql = "INSERT INTO categories(name) VALUES(?)";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
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
		String sql = "DELETE FROM categories WHERE id = ?";
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
	public Category getById(int id) {
		Category categories = null;
		String sql = "SELECT id, name FROM categories WHERE id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				categories = new Category(rs.getInt("id"), rs.getString("name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return categories;
	}
	public int edit(Category cat) {
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, cat.getName());
			pst.setInt(2, cat.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return result;
	}
public static void main(String[] args) {
	CategoryDAO categoryDAO = new CategoryDAO();
	List<Category> categories = categoryDAO.getCategories();
	for(Category c:categories) {
		System.out.println(c);
	}
}
}
