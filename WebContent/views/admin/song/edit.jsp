<%@page import="models.Song"%>
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
                <h2>Thêm bài hát</h2>
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
                             		int id = (int)request.getAttribute("id");
                               		if(request.getAttribute("song") != null){
                                		Song songs = (Song)request.getAttribute("song");
                                		String name = songs.getName();
                                		String preview_text = songs.getPreview_text();
                                		String detail_text = songs.getDetail_text();
                                		String picture = songs.getPicture();
                              %>
                                <form role="form" method="post" enctype="multipart/form-data" id="form_song" action="<%=request.getContextPath()%>/admin/song/edit?id=<%=id%>">

                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%=name%>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>

                                        <select id="category" name="category" class="form-control">
                                	<%
	                            		if(request.getAttribute("categories") != null){
	                            			
	                            			List<Category> categories = (List<Category>)request.getAttribute("categories");
	                            			if(categories.size() > 0){
	                            				for(Category cat : categories){
	                            					int catId = cat.getId();
	                            					String catName = cat.getName();
                                	%>
	                                        <option value="<%=catId%>"><%=catName%></option>
	                                        <%}}} %>
                                        </select>
                                    
                                    </div>
                                    <div class="form-group">
                                    
                                        <label for="picture">Hình ảnh</label>
                                        	<%
                                        		if(!"".equals(picture)){
                                        			
                                        		
                                        	%>
											<div><img width="200px" height="200px" src="<%=request.getContextPath() %>/uploads/<%=picture%>" alt="<%=picture%>"/></div>
											<%}else{
											%>
											Không có hình ảnh
											<%} %>
                                        <input type="file" name="picture" value="<%=picture%>"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%=preview_text%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%=detail_text%></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <%} %>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>