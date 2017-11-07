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
    <title>中国好2班</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
    <!-- 导航栏 -->
    <link href="${ pageContext.request.contextPath }/css/nav.css" rel="stylesheet" type="text/css">

    <style>
        .recommend {
            color: #666;
            display: inline-block;
            height: 40px;
            width: 120px;
            text-align: center;
            font-size: large;
            line-height: 40px;
        }
        .recommend-active{
            background-color: #F77825;
            color: #ffffff;
        }

        .item {
            background-color: #F9F9F9;
            margin: 10px 0px;
            padding: 10px;
        }

        .item div a {
            font-size: large;
            color: #624645;
        }

        .item div a:hover,
        .item div a:focus {
            color: #066;
        }

        .item-author {
            color: #89A296;
        }

        .item-abstract {
            margin-top: 10px;
            color: #666;
        }

        /*标签云*/
        .cloud { width: 100%; clear: both; overflow: hidden }
        .cloud ul { margin: 20px 0 }
        .cloud ul li { line-height: 30px; height: 30px; display: block; background: #999;  padding: 3px 11px; margin: 10px 10px 0 0; border-radius: 8px; -moz-transition: all 0.5s; -webkit-transition: all 0.5s; -o-transition: all 0.5s; transition: all 0.5s; }
        .cloud ul li a { color: #FFF }
        .cloud ul li:nth-child(8n-7) { background: #8A9B0F }
        .cloud ul li:nth-child(8n-6) { background: #EB6841 }
        .cloud ul li:nth-child(8n-5) { background: #3FB8AF }
        .cloud ul li:nth-child(8n-4) { background: #FE4365 }
        .cloud ul li:nth-child(8n-3) { background: #FC9D9A }
        .cloud ul li:nth-child(8n-2) { background: #EDC951 }
        .cloud ul li:nth-child(8n-1) { background: #C8C8A9 }
        .cloud ul li:nth-child(8n) { background: #83AF9B }
        .cloud ul li:first-child { background: #036564 }
        .cloud ul li:last-child { background: #3299BB }
        .cloud ul li:hover { border-radius: 0; text-shadow: #000 1px 1px 1px }
    </style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="page-flag" title="2"></div>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid" style="margin-top: 10px;">

    <div class="col-lg-1"></div>
    <!--左侧-->
    <div class="col-lg-6">
        <!--最新发布-->
        <div style="border-radius: 5px;background-color: #F9F9F9; height: 40px; padding-left: 10px;">
            <span style="display: inline-block; font-size: large; height: 40px; width: 120px; text-align: center; line-height: 40px; background-color: #555555; color: #ffffff; border-radius: 5px;">最新发布</span>
        </div><!--最新发布-->
<s:iterator value="#page.list" var="obj">
        	<!--item-->
        <div class="item" >
            <div><a href="${ pageContext.request.contextPath }/employmentAction_getEmployInfo?emp_info_id=${ obj.emp_info_id }">${ obj.emp_info_title }</a></div>
            <div class="item-author">
                <i class="fa fa-user-o" aria-hidden="true"></i>
                ${ obj.student.stu_name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <i class="fa fa-calendar" aria-hidden="true"></i>
                ${ obj.emp_info_date }
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <i class="fa fa-eye" aria-hidden="true"></i>
                阅读(${ obj.emp_info_scan })
            </div>
            <div class="item-abstract">
               起始日期：${ obj.emp_info_date }<br>
               截止日期：${ obj.emp_info_deadline }
            </div>
        </div><!--item-->
</s:iterator>

<s:if test="#page.currentPage < #page.totalPage">
        <!--加载更多-->
        <div id="loadMore" style="margin: 10px; border-radius: 5px;background-color: #F9F9F9; height: 10px; line-height: 10px;text-align: center;">
            <a href="javascript:void(0);" style="color: #624645;" onclick="loadMoreEmployment(${ page.currentPage + 1 })">点击加载更多</a>
        </div><!--加载更多-->
</s:if>
    </div><!--左侧-->
    
    <!--右侧-->
    <div class="col-lg-4">
        <!--栏目更新及热门阅读-->
        <div style="border-bottom: 1px solid #E6E6E6; height: 40px; margin-bottom: 15px">
            <span class="recommend recommend-active">热门点击</span>
        </div><!--栏目更新及热门阅读-->

        <div class="hidden-lg hidden-md hidden-sm" style="height: 10px;"></div>
<s:iterator value="#hotEmployInfos" var="hotEmployInfo" status="status">
        <!--通知条目-->
        <div style="margin: 20px 0px;">
        	<s:if test="#status.getCount()==1">
        		<span style="background-color: #FF3300; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center;width: 30px; height: 30px;line-height: 30px; font-size: large;">
                	1
            	</span>
        	</s:if>
            
            <s:if test="#status.getCount()==2">
        		<span style="background-color: #FF6600; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                2
	            </span>
        	</s:if>
        	
        	<s:if test="#status.getCount()==3">
        		<span style="background-color: #FF9900; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                3
	            </span>
        	</s:if>
        	
        	<s:if test="#status.getCount()==4">
        		<span style="background-color: #666666; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                4
	            </span>
        	</s:if>
            
            <span>
                <a href="#">${ hotEmployInfo.emp_info_title }</a>
            </span>
            <span style="color: #75B0B0;">${ hotEmployInfo.student.stu_name  }</span>
            <span style="color: #D8B092;">${ hotEmployInfo.emp_info_date }</span>
        </div>
        <hr>
        <!--通知条目END-->
</s:iterator>
        <!--友情链接-->
        <div style="border-bottom: 1px solid #E6E6E6; height: 40px; margin: 15px 0px;">
            <div style="font-size: large;">友情链接</div>
            <div class="cloud">
                <ul>
                    <li><a href="http://www.gdut.edu.cn">广东工业大学官网</a></li>
                    <li><a href="http://222.200.98.147/">新教务系统</a></li>
                    <li><a href="http://jwgl.gdut.edu.cn">旧教务系统</a></li>
                    <li><a href="http://39.108.165.238/Blog/">廖志行博客</a></li>
                    <li><a href="http://www.baidu.com">百度</a></li>
                </ul>
            </div>
        </div><!--友情链接-->
    </div><!--右侧-->
    <div class="col-lg-1"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
    /* $(function(){
        $(".recommend").mouseover(
            function() {
                $(".recommend[class='recommend recommend-active']").attr("class","recommend");
                $(this).attr("class","recommend recommend-active");
            }
        );
        $(".item").mouseover(
            function() {
                $(this).css("border-bottom", "1px solid #009688");
            }
        );
        $(".item").mouseout(
            function() {
                $(this).css("border-bottom", "");
            }
        );
    }); */
    
    function loadMoreEmployment(page) {
    	$.ajax({
    		url:"${ pageContext.request.contextPath }/employmentAction_getEmployment",
    		data: {currentPage:page},
    		type: "get",
    		success: function(page){
    			for (var i=0; i<page.list.length; i++){
    				var newHtml = "<div class=\"item\" >\n" +
    		        "            <div><a href=\"${ pageContext.request.contextPath }/employmentAction_getEmployInfo?emp_info_id="+page.list[i].emp_info_id+"\">"+ page.list[i].emp_info_title +"</a></div>\n" +
    		        "            <div class=\"item-author\">\n" +
    		        "                <i class=\"fa fa-user-o\" aria-hidden=\"true\"></i>\n" +
    		        "                "+ page.list[i].student.stu_name +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
    		        "                <i class=\"fa fa-calendar\" aria-hidden=\"true\"></i>\n" +
    		        "                "+page.list[i].emp_info_date +"\n" +
    		        "                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
    		        "                <i class=\"fa fa-eye\" aria-hidden=\"true\"></i>\n" +
    		        "                阅读("+page.list[i].emp_info_scan +")\n" +
    		        "            </div>\n" +
    		        "            <div class=\"item-abstract\">\n" +
    		        "                  起始日期："+page.list[i].emp_info_date+"<br> 截止日期："+page.list[i].emp_info_deadline+"\n" +
    		        "            </div>\n" +
    		        "        </div><!--item-->";
    		        $("#loadMore").before(newHtml);
    			}
    			if (page.currentPage<page.totalPage) {
    				var pageNo = page.currentPage + 1;
    				var $a = $("#loadMore").children("a");
    				$($a).attr("onclick","loadMoreEmployment("+pageNo+")");
    			} else {
    				$("#loadMore").css("display", "none");
    			}
    		},
    		dataType:"JSON"
    	});
    }
</script>
</body>
</html>