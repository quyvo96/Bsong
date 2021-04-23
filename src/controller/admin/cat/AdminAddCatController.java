package controller.admin.cat;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstants;
import daos.CategoryDAO;
import models.Category;
import utils.AuthUtil;


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminAddCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/add.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		String name = request.getParameter("name");
		Category category = new Category(name);
		int countRecordInserted = categoryDAO.add(category);
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		if(countRecordInserted > 0) {
			//success
			
			url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc-").append(GlobalConstants.SUCCESS).append(".html").toString();
			response.sendRedirect(url);
			return;
		}
		//fail
		url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc-").append(GlobalConstants.ERROR).append(".html").toString();
		response.sendRedirect(url);
	}

}
