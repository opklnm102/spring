<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="servera.spring.hello.Article" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>��������</h1>
		<hr>
		<form>
			<div class="center">
			<select name="search_type">
				<option>����</option selected>
				<option>�ۼ���</option>
				<option>����</option>
			</select>
			<input type="search" name="search_text"/>
			<input type="submit" id="bt" value="search">
			</div>
		</form>
	</div>
	<div>
		<p align="right">�ѱ� ${noTotalArticles}&nbsp;&nbsp;������ 1/${noTotalPages}</p>
		<table>
			<tr>
				<th width="120">��ȣ</th>
				<th width="600">����</th>
				<th width="160">�ۼ���</th>
				<th width="160">�����</th>
				<th width="160">��ȸ��</th>
			</tr>

<%
		ArrayList<Article> articles = (ArrayList<Article>) request.getAttribute("articleList");
			int count = articles.size() ;
		for (Article art : articles) {
			out.print("<tr>") ;
			out.print("<td>" + art.getNo() + "</td>") ;
			
			String sHref = "" ;
			sHref += "<a href='contents?idx=" + art.getKey() + "'>" ;
			out.print("<td>" + sHref + "") ;
			out.print(art.getTitle() + "</a> </td>") ;
			//out.print("<td>" + art.getTitle() + "</td>") ;
			
			out.print("<td>" + art.getName() + "</td>") ;
			out.print("<td>" + art.getDate() + "</td>") ;
			out.print("<td>" + art.getHit() + "</td>") ;
			out.print("</tr>") ;
		}
			
%>		

		</table>
		<br> DB Open Result : ${strError}

		<div class="center">
		<input type="button" value="<<" onclick="">
		<input type="button" value="< PREV" onclick="">
		<input id="num" type="button" value="1" onclick="">
		<input type="button" value="NEXT >" onclick="">
		<input type="button" value=">>" onclick="">
		</div>
	</div>

</body>
</html>