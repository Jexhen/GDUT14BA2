<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
        .notice-title a {
            color: #66668F;
        }

        .notice-title a:focus,
        .notice-title a:hover{
            color: black;
        }

        .emp-title a:focus,
        .emp-title a:hover {
            color: #066;
        }

        .emp-title a {
            color: #624645;
        }
    </style>
</head>
<body>
<div id="page-flag" title="0"></div>
<jsp:include page="header.jsp"></jsp:include>
<div class="container-fluid">


    <!--轮播图和班委通知-->
    <div class="row">
        <!--轮播图-->
        <div class="col-lg-6" style="margin-top: 10px;">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                	<s:iterator value="#carousels" status="stts">
                		<s:if test="stts.index==0">
            			    <li data-target="#carousel-example-generic" data-slide-to="<s:property value='#stts.index'/>" class="active"></li>
                		</s:if>
                		<s:else>
                			<li data-target="#carousel-example-generic" data-slide-to="<s:property value='#stts.index'/>"></li>
                		</s:else>
                	</s:iterator>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                	<s:iterator value="#carousels" var="crs" status="stts">
                		<s:if test="#stts.count==1">
	                		<div class="item active">
		                        <img src="<s:property value='#crs.crs_src'/>" alt="<s:property value='#crs.crs_desc'/>">
		                        <div class="carousel-caption">
		                            <s:property value='#crs.crs_desc'/>
		                        </div>
		                    </div>
                		</s:if>
                		<s:else>
	                		<div class="item">
		                        <img src="<s:property value='#crs.crs_src'/>" alt="<s:property value='#crs.crs_desc'/>">
		                        <div class="carousel-caption">
		                            <s:property value='#crs.crs_desc'/>
		                        </div>
		                    </div>
                		</s:else>
                	</s:iterator>
                
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div> <!--轮播图-->
        <!--班级通知-->
        <div class="col-lg-6" style="margin-top: 10px;">
            <!--标题栏-->
            <div style="border-bottom: 1px solid #E6E6E6;">
                <span style="background-color: #F77825; display: block; height: 40px; width: 120px; text-align: center; color: #ffffff; font-size: large; line-height: 40px; ">
                    最近通知
                </span>
            </div><!--标题栏-->
			<s:iterator value="#notices" var="notice" status="status">
				<!--通知条目-->
	            <div class="row" style="margin: 10px 0px;">
	                <div class="col-lg-1" style="background-color: #FF3300; border-radius: 1px; display: inline-block;color: #ffffff; text-align: center;width: 40px; height: 40px;line-height: 40px; font-size: large;">
	                    ${ status.count }
	                </div>
	                <div class="col-lg-11">
	                    <div class="row notice-title" style="padding-left: 9px;">
	                        <a href="${ pageContext.request.contextPath }/noticeAction_getNotice?notice_id=${ notice.notice_id }" style="font-size: large; margin-left: 5px;">${ notice.notice_title }</a>
	                    </div>
	                    <div class="row">
	                        <div class="col-lg-2" style="color: #75B0B0;">${ notice.student.stu_name }</div>
	                        <div class="col-lg-3" style="color: #D8B092;">${ notice.notice_date }</div>
	                    </div>
	                </div>
	            </div>
	            <hr>
	        </s:iterator>
            <!--通知条目END-->
        </div><!--班级通知-->
    </div><!--轮播图和班委通知-->

    <!--招聘启示和热门侃侃-->
    <div class="row">
        <!--招聘启示-->
        <div class="col-lg-6" style="margin-top: 15px;">
        <div style="font-size: large; font-weight: bold;">招聘启示</div>
        <hr style="border: solid #DB6D4C"/>
        <s:iterator value="#employInfos" var="empInfo">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                    <img src="${ pageContext.request.contextPath }/${ empInfo.student.stu_avater_src }" width="90%">
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-8">
                    <div class="col-lg-12 emp-title"><a href="${ pageContext.request.contextPath }/employmentAction_getEmployInfo?emp_info_id=${ empInfo.emp_info_id }" style="font-size: large; ">${ empInfo.emp_info_title }</a></div>
                    <div class="col-lg-12" style="color:#66668F;">
                        有效期限：${ empInfo.emp_info_date }至${ empInfo.emp_info_deadline }
                    </div>
                </div>
            </div>
            <hr>
        
        
        </s:iterator>
        
        </div><!--招聘启示-->
        <!--热门侃侃-->
        <div class="col-lg-6" style="margin-top: 15px;">
            <div style="font-size: large; font-weight: bold;">热门侃侃</div>
            <hr style="border: solid #DB6D4C"/>
            <div class="row">
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-8">
                    <div class="col-lg-12 emp-title"><a href="#" style="font-size: large; ">待续</a></div>
                    <div class="col-lg-12" style="color:#66668F;">
                        <i class="fa fa-user-o" aria-hidden="true"></i>
                        发布者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                        2017-09-28 22:57
                        <br/>
                        <i class="fa fa-comment-o" aria-hidden="true"></i>
                        评论(0)&nbsp;&nbsp;
                        <i class="fa fa-eye" aria-hidden="true"></i>
                        阅读(0)
                    </div>
                </div>
                <div class="col-lg-2 col-md-1 col-sm-2 col-xs-4">
                    <img src="${ student.stu_avater_src }" width="90%">
                </div>
            </div>
            <hr>
        </div><!--热门侃侃-->
    </div><!--招聘启示和热门吐槽-->
</div><!--container-fuild-->

<jsp:include page="footer.jsp"></jsp:include>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<!--轮播图js-->
<script src="js/sliders.js"></script>
</body>
</html>