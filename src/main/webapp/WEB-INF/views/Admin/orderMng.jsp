<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<h2>DANH SÁCH PHIẾU ĐẶT</h2>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/orderMng.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />

		</div>
		<table>
			<tr>
				<th>Mã phiếu đặt</th>
				<th>Ngày tạo</th>
				<th>Nhà cung cấp</th>
				<th>Nhân viên</th>
				<th></th>
				<th></th>

			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
				<tr>
					<td>${s.maPD}</td>
					<td><fmt:formatDate pattern="dd-MM-yyyy" value="${s.ngayTao}" /></td>
					<td>${s.nhaCungCap.tenNCC}</td>
					<td>${s.nhanVien.hoTen}</td>
					<c:if test="${empty s.phieuNhap}">
						<td><a href="Admin/order/${s.maPD}.htm?linkDelete">Xóa</a></td>
						<td><a href="Admin/import/${s.maPD}.htm">Tạo phiếu nhập</a></td>
					</c:if>
					<c:if test="${not empty s.phieuNhap}">
						<td><a href="Admin/orderDetail/${s.maPD}.htm?">Xem CT phiếu đặt</a></td>
						<td><a href="Admin/importDetail/${s.maPD}.htm">Xem chi tiết
								phiếu nhập</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>