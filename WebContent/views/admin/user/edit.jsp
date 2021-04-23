<%@page import="models.UserRole"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm danh mục</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                            <%
					          if(session.getAttribute("userInfo") != null){
					            User userInfor = (User)session.getAttribute("userInfo");
					            int id_user = userInfor.getId();
					            String nameuser = userInfor.getUserName();
					            
	                            	if(request.getAttribute("user") != null){
	                            		User user = (User)request.getAttribute("user");
	                            		int id = user.getId();
	                            		String username = user.getUserName();
	                            		String password = user.getPassword();
	                            		String fullname = user.getFullName();
	                            		int id_rl = user.getUserRole().getId();
	                            		String name_role = user.getUserRole().getRole();
                            	
                            %>
                                <form role="form" method="post" id="form_user">
                                    <div class="form-group">
                                        <label for="userName">Tên đăng nhập</label>
                                        <input type="text" id="name" value="<%=username %>" name="userName" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullName">Họ tên</label>
                                        <input type="text" id="name" value="<%=fullname %>" name="fullName" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật Khẩu</label>
                                        <input type="password" id="name" value="<%=password %>" name="password" class="form-control" />
                                    </div>
                                    
                                    <div class="form-group">
                                    	
                                        <label >Chuc Vu</label>
                                        <%if(id_user == 1 && id_rl!=1 ){ %>
                                        <select id="role" name="role" class="form-control">
                                        	<%
                                        		if(request.getAttribute("listrole") != null){
                                        			
                                        			List<UserRole> listrole = (List<UserRole>)request.getAttribute("listrole");
                                        			if(listrole.size() > 0){
                                        				for(UserRole r : listrole){
                                        					int id_r = r.getId();
                                        					String role = r.getRole();
                                        	%>
                                        	
	                                        <option value="<%=id_r%>"><%=role%></option>

                                        	<%}}}
                                        	%>
                                        	
                                        </select>
                                        <%}else{ %>
	                                        <h5><%=name_role%></h5>
	                                        <input type="hidden" id="name" value="<%=id_rl %>" name="role" class="form-control" />
	                                       <%} %>
                                        </div>
                                        
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <%}} %>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>