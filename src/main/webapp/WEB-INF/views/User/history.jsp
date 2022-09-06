<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resource/css/User/menuStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Style.css' />" rel="stylesheet">
<link
	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
<style type="text/css">
.cart-app {
	margin: 200px 100px;
	font-size: 20px;
	margin-bottom: 300px;
}

.cart-app__title {
	display: flex;
	margin-bottom: 24px;
	border-radius: 5px;
	box-shadow: 0 0 2px 2px rgb(202, 195, 195);
	text-align: center;
	padding: 16px 0;
}


.title__item, .cart-detail__item {
	flex: 1;
	/* border: solid 1px; */
}

input {
	width: 40px;
	display: inline-block;
	font-size: 20px;
	border: none;
}

.cart__delete {
	width: 120px;
	display:block;
	padding: 4px;
	font-size: 20px;
	border: none;
	cursor: pointer;
	color: white;
	background-color: #007bff;
	border-radius: 4px;
	transition: all 0.5s;
}

.cart__delete:hover{
	background-color: #095bb3;
}

.cart__item {
	display: flex;
	align-items: center;
	text-align: center;
	border-radius: 5px;
	box-shadow: 0 0 2px 2px rgb(202, 195, 195);
	margin-bottom: 12px;
	padding: 12px 0;
}

</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<header class="header-app">
		<%@include file="/WEB-INF/views/User/Menu/header.jsp"%>
	</header>


	<div class="cart-app">
	
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="User/history.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
				<style type="text/css">
					.fs-search{
					display: none;
					}
				</style>
		</div>
	
		<div class="cart-app__title">
			<div class="title__item">Hóa đơn</div>
			<div class="title__item">Trạng thái</div>
			<div class="title__item">Họ tên nhận</div>
			<div class="title__item">SDT nhận</div>
			<div class="title__item">Ngày tạo</div>
			<div class="title__item">Ngày nhận</div>
			<div class="title__item">Số tiền</div>
			<div class="title__item">Họ tên nhân viên giao</div>
			<div class="title__item">Thao tác</div>
		</div>
		<div class="cart__list">

			<c:forEach var="b" items="${pagedListHolder.pageList}">
				<div class="cart__item">
					<div class="cart-detail__item"><label >${b.maDH}</label></div>	
					<div class="cart-detail__item"><label >
						<c:if test="${b.trangThai==1 && b.nhanVienD == NULL}">Chờ xác nhận</c:if>
						<c:if test="${b.trangThai==1 && b.nhanVienD != NULL}">Đang giao</c:if>
						<c:if test="${b.trangThai==2}">Đã hoàn thành</c:if>
						<c:if test="${b.trangThai==-1}">Đã hủy</c:if>
					</label></div>					
					<div class="cart-detail__item"><label >${b.hoTenNN}</label></div>
					<div class="cart-detail__item"><label >${b.sdtNN}</label></div>
					<div class="cart-detail__item"><label ><fmt:formatDate pattern = "dd-MM-yyyy" value = "${b.ngayTao}" /></label></div>
					<div class="cart-detail__item"><label ><fmt:formatDate pattern = "dd-MM-yyyy" value = "${b.ngayNhan}" /></label></div>

					<div class="cart-detail__item"><fmt:formatNumber type = "currency" value = "${b.tongTien}" /></div>
					<div class="cart-detail__item"><label >${b.nhanVienG.hoTen}</label></div>

					<div class="cart-detail__item">
						<a class="cart__delete" href="User/history/idBill=${b.maDH}.htm?linkDetail">Xem chi tiết</a>
					</div>			
				</div>
			</c:forEach>

		</div>
	</div>

	<footer class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</footer>
</body>
</html>