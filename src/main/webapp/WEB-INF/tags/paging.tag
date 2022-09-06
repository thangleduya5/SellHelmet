<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="org.springframework.util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pagedListHolder" required="true"
	type="org.springframework.beans.support.PagedListHolder"%>
<%@ attribute name="pagedLink" required="true" type="java.lang.String"%>
<c:if test="${pagedListHolder.pageCount > 1}">
	<ul class="pagination justify-content-end">
		<c:if test="${!pagedListHolder.firstPage}">
			<li class="page-item"><a class="page-link"
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() - 1))%>">Previous</a></li>
		</c:if>
		<c:if test="${pagedListHolder.firstLinkedPage > 0}">
			<li class="page-item"><a class="page-link" href="<%=StringUtils.replace(pagedLink, "~", "0")%>">1</a></li>
		</c:if>
		<c:if test="${pagedListHolder.firstLinkedPage > 1}">
			<li class="page-item"><span class="pagingDots">...</span>
			<li>
		</c:if>
		<c:forEach begin="${pagedListHolder.firstLinkedPage}"
			end="${pagedListHolder.lastLinkedPage}" var="i">
			<c:choose>
				<c:when test="${pagedListHolder.page == i}">
					<li class="page-item active"><a class="page-link page-link--active" href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="page-link"
						href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if
			test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 2}">
			<li class="page-item"><span class="pagingDots">...</span></li>
		</c:if>
		<c:if
			test="${pagedListHolder.lastLinkedPage < pagedListHolder.pageCount - 1}">
			<li class="page-item"><a class="page-link"
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPageCount() - 1))%>">${pagedListHolder.pageCount}</a></li>
		</c:if>
		<c:if test="${!pagedListHolder.lastPage}">
			<li class="page-item"><a class="page-link"
				href="<%=StringUtils.replace(pagedLink, "~", String.valueOf(pagedListHolder.getPage() + 1))%>">Next</a></li>
		</c:if>
	</ul>
</c:if>
			
<form action="" class="fs-search">
	<c:if test="${nameBill != NULL}">
		<label>Từ:</label>
		<input style="margin-right: 20px" type="date" id="from" name="from" value="${from}" class="fs-search__input" required="required" >
		<label>Đến:</label>
		<input style="margin-right: 20px" type="date" id="to" name="to" value="${to}" class="fs-search__input" required="required" >
	</c:if>
	<c:if test="${nameBill == NULL}">
		<input name="name" value="${name}" class="fs-search__input" placeholder="Nhập tên cần tìm">
	</c:if>
	<button class="fs-search__link" name="btnSearch" ${nameBill != NULL ? 'onclick="search(event)"' : ''}>Tìm kiếm</button>
</form>
<style>
.pagination{
	margin: 24px 0px;
	margin-left: 24px;
	display: inline-block;
}
.pagination li{
	display: inline-block;
	text-decoration: none;
}
.fs-search__link,
.page-link{
	color: white;
	padding: 4px 8px;
	background: #007bff;
	border-radius: 4px;
	cursor: pointer;
}
.fs-search__link:hover,
.page-link:hover {
	background: blue;
	transition: all 0.5s;
}
.page-link--active{
	background: blue;
}
.fs-search{
    padding: 0;
    border-radius:  0;
    box-shadow: none;
	display: inline-block;
	float: right;
	margin: 24px 0px;
}
.fs-search__input{
	width: 320px;
	font-size: 16px;
	padding: 4px;
	height: 32px;
}
.fs-search__link{
	width: 150px;
	display: inline-block;
	font-size: 20px;
}
</style>