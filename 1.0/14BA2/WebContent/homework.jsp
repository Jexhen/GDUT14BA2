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
    <title>作业提交-中国好2班</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
    <!-- 导航栏 -->
    <link href="${ pageContext.request.contextPath }/css/nav.css" rel="stylesheet" type="text/css">

    <style>
        .homework {
            margin-top: 10px;
        }

        .attach {
            visibility: hidden;
            height: 0.5px;
        }

        .fa-times {
            color: red;
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

<div id="page-flag" title="3"></div>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <form method="post" action="${ pageContext.request.contextPath }/homeworkAction_saveHomework" enctype="multipart/form-data">
                <div class="homework row">
                    <div class="col-lg-11 col-xs-8">
                        <select class="form-control input-sm" id="task_id" name="task_id">
                        	<s:iterator value="#tasks" var="task">
                        		<option value="${ task.task_id }">${ task.task_name }--截止日期${ task.task_deadline }</option>
                        	</s:iterator>
                        </select>
                    </div>
                    <div class="col-lg-1 col-xs-4">
                        <a href="javascript:void(0);" onclick="addFile()">
                            <i class="fa fa-plus-square-o fa-2x" aria-hidden="true" style="padding-top: 5px;"></i>
                        </a>
                    </div>
                </div>
                <div class="homework row attach-row">
                    <div class="col-lg-1 col-xs-1" style="padding-top: 5px;">
                        <i class="fa fa-folder-o fa-2x" aria-hidden="true"></i>
                    </div>
                    <div class="col-lg-8 col-xs-7">
                        <input type="text" class="form-control" id="filePath" onclick="openExplore(this)" style="display: inline;" readonly>
                    </div>
                    <div class="col-lg-2 col-xs-2">
                        <a class="btn btn-success " href="javascript:void(0);" role="button" onclick="openExplore(this)">浏览</a>
                    </div>
                    <div class="col-lg-1 col-xs-1">
                        <a href="javascript:void(0);" onclick="remove(this)">
                            <i class="fa fa-times fa-2x" aria-hidden="true" style="padding-top: 5px;"></i>
                        </a>
                    </div>
                    <input type="file" class="attach" name="attach" onchange="change(this)">
                    <input type="hidden" name="stu_id" value="${ student.stu_id }">
                    <input type="hidden" name="stu_name" value="${ student.stu_name }">
                </div>
                <div id="submit" class="homework row">
                    <div class="col-lg-12">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </div>
                <div class="row" id="tips" style="background-color:#A0D98F; text-align: center; margin:10px 0px;display:none;">
					<span style="color:#487C35; font-size:large;" id="tipsText"></span>
	            </div>
                <h3>已提交作业列表</h3>
                <div class="row">
                <table class="table table-striped">
                	<thead>
                		<tr>
                			<td>作业名</td>
                			<td>删除</td>
                		</tr>
                	</thead>
                	<tbody>
                		<s:iterator value="#homeworks" var="homework">
	                		<tr>
	                			<td><s:property value="#homework.homework_name"/></td>
	                			<td>
	                				<a href="javascript:void(0);" onclick="removeHomework(<s:property value='#homework.homework_name'/>)">
			                            <i class="fa fa-times" aria-hidden="true"></i>
			                        </a>
	                			</td>
	                		</tr>
                		</s:iterator>
                	</tbody>
                </table>
               </div>
            </form>
        </div>
    </div>
    
    <div class="modal fade" tabindex="-1" role="dialog" id="tipsModal">
	 <div class="modal-dialog" role="document">
	   <div class="modal-content">
	     <div class="modal-header">
	       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	       <h4 class="modal-title">页面提示</h4>
	     </div>
	     <div class="modal-body" id="tips">
	       <p>One fine body&hellip;</p>
	     </div>
	     <div class="modal-footer">
	       <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	       <button type="button" class="btn btn-primary" onclick="goHomeworkPage()">确认</button>
	     </div>
	   </div><!-- /.modal-content -->
	 </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
	$(function(){
		getTips();
	});
	
	$("#task_id").change(function(){
		var $task_id = $(this).val();
		var stu_id = ${ student.stu_id };
		$.ajax({
			url:"${pageContext.request.contextPath}/homeworkAction_getHomeworkByTask_id",
			data:{"task_id":$task_id,"stu_id":stu_id},
			type:"post",
			success:function(data){
				var html = "";
				for (var i=0;i<data.length;i++) {
					html = html + "<tr>\n" +
		            "<td>"+data[i].homework_name+"</td>\n" +
		            "<td>\n" +
		            "<a href=\"javascript:void(0);\" onclick=\"removeHomework("+data[i].homework_id+")\">\n" +
		            "<i class=\"fa fa-times\" aria-hidden=\"true\"></i>\n" +
		            "</a>\n" +
		            "</td>\n" +
		            "</tr>";
				}
				$("tbody").html(html);
			},
			dataType:"json"
		});
	})

    var itemMax = 3;
    var itemTotal = 1;
    function addFile() {
        if (itemTotal < itemMax) {
            var beforeRow = $(".attach-row")[itemTotal - 1];
            if (itemTotal==0)
                beforeRow = $(".homework")[0];
            itemTotal++;
            var newHtml = "<div class=\"homework row attach-row\">\n" +
                "                    <div class=\"col-lg-1 col-xs-1\" style=\"padding-top: 5px;\">\n" +
                "                        <i class=\"fa fa-folder-o fa-2x\" aria-hidden=\"true\"></i>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-8 col-xs-7\">\n" +
                "                        <input type=\"text\" class=\"form-control\" id=\"filePath\" onclick=\"openExplore(this)\" style=\"display: inline;\" readonly>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-2 col-xs-2\">\n" +
                "                        <a class=\"btn btn-success \" href=\"javascript:void(0);\" role=\"button\" onclick=\"openExplore(this)\">浏览</a>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-1 col-xs-1\">\n" +
                "                        <a href=\"javascript:void(0);\" onclick=\"remove(this)\">\n" +
                "                            <i class=\"fa fa-times fa-2x\" aria-hidden=\"true\" style=\"padding-top: 5px;\"></i>\n" +
                "                        </a>\n" +
                "                    </div>\n" +
                "                    <input type=\"file\" class=\"attach\" name=\"attach\" onchange=\"change(this)\">\n" +
                "                </div>";
            $(beforeRow).after(newHtml);
        } else {

        }
    }

    function remove(obj) {
        var parent = $(obj).parent();
        $($(parent).parent()).replaceWith("");
        itemTotal--;
    }
    
    function removeHomework(homework_id) {
        $.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/homeworkAction_removeHomework",
			data:{"homework_id":homework_id},
			success: function(data) {
				location.reload();
			}
		});
    }

    function openExplore(obj) {
        var attachRow = $($(obj).parent()).parent();
        var file = $(attachRow).children()[4];//第五个孩子就是input file
        file.click();
    }

    function  change(obj) {
        var attachRow = $(obj).parent();
        var file = $(attachRow).children()[4];//第五个孩子就是input file
        var text = $($(attachRow).children()[1]).children()[0];
        $(text).val(file.value);
    }
    
    function getTips() {
    	if ("${ isOk }"==="true") {
    		$("#tipsText").html("上传作业成功!");
			$("#tips").fadeIn("normal", function() {
				var timer = setInterval(function(){
					$("#tips").fadeOut("normal");
					clearInterval(timer);
					goHomeworkPage();
				},2000);
			});
    	} else if ("${ isOk }"==="false"){
    		$("#tipsText").html("上传作业失败!");
			$("#tips").fadeIn("normal", function() {
				var timer = setInterval(function(){
					$("#tips").fadeOut("normal");
					clearInterval(timer);
					goHomeworkPage();
				},2000);
			});
    	} else {
    		
    	}
    }
    
  	//弹出提示信息的模态框
    function showTips(tips) {
    	$("#tips").empty();
    	$("#tips").append(tips);
    	$("#tipsModal").modal();
    }
  	
  	function goHomeworkPage() {
  		location.href="${ pageContext.request.contextPath }/homeworkAction_getTasks?stu_id=${ student.stu_id }";
  	}
</script>
</body>
</html>