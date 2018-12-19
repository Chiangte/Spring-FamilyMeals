$(function() {
	var form = $("form").bootstrapValidator({
		feedbackIcons: {
            valid:      "glyphicon glyphicon-ok",
            invalid:    "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh"
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: "用户名不能为空"
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: "密码不能为空"
                    }
                }
            },
            kaptcha: {
                validators: {
                    notEmpty: {
                        message: "验证码不能为空"
                    }
                }
            }
        }
	})
	//success.form.bv		表单验证成功后触发的事件
	.on("success.form.bv", function() {
		//表单验证成功，删除名称叫curr_active的cookie，目的是使主页菜单总被激活
		$.removeCookie("curr_active");
	});
	
	//给刷新超链接注册单击事件
	$("#refreshLink").click(function() {
		var refreshLink = $(this);
		refreshLink.find("img").attr("src", "kaptcha.jpg?r=" + Math.random());
	});
});