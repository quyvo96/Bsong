package controller.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstants;
import daos.CategoryDAO;
import daos.SongDAO;
import daos.UserDAO;
import models.Category;
import models.Song;
import models.User;
import utils.StringUtil;


public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogoutController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		response.sendRedirect(request.getContextPath()+"/trang-chu.html");
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
