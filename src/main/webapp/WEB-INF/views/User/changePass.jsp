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

button {
	padding: 8px 24px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	border: none;
	font-size: 20px;
}

.info__btn, .pass__btn {
	display: flex;
	margin-top: 24px;
}

button:hover {
	background-color: blue;
}


.pass-form {
	padding: 24px;
	border-radius: 5px;
	box-shadow: 0 0 4px 4px rgb(134, 131, 131);
	display: inline-block;
	position: absolute;
	top: calc(80px + 50%);
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
}

.btn__item {
	display: inline-block;
}
footer{
	position: fixed;
	left: 0;
	right: 0;
	bottom: 0;
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
        <form class="pass-form" id="pass-form" action="User/changePass.htm" method="post" >
            <div class="form__item">
                <label for="">Mật khẩu cũ</label>
                <input name="oldPass" value="${oldPass}" type="password">
                <div class="error">${oldPassEr}</div>
            </div>
            <div class="form__item">
                <label for="">Mật khẩu mới</label>
                <input name="newPass" value="${newPass}" type="password">
                <div class="error">${newPassEr}</div>
            </div>
            <div class="form__item">
                <label for="">Nhập lại</label>
                <input name="newPassReset" value="${newPassReset}" type="password">
                <div class="error">${newPassResetEr}</div>
            </div>
            <div class="pass__btn">
                <button name="btnUpdatePass">Lưu</button>
            </div>
        </form>
    </div>

	<footer class="footer-app">
		<%@include file="/WEB-INF/views/User/Menu/footer.jsp"%>
	</footer>

</body>
</html>