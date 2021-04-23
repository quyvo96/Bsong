package controller.admin.song;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.GlobalConstants;
import daos.CategoryDAO;
import daos.SongDAO;
import models.Category;
import models.Song;
import utils.AuthUtil;

@MultipartConfig
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminAddSongController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getCategories();
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SongDAO songDAO = new SongDAO();
		String name = request.getParameter("name");
		int catId = Integer.parseInt(request.getParameter("category"));
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		Part filePart = request.getPart("picture");
		String fileName = filePart.getSubmittedFileName();
		if (!"".equals(fileName)) {
			 String rootPath = request.getServletContext().getRealPath("");
			 String dirUploadPath = rootPath + "uploads";
			 File createDir = new File(dirUploadPath);
			 if (!createDir.exists()) {
				 createDir.mkdir();
			 }
			 //string, string builder
			 StringBuilder sb = new StringBuilder();
			 StringBuilder sbNew = new StringBuilder();
			 LocalDateTime myDatePictuer = LocalDateTime.now();  
			 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yy HH_mm_ss");  
			 String formattedDate = myDatePictuer.format(myFormatObj);
			 String newFileName = name + formattedDate + ".jpg";
			 String newFilePath = sbNew.append(dirUploadPath).append(File.separator).append(newFileName).toString();
			 String filePath = sb.append(dirUploadPath).append(File.separator).append(fileName).toString();
		      try{
		    	  	File oldFile = new File(filePath);
		    	  	File newFile = new File(newFilePath);
		    		if (oldFile.renameTo(newFile)) {
		    			System.out.println("Đổi tên thành công!");
		    		} else {
		    			System.out.println("Đổi tên bị lỗi!");
		    		}

		        }catch(Exception e){
		            System.out.println("Exception occurred");
		            e.printStackTrace();
		         }
			 filePart.write(newFilePath); // Truyền vào đường dẫn upload file
			 fileName = newFileName;  
			 System.out.println("dirUploadPath :" + dirUploadPath);
		 }
		
		Song song = new Song(name, preview, detail, fileName, new Category(catId));
		int countRecordInserted = songDAO.add(song);
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		if(countRecordInserted > 0) {
					//success
					
			url = sbd.append(request.getContextPath()).append("/admin/song/index?msg=").append(GlobalConstants.SUCCESS).toString();
			response.sendRedirect(url);
			return;
		}
				//fail
		url = sbd.append(request.getContextPath()).append("/admin/song/index?msg=").append(GlobalConstants.ERROR).toString();
		response.sendRedirect(url);
	}
}
