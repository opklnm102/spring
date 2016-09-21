<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<html>
<head>
<title><spring:message code="itemDelete.title" /></title>
</head>
<body>
<form:form modelAttribute="item" action="delete.html">
	<form:hidden path="itemId" />
	<div align="center" class="body">
	<h2><font color="green"><spring:message
		code="itemDelete.title" /></font></h2>
	<table>
		<tr height="40px">
			<td>상품명</td>
			<td><form:input path="itemName" cssClass="itemName"
				disabled="true" /></td>
		</tr>
		<tr height="40px">
			<td>가격</td>
			<td><form:input path="price" cssClass="price" disabled="true" />&nbsp;원
			</td>
		</tr>
		<tr height="40px">
			<td>파일</td>
			<td><img
				src="<c:url value="image.html">
				<c:param name="itemId" value="${item.itemId}"></c:param>
				</c:url>" width="210" height="240"></td>
		</tr>
		<tr height="40px">
			<td>개요</td>
			<td><form:textarea path="description" cssClass="description"
				disabled="true" /></td>
		</tr>
	</table>
	<br>
	<input type="submit" value="제거" /><br>
	<br>
	<a href="index.html">돌아가기</a></div>
</form:form>
</body>
</html>