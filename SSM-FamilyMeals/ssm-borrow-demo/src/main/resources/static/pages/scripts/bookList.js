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
					location.href = "deleteBook?id=" + id;
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
		location.href = "bookList?pageNum=" + pageNum + "&pageSize=" + pageSize + "&keyword=" + keyword;
	});
});