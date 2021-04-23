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
import daos.SongDAO;
import models.Category;
import utils.AuthUtil;


public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		try {
			int id =Integer.parseInt(request.getParameter("id"));
			CategoryDAO catDAO = new CategoryDAO();
			int countRecordDel = catDAO.del(id);
			
			StringBuilder sbd = new StringBuilder();
			String url = " ";
			if(countRecordDel > 0) {
				//success
				
				url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc/").append(GlobalConstants.SUCCESS).append(".html").toString();
				response.sendRedirect(url);
				return;
			}
			//fail
			url = sbd.append(request.getContextPath()).append("/quan-ly/danh-muc/").append(GlobalConstants.ERROR).append(".html").toString();
			response.sendRedirect(url);
			return;
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
