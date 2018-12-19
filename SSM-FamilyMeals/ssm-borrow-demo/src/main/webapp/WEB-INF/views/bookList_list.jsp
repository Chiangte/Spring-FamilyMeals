<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>图书管理</title>
	</head>
	<body>
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/pages/scripts/bookList.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li class="active">图书管理</li>
		</content>
		
		<!-- 面板 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="pull-left">
					<!-- 搜索表单 -->
					<form action="bookList" class="form-inline">
						<div class="form-group">
							<label for="keyword">书名：</label>
							<input type="text" id="keyword" name="keyword" class="form-control" placeholder="支持模糊查询" value="${requestScope.keyword}">
						</div>
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
						<a href="addBook" class="btn btn-default">
							<span class="glyphicon glyphicon-plus"></span> 新增图书
						</a>
					</form>
				</div>
				<div class="pull-right">
					<a href="bookList?currView=table" class="btn btn-default" title="表格视图">
						<span class="glyphicon glyphicon-th"></span>
					</a>
					<a href="bookList?currView=list" class="btn btn-default disabled" title="列表视图">
						<span class="glyphicon glyphicon-th-list"></span>
					</a>
				</div>
			</div>
			
			<!-- 列表视图开始 -->
			<ul id="ulView" class="list-group book-list">
				<c:if test="${requestScope.pageInfo.total gt 0}" var="flag">
					<c:forEach var="book" items="${requestScope.pageInfo.list}">
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-3">
									<img src="assets/pages/imgs/${book.cover}" class="img-thumbnail" alt="${book.name}">
								</div>
								<div class="col-md-9">
									<p><span>书名：</span>${book.name}</p>
									<p><span>作者：</span>${book.author}</p>
									<p><span>价格：</span>${book.price}</p>
									<p><span>出版社：</span>${book.publishing.name}</p>
									<p><span>出版日期：</span><fmt:formatDate value="${book.createDate}" pattern="yyyy-MM-dd"/></p>
									<div>
										<p class="pull-left"><span>摘要：</span>${book.summary}</p>
										<p class="pull-right">
											<a href="updateBook?id=${book.id}" class="btn btn-default btn-xs">
												<span class="glyphicon glyphicon-pencil"></span> 修改
											</a>
											<button type="button" class="btn btn-danger btn-xs" data-id="${book.id}">
												<span class="glyphicon glyphicon-trash"></span> 删除
											</button>
										</p>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
					<li class="list-group-item text-center">
						<!-- 分页 -->
						<%@ include file="commons/pagination.jsp" %>
					</li>
				</c:if>
				<c:if test="${not flag}">
					<li class="list-group-item text-center">
						没有更多图书数据
					</li>
				</c:if>
			</ul>
			<!-- 列表视图结束 -->
			
		</div>
	</body>
</html>