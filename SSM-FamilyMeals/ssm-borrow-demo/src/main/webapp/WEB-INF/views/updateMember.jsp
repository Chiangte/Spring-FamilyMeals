<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<title>修改会员</title>
	</head>
	<body>
		<%-- 当前页面需要引入的css --%>
		<content tag="pagecss">
			<link href="assets/plugins/bootstrap-validator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
		</content>
		
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/plugins/bootstrap-validator-0.5.3-dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
			<script src="assets/pages/scripts/updateMember.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li><a href="memberList">会员管理</a></li>
			 <li class="active">修改会员</li>
		</content>
		
		<!-- 面板 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<!-- 修改表单 -->
						<form:form modelAttribute="memberVo">
							<div class="form-group">
								<label for="name">姓名：</label>
								<form:hidden path="id" />
								<form:input path="name" cssClass="form-control" placeholder="请输入姓名" />
								<form:errors path="name" />
							</div>
							<div class="form-group">
								<label for="identityCard">身份证号：</label>
								<form:input path="identityCard" cssClass="form-control" placeholder="请输入身份证号" />
								<form:errors path="identityCard" />
							</div>
							<div class="form-group">
								<label for="phone">电话：</label>
								<form:input path="phone" cssClass="form-control" placeholder="请输入电话" />
								<form:errors path="phone" />
							</div>
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-pencil"></span> 修改
							</button>
							<button type="reset" class="btn btn-default">
								<span class="glyphicon glyphicon-refresh"></span> 重置
							</button>
							<a href="memberList" class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-left"></span> 返回
							</a>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>