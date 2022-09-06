<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value='/resource/css/Admin/menuStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Admin/adminStyle.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/css/Style.css' />"
	rel="stylesheet">
<link href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"
	rel="stylesheet">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<h2>DANH SÁCH ĐƠN HÀNG 
		<c:if test="${nameBill == 'billComplete'}">HOÀN THÀNH</c:if> 
		<c:if test="${nameBill == 'billDelivery'}">ĐANG GIAO</c:if> 
		<c:if test="${nameBill == 'billCancel'}">ĐÃ HỦY</c:if> 
		<c:if test="${nameBill == 'billUnConfirm'}">CHỜ DUYỆT</c:if> 
		</h2>
<%-- 		<c:if test="${sessionScope.staff.taiKhoan.quyen.maQuyen!=4}">
			<div class="link-export__wrapper"><a class="link-export" href="pdf/billConfirm.htm">Xuất danh sách</a></div>
		</c:if> --%>
		<jsp:useBean id="pagedListHolder" scope="request"
			type="org.springframework.beans.support.PagedListHolder" />
		<c:url value="Admin/${nameBill}.htm" var="pagedLink">
			<c:param name="p" value="~" />
		</c:url>
		<div>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
		<table>
			<tr>
				<th>Mã</th>
				<th>Khách hàng</th>
				<th>Ngày tạo</th>
				<th>Ngày nhận</th>
				<th>Tổng tiền</th>
				<th>Nhân viên giao</th>
				<th>Nhân viên duyệt</th>
				<th>Xem chi tiết</th>
			</tr>
			<c:forEach var="s" items="${pagedListHolder.pageList}">
            	<tr>
					<td>${s.maDH}</td>
					<td>${s.khachHang.hoTen}</td>
					<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${s.ngayTao}" /></td>
					<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${s.ngayNhan}" /></td>
					<td><fmt:formatNumber type = "currency" value = "${s.tongTien}" /></td>
					<td>${s.nhanVienG.hoTen}</td>
					<td>${s.nhanVienD.hoTen}</td>
					<td><a href="Admin/billDetail/${s.maDH}.htm">Xem chi tiết</a></td>
				</tr>
            </c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function search(event) {
			let from = new Date(document.getElementById("from").value);
			let to = new Date(document.getElementById("to").value);
			if(from > to){
				alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc");
				event.preventDefault();
			}
		}
	</script>
</body>
</html>