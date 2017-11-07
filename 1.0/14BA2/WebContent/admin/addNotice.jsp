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

        .attach {
            visibility: hidden;
            height: 0.5px;
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
            <div class="admin-item"><a href="${ pageContext.request.contextPath }/adminAction_getStudents?currentPage=1&currentCount=10"><i class="fa fa-user-circle-o" aria-hidden="true"></i>
                学生管理
            </a>
            </div>
            <div class="admin-item admin-item-active"><a href="${ pageContext.request.contextPath }/adminAction_getNotices?currentPage=1&currentCount=10"><i class="fa fa-bullhorn" aria-hidden="true"></i>
                通知管理
            </a>
            <div style="text-align: right; color: cornflowerblue;"><i class="fa fa-hand-o-right" aria-hidden="true"></i></div>
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
                    <li><a href="${ pageContext.request.contextPath }/adminAction_getNotices?currentPage=1&currentCount=10">通知管理</a></li>
                    <li class="active">发布新通知</li>
                </ol>
            </div>
            <h4>发布新通知</h4>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-12">
                    <form action="${ pageContext.request.contextPath }/adminAction_saveNotice" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="col-lg-2"><label for="notice_title">标题</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="notice_title" placeholder="请输入标题" name="notice_title"></div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-12"><label for="">正文内容</label></div>
                            <div class="col-lg-12" id="editor">
                                <p>在此处输入正文内容</p>
                            </div>
                            <input class="hidden" name="notice_content" id="notice_content">
                            <input class="hidden" name="notice_abstract" id="notice_abstract">
                            <input class="hidden" name="stu_id" value="${ student.stu_id }">
                        </div>
                        <div class="col-lg-12"><label for="">添加附件</label></div>
                        <div class="form-group">
                            <div class="col-lg-1 col-xs-1" style="padding-top: 5px;">
                                <i class="fa fa-folder-o fa-2x" aria-hidden="true"></i>
                            </div>
                            <div class="col-lg-7 col-xs-7">
                                <input type="text" class="form-control" id="filePath" onclick="openExplore(this)" style="display: inline;" readonly>
                            </div>
                            <div class="col-lg-2 col-xs-2">
                                <a class="btn btn-success " href="javascript:void(0);" role="button" onclick="openExplore(this)">浏览</a>
                            </div>
                            <div class="col-lg-1 col-xs-4">
                                <a href="javascript:void(0);" onclick="addFile(this)">
                                    <i class="fa fa-plus-square-o fa-2x" aria-hidden="true" style="padding-top: 5px;"></i>
                                </a>
                            </div>
                            <div class="col-lg-1 col-xs-1">
                                <a href="javascript:void(0);" onclick="remove(this)">
                                    <i class="fa fa-times fa-2x" aria-hidden="true" style="padding-top: 5px;"></i>
                                </a>
                            </div>
                            <input type="file" class="attach" name="attach" onchange="change(this)">
                        </div>
                        <div class="col-lg-12" style="text-align: right;">
                            <button type="submit" class="btn btn-default btn-success">发布</button>
                        </div>

                    </form>
                </div>
            </div>
            <div></div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>

<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!--wangEditor-->
<script src="js/wangEditor.min.js"></script>

<script>
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

    var E = window.wangEditor
    var editor = new E('#editor')
    editor.customConfig.zIndex = 100
    editor.create()

    $("button[type='submit']").mouseover(function(){
    	var html = editor.txt.html();
    	$("#notice_content").val(html);
    	var text = editor.txt.text();
    	if (text.length>150)
    		$("#notice_abstract").val(text.substring(0,150)+"...");
    	else 
    		$("#notice_abstract").val(text);
    });
    
    function addFile(obj) {
        var $formGroup = $($(obj).parent()).parent();
        var newHtml = "<div class=\"form-group\">\n" +
            "                            <div class=\"col-lg-1 col-xs-1\" style=\"padding-top: 5px;\">\n" +
            "                                <i class=\"fa fa-folder-o fa-2x\" aria-hidden=\"true\"></i>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-lg-7 col-xs-7\">\n" +
            "                                <input type=\"text\" class=\"form-control\" id=\"filePath\" onclick=\"openExplore(this)\" style=\"display: inline;\" readonly>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-lg-2 col-xs-2\">\n" +
            "                                <a class=\"btn btn-success \" href=\"javascript:void(0);\" role=\"button\" onclick=\"openExplore(this)\">浏览</a>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-lg-1 col-xs-4\">\n" +
            "                                <a href=\"javascript:void(0);\" onclick=\"addFile(this)\">\n" +
            "                                    <i class=\"fa fa-plus-square-o fa-2x\" aria-hidden=\"true\" style=\"padding-top: 5px;\"></i>\n" +
            "                                </a>\n" +
            "                            </div>\n" +
            "                            <div class=\"col-lg-1 col-xs-1\">\n" +
            "                                <a href=\"javascript:void(0);\" onclick=\"remove(this)\">\n" +
            "                                    <i class=\"fa fa-times fa-2x\" aria-hidden=\"true\" style=\"padding-top: 5px;\"></i>\n" +
            "                                </a>\n" +
            "                            </div>\n" +
            "                            <input type=\"file\" class=\"attach\" name=\"attach\" onchange=\"change(this)\">\n" +
            "                        </div>";
        $($formGroup).after(newHtml);
    }

    function remove(obj) {
        var parent = $(obj).parent();
        $($(parent).parent()).replaceWith("");
        itemTotal--;
    }

    function openExplore(obj) {
        var attachRow = $($(obj).parent()).parent();
        var file = $(attachRow).children()[5];//第五个孩子就是input file
        file.click();
    }

    function  change(obj) {
        var attachRow = $(obj).parent();
        var file = $(attachRow).children()[5];//第五个孩子就是input file
        var text = $($(attachRow).children()[1]).children()[0];
        $(text).val(file.value);
    }
</script>
</body>
</html>