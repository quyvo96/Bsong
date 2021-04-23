package controller.publics;

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


public class PublicAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicAdverController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		int id_adver = Integer.parseInt(request.getParameter("id_adver"));
		AdverDAO adverDAO = new AdverDAO();
		Adver adver = adverDAO.getById(id_adver);
		String web = adver.getWeb();
		int count = adver.getCount();
		count++;
		int sendWeb = adverDAO.addCouter(id_adver, count);
		if(sendWeb> 0) {
			response.sendRedirect(web);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("views/public/index.jsp");
		rd.forward(request, response);
	}catch(Exception e) {

		response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
