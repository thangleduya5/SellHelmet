<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
.info-app {
	padding: 24px;
	display: flex;
	align-items: center;
	margin: 200px 100px;
	margin-bottom: 100px;
	padding-top: 12px;
	position: relative;
}

.info-login {
	text-align: center;
}

.info-login img {
	width: 50%;
	display: block;
	margin-bottom: 12px;
}

.info-login p {
	color: #007bff;
	font-size: 20px;
	cursor: pointer;
}


.info-user {
	transform: translate(-30%, 0);
	/* border: solid 1px; */
	padding: 24px;
	border-radius: 5px;
	box-shadow: 0 0 4px 4px rgb(134, 131, 131);
}

.info-form {
	margin: 24px 0;
}

label {
	display: inline-block;
	width: 120px;
}

input {
	font-size: 20px;
	width: 300px;
	padding: 4px;
	border: solid 1px;
}

h1 {
	text-align: center;
}

.form__item {
	margin-bottom: 12px;
	font-size: 20px;
}

button, .a-btn {
	padding: 8px 24px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	border: none;
	font-size: 20px;
	text-decoration: none;
}

.info__btn, .pass__btn {
	display: flex;
	margin-top: 24px;
}
.a-btn:hover,
button:hover {
	background-color: blue;
}

#overlay {
	position: fixed;
	background-color: #18171790;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	opacity: 1;
	display: none;
}

.pass-form {
	padding: 24px;
	border-radius: 5px;
	box-shadow: 0 0 4px 4px rgb(134, 131, 131);
	display: inline-block;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
}

.btn__item {
	display: inline-block;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<header class="header-app">
		<%@include file="/WEB-INF/views/User/Menu/header.jsp"%>
	</header>

    <div class="info-app">
        <div class="info-login">
            <img src="resource/img/USER.png" alt="">
            <a class="a-btn" href="User/changePass.htm">Thay đổi mật khẩu</a>
        </div>
        <div class="info-user">
            <h1>THÔNG TIN CÁ NHÂN</h1>
            <form:form class="info-form" action="User/info.htm" modelAttribute="customer">
                <div class="form__item">
                    <label for="">Họ và tên</label>
					<form:input type="text" path="hoTen" placeholder="Họ và tên"/> 
					<form:errors class="error" path="hoTen"/>                
				</div>
                <div class="form__item">
                    <label for="">Số điện thoại</label>
					<form:input type="number" path="sdt" placeholder="Số điện thoại"/> 
					<form:errors class="error" path="sdt"/>
                </div>
                <div class="form__item">
                    <label for="">Email</label>
					<form:input type="email" path="email" placeholder="Email"/> 
					<form:errors class="error" path="email"/>
			    </div>
                <div class="form__item">
                    <label for="">Địa chỉ</label>
					<form:input type="text" path="diaChi" placeholder="Địa chỉ"/>
					<form:errors class="error" path="diaChi"/>                
				</div>
                <form:input type="hidden" path="maKH"/>
                <form:input type="hidden" path="gioiTinh"/>
                <form:input type="hidden" path="taiKhoan.tenDN"/>
                
                <form:input type="hidden" path="trangThai"/>
                <button class="info__btn" name="btnUpdate">Cập nhật thông tin</button>
                ${message}
            </form:form>
        </div>
    </div>

	<header class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</header>

</body>
</html>