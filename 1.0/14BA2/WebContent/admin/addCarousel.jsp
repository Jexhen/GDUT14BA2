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
            <div class="admin-item  admin-item-active"><a href="${ pageContext.request.contextPath }/adminAction_getCarousels"><i class="fa fa-picture-o" aria-hidden="true"></i>
                图片管理
            </a>
            <div style="text-align: right; color: cornflowerblue;"><i class="fa fa-hand-o-right" aria-hidden="true"></i></div>
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
                    <li><a href="${ pageContext.request.contextPath }/adminAction_getCarousels">图片管理</a></li>
                    <li class="active">新增图片</li>
                </ol>
            </div>
            <h4>新增图片</h4>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-12">
                    <form action="${ pageContext.request.contextPath }/adminAction_saveCarousel" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="col-lg-2"><label for="crs_desc">图片描述</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" placeholder="请输入图片描述(可选)" name="crs_desc"></div>
                        </div>

                        <div class="col-lg-12"><label for="">上传图片</label></div>
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
                            <input type="file" class="attach" name="attach" onchange="change(this)">
                        </div>
                        <div class="col-lg-12" style="text-align: right;">
                            <button type="submit" class="btn btn-default btn-success">添加</button>
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
<script src="${ pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>


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

    function openExplore(obj) {
        var attachRow = $($(obj).parent()).parent();
        var file = $(attachRow).children()[3];//第4个孩子就是input file
        file.click();
    }

    function  change(obj) {
        var attachRow = $(obj).parent();
        var file = $(attachRow).children()[3];//第4个孩子就是input file
        var text = $($(attachRow).children()[1]).children()[0];
        $(text).val(file.value);
    }
</script>
</body>
</html>