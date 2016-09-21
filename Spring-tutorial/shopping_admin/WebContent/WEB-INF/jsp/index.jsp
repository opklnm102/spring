<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="itemMenu.title" /></title>
</head>
<body>
	<form:form method="POST" action="search.html">
		<div align="center" class="body">
			<h2>
				<spring:message code="itemMenu.title" />
			</h2>
			상품명 검색 <input type="text" name="itemName" /> <input type="submit"
				value="검색" /><a href="create.html">상품 등록</a>
			<table border="1">
				<tr class="header">
					<th align="center" width="80">상품ID</th>
					<th align="center" width="320">상품명</th>
					<th align="center" width="100">가격</th>
					<th align="center" width="80">편집</th>
					<th align="center" width="80">제거</th>
				</tr>
				<c:forEach items="${itemList }" var="item">
					<tr class="record">
						<td align="center">${item.itemId }</td>
						<td align="left">${item.itemName }</td>
						<td align="right"><f:formatNumber type="CURRENCY"
								currencySymbol="" value="${item.price }" minFractionDigits="0" />원
						</td>

						<td align="center"><a
							href="<c:url value="edit.html">
				<c:param name="itemId" value="${item.itemId}"></c:param>
						 		</c:url>">상품
								편집</a></td>
						<td align="center"><a
							href="<c:url value="confirm.html">
							<c:param name="itemId" value="${item.itemId }"></c:param>
				</c:url>">상품
								제거</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
</body>
</html>