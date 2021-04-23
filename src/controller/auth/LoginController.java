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


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User userInfo = userDAO.findByUserAndPassword(username, password);
		
		if(userInfo != null) {
			session.setAttribute("userInfo", userInfo);
			response.sendRedirect(request.getContextPath()+"/quan-ly.html");
			return;
		}
		String url = request.getContextPath()+ "/login-" + GlobalConstants.ERROR + ".html";
		response.sendRedirect(url);
	}

}
