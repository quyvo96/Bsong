<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
	int id1 = 0;
  	if(request.getAttribute("song") != null){
  		Song song = (Song)request.getAttribute("song");
  		id1 = song.getId();
  		String name = song.getName();
  		String detail = song.getDetail_text();
  		Timestamp date = song.getDate_create();
  		int counter = song.getCounter();
  		String picture = song.getPicture();
  %>
    <div class="article">
      <h1><%=name%></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=date%> Lượt xem: <%=counter%></p>
      <img src="<%=request.getContextPath() %>/uploads/<%=picture%>" width="177" height="213" alt="<%=name %>" class="fl" />
      <div class="vnecontent">
         <%=detail%>
      </div>
    </div>
    <%} %>
    <div class="article">
    <% if(id1 == 0){
    	%>
    	<h2>Không có bài hát!</h2>
    <%}else{
    	%>
    	<h2>Bài viết liên quan</h2>
   <%
	if(request.getAttribute("songCat") != null){
		List<Song> songCat = (List<Song>)request.getAttribute("songCat");
		int i = 0;
		if(songCat.size() > 0){
			for(Song s:songCat){
				i++;
				int idSong =s.getId();
    	  		String name = s.getName();
    	  		String preview = s.getPreview_text();
    	  		String detail = s.getDetail_text();
    	  		Timestamp date = s.getDate_create();
    	  		int counter = s.getCounter();
    	  		String picture = s.getPicture();
    	  		if(id1 != idSong && i<5){   
   %>
          <div class="clr"></div>
	      <div class="comment"> <a href=""><img src="<%=request.getContextPath()%>/uploads/<%=picture%>" width="40" height="40" alt="" class="userpic" /></a>
	        <h2><a href="<%=request.getContextPath() %>/detail?id=<%=idSong%>"><%=name%></a></h2>
	        <p><%=preview%></p>
	      </div>
    <%}}}}} %>
      
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
