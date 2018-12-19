$(function() {
	var data = {id: $("#id").val()};
	var form = $("form").bootstrapValidator({
		feedbackIcons: {
            valid:      "glyphicon glyphicon-ok",
            invalid:    "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh"
        },
        fields: {
        	name: {
                validators: {
                    notEmpty: {
                        message: "姓名不能为空"
                    }
                }
            },
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
                        message: "身份证号已经存在",
                        type: "GET",
                        url: "checkIdentityCard",
                        data: data,
                        delay: 200
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: "电话不能为空"
                    },
                    phone: {
                    	country: "CN",
                    	message: "电话号码格式不正确"
                    },
                    remote: {
                        message: "电话号码已经存在",
                        type: "GET",
                        url: "checkPhone",
                        data: data,
                        delay: 200
                    }
                }
            }
        }
	});
	
	$("button[type=reset]").click(function() {
		form.bootstrapValidator("resetForm");
	});
});