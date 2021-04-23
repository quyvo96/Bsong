package controller.admin.contact;

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
import daos.ContactDAO;
import daos.SongDAO;
import models.Category;
import utils.AuthUtil;


public class AdminDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelContactController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}

		try {
			int id =Integer.parseInt(request.getParameter("id"));
			ContactDAO contactDAO = new ContactDAO();
			int countRecordDel = contactDAO.del(id);
			
			StringBuilder sbd = new StringBuilder();
			String url = " ";
			if(countRecordDel > 0) {
				//success
				
				url = sbd.append(request.getContextPath()).append("/quan-ly/lien-he-").append(GlobalConstants.SUCCESS).append(".html").toString();
				response.sendRedirect(url);
				return;
			}
			//fail
			url = sbd.append(request.getContextPath()).append("/quan-ly/lien-he-").append(GlobalConstants.ERROR).append(".html").toString();
			response.sendRedirect(url);
			return;
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
