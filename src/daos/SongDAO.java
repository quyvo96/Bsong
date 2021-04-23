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
import utils.ConnectDBUtil;

public class SongDAO {
	
	private Connection conn;
	
	private Statement st;
	
	private PreparedStatement pst;
	
	private ResultSet rs;

	public List<Song> getAll() {
		List<Song> songs = new ArrayList<>();
		String sql = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC";
		conn = ConnectDBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("preview_text"),
							rs.getString("detail_text"),
							rs.getTimestamp("date_create"),
							rs.getString("picture"),
							rs.getInt("counter"),
							new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songs.add(song);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return songs;
	}

	public int add(Song song) {
		String sql = "INSERT INTO songs(name, preview_text, detail_text, picture, cat_id) VALUES(?, ?, ?, ?, ?)";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCat().getId());
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return result;
	}
	public Song getById(int id) {
		Song song = null;
		String sql = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id Where s.id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				song = new Song(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("preview_text"),
								rs.getString("detail_text"),
								rs.getTimestamp("date_create"),
								rs.getString("picture"),
								rs.getInt("counter"),
								new Category(rs.getInt("cat_id"), rs.getString("catName")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(st, rs, conn);

		}
		return song;
	}
	public int edit(Song song) {
		String sql = "UPDATE songs SET name = ?, preview_text = ?,detail_text = ?,picture = ?, cat_id = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, song.getName());
			pst.setString(2, song.getPreview_text());
			pst.setString(3, song.getDetail_text());
			pst.setString(4, song.getPicture());
			pst.setInt(5, song.getCat().getId());
			pst.setInt(6, song.getId());
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
		String sql = "DELETE FROM songs WHERE id = ?";
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
	public List<Song> getByCatId(int id) {
		List<Song> songs = new ArrayList<>();
		String sql ="SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? ORDER BY s.id DESC";
		//String sql = "SELECT id, name, preview_text, detail_text, date_create, picture, counter FROM songs Where cat_id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"),
								rs.getString("name"),
								rs.getString("preview_text"),
								rs.getString("detail_text"),
								rs.getTimestamp("date_create"),
								rs.getString("picture"),
								rs.getInt("counter"),
								new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songs.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return songs;
	}

	public int addCouter(int id) {
		 Song song = getById(id);
		 int counters = song.getCounter();
		 counters = counters + 1;
		String sql = "UPDATE songs SET counter = ? WHERE id = ?";
		int result = 0;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, counters);
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
	public List<Song> findAllByIdAndNameOrderByNewsId(Song song) {
		List<Song> listSong = new ArrayList<>();
		String sql = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE 1";
		if(!"".equals(song.getName())) {
			sql += " AND s.name LIKE ?";
		}
		if(song.getCat().getId() > 0) {
			sql += " AND s.cat_id LIKE ?";
		}
		sql +=  " ORDER BY s.id DESC LIMIT 0, 4" ;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(song.getName())) {
				pst.setString(1,"%"+song.getName()+"%");
				if(song.getCat().getId() > 0) {
					pst.setInt(2, song.getCat().getId());
				}
			}else {
				if(song.getCat().getId() > 0) {
					pst.setInt(1, song.getCat().getId());
			}}

			rs = pst.executeQuery();
			while(rs.next()) {
				Song songs = new Song(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("preview_text"),
							rs.getString("detail_text"),
							rs.getTimestamp("date_create"),
							rs.getString("picture"),
							rs.getInt("counter"),
							new Category(rs.getInt("cat_id"), rs.getString("catName")));
				listSong.add(songs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return listSong;
	}
	public List<Song> findAllByDetailAndName(String sname) {
		List<Song> listSong = new ArrayList<>();
		String sql = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE 1";
		if(!"".equals(sname)) {
			sql += " AND (s.name LIKE ? OR s.preview_text LIKE ? OR s.detail_text LIKE ?)";
		}
		sql +=  " ORDER BY s.id DESC LIMIT 0, 4" ;
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			if(!"".equals(sname)) {
				pst.setString(1,"%"+sname+"%");
				pst.setString(2,"%"+sname+"%");
				pst.setString(3,"%"+sname+"%");

				}


			rs = pst.executeQuery();
			while(rs.next()) {
				Song songs = new Song(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("preview_text"),
							rs.getString("detail_text"),
							rs.getTimestamp("date_create"),
							rs.getString("picture"),
							rs.getInt("counter"),
							new Category(rs.getInt("cat_id"), rs.getString("catName")));
				listSong.add(songs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return listSong;
	}

	public int numerOfItems() {
		String sql = "SELECT COUNT(*) AS count FROM songs";
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

	public List<Song> getAllPagination(int offset) {
		List<Song> songs = new ArrayList<>();
		String sql = "SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id ORDER BY s.id DESC LIMIT ?, ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, GlobalConstants.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"),
							rs.getString("name"),
							rs.getString("preview_text"),
							rs.getString("detail_text"),
							rs.getTimestamp("date_create"),
							rs.getString("picture"),
							rs.getInt("counter"),
							new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songs.add(song);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return songs;
	}

	public int numerOfItemsForCat(int cat_id) {
		String sql = "SELECT COUNT(*) AS count FROM songs WHERE cat_id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return 0;
	}

	public List<Song> getAllPaginationForCat(int id, int offset) {
		List<Song> songs = new ArrayList<>();
		String sql ="SELECT s.*, c.name AS catName FROM songs AS s INNER JOIN categories AS c ON s.cat_id = c.id WHERE s.cat_id = ? ORDER BY s.id DESC LIMIT ?,?";
		//String sql = "SELECT id, name, preview_text, detail_text, date_create, picture, counter FROM songs Where cat_id = ?";
		conn = ConnectDBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, offset);
			pst.setInt(3, GlobalConstants.NUMBER_PER_PAGE);
			rs = pst.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"),
								rs.getString("name"),
								rs.getString("preview_text"),
								rs.getString("detail_text"),
								rs.getTimestamp("date_create"),
								rs.getString("picture"),
								rs.getInt("counter"),
								new Category(rs.getInt("cat_id"), rs.getString("catName")));
				songs.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectDBUtil.close(pst, rs, conn);

		}
		return songs;
	}
public static void main(String[] args) {
	SongDAO songDAO = new SongDAO();
	int a = songDAO.numerOfItemsForCat(10);
	System.out.println(a);
	List<Song> list = songDAO.getAllPagination(1);
	for(Song s:list) {
		System.out.println(s);
	}
	
}
}
