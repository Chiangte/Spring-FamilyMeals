<%@ page pageEncoding="UTF-8"%>
<!-- 导航条 -->
<nav class="navbar navbar-inverse">
	<!-- 导航容器 -->
	<div class="container-fluid">
		<!-- 导航头部 -->
		<div class="navbar-header">
			<!-- 品牌图标 -->
			<a class="navbar-brand" href="home">
				<span class="glyphicon glyphicon-book"></span>
			</a>
		</div>
		
		<!-- 导航链接容器 -->
		<div class="collapse navbar-collapse">
			<!-- 导航链接，位于左侧 -->
			<ul id="topMenu" class="nav navbar-nav">
				<!-- class="active" 把当前的li激活 -->
				<li><a href="home"><span class="glyphicon glyphicon-home"></span> 主页</a></li>
				<li><a href="memberList"><span class="glyphicon glyphicon-user"></span> 会员管理</a></li>
				<li><a href="bookList"><span class="glyphicon glyphicon-book"></span> 图书管理</a></li>
				<li><a href="borrowList"><span class="glyphicon glyphicon-tags"></span> 借阅管理</a></li>
			</ul>
			<!-- 注销链接，位于右侧 -->
			<ul class="nav navbar-nav navbar-right">
				<li><a id="logout" href="javascript:;"><span class="glyphicon glyphicon-off"></span></a></li>
			</ul>
		</div>
	</div>
</nav>