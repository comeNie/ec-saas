/**
 * @FileName: system-ajax.js
 * @Description: 二次开发ajax组件
 * @author 张超
 * 
 */
var jqsuccessUrl = "";
var jqsuccessData;
var btn_more_click = true;
$.extend({

	systemAjax : function(ajaxOption) {
		var defaultOption = {
			isLogin : true,// 是否需要验证登录ajax
			data : {},// post请求参数数据
			type : "post",// 请求类型
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			cache : false,// 是否需要缓存
			async : true,// ajax请求是否异步，默认为异步请求
			success : null,
			moreClick : false,// 绑定ajax事件，防止重复提交
			loadingId : "",
			loadingMessage : "加载中......",

			isAlert : true,
			alertImgSize : 48,// 等待图标大小
			alertMessage : "加载中......",
			successUrl : "",
			confirmUrl : "",
			isSuccess : false,
			needSuccessMethod : false,
			renderRoles : null
		};
		var option_ = $.extend(defaultOption, ajaxOption);
		if (btn_more_click) {
			if (option_.moreClick) {
				btn_more_click = false;
			}
			if (!isNull(option_.loadingId)) {
				$("#" + option_.loadingId)
						.html("<div class=\"lodingImg\">&nbsp;</div>");
			}

			var systemUrl = "";
			if (option_.isLogin && !isNull(option_.url)) {
				systemUrl = local_url + option_.url;
			} else {
				systemUrl = local_url + option_.url;
			}

			if (!isNull(option_.isAlert) && isNull(option_.loadingId)) {
				$.systemLoading(option_.alertMessage);

				window.setTimeout(function() {
							$.unblockUI();
							btn_more_click = true;
						}, 200000);

			}
			if (!isNull(option_.successUrl)) {
				jqsuccessUrl = local_url + option_.successUrl;
			}

			$.ajax({
						url : systemUrl,
						type : option_.type,
						async : option_.async,
						cache : option_.cache,
						data : option_.data,
						dataType : option_.dataType,
						contentType : option_.contentType,
						timeout : 200000,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							if (textStatus == "timeout"
									&& !isNull(option_.isAlert)
									&& isNull(option_.loadingId)) {
								alert("请求超时！");
								btn_more_click = true;
								$.unblockUI();
							}

						},
						success : function(data) {
							if (option_.dataType == "html") {
								if (data.indexOf("resultType") != -1
										&& (data.indexOf("exception") != -1 || data
												.indexOf("validate") != -1)) {
									data = $.strToJson(data);
								}
							}

							var message = "";
							message = ajaxUtilMessage[data.resultCode];
							if (isNull(message) && !isNull(data.message)) {
								message = ajaxUtilMessage[data.message];
								if (isNull(message)) {
									message = data.message;
								}
							}

							if (!isNull(message)) {
								var paramObj = data.paramMap;
								if (!isNull(paramObj)) {
									for (var k in paramObj) {
										message = message.replace(
												"{" + k + "}", paramObj[k]);
									}
								}

								var jsRender = option_.renderRoles;
								if (!isNull(jsRender)) {
									for (var k in jsRender) {
										message = message.replace(
												"{" + k + "}", jsRender[k]);
									}
								}

								data.message = message;
							}

							if (!isNull(option_.isAlert)
									&& isNull(option_.loadingId)) {
								$.unblockUI();
							}

							if (!isNull(option_.loadingId)) {
								$("#" + option_.loadingId).empty();
							}
							btn_more_click = true;
							function sysAlert(message) {
								vcShowMessage({
											severity : 'info',
											summary : '提示',
											detail : message
										});
							}

							function errorAlert(message) {
								vcShowMessage({
											severity : 'error',
											summary : '异常',
											detail : message
										});
							}

							var loginUse = data;

							if (data.resultType == "AuthErrorCode") {
								$.systemAlert("请先登录。", function() {
											window.location.href = data.message;
										});
							}

							if (data.resultType == "exception"
									|| data.resultType == "validate") {
								errorAlert(data.message);
								if (!option_.needSuccessMethod) {
									return;
								}
							}

							if (option_.dataType == "html") {
								if (data.indexOf("resultType") != -1
										&& (data.indexOf("exception") != -1 || data
												.indexOf("validate") != -1)) {
									var newdata = $.strToJson(data);
									errorAlert(newdata.message);
									if (!isNull(newdata.resultType)) {
										if (!option_.needSuccessMethod) {
											return;
										}
									}
								}

								if (data.indexOf("\"AuthErrorCode\":") != -1) {
									var newdata = $.strToJson(data);
									loginUse = newdata;
								}

							}

							if (option_.isAlert) {
								if (option_.isSuccess) {
									sysAlert(data.message);
									if (!isNull(jqsuccessUrl)) {
										if (jqsuccessData.result == "success") {
											window.location.href = jqsuccessUrl;
											return;
										}
									}
								}
							}
							if ($.isFunction(option_.success)) {
								option_.success(data);
							}

						}
					});
		}
	},
	systemAlert : function(data, closeFun) {
		var content = "";
		var title = "提示";

		if (isJson(data)) {
			content = data.message;
			title = data.title;
		} else {
			content = data;
		}

		var winWidth = document.body.clientWidth;

		var left_ = winWidth / 2 - 120;

		$.blockUI({
			message : "<div class=\"loadingContent\"><div class=\"lodingMessage_title\">"
					+ title
					+ "</div><div class=\"lodingMessage\">"
					+ content
					+ "</div><div class=\"loadingClickBtn\"><a class=\"btnClick\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确&nbsp;定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div></div>",
			css : {
				border : 'none',
				backgroundColor : 'inherit',
				width : "240px",
				height : "auto",
				left : left_,
				color : 'Red',
				"z-index" : "110000",
				cursor : "auto"
			}
		});

		var lmHeight = $(".lodingMessage").height();

		if (lmHeight > 30) {
			if (lmHeight > 45) {
				$(".lodingMessage").css("height", "64px");
				$(".loadingContent").css("height", "125px");
			} else {
				$(".lodingMessage").css("height", "52px");
			}
		} else {
			$(".lodingMessage").css("height", "23px");
			$(".loadingContent").css("height", "85px");
			$(".loadingContent").parent().css("height", "85px");
		}

		$(".btnClick").click(function() {
					if (!isNull(closeFun)) {
						closeFun();
					}
					$.unblockUI();
				});

	},
	systemPhoneConfirm : function(cameraFun, photoFun) {

		var winWidth = document.body.clientWidth;

		var left_ = winWidth / 2 - 130;

		$.blockUI({
			message : "<div class=\"phoneContent\"><div class=\"contentMessage\">"
					+ "<div class=\"cameraImg\"><img border=\"0\" src=\""
					+ ctx
					+ "/resource/img/xiangji.png\"></div><div class=\"photoImg\"><img border=\"0\" src=\""
					+ ctx
					+ "/resource/img/xiangce.png\"></div>"
					+ "</div><div class=\"imgClickBtn\"><a class=\"btnClick\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关&nbsp;闭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></div></div>",
			css : {
				border : 'none',
				backgroundColor : 'inherit',
				width : "260px",
				height : "140px",
				left : left_,
				color : 'Red',
				"z-index" : "110000",
				cursor : "auto"
			}
		});

		var lmHeight = $(".lodingMessage").height();

		if (lmHeight > 30) {
			$(".lodingMessage").css("height", "52px");
		} else {
			$(".lodingMessage").css("height", "23px");
			$(".loadingContent").css("height", "80px");
			$(".loadingContent").parent().css("height", "80px");
		}

		$(".imgClickBtn").click(function() {
					$.unblockUI();
				});

		$(".cameraImg").click(function() {
					$.unblockUI();
					if (!isNull(cameraFun)) {
						cameraFun();
					}
				});

		$(".photoImg").click(function() {
					$.unblockUI();
					if (!isNull(photoFun)) {
						photoFun();
					}
				});

	},
	systemConfirm : function(data, okFun, noFun) {
		var btn1 = "放弃";
		var btn2 = "确定";
		var content = "";
		if (isJson(data)) {
			btn1 = data.btn1;
			btn2 = data.btn2;
			content = data.content;
		} else {
			content = data;
		}

		var winWidth = document.body.clientWidth;
		var left_ = winWidth / 2 - 120;

		$.blockUI({
			message : "<div class=\"loadingContent\"><div class=\"lodingMessage_title\">提示</div><div class=\"lodingMessage\">"
					+ content
					+ "</div><div class=\"loadingBtn\"><a class=\"btnLeft\">"
					+ btn1
					+ "</a><a class=\"btnRight\">"
					+ btn2
					+ "</a></div></div>",
			css : {
				border : 'none',
				backgroundColor : 'inherit',
				width : "240px",
				height : "auto",
				left : left_,
				color : 'Red',
				"z-index" : "110000",
				cursor : "auto"
			}
		});

		var lmHeight = $(".lodingMessage").height();

		if (lmHeight > 30) {
			$(".lodingMessage").css("height", "52px");
		} else {
			$(".lodingMessage").css("height", "23px");
			$(".loadingContent").css("height", "85px");
		}

		$(".btnRight").click(function() {
					$.unblockUI();
					if (!isNull(okFun)) {
						okFun();
					}
				});

		$(".btnLeft").click(function() {
					$.unblockUI();
					if (!isNull(okFun)) {
						noFun();
					}
				});

	},
	systemShowMessage : function(message) {
		vcShowMessage([{
					severity : 'info',
					summary : '提示',
					detail : message
				}]);
	},
	systemErrorMessage : function(message) {
		vcShowMessage([{
					severity : 'error',
					summary : '提示',
					detail : message
				}]);
	},
	systemLoading : function(message, lfun) {
		$.blockUI({
					message : "<div class=\"lodingImg\">" + initLoadingHtmlObj
							+ "</div>",
					css : {
						border : 'none',
						backgroundColor : 'inherit',
						width : "0px",
						height : "0px",
						opacity : .5,
						left : "48%",
						color : 'Red',
						"z-index" : "110000",
						cursor : "auto"
					}
				});
		if (!isNull(lfun)) {
			lfun();
		}

	},
	systemClipboardAlert : function(data, closeFun) {
		var content = "";
		var title = "提示";

		if (isJson(data)) {
			content = data.message;
			title = data.title;
		} else {
			content = data;
		}

		var winWidth = document.body.clientWidth;

		$.blockUI({
			message : "<div class=\"loadingContent\"><div class=\"lodingMessage_title\">"
					+ "提示"
					+ "</div><div class=\"lodingMessage\">"
					+ "<div style=\"color:#333333;font-size:13px;\">长按下面文字，放开后选择【全选】菜单，再选择【拷贝】菜单，完成分享内容到剪贴板的拷贝。</div><br/>"
					+ "<div><input id=\"link_input\" value='"
					+ content
					+ "' style=\"width:95%;height:25px;font-size:14px;\"/></div><br/>"
					+ "</div><div class=\"loadingClickBtn\" style=\"height:25px;\"><a class=\"btnClick\">我知道了</a></div></div>",
			css : {
				border : 'none',
				backgroundColor : 'inherit',
				width : "90%",
				height : "auto",
				left : 0,
				color : 'Red',
				"z-index" : "110000",
				cursor : "auto"
			}
		});

		var left_ = winWidth / 2 - ($(".loadingContent").width() / 2);

		$(".loadingContent").parent().css("left", left_ + "px");
		$(".loadingContent").css("height", "auto");
		$(".loadingClickBtn").addClass("yuanjiao").css("border", "0px");
		$(".lodingMessage").css("border-bottom", "1px solid #cccccc");

		$("#link_input").focus();

		$(".btnClick").click(function() {
					if (!isNull(closeFun)) {
						closeFun();
					}
					$.unblockUI();
				});

	}
});

var isJson = function(obj) {
	var isjson = typeof(obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}

/*
 * function googleAnalytics_() { try { ga('create', 'UA-45888549-2', 'jd.com');
 * ga('send', 'pageview'); } catch (e) { } }
 */
var initLoadingHtmlObj = "";
$(function() {

			initLoadingHtmlObj = $("#initLoadingHtml").html();
			$("#initLoadingHtml").remove();

			vcShowMessage = function(msg) {
				$.systemAlert(msg.detail, function() {
						});
			}
		});
