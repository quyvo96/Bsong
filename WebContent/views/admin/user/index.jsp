<%@page import="models.User"%>
<%@page import="constants.GlobalConstants"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                               <%
					            	if(session.getAttribute("userInfo") != null){
					            		User userInfor = (User)session.getAttribute("userInfo");
					            		int id_role = userInfor.getUserRole().getId();
					            		String nameuser = userInfor.getUserName();
					            		if(id_role == 1){
					            			
					            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/quan-ly/nguoi-dung/them-nguoi-dung.html" class="btn btn-success btn-md">Thêm</a>
                                </div>
                            </div>
                            <%
					            		}
		                        int numerOfPages = (Integer)request.getAttribute("numerOfPages");
		                        int numerOfItems = (Integer)request.getAttribute("numerOfItems");
		                        int currentPage = (Integer)request.getAttribute("currentPage");
                            	if(request.getParameter("msg")!=null){
                            		String msg = request.getParameter("msg");
                            		if(GlobalConstants.SUCCESS.equals(msg)){
                            %>
	                            <div class="alert alert-success" role="alert">
								  <h4>Xử lý thành công!</h4>
								</div>
                            	
                            <% 
                            		}else{
                             %>
                                <div class="alert alert-danger" role="alert">
								  <h4>Xử lý thất bại!</h4>
								</div>
                                <% 
                            	}}
                            
                            %>
                            
			
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên đăng nhập</th>
                                        <th>Họ tên</th>
                                        <th>Chức vụ</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	@SuppressWarnings("unchecked")
	                                List<User> users = (List<User>)request.getAttribute("users");
	                                	if(users != null && users.size() > 0){
	                                		for(User user: users){
	                                			int id = user.getId();
	                                			String userName = user.getUserName();
	                                			String password = user.getPassword();
	                                			String fullName = user.getFullName();
	                                			int role = user.getUserRole().getId();
	                                			String name_role = user.getUserRole().getRole();
	                                			String urlEdit = request.getContextPath() + "/quan-ly/nguoi-dung/sua-nguoi-dung-" + id+".html";
	                                			String urlDel = request.getContextPath() + "/admin/user/del?id=" + id;

                                %>

                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=userName %></td>
                                        <td class="center"><%=fullName %></td>
                                        <td class="center"><%=name_role %></td>
                                        <td class="center">
                                        	<%
                                        		if(id_role == 1 || nameuser.equals(userName)){
                                        			
                                        		
                                        	%>
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <%
                                            		if(id_role != role){	
                                            %>
                                            <a href="<%=urlDel %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không!')"><i class="fa fa-pencil"></i> Xóa</a>
                                        	<%}} %>
                                        </td>
                                    </tr>
                               <% }%>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px"></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<%
                                        		if(currentPage > 1){
                                        			String urlSlugBack = request.getContextPath()+ "/quan-ly/nguoi-dung/trang-"+(currentPage-1)+".html";
                                        	%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=urlSlugBack%>">Trang trước</a></li>
                                            <%
                            				}	
                                        	for(int i = 1; i<=numerOfPages; i++){
                                        		String urlSlug = request.getContextPath()+ "/quan-ly/nguoi-dung/trang-"+i+".html";
                                        		if(currentPage == i){
                                        	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=currentPage%></a></li>
                                            <%}else{ %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=i%></a></li>
                                         <%
                                         }}
                                        	if(currentPage < numerOfPages){
                                        		String urlSlugNext = request.getContextPath()+ "/quan-ly/nguoi-dung/trang-"+(currentPage+1)+".html";
                                         
                                         %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=urlSlugNext%>">Trang tiếp</a></li>
                                        <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%}} %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>