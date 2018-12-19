<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<title>新增图书</title>
	</head>
	<body>
		<%-- 当前页面需要引入的css --%>
		<content tag="pagecss">
			<link href="assets/plugins/bootstrap-validator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
			<link href="assets/plugins/bootstrap-datetimepicker-2.4.4-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
			<link href="assets/plugins/bootstrap-fileinput-4.5.1-dist/css/fileinput.min.css" rel="stylesheet">
		</content>
		
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/plugins/bootstrap-validator-0.5.3-dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
			<script src="assets/plugins/bootstrap-datetimepicker-2.4.4-dist/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
			<script src="assets/plugins/bootstrap-datetimepicker-2.4.4-dist/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
			<script src="assets/plugins/bootstrap-fileinput-4.5.1-dist/js/fileinput.min.js" type="text/javascript"></script>
			<script src="assets/plugins/bootstrap-fileinput-4.5.1-dist/js/locales/zh.js" type="text/javascript"></script>
			<script src="assets/pages/scripts/addBook.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li><a href="bookList">图书管理</a></li>
			 <li class="active">新增图书</li>
		</content>
		
		<!-- 面板 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<!-- 新增表单 -->
						<form:form modelAttribute="bookVo" enctype="multipart/form-data">
							<div class="form-group">
								<label for="name">书名：</label>
								<form:input path="name" cssClass="form-control" placeholder="请输入书名" />
								<form:errors path="name" />
							</div>
							<div class="form-group">
								<label for="author">作者：</label>
								<form:input path="author" cssClass="form-control" placeholder="请输入作者" />
								<form:errors path="author" />
							</div>
							<div class="form-group">
								<label for="price">价格：</label>
								<form:input path="price" cssClass="form-control" placeholder="请输入价格" />
								<form:errors path="price" />
							</div>
							<div class="form-group">
								<label for="pid">出版社：</label>
								<form:select path="publishing.id" class="form-control">
									<option value="">请选择</option>
								</form:select>
							</div>
							<div class="form-group">
								<label for="createDate">出版日期：</label>
								<form:input path="createDate" cssClass="form-control" placeholder="请选择出版日期" data-startDate="${sessionScope.addStartDate}" data-endDate="${sessionScope.addEndDate}" />
								<form:errors path="createDate" />
							</div>
							<div class="form-group">
								<label for="summary">图书摘要：</label>
								<form:textarea rows="5" path="summary" cssClass="form-control" placeholder="请输入图书摘要" cssStyle="resize:none;" />
								<form:errors path="summary" />
							</div>
							<div class="form-group">
								<label for="cover">封面图书：</label>
								<input type="file" id="file" name="file" class="form-control" value="${requestScope.cover}">
							</div>
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-plus"></span> 新增
							</button>
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-refresh"></span> 重置
							</button>
							<a href="bookList" class="btn btn-default">
								<span class="glyphicon glyphicon-chevron-left"></span> 返回
							</a>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>