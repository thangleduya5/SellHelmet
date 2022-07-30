<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="header-top">
	<div class="header-top__wrap">
		<div class="header-top__item header-top__left">
			<a href="User/home.htm" class="header-top-left__link"> <img
				src="resource/img/logo.png" alt="" class="header__logo">
			</a>
		</div>
		<div class="header-top__item header-top__center">
			<form class="header__search" method="post">
				<input name="nameProduct" type="text" class="header-search__input"
					placeholder="Nhập sản phẩm">
				<button name="btnSearchByName" class="btn__search" type="submit">
					<i class="fas fa-search header-search__icon"></i>
					<p class="tooltip">Tìm kiếm</p>
				</button>
			</form>
		</div>
		<div class="header-top__item header-top__right">
			<p class="header__hotline">Hotline: 19008198</p>
			<div class="header__user">
				<i class="fas fa-user"></i>
				<ul class="header-user__hover header__hover">	
					<c:if test="${sessionScope.customer!=null}">
						<li class="user__item"><a href="User/info.htm" class="">${sessionScope.customer.taiKhoan.tenDN}</a></li>
						<li class="user__item"><a href="User/history.htm" class="">Lịch sử mua hàng</a></li>
						<li class="user__item"><a href="User/logout.htm" class="">Đăng xuất</a></li>
					</c:if>
					<c:if test="${sessionScope.customer==null}">
						<li class="user__item"><a href="User/register.htm" class="">Đăng ký</a></li>
						<li class="user__item"><a href="User/login.htm" class="">Đăng nhập</a></li>
					</c:if>
				</ul>
			</div>
			<div class="header__cart">
				<i class="fas fa-shopping-cart"></i>
				<div class="header-cart__hover header__hover">
					<c:if test="${sessionScope.customer!=null}">
						<h3 class="cart-hover__title">Sản phẩm mới thêm</h3>
						<ul class="cart-hover__list">
						<%! int m = 0; %> 
						<%if(m>0) { m= 0; }%>
						<c:forEach var="s" items="${sessionScope.detailBills}">
								<li class="cart-hover-list__item"><img src="resource/img/imgProduct/${s.pk.sanPham.hinhAnh}"	width="100" height="100" alt="">
								<p>${s.pk.sanPham.tenSP} khởi động 5.0 đi ra ngoài Trái Đất</p> 
								<span> <fmt:formatNumber type="currency" value="${s.pk.sanPham.gia}" /> </span>
							</li> 
						<%m= m+1;%>
						</c:forEach>
						</ul>
						<div class="cart-hover__bottom">
							<p class="count-product"><%=m%> sản phẩm được thêm</p>
							<a href="User/cart.htm" class="cart__view btn">Xem giỏ hàng</a>
						</div>
					</c:if>
					<c:if test="${sessionScope.customer==null}">
						<a href="User/login.htm" class="btn btn__view-login">Đăng nhập để xem giỏ hàng</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</div>

<div class="header-bottom">
	<ul class="header-bottom__list">
		<c:forEach var="s" items="${sessionScope.brands}">
			<li class="header-bottom__item"><a href="User/home/${s.tenNCC}.htm" class="header-bottom__link">${s.tenNCC}</a></li>
		</c:forEach>
	</ul>
</div>
