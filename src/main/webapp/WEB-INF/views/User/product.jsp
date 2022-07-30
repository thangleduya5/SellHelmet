<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<style type="text/css">
.phone-app {
	margin: 200px 100px;
	margin-bottom: 100px;
}

.phone-wrap {
	display: flex;
	margin-top: 24px;
}

.phone__item {
	border-radius: 10px;
	box-shadow: 0 0 2px 2px #ddd;
}

.phone-left {
	font-size: 24px;
	flex: 50%;
	padding: 24px;
}

.cost {
	display: flex;
	margin-top: 24px;
}

.cost__discount {
	margin-left: 10%;
	color: #cd1818;
	font-weight: bolder;
}

.cost__unit {
	margin-right: 10%;
	text-decoration: line-through;
}
.cost__unit-action{
	margin-right: 10%;
	color: #cd1818;
	font-weight: bolder;
}

.phone__color {
	margin: 24px 0;
	text-align: center;
}

.phone__color input {
	margin-left: 8px;
}

.phone__amount{
	font-size: 24px;
	width: 50px;
	margin-left: 20px;
}

.btn__buyMain {
	font-size: 28px;
	display: block;
	width: 80%;
	text-align: center;
	margin-top: 24px;
	background-color: #cd1818;
	color: white;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	padding: 8px;
}

/*slide*/
.slide {
	position: relative;
	width: 70%;
}

.slide:hover .btn__slide {
	display: block;
}

.btn__slide {
	background-color: white;
	opacity: 0.7;
	border-radius: 50%;
	padding: 8px 16px;
	cursor: pointer;
	position: absolute;
	font-size: 32px;
	top: 50%;
	transform: translate(0, -50%);
	transition: all 0.5s;
	display: none;
}

#btn__slide--next {
	right: 0;
}

.btn__slide:hover {
	opacity: 1;
	box-shadow: 0 0 2px 2px;
}

.slide__item {
	display: none;
}

.slide__item img {
	width: 100%;
}

.slide__item--active {
	display: block;
}

/*end of slide*/
.phone-right {
	padding: 24px 100px;
	flex: 45%;
	margin-left: 5%;
}

.parameter-tech {
	margin: 0;
	margin-top: 24px;
	border-collapse: collapse;
}

.parameter-tech td {
	border: 1px solid #ddd;
	padding: 8px;
}

.parameter-tech tr:nth-child(even) {
	background-color: #f2f2f2;
}

.parameter-tech tr:hover {
	background-color: #ddd;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<header class="header-app">
		<%@include file="/WEB-INF/views/User/Menu/header.jsp"%>
	</header>

	<div class="phone-app">
		<h1 class="phone__name">${p.tenSP}</h1>
		<hr>
		<div class="phone-wrap">
			<form:form class="phone-left phone__item" action="User/product.htm" modelAttribute="detailBill">
				<div class="slide">
					<div class="slide__list">
						<div class="slide__item slide__item--active">
							<img src="resource/img/imgProduct/${p.hinhAnh}">
						</div>
					</div>
				</div>
				<div class="cost">
					<c:if test="${discount>0}">
						<span class="cost__discount">- ${discount} %</span> 
						<span class="cost__unit"> <fmt:formatNumber type="currency" value="${p.gia}"/> </span>	
					</c:if>
					<c:if test="${discount==0}">
						<span class="cost__unit-action"> <fmt:formatNumber type="currency" value="${p.gia}"/> </span>				
					</c:if>
				</div>
				<label style="margin-left: 100px;">Số lượng:</label>
				<form:input class="phone__amount" type="number" path="sl"/>
				<form:errors class="error" path="sl"/>   
				<form:input type="hidden" path="gia"/>
				<form:input type="hidden" value="${p.maSP}" path="pk.sanPham.maSP"/>
				<button class="btn__buyMain" name="btnBuy">MUA NGAY</button>
				<div class="error">${message}</div> 
			</form:form>
			<div class="phone-right phone__item">
				<h2 class="tech__title">Thông số kĩ thuật</h2>
				<table class="parameter-tech">
					<tbody>
						<tr>
							<td>Loại mũ</td>
							<td>${p.loaiSP.tenLoai}</td>
						</tr>
						<tr>
							<td>Thương hiệu</td>
							<td>${p.nhaCungCap.tenNCC}</td>
						</tr>
						<tr>
							<td>Mô tả</td>
							<td>${p.moTa}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<header class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</header>
	<script type="text/javascript" src="<c:url value='/resource/javascript/slide.js' />"></script>
</body>
</html>