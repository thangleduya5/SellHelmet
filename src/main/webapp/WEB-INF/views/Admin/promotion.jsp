<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<style type="text/css">
.element {
	margin: 12px 0;
	display: flex;
	align-items: center;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<h2>THÔNG TIN KHUYẾN MÃI</h2>
		<form
			action="${pageContext.request.contextPath}/Admin/promotionInsert.htm"
			method="post" class="form-horizontal">
			<input id="maxED" type="hidden" value="${maxED}" />
			<div class="element">
				<label>Ngày bắt đầu</label> <input id="from" type="date"
					name="ngayBD" required="required" /> <label>Ngày kết thúc</label>
				<input id="to" type="date" name="ngayKT" required="required" />
			</div>
			<div class="element">
				<label>Lý do</label>
				<textarea cols="80" rows="5" name="moTa" required="required" ></textarea>
			</div>
			<div id="template" class="element">
				<label>Tên sản phẩm</label> <select name="maSP"
					class="product__item">
					<c:forEach var="i" items="${dsSanPham}">
						<option value="${i.maSP}">${i.tenSP}</option>
					</c:forEach>
				</select> <label>Giảm giá</label> <input type="number" min="1" max="99"
					name="giamGia" required="required" />
			</div>
			<div id="container"></div>
			<div>
				<button
					style="font-size: 16px; display: inline-block; width: 180px; margin-top: 20px"
					name="btnMoreProduct" onclick="moreProduct(event)">Thêm
					khuyến mãi</button>
			</div>
			<button onclick="checkSubmit(event);" name="${btnStatus}">Lưu</button>
		</form>
	</div>
	<script>
		var templete = document.getElementById("template");
		function moreProduct(event) {
			event.preventDefault();
			let container = document.getElementById("container");
			let select = templete.cloneNode(true);
			console.log(select);
			container.appendChild(select);
		}
		function checkSubmit(event) {
			let from = new Date(document.getElementById("from").value);
			let to = new Date(document.getElementById("to").value);
			let max = new Date(document.getElementById("maxED").value);
			if (max >= from) {
				alert("Ngày bắt đầu phải sau ngày kết thúc của đợt khuyến mãi gần nhất: " + (max.getDate() + 1) +"/" + max.getMonth() + "/" + max.getFullYear());
				event.preventDefault();
			} else if (from > to) {
				alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc");
				event.preventDefault();
			}
			let flag = true;
			let e = document.getElementsByClassName("product__item");
			for (let i = 0; i < e.length - 1; ++i) {
				for (let j = i + 1; j < e.length; ++j) {
					if (e[i].value == e[j].value) {
						/*Tìm thấy 1 phần tử trùng là đủ và dừng vòng lặp*/
						flag = false;
						break;
					}
				}
			}
			if (!flag) {
				alert("Sản phẩm khuyến mãi bị trùng, vui lòng kiểm tra lại!!");
				event.preventDefault();
			}
		}
	</script>
</body>
</html>
