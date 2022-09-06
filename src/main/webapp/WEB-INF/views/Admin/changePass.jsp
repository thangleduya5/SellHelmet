<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resource/css/Admin/menuStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Style.css' />" rel="stylesheet">
<link
	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">


<style type="text/css">
.info-app {
	padding: 24px;
	display: flex;
	align-items: right;
	margin: 100px 100px;
	margin-bottom: 100px;
	margin-left: 350px;
	padding-top: 12px;
	position: relative;
}
.infos{
    margin: 24px 0;
}
.info-login {
	transform: translate(+5%, 0);
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

.info-login a:hover {
	color: blue;
}

.info-user {
	transform: translate(+10%, 0);
	/* border: solid 1px; */
	padding: 24px;
	border-radius: 5px;
	box-shadow: 0 0 4px 4px rgb(134, 131, 131);
}
.info__btn, .pass__btn {
	display: flex;
	margin-top: 24px;
}
label {
	display: inline-block;
	width: 120px;
}
.info,
input {
	font-size: 20px;
	width: 300px;
	padding: 4px;
	border: solid 1px;
}
.item,
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
}
.pass-form {
	margin-top: 200px;
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
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="info-app">
        <form:form class="pass-form" id="pass-form" action="Admin/changePass.htm">
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
        </form:form>    
	</div>
</body>
</html>