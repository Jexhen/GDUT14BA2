<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>重置密码-中国好2班</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container-fluid">
	<div class="row" style="text-align: center;">
		<h2>修改密码</h2>
	</div>
	<form id="passwordForm" action="${ pageContext.request.contextPath }/studentAction_modifyPassword" method="post">
		<input type="hidden" name="stu_id" value="${ stu_id }">
		<input type="hidden" name="md5" value="${ md5 }">
		<div class="row" style="margin-top: 20px;">
			<div class="col-lg-3"></div>
			<div class="col-lg-1">
				<label for="stu_password">输入密码</label>
			</div>
			<div class="col-lg-4">
				<input type="password" name="stu_password" id="stu_password" class="form-control">
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-lg-3"></div>
			<div class="col-lg-1">
				<label for="rePassword">确认密码</label>
			</div>
			<div class="col-lg-4">
				<input type="password" id="rePassword" name="rePassword" class="form-control">
			</div>
		</div>
		<div class="row" style="text-align: center; margin-top: 20px;">
			<button type="submit" class="btn btn-success">确认修改</button>
		</div>
	</form>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<!-- validate -->
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>

<script>
$("#passwordForm").validate({
	rules:{
		stu_password:{
			required:true,
			minlength:5,
			maxlength:15
		},
		rePassword:{
			required:true,
			equalTo:"#stu_password",
			minlength:5,
			maxlength:15
		}
	},
	messages:{
		stu_password:{
			required:"密码必填",
			minlength:"密码不少于5位",
			maxlength:"密码不多于15位"
		},
		rePassword:{
			required:"再次输入密码必填",
			equalTo:"与第一次输入的密码不一致",
			minlength:"密码不少于5位",
			maxlength:"密码不多于15位"
		}
	},
	errorPlacement: function (error, element) {
        if ($(element).next("div").hasClass("tooltip")) {
                $(element).attr("data-original-title", $(error).text()).tooltip("show");
         } else {
            $(element).attr("title",     
            $(error).text()).tooltip("show");
        }
    },
});
</script>
</body>
</html>