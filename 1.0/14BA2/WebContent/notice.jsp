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
    <title>班委通知-中国好2班</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
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
    </style>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="page-flag" title="1"></div>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid" style="margin-top: 10px;">

    <div class="col-lg-1"></div>
    <!--左侧-->
    <div class="col-lg-6" style="background-color: #F9F9F9; border-radius: 5px; border: #e9e9e9 1px solid; ">
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="${ pageContext.request.contextPath }/studentAction_welcome">首页</a></li>
                <li><a href="${ pageContext.request.contextPath }/noticeAction_getNotices">通知</a></li>
                <li class="active">通知详情</li>
            </ol>
        </div>
        <div class="row" style="padding: 0px 20px 10px;">
            <h3 style="text-align: center;"><s:property value="#notice.notice_title"/></h3>
            <div class="col-lg-12" style="border: 1px dashed #CCCCCC; color: #666666; text-align: center;">
                发布时间：<s:property value="#notice.notice_date"/>&nbsp;&nbsp;&nbsp;
                编辑：<s:property value="#notice.student.stu_name"/>&nbsp;&nbsp;&nbsp;
                阅读(<s:property value="#notice.notice_scan"/>)
            </div>
            <div class="col-lg-12" style="margin-top: 15px;">
                <s:property value="#notice.notice_content" escape="false"/>
            </div>
            <s:if test="#notice.attachs.size!=0">
            	<div class="col-lg-12" style="margin-top: 15px;"><strong style="font-size:large">通知附件下载</strong></div>
            	<s:iterator value="#notice.attachs" var="attach">
            		<div class="col-lg-12" style="margin-top: 15px;">
            			<a href="${ pageContext.request.contextPath }/noticeAction_downloadNoticeAttach?notice_attach_id=<s:property value='#attach.notice_attach_id'/>"><s:property value='#attach.noitce_attach_name'/></a>
            		</div>
            	</s:iterator>
            </s:if>
        </div>
    </div><!--左侧-->
    <!--右侧-->
    <div class="col-lg-4">
        <!--栏目更新及热门阅读-->
        <div style="border-bottom: 1px solid #E6E6E6; height: 40px; margin-bottom: 15px">
            <span class="recommend recommend-active">热门点击</span>
            <span class="recommend">栏目更新</span>
        </div><!--栏目最新-->

        <div class="hidden-lg hidden-md hidden-sm" style="height: 10px;"></div>
		<div id="item">
	        <!--通知条目-->
	        <div style="margin: 20px 0px;">
	            <span style="background-color: #FF3300; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center;width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                1
	            </span>
	            <span>
	                <a href="${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id=<s:property value="#hot[0].notice_id"/>"><s:property value="#hot[0].notice_title"/></a>
	            </span>
	            <span style="color: #75B0B0;"><s:property value="#hot[0].student.stu_name"/></span>
	            <span style="color: #D8B092;"><s:property value="#hot[0].notice_date"/></span>
	        </div>
	        <hr>
	        <div class="row" style="margin: 10px 0px;">
	            <span style="background-color: #FF6600; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                2
	            </span>
	            <span>
	                <a href="${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id=<s:property value="#hot[1].notice_id"/>"><s:property value="#hot[1].notice_title"/></a>
	            </span>
	            <span style="color: #75B0B0;"><s:property value="#hot[1].student.stu_name"/></span>
	            <span style="color: #D8B092;"><s:property value="#hot[1].notice_date"/></span>
	        </div>
	        <hr>
	        <div class="row" style="margin: 10px 0px;">
	            <span style="background-color: #FF9900; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                3
	            </span>
	            <span>
	                <a href="${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id=<s:property value="#hot[2].notice_id"/>"><s:property value="#hot[2].notice_title"/></a>
	            </span>
	            <span style="color: #75B0B0;"><s:property value="#hot[2].student.stu_name"/></span>
	            <span style="color: #D8B092;"><s:property value="#hot[2].notice_date"/></span>
	        </div>
	        <hr>
	        <div class="row" style="margin: 10px 0px;">
	            <span style="background-color: #666666; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center; width: 30px; height: 30px;line-height: 30px; font-size: large;">
	                4
	            </span>
	            <span>
	                <a href="${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id=<s:property value="#hot[3].notice_id"/>"><s:property value="#hot[3].notice_title"/></a>
	            </span>
	            <span style="color: #75B0B0;"><s:property value="#hot[3].student.stu_name"/></span>
	            <span style="color: #D8B092;"><s:property value="#hot[3].notice_date"/></span>
	        </div>
	        <hr>
	        <!--通知条目END-->
        </div>
    </div><!--右侧-->
    <div class="col-lg-1"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<script>
	/* 判断是否为第一次加载页面，如若第一次加载页面,默认“栏目更新”并没有更新，所以updateFlag为false
	同时保存“热门点击”的html,不用在页面加载后继续访问后台服务器,在鼠标滑动至“热门点击”时将item的html设置回热门html即可
	而“栏目更新”的html则通过ajax异步访问后台获得数据拼接而成，拼接成功后立即保存至recentHTML,同时将updateFlag置为true
	表明"栏目更新"的html已经读取,下次在鼠标再滑动回“栏目更新”时,不用继续访问后台,减轻后台压力
	*/
	var updateFlag = false;
	var $hotHTML = $("#item").html();//热门点击的html
	var $recentHTML = "";
    $(function(){
        $(".recommend").mouseover(
            function() {
                $(".recommend[class='recommend recommend-active']").attr("class","recommend");
                $(this).attr("class","recommend recommend-active");
                var $recommend =  $(".recommend-active").html();
                if ($recommend==="栏目更新") {
                	if (!updateFlag) {
                		$.ajax({
                			url:"${ pageContext.request.contextPath }/noticeAction_getRecentNotice",
                			type:"post",
                			success:function(data){
                				for (var i=0;i<data.length;i++){
                					$recentHTML = $recentHTML + "<div style=\"margin: 20px 0px;\">\n" +
                			        "\t            <span style=\"background-color: #FF3300; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center;width: 30px; height: 30px;line-height: 30px; font-size: large;\">\n" +
                			        "\t                "+(i+1)+"\n" +
                			        "\t            </span>\n" +
                			        "\t            <span>\n" +
                			        "\t                <a href=\"${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id="+data[i].notice_id+"\">"+data[i].notice_title+"</a>\n" +
                			        "\t            </span>\n" +
                			        "\t            <span style=\"color: #75B0B0;\">"+data[i].student.stu_name+"</span>\n" +
                			        "\t            <span style=\"color: #D8B092;\">"+data[i].notice_date+"</span>\n" +
                			        "\t        </div>\n" +
                			        "\t        <hr>";
                				}
                				$("#item").html($recentHTML);
                				updateFlag=true;
                			},
                			dataType:"json"
                		});
                	} else {
                		$("#item").html($recentHTML);
                	}
                } else {
                	$("#item").html($hotHTML);
                }
            }
        );
       
    });
</script>
</body>
</html>