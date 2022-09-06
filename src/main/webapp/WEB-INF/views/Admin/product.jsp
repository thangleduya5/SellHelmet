<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<h2>THÔNG TIN SẢN PHẨM</h2>
		<c:choose>
			<c:when test="${btnStatus == 'btnAdd'}">
				<form:form action="Admin/productInsert.htm" class="form-product"
					modelAttribute="product" enctype="multipart/form-data">
					<div class="form-group">
						<form:input type="hidden" value="0" path="slt" />
						<form:input type="hidden" value="0" path="gia" />
						<form:input type="hidden" value="1" path="spMoi" />
						<div class="form-element">
							<label class="label-title" for="">Mã SP: </label>
							<form:input type="text" path="maSP" required="required"
								maxlength="10" />
							<form:errors class="error" path="maSP" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Tên sản phẩm: </label>
							<form:input type="text" path="tenSP" placeholder="Nhập tên"
								required="required" maxlength="30" />
							<form:errors class="error" path="tenSP" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Mô tả: </label>
							<form:textarea type="text" path="moTa" placeholder="Nhập mô tả"
								cols="38" rows="5" required="required" />
							<form:errors class="error" path="moTa" />
							<label for="" class="error"></label>
						</div>

						<div class="form-element">
							<label class="label-title" for="">Loại sản phẩm: </label>
							<form:select path="loaiSP.maLoai">
								<c:forEach var="s" items="${loaiSPs}">
									<form:option value="${s.maLoai}">${s.tenLoai}</form:option>
								</c:forEach>
							</form:select>
							<label for="" class="error"></label>
						</div>

						<div class="form-element">
							<label class="label-title" for="">Nhà cung cấp: </label>
							<form:select path="nhaCungCap.maNCC">
								<c:forEach var="s" items="${nhaCungCaps}">
									<form:option value="${s.maNCC}">${s.tenNCC}</form:option>
								</c:forEach>
							</form:select>
							<label for="" class="error"></label>
						</div>

						<div class="form-element">
							<label class="label-title" for="">Hình ảnh: </label> <input
								type="file" onchange="chooseImgEvent(event)" name="image"
								required="required"> <img id="productImg"
								style="max-width: 100px">
						</div>
					</div>
					<button name="${btnStatus}">Lưu</button>
	            ${message}
	        </form:form>
			</c:when>
			<c:otherwise>
				<form:form action="Admin/productEdit.htm" class="form-product"
					modelAttribute="product" enctype="multipart/form-data">
					<div class="form-group">
						<form:input type="hidden" value="${product.slt}" path="slt" />
						<form:input type="hidden" value="${product.spMoi}" path="spMoi" />
						<form:input type="hidden" value="${product.hinhAnh}" path="hinhAnh" />
						<div class="form-element">
							<label class="label-title" for="">Mã SP: </label>
							<form:input type="text" path="maSP" readonly="true" />
							<form:errors class="error" path="maSP" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Tên sản phẩm: </label>
							<form:input type="text" path="tenSP" placeholder="Nhập tên" required="required" />
							<form:errors class="error" path="tenSP" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Mô tả: </label>
							<form:textarea type="text" path="moTa" placeholder="Nhập mô tả" required="required"
								cols="38" rows="5" />
							<form:errors class="error" path="moTa" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Giá: </label>
							<form:input type="number" path="gia" placeholder="Nhập giá" min="1" required="required" />
							<form:errors class="error" path="gia" />
							<label for="" class="error"></label>
						</div>
						<div class="form-element">
							<label class="label-title" for="">Loại sản phẩm: </label>
							<form:select path="loaiSP.maLoai">
								<c:forEach var="s" items="${loaiSPs}">
									<form:option value="${s.maLoai}">${s.tenLoai}</form:option>
								</c:forEach>
							</form:select>
							<label for="" class="error"></label>
						</div>

						<div class="form-element">
							<label class="label-title" for="">Nhà cung cấp: </label>
							<form:select path="nhaCungCap.maNCC">
								<c:forEach var="s" items="${nhaCungCaps}">
									<form:option value="${s.maNCC}">${s.tenNCC}</form:option>
								</c:forEach>
							</form:select>
							<label for="" class="error"></label>
						</div>

						<div class="form-element">
							<label class="label-title" for="">Hình ảnh: </label> <input
								type="file" onchange="chooseImgEvent(event)" name="image">
							<img id="productImg" style="max-width: 100px"
								src="resource/img/imgProduct/${product.hinhAnh}">
						</div>
					</div>
					<button name="${btnStatus}">Lưu</button>
		            ${message}
		        </form:form>
			</c:otherwise>
		</c:choose>

	</div>
	<script>
		function chooseImgEvent(event) {
			const img = event.target.files[0];
			if (img) {
				document.getElementById("productImg").src = URL
						.createObjectURL(img);
			}
		};
	</script>
</body>
</html>