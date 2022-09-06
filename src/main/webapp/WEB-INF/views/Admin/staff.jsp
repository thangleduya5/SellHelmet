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
		<h2>THÔNG TIN NHÂN VIÊN</h2>
		<form:form action="Admin/staff.htm" class="form-staff" modelAttribute="staff">
			<div class="form-group">
				<form:input type="hidden" path="taiKhoan.tenDN"/> 
				<form:input type="hidden" path="maNV"/>
				<div class="form-element">
					<label class="label-title" for="">Họ tên: </label> 
					<form:input type="text" placeholder="Nhập họ tên" path="hoTen" required="required"/>
					<label for="" class="error"></label>
					<form:errors class="error" path="hoTen"/> 
				</div>
				<div class="form-element">
					<label class="label-title" for="">Số điện thoại: </label> 
					<form:input type="text" placeholder="Nhập số điện thoại" path="sdt"/> 					
					<label class="error"></label>
					<form:errors class="error" path="sdt"/>
				</div>	
				<div class="form-element">
					<label class="label-title" for="">Email: </label> 
					<form:input type="email" path="email"/> 
					<label for="" class="error"></label>
					<form:errors class="error" path="email"/>
				</div>
				<div class="form-element">
					<label class="label-title" for="">Địa chỉ: </label> 
					<form:input type="text" path="diaChi"/> 
					<label for="" class="error"></label>
					<form:errors class="error" path="diaChi"/>
				</div>
				<div class="form-element">
					<label class="label-title" for="">Giới tính: </label> 
					<form:select path="gioiTinh" id="sex">
						<option ${staff.gioiTinh == 'Nam' ? 'selected="selected"' : ''} value="Nam">Nam</option>
						<option ${staff.gioiTinh == 'Nữ' ? 'selected="selected"' : ''} value="Nữ">Nữ</option>
					</form:select> 
				</div>
                <div class="form-element">
                    <label class="label-title" for="">Quyền: </label>
                    <select name="quyen" >
                    	<c:forEach var="s" items="${sessionScope.roles}">
	                    	<c:if test="${s.maQuyen !=5}">
	                    	   <option ${staff.taiKhoan.quyen.maQuyen == s.maQuyen ? 'selected="selected"' : ''} value="${s.maQuyen}">${s.tenQuyen}</option>                   	
	                    	</c:if>
                    	</c:forEach>
                    </select>
                    <label for="" class="error"></label>
                </div>
			</div>
			<button name="${btnStatus}">Lưu</button>
		</form:form>
		<div> <p>${message}</p></div>

		<h2>DANH SÁCH NHÂN VIÊN</h2>
		<div class="link-export__wrapper"><a class="link-export" href="pdf/staff.htm">Xuất danh sách</a></div>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/staff.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />

		</div>
		<table>
			<tr>
				<th>Mã nhân viên</th>
				<th>Họ và tên</th>
				<th>Giới tính</th>
				<th>Số điện thoại</th>
				<th>Email</th>
				<th>Địa chỉ</th>
				<th>Quyền</th>
				<th>Xóa</th>
				<th>Sửa</th>
				<th>Reset</th>
			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
				<tr>
					<td>${s.maNV}</td>
					<td>${s.hoTen}</td>
					<td>${s.gioiTinh}</td>
					<td>${s.sdt}</td>
					<td>${s.email}</td>
					<td>${s.diaChi}</td>
					<td>${s.taiKhoan.quyen.tenQuyen}</td>
					<td>
					<c:if test="${s.taiKhoan.quyen.maQuyen!=1}">
						<a onclick="confirmDelete(event)" href="Admin/staff/${s.maNV}.htm?linkDelete">Xóa</a>
					</c:if>
					</td>
					<td><a href="Admin/staff/${s.maNV}.htm?linkEdit">Sửa</a></td>
					<td><a href="Admin/staff/${s.maNV}.htm?linkReset">Reset</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript" src="<c:url value='/resource/javascript/index.js' />"></script>
</body>
</html>