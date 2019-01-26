<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title>新增产品 - P2P后台管理系统</title>
	<meta name="description" content="新增产品，P2P后台管理系统" />
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
							<a href="javascript:void(0)">管理面板</a>
						</li>
						<li>
							<a href="javascript:void(0)">产品管理</a>
						</li>
						<li class="active">
						<c:choose>
							<c:when test="${productType eq 0}">体验产品</c:when>
							<c:when test="${productType eq 1}">优选产品</c:when>
							<c:otherwise>散标产品</c:otherwise>
						</c:choose>
						</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							产品管理
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								<c:choose>
									<c:when test="${productType eq 0}">体验产品</c:when>
									<c:when test="${productType eq 1}">优选产品</c:when>
									<c:otherwise>散标产品</c:otherwise>
								</c:choose>
							</small>
						</h1>
					</div><!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1">产品名称</label>
									<div class="col-sm-9">
										<input type="text" id="productName" name="productName" placeholder="请输入产品名称" class="col-xs-10 col-sm-5" value="${loanInfo.productName}"/>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品编号</label>
									<div class="col-sm-9">
										<input type="text" id="productNo" name="productNo" placeholder="请输入产品编号" class="col-xs-10 col-sm-5" value="${loanInfo.productNo}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品利率</label>
									<div class="col-sm-9">
										<input type="text" id="rate" name="rate" placeholder="请输入产品利率" class="col-xs-10 col-sm-5" value="${loanInfo.rate}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品期限</label>
									<div class="col-sm-9">
									<c:choose>
										<c:when test="${not empty loanInfo}">
											<input type="text" id="cycle" name="cycle" placeholder="请输入产品期限" class="col-xs-10 col-sm-5" value="${loanInfo.cycle}"/>
										</c:when>
										<c:when test="${not empty creditorRights}">
											<input type="text" id="cycle" name="cycle" placeholder="请输入产品期限" class="col-xs-10 col-sm-5" value="${creditorRights.auditLoanTerm}" readOnly/>
										</c:when>
										<c:otherwise>
											<input type="text" id="cycle" name="cycle" placeholder="请输入产品期限" class="col-xs-10 col-sm-5"/>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品类型</label>
									<div class="col-sm-9">
										<select id="productType" name="productType" class="col-xs-10 col-sm-5">
											<option value="">请选择</option>
											<c:choose>
												<c:when test="${not empty loanInfo}">
													<c:forEach items="${dictionaryInfoList}" var="dictionaryInfo">
														<option value="${dictionaryInfo.typevalue}" <c:if test="${loanInfo.productType eq dictionaryInfo.typevalue}">selected</c:if>>${dictionaryInfo.typename}</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach items="${dictionaryInfoList}" var="dictionaryInfo">
														<option value="${dictionaryInfo.typevalue}" <c:if test="${productType eq dictionaryInfo.typevalue}">selected</c:if>>${dictionaryInfo.typename}</option>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品金额</label>
									<div class="col-sm-9">
										<c:choose>
										<c:when test="${not empty loanInfo}">
											<input type="text" id="productMoney" name="productMoney" placeholder="请输入产品金额" class="col-xs-10 col-sm-5" value="${loanInfo.productMoney}"/>
										</c:when>
										<c:when test="${not empty creditorRights}">
											<input type="text" id="productMoney" name="productMoney" placeholder="请输入产品金额" class="col-xs-10 col-sm-5" value="${creditorRights.auditLoanMoney}" readOnly/>
										</c:when>
										<c:otherwise>
											<input type="text" id="productMoney" name="productMoney" placeholder="请输入产品金额" class="col-xs-10 col-sm-5"/>
										</c:otherwise>
									</c:choose>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品起投金额</label>
									<div class="col-sm-9">
										<input type="text" id="bidMinLimit" name="bidMinLimit" placeholder="请输入起投金额" class="col-xs-10 col-sm-5" value="${loanInfo.bidMinLimit}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品投资限额</label>
									<div class="col-sm-9">
										<input type="text" id="bidMaxLimit" name="bidMaxLimit" placeholder="请输入产品投资限额" class="col-xs-10 col-sm-5" value="${loanInfo.bidMaxLimit}"/>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1">产品描述</label>
									<div class="col-sm-9">
										<textarea id="productDesc" name="productDesc" placeholder="请输入产品描述" class="col-xs-10 col-sm-5" style="height:82px;">${loanInfo.productDesc}</textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"></label>
									<div class="col-sm-9" style="color:red;" id="addProductResult"></div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button" onclick="addProduct()">
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
	<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='ace/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
	</script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap.min.js"></script>
	<!-- page specific plugin scripts -->
	<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/static/ace/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery-ui.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/chosen.jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/spinbox.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap-datepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap-timepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/daterangepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap-colorpicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.knob.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/autosize.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.inputlimiter.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/jquery.maskedinput.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/bootstrap-tag.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/ace/js/ace.min.js"></script>
	<!-- inline scripts related to this page -->
	
	<script type="text/javascript">
		//激活左侧导航栏样式
		var productType = "${productType}"
		if ("0" == productType) {
			$("#product").addClass("active open");
			$("#product-tiyan").addClass("active");
		} else if ("1" == productType) {
			$("#product").addClass("active open");
			$("#product-youxuan").addClass("active");
		} else if ("2" == productType) {
			$("#product").addClass("active open");
			$("#product-sanbiao").addClass("active");
		}
	</script>
	<script src="${pageContext.request.contextPath}/js/product.js"></script>
</body>
</html>
