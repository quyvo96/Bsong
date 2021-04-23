package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CategoryDAO;
import daos.SongDAO;
import daos.UserDAO;
import models.Category;
import models.Song;
import models.User;
import utils.AuthUtil;


public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getServletContext().getRealPath(""));
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CategoryDAO cats = new CategoryDAO();
		List<Category> cat = cats.getCategories();
		int lenCat = cat.size();
		SongDAO songs = new SongDAO();
		List<Song> song = songs.getAll();
		int lenSong = song.size();
		UserDAO users = new UserDAO();
		List<User> user = users.getUser();
		int lenUser = user.size();
		
		request.setAttribute("lenCat", lenCat);
		request.setAttribute("lenSong", lenSong);
		request.setAttribute("lenUser", lenUser);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
