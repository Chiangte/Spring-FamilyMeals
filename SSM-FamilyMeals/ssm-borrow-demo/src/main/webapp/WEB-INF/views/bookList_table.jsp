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
					<a href="bookList?currView=table" class="btn btn-default disabled" title="表格视图">
						<span class="glyphicon glyphicon-th"></span>
					</a>
					<a href="bookList?currView=list" class="btn btn-default" title="列表视图">
						<span class="glyphicon glyphicon-th-list"></span>
					</a>
				</div>
			</div>
			
			<!-- 表格视图开始 -->
			<div class="table-responsive">
				<table id="tableView" class="table table-striped">
					<thead>
						<tr>
							<th>书名</th>
							<th>作者</th>
							<th>价格(￥)</th>
							<th>出版社</th>
							<th>出版日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${requestScope.pageInfo.total gt 0}" var="flag">
							<c:forEach var="book" items="${requestScope.pageInfo.list}">
								<tr>
									<td>${book.name}</td>
									<td>${book.author}</td>
									<td>${book.price}</td>
									<td>${book.publishing.name}</td>
									<td><fmt:formatDate value="${book.createDate}" pattern="yyyy-MM-dd"/></td>
									<td>
										<a href="updateBook?id=${book.id}" class="btn btn-default btn-xs">
											<span class="glyphicon glyphicon-pencil"></span> 修改
										</a>
										<button type="button" class="btn btn-danger btn-xs" data-id="${book.id}">
											<span class="glyphicon glyphicon-trash"></span> 删除
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${not flag}">
							<tr>
								<td colspan="6" class="text-center">
									没有更多图书数据
								</td>
							</tr>
						</c:if>
					</tbody>
					<c:if test="${flag}">
						<tfoot>
							<tr>
								<td colspan="6" class="text-center">
									<!-- 分页 -->
									<%@ include file="commons/pagination.jsp" %>
								</td>
							</tr>
						</tfoot>
					</c:if>
				</table>
			</div>
			<!-- 表格视图结束 -->
		</div>
	</body>
</html>