<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

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
	padding: 24px;
	width: 400px;
	transform: translate(-15%, 0);
	border-radius: 5px;
	box-shadow: 0 0 2px 2px;
}

.title {
	margin-bottom: 20px;
	font-size: 28px;
}

.login-app input {
	width: 100%;
	margin-bottom: 16px;
	font-size: 16px;
	padding: 8px;
}

.btn {
	display: block;
	width: 100%;
	padding: 8px;
	margin-top: 4px;
	margin-bottom: 20px;
	background-color: #cd1818;
	font-size: 16px;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	border: none;
	transition: all 0.5s;
}

.btn:hover {
	background-color: rgb(226, 79, 79);
}

.separator {
	display: flex;
	margin-bottom: 20px;
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
	margin-bottom: 16px;
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
	margin-bottom: 16px;
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
				Hệ thống công nghệ hàng đầu Châu Á <br> vươn tầm thế giới
			</h1>
		</div>

		<form action="User/login.htm" class="login-app" method="post">
			<h3 class="title">Đăng nhập</h3>
			<input type="text" name="name" placeholder="Tên người dùng"> 
			<input type="password" name="pass" placeholder="Mật khẩu"> 
			<button class="btn">Xác nhận</button>
			<div class="separator">
				<div class="separator__line"></div>
				<p class="separator__or">HOẶC</p>
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
			<div class="bottom">
				<span class="question">Bạn đã mới biết đến Asian shop</span> <a href="User/register.htm">Đăng ký</a>
			</div>
			<div class="error">${message}</div>
		</form>
	</div>
</body>
</html>