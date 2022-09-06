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
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="info-app">
        <div class="info-login">
            <img src="resource/img/USER.png" alt="">
            <a class="a-btn" href="Admin/changePass.htm">Thay đổi mật khẩu</a>
        </div>
        <div class="info-user">
            <h1>THÔNG TIN CÁ NHÂN</h1>
            <div class="infos">
                <div class="item">
                    <label for="">Tên đăng nhập</label>
                    <label class="info">${sessionScope.staff.taiKhoan.tenDN}</label>
                </div>
                <div class="item">
                    <label for="">Họ và tên</label>
                    <label class="info">${sessionScope.staff.hoTen}</label>
                </div>
                <div class="item">
                    <label for="">Giới tính</label>
                    <label class="info">${sessionScope.staff.gioiTinh}</label>
                </div>
                <div class="item">
                    <label for="">Số điện thoại</label>
                    <label class="info">${sessionScope.staff.sdt}</label>
                </div>
                <div class="item">
                    <label for="">Email</label>
                    <label class="info">${sessionScope.staff.email}</label>
                </div>
                <div class="item">
                    <label for="">Địa chỉ</label>
                    <label class="info">${sessionScope.staff.diaChi}</label>
                </div>
                <div class="item">
                    <label for="">Quyền</label>
                    <label class="info">${sessionScope.staff.taiKhoan.quyen.tenQuyen}</label>
                </div>
            </div>
        </div>
    	
    </div>
</body>
</html>