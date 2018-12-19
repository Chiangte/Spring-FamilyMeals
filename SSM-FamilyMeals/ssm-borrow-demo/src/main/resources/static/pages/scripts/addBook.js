$(function() {
	//动态加载出版社
	$.getJSON("publishingList", function(data) {
		/*
			 [
			 	{"id": 1, "name": "人民教育出版社"},
			 	{"id": 2, "name": "机械工业出版社"},
			 	{"id": 3, "name": "清华大学出版社"}
			 ]
		 */
		$.each(data, function(index, publishing) {
			/*$('<option value="' + publishing.id + '">' + publishing.name + '</option>')
			.appendTo("#pid");*/
			$("<option />", {
				value: publishing.id,
				text: publishing.name
			}).appendTo("#" + $.escapeSelector("publishing.id"));
		});
	});
	
	//表单验证
	var form = $("form");
	form.bootstrapValidator({
		feedbackIcons: {
            valid:      "glyphicon glyphicon-ok",
            invalid:    "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh"
        },
        verbose: false,//是否短路验证，默认值是true，表示不短路验证，即每个表单元素的所有验证器都要执行。
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: "书名不能为空"
                    },
                    stringLength: {
                    	max: 100,
                    	message: "书名长度不能超过%s个字符"
                    },
                    remote: {
                        message: "书名已经存在，请更换",
                        type: "GET",
                        url: "checkName",
                        delay: 200
                    }
                }
            },
            author: {
                validators: {
                    notEmpty: {
                        message: "作者不能为空"
                    },
                    stringLength: {
                    	max: 50,
                    	message: "作者长度不能超过%s个字符"
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: "价格不能为空"
                    }/*,
                    numeric: {
                    	message: "价格必须是数字"
                    },
                    greaterThan: {
                    	value: 0,
                    	inclusive: false,
                    	message: "价格必须大于%s"
                    }*/
                }
            },
            "publishing.id": {
                validators: {
                    notEmpty: {
                        message: "请选择出版社"
                    }
                }
            },
            createDate: {
                validators: {
                    notEmpty: {
                        message: "请选择出版日期"
                    }
                }
            },
            summary: {
                validators: {
                    stringLength: {
                    	max: 1000,
                    	message: "图书摘要长度不能超过%s个字符"
                    }
                }
            },
            cover: {
                validators: {
                    notEmpty: {
                        message: "图书封面不能为空"
                    }
                }
            }
        }
	});
	//在表单调用bootstrapValidator()方法后，才能获取验证对象
	var formValidator = form.data("bootstrapValidator");
	
	//渲染datetimepicker控件
	var createDate = $("#createDate");
	createDate.datetimepicker({
		language: "zh-CN",//设置语言
		format: "yyyy-mm-dd",//日期显示格式
		startView: "decade",//初始显示的视图
		minView: "month",//最小显示的视图
		autoclose: true,//选择完日期后自动关闭日期选择器
		startDate: createDate.data("startdate"),
		endDate: createDate.data("enddate")
	}) 
	.on("hide", function() {
    	//调用formValidator的revalidateField方法，重新验证该元素
		formValidator.revalidateField("createDate");
    });
	
	//渲染fileUpload控件
	var cover = $("#file").fileinput({
		language: "zh",//设置语言
		maxFilesNum: 1,//设置允许选择的文件的数量
		showUpload: false,//是否显示上传按钮
        allowedFileExtensions: ["jpg", "gif", "png"]//设置允许选择的文件扩展名
    });
    cover.on("filecleared", function() {
    	//调用formValidator的revalidateField方法，重新验证该元素
    	formValidator.revalidateField("cover");
    });
	
	$("button[type=button]").click(function() {
		form[0].reset();
		form.bootstrapValidator("resetForm");
	});
});