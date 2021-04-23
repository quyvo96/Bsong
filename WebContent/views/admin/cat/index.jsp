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
                <h2>Quản lý danh mục</h2>
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
                                    <a href="<%=request.getContextPath()%>/quan-ly/danh-muc/them-danh-muc.html" class="btn btn-success btn-md">Thêm</a>
                                </div>
                            </div>
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
                            
			
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	@SuppressWarnings("unchecked")
                                	List<Category> categories = (List<Category>)request.getAttribute("categories");
                                	if(categories != null && categories.size() > 0){
                                		for(Category category: categories){
                                			int id = category.getId();
                                			String name = category.getName();
                                			String urlEdit = request.getContextPath() + "/quan-ly/danh-muc/sua-danh-muc-" + id +".html";
                                			String urlDel = request.getContextPath() + "/admin/cat/del?id=" + id ;

                                %>

                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không!')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                               <% }}%>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>