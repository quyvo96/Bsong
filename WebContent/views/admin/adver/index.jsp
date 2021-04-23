<%@page import="models.Adver"%>
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
                <h2>Quản lý quảng cáo</h2>
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
                                    <a href="<%=request.getContextPath()%>/quan-ly/quang-cao/them-quang-cao.html" class="btn btn-success btn-md">Thêm</a>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên</th>
                                        <th>Website</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	List<Adver> listadver = (List<Adver>)request.getAttribute("listadver");
                                	if(request.getAttribute("listadver") != null){
                                		
                                		if(listadver.size() > 0){
                                			for(Adver a : listadver){
                                				int id = a.getId();
                                				String name = a.getName();
                                				int count = a.getCount();
                                				String picture = a.getPicture();
                                				String web = a.getWeb();		
                                    			String urlEdit = request.getContextPath() + "/quan-ly/quang-cao/sua-quang-cao-" + id +".html";
                                    			String urlDel = request.getContextPath() + "/admin/adver/del?id=" + id ;
                                %>
                                    <tr>
                                        <td><%=id %></td>
                                        <td class="center"><%=name %></td>
                                        <td class="center"><%=web %></td>
                                        <td class="center"><%=count %></td>
                                        <td class="center">
                                        	<%
                                        		if(!"".equals(picture)){
                                        			
                                        		
                                        	%>
											<img width="310" height="107" src="<%=request.getContextPath() %>/uploads/<%=picture%>" alt="<%=picture%>"/>
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