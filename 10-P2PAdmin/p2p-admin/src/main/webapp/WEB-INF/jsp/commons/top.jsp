<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!-- 头部导航条start -->
<div id="navbar" class="navbar navbar-default ace-save-state">
	<div class="navbar-container ace-save-state" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="${pageContext.request.contextPath}/" class="navbar-brand">
				<small>
					<i class="fa fa-leaf"></i>
					P2P后台管理系统
				</small>
			</a>
		</div>
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue dropdown-modal">
					<a data-toggle="dropdown" href="javascript:void(0)" class="dropdown-toggle">
						<img class="nav-user-photo" src="${pageContext.request.contextPath}/static/ace/images/avatars/user.jpg" alt="admin" />
						<span class="user-info">
							<small>欢迎使用</small>${userInfo.username}
						</span>
						<i class="ace-icon fa fa-caret-down"></i>
					</a>
					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="${pageContext.request.contextPath}/admin/profile">
								<i class="ace-icon fa fa-cog"></i>
								设置
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/admin/profile">
								<i class="ace-icon fa fa-user"></i>
								信息
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="${pageContext.request.contextPath}/admin/logout">
								<i class="ace-icon fa fa-power-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div><!-- /.navbar-container -->
</div>
<!-- 头部导航条start -->