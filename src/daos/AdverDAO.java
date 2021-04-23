package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constants.GlobalConstants;
import models.Adver;
import models.Category;
import models.Song;
import utils.ConnectDBUtil;

public class AdverDAO {
	
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<Adver> getAll() {
		List<Adver> listadver = new ArrayList<>();
		String sql = "SELECT * FROM adver  ORDER BY id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Adver adver = new Adver(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("picture"),
							rs.getString("web"),
							rs.getInt("count"));
				listadver.add(adver);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return listadver;
	}
	public int add(Adver adver) {
		String sql = "INSERT INTO adver(name, picture, web) VALUES(?, ?, ?)";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, adver.getName());
			pst.setString(2, adver.getPicture());
			pst.setString(3, adver.getWeb());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);
		}
		return result;
	}
	public Adver getById(int id) {
		Adver adver = null;
		String sql = "SELECT * FROM adver  Where id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				adver = new Adver(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("picture"),
								rs.getString("web"),
								rs.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return adver;
	}
	
	public int edit(Adver adver) {
		String sql = "UPDATE adver SET name = ?, picture = ?,web = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, adver.getName());
			pst.setString(2, adver.getPicture());
			pst.setString(3, adver.getWeb());
			pst.setInt(4, adver.getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return result;
	}
	
	public int del(int id) {
		String sql = "DELETE FROM adver WHERE id = ?";
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
			ConnectDBUtil.close(pst, rs, conn);

		}
		return result;
	}
	public int addCouter(int id, int count) {
		String sql = "UPDATE adver SET count = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, count);
			pst.setInt(2, id);

			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return result;
	}
}
