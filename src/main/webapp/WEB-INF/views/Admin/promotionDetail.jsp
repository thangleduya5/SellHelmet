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
<link href="<c:url value='/resource/css/Style.css' />" rel="stylesheet">
<link
	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<form action="Admin/promotionDetail/${id}.htm" method="post"
			class="form-horizontal">

			<div id="template" class="element">
				<label>Tên sản phẩm</label> <select name="maSP"
					class="product__item">
					<c:forEach var="i" items="${dsSanPham}">
						<option value="${i.maSP}">${i.tenSP}</option>
					</c:forEach>
				</select> <label>Giảm giá</label> <input type="number" min="1" max="99"
					name="giamGia" required="required" value="${giamGia}" />
			</div>
			<button style="margin-top: 20px" onclick="checkSubmit(event);"
				name="${btnStatus}">Lưu</button>
		</form>
		<h2>CHI TIẾT KHUYẾN MÃI</h2>
		<div class="link-export__wrapper">
			<a class="link-export" href="pdf/product.htm">Xuất danh sách</a>
		</div>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/promotionDetail/${s.pk.khuyenMai.maKM}.htm"
			var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
		<table>
			<tr>
				<th>Mã sản phẩm</th>
				<th>Tên sản phẩm</th>
				<th>% Giảm giá</th>
				<th>Xóa</th>
				<th>Sửa</th>
			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
				<tr>
					<td>${s.pk.sanPham.maSP}</td>
					<td>${s.pk.sanPham.tenSP}</td>
					<td>${s.giamGia}</td>
					<th><a href="Admin/promotionDetail/${id}.htm?maSP=${s.pk.sanPham.maSP}&linkDelete">Xóa</a></th>
					<th><a href="Admin/promotionDetail/${id}.htm?maSP=${s.pk.sanPham.maSP}&giamGia=${s.giamGia}&linkEdit">Sửa</a></th>
				</tr>
			</c:forEach>
		</table>
		${message}
	</div>
</body>
</html>