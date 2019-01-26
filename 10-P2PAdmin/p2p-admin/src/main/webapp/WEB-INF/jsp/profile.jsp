<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="description" content="欢迎使用 P2P后台管理系统" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>欢迎使用 - P2P后台管理系统</title>
	<jsp:include page="commons/header.jsp" />
</head>

<body class="no-skin">
	<!-- 导入头部导航条start -->
	<jsp:include page="commons/top.jsp" />
	<!-- 导入头部导航条start -->
	
	<!-- 页面主体内容start -->
	<div class="main-container ace-save-state" id="main-container">
		
		<!-- 导入左侧导航栏start -->
		<jsp:include page="commons/left.jsp" />
		<!-- 导入左侧导航栏end -->
		
		<!-- 页面右侧主体内容start -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${pageContext.request.contextPath}/">首页</a>
						</li>
						<li class="active">管理面板</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>用户信息</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<div class="hr dotted"></div>
							<div>
								<div id="user-profile-1" class="user-profile row">
									<div class="col-xs-12 col-sm-3 center">
										<div>
											<!-- 头像start -->
											<span class="profile-picture">
												<img id="avatar" class="editable img-responsive editable-click editable-empty" alt="Alex's Avatar" src="${pageContext.request.contextPath}/static/ace/images/avatars/profile-pic.jpg"></img>
											</span>
											<!-- 头像end -->

											<div class="space-4"></div>

											<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
												<div class="inline position-relative">
													<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
														<i class="ace-icon fa fa-circle light-green"></i>
														&nbsp;
														<span class="white">${userInfo.username}</span>
													</a>

												</div>
											</div>
										</div>


									<div class="space-6"></div>

									<div class="hr hr12 dotted"></div>

									<div class="clearfix">
										<div class="grid2">
											<span class="bigger-175 blue">25</span>

											<br/>
											Followers
										</div>

										<div class="grid2">
											<span class="bigger-175 blue">12</span>

											<br/>
											Following
										</div>
									</div>

									<div class="hr hr16 dotted"></div>
								</div>
									
									<!-- 用户信息start -->
									<div class="col-xs-12 col-sm-9">

										<div class="center">
												<span class="btn btn-app btn-sm btn-light no-hover">
													<span class="line-height-1 bigger-170 blue"> 1518 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Views </span>
												</span>

											<span class="btn btn-app btn-sm btn-yellow no-hover">
													<span class="line-height-1 bigger-170"> 32 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Followers </span>
												</span>

											<span class="btn btn-app btn-sm btn-pink no-hover">
													<span class="line-height-1 bigger-170"> 4 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Projects </span>
												</span>

											<span class="btn btn-app btn-sm btn-grey no-hover">
													<span class="line-height-1 bigger-170"> 23 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Reviews </span>
												</span>

											<span class="btn btn-app btn-sm btn-success no-hover">
													<span class="line-height-1 bigger-170"> 7 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Albums </span>
												</span>

											<span class="btn btn-app btn-sm btn-primary no-hover">
													<span class="line-height-1 bigger-170"> 55 </span>

													<br/>
													<span class="line-height-1 smaller-90"> Contacts </span>
												</span>
										</div>

											<div class="space-12"></div>
											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name">用户名 </div>
													<div class="profile-info-value">
														<span class="editable editable-click" id="username">${userInfo.username}</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">所属部门 </div>
													<div class="profile-info-value">
														<i class="fa fa-map-marker light-orange bigger-110"></i>
														<span class="editable editable-click" id="country">${staffInfo.pOrgInfo.pOrgName}</span>
														<span class="editable editable-click" id="city">${staffInfo.orgInfo.orgName}</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">性别 </div>
													<div class="profile-info-value">
														<span class="editable editable-click" id="age">
														<c:choose>
															<c:when test="${staffInfo.sex eq 0}">女</c:when>
															<c:otherwise>男</c:otherwise>
														</c:choose>
														</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">入职时间 </div>
													<div class="profile-info-value">
														<span class="editable editable-click" id="signup"><fmt:formatDate value="${staffInfo.hiredate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">最近登录 </div>
													<div class="profile-info-value">
														<span class="editable editable-click" id="login"><fmt:formatDate value="${userInfo.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name">职位 </div>
													<div class="profile-info-value">
														<span class="editable editable-click" id="about">${staffInfo.jobtitle}</span>
													</div>
												</div>
											</div>
										<div class="space-20"></div>
									</div>
									<!-- 用户信息end -->
								</div>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div>
			</div>
		</div><!-- /.main-content -->
		<!-- 页面右侧主体内容end -->
		
		<!-- 导入页脚start -->
		<jsp:include page="commons/footer.jsp" />
		<!-- 导入页脚end -->
		
		<!-- 回到页面顶部start -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
		<!-- 回到页面顶部end -->
	</div><!-- /.main-container -->
	<!-- 页面主体内容end -->
	
	<!-- basic scripts start -->
	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-2.1.4.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-1.11.3.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/static/ace/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-ui.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.easypiechart.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.sparkline.index.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.flot.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.flot.pie.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.flot.resize.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace.min.js"></script>
	<!-- basic scripts end -->
	<script type="text/javascript">
		$("#main").addClass("active");
	</script>
</body>
</html>
