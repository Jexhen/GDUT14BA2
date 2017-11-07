<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>中国好2班-后台管理</title>

    <!-- Bootstrap -->
    <link href="${ pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet">

    <link href="${ pageContext.request.contextPath }/admin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <style>
        .admin-item {
            margin: 10px 0px;
            font-size: large;
            border-bottom: 1px solid #E5E5E5;
        }
        .admin-item-active {
            background-color: #FFFFFF;
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

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-5">
            <h3>广东工业大学14工商管理2班后台管理系统</h3>
        </div>
        <div class="col-lg-5" style="padding-top: 25px; text-align: right;">
            欢迎您，管理员${ student.stu_name }同学
            <a href="${ pageContext.request.contextPath }/studentAction_logout">退出</a>
            <a href="${ pageContext.request.contextPath }/studentAction_welcome">回到网站首页</a>
        </div>
    </div>
    <div class="row" style="background-color:#EEEEEE;">
        <div class="col-lg-2">
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_welcomeAdmin"><i class="fa fa-user" aria-hidden="true"></i>
                欢迎页
            </a>
            </div>
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getAdministrators"><i class="fa fa-user" aria-hidden="true"></i>
                管理员管理
            </a>
            </div>
            <div class="admin-item  admin-item-active"><a href="${ pageContext.request.contextPath }/adminAction_getStudents?currentPage=1&currentCount=10"><i class="fa fa-user-circle-o" aria-hidden="true"></i>
                学生管理
            </a>
            <div style="text-align: right; color: cornflowerblue;"><i class="fa fa-hand-o-right" aria-hidden="true"></i></div>
            </div>
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getNotices?currentPage=1&currentCount=10"><i class="fa fa-bullhorn" aria-hidden="true"></i>
                通知管理
            </a>
            </div>
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getEmployInfos?currentPage=1&currentCount=10"><i class="fa fa-address-card-o" aria-hidden="true"></i>
                招聘管理
            </a>
            </div>
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=1&currentCount=10"><i class="fa fa-file-word-o" aria-hidden="true"></i>
                作业管理
            </a>
            </div>
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getCarousels"><i class="fa fa-picture-o" aria-hidden="true"></i>
                图片管理
            </a>
            </div>
            <div class="admin-item"><a href="#"><i class="fa fa-comments-o" aria-hidden="true"></i>
                侃侃管理
            </a>
            </div>
        </div>
        <div class="col-lg-10">
            <div class="row">
                <ol class="breadcrumb">
                    <li><a href="${ pageContext.request.contextPath }/adminAction_welcomeAdmin">后台管理系统</a></li>
                    <li><a href="${ pageContext.request.contextPath }/adminAction_getStudents?currentPage=1&currentCount=10">学生管理</a></li>
                    <li class="active">添加新成员</li>
                </ol>
            </div>
            <h4>添加新成员</h4>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <form id="addForm" action="${ pageContext.request.contextPath }/studentAction_addStudent" method="post">
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_id">学号</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_id" placeholder="请输入学号" name="stu_id"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_name">姓名</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_name" placeholder="请输入姓名" name="stu_name"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_dormitory">宿舍</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_dormitory" placeholder="请输入宿舍号" name="stu_dormitory"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_signature">个性签名</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_signature" placeholder="请输入个性签名" name="stu_signature"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_email">邮箱</label></div>
                            <div class="col-lg-10"><input type="email" class="form-control" id="stu_email" placeholder="请输入邮箱" name="stu_email"></div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="isactive" value="0">
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_birthday">出生日期</label></div>
                            <div class="col-lg-10">
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="stu_birthday" value="" name="stu_birthday" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-2"><label>性别</label></div>
                            <div class="col-lg-10">
                                <input type="radio" name="stu_sex" value="男"> 男
                                <input type="radio" name="stu_sex" value="女"> 女
                                <input type="radio" name="stu_sex" value="其他"> 其他
                            </div>
                        </div>


                        <div class="col-lg-12" style="text-align: right;">
                            <button type="submit" class="btn btn-default btn-success">增加</button>
                        </div>

                    </form>
                </div>
                <div class="col-lg-2"></div>
            </div>
            <div></div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${ pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>

<!-- validate -->
<script src="${ pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script src="${ pageContext.request.contextPath }/js/messages_zh.min.js"></script>

<script src="${ pageContext.request.contextPath }/admin/js/bootstrap-datetimepicker.js"></script>
<script src="${ pageContext.request.contextPath }/admin/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<script>
	$("#addForm").validate({
		rules:{
			stu_id:{
				required:true,
				minlength:10,
				maxlength:10,
				digits:true
			},
			stu_name:{
				required:true,
				minlength:2,
				maxlength:8
			},
			stu_dormitory:{
				required:true
			},
			stu_sex:{
				required:true
			},
			stu_email:{
				email:true
			},
			stu_signature:{
				minlength:1,
				maxlength:30
			}
		},
		messages:{
			stu_id:{
				required:"必须输入邮箱",
				minlength:"请输入合法的学号",
				maxlength:"请输入合法的学号",
				digits:"请输入合法的学号"
			},
			stu_name:{
				required:"必须输入姓名",
				minlength:"姓名最短2位",
				maxlength:"姓名最长8位"
			},
			stu_dormitory:{
				required:"必须输入宿舍"
			},
			stu_sex:{
				required:"性别必选"
			},
			stu_email:{
				email:"请输入合法的邮箱地址"
			},
			stu_signature:{
				minlength:"最短输入1个字符",
				maxlength:"最长输入30个字符"
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

    function showMenu(obj) {
        var $objParent = $(obj).parent();
        var $adminItemMenu = $($objParent).children(".admin-item-menu");
        if ($($adminItemMenu).css("display")==="none") {
            $($adminItemMenu).css("display", "block");
        } else {
            $($adminItemMenu).css("display", "none");
        }
    }

    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
</body>
</html>