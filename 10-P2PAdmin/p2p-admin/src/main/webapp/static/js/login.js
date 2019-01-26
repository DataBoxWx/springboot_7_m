if('ontouchstart' in document.documentElement) document.write("<script src='/p2p-admin/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");

jQuery(function($) {
	$(document).on('click', '.toolbar a[data-target]', function(e) {
		e.preventDefault();
		var target = $(this).data('target');
		$('.widget-box.visible').removeClass('visible');//hide others
		$(target).addClass('visible');//show target
	});
});
	
//you don't need this, just used for changing background
jQuery(function($) {
	$('#btn-login-dark').on('click', function(e) {
		$('body').attr('class', 'login-layout');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'blue');
		e.preventDefault();
	});
	$('#btn-login-light').on('click', function(e) {
		$('body').attr('class', 'login-layout light-login');
		$('#id-text2').attr('class', 'grey');
		$('#id-company-text').attr('class', 'blue');
		e.preventDefault();
	});
	$('#btn-login-blur').on('click', function(e) {
		$('body').attr('class', 'login-layout blur-login');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'light-blue');
		e.preventDefault();
	});
});

//用户登录,提交表单
function login() {
	//登录账号
	var userName = $.trim($("#userName").val());
	//登录密码
	var password = $.trim($("#password").val());
	//记住我
	var rememberMe = "";
	if ($("#rememberMe").is(":checked")) {
		rememberMe = "rememberMe";
	}
	//是否是记住我的账户去登录
	var isAutoLogin = $("#isAutoLogin").val();
	if (userName == "") {
		//在页面提示错误信息
		$("#loginTip").html("请输入登录账户");
		return false;
	} else if (password == "") {
		//在页面提示错误信息
		$("#loginTip").html("请输入登录密码");
		return false;
	} else {
		if (isAutoLogin != 1) {
			password = $.md5(password)
		}
		$.ajax({
		    type: "POST",
		    url: "admin/login",
		    dataType: "text",
		    async: true,
		    data: {"userName" : userName, "password" : password, "rememberMe" : rememberMe},
		    success: function(message) {
		    	//通过jquery解析后台返回的json字符串
			    var jsonObject = jQuery.parseJSON(message);
			    if ("0" == jsonObject.errorCode) {//登录成功
				    window.location.href = "admin/profile";
			    } else {
			    	//在页面提示错误信息
				    $("#showId").html(jsonObject.errorMessage);
	           	    return false;
			    }
		   	}
		});
	}
}