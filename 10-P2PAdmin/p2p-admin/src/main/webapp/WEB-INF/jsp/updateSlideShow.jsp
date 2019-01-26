<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>欢迎使用 - P2P后台管理系统</title>
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
							<a href="javascript:void(0)">轮播图管理</a>
						</li>
						<li class="active">轮播图管理</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							轮播图管理
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								轮播图管理
							</small>
						</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form" id="uploadForm" action="${pageContext.request.contextPath}/admin/uploadSlideShow" method="post" enctype="multipart/form-data" target="frameFile">
								<div class="step-pane active" data-step="1">
										<div class="form-group">
											<label for="inputWarning" class="col-xs-12 col-sm-3 control-label no-padding-right">轮播图名称</label>
											<div class="col-xs-12 col-sm-5">
												<span class="block input-icon input-icon-right">
													<input type="text" id="slideTitle" name="slideTitle" class="width-100" placeholder="请输入轮播图名称" value="${slideShow.slideTitle}">
													<i class="ace-icon fa"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
								
										<div class="form-group"><!-- 有错误追加has-error样式 -->
											<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">轮播图链接</label>
											<div class="col-xs-12 col-sm-5">
												<span class="block input-icon input-icon-right">
													<input type="text" id="slideUrl" name="slideUrl" class="width-100" placeholder="请输入轮播图链接" value="${slideShow.slideUrl}">
													<i class="ace-icon fa"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">  </div>
										</div>
										<div class="form-group"><!-- 成功追加has-success样式 -->
											<label for="inputSuccess" class="col-xs-12 col-sm-3 control-label no-padding-right">轮播图生效时间</label>
											<div class="col-xs-12 col-sm-5">
												<span class="block input-icon input-icon-right">
													<input type="text" id="slideStartTime" name="slideStartTime" class="form-control" placeholder="请输入轮播图生效时间" value="<fmt:formatDate value="${slideShow.slideStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">  </div>
										</div>
										<div class="form-group"><!-- 有提示追加has-info样式 -->
											<label for="inputInfo" class="col-xs-12 col-sm-3 control-label no-padding-right">轮播图失效时间</label>
											<div class="col-xs-12 col-sm-5">
												<span class="block input-icon input-icon-right">
													<input type="text" id="slideEndTime" name="slideEndTime" class="width-100" placeholder="请输入轮播图失效时间" value="<fmt:formatDate value="${slideShow.slideEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
													<i class="ace-icon fa fa-calendar"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">  </div>
										</div>
										<div class="form-group">
											<label for="inputError2" class="col-xs-12 col-sm-3 control-label no-padding-right">轮播图状态</label>
											<div class="col-xs-12 col-sm-5">
												<span class="input-icon block input-icon-right">
													<select class="width-100" id="slideStatus" name="slideStatus">
														<option value="">请选择</option>
														<option value="1" <c:if test="${slideShow.slideStatus eq 1}">selected</c:if>>启用</option>
														<option value="2" <c:if test="${slideShow.slideStatus eq 2}">selected</c:if>>禁用</option>
													</select>
													<i class="ace-icon fa red"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">  </div>
										</div>
										
										<div class="form-group">
											<label for="inputError2" class="col-xs-12 col-sm-3 control-label no-padding-right">上传图片</label>
											<div class="col-xs-12 col-sm-5">
												<span class="input-icon block input-icon-right">
													<input type="file" id="fileName" name="fileName" class="width-100">
													<i class="ace-icon fa red"></i>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group"><!-- 有提示追加has-info样式 -->
											<label for="inputInfo" class="col-xs-12 col-sm-3 control-label no-padding-right"></label>
											<div class="col-xs-12 col-sm-5">
												<span class="block input-icon input-icon-right" style="color:blue;" id="uploadLog"></span>
												<input type="hidden" id="fileURL" name="fileURL" value=""/>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
								</div>
								
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="shubmitSlideShow()">
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

		<jsp:include page="commons/footer.jsp" />

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
	
	<!-- 图片上传 -->
	<iframe id='frameFile' name='frameFile' style='display: none;'></iframe>
	
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-2.1.4.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-1.11.3.min.js"></script>
	<![endif]-->
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqueryui/css/jquery-ui-timepicker-addon.min.css" />
	<script src="${pageContext.request.contextPath}/static/jqueryui/js/jquery-ui-timepicker-addon.min.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	jQuery(function($) {
		//激活左侧导航栏样式
		$("#slideShow").addClass("active open");
		$("#slideShow_sub").addClass("active");
		
		$("#slideStartTime").datetimepicker({
			lang:"ch",
	        dateFormat: "yy-mm-dd",
			changeMonth: true,       
			changeYear: true,        
			timeFormat: 'hh:mm:ss',        
			stepHour: 1,        
			stepMinute: 5,        
			stepSecond: 10,        
			dayNamesMin: ["七","一", "二", "三", "四", "五", "六"],        
			monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"]    
		});
		$("#slideEndTime").datetimepicker({
			dateFormat: "yy-mm-dd",
			changeMonth: true,       
			changeYear: true,        
			timeFormat: 'hh:mm:ss',        
			stepHour: 1,        
			stepMinute: 5,        
			stepSecond: 10,        
			dayNamesMin: ["七","一", "二", "三", "四", "五", "六"],        
			monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"]    
		});
	});
	//选择图片上传，自动触发
	$(function() {
           $('#fileName').change(function() {
               $('#uploadLog').html("开始上传中....");
               $('#uploadForm').submit();
           });
       })
       //上传成功后调用该方法
	function uploadSuccess(msg) {
		if (msg == 'error') {
			$("#uploadLog").html("文件上传失败");
		} else {
			$("#fileURL").val(msg)
			$("#uploadLog").html("文件上传成功");
		}
   	}
	//更新轮播图
	function shubmitSlideShow () {
		var slideTitle = $("#slideTitle").val();
		var slideUrl = $("#slideUrl").val();
		var slideStartTime = $("#slideStartTime").val();
		var slideEndTime = $("#slideEndTime").val();
		var slideStatus = $("#slideStatus").val();
		var fileURL = $("#fileURL").val();
		if (slideTitle == "") {
			alert("请输入轮播图名称");
			return;
		} else if (slideUrl == "") {
			alert("请输入轮播图链接");
			return;
		}
		$.ajax({
			type : "POST",
			url : "../admin/submitSlideShow",
			dataType : "text",
			async : true,
			data : {
				"slideTitle" : slideTitle,
				"slideUrl" : slideUrl,
				"slideStartTime" : slideStartTime,
				"slideEndTime" : slideEndTime,
				"slideStatus" : slideStatus,
				"fileURL" : fileURL
			}, 
			success : function (message) {
				var jsonObject = jQuery.parseJSON(message);
				$("#uploadLog").html(jsonObject.errorMessage + "，3秒后将为您跳转...");
				times();
			},
			error : function () {
				$("#uploadLog").html("网络错误");
			}
		});
	}
	function times() {
		setTimeout('relink()', 3000);//3秒后执行
	}
	function relink() {
	   	window.location.href="../admin/slideShow";
	   	return;
	}
	</script>
</body>
</html>