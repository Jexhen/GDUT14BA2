<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <!--学年和学期-->
        <div class="row">
            <div class="col-lg-1">
                <label class="scoreLabel" for="schoolYear">学年</label>
            </div>
            <div class="col-lg-3">
                <select class="form-control" id="schoolYear" name="schoolYear">
                    <option>2017-2018</option>
                </select>
            </div>
            <div class="col-lg-4"></div>
            <div class="col-lg-1">
                <label class="scoreLabel" for="semester">学期</label>
            </div>
            <div class="col-lg-3">
                <select class="form-control" id="semester" name="semester">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
        </div><!--学年和学期-->

        <!--查询功能-->
        <div class="row" style="text-align: center; margin-top: 10px;">
            <div class="col-lg-4 col-xs-4">
                <a class="btn btn-success" href="javascript:void(0);" onclick="queryBySemester()">按学期查询</a>
            </div>
            <div class="col-lg-4 col-xs-4">
                <a class="btn btn-success" href="javascript:void(0);" onclick="queryBySchoolYear()">按学年查询</a>
            </div>
            <div class="col-lg-4 col-xs-4">
                <a class="btn btn-success" href="javascript:void(0);" onclick="queryAll()">全部查询</a>
            </div>
        </div><!--查询功能-->

        <!--绩点展示-->
        <div class="row" id="gpa" style="margin-top: 10px; background-color: #EEEEEE; border: 1px solid #CCCCCC; border-radius: 5px; height: 40px; text-align: center; line-height: 40px; font-size: large;">
            绩点：
        </div><!--绩点展示-->

        <!--成绩明示-->
        <div class="row" style="margin-top: 10px;">
            <table class="table table-striped">
                <thead style="font-weight: bold;">
                    <tr>
                        <td>
                            <input type="checkbox" id="selectAll">
                        </td>
                        <td>课程名称</td>
                        <td>成绩</td>
                        <td>绩点</td>
                        <td>学分</td>
                    </tr>
                </thead>
                <tbody>
                	
                </tbody>
            </table>
        </div><!--成绩明示-->
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
	    createSchoolYear();
	    $(window).bind('beforeunload',function(){ 
	    	$.ajax({
	            url:"${pageContext.request.contextPath}/scoreAction_logout",
	            type:"post"
	        });
	    	return '确定离开此页面吗？'; 
	    }); 
	});
	
	$("#selectAll").change(function(){
       $("tbody tr td input").prop("checked",$(this).prop("checked"));
       $("#gpa").html("绩点:" + getGPA());
    });
	
	function createSchoolYear() {
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var newHtml = "";
	    for (var i=year;i>(year-10);i--) {
	        newHtml = newHtml + "<option value='"+i+"'>"+i+"-"+(i+1)+"</option>";
	    }
	    $("#schoolYear").html(newHtml);
	}
	
	function queryBySemester(){
		var $schoolYear = $("#schoolYear").val();
		var $semester = $("#semester").val();
		var resultTab = "";
        $.ajax({
            url:"${pageContext.request.contextPath}/scoreAction_getScoresBySemester",
            data:{"schoolYear":$schoolYear,"semester":$semester},
            type:"post",
            success:function(data){
            	for (var i=0;i<data.length;i++) {
            		resultTab = resultTab + "<tr>\n" +
                    "                        <td>\n" +
                    "                            <input type=\"checkbox\">\n" +
                    "                        </td>\n" +
                    "                        <td>"+data[i].courseName+"</td>\n" +
                    "                        <td>"+data[i].score+"</td>\n" +
                    "                        <td>"+data[i].gradePoint+"</td>>\n" +
                    "                        <td>"+data[i].credit+"</td>\n" +
                    "                    </tr>";
            	}
            	$("tbody").html(resultTab);
            	$("tbody tr td input").prop("checked","true");
            	$("#selectAll").prop("checked", "true");
            	$("#gpa").html("绩点:" + getGPA());
            },
            dataType:"json"
        });
    }
	
	function queryBySchoolYear(){
		var $schoolYear = $("#schoolYear").val();
		var resultTab = "";
        $.ajax({
            url:"${pageContext.request.contextPath}/scoreAction_getScoresBySchoolYear",
            data:{"schoolYear":$schoolYear},
            type:"post",
            success:function(data){
            	for (var i=0;i<data.length;i++) {
            		resultTab = resultTab + "<tr>\n" +
                    "                        <td>\n" +
                    "                            <input type=\"checkbox\">\n" +
                    "                        </td>\n" +
                    "                        <td>"+data[i].courseName+"</td>\n" +
                    "                        <td>"+data[i].score+"</td>\n" +
                    "                        <td>"+data[i].gradePoint+"</td>>\n" +
                    "                        <td>"+data[i].credit+"</td>\n" +
                    "                    </tr>";
            	}
            	$("tbody").html(resultTab);
            	$("tbody tr td input").prop("checked","true");
            	$("#selectAll").prop("checked", "true");
            	$("#gpa").html("绩点:" + getGPA());
            },
            dataType:"json"
        });
	}
	
	function queryAll(){
		var resultTab = "";
        $.ajax({
            url:"${pageContext.request.contextPath}/scoreAction_getAllScores",
            type:"post",
            success:function(data){
            	for (var i=0;i<data.length;i++) {
            		resultTab = resultTab + "<tr>\n" +
                    "                        <td>\n" +
                    "                            <input type=\"checkbox\">\n" +
                    "                        </td>\n" +
                    "                        <td>"+data[i].courseName+"</td>\n" +
                    "                        <td>"+data[i].score+"</td>\n" +
                    "                        <td>"+data[i].gradePoint+"</td>>\n" +
                    "                        <td>"+data[i].credit+"</td>\n" +
                    "                    </tr>";
            	}
            	$("tbody").html(resultTab);
            	$("tbody tr td input").prop("checked","true");
            	$("#selectAll").prop("checked", "true");
            	$("#gpa").html("绩点:" + getGPA());
            },
            dataType:"json"
        });
	}
	
	$("tbody").change(function(){
		$("#gpa").html("绩点:" + getGPA());
	});
	
	function getGPA() {
		var allCreditScoreSum = 0;
		var allCreditSum = 0;
		//获得被选的所有行
		$("tbody tr td input:checked").each(function(){
			var $td = $(this).parent();
			var $tr = $($td).parent();
			//获得这些行的学分绩点，并求乘积，再求这些行的乘积和
			var $gradePoint = $($tr).children()[3];
			//获得这些行的学分，再求这些行的学分和
			var $credit = $($tr).children()[4];
			var gradePoint = $($gradePoint).html();
			var credit = $($credit).html();
			allCreditScoreSum = allCreditScoreSum + gradePoint * credit;
			allCreditSum = allCreditSum + credit * 1.0;
		});
		//利用学分成绩和除于学分和获得平均绩点
		var GPA = allCreditScoreSum * 1.0 / allCreditSum;
		return GPA.toFixed(3);
	}
</script>
</body>
</html>