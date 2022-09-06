<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
#chartContainer {
	width: 80%;
	height: 500px;
	margin-left: 10%;
	font-size: 20px;
}

form {
	padding: 0;
	border-radius: 0;
	box-shadow: none;
	margin: 24px 0px;
	margin-top: 0px;
}

input {
	width: 320px;
	font-size: 16px;
	padding: 4px;
	height: 32px;
}

button {
	width: 150px;
	display: inline-block;
	font-size: 16px;
}
</style>
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@include file="/WEB-INF/views/Admin/menu.jsp"%>
	<div class="container">
		<form action="" method="post">
			<label>Từ</label> <input id="from" type="date" min="2000" max="2100"
				name="from" value="${from}" required="required"> <label
				style="margin-left: 50px">Đến</label> <input id="to" type="date"
				min="2000" max="2100" name="to" value="${to}" required="required">
			<label class="error">${error}</label>
			<button onclick="search(event)" name="btnSearch">Tra cứu</button>
		</form>
		<c:if test="${from != null}">
			<div class="link-export__wrapper">
				<a class="link-export"
					href="pdf/statistic.htm?from=${from}&to=${to}&sum=${sum}"
					onclick="search(event)">Xuất danh sách</a>
			</div>
		</c:if>
		<table>
			<tr>
				<th>Năm</th>
				<th>Tháng</th>
				<th>Doanh thu</th>
			</tr>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.year}</td>
					<td>${c.month == 0 ? '' : c.month}</td>
					<td style="text-align: right;"><fmt:formatNumber type="currency" value="${c.total}" /></td>
				</tr>
			</c:forEach>
			<tr style="font-size: 24px; font-weight: 900;">
				<td></td>
				<td>Tổng tiền</td>
				<td style="text-align: right;"><fmt:formatNumber type="currency" value="${sum}" /></td>
			</tr>
		</table>
		<div id="chartContainer"></div>
	</div>
	<script type="text/javascript">
		function search(event) {
			let from = new Date(document.getElementById("from").value);
			let to = new Date(document.getElementById("to").value);
			if (from > to) {
				alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc");
				event.preventDefault();
			}
		}
	</script>
</body>
</html>