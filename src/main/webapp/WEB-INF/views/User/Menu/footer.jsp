<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link	href="<c:url value='/resource/css/User/menuStyle.css' />"	rel="stylesheet">
<link	href="<c:url value='/resource/css/Style.css' />"	rel="stylesheet">
<link	href="<c:url value='/resource/fontawesome-free-5.15.4-web/css/all.css' />"	rel="stylesheet">
<base href="${pageContext.servletContext.contextPath}/">
<div class="footer-app__top">
	<div class="footer-app__wrap">
		<div class="footer__about footer__item">
			<h3 class="footer__title">VỀ CÔNG TY</h3>
			<ul class="footer-about__list">
				<li class="footer-about__item"><a href="User/introductCompany.htm">Giới thiệu về
						công ty</a></li>
				<li class="footer-about__item"><a href="User/privacyPolicy.htm">Chính sách bảo
						mật</a></li>
				<li class="footer-about__item"><a href="User/operatingRegulation.htm">Qui chế hoạt động</a></li>
			</ul>
		</div>
		<div class="footer__office footer__item">
			<h3 class="footer__title">VĂN PHÒNG LÀM VIỆC</h3>
			<div class="footer-office__item">
				<span class="footer-office__name">Trụ sở miền Bắc: </span> <br>
				<span> 108 Hai Bà Trưng, Cầu Giấy, Hà Nội</span>
			</div>
			<div class="footer-office__item">
				<span class="footer-office__name">Trụ sở miền Nam: </span> <br>
				<span> 97 Man Thiện, Hiệp Phú, Quận 9</span>
			</div>
		</div>
		<div class="footer__support footer__item">
			<h3 class="footer__title">TỔNG ĐÀI HỖ TRỢ</h3>
			<ul class="footer-support__list">
				<li class="footer-support__item"><span class="support__name">Kỹ
						thuật</span> <span class="support__number-phone">17009567</span></li>
				<li class="footer-support__item"><span class="support__name">Kỹ
						thuật</span> <span class="support__number-phone">17009567</span></li>
				<li class="footer-support__item"><span class="support__name">Kỹ
						thuật</span> <span class="support__number-phone">17009567</span></li>
			</ul>
		</div>
		<div class="footer__certification footer__item">
			<h3 class="footer__title">CHỨNG NHẬN</h3>
			<ul class="footer-certification__list">
				<li class="footer-certification__item"><img
					src="resource/img/certification1.png" alt=""></li>
				<li class="footer-certification__item"><img
					src="resource/img/certification2.jpg" alt=""></li>
				<li class="footer-certification__item"><img
					src="resource/img/certification3.png" alt=""></li>
			</ul>
		</div>
	</div>
</div>
<div class="footer-app__bottom">
	<i class="fab fa-facebook"></i> <i class="fab fa-facebook-messenger"></i>
	<i class="fab fa-instagram"></i> <i class="fab fa-google"></i> Powered
	by <a
		href="https://www.facebook.com/Khoa-h%E1%BB%8Dc-t%E1%BB%B1-nhi%C3%AAn-425078731188713">Khoa Học Tự Nhiên</a>
</div>