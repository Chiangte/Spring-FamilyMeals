$(function() {
	//存储用户选择的图书的ID
	var bookIds = [];
	
	var tbody = $("tbody");
	//编译模板
	var dataTemplate = Handlebars.compile($("#dataTemplate").html());
	var paginationTemplate = Handlebars.compile($("#paginationTemplate").html());

	//AJAX分页
	function ajaxPagination(pageNum, pageSize) {
		var data = {pageNum: pageNum, pageSize: pageSize, keyword: $("#keyword").val()};
		//动态加载出版社
		$.getJSON("allowBorrowList", data, function(data) {
			console.debug(data);
			//预处理图书数据，有的图书应该被选中
			$.each(data.list, function(index, book) {
				book.selected = $.inArray(book.id, bookIds) != -1;
			});
			
			//数据 + 模板 => 页面
			var newDateHtml = dataTemplate(data);
			var newPaginationHtml = paginationTemplate(data);
			
			//展示页面
			tbody.html(newDateHtml);
			tbody.next().find("td").html(newPaginationHtml);
		});
	}
	
	//查询按钮单击事件
	$("#btnSearch").click(function() {
		ajaxPagination(1, 1);
	}).trigger("click");
	
	//数据行单击事件
	tbody.on("click", "tr", function() {
		var tr = $(this);
		var bookId = tr.data("id");
		if (tr.hasClass("success")) {
			tr.removeClass("success");
			/*
			var index = -1;
			for (var i = 0; i < bookIds.length; i++) {
				if (bookId == bookIds[i]) {
					index = i;
					break;
				}
			}
			bookIds.splice(index, 1);
			*/
			bookIds = $.grep(bookIds, function(value, index) {
				return value != bookId;
			});
		} else {
			tr.addClass("success");
			bookIds.push(bookId);
		}
	});
	
	//分页按钮单击事件
	$(".table").on("click", "a[data-pagenum][data-pagesize]", function(e) {
		//禁用超链接的默认行为
		e.preventDefault();
		//把this转为jQuery对象
		var $this = $(this);
		//获取缓存的页码和页大小
		var pageNum = $this.data("pagenum");
		var pageSize = $this.data("pagesize");
		
		ajaxPagination(pageNum, pageSize);
	});
	
	//表单验证
	var form = $("#addBorrowForm");
	form.bootstrapValidator({
		feedbackIcons: {
            valid:      "glyphicon glyphicon-ok",
            invalid:    "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh"
        },
        verbose: false,//是否短路验证，默认值是true，表示不短路验证，即每个表单元素的所有验证器都要执行。
        fields: {
        	identityCard: {
                validators: {
                    notEmpty: {
                        message: "身份证号不能为空"
                    },
                    regexp: {
                    	regexp: /^\d{17}[\dXx]{1}$/,
                    	message: "身份证号格式不正确"
                    },
                    remote: {
                        message: "身份证号不存在",
                        type: "GET",
                        url: "existsIdentityCard",
                        delay: 200
                    }
                }
            }
        }
	})
	//表单验证通过后会触发success.form.bv事件，该事件是bootstrapValidator自定义事件
	.on("success.form.bv", function(e) {
		//1.禁用提交按钮的默认行为，后续AJAX提交(为什么要AJAX提交？用户可以连续借书)
		e.preventDefault();
		
		//2.保存按钮一直处于非禁用状态
		form.bootstrapValidator("disableSubmitButtons", false);
		
		//3.判断用户是否选择要借阅的图书
		if (bookIds.length == 0) {
			bootbox.alert({
				title: "提示",
				message: "请选择您要借阅的图书",
				size: "small",
				buttons: {
					ok: {
						label: "确定",
						className: "btn-primary btn-sm"
					}
				}
			});
			return;
		}
		
		//4.准备要提交的数据
		var json = {identityCard: $("#identityCard").val(), bookIds: bookIds};
		
		//5.AJAX提交数据
		$.ajax({
			url: "addBorrow", 
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(json),
			dataType: "json",
			success: function(data) {
				bootbox.alert({
					title: "提示",
					message: data.msg,
					size: "small",
					buttons: {
						ok: {
							label: "确定",
							className: "btn-primary btn-sm"
						}
					},
				    callback: function () {
				    	if (data.status) {
				    		//重置表格和表单
				    		ajaxPagination(1, 1);
				    		form.bootstrapValidator("resetForm", true);
						}
				    }
				});
			}
		});
	});
});