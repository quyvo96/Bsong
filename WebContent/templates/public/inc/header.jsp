<%@page import="models.Adver"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath()%>/resources/public/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/public/css/coin-slider.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/public/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/public/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/public/js/script.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/public/js/coin-slider.min.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="">BSong <small>Một dự án khóa JAVA tại VinaEnter Edu</small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li class="active"><a href="<%=request.getContextPath()%>/trang-chu.html"><span>Trang chủ</span></a>
          <li><a href="<%=request.getContextPath()%>/lien-he.html"><span>Liên hệ</span></a></li>
          <li><a href="<%=request.getContextPath()%>/login.html"><span>Đăng Nhập</span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider">
        <%
    	if(request.getAttribute("listadver") != null){
    		List<Adver> listadver = (List<Adver>)request.getAttribute("listadver");
    		if(listadver.size() > 0){
    			int i = 0;
    			for(Adver a:listadver){
    				int id = a.getId();
    				String picture = a.getPicture();
        %>
        <a href="<%=request.getContextPath() %>/adver?id_adver=<%=id%>"><img src="<%=request.getContextPath() %>/uploads/<%=picture%>" width="935" height="307" alt="" /></a>
        <%}}} %>
        </div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">