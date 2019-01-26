<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>合同管理 - P2P后台管理系统</title>
	<meta name="description" content="Static &amp; Dynamic Tables" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<jsp:include page="commons/header.jsp" />
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
							<a href="${pageContext.request.contextPath}/admin/main">管理面板</a>
						</li>
						<li>
							<a href="javascript:void(0)">合同管理</a>
						</li>
						<li class="active">合同管理</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							合同管理
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								合同管理
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
										
										</div>
										</div>
										<table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="dynamic-table_info">
											<thead>
												<tr role="row">
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">序号</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending">进件来源</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending">申请借款金额</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending">借款人真实姓名</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending">借款人年龄</th>
												<th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">借款合同</th>
												<th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作管理</th>
												</tr>
											</thead>

											<tbody>
											
											<!-- 合同列表数据start -->
											<c:forEach items="${creditorRightsList}" var="creditorRights" varStatus="index">
											<c:if test="${index.count%2 == '0'}">
										  	<tr role="row" class="odd">
										  	</c:if>
										  	<c:if test="${obj.count%2 != '0'}">
											<tr role="row" class="even">
											</c:if>
													<td>
														<span class="lbl">${index.count}</span>
													</td>

													<td>
														<a href="#">${creditorRights.intoSource}</a>
													</td>
													<td>
													<span class="ace-thumbnails clearfix">
															<a href="${creditorRights.applyMoney}" data-rel="colorbox">${creditorRights.applyMoney}</a>
													</span>
													</td>
													<td class="hidden-480">${creditorRights.borrowerRealname}</td>
													<td>${creditorRights.borrowerAge}</td>

													<td class="hidden-480">
														<c:choose>
															<c:when test="${empty creditorRights.loanContractPath}">----</c:when>
															<c:otherwise><a href="http://192.168.230.128/${creditorRights.loanContractPath}" target="_blank">下载</a></c:otherwise>
														</c:choose>
													</td>
													<td>
														<div class="hidden-sm hidden-xs action-buttons">
															<a class="red" href="">
																<c:choose>
																	<c:when test="${empty creditorRights.loanContractPath}"><a href="javascript:generateContract(${creditorRights.id})">生成</a></c:when>
																	<c:otherwise><a href="javascript:generateContract(${creditorRights.id})">重新生成</a></c:otherwise>
																</c:choose>
															</a>
														</div>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<div class="row">
										<div class="col-xs-6" style="width:40%;">
										<div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">显示 ${startRow+1} 到  ${startRow+pageSize} 共${totalPage}页 ${totalRows} 条</div>
										</div><div class="col-xs-6" style="width:60%;">
										<div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
										
										<!-- 分页start -->
										<ul class="pagination">

											<c:choose>
												<c:when test="${currentPage gt 1}">
													<li class="paginate_button previous" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
														<a href="javascript:toPage(${currentPage-1})">上一页</a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="paginate_button previous disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
														<a href="javascript:void(0)">上一页</a>
													</li>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${currentPage lt 10}">
													<c:forEach var="i" begin="1" end="${totalPage le 10 ? totalPage : 10}">
														<li class="paginate_button <c:if test='${currentPage eq i}'>active</c:if>" aria-controls="dynamic-table" tabindex="0">
															<a href="javascript:toPage(${i})">${i}</a>
														</li>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="i" begin="${(totalPage-currentPage)>5 ? (currentPage-4) : totalPage-9}"
															   end="${(totalPage-currentPage)>5 ? (currentPage+5) : totalPage}">
														<li class="paginate_button <c:if test='${currentPage eq i}'>active</c:if>" aria-controls="dynamic-table" tabindex="0">
															<a href="javascript:toPage(${i})">${i}</a>
														</li>
													</c:forEach>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${currentPage lt totalPage}">
													<li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
														<a href="javascript:toPage(${currentPage+1})">下一页</a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="paginate_button next disabled" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
														<a href="javascript:void(0)">下一页</a>
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

		<jsp:include page="commons/footer.jsp" />

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
	
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-2.1.4.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-1.11.3.min.js"></script>
	<![endif]-->
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
	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.colorbox.min.js"></script>
	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layer/layer.js"></script>
	<script type="text/javascript">
		//左侧菜单激活
        $("#contract").addClass("active open");
        $("#contract-contract").addClass("active");
        //切换每页显示的数据条数
		function changeDisplayNumber() {
			var checkValue = $("#pageSize").val();//获取Select选择的Value
			window.location.href="${pageContext.request.contextPath}/admin/contract?pageSize="+checkValue;
		}
        //分页跳转
        function toPage(currentPage) {
            window.location.href="${pageContext.request.contextPath}/admin/contract?currentPage="+currentPage;
        }
        //生成合同
        function generateContract(creditorRightsId) {
            layer.confirm('您确定要重新生成吗?', function(index){
                window.location.href="${pageContext.request.contextPath}/admin/generateContract?creditorRightsId="+creditorRightsId+"&currentPage=${currentPage}&pageSize=${pageSize}";
                layer.close(index);
            });
		}
	</script>
</body>
</html>