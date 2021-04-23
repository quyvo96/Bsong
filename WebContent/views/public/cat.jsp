<%@page import="constants.GlobalConstants"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    	<%
    		if(request.getAttribute("cats") != null){
    	    	int numerOfPages = (Integer)request.getAttribute("numerOfPages");
    	    	int numerOfItems = (Integer)request.getAttribute("numerOfItems");
    	    	int currentPage = (Integer)request.getAttribute("currentPage");
    			int offset = (currentPage-1)*GlobalConstants.NUMBER_PER_PAGE;
    			Category cats = (Category)request.getAttribute("cats");
    			String namecat = cats.getName();
    			int id_cat = cats.getId();
    			
    	%>
		<h1><%=namecat%></h1>
    </div>
    <%	
    	if(request.getAttribute("songCat") != null){
    		List<Song> songCat = (List<Song>)request.getAttribute("songCat");
    		if(songCat.size() > 0){
    			for(Song s:songCat){
    				offset = offset + 1;
    				int id =s.getId();
        	  		String name = s.getName();
        	  		String preview = s.getPreview_text();
        	  		String detail = s.getDetail_text();
        	  		Timestamp date = s.getDate_create();
        	  		int counter = s.getCounter();
        	  		String picture = s.getPicture();
        	  		String urlSlug = request.getContextPath()+ "/chi-tiet" +"/"+StringUtil.makeSlug(namecat)+"/"+ StringUtil.makeSlug(name) +"-"+id+".html";
    %>
    <div class="article">
      <h2><a href="<%=urlSlug%>" title="Đổi thay"><%=name%></a></h2>
      <p class="infopost">Ngày đăng: <%=date%> Lượt xem: <%=counter%> <a href="#" class="com"><span><%=offset%></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath()%>/uploads/<%=picture%>" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%=preview%></p>
        <p class="spec"><a href="<%=urlSlug%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%	}}}

    	if(request.getAttribute("listSongs") != null && ((List<Song>)request.getAttribute("listSongs")).size()>0){
    		String urlSlugBack = request.getContextPath()+ "/danh-muc" +"/"+ StringUtil.makeSlug(namecat) +"-"+id_cat+"/trang-"+(currentPage-1) +".html";
    		String urlSlugNext = request.getContextPath()+ "/danh-muc" +"/"+ StringUtil.makeSlug(namecat) +"-"+id_cat+"/trang-"+(currentPage+1) +".html";
    %>
    <p class="pages"><small>Trang <%=currentPage%> của <%=numerOfPages%></small>
    <%
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
    	String urlSlug = request.getContextPath()+ "/danh-muc" +"/"+ StringUtil.makeSlug(namecat) +"-"+id_cat+"/trang-"+i +".html";
    %>
    	<a href="<%=urlSlug%>"><%=i %></a>
    <%}}
    	if(currentPage < numerOfPages){
    %>
    <a href="<%=urlSlugNext%>">&raquo;</a></p>
    <%}%>
  </div>
  <%}} %>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>