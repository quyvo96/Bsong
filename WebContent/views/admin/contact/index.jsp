<%@page import="models.Contact"%>
<%@page import="constants.GlobalConstants"%>
<%@page import="models.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
                            <%
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
                            <div class="row">
                            </div>
                            <%  
                            	if(request.getAttribute("contacts") != null){
                        			List<Contact> contacts = (List<Contact>)request.getAttribute("contacts");
    		                        int numerOfPages = (Integer)request.getAttribute("numerOfPages");
    		                        int numerOfItems = (Integer)request.getAttribute("numerOfItems");
    		                        int currentPage = (Integer)request.getAttribute("currentPage");
                        	%>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Họ tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Nội dung</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%

                                		if(contacts.size() > 0){
                                			for(Contact c : contacts){
                                				int id = c.getId();
                                				String name = c.getName();
                                				String email = c.getEmail();
                                				String message = c.getMessage();
                                				String web = c.getWebsite();		
    
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=email %></td>
                                        <td class="center"><%=web %></td>
										<td class="center"><%=message %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=id%>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không!')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                <%}} %>
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
                                        			String urlSlugBack = request.getContextPath()+ "/quan-ly/lien-he/trang-"+(currentPage-1)+".html";
                                        	%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=urlSlugBack%>">Trang trước</a></li>
                                            <%
                            				}	
                                        	for(int i = 1; i<=numerOfPages; i++){
                                        		String urlSlug = request.getContextPath()+ "/quan-ly/lien-he/trang-"+(i)+".html";
                                        		if(currentPage == i){
                                        	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=currentPage%></a></li>
                                            <%}else{ %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=i%></a></li>
                                         <%
                                         }}
                                        	if(currentPage < numerOfPages){
                                        		String urlSlugNext = request.getContextPath()+ "/quan-ly/lien-he/trang-"+(currentPage+1)+".html";
                                         
                                         %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=urlSlugNext%>">Trang tiếp</a></li>
                                        <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>