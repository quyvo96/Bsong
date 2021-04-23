package controller.admin.adver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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


public class AdminDelAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminDelAdverController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}

		try {
			int id =Integer.parseInt(request.getParameter("id"));
			AdverDAO adverDAO = new AdverDAO();
			String rootPath = request.getServletContext().getRealPath("");
			String dirUploadPath = rootPath + "uploads";
			StringBuilder sb = new StringBuilder();
			Adver getNamePicture = adverDAO.getById(id);
			String fileName =  getNamePicture.getPicture();
			String FilePath = sb.append(dirUploadPath).append(File.separator).append(fileName).toString();
		      try{
		          File file = new File(FilePath);
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
			int countRecordDel = adverDAO.del(id);
			
			StringBuilder sbd = new StringBuilder();
			String url = " ";
			if(countRecordDel > 0) {
				//success
				
				url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.SUCCESS).append(".html").toString();
				response.sendRedirect(url);
				return;
			}
			//fail
			url = sbd.append(request.getContextPath()).append("/quan-ly/quang-cao-").append(GlobalConstants.ERROR).append(".html").toString();
			response.sendRedirect(url);
			return;
			
		}catch(Exception e) {

			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
