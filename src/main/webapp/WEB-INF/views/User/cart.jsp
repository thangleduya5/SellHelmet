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

.title__product, .cart__product {
	flex: 2;
}

.cart__product {
	text-align: left;
	transform: translate(100px, 0);
}

.cart__product p {
	display: inline-block;
	margin-left: 12px;
	width: 70%;
	position: absolute;
	top: 50%;
	transform: translate(0, -50%);
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
	width: 80px;
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

.cart__product img {
	width: 10%;
}

.total-pay {
	float: right;
	margin-top: 24px;
	width: 100%;
	text-align: right;
}

.btn__buy {
	float: right;
	text-align:center;
	margin-top: 12px;
	width: 300px;
	font-size: 24px;
	padding: 12px;
	border: none;
	background-color: #cd1818;
	color: white;
	border-radius: 8px;
	transition: all 0.5s;
}

.btn__buy:hover{
	background: #ae1427;
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
		<c:url value="User/cart.htm" var="pagedLink">
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
			<div class="title__product">Sản phẩm</div>
			<div class="title__item">Đơn giá</div>
			<div class="title__item">Số lượng</div>
			<div class="title__item">Số tiền</div>
			<div class="title__item">Thao tác</div>
		</div>
		<div class="cart__list">
		<%! int total = 0; %> 
		<%if(total>0) { total= 0; }%>
		<c:forEach var="b" items="${pagedListHolder.pageList}">
			<div class="cart__item">
				<div class="cart__product">
					<img src="resource/img/imgProduct/${b.pk.sanPham.hinhAnh}"	width="50" alt="">
					<p>${b.pk.sanPham.tenSP}</p>
				</div>
				
				<div class="cart-detail__item"><fmt:formatNumber type = "currency" value = "${b.gia}" /></div>
				<div class="cart-detail__item">
					<label >${b.sl}</label>
				</div>				
				<div class="cart-detail__item"><fmt:formatNumber type = "currency" value = "${b.gia*b.sl}" /></div>
				<div class="cart-detail__item">
					<a class="cart__delete" href="User/cart/idBill=${b.pk.donHang.maDH}+idProduct=${b.pk.sanPham.maSP}.htm?linkDelete">Xóa</a>
				</div>
			</div>
		</c:forEach>

		</div>
		<p class="total-pay">Tổng thanh toán: <fmt:formatNumber type="currency" value="${sum}" /></p>
		
		<c:if test="${sum != 0 && message == null}">
			<a class="btn__buy" href="User/payment.htm">Thanh toán</a>		
		</c:if>
		<p style="color: red; text-align: center;">${message}</p>
		
	</div>

	<header class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</header>
</body>
</html>