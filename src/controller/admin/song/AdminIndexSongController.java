package controller.admin.song;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstants;
import daos.CategoryDAO;
import daos.SongDAO;
import models.Category;
import models.Song;
import utils.AuthUtil;


public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminIndexSongController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		SongDAO songDAO = new SongDAO();
		CategoryDAO catDAO = new CategoryDAO();
		//Phaan trang
		int numerOfItems = songDAO.numerOfItems();
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
		List<Song> listSong = songDAO.getAllPagination(offset);
		//--------------
		//search
		String sname = "";
		int scat = 0;
		if(!"".equals(request.getParameter("sname"))&&request.getParameter("sname") != null) {
				//người dùng muốn nhập
			 sname = request.getParameter("sname");
		}
		if(request.getParameter("scat") != null) {
			scat = Integer.parseInt(request.getParameter("scat"));
		}
		Song song = new Song(sname, new Category(scat));
		List<Song> listSearch = songDAO.findAllByIdAndNameOrderByNewsId(song);
		//*****
		List<Category> listCat = catDAO.getCategories();
		
		List<Song> listSongs = songDAO.getAll();
		
		if(scat > 0 || !"".equals(sname)) {
			listSong = listSearch;
		}
		request.setAttribute("numerOfItems", numerOfItems);
		request.setAttribute("numerOfPages", numerOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listCat", listCat);
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
