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
<link href="<c:url value='/resource/css/Admin/adminStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Style.css' />" rel="stylesheet">
<link
	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<h2>THÔNG TIN NHÀ CUNG CẤP</h2>
		<form:form action="Admin/supplier.htm" class="form-staff" modelAttribute="supplier">
			<div class="form-group">
				<div class="form-element">
					<label class="label-title" for="">Mã : </label> 
					<c:if test="${btnStatus == 'btnAdd'}">
						<form:input type="text" placeholder="Nhập mã" path="maNCC" maxlength="10" required="true" />
					</c:if>
					<c:if test="${btnStatus == 'btnEdit'}">
						<form:input type="text" placeholder="Nhập mã" path="maNCC" readonly="true" />
					</c:if>
					<label for="" class="error"></label>
					<form:errors class="error"
					 path="maNCC"/> 
				</div>
				<div class="form-element">
					<label class="label-title" for="">Tên NCC: </label> 
					<form:input type="text" placeholder="Nhập họ tên" path="tenNCC" maxlength="50" required="true" />
					<label for="" class="error"></label>
					<form:errors class="error" path="tenNCC"/> 
				</div>
				<div class="form-element">
					<label class="label-title" for="">Số điện thoại: </label> 
					<form:input type="number" placeholder="Nhập số điện thoại" path="sdt" required="true" /> 					
					<label class="error"></label>
					<form:errors class="error" path="sdt"/>
				</div>	
				<div class="form-element">
					<label class="label-title" for="">Email: </label> 
					<form:input type="email" path="email" maxlength="30" required="true" /> 
					<label for="" class="error"></label>
					<form:errors class="error" path="email"/>
				</div>
				<div class="form-element">
					<label class="label-title" for="">Địa chỉ: </label> 
					<form:input type="text" path="diaChi" maxlength="200" required="true" /> 
					<label for="" class="error"></label>
					<form:errors class="error" path="diaChi"/>
				</div>
			</div>
			<button name="${btnStatus}">Lưu</button>
		</form:form>
		<div> <p>${message}</p></div>

		<h2>DANH SÁCH NHÀ CUNG CẤP</h2>
		<div class="link-export__wrapper"><a class="link-export" href="pdf/supplier.htm">Xuất danh sách</a></div>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/supplier.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />

		</div>
		<table>
			<tr>
				<th>Mã NCC</th>
				<th>Tên NCC</th>
				<th>Số điện thoại</th>
				<th>Địa chỉ</th>
				<th>Email</th>
				<th>Xóa</th>
				<th>Sửa</th>
			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
				<tr>
					<td>${s.maNCC}</td>
					<td>${s.tenNCC}</td>
					<td>${s.sdt}</td>
					<td>${s.diaChi}</td>
					<td>${s.email}</td>
					<td><a href="Admin/supplier/${s.maNCC}.htm?linkDelete">Xóa</a></td>
					<td><a href="Admin/supplier/${s.maNCC}.htm?linkEdit">Sửa</a></td>				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>