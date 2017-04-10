$(window).keydown(function(event) {
			if (event.keyCode == 116) {
				event.keyCode = 0;
				event.cancelBubble = true;
				return false;
			}
		});

$.fn.extend({
			"buttonSlider" : function() {
				this.each(function() {
							var _o = new buttonSlider(this);
							_o.init();
						});
			},
			/**
			 * table添加边框
			 */
			tableStyle : function(options) {
				var td_tr_css = {
					"border" : "#c7cccf solid",
					"border-width" : "0px 1px 1px 0px"
				};

				var td_css = {
					"padding" : "5px"
				};

				var table_css = {
					"border" : "#c7cccf solid",
					"border-width" : "1px 0px 0px 1px"
				};

				function addCss(obj) {
					$(obj).css(table_css);
					$(obj).find("td").css(td_tr_css);
					$(obj).find("td").css(td_css);
					$(obj).find("tr").css(td_tr_css);
				}
				addCss(this);
			},
			mobileVal : function(cval) {
				if (isNull(cval)) {
					var nowval = $(this).val();
					nowval = nowval.replace($(this).attr("bgtext"), "");
					nowval = nowval.replace("：", "");
					return nowval;
				} else {
					$(this).val($(this).attr("bgtext") + "：" + cval);
				}
			},
			vcval : function() {
				var val = "";
				var title = $(this).attr("title");
				val = $(this).val();
				if (!isNull(title)) {
					val = val.replace(title, "");
				}
				return $.trim(val);
			},
			serializeJson : function() {
				var serializeObj = {};
				var array = this.serializeArray();
				var str = this.serialize();
				$(array).each(function() {
					var myValue = this.value;

					var title = $("#" + this.name).attr("title");

					if (!isNull(title)) {
						myValue = myValue.replace(title, "");
					}
					if (!isNull(myValue)) {
						if (serializeObj[this.name]) {

							if ($.isArray(serializeObj[this.name])) {
								serializeObj[this.name].push(myValue);
							} else {
								serializeObj[this.name] = [
										serializeObj[this.name], myValue];
							}
						} else {
							serializeObj[this.name] = myValue;
						}
					}
				});
				return serializeObj;
			}

		});

/**
 * 字符串日期转为日期对象
 */
$.extend({
			// escape
			jqEscape : function(str) {
				var reg = new RegExp("%", "g");
				str = escape(str);
				str = str.replace(/\+/g, "%u002B");
				str = str.replace(reg, "^");
				return str;
			},
			// 字符串转日期
			toDate : function(date) {
				var sd = "";
				if (/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/.test(date)) {
					sd = date.split("-");
					return new Date(sd[0], sd[1], sd[2]);
				} else {
					sd = date.split(" ");
					var sd1 = sd[0].split("-");
					var sd2 = sd[1].split(":");
					return new Date(sd1[0], sd1[1], sd1[2], sd2[0], sd2[1],
							sd2[2]);
				}
			},
			// 保留小数点后两位
			changeTwoDecimal : function(x) {
				var f_x = parseFloat(x);
				if (isNaN(f_x)) {
					alert('function:changeTwoDecimal->parameter error');
					return false;
				}
				f_x = Math.round(x * 100) / 100;

				return f_x;
			},
			strToJson : function(datas) {
				var jsonObj_ = null;
				jsonObj_ = eval("(" + datas + ")");
				return jsonObj_;
			},
			jsonToStr : function(datas) {
				var jsonObj_ = "";
				// if (isIE6() || isIE7()) {
				jsonObj_ = JsonToString(datas).replace(/[\r\n]/g, "");// Windows
				jsonObj_ = jsonObj_.replace(/[\n]/g, "");// Unix/Linux
				jsonObj_ = jsonObj_.replace(/[\r]/g, "");// Mac
				// } else {
				// jsonObj_ = JSON.stringify(datas);
				// }
				return jsonObj_;
			},
			jsonToStrClient : function(datas) {
				var jsonObj_ = "";
				jsonObj_ = JsonToStringClient(datas).replace(/[\r\n]/g, "");// Windows
				jsonObj_ = jsonObj_.replace(/[\n]/g, "");// Unix/Linux
				jsonObj_ = jsonObj_.replace(/[\r]/g, "");// Mac
				return jsonObj_;
			}
		});

