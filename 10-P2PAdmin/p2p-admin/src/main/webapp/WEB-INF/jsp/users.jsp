<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>用户管理 - P2P后台管理系统</title>
	<meta name="description" content="产品管理，P2P后台管理系统" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
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
							<a href="javascript:void(0)">管理面板</a>
						</li>
						<li>
							<a href="javascript:void(0)">账户管理</a>
						</li>
						<li class="active">
							用户管理
						</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">

					<div class="page-header">
						<h1>
							账户管理
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								用户管理
							</small>
						</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="row">
								<div class="col-xs-12">
									<!-- div.table-responsive -->
									<!-- div.dataTables_borderWrap -->
									<div>
										<div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
										<div class="row">
										<div class="col-xs-6">
										<div class="dataTables_length" id="dynamic-table_length">
										<label>
										显示
										<select id="pageSize" name="dynamic-table_length" aria-controls="dynamic-table" class="form-control input-sm" onchange="changeDisplayNumber()">
											<option value="10" <c:if test="${pageSize eq 10}">selected</c:if>>10</option>
											<option value="15" <c:if test="${pageSize eq 15}">selected</c:if>>15</option>
											<option value="20" <c:if test="${pageSize eq 20}">selected</c:if>>20</option>
											<option value="25" <c:if test="${pageSize eq 25}">selected</c:if>>25</option>
											<option value="30" <c:if test="${pageSize eq 30}">selected</c:if>>30</option>
											<option value="40" <c:if test="${pageSize eq 40}">selected</c:if>>40</option>
											<option value="50" <c:if test="${pageSize eq 50}">selected</c:if>>50</option>
											<option value="100" <c:if test="${pageSize eq 100}">selected</c:if>>100</option>
										</select> 
										条记录
										</label>
										</div>
										</div>
										<div class="col-xs-6">
										<div id="dynamic-table_filter" class="dataTables_filter">
										<label>
										<a class="blue" href="${pageContext.request.contextPath}/admin/toAddUser">
										<i class="ace-icon fa fa-search-plus bigger-130"></i>添加用户
										</a>
										</label>
										</div>
										</div>
										</div>
										<table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
											<thead>
												<tr role="row">
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">序号</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">用户名</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending">姓名</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">手机</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending">邮箱</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">性别</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">职位</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">员工编号</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">入职时间</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">所在部门</th>
												<th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
												</tr>
											</thead>

											<tbody>
											
											<!-- 用户列表数据start -->
											<c:forEach items="${userInfoList}" var="userInfo" varStatus="index">
											<c:if test="${index.count%2 == '0'}">
										  	<tr role="row" class="odd">
										  	</c:if>
										  	<c:if test="${index.count%2 != '0'}">
											<tr role="row" class="even">
											</c:if>
													<td class="center">
															<span class="lbl">${index.count}</span>
													</td>

													<td>
														<a href="#">${userInfo.username}</a>
													</td>
													<td>${userInfo.staffInfo.realname}</td>
													<td class="hidden-480">${userInfo.staffInfo.phone}</td>
													<td>${userInfo.staffInfo.email}</td>

													<td class="hidden-480">
															<c:choose>
																<c:when test="${userInfo.staffInfo.sex eq 0}">女</c:when>
																<c:otherwise>男</c:otherwise>
															</c:choose>
													</td>
													
													<td class="hidden-480">
															${userInfo.staffInfo.jobtitle}
													</td>
													
													<td class="hidden-480">
															${userInfo.staffInfo.staffno}
													</td>
													
													<td class="hidden-480">
															<fmt:formatDate value="${userInfo.staffInfo.hiredate}" pattern="yyyy-MM-dd"/>
													</td>
													
													<td class="hidden-480">
															${userInfo.staffInfo.pOrgInfo.pOrgName} . ${userInfo.staffInfo.orgInfo.orgName}
													</td>
													
													<td>
														<div class="hidden-sm hidden-xs action-buttons">
															<a class="green" title="修改" href="${pageContext.request.contextPath}/admin/toEditUser?id=${userInfo.id}">
																<i class="ace-icon fa fa-pencil bigger-130"></i>
															</a>
															<a class="red" id="bootbox-confirm" title="删除" href="javascript:deleteUser(${userInfo.id})" >
																<i class="ace-icon fa fa-trash-o bigger-130"></i>
															</a>
														</div>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<div class="row">
										<div class="col-xs-6">
										<div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">显示 ${startRow+1} 到  ${startRow+pageSize} 共${totalPage}页 ${totalRows} 条</div>
										</div><div class="col-xs-6">
										<div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
										<!-- 分页start -->
										<ul class="pagination">
										<c:choose>
											<c:when test="${currentPage eq 1}">
											<li class="paginate_button previous disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
												<a href="javascript:void(0)">上一页</a>
											</li>
											</c:when>
											<c:otherwise>
											<li class="paginate_button previous" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
												<a href="${pageContext.request.contextPath}/admin/users?currentPage=${currentPage-1}">上一页</a>
											</li>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${totalPage le 10}">
												<c:forEach var="i" begin="1" end="${totalPage}" step="1">   
												<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
												<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
												</li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:if test="${currentPage lt 10}">
													<c:forEach var="i" begin="1" end="10" step="1">   
													<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
													<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
													</li>
													</c:forEach>
												</c:if>
												<c:if test="${currentPage ge 10}">
													<c:if test="${(totalPage-currentPage) gt 5}">
														<c:forEach var="i" begin="${currentPage-4}" end="${currentPage-1}" step="1">   
														<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
														<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
														</li>
														</c:forEach>
														<c:forEach var="i" begin="${currentPage}" end="${currentPage+5}" step="1">   
														<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
														<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
														</li>
														</c:forEach>
													</c:if>
													<c:if test="${(totalPage-currentPage) le 5}">
														<c:forEach var="i" begin="${currentPage-(10-(totalPage-currentPage)-1)}" end="${currentPage-1}" step="1">   
														<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
														<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
														</li>
														</c:forEach>
														<c:forEach var="i" begin="${currentPage}" end="${totalPage}" step="1">   
														<li class="paginate_button <c:if test="${currentPage eq i}">active</c:if>" aria-controls="dynamic-table" tabindex="0">
														<a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
														</li>
														</c:forEach>
													</c:if>
												</c:if>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${currentPage eq totalPage}">
											<li class="paginate_button disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next">
												<a href="javascript:void(0)">下一页</a>
											</li>
											</c:when>
											<c:otherwise>
											<li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next">
												<a href="${pageContext.request.contextPath}/admin/users?currentPage=${currentPage+1}">下一页</a>
											</li>
											</c:otherwise>
										</c:choose>
										</ul>
										<!-- 分页end -->
										</div>
										</div>
										</div>
										</div>
									</div>
								</div>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div>
				</div><!-- /.page-content -->
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
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/dataTables.buttons.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/buttons.flash.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/buttons.html5.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/buttons.print.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/buttons.colVis.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/dataTables.select.min.js"></script>
	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace.min.js"></script>
	<script type="text/javascript">
		//让当前页面激活左侧导航菜单
		$("#account").addClass("active open");
		$("#account-account").addClass("active");
		//改变分页中每页显示条数
		function changeDisplayNumber() {
			var checkValue = $("#pageSize").val();//获取Select选择的Value
			window.location.href="${pageContext.request.contextPath}/admin/users?pageSize="+checkValue;
		}
		//删除用户账户
		function deleteUser (id) {
			if (confirm('确定将此记录删除?')) {
				window.location.href="${pageContext.request.contextPath}/admin/deleteUser?id="+id;
			}
		}
	</script>
</body>
</html>
