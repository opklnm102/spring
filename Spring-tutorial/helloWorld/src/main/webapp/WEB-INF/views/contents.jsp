<html>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<body>

<%
			String strQuery = "select subject, name, created, hit, contents from bbs_studypds where id=" ;
			strQuery += request.getParameter("idx") ;
		Connection con = null ;
		Statement st = null ;
		String sName="", sTitle="", sCreated="", sContents="", sHit="" ;
		ResultSet rs ;		
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
			String url = "jdbc:ucanaccess://C:/CS-Board2003.mdb";
			con = DriverManager.getConnection(url); 
			st = con.createStatement() ;
			rs = st.executeQuery(strQuery) ;
            
			rs.next() ;
			sTitle = rs.getObject(1).toString() ;
			sName = rs.getObject(2).toString() ;
			String sDate = "" ;
			sDate += " " + rs.getObject(3).toString();
			int ndx = sDate.indexOf("00:");
			sCreated = sDate.substring(0, ndx);
			sContents = rs.getObject(5).toString() ;
			sHit = rs.getObject(4).toString() ;
			
			st.close();
			con.close() ;
		}
		catch (SQLException e) {
			out.println("Error on sqlDB ") ;
		}
		catch (Exception e) {
			out.println("Error on DB ") ;
		}
%>

<table width="800" height="100" border="1">
<tr>
	<td  colspan="6"><center><B> <%= sTitle %></b> </center></td>
</tr>

<tr>
<td> <b>작성자</b> </td> 
<td><%= sName %>     </td> 
<td> <b>조회수 </b></td> 
<td> <%= sHit %> </td>
<td> <b>등록일</b></td>
<td> <%= sCreated %> </td>
</tr>

<tr>
<td  colspan="6"> 
<%= sContents %>
</td>
</tr>

</table>

	<a href="list">목록 돌아가기</a>
</body>
</html>
 