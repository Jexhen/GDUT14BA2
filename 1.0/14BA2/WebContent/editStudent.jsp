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
        <div class="col-lg-2" style="background-color: #EEEEEE;">
        </div>
        <div class="col-lg-10">
            <h4>修改资料</h4>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <form id="editForm" action="${ pageContext.request.contextPath }/studentAction_saveStudent" method="post" enctype="multipart/form-data">
                        
                        <div class="form-group">
	                        <div class="col-lg-2"><label>头像</label></div>
                            <div class="col-lg-8">
                                <img src="${pageContext.request.contextPath }/${ targetStu.stu_avater_src }"  width="45%" alt="..." class="img-circle">
                            </div>
                            <div class="col-lg-2">
                            	<a class="btn btn-primary btn-sm" href="javascript:void(0);" onclick="openFileExplore()" >更改头像</a>
                            </div>
                            <div class="row">
                            	<input type="file" name="avata" id="avata" style="display:none;">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_id">学号</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_id" placeholder="请输入学号" name="stu_id" value="${ targetStu.stu_id }" readonly></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_name">姓名</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_name" placeholder="请输入姓名" name="stu_name" value="${ targetStu.stu_name }" readonly></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_dormitory">宿舍</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_dormitory" placeholder="请输入宿舍号" name="stu_dormitory" value="${ targetStu.stu_dormitory }"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_signature">个性签名</label></div>
                            <div class="col-lg-10"><input type="text" class="form-control" id="stu_signature" placeholder="请输入个性签名" name="stu_signature" value="${ targetStu.stu_signature }"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_email">邮箱</label></div>
                            <div class="col-lg-10"><input type="email" class="form-control" id="stu_email" placeholder="请输入邮箱" name="stu_email" value="${ targetStu.stu_email }" readonly></div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-2"><label for="stu_birthday">出生日期</label></div>
                            <div class="col-lg-10">
                                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="stu_birthday" value="${ targetStu.stu_birthday }" name="stu_birthday" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-2"><label>性别</label></div>
                            <div class="col-lg-10">
                            	<s:iterator value="{'男','女','其他'}" var="sex">
                            		<s:if test="'#sex'=='#targetStu.stu_sex'">
                            			<label><input type="radio" name="stu_sex" value="${ sex }" checked="checked"><s:property value="#sex"/></label>
                            		</s:if>
                            		<s:else>
                            			<label><input type="radio" name="stu_sex" value="${ sex }"><s:property value="#sex"/></label>
                            		</s:else>
                            	</s:iterator>
                            </div>
                        </div>

                        <div class="col-lg-12" style="text-align: right;">
                            <button type="submit" class="btn btn-default btn-success">修改</button>
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
	
	$("#editForm").validate({
		rules:{
			stu_signature:{
				minlength:1,
				maxlength:30
			},
			stu_dormitory:{
				required:true
			},
			stu_sex:{
				required:true
			}
		},
		messages:{
			stu_signature:{
				minlength:"最短输入1个字符",
				maxlength:"最长输入30个字符"
			},
			stu_dormitory:{
				required:"必须输入宿舍"
			},
			stu_sex:{
				required:"性别必选"
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
    
    function openFileExplore() {
    	$("#avata").click();
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