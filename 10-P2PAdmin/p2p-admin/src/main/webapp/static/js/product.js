//添加产品提交
function addProduct () {
	var productName = $.trim($("#productName").val());//产品名称
	var productNo = $.trim($("#productNo").val());//产品编号
	var rate = $.trim($("#rate").val());//产品利率
	var cycle = $.trim($("#cycle").val());//产品期限
	var productType = $.trim($("#productType").val());//产品类型
	var productMoney = $.trim($("#productMoney").val());//产品金额
	var bidMinLimit = $.trim($("#bidMinLimit").val());//产品起投金额
	var bidMaxLimit = $.trim($("#bidMaxLimit").val());//产品最大投资金额
	var productDesc = $.trim($("#productDesc").val());//产品描述
	$.ajax({
		type: "POST",
		url: "../admin/addProduct",
		dataType: "text",
		data: "productName="+productName+"&productNo="+productNo+"&rate="+rate+"&cycle="+cycle+"&productType="+productType+"&productMoney="+productMoney+"&bidMinLimit="+bidMinLimit+"&bidMaxLimit="+bidMaxLimit+"&productDesc="+productDesc,
		success: function(message) {
			var obj = jQuery.parseJSON(message);
			if (obj.errorCode == "0") {
				window.location.href = "../admin/product?productType="+productType;
			} else {
				$("#addProductResult").html("Error：" + obj.errorMessage);
			}
		},
	    error:function() {
	    	$("#addProductResult").html("Error：网络错误");
		}
	});
}