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
		<h2>找回密码</h2>
	</div>
	<div class="row" style="margin-top: 20px;">
		<div class="col-lg-3"></div>
		<div class="col-lg-1">
		  <label for="stu_id">学号</label>
		</div>
		<div class="col-lg-4">
			<input type="text" class="form-control" id="stu_id" placeholder="请输入您的学号">
		</div>
		<div class="col-lg-4">
			<button id="verify-btn" class="btn btn-primary" onclick="getVerifyCode()">获取验证码</button>
		</div>
	</div>
	<div id="sendTips" class="row" style="display:none;margin-top: 20px;text-align: center;">
	  	获取验证码提示
	</div>
	<div class="row" style="margin-top: 20px;">
		 <div class="col-lg-3"></div>
		 <div class="col-lg-1">
		   <label for="verify_code">验证码</label>
		 </div>
		 <div class="col-lg-4">
		 	<input type="text" class="form-control" id="verify_code" placeholder="请登陆邮箱获取验证码并输入">
		 </div>
	</div>
	<div class="row" style="margin-top: 20px; text-align:center; color: red;">
		<s:property value="exception.message" />
	</div>
	<form action="${pageContext.request.contextPath}/studentAction_validateVerifyCode" method="post">
		<input type="hidden" class="form-control" id="hidden_stu_id" name="stu_id">
		<input type="hidden" class="form-control" id="hidden_verify_code" name="verify_code">
		<div class="row" style="text-align: center;margin-top: 20px;">
			<button type="submit" class="btn btn-success">下一步</button>
		</div>
	</form>
	
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
	$("#verify_code").blur(function(){
		var $stu_id = $("#stu_id").val();
		var $verify_code = $("#verify_code").val();
		$("#hidden_stu_id").val($stu_id);
		$("#hidden_verify_code").val($verify_code);
	});

	function getVerifyCode(){
		var $stu_id = $("#stu_id").val();
		if ($stu_id!="") {
			$.ajax({
				url:"${pageContext.request.contextPath}/studentAction_getVerifyCode",
				data:{stu_id:$stu_id},
				type:"post",
				success:function(data){
					$("#verify-btn").attr("disabled", "disabled");
					$("#verify-btn").removeAttr("onclick");
					$("#verify-btn").attr("class","btn btn-default"); 
					$("#sendTips").html("验证码已经发送到您的邮箱<strong style='font-size:large;'>" + data.email + "</strong>,30分钟有效,请登陆邮箱查收。");
					$("#sendTips").css("display","block");
					var second = 60;
					var timer = setInterval(function(){
						$("#verify-btn").html("获得验证码(" + second + "s)");
						second--;
						if (second===-1) {
							clearInterval(timer);
							$("#verify-btn").removeAttr("disabled");
							$("#verify-btn").attr("onclick","getVerifyCode()");
							$("#verify-btn").attr("class","btn btn-primary");
							$("#verify-btn").html("获得验证码");
						}
					},1000);
				},
				dataType:"json"
			});
		} else {
			alert("学号不能为空！");
		}
	}
	
</script>
</body>
</html>