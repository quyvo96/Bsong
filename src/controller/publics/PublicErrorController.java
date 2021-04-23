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


public class PublicErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicErrorController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		CategoryDAO cat = new CategoryDAO();
		SongDAO songDAO = new SongDAO();
		
		//quang cao
		AdverDAO adverDAO = new AdverDAO();
		List<Adver> listadver = adverDAO.getAll();
		request.setAttribute("listadver", listadver);
		//-------------------

		List<Song> listSongs = songDAO.getAll();
		List<Category> listcats = cat.getCategories();
		request.setAttribute("listSongs", listSongs);
		request.setAttribute("listcats", listcats);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/error.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
