<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
		<title>会员管理</title>
	</head>
	<body>
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/pages/scripts/memberList.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li class="active">会员管理</li>
		</content>
		
		<!-- 面板 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<!-- 搜索表单 -->
				<form action="memberList" class="form-inline">
					<div class="form-group">
						<label for="keyword">姓名：</label>
						<input type="text" id="keyword" name="keyword" class="form-control" placeholder="支持模糊查询" value="${requestScope.keyword}">
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-search"></span> 查询
					</button>
					<a href="addMember" class="btn btn-default">
						<span class="glyphicon glyphicon-plus"></span> 新增会员
					</a>
				</form>
			</div>
			<!-- 表格 -->
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>身份证号</th>
							<th>电话</th>
							<th>姓名</th>
							<th>注册时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${requestScope.pageInfo.total gt 0}" var="flag">
							<c:forEach var="member" items="${requestScope.pageInfo.list}">
								<tr>
									<td>${member.identityCard}</td>
									<td>${member.phone}</td>
									<td>${member.name}</td>
									<td><fmt:formatDate value="${member.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<a href="updateMember?id=${member.id}" class="btn btn-default btn-xs">
											<span class="glyphicon glyphicon-pencil"></span> 修改
										</a>
										<button type="button" class="btn btn-danger btn-xs" data-id="${member.id}">
											<span class="glyphicon glyphicon-trash"></span> 删除
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${not flag}">
							<tr>
								<td colspan="5" class="text-center">
									没有更多会员数据
								</td>
							</tr>
						</c:if>
					</tbody>
					<c:if test="${flag}">
						<tfoot>
							<tr>
								<td colspan="5" class="text-center">
									<!-- 分页 -->
									<%@ include file="commons/pagination.jsp" %>
								</td>
							</tr>
						</tfoot>
					</c:if>
				</table>
			</div>
		</div>
	</body>
</html>