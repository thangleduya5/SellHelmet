<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
		<h2>DANH SÁCH KHÁCH HÀNG</h2>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/customer.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
		<table>
			<tr>
				<th>Mã KH</th>
				<th>Họ và tên</th>
				<th>Giới tính</th>
				<th>Số điện thoại</th>
				<th>Email</th>
				<th>Địa chỉ</th>
				<th></th>
			</tr>
			<c:forEach var="c" items="${pagedListHolder.pageList}">
				<tr>
					<td>${c.maKH}</td>
					<td>${c.hoTen}</td>
					<td>${c.gioiTinh}</td>
					<td>${c.sdt}</td>
					<td>${c.email}</td>
					<td>${c.diaChi}</td>
					<c:choose>
						<c:when test = "${c.trangThai==1}">
							<td><a href="Admin/customer/${c.maKH}.htm?linkBlock">Khóa</a></td>
         				</c:when>
				         <c:otherwise>
							<td><a href="Admin/customer/${c.maKH}.htm?linkUnBlock">Mở khóa</a></td>
         				</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		<div class="message" style="text-align: center; color: red;">${message}</div>
	</div>
</body>
</html>