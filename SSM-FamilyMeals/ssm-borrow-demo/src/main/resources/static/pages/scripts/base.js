$(function() {
	//激活菜单
	var curr_active = "curr_active";
	//以curr_active为key读取cookie
	var value = $.cookie(curr_active);
	if (undefined == value) {
		$("#topMenu a[href='home']").parent().addClass("active");
	} else {
		$("#topMenu a[href='" + value + "']").parent().addClass("active");
		$("#leftMenu a[href='" + value + "']").addClass("active");
	}
	$("#topMenu a, #leftMenu a, a.navbar-brand").click(function() {
		//获取当前超链接的href属性值
		var href = $(this).attr("href");
		//把当前超链接的href属性值以curr_active为key写入cookie
		$.cookie(curr_active, href);
	});
	
	//注销
	$("#logout").click(function() {
		bootbox.confirm({
			title: "提示",
			message: "您确定要注销吗？",
			size: "small",
			buttons: {
				confirm: {
					label: "确定",
					className: "btn-primary btn-sm"
				},
				cancel: {
					label: "取消",
					className: "btn-default btn-sm"
				}
			},
			callback: function(result) {
				if (result) {
					$.removeCookie(curr_active);
					location.href = "logout";
				}
			}
		});
	});
});