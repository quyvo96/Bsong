<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Đăng Nhập</h2>
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
                            	if(request.getParameter("msg")!=null){
                            %>
	                            <div class="alert alert-danger" role="alert">
								  <h4>Sai tên đăng nhập hoặc mật khẩu!</h4>
								  <h4>Vui lòng nhập lại!</h4>
								</div>
                            	
                            <% 
                            		}
                             %>
                                <form role="form" method="post" id="form_login" action="<%=request.getContextPath()%>/auth/login">
                                    <div class="form-group">
                                        <label for="name">Tên Đăng Nhập</label>
                                        <input type="text" id="name" value="" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Đăng Nhập</button>
                                </form>
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
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>