<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>借阅管理系统 - 登录</title>
		<link href="assets/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-validator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
	</head>
	<body>
		<!-- 布局容器 -->
		<div class="container">
			<!-- 栅格系统 -->
			<div class="row">
				<!-- 
					col-md-4			中等屏幕占4份宽度
					col-md-offset-4		中等屏幕左侧空出4份宽度
				-->
				<div class="col-md-4 col-md-offset-4">
					<!-- 页头 -->
					<div class="page-header">
						<h1 class="text-center">用户登录</h1>
					</div>
				</div>
			</div>
			
			<!-- 登录失败的消息 -->
			<c:if test="${not empty requestScope.msg}">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<!-- 
							alert				警告框
							alert-danger		警告框的外观，还可以是alert-warning、alert-info、alert-success
							alert-dismissible	警告框可以关闭
						 -->
						<div class="alert alert-danger alert-dismissible fade in">
							<!-- 
								class="close"			关闭按钮
								data-dismiss="alert"	缓存的名称叫dismiss的数据，数据为alert
							 -->
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${requestScope.msg}
						</div>
					</div>
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<form:form modelAttribute="loginVo" >
						<!-- form-group 	表单组 -->
						<div class="form-group">
							<label for="username">用户名：</label>
							<!-- form-control	文本框 -->
							<form:input path="username" cssClass="form-control" placeholder="请输入用户名" autocomplete="off" />
							<form:errors path="username" />
						</div>
						<div class="form-group">
							<label for="password">密码：</label>
							<form:password path="password" cssClass="form-control" placeholder="请输入密码" />
							<form:errors path="password" />
						</div>
						<div class="form-group">
							<a id="refreshLink" href="javascript:;">
								<img alt="刷新" src="kaptcha.jpg" class="img-thumbnail">
								看不清，点击刷新
							</a>
						</div>
						<div class="form-group">
							<label for="kaptcha">验证码：</label>
							<!-- form-control	文本框 -->
							<form:input path="kaptcha" cssClass="form-control" placeholder="请输入验证码" autocomplete="off" />
							<form:errors path="kaptcha" />
						</div>
						<!-- 
							btn				按钮
							btn-primary  	主项按钮
							btn-default		默认按钮
						-->
						<button type="submit" class="btn btn-primary btn-block">
							<span class="glyphicon glyphicon-ok"></span> 登录
						</button>
					</form:form>
				</div>
			</div>
		</div>
		<script src="assets/plugins/jquery-3.3.1.min.js" type="text/javascript"></script>
		<script src="assets/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="assets/plugins/bootstrap-validator-0.5.3-dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
		<script src="assets/plugins/jquery.cookie-1.4.1-dist/jquery.cookie-1.4.1.min.js" type="text/javascript"></script>
		<script src="assets/pages/scripts/login.js" type="text/javascript"></script>
	</body>
</html>