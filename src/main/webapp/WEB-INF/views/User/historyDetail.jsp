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
		<c:url value="User/history/idBill=${idBill}.htm?linkDetail" var="pagedLink">
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
			<div class="title__item">Mã sản phẩm</div>
			<div class="title__item">Tên sản phẩm</div>
			<div class="title__item">Đơn giá</div>
			<div class="title__item">Số lượng</div>
		</div>
		<div class="cart__list">

			<c:forEach var="b" items="${pagedListHolder.pageList}">
				<div class="cart__item">
					<div class="cart-detail__item"><label >${b.pk.sanPham.maSP}</label></div>	
					<div class="cart-detail__item"><label >${b.pk.sanPham.tenSP}</label></div>
					<div class="cart-detail__item"><fmt:formatNumber type = "currency" value = "${b.gia}" /></div>
					<div class="cart-detail__item"><label >${b.sl}</label></div>
				</div>
			</c:forEach>

		</div>
	</div>

	<footer class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</footer>
</body>
</html>