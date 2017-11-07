<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   
<div class="container-fluid">

    <!--logo和用户名-->
    <div class="row">
        <div class="col-lg-1"></div>

        <!--logo-->
        <div class="col-lg-5 col-xs-12">
            <img src="img/gdut-logo.png" style="width: 10%; ">
            <img src="img/logo.png" style="width: 30%; ">
        </div><!--logo-->

        <div class="col-lg-2"></div>


        <!--个人资料-->
        <div class="col-lg-3" style=" margin-top: 30px;">
            欢迎您,<span id="profile" style="color: #6495ed;">${ student.stu_name }同学</span><span class="caret"></span>
            <a href="${ pageContext.request.contextPath }/adminAction_welcomeAdmin" style="margin-left: 30px;">管理员模块</a>
            <a href="${ pageContext.request.contextPath }/studentAction_logout" style="margin-left: 30px;">退出</a>
            <!--个人资料修改下拉框-->
            <div id="profileBox"  style="text-align: center; background-color: #F9F9F9; display: none;position: absolute; z-index: 8421;border: 1px solid #DDDDDD; border-radius: 5px; width: 150px; padding: 5px;">
                <div style="margin-bottom: 10px; " >
                    <img src="${pageContext.request.contextPath }/${ student.stu_avater_src }"  width="45%" alt="..." class="img-circle">
                    <div style="text-align: left; font-size: small; color: #666666;">${ student.stu_signature }</div>
                </div>
                
                    <a class="btn btn-default btn-sm btn-block " href="${ pageContext.request.contextPath }/studentAction_editStudent?stu_id=${ student.stu_id }">修改资料</a>
                
                
                    <a  class="btn btn-default btn-sm btn-block " href="${ pageContext.request.contextPath }/studentAction_getVerifyCodeOfLogin">修改密码</a>
                
            </div><!--个人资料修改下拉框-->
        </div><!--个人资料-->

        <div class="col-lg-1"></div>
    </div><!--logo和用户名-->

    <!--导航栏-->
    <div class="row" >
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="javascript:void(0);" style="font-size: large; color: #ffffff;">14工商2班</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${ pageContext.request.contextPath }/studentAction_welcome">首页<span class="sr-only">(current)</span></a></li>
                        <li><a href="${ pageContext.request.contextPath }/noticeAction_getNotices">班委通知</a></li>
                        <li><a href="${ pageContext.request.contextPath }/employmentAction_getEmployment">招聘启事</a></li>
                        <li><a href="${ pageContext.request.contextPath }/homeworkAction_getTasks?stu_id=${ student.stu_id }">作业提交</a></li>
                        <li><a href="${ pageContext.request.contextPath }/scoreAction_getLogin">成绩查询</a></li>
                        <li><a href="#">闲来侃侃</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div><!--导航栏-->

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>
    $(function(){
        $("#profile").mouseover(
            function(){
               $("#profileBox").css("display", "block");
            }
        );
        //有可能鼠标离开学生名
        $("#profile").mouseout(
            function(event){
                var x = event.clientX;
                var y = event.clientY;
                var profileBox = document.getElementById("profileBox").getBoundingClientRect();;
                var top = profileBox.top;
                var left = profileBox.left;
                var bottom = profileBox.bottom;
                var right = profileBox.right;
                // alert("x:" + x + "y:" + y + "left:" + left + "right:" + right + "top:" + top + "bottom:" + bottom);
                if (x < left || x > right || y < top || y > bottom) {
                    $("#profileBox").css("display", "none");
                }
            }
        );
        //也有可能鼠标离开个人资料盒
        $("#profileBox").mouseout(
            function(event){
                var x = event.clientX;
                var y = event.clientY;
                var profileBox = document.getElementById("profileBox").getBoundingClientRect();;
                var top = profileBox.top;
                var left = profileBox.left;
                var bottom = profileBox.bottom;
                var right = profileBox.right;
               // alert("x:" + x + "y:" + y + "left:" + left + "right:" + right + "top:" + top + "bottom:" + bottom);
                if (x < left || x > right || y < top || y > bottom) {
                    $("#profileBox").css("display", "none");
                }
            }
        );
        $(".nav li").click(function(){
        	$(".nav li[class='active']").attr("class", "");
        	$(this).attr("class", "active");
        });
        //修改所在页面标识
        var pageFlag = $("#page-flag").attr("title");
        var $jspPage = $(".nav li")[pageFlag];
        $($jspPage).attr("class", "active");
    });
</script>