package controller.admin.users;

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
import daos.UserDAO;
import models.Category;
import utils.AuthUtil;


public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelUserController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		try {
			int id =Integer.parseInt(request.getParameter("id"));
			StringBuilder sbd = new StringBuilder();
			String url = " ";
			if(id != 1) {
				UserDAO userDAO = new UserDAO();
				int countRecordDel = userDAO.del(id);
				if(countRecordDel > 0) {
					//success
					url = sbd.append(request.getContextPath()).append("/quan-ly/nguoi-dung-").append(GlobalConstants.SUCCESS).append(".html").toString();
					response.sendRedirect(url);
					return;
				}
				
			}
			//fail
			url = sbd.append(request.getContextPath()).append("/admin/user/index?msg=").append(GlobalConstants.ERROR).toString();
			response.sendRedirect(url);
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
