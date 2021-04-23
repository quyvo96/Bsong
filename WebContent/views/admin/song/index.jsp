<%@page import="sun.security.util.Length"%>
<%@page import="models.Category"%>
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
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath()%>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath() %>/admin/song/index">
                                    	<input type="search" class="form-control input-sm" name="sname" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                    	<select  name="scat" class="btn" style="float:right" >
                                    		<option value="0">-Chọn danh mục-</option>
			                                <%
			                                	if(request.getAttribute("listCat") != null){
			                                		List<Category> cat = (List<Category>)request.getAttribute("listCat");
			                                		if(cat.size() > 0){
			                                			for(Category c : cat){
			                                				int id = c.getId();
			                                				String catName = c.getName();	
			    
			                                %>
                                    		<option value="<%=id%>"><%=catName%></option>
                                    		<%}}} %>
                                    	</select>
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	List<Song> songs = (List<Song>)request.getAttribute("listSong");
                                	if(request.getAttribute("listSong") != null){
                                		
                                		if(songs.size() > 0){
                                			for(Song s : songs){
                                				int id = s.getId();
                                				String songName = s.getName();
                                				int count = s.getCounter();
                                				String picture = s.getPicture();
                                				String catName = s.getCat().getName();
                                				String urlEdit = request.getContextPath() + "/quan-ly/bai-hat/sua-bai-hat-" + id+".html";
                                				String urlDel = request.getContextPath() + "/admin/song/del?id=" + id ;
    
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=songName %></td>
                                        <td class="center"><%=catName %></td>
                                        <td class="center"><%=count %></td>
                                        <td class="center">
                                        	<%
                                        		if(!"".equals(picture)){
                                        			
                                        		
                                        	%>
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/uploads/<%=picture%>" alt="<%=picture%>"/>
											<%}else{
											%>
											Không có hình ảnh
											<%} %>
											
                                        </td>
                                        <td class="center">
                                            <a href="<%=urlEdit%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel%>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không!')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                <%}}} %>
                                </tbody>
                            </table>
                            <%
                            
                            	int numerOfPages = (Integer)request.getAttribute("numerOfPages");
                            	int numerOfItems = (Integer)request.getAttribute("numerOfItems");
                            	int currentPage = (Integer)request.getAttribute("currentPage");
                            	if(songs != null && songs.size()>0){
                            		
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=GlobalConstants.NUMBER_PER_PAGE %> của các bài hát.</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<%
                                        	
                                        		if(currentPage > 1){
                                        			String urlSlugBack = request.getContextPath()+ "/quan-ly/bai-hat/trang-"+(currentPage-1)+".html";
                                        	%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=urlSlugBack%>">Trang trước</a></li>
                                            <%
                            				}	
                                        	for(int i = 1; i<=numerOfPages; i++){
                                        		String urlSlug = request.getContextPath()+ "/quan-ly/bai-hat/trang-"+i+".html";
                                        		if(currentPage == i){
                                        	%>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=currentPage%></a></li>
                                            <%}else{ %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=urlSlug%>"><%=i%></a></li>
                                         <%
                                         }}
                                        	if(currentPage < numerOfPages){
                                        		String urlSlugNext = request.getContextPath()+ "/quan-ly/bai-hat/trang-"+(currentPage+1)+".html";
                                         
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>