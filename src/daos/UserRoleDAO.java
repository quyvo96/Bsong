package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.User;
import models.UserRole;
import utils.ConnectDBUtil;

public class UserRoleDAO {
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<UserRole> getUserRole() {
		List<UserRole> listrole = new ArrayList<>();
		String sql = "SELECT id, role FROM user_role ORDER BY id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String role = rs.getString("role");
				UserRole userrole = new UserRole(id, role);
				listrole.add(userrole);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return listrole;
	}

}
