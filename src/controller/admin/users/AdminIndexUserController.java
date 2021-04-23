package controller.admin.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.GlobalConstants;
import daos.UserDAO;
import models.Song;
import models.User;
import utils.AuthUtil;


public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexUserController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		UserDAO userDAO = new UserDAO();
		//Phaan trang
		int numerOfItems = userDAO.numerOfItems();
		int numerOfPages = (int)Math.ceil((float)((float)numerOfItems/(float)GlobalConstants.NUMBER_PER_PAGE));
		int currentPage = 1;
		if(!"".equals(request.getParameter("page")) && request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/error.html");
				return;
			}
		}
		if(currentPage < 1 || currentPage > numerOfPages){
			response.sendRedirect(request.getContextPath()+"/error.html");
			return;
		}
		int offset = (currentPage-1)*GlobalConstants.NUMBER_PER_PAGE;
		if(currentPage > numerOfPages || currentPage < 1) {
			currentPage = 1;
		}
		List<User> users = userDAO.getAllPagination(offset);
		//--------------
		//List<User> users = userDAO.getUser();
		request.setAttribute("numerOfItems", numerOfItems);
		request.setAttribute("numerOfPages", numerOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
