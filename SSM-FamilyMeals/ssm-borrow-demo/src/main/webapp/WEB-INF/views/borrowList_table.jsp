<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>借阅管理</title>
	</head>
	<body>
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/pages/scripts/borrowList.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li class="active">借阅管理</li>
		</content>
		
		<!-- 面板 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="pull-left">
					<!-- 搜索表单 -->
					<form action="borrowList" class="form-inline">
						<div class="form-group">
							<label for="keyword">关键字：</label>
							<input type="text" id="keyword" name="keyword" class="form-control" placeholder="支持模糊查询" value="${requestScope.keyword}">
						</div>
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> 查询
						</button>
						<a href="addBorrow" class="btn btn-default">
							<span class="glyphicon glyphicon-plus"></span> 新增借阅
						</a>
					</form>
				</div>
				<div class="pull-right">
					<a href="javascript:;" class="btn btn-default disabled" title="表格视图">
						<span class="glyphicon glyphicon-th"></span>
					</a>
					<a id="linkList" href="javascript:;" class="btn btn-default" title="列表视图">
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
							<th>借阅者</th>
							<th>借阅时间</th>
							<th>归还时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${requestScope.pageInfo.total gt 0}" var="flag">
							<c:forEach var="borrow" items="${requestScope.pageInfo.list}">
								<tr>
									<td>${borrow.book.name}</td>
									<td>${borrow.member.name}</td>
									<td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<c:if test="${empty borrow.returnDate}" var="isEmpty">
											尚未归还
										</c:if>
										<c:if test="${not isEmpty}">
											<fmt:formatDate value="${borrow.returnDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</c:if>
									</td>
									<td>
										<c:if test="${isEmpty}">
											<a href="javascript:;"  class="btn btn-danger btn-xs" data-id="${borrow.id}">
												<span class="glyphicon glyphicon-leaf"></span> 归还
											</a>
										</c:if>
										<c:if test="${not isEmpty}">
											<button type="button" class="btn btn-danger btn-xs" data-id="${borrow.id}">
												<span class="glyphicon glyphicon-trash"></span> 删除
											</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${not flag}">
							<tr>
								<td colspan="6" class="text-center">
									没有更多借阅数据
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