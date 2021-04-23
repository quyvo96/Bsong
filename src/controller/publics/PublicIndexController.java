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


public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PublicIndexController() {
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
		//Phaan trang
		int numerOfItems = songDAO.numerOfItems();
		int numerOfPages = (int)Math.ceil((float)((float)numerOfItems/(float)GlobalConstants.NUMBER_PER_PAGE));
		int currentPage = 1;
		if(!"".equals(request.getParameter("page")) && request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
				return;
			}
		}
		if(currentPage < 1 || currentPage > numerOfPages){
			response.sendRedirect(request.getContextPath()+"/trang-chu/error.html");
			return;
		}
		int offset = (currentPage-1)*GlobalConstants.NUMBER_PER_PAGE;
		if(currentPage > numerOfPages || currentPage < 1) {
			currentPage = 1;
		}
		List<Song> listSongs = songDAO.getAllPagination(offset);
		//--------------
		//////search
		String sname = "";
		if(!"".equals(request.getParameter("editbox_search"))&&request.getParameter("editbox_search") != null) {
				//người dùng muốn nhập
			 sname = request.getParameter("editbox_search");

		}
		//List<Song> listSongs = songDAO.getAll();
		List<Song> listSearch = songDAO.findAllByDetailAndName(sname);
		if(!"".equals(sname)) {
			listSongs = listSearch;
		}
		List<Category> listcats = cat.getCategories();
		request.setAttribute("numerOfItems", numerOfItems);
		request.setAttribute("numerOfPages", numerOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listSongs", listSongs);
		request.setAttribute("listcats", listcats);
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
