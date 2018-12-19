<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
	<ul class="pagination pagination-sm">
		<c:if test="${requestScope.pageInfo.isFirstPage}" var="isFirst">
			<li class="disabled"><a href="javascript:;">首页</a></li>
		    <li class="disabled"><a href="javascript:;">上一页</a></li>
		</c:if>
		<c:if test="${not isFirst}">
			<li><a href="javascript:;" data-pagenum="1" data-pagesize="${requestScope.pageInfo.pageSize}">首页</a></li>
			<li><a href="javascript:;" data-pagenum="${requestScope.pageInfo.prePage}" data-pagesize="${requestScope.pageInfo.pageSize}">上一页</a></li>
		</c:if>
		<c:if test="${requestScope.pageInfo.isLastPage}" var="isLast">
			<li class="disabled"><a href="javascript:;">下一页</a></li>
		    <li class="disabled"><a href="javascript:;">尾页</a></li>
		</c:if>
		<c:if test="${not isLast}">
			<li><a href="javascript:;" data-pagenum="${requestScope.pageInfo.nextPage}" data-pagesize="${requestScope.pageInfo.pageSize}">下一页</a></li>
			<li><a href="javascript:;" data-pagenum="${requestScope.pageInfo.pages}" data-pagesize="${requestScope.pageInfo.pageSize}">尾页</a></li>
		</c:if>
	</ul>
</nav>