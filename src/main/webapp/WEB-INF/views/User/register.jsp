<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resource/css/Style.css' />" rel="stylesheet">
<link
	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<style type="text/css">
.container-app {
	background-color: #007aff;
	height: 100vh;
	display: flex;
	align-items: center;
}

.logo-app {
	display: flex;
	flex-direction: column;
	padding: 24px 24px;
	text-align: center;
	color: white;
	font-family: 'Lucida Sans', Arial, sans-serif;
	font-size: 20px;
}

.login-app {
	background-color: white;
	padding: 20px;
	width: 444px;
	transform: translate(-15%, 0);
	border-radius: 5px;
	box-shadow: 0 0 2px 2px;
}

.title {
	margin-bottom: 16px;
	font-size: 28px;
}

.login-app input,
.login-app select{
	width: 100%;
	margin-top: 12px;
	font-size: 16px;
	padding: 8px;
}
.input-top{
	margin-top: 0px !important;
}

.btn {
	display: block;
	width: 100%;
	padding: 8px;
	margin-bottom: 12px;
	background-color: #cd1818;
	font-size: 16px;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	border: none;
	transition: all 0.5s;
	margin-top: 12px;
}

.btn:hover {
	background-color: rgb(226, 79, 79);
}

.separator {
	display: flex;
	margin-bottom: 8px;
	align-items: center;
}

.separator__or {
	flex: 30%;
	text-align: center;
}

.separator__line {
	text-decoration: line-through;
	height: 2px;
	flex: 60%;
	background-color: #007aff;
}

.contact__list {
	display: flex;
}

.contact__item {
	display: flex;
	width: 30%;
	padding: 8px;
	color: white;
	background-color: #4285f4;
	border-radius: 5px;
	cursor: pointer;
	transition: all 0.5s;
}

.contact__item:first-child {
	margin-left: -0.2%;
	background-color: #1877f2;
}

.contact__item:last-child {
	margin-right: -0.2%;
	background-color: #000;
}

.contact__item:hover {
	opacity: 0.8;
}

.commit {
	margin-top: 8px;
	margin-bottom: 8px;
	text-align: center;
}

.bottom {
	text-align: center;
}

.commit a, .bottom a {
	color: #cd1818;
	font-weight: bolder;
}

.commit a:hover, .bottom a:hover {
	color: #7a1111;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div class="container-app">
		<div class="logo-app">
			<img src="resource/img/logoMain.gif" alt="">
			<h1>
				H??? th???ng c??ng ngh??? h??ng ?????u Ch??u ?? <br> v????n t???m th??? gi???i
			</h1>
		</div>

		<form:form action="User/register.htm" class="login-app" modelAttribute="customer">
			<h3 class="title">????ng k??</h3>			
			<form:input class="input-top" type="text" path="hoTen" placeholder="H??? v?? t??n" required="required" maxlength="30"/> 
			<form:errors class="error" path="hoTen"/>
			<form:select path="gioiTinh" id="sex">
				<option value="Nam">Nam</option>
				<option value="N???">N???</option>
			</form:select> 
			<form:input type="number" path="sdt" placeholder="S??? ??i???n tho???i" required="required" maxlength="10"/> 
			<form:errors class="error" path="sdt"/>
			<form:input type="email" path="email" placeholder="Email" required="required" maxlength="20"/> 
			<form:errors class="error" path="email"/>
			<form:input type="text" path="diaChi" placeholder="?????a ch???" required="required" maxlength="100"/>
			<form:errors class="error" path="diaChi"/>
			<input type="text" name="userName" placeholder="T??n ????ng nh???p" required="required" maxlength="10"/>
			<input class="input-bottom" type="password" name="pass" placeholder="M???t kh???u" required="required" maxlength="16"/>
			<form:input type="hidden" path="maKH" value="1"/>
			<form:input type="hidden" path="trangThai" value="1"/>
			<button class="btn" name="btnAdd">X??c nh???n</button>
			<div class="error">${message}</div>
			<div class="separator">
				<div class="separator__line"></div>
				<p class="separator__or">HO???C</p>
				<div class="separator__line"></div>
			</div>
			<div class="contact__list">
				<div class="contact__item">
					<i class="fab fa-facebook"></i>
					<p>Facebook</p>
				</div>
				<div class="contact__item">
					<i class="fab fa-google"></i>
					<p>Google</p>
				</div>
				<div class="contact__item">
					<i class="fab fa-apple"></i>
					<p>Apple</p>
				</div>
			</div>
			<p class="commit">
				B???ng vi???c ????ng k??, b???n ???? ?????ng ?? v???i AsianShop v??? <br> <a
					href="User/operatingRegulation.htm" class="commit__rule">??i???u kho???n d???ch v???</a> & <a href="User/privacyPolicy.htm"
					class="commit__security">Ch??nh s??ch b???o m???t</a>
			</p>
			<div class="bottom">
				<span class="question">B???n ???? c?? t??i kho???n</span> <a href="User/login.htm">????ng
					nh???p</a>
			</div>
		</form:form>
	</div>
</body>
</html>