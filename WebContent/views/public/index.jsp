<%@page import="constants.GlobalConstants"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
      <%
      	
    	if(request.getAttribute("listSongs") != null){
        	int numerOfPages = (Integer)request.getAttribute("numerOfPages");
        	int numerOfItems = (Integer)request.getAttribute("numerOfItems");
        	int currentPage = (Integer)request.getAttribute("currentPage");
        	int offset = (currentPage-1)*GlobalConstants.NUMBER_PER_PAGE;
    		List<Song> listSongs = (List<Song>)request.getAttribute("listSongs");
    		if(listSongs.size() > 0){
    			int i = 0;
    			for(Song s:listSongs){
    				offset++;
    				int id = s.getId();
    				String name = s.getName();
    				String preview_text = s.getPreview_text();
    				String detail_text = s.getDetail_text();
    				String picture =s.getPicture();
    				Timestamp date_create =s.getDate_create();
    				String nameSong = s.getCat().getName();
    				String urlSlug = request.getContextPath()+ "/chi-tiet" +"/"+StringUtil.makeSlug(nameSong)+"/"+ StringUtil.makeSlug(name) +"-"+id+".html";
    				int count = s.getCounter();

    %>
    <div class="article">
      <h2><a href="<%=urlSlug%>" title="<%=name%>"><%=name%></a></h2>
      <p class="infopost">Ngày đăng: <%=date_create%>. Lượt xem: <%=count%> <a href="#" class="com"><span><%=offset%></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/uploads/<%=picture%>" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%=preview_text%></p>
        <p class="spec"><a href="<%=urlSlug%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%	}}
    %>
    <p class="pages"><small>Trang <%=currentPage%> của <%=numerOfPages%></small>
    <%
    	String urlSlugBack = request.getContextPath()+ "/trang-chu/trang-"+(currentPage-1) +".html";
    	String urlSlugNext = request.getContextPath()+ "/trang-chu/trang-"+(currentPage+1) +".html";
    	if(currentPage > 1){
    %>
    	<a href="<%=urlSlugBack%>">&laquo;</a>
    <%}%>
    <%
    	for(int i = 1; i<=numerOfPages; i++){
    		if(currentPage == i){
    	
    %>
    <span><%=i %></span>
    <%		}else{
    	String urlSlug = request.getContextPath()+ "/trang-chu/trang-"+(i)+".html";
    %>
    	<a href="<%=urlSlug%>"><%=i %></a>
    <%}}
    	if(currentPage < numerOfPages){
    %>
    <a href="<%=urlSlugNext%>">&raquo;</a></p>
    <%}%>
  </div>
  <%} %>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
