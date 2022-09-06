<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resource/css/Admin/menuStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Admin/adminStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Style.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
		<div class="container">
		<h2>DANH SÁCH SẢN PHẨM</h2>
		<div class="link-export__wrapper"><a class="link-export" href="pdf/product.htm">Xuất danh sách</a></div>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/productList.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
		<table>
			<tr>
				<th>Mã</th>
				<th>Tên</th>
				<th>Mô tả</th>
				<th>Giá</th>
				<th>SLT</th>
				<th>Loại</th>
				<th>Nhà cung cấp</th>				
				<th></th>				
				<th></th>
			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
				<tr>
					<td>${s.maSP}</td>
					<td>${s.tenSP}</td>
					<td>${s.moTa}</td>
					<td><fmt:formatNumber type = "currency" value = "${s.gia}" /></td>
					<td>${s.slt}</td>
					<td>${s.loaiSP.tenLoai}</td>
					<td>${s.nhaCungCap.tenNCC}</td>
					<td><a href="Admin/productList/${s.maSP}.htm?linkDelete">Xóa</a></td>
					<td><a href="Admin/productEdit/${s.maSP}.htm?linkEdit">Sửa</a></td>
				</tr>
			</c:forEach>
		</table>
		${message}
	</div>
</body>
</html>