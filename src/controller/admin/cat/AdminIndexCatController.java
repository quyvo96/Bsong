package controller.admin.cat;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import models.Category;
import utils.AuthUtil;


public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCategories();
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
