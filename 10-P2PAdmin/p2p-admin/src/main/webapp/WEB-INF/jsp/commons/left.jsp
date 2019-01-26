<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" class="sidebar responsive ace-save-state">
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>
			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>
			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>
			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>
		</div>
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>
			<span class="btn btn-info"></span>
			<span class="btn btn-warning"></span>
			<span class="btn btn-danger"></span>
		</div>
	</div><!-- /.sidebar-shortcuts -->
	<!-- nav nav-list -->
	<ul class="nav nav-list">
		
		<!-- 管理面板 start -->
		<li id="main">
			<a href="${pageContext.request.contextPath}/admin/profile">
				<i class="menu-icon fa fa-tachometer"></i>
				<span class="menu-text">管理面板 </span>
			</a>
			<b class="arrow"></b>
		</li>
		<!-- 管理面板 end -->
		
		<!-- 导航菜单 start -->
		<c:set var="sq" value="${0}"/>
		
		<c:forEach items="${userInfo.menuPermissionInfo}" var="menuPermissionInfo" varStatus="index">
			
		<c:if test="${menuPermissionInfo.parentid eq 1}">
		
			<c:if test="${sq gt 0}">
				</ul>
			</li>
			<!-- 一级菜单 结束 -->
			</c:if>
			
			<c:set var="sq" value="${sq+1}"/>
			
		<!-- 一级菜单 start -->
		<li id="${menuPermissionInfo.code}">
				<a href="#" class="dropdown-toggle">
					<i class="menu-icon fa 
					<c:if test="${sq eq 1}">fa-desktop</c:if>
					<c:if test="${sq eq 2}">fa-list</c:if>
					<c:if test="${sq eq 3}">fa-pencil-square-o</c:if>
					<c:if test="${sq eq 4}">fa-list-alt</c:if>
					<c:if test="${sq eq 5}">fa-tag</c:if>
					"></i>
					<span class="menu-text">${menuPermissionInfo.name}</span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				
				<ul class="submenu">
		</c:if>

				<c:if test="${menuPermissionInfo.parentid ne 1}">
				<!-- 二级菜单 start -->
				<li id="${menuPermissionInfo.code}">
					<a href="${pageContext.request.contextPath}${menuPermissionInfo.url}">
						<i class="menu-icon fa fa-caret-right"></i>
						${menuPermissionInfo.name}
					</a>
					<b class="arrow"></b>
				</li>
				<!-- 二级菜单 end -->
				</c:if>
				
		</c:forEach>
		<!-- 导航菜单 end -->
				</ul>
		</li>
	<!-- 一级菜单 结束 -->
		
	</ul>
	<!-- /nav nav-list -->
	
	<!-- 左侧导航栏折叠 start -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
	<!-- 左侧导航栏折叠 start -->
</div>