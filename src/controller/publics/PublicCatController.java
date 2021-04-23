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


public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicCatController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO cat = new CategoryDAO();
		SongDAO songDAO = new SongDAO();
		List<Category> listcats = cat.getCategories();
		try {
			int cat_id = Integer.parseInt(request.getParameter("cat_id"));
			Category cats = cat.getById(cat_id);
			//quang cao
			AdverDAO adverDAO = new AdverDAO();
			List<Adver> listadver = adverDAO.getAll();
			request.setAttribute("listadver", listadver);
			//-------------------
			
			//Phaan trang
			int numerOfItems = songDAO.numerOfItemsForCat(cat_id);
			int numerOfPages = (int)Math.ceil((float)((float)numerOfItems/(float)GlobalConstants.NUMBER_PER_PAGE));
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {

			}
			int offset = (currentPage-1)*GlobalConstants.NUMBER_PER_PAGE;
			if(currentPage > numerOfPages || currentPage < 1) {
				currentPage = 1;
			}
			List<Song> songCat = songDAO.getAllPaginationForCat(cat_id, offset);
			//--------------
			List<Song> listSongs = songDAO.getAll();
			
			request.setAttribute("numerOfPages", numerOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listSongs", listSongs);
			request.setAttribute("songCat", songCat);
			request.setAttribute("listcats", listcats);
			request.setAttribute("cats", cats);
			RequestDispatcher rd = request.getRequestDispatcher("views/public/cat.jsp");
			rd.forward(request, response);
			return;
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
