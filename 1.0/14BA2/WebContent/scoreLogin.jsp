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
    <title>成绩查询-中国好2班</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <!-- 导航栏 -->
    <link href="${ pageContext.request.contextPath }/css/nav.css" rel="stylesheet" type="text/css">

    <style>
        .scoreLabel {
            font-size: large;
        }
    </style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="page-flag" title="4"></div>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid" style="margin-top: 10px;">
    <div class="col-lg-2"></div>
    <div class="col-lg-8">
      	<form class="form-horizontal" action="${ pageContext.request.contextPath }/scoreAction_login" method="post">
		  <div class="form-group">
		    <label for="stu_id" class="col-sm-2 control-label">学号</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="stu_id"  name="stu_id" value="${ student.stu_id }" readonly>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">教务系统密码</label>
		    <div class="col-sm-5">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入教务系统密码，非本网站密码" name="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="verifyCode" class="col-sm-2 control-label">验证码</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="verifyCode" placeholder="请输入验证码" name="verifyCode">
		    </div>
		    <div class="col-sm-5">
		    	<img id="verifyCodeImg" src="img/verifyCode/verifycode_${ uuid }.jpeg">
		   </div>
		  </div>
		  <div class="form-group" style="color:red; text-align: center; font-size: large;">
            	<div clas="col-sm-2" id="loginTips"><s:property value="exception.message" /></div>
            </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">进入</button>
		    </div>
		  </div>
		</form>
    </div>
    <div class="col-lg-2"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
	$(function(){
		$("#verifyCodeImg").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/scoreAction_refreshVerifyCode",
				type:"post",
				success:function(data){
					$("#verifyCodeImg").attr("src", "img/verifyCode/verifycode_"+data+".jpeg")
				}
			});
		});
		//若出现错误提示，重新获取验证码
		var html = $("#loginTips").html();
		if (html!="") {
			$.ajax({
				url:"${pageContext.request.contextPath}/scoreAction_refreshVerifyCode",
				type:"post",
				success:function(data){
					$("#verifyCodeImg").attr("src", "img/verifyCode/verifycode_"+data+".jpeg")
				}
			});
		}
	}); 
	
</script>
</body>
</html>