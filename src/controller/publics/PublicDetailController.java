package controller.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.AdverDAO;
import daos.CategoryDAO;
import daos.SongDAO;
import models.Adver;
import models.Category;
import models.Song;


public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicDetailController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//quang cao
		AdverDAO adverDAO = new AdverDAO();
		List<Adver> listadver = adverDAO.getAll();
		request.setAttribute("listadver", listadver);
		//-------------------
		CategoryDAO cat = new CategoryDAO();
		List<Category> listcats = cat.getCategories();

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			SongDAO songDAO = new SongDAO();
			int updateCounter = songDAO.addCouter(id);
			if(updateCounter > 0) {
				Song song = songDAO.getById(id);
				
				List<Song> listSongs = songDAO.getAll();
				int cat_id = song.getCat().getId();
				List<Song> songCat = songDAO.getByCatId(cat_id);
		
				request.setAttribute("songCat", songCat);
				request.setAttribute("listSongs", listSongs);
				request.setAttribute("song", song);
				request.setAttribute("listcats", listcats);
				RequestDispatcher rd = request.getRequestDispatcher("views/public/detail.jsp");
				rd.forward(request, response);
				return;
			}
			response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
