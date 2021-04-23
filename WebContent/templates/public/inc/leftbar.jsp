<%@page import="utils.StringUtil"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="models.Category"%>
<%@page import="models.Song"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="get" action="<%=request.getContextPath() %>/home">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath()%>/resources/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  	if(request.getAttribute("listcats") != null){
  		
  		List<Category> listcats = (List<Category>)request.getAttribute("listcats");
  		if(listcats.size() > 0){
  			for(Category c : listcats){
  				int idCat = c.getId();
  				String nameCat = c.getName();
  				String urlSlug = request.getContextPath()+ "/danh-muc" +"/"+ StringUtil.makeSlug(nameCat) +"-"+idCat+"/trang-1"+".html";
  %>
    			<li><a href="<%=urlSlug%>"><%=nameCat%></a></li>
  <%}}} %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
        <%
    	if(request.getAttribute("listSongs") != null){
    		List<Song> newSongs = (List<Song>)request.getAttribute("listSongs");
    		if(newSongs.size() > 0){
    			int i = 0;
    			for(Song s:newSongs){
    				i++;
    				int id = s.getId();
    				String name = s.getName();
    				String preview_text = s.getPreview_text();
    				try{
        				String arrayText[] = preview_text.split("\\s", 11);
        				String text = "";
        				for(int j =0; j < 10; j ++){
        					text = text +" "+ arrayText[j]+ " ";
        					//break;
        				}
        				preview_text = text + "...";
    				}catch(Exception e){
    					
    				}
    				String nameSong = s.getCat().getName();
    				String urlSlug = request.getContextPath()+ "/chi-tiet" +"/"+StringUtil.makeSlug(nameSong)+"/"+ StringUtil.makeSlug(name) +"-"+id+".html";
    				if(i<=5){
    %>
    <li><a href="<%=urlSlug%>"><%=name %></a><br />
      <%=preview_text %></li>
 <%}}}} %>
  </ul>
</div>