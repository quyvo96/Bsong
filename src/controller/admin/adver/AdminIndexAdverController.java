package controller.admin.adver;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstants;
import daos.AdverDAO;
import daos.CategoryDAO;
import daos.SongDAO;
import models.Adver;
import models.Category;
import models.Song;
import utils.AuthUtil;


public class AdminIndexAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexAdverController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		AdverDAO adverDAO = new AdverDAO();

		List<Adver> listadver = adverDAO.getAll();
		
		request.setAttribute("listadver", listadver);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/adver/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
