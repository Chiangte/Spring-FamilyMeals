$(function() {
	//动态加载出版社
	var pid = "#" + $.escapeSelector("publishing.id");
	$.getJSON("publishingList", function(data) {
		$.each(data, function(index, publishing) {
			var param = {value: publishing.id, text: publishing.name};
			if (publishing.id == $(pid).data("pid")) {
				param.selected = true;
			}
			$("<option />", param).appendTo(pid);
		});
	});
	
	//表单验证
	var data = {id: $("#id").val()};
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
                        message: "书名已经存在",
                        type: "GET",
                        url: "checkName",
                        data: data,
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
                    },
                    numeric: {
                    	message: "价格必须是数字"
                    },
                    greaterThan: {
                    	value: 0,
                    	inclusive: false,
                    	message: "价格必须大于%s"
                    }
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
	});
	
	//渲染fileinput控件
	var cover = $("#file");
	cover.fileinput({
		language: "zh",//设置语言
		maxFilesNum: 1,//设置允许选择的文件的数量
		showUpload: false,//是否显示上传按钮
        allowedFileExtensions: ["jpg", "gif", "png"],//设置允许选择的文件扩展名
        showRemove: false,//设置是否显示删除按钮
        showClose: false,//设置是否显示关闭按钮
        layoutTemplates: {
        	actionDelete: "",//置空删除图标按钮
            actionDrag: "",//置空拖拽图标按钮
        },
        initialPreviewAsData: true,//设置是否有初始视图数据
        initialPreview: [			//设置初始视图数据
        	cover.data("cover")
        ]
    });
	
	$("button[type=reset]").click(function() {
		form.bootstrapValidator("resetForm");
	});
});