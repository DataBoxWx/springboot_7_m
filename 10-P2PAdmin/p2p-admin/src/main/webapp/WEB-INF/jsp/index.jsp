<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="description" content="欢迎登录P2P后台管理系统，为您提供各类后台管理操作" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>欢迎登录 - P2P后台管理系统</title>
	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
	<!-- text fonts -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/fonts.googleapis.com.css" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace.min.css" />
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-part2.min.css" />
	<![endif]-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-rtl.min.css" />
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-ie.min.css" />
	<![endif]-->
	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
	<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/static/ace/js/html5shiv.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="login-layout blur-login">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<!-- 登录框上方标题start -->
						<div class="center" style="margin-top:70px;margin-bottom:30px;">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i>
								<span class="red">P2P</span>
								<span class="white" id="id-text2">后台管理系统</span>
							</h1>
							<h4 class="blue" id="id-company-text"></h4>
						</div>
						<!-- 登录框上方标题end -->
						
						<div class="space-6"></div>
						
						<!-- 登录框内容体start -->
						<div class="position-relative">
							<!-- 登录框内部div盒子 start -->
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i>
											请输入登录账号
										</h4>

										<div class="space-6"></div>
										
										<!-- fieldset组合表单中的相关元素start -->
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" id="userName" name="userName" class="form-control" placeholder="请输入登录账户" value="${userName}"/>
													<i class="ace-icon fa fa-user"></i>
												</span>
											</label>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="password" id="password" name="password" class="form-control" placeholder="请输入登录密码" value="${password}"/>
													<i class="ace-icon fa fa-lock"></i>
												</span>
											</label>

											<div class="space"></div>

											<div class="clearfix">
												<label class="inline">
													<input type="checkbox" class="ace" id="rememberMe" name="rememberMe"/>
													<span class="lbl">七天内免登录</span>
												</label>
												<input type="hidden" id="isAutoLogin" name="isAutoLogin" value="${isAutoLogin}"/>
												<button type="button" class="width-35 pull-right btn btn-sm btn-primary" onclick="login()">
													<i class="ace-icon fa fa-key"></i>
													<span class="bigger-110">登录</span>
												</button>
											</div>
											<div class="space-4"></div>
										</fieldset>
										<!-- fieldset组合表单中的相关元素end -->
										
										<div class="space-6"></div>
										
										<!-- 登录异常信息提示 -->
										<div id="loginTip" style="text-align:center;color:red;"></div>
										
									</div><!-- /.widget-main -->

								</div><!-- /.widget-body -->
							</div><!-- /.login-box -->
							<!-- 登录框内部div盒子 end -->
						</div><!-- /.position-relative -->
						<!-- 登录框内容体end -->
						
						<!-- 页面底部版权信息start -->
						<div class="center" style="margin-top:65px;margin-bottom:30px;">
							<h5><span class="white" id="id-text2">北京动力节点教育科技有限公司</span></h5>
							<h5><span class="white" id="id-text2">2009-2017 @ 版权所有</span></h5>
						</div>
						<!-- 页面底部版权信息end -->
						
						<!-- 改变背景色 start -->
						<div class="navbar-fixed-top align-right">
							<br />
							&nbsp;
							<a id="btn-login-dark" href="#">Dark</a>
							&nbsp;
							<span class="blue">/</span>
							&nbsp;
							<a id="btn-login-blur" href="#">Blur</a>
							&nbsp;
							<span class="blue">/</span>
							&nbsp;
							<a id="btn-login-light" href="#">Light</a>
							&nbsp; &nbsp; &nbsp;
						</div>
						<!-- 改变背景色 end -->
					</div><!-- /.login-container -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.main-content -->
	</div><!-- /.main-container -->

	<!-- basic scripts start-->
	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-2.1.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jQuery.md5.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-1.11.3.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/static/js/login.js"></script>
	<!-- basic scripts end-->
	<script>
	jQuery(function($) {
		var isAutoLogin = $("#isAutoLogin").val();
		if (isAutoLogin == 1) {
			login();
		}
	});
	</script>
</body>
</html>