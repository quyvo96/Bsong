package controller.admin.contact;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstants;
import daos.CategoryDAO;
import daos.ContactDAO;
import models.Category;
import models.Contact;
import models.User;
import utils.AuthUtil;


public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexContactController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		ContactDAO contact = new ContactDAO();
		//Phaan trang
		int numerOfItems = contact.numerOfItems();
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
		List<Contact> contacts = contact.getAllPagination(offset);
		//--------------
		//List<Contact> contacts = contact.getContact();
		request.setAttribute("numerOfItems", numerOfItems);
		request.setAttribute("numerOfPages", numerOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("contacts", contacts);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
