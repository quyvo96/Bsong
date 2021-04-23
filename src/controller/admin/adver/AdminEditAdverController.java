package controller.admin.adver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
public class AdminEditAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminEditAdverController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			AdverDAO adverDAO = new AdverDAO();
			Adver adver = adverDAO.getById(id);
			StringBuilder sbd = new StringBuilder();
			String url = " ";
			if(adver != null) {
				request.setAttribute("adver", adver);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/adver/edit.jsp");
				rd.forward(request, response);
				return;
			}
			url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao.html").toString();
			response.sendRedirect(url);
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}

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
		StringBuilder sbd = new StringBuilder();
		String url = " ";
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String web = request.getParameter("web");
			Part filePart = request.getPart("picture");
			String fileName = filePart.getSubmittedFileName();
			Adver getNamePicture = adverDAO.getById(id);
			System.out.println(fileName);
			if(!"".equals(fileName)) {
				if (!"".equals(fileName)) {
					 String fileNameOld =  getNamePicture.getPicture();
					 String rootPath = request.getServletContext().getRealPath("");
					 String dirUploadPath = rootPath + "uploads";
					 File createDir = new File(dirUploadPath);
					 if (!createDir.exists()) {
						 createDir.mkdir();
					 }
					 //string, string builder
					 StringBuilder sb = new StringBuilder();
					 StringBuilder sbOld = new StringBuilder();
					 StringBuilder sbNew = new StringBuilder();
					 String filePath = sb.append(dirUploadPath).append(File.separator).append(fileName).toString();
					 LocalDateTime myDatePictuer = LocalDateTime.now();  
					 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yy_HH_mm_ss");  
					 String formattedDate = myDatePictuer.format(myFormatObj);
					 String newFileName = name + formattedDate + ".jpg";
					 String newFilePath = sbNew.append(dirUploadPath).append(File.separator).append(newFileName).toString();
					 String filePathOld = sbOld.append(dirUploadPath).append(File.separator).append(fileNameOld).toString();
					 System.out.println("filePathOld :" + filePathOld);
				      try{
				    	  	File oldFile = new File(filePath);
				    	  	File newFile = new File(newFilePath);
				    		if (oldFile.renameTo(newFile)) {
				    			System.out.println("�?ổi tên thành công!");
				    		} else {
				    			System.out.println("�?ổi tên bị lỗi!");
				    		}
				          //Specify the file name and path
				          File file = new File(filePathOld);
				          /*the delete() method returns true if the file is
				           * deleted successfully else it returns false
				           */
				          if(file.delete()){
				             System.out.println(file.getName() + " is deleted!");
				          }else{
				             System.out.println("Delete failed: File didn't delete");
				           }
				        }catch(Exception e){
				            System.out.println("Exception occurred");
				            e.printStackTrace();
				         }
					 filePart.write(newFilePath); // Truy�?n vào đư�?ng dẫn upload file
					 fileName = newFileName;
					 System.out.println("dirUploadPath :" + dirUploadPath);
				 }	
			}else {
				fileName =  getNamePicture.getPicture();
			}
			Adver adver = new Adver(id, name, fileName, web);
			int countRecordInserted = adverDAO.edit(adver);

			if(countRecordInserted > 0) {
						//success
						
				url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.SUCCESS).append(".html").toString();
				response.sendRedirect(url);
				return;
			}
					//fail
			url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.ERROR).append(".html").toString();
			response.sendRedirect(url);

			
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}
}
