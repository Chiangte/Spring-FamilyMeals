<%@ page pageEncoding="UTF-8"%>
<html>
	<head>
		<title>新增借阅</title>
	</head>
	<body>
		<%-- 当前页面需要引入的css --%>
		<content tag="pagecss">
			<link href="assets/plugins/bootstrap-validator-0.5.3-dist/css/bootstrapValidator.min.css" rel="stylesheet">
		</content>
		
		<%-- 当前页面需要引入的js --%>
		<content tag="pagejs">
			<script src="assets/plugins/bootstrap-validator-0.5.3-dist/js/bootstrapValidator.min.js" type="text/javascript"></script>
			<script src="assets/plugins/handlebars-v4.0.12-dist/handlebars-v4.0.13.min.js" type="text/javascript"></script>
			<script src="assets/plugins/handlebars-v4.0.12-dist/handlebars-ext.js" type="text/javascript"></script>
			<script src="assets/pages/scripts/addBorrow.js" type="text/javascript"></script>
		</content>
		
		<%-- 当前页面的面包屑导航 --%>
		<content tag="breadcrumb">
			 <li><a href="borrowList">借阅管理</a></li>
			 <li class="active">新增借阅</li>
		</content>

		<div class="row">
			<div class="col-md-8">		
				<!-- 面板 -->
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- 搜索表单 -->
						<form class="form-inline">
							<div class="form-group">
								<label for="keyword">书名：</label>
								<input type="text" id="keyword" name="keyword" class="form-control" placeholder="支持模糊查询">
							</div>
							<button id="btnSearch" type="button" class="btn btn-primary">
								<span class="glyphicon glyphicon-search"></span> 查询
							</button>
						</form>
					</div>
					<!-- 表格 -->
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>书名</th>
									<th>作者</th>
									<th>价格</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3" class="text-center">
										
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<!-- 面板 -->
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- 新增表单 -->
						<form id="addBorrowForm">
							<div class="form-group">
								<label for="identityCard">身份证号：</label>
								<input type="text" id="identityCard" name="identityCard" class="form-control" placeholder="请输入身份证号">
							</div>
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-save"></span> 保存
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 定义数据模板 -->
		<script id="dataTemplate" type="text/x-handlebars-template">
			{{#if list}}
				{{#each list}}
					<tr data-id="{{id}}"{{#if selected}} class="success"{{/if}}>
						<td>{{name}}</td>
						<td>{{author}}</td>
						<td>{{price}}</td>
					</tr>
				{{/each}}
			{{/if}}
		</script>
		
		<!-- 定义分页模板 -->
		<script id="paginationTemplate" type="text/x-handlebars-template">
			{{#if list}}
				<nav>
					<ul class="pagination pagination-sm">
						{{#if isFirstPage}}
							<li class="disabled"><a href="javascript:;">首页</a></li>
						    <li class="disabled"><a href="javascript:;">上一页</a></li>
						{{else}}
							<li><a href="javascript:;" data-pagenum="1" data-pagesize="{{pageSize}}">首页</a></li>
							<li><a href="javascript:;" data-pagenum="{{prePage}}" data-pagesize="{{pageSize}}">上一页</a></li>
						{{/if}}
						{{#if isLastPage}}
							<li class="disabled"><a href="javascript:;">下一页</a></li>
						    <li class="disabled"><a href="javascript:;">尾页</a></li>
						{{else}}
							<li><a href="javascript:;" data-pagenum="{{nextPage}}" data-pagesize="{{pageSize}}">下一页</a></li>
							<li><a href="javascript:;" data-pagenum="{{pages}}" data-pagesize="{{pageSize}}">尾页</a></li>
						{{/if}}
					</ul>
				</nav>
			{{else}}
				没有更多可借阅的图书信息
			{{/if}}
		</script>
	</body>
</html>