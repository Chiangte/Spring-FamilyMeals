$(function() {
	$("#ulView button, #tableView button").click(function() {
		var id = $(this).data("id");
		bootbox.confirm({
			title: "提示",
			message: "您确定要删除吗？",
			size: "small",
			buttons: {
				confirm: {
					label: "确定",
					className: "btn-danger btn-sm"
				},
				cancel: {
					label: "取消",
					className: "btn-default btn-sm"
				}
			},
			callback: function(result) {
				if (result) {
					location.href = "deleteBorrow?id=" + id;
				}
			}
		});
	});

	$("#ulView .pull-right a, #tableView tbody a").click(function() {
		var id = $(this).data("id");
		bootbox.confirm({
			title: "提示",
			message: "您确定要归还吗？",
			size: "small",
			buttons: {
				confirm: {
					label: "确定",
					className: "btn-danger btn-sm"
				},
				cancel: {
					label: "取消",
					className: "btn-default btn-sm"
				}
			},
			callback: function(result) {
				if (result) {
					location.href = "returnBook?id=" + id;
				}
			}
		});
	});
	
	$("a[data-pagenum][data-pagesize]").click(function(e) {
		//禁用超链接的默认行为
		e.preventDefault();
		//把this转为jQuery对象
		var $this = $(this);
		//获取缓存的页码和页大小
		var pageNum = $this.data("pagenum");
		var pageSize = $this.data("pagesize");
		//获取姓名关键字
		var keyword = $("#keyword").val();
		//发起分页请求
		location.href = "borrowList?pageNum=" + pageNum + "&pageSize=" + pageSize + "&keyword=" + keyword;
	});
	
	$("#linkTable").click(function() {
		location.href = "borrowList?currView=table&keyword=" + $("#keyword").val();
	});
	
	$("#linkList").click(function() {
		location.href = "borrowList?currView=list&keyword=" + $("#keyword").val();
	});
});