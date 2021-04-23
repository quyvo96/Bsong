package controller.admin.adver;

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
import daos.AdverDAO;
import daos.CategoryDAO;
import daos.SongDAO;
import models.Adver;
import models.Category;
import models.Song;
import utils.AuthUtil;

@MultipartConfig
public class AdminAddAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminAddAdverController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/adver/add.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		AdverDAO adverDAO = new AdverDAO();
		String name = request.getParameter("name");
		String web = request.getParameter("web");
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
			 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yy_HH_mm_ss");  
			 String formattedDate = myDatePictuer.format(myFormatObj);
			 String newFileName = name + formattedDate + ".jpg";
			 String newFilePath = sbNew.append(dirUploadPath).append(File.separator).append(newFileName).toString();
			 String filePath = sb.append(dirUploadPath).append(File.separator).append(fileName).toString();
		      try{
		    	  	File oldFile = new File(filePath);
		    	  	File newFile = new File(newFilePath);
		    		if (oldFile.renameTo(newFile)) {
		    			System.out.println("�?ổi tên thành công!");
		    		} else {
		    			System.out.println("�?ổi tên bị lỗi!");
		    		}

		        }catch(Exception e){
		            System.out.println("Exception occurred");
		            e.printStackTrace();
		         }
			 filePart.write(newFilePath);
			 fileName = newFileName;  
			 System.out.println("dirUploadPath :" + dirUploadPath);
		 }
		Adver adver = new Adver(name, fileName, web);
		int countRecordInserted = adverDAO.add(adver);
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		if(countRecordInserted > 0) {
					//success
					
			url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.SUCCESS).append(".html").toString();
			response.sendRedirect(url);
			return;
		}
				//fail
		url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.ERROR).append(".html").toString();
		response.sendRedirect(url);
	}
}
