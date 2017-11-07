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
    <!-- font-awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container-fluid">
	<div class="col-lg-3"></div>
	<div class="col-lg-6" style="background-color: #F9F9F9; border-radius: 5px; border: #e9e9e9 1px solid; ">
		<div>提示信息</div>
		<div style="text-align:center;">
			<h1><i class="fa fa-check-circle-o" aria-hidden="true"></i><s:property value="#successMsg"/></h1>
		</div>
		<div style="text-align:center;">
			<a href="${ pageContext.request.contextPath }/adminAction_welcomeAdmin" class="btn btn-default">回到管理模块</a><a href="${ pageContext.request.contextPath }/studentAction_welcome" class="btn btn-default">回到本站首页</a>
		</div>
	</div>
	<div class="col-lg-3"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
	function toLogin() {
		location.href="${pageContext.request.contextPath}";
	}
</script>
</body>
</html>