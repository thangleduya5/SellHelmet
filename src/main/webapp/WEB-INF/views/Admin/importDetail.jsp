<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<h2>DANH SÁCH SẢN PHẨM TRONG PHIẾU NHẬP</h2>
		<table>
			<tr>
				<th>Mã SP</th>
				<th>Tên SP</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
			</tr>
			<c:forEach var="i" items="${ctPhieuNhap}">
				<tr>
					<td>${i.pk.sanPham.maSP}</td>
					<td>${i.pk.sanPham.tenSP}</td>
					<td>${i.sl}</td>
					<td><fmt:formatNumber type = "currency" value = "${i.gia}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>