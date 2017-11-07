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
    <title>欢迎登录中国好2班班级网站</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
  	<link href="css/validate-tips.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container-fluid" style="background-image: url('img/login-background.jpg'); width: 100%; height: 100%;position: absolute; display: flex;">
    <div class="col-lg-4 hidden-sm hidden-xs"></div>
    <!--登录-->
    <div class="col-lg-4" style="margin: auto;">
        <h3 style="text-align: center; color: #ffffff;">欢迎登录中国好2班</h3>
        <form id="loginForm" class="form-horizontal" style="padding-top: 15px;" action="${ pageContext.request.contextPath }/studentAction_login" method="post">
            <div class="form-group">
                <label for="stu_id" class="col-sm-2 control-label" style="color: #ffffff;" >学号</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="stu_id" placeholder="请输入学号" name="stu_id" value="${ stu_id }">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label" style="color: #ffffff;">密码</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword" placeholder="请输入密码" name="stu_password" value="${ stu_password }">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="rememberPwd" checked="checked"><font color="#f0f8ff">记住密码</font>
                        </label>
                        <label>
                            <a href="${ pageContext.request.contextPath }/resetPassword.jsp">忘记密码？</a>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group" style="color:red; text-align: center; font-size: large;">
            	<div clas="col-sm-2"><s:property value="exception.message" /><s:actionerror/></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
            </div>
        </form>
    </div><!--登录-->
    <div class="col-lg-4 hidden-sm hidden-xs"></div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<!-- validate -->
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>

<script>
	$("#loginForm").validate({
		rules:{
			stu_id:{
				required:true
			},
			stu_password:{
				required:true
			}
		},
		messages:{
			stu_id:{
				required:"学号不能为空"
			},
			stu_password:{
				required:"密码不能为空"
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