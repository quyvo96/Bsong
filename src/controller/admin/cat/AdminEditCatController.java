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


public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminEditCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		try {
			int id = Integer.parseInt(request.getParameter("id"));

			CategoryDAO categoryDAO = new CategoryDAO();
			Category cat =categoryDAO.getById(id);

			if(cat != null) {
				request.setAttribute("cat", cat);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
				rd.forward(request, response);
				return;
			}
			url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc.html").toString();
			response.sendRedirect(url);
			return;
			
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}

		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Category cat = new Category(id, name);
			int countRecordEdit = categoryDAO.edit(cat);

			if(countRecordEdit > 0) {
				//success
				
				url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc-").append(GlobalConstants.SUCCESS).append(".html").toString();
				response.sendRedirect(url);
				return;
			}
			//fail
			url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc-").append(GlobalConstants.ERROR).append(".html").toString();
			response.sendRedirect(url);
			return;
			
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}

	}



}
