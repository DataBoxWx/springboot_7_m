<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>添加用户 - P2P后台管理系统</title>
	<meta name="description" content="Restyling jQuery UI Widgets and Elements" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<!-- bootstrap & fontawesome -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
	<!-- page specific plugin styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/jquery-ui.min.css" />
	<!-- text fonts -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/fonts.googleapis.com.css" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-skins.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-rtl.min.css" />
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ace/css/ace-ie.min.css" />
	<![endif]-->
	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-extra.min.js"></script>
	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
	<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/static/ace/js/html5shiv.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="no-skin">

	<jsp:include page="commons/top.jsp" />
	
	<div class="main-container ace-save-state" id="main-container">

		<jsp:include page="commons/left.jsp" />
		
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="javascript:void(0)">管理面板</a>
						</li>
						<li>
							<a href="javascript:void(0)">账户管理</a>
						</li>
						<li class="active">
						添加用户
						</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							账户管理
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								添加用户
							</small>
						</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">用户名称</label>
									<div class="col-sm-9">
										<input type="text" id="username" name="username" placeholder="请输入用户名称" class="col-xs-10 col-sm-5" value="${updateUserInfo.username}"/>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">用户密码</label>
									<div class="col-sm-9">
										<input type="password" id="password" name="password" placeholder="请输入用户密码" class="col-xs-10 col-sm-5" value="${updateUserInfo.password}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">用户手机</label>
									<div class="col-sm-9">
										<input type="text" id="phone" name="phone" placeholder="请输入用户手机" class="col-xs-10 col-sm-5 ui-autocomplete-input" autocomplete="off" value="${updateUserInfo.staffInfo.phone}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"></label>
									<div class="col-sm-9" style="color:red;" id="addUserTip"></div>
								</div>
								
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="addUser()">
											<i class="ace-icon fa fa-check bigger-110"></i>
											提交
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i>
											重置
										</button>
									</div>
								</div>
								<div class="hr hr-24"></div>
								<div class="space-24"></div>
							</form>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->

		<!-- 导入页脚start -->
		<jsp:include page="commons/footer.jsp" />
		<!-- 导入页脚end -->
		
		<!-- 回到页面顶部start -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
		<!-- 回到页面顶部end -->
	</div><!-- /.main-container -->
	
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-2.1.4.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-1.11.3.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/js/jQuery.md5.js"></script>
	<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/static/ace/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap.min.js"></script>
	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.ui.touch-punch.min.js"></script>
	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace.min.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		//激活左侧导航栏样式
		$("#account").addClass("active open");
		$("#account-account").addClass("active");
		jQuery(function($) {
			/**
				autocomplete函数
				jQuery UI Autocomplete常用的参数有：
				Source：用于指定数据来源，类型为String、Array、Function
				String：用于ajax请求的服务器端地址，返回Array/JSON格式，默认传递的参数名称为term
				Array：即字符串数组 或 JSON数组
				Function(request, response)：通过request.term获得输入的值，response([Array])来呈现数据；(JSONP是这种方式)
				minLength：当输入框内字符串长度达到minLength时，激活Autocomplete
				autoFocus：当Autocomplete选择菜单弹出时，自动选中第一个
				delay：即延迟多少毫秒激活Autocomplete
			*/
			$("#phone").autocomplete({
				source: "../admin/getStaffPhone",
			    minLength: 1,
			    delay: 300,
			    autoFocus: true
			});
		});
		//添加用户提交
		function addUser () {
			var username = $.trim($("#username").val());//用户名称
			var password = $.trim($("#password").val());//用户密码
			var phone = $.trim($("#phone").val());//用户手机号
			if (username == "") {
				$("#addUserTip").html("请输入用户名称");
				return false;
			} else if (password == "") {
				$("#addUserTip").html("请输入用户密码");
				return false;
			} else if (phone == "") {
				$("#addUserTip").html("请输入用户手机");
				return false;
			} else {
				$.ajax({
					type: "POST",
					url: "../admin/addUser",
					dataType: "text",
					async: true,
					data: {"username": username, "password": $.md5(password), "phone": phone},
					success: function(message) {
						var obj = jQuery.parseJSON(message);
						if (obj.errorCode == "0") {
							window.location.href = "../admin/users";
						} else {
							$("#addUserResult").html("Error：" + obj.errorMessage);
						}
					},
				    error:function() {
				    	$("#addUserResult").html("Error：网络错误");
					}
				});
			}
		}
	</script>
</body>
</html>
