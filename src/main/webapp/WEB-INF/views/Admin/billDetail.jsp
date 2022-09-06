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
<style type="text/css">
.bill-Info p{
margin: 8px 0;
}
span {
	display:  inline-block;
	width: 300px;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<h2>ĐƠN HÀNG</h2>
		
<%-- 		<c:if test="${sessionScope.staff.taiKhoan.quyen.maQuyen!=4}">
			<div class="link-export__wrapper"><a class="link-export" href="pdf/detailBill/${id}.htm">Xuất danh sách</a></div>
		</c:if>	 --%>	
		
		<div class="bill-Info">
			<p> <span>Mã đơn hàng: </span>${bill.maDH}</p>
			<p> <span>Mã khách hàng:</span>${bill.khachHang.maKH}</p>
			<p> <span>Họ tên người nhận:</span>${bill.hoTenNN}</p>
			<p> <span>Địa chỉ người nhận:</span>${bill.diaChiNN}</p>
			<p> <span>Số điện thoại người nhận:</span>${bill.sdtNN}</p>
			<p> <span>Email người nhận:</span>${bill.emailNN}</p>
			<p> <span>Ngày tạo:</span><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.ngayTao}" /></p>
			<p> <span>Ngày nhận:</span><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.ngayNhan}" /></p>
			<p> <span>Tổng tiền:</span><fmt:formatNumber type = "currency" value = "${bill.tongTien}" /></p>
		</div>
		
		<table>
			<tr>
				<th>Mã sản phẩm</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
			</tr>
			<c:forEach var="s" items="${list}">
				<tr>
					<td>${s.pk.sanPham.maSP}</td>
					<td>${s.pk.sanPham.tenSP}</td>
					<td>${s.sl}</td>
					<td><fmt:formatNumber type = "currency" value = "${s.gia}" /></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${bill.nhanVienD == NULl}">
			<form action="Admin/billDetail/${bill.maDH}.htm">
				<div class="form-element">
					<label>Chọn nhân viên giao hàng</label>
						<select name="maNVG">
							<c:forEach var="s" items="${listNV}">
								<option value="${s.maNV}">${s.maNV}__${s.hoTen}</option>
							</c:forEach>
						</select>		
				</div>
				<div style="display: flex;">
					<button name="btnBrower">Duyệt đơn hàng</button>
					<button name="btnCancel">Hủy đơn hàng</button>
				</div>
			</form>
		</c:if>
		<c:if test="${sessionScope.staff.taiKhoan.quyen.maQuyen==4 && bill.trangThai == 1}">
			<form action="Admin/billDetail/${bill.maDH}.htm">
				<button name="btnConfirm">Xác nhận đã giao</button>
			</form>
		</c:if>

	</div>

</body>
</html>