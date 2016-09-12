//alert($);
//表单校验
$(function () {
    if ($("#editForm").size() > 0) {
        $("#editForm").validate({
            //校验规则
            rules: {
                "employee.name": {
                    required: true,
                    rangelength: [2, 8]
                }, "employee.password": {
                    required: true,
                    minlength: 4
                }, "repassword": {
                    equalTo: "#password"
                }, "employee.email": {
                    required: true,
                    email: true
                }, "employee.age": {
                    required: true,
                    range: [18, 60]
                }
            },
            //返回信息
            messages: {
                "employee.name": {
                    required: "用户名必填",
                    rangelength: "用户名须在{0}到{1}位之间"
                }, "employee.password": {
                    required: "密码必填",
                    minlength: "密码长度至少4位"
                }, "repassword": {
                    equalTo: "密码不一致"
                }, "employee.email": {
                    required: "邮箱必填",
                    email: "邮箱格式不正确"
                }, "employee.age": {
                    required: "年齡必填",
                    range: "年齡须在{0}到{1}之间"
                }
            }
        });
    }
});