function JsonToString(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null)
			return JsonToString(s);
		return /^(string|number)$/.test(typeof s) ? "\"" + s + "\"" : s;
	};
	for (var i in o)
		arr.push("\"" + i + "\":" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}

function JsonToStringClient(o) {
	if (o == undefined) {
		return "";
	}
	var r = [];
	if (typeof o == "string")
		return "\""
				+ o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n")
						.replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "object") {
		if (!o.sort) {
			for (var i in o)
				r.push("\"" + i + "\":" + JsonToStringClient(o[i]));
			if (!!document.all
					&& !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/
							.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for (var i = 0; i < o.length; i++)
				r.push(JsonToStringClient(o[i]));
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString().replace(/\"\:/g, '":""');
}

function toDecimal(x) {
	if (isNull(x)) {
		if (x == 0) {
			return "0.00";
		}
		return "";
	}

	if (/^-?\d$|^-?[1-9]\d+$|^-?[1-9]\d+.\d+$|^-?\d.\d+$/.test(x)) {
		var f = Number(x).toFixed(2);
		return f;
	} else {
		return "";
	}
}

function redirectUrl(url) {
	window.location.href = ctx + url;
}

function sysExtend(jsonOne, jsonTwo) {
	var newJson = {};
	if (!isNull(jsonOne)) {
		for (var key in jsonOne) {
			newJson[key] = jsonOne[key];
		}
	}
	if (!isNull(jsonTwo)) {
		for (var key in jsonTwo) {
			newJson[key] = jsonTwo[key];
		}
	}
	return newJson;
}

$(function() {
			initTopBtn();
		});

$(window).resize(function() {
			initTopBtn();
		});

function initTopBtn() {
	var width = $(".tab_list").width();
	var childWidth = width / 3;

	$(".myself").css("width", childWidth + "px");
	$(".zhanghu").css("width", (childWidth - 2) + "px");
	$(".shezhi").css("width", childWidth + "px");

	initFooterBtn();

	if ($("footer").length == 0) {
		$("body").css("padding-bottom", "0px");
	}

	if ($(".message_content").length != 0) {
		var mhtml = $(".message_content").html();
		$(".message_content").html("<div class=\"message_content_in\">" + mhtml
				+ "</div>");
	}

}

function initFooterBtn() {
	var location = window.location.href;
	if (location.indexOf("/book/") != -1) {
		$(".clinic_info").find("img").prop("src",
				ctx + "/resource/img/yuyuebottom2.png");
		$(".clinic_info").find("h1").prop("class", "check_font_color");
	}

	if (location.indexOf("/patinfo/") != -1
			|| location.indexOf("/patrecharge/") != -1) {
		$(".user_info").find("img").prop("src",
				ctx + "/resource/img/geren2.png");
		$(".user_info").find("h1").prop("class", "check_font_color");
	}

	if (location.indexOf("/apply/") != -1) {
		$(".phone_qa").find("img").prop("src",
				ctx + "/resource/img/dianhua1.png");
		$(".phone_qa").find("h1").prop("class", "check_font_color");
	}

	if (location.indexOf("/applyonline/") != -1) {
		$(".im_qa").find("img").prop("src",
				ctx + "/resource/img/zaixianwz2.png");
		$(".im_qa").find("h1").prop("class", "check_font_color");
	}

	if (location.indexOf("/buyspforgeneral/") != -1) {
		$(".recharge_info").find("img").prop("src",
				ctx + "/resource/img/bottom_buy2.png");
		$(".recharge_info").find("h1").prop("class", "check_font_color");
	}

	var width = $(".footer_content").width();

	$(".footer_content div").css("width", (width / 5) + "px");

}