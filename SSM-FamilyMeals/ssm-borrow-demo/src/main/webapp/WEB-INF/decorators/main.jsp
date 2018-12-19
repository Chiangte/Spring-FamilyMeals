<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh-decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="sitemesh-page" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <%-- 引入被装饰页面的标题，如果被装饰页面没有标题，则采用默认值localhost/com-book --%>
		<title>借阅管理系统 - <sitemesh-decorator:title default="localhost/com-book" /></title>
		<link href="assets/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">
		<link href="assets/pages/css/base.css" rel="stylesheet">
		<%-- 引入被装饰页面中名称叫pagestyle的内容 --%>
		<sitemesh-decorator:getProperty property="page.pagecss" />
	</head>
	<body>
		<div class="container">
			<!-- 引入顶部菜单装饰器 -->
			<sitemesh-page:applyDecorator name="top_menu" />
			
			<!-- 布局 -->
			<div class="row">
				<div class="col-md-3">
					<!-- 引入左侧菜单装饰器 -->
					<sitemesh-page:applyDecorator name="left_menu" />
				</div>
				<div class="col-md-9">
					<!-- 路径导航 -->
					<ol class="breadcrumb">
						<%-- 引入被装饰页面中名称叫breadcrumb的内容 --%>
						<sitemesh-decorator:getProperty property="page.breadcrumb" />
					</ol>
					
					<!-- 显示修改失败的消息 -->
					<c:if test="${not empty requestScope.map}">
						<div class="alert alert-${requestScope.map['status'] ? 'success' : 'danger'} alert-dismissible fade in">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							${requestScope.map['msg']}
						</div>
					</c:if>
					
					<%-- 引入被装饰页面的主体 --%>
					<sitemesh-decorator:body />
				</div>
			</div>
			
			<!-- 引入版权声明装饰器 -->
			<sitemesh-page:applyDecorator name="copyright" />
		</div>
		<script src="assets/plugins/jquery-3.3.1.min.js" type="text/javascript"></script>
		<script src="assets/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="assets/plugins/bootbox.min.js" type="text/javascript"></script>
		<script src="assets/plugins/jquery.cookie-1.4.1-dist/jquery.cookie-1.4.1.min.js" type="text/javascript"></script>
		<script src="assets/pages/scripts/base.js" type="text/javascript"></script>
		<%-- 引入被装饰页面中名称叫pagejs的内容 --%>
		<sitemesh-decorator:getProperty property="page.pagejs" />
	</body>
</html>