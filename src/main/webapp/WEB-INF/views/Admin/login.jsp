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
	background-color: #ca0404;
	height: 100vh;
	
	align-items: center;
}

.logo-app {
	display: flex;
	flex-direction: column;
	padding: 24px 24px;
	text-align: center;
	color: white;
	font-family: 'Lucida Sans', Arial, sans-serif;
	font-size: 10px;
}

.login-app {
	background-color: white;
	padding: 24px;
	width: 400px;
	transform: translate(0, 0);
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
</style>

<title>Đăng nhập</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div class="container-app">
		<div class="logo-app">
			<img src="resource/img/FPTShop_logo.png" alt="">
			<h1>
				Luôn tận tâm trong công việc, khách hàng là thượng đế
			</h1>
		</div>

		<form action="Admin/login.htm" class="login-app" method="post">
			<h3 class="title">Đăng nhập</h3>
			<input type="text" name="name" placeholder="Tên người dùng">
			<input type="password" name="password" placeholder="Mật khẩu"> 
			<button class="btn">Xác nhận</button>
			<div class="error">${message}</div>
		</form>
	</div>
</body>
</html>