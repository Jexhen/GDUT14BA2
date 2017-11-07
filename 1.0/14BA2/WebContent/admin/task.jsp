<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
            <div class="admin-item admin-item-active"><a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=1&currentCount=10"><i class="fa fa-file-word-o" aria-hidden="true"></i>
                作业管理
            </a>
            <div style="text-align: right; color: cornflowerblue;"><i class="fa fa-hand-o-right" aria-hidden="true"></i></div>
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
                    <li><a href="#">后台管理系统</a></li>
                    <li class="active">作业管理</li>
                </ol>
            </div>
            <div class="row">
            	<form action="${ pageContext.request.contextPath }/adminAction_getTaskBySearchCondition" method="post">
	               <div class="col-lg-4">
	                   <input type="text" class="form-control" id="task_name"  name="task_name" placeholder="按作业名搜索" value='<s:property value='#task_name'/>'>
	               </div>
	                <div class="col-lg-4">
	                    <button type="submit" class="btn btn-default">搜索</button>
	                </div>
                </form>
                <div class="col-lg-4">
                    <a class="btn btn-success" href="${ pageContext.request.contextPath }/admin/addTask.jsp">发布新作业</a>
                </div>
            </div>
            <div class="row" id="tips" style="background-color:#A0D98F; text-align: center; margin:10px 0px;display:none;">
				<span style="color:#487C35; font-size:large;" id="tipsText"></span>
            </div>
            <h4>全部作业</h4>
            <div class="row" style="margin-top: 10px; text-align: right;">
            	共【<s:property value="#page.totalCount"/>】条记录,每页显示
            	<select id="currentCount">
            		<s:iterator value="{10,15,20,25,30,35,40,45,50}" var="num">
            			<s:if test="#num==#page.currentCount">
            				<option value="<s:property value='#num'/>" selected="selected"><s:property value='#num'/></option>
            			</s:if>
            			<s:else>
	            			<option value="<s:property value='#num'/>"><s:property value='#num'/></option>
            			</s:else>
            		</s:iterator>
            	</select>
            	条,共【<s:property value="#page.totalPage"/>】页
            </div>
            <div class="row" style="margin-top: 10px;">
                <table class="table table-striped">
                   <thead>
	                   <tr>
	                       <td>标题</td>
	                       <td>截止日期</td>
	                       <td>查看全部</td>
	                       <td>打包下载</td>
	                       <td>删除</td>
	                    </tr>
                   </thead>
                   <tbody>
                   	<s:iterator value="#page.list" var="member">
                   		<tr>
	                        <td><s:property value="#member.task_name"/></td>
	                        <td><s:property value="#member.task_deadline"/></td>
	                        <td><a href="${ pageContext.request.contextPath }/adminAction_getHomeworkOfTask?task_id=<s:property value='#member.task_id'/>"><i class="fa fa-eye" aria-hidden="true"></i></a></td>
	                        <td>
	                            <a href="${ pageContext.request.contextPath }/adminAction_downloadAllHomework?task_id=<s:property value='#member.task_id'/>"><i class="fa fa-download" aria-hidden="true"></i></a>
	                        </td>
	                        <td>
	                            <a onclick="delTask(<s:property value="#member.task_id"/>)"><i class="fa fa-times" aria-hidden="true"></i></a>
	                        </td>
	                    </tr>
                   	</s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="row" style="text-align:center;">
            	<nav aria-label="Page navigation">
				  <ul class="pagination">
				  <s:if test="#page.currentPage==1">
				  		<li class="disabled">
					      <a href="javascript:void(0);" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				  	</s:if>
				  	<s:else>
				  		<li>
					      <a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=${ page.currentPage-1 }&currentCount=" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				  	</s:else>
				  <s:iterator begin="1" end="#page.totalPage" status="status">
				  	<s:if test="#status.count==#page.currentPage">
				  		<li class="active"><a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=${ status.count }&currentCount="><s:property value='#status.count'/></a></li>
				  	</s:if>
				  	<s:else>
				  		<li><a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=${ status.count }&currentCount="><s:property value='#status.count'/></a></li>
				  	</s:else>
				  </s:iterator>
				  <s:if test="#page.currentPage==#page.totalPage">
				  		<li class="disabled">
					      <a href="javascript:void(0);" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				  	</s:if>
				  	<s:else>
				  		<li>
					      <a href="${ pageContext.request.contextPath }/adminAction_getTasks?currentPage=${ page.currentPage+1 }&currentCount=" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				  	</s:else>  
				  </ul>
				</nav>
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
    
    function delTask(task_id) {
    	$.ajax({
    		url:"${pageContext.request.contextPath}/adminAction_delTask",
    		data:{"task_id":task_id},
    		type:"post",
    		success:function(data){
    			$("#tipsText").html(data);
				$("#tips").fadeIn("normal", function() {
					var timer = setInterval(function(){
						$("#tips").fadeOut("normal");
						clearInterval(timer);
						location.reload();
					},2000);
				});
    		}
    	});
    }
</script>
</body>
</html>