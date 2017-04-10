/**
 * @FileName: form-check.js
 * @Description: vc项目定制开发表单校验组件
 * @author 营销研发-张超
 * @date 2013-8-6 上午10:53:12
 * 
 */
var float_id = "";
var isSelfCssStyle = false;

jQuery.fn.extend({
	/**
	 * 常用验证方法。
	 */
	formDataCheck : function(option, event, otherOption) {

		var regexEnum = {
			intege : /^-?\d+$/, // 整数
			intege1 : /^\d+$/, // 正整数
			intege2 : /^-[0-9]*[1-9][0-9]*$/, // 负整数
			intNotZero : /^[1-9]\d+$|^[1-9]$/, // 正整数不包括0
			num : /^([+-]?)\d*\.?\d+$/, // 数字F
			decmal : /^(\-|\+)?[1-9]\d{1,10}$|^(\-|\+)?[1-9]\d{1,10}\.\b\d{1,10}\b$|^(\-|\+)?[1-9]$|^(\-|\+)?[1-9]\.\b\d{1,10}\b$|^(\-|\+)?0\.\d{0,100}$/, // 浮点数
			decmal1 : /^[1-9]\d*.\d*|0.\d*[1-9]\d*$/, // 正浮点数
			decmal2 : /^-([1-9]\d*.\d*|0.\d*[1-9]\d*)$/, // 负浮点数
			decmal3 : /^-?([1-9]\d*.\d*|0.\d*[1-9]\d*|0?.0+|0)$/, // 浮点数
			decmal4 : /^[1-9]\d*.\d*|0.\d*[1-9]\d*|0?.0+|0$/, // 非负浮点数（正浮点数加0）
			decmal5 : /^(-([1-9]\d*.\d*|0.\d*[1-9]\d*))|0?.0+|0$/, // 非正浮点数（负浮点数加0）
			email : /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/, // 邮件
			vc_decmal : /^-?\d$|^-?[1-9]\d+$|^-?[1-9]\d+.\d{1,2}$|^-?\d.\d{1,2}$/, // 匹配正负整数小数，小数点后最多2位
			vc_positive_decmal : /^\d$|^[1-9]\d+$|^[1-9]\d+\.\d{1,2}$|^\d\.\d{1,2}$/, // 匹配正整数小数，小数点后最多2位
			color : /^[a-fA-F0-9]{6}$/, // 颜色
			url : /(.*?):\/\/(?:([^:@]+)(?::([^@]*))?@)?([^:@\/]+)(?::(\d+))?(?:(\/.*?)(?:\?(.*?))?(?:#(.*?))?)$/, // url
			chinese : /^[\u4e00-\u9fa5]+$/, // 仅中文
			ascii : /^[\x00-\xFF]+$/, // 仅ACSII字符
			zipcode : /^\d{6}$/, // 邮编
			mobile : /^(13|14|15|18|17)[0-9]{9}$/, // 手机
			ip4 : /^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)$/, // ip地址
			notempty : /^\S+$/, // 非空
			picture : /(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/, // 图片
			rar : /(.*)\.(rar|zip|7zip|tgz)$/, // 压缩文件
			date : /^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))$/,
			datetime : /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$/,
			qq : /^[1-9]*[1-9][0-9]*$/, // QQ号码
			tel : /^\d{3,4}-\d{7,8}(-\d{3,4})?$/, // 电话号码的函数(包括验证国内区号,国际区号,分机号)
			username : /^[a-z|A-Z|0-9][a-z|A-Z|0-9|-]+$/, // 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
			password : /^(\w){6,20}$/, // 验证密码
			letter : /^[A-Za-z]+$/, // 字母
			letter_u : /^[A-Z]+$/, // 大写字母
			letter_l : /^[a-z]+$/, // 小写字母
			idcard : /^[1-9]([0-9]{14}|[0-9]{17}|[0-9]{16}[xX])$/, // 身份证
			othercard : /^[a-z|A-Z|0-9]{3,20}$/,
			// 其他证件 匹配由数字、26个英文字母或者下划线组成的字符串
			common_char : /^[a-z|A-Z|0-9|\u4e00-\u9fa5|\uFF00-\uFFFF|-]+$/,// 匹配字母数字，中文，全角中文符号。满足一般字段中文字符串要求
			carNumber : /^[a-z|A-Z|0-9|\u4e00-\u9fa5|-]{1,10}$/,
			// 以下五个为密码有效验证组合
			contain_d : /\d+/,
			contain_l : /[a-zA-Z]+/,
			contain_c : /[^0-9a-zA-Z\u4e00-\u9fa5]+/,
			no_chinese : /^[^\u4e00-\u9fa5]+$/,
			no_spaceend : /^\S+.*\S+$/,
			// 以上五个为密码有效验证组合
			mac_address : /[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/,
			// mac地址
			vc_sku : /^[0-9]{1,10}$|^([0-9]{1,10}\s*\n+\s*){0,100}$|^([0-9]{1,10}\s*\n+\s*){0,99}[0-9]{1,10}$/,
			// 可回车输入多行，最多输入100行；单行只能输入1-10位数字；允许输入多回车
			vc_sku_new : /^[0-9|a-z|A-Z|_]{1,50}$|^([0-9|a-z|A-Z|_]{1,50}\s*\n+\s*){0,100}$|^([0-9|a-z|A-Z|_]{1,50}\s*\n+\s*){0,99}[0-9|a-z|A-Z|_]{1,50}$/,
			vc_sku_new_1 : /^[0-9|a-z|A-Z|_|-]{1,50}$|^([0-9|a-z|A-Z|_|-]{1,50}\s*\n+\s*){0,100}$|^([0-9|a-z|A-Z|_|-]{1,50}\s*\n+\s*){0,99}[0-9|a-z|A-Z|_|-]{1,50}$/
		};

		var defaut = {
			checkType : "null",
			length : "",// 如果为区间格式为0-8，如果为最大直接输入整数即可
			message : "",
			regex_message : []
		};

		var option_ = jQuery.extend(defaut, option);

		option_ = jQuery.extend(option_, otherOption);

		var value = $(this).vcval();
		if ($(this).attr("type") == "checkbox") {
			var checkboxObjs = $(":checkbox[name='" + $(this).attr("name")
					+ "'][checked]");
			if (checkboxObjs.length > 0) {
				initSuccessHtml($(this));
				return false;
			} else {
				initErrorHtml($(this), option_.message);
				return true;

			}
		}
		if ($(this).attr("type") == "radio") {
			var checkboxObjs = $(":radio[name='" + $(this).attr("name")
					+ "'][checked]");
			if (checkboxObjs.length > 0) {
				initSuccessHtml($(this));
				return false;
			} else {
				initErrorHtml($(this), option_.message);
				return true;

			}
		}

		if (option_.checkType == "null") {
			if (isNull(value)) {
				initErrorHtml($(this), option_.message);
				return true;
			} else {
				if (isNull(option_.lengthCk)) {
					if (!isNull(option_.length)) {
						var l = option_.length.split("-");
						if (l.length > 1) {
							if (getLength(value) > l[1]
									|| getLength(value) < l[0]) {
								initErrorHtml($(this), option_.message);
								return true;
							} else {
								initSuccessHtml($(this));
								return false;
							}
						} else {
							if (getLength(value) > l[0]) {
								initErrorHtml($(this), option_.message);
								return true;
							} else {
								initSuccessHtml($(this));
								return false;
							}
						}
					} else {
						initSuccessHtml($(this));
						return false;
					}

				} else {
					if (!isNull(option_.lengthCk.length)) {
						var l = option_.lengthCk.length.split("-");
						if (l.length > 1) {
							if (getLength(value) > l[1]
									|| getLength(value) < l[0]) {
								initErrorHtml($(this), option_.lengthCk.message);
								return true;
							} else {
								initSuccessHtml($(this));
								return false;
							}
						} else {
							if (getLength(value) > l[0]) {
								initErrorHtml($(this), option_.lengthCk.message);
								return true;
							} else {
								initSuccessHtml($(this));
								return false;
							}
						}
					} else {
						initSuccessHtml($(this));
						return false;
					}
				}
			}
		}

		function checkMoreObjs(debug) {
			// alert(debug);

			// 检验数字
			if (!isNull(option_.twoNumber)) {
				if (option_.twoNumber.length == 2) {
					var one = option_.twoNumber[0];
					var two = option_.twoNumber[1];
					var oneVal = $("#" + one.name).vcval();
					var twoVal = $("#" + two.name).vcval();

					if (isNull(oneVal)) {
						initErrorHtml($("#" + one.name), one.message + "不能为空");
						return false;
					}
					if (isNull(twoVal)) {
						initErrorHtml($("#" + one.name), two.message + "不能为空");
						return false;
					}
					if (Number(oneVal) > Number(twoVal)) {
						if (one.showMessage) {
							initErrorHtml($("#" + one.name), one.message
											+ "不能大于" + two.message);
						}
						if (two.showMessage) {
							initErrorHtml($("#" + two.name), one.message
											+ "不能大于" + two.message);
						}
						return false;
					}
				} else {
					alert("数字比较数据设置错误");
					return false;
				}
			}

			if ($.isFunction(option_.otherMethod)) {
				var result = option_.otherMethod(event);
				if (!isNull(result)) {
					if (!result.checkKey) {
						initErrorHtml($("#" + result.showMessageId),
								result.checkMessage);
						return false;
					} else {
						initSuccessHtml($("#" + result.showMessageId),
								result.checkMessage);
					}
				}
				return true;
			}
			return true;
		}
		if (option_.checkType == "regex") {
			if (isNull(option_.lengthCk)) {
				if (!isNull(option_.length)) {
					var l = option_.length.split("-");
					if (l.length > 1) {
						if (getLength(value) > l[1] || getLength(value) < l[0]) {
							initErrorHtml($(this), option_.message);
							return true;
						} else {
							if (isNull(option_.regex_message)) {
								initSuccessHtml($(this));
								return false;
							}
						}
					} else {
						if (getLength(value) > l[0]) {
							initErrorHtml($(this), option_.message);
							return true;
						} else {
							if (isNull(option_.regex_message)) {
								initSuccessHtml($(this));
								return false;
							}
						}
					}
				} else {
					if (isNull(option_.regex_message)) {
						initSuccessHtml($(this));
						return false;
					}
				}

			} else {
				if (!isNull(option_.lengthCk.length)) {
					var l = option_.lengthCk.length.split("-");
					if (l.length > 1) {
						if (getLength(value) > l[1] || getLength(value) < l[0]) {
							initErrorHtml($(this), option_.lengthCk.message);
							return true;
						} else {
							if (isNull(option_.regex_message)) {
								initSuccessHtml($(this));
								return false;
							}
						}
					} else {
						if (getLength(value) > l[0]) {
							initErrorHtml($(this), option_.lengthCk.message);
							return true;
						} else {
							if (isNull(option_.regex_message)) {
								initSuccessHtml($(this));
								return false;
							}
						}
					}
				} else {
					if (isNull(option_.regex_message)) {
						initSuccessHtml($(this));
						return false;
					}
				}
			}

			if (isNull(option_.regex_message.length)) {
				if (!isNull(value)) {
					if (isNull(option_.regex_message.formatBase)) {
						if (!isNull(option_.regex_message.format)) {
							if (!option_.regex_message.format.test(value)) {
								// alert(option_.regex_message.message);
								initErrorHtml($(this),
										option_.regex_message.message);
								// $($(this)).focus();
								return true;
							} else {
								if (!checkMoreObjs(1)) {
									return true;
								}
								initSuccessHtml($(this));
								return false;
							}
						} else {
							alert("正则表达式规则未填写");
							return true;
						}
					} else {
						if (!isNull(option_.regex_message.checkById)) {

							var checkVal = $("#"
									+ option_.regex_message.checkById).vcval();
							var fmessage = option_.regex_message.checkByIdKey[""
									+ checkVal + ""];

							if (!isNull(fmessage)) {

								if (!regexEnum["" + fmessage + ""].test(value)) {
									// alert(option_.regex_message.message);
									initErrorHtml($(this),
											option_.regex_message.message);
									// $($(this)).focus();
									return true;
								} else {
									if (!checkMoreObjs(2)) {
										return true;
									}

									var checkVal = $("#"
											+ option_.regex_message.checkById)
											.vcval();
									var fmessage = option_.regex_message.checkByIdKey[""
											+ checkVal + ""];
									initSuccessHtml($(this));
									return false;
								}
							}
						} else {
							if (!regexEnum[""
									+ option_.regex_message.formatBase + ""]
									.test(value)) {
								// alert(option_.regex_message.message);
								initErrorHtml($(this),
										option_.regex_message.message);
								// $($(this)).focus();
								return true;
							} else {
								if (!checkMoreObjs(3)) {
									return true;
								}

								initSuccessHtml($(this));
								return false;
							}
						}
					}
				}
				// 多正则表达式
			} else {
				if (!isNull(value) && option_.regex_message.length > 0) {
					var this_ = this;
					var check_result = true;
					$(option_.regex_message).each(function(key, val) {

						if (isNull(val.formatBase)) {
							if (!isNull(value)) {
								if (!val.format.test(value)) {
									initErrorHtml($(this_), val.message);
									// $($(this_)).focus();
									check_result = true;
									return false;
								} else {
									if (!checkMoreObjs(1)) {
										check_result = true;
										return false;
									}
									initSuccessHtml($(this_));
									check_result = false;
								}
							} else {
								alert("正则表达式规则未填写");
								check_result = true;
								return false;
							}
						} else {
							if (!isNull(val.checkById)) {

								var checkVal = $("#" + val.checkById).vcval();
								var fmessage = val.checkByIdKey["" + checkVal
										+ ""];
								if (!regexEnum["" + fmessage + ""].test(value)) {
									// alert(val.message);
									initErrorHtml($(this_), val.message);
									// $($(this_)).focus();
									check_result = true;
									return false;
								} else {
									if (!checkMoreObjs(2)) {
										check_result = true;
										return false;
									}

									var checkVal = $("#" + val.checkById)
											.vcval();
									var fmessage = val.checkByIdKey[""
											+ checkVal + ""];

									initSuccessHtml($(this_));
									check_result = false;
								}
							} else {
								if (!regexEnum["" + val.formatBase + ""]
										.test(value)) {
									initErrorHtml($(this_), val.message);
									// $($(this_)).focus();
									check_result = true;
									return false;
								} else {
									if (!checkMoreObjs(3)) {
										check_result = true;
										return false;
									}

									initSuccessHtml($(this_));
									check_result = false;
								}
							}

						}
					});

					return check_result;
				}
			}
		}

		if (option_.checkType == "equal") {
			var target = option_.target;
			var message = option_.message;
			if ($(this).vcval() != $("#" + target).vcval()) {
				initErrorHtml($(this), message);
				return true;
			} else {
				initSuccessHtml($(this));
				return false;
			}
		}

		function initErrorHtml(obj, message) {
			var id = obj.attr("id");
			if (isNull(id)) {
				id = obj.attr("name");
			}
			if (obj.attr("type") == "checkbox" || obj.attr("type") == "radio") {
				if (!isNull($("#success_float_" + id).html())) {
					$("#success_float_" + id).remove();
				}
				if (isSelfCssStyle) {
					if (!isNull(option_.selfClass)) {
						obj.removeClass(option_.selfClass.successClass);
						obj.addClass(option_.selfClass.errorClass);
					}
					obj.addClass(option_.selfClass.errorClass);
				} else {
					if (!obj.is("select")) {
						obj.parent().css("border-bottom", "1px solid #FF0000");
					}
				}
			} else {
				if (!isNull($("#success_float_" + id).html())) {
					$("#success_float_" + id).remove();
				}
				if (isSelfCssStyle) {
					if (!isNull(option_.selfClass)) {
						obj.removeClass(option_.selfClass.successClass);
						obj.addClass(option_.selfClass.errorClass);
					}
				} else {
					if (!obj.is("select")) {
						obj.css("border-bottom", "1px solid #FF0000");
					}
				}

			}
			$.showFloatHtmlSelf(obj, message);
		}

		function initSuccessHtml(obj, message) {
			var id = obj.attr("id");
			if (isNull(float_id)) {
				if (isNull(id)) {
					id = obj.attr("name");
				}
				if (obj.attr("type") == "checkbox"
						|| obj.attr("type") == "radio") {
					obj.parent().css("border-bottom", "1px solid #33CC33");
				} else {
					if (isSelfCssStyle) {
						if (!isNull(option_.selfClass)) {
							obj.removeClass(option_.selfClass.errorClass);
							obj.addClass(option_.selfClass.successClass);
						}
					} else {
						if (!obj.is("select")) {
							obj.css("border-bottom", "1px solid #33CC33");
						}
					}
				}
			} else {
				if (isSelfCssStyle) {
					if (!isNull(option_.selfClass)) {
						obj.removeClass(option_.selfClass.errorClass);
						obj.addClass(option_.selfClass.successClass);
					}
				} else {
					if (!obj.is("select")) {
						obj.css("border-bottom", "1px solid #33CC33");
					}
				}
				id = float_id;
			}

			// if (!isNull($("#success_float_" + id).html())) {
			$("#" + id).parent().find(".errorCon").remove();
			// }

		}

		function getLength(str) {
			var len = str.length;
			var reLen = 0;
			for (var i = 0; i < len; i++) {
				if (str.charCodeAt(i) < 27 || str.charCodeAt(i) > 126) {
					// 全角
					reLen += 3;
				} else {
					reLen++;
				}
			}
			return reLen;
		}

		return false;
	}
});
var jqcheckResult = true;
var jqcheckdata = [];
var jqOtherCheckData = [];
var jqcheckSubmitdata = [];
var jqOthercheckSubmitdata = [];
var isInitOldTime = false;

jQuery.extend({
	checkSubmit : function(opt) {
		var checkSr = true;
		var i = 0;
		$(opt).each(function(key, val) {
			$("#" + val.name).focus(function() {
						$.removeFloatMessage([$(this).attr("id")]);
					});
			if (!isNull(val)) {
				if (!$.commonCheck(val)) {
					checkSr = false;

					if (i == 0) {
						$("html,body").animate({
							scrollTop : Number($("#" + val.name).offset().top)
									- 20
						}, 300);
						i++;
					}
				}
			}
		});
		jqcheckResult = checkSr;
		if (!isInitOldTime) {
//			$.initCheck(opt);
			isInitOldTime = true;
		}
		return jqcheckResult;
	},
	initCheck : function(opt) {
		isInitOldTime = true;
		$(opt).each(function(key, val) {
			var this_data = this;
			if (!isNull(val)) {
				var events = $("#" + val.name).data("events");

				if (!isNull(events) && isNull(events["focus"])) {
					$("#" + val.name).focus(function() {
								$.removeFloatMessage([$(this).attr("id")]);
								if (!isSelfCssStyle) {
									//$(this).css("border", "1px solid #CCCCCC");
								}
							});
				}
				if (!isNull(val.isSelect) && val.isSelect) {
					$("#" + val.name).focus(function() {
								this.select();
							});
				}
				if (isNull(val.initCheckType) || val.initCheckType.length == 0) {
					$("#" + val.name).unbind("focusout");
					$("#" + val.name).change(function() {
								$.initCommonCheck(this, "change", this_data);
							});
					$("#" + val.name).focusout(function() {
								$.initCommonCheck(this, "focusout", this_data);
							});
				} else {
					if ($("#" + val.name).length != 0) {
						$(val.initCheckType).each(function(key1, val1) {

							if (val1 = "change") {
								$("#" + val.name).unbind("change");
								$("#" + val.name).change(function() {
									$
											.initCommonCheck(this, "change",
													this_data);
								});
							}
							if (val1 = "focusout") {
								$("#" + val.name).unbind("focusout");
								$("#" + val.name).focusout(function() {
									$.initCommonCheck(this, "focusout",
											this_data);
								});
							}
						});
					} else {
						if ($("input[name='" + val.name + "']").length > 0) {
							var nowObj = $("input[name='" + val.name + "']");

							$(val.initCheckType).each(function(key1, val1) {
								$(nowObj).each(function(objkey, objval) {

									if (val1 = "change") {
										$(objval).unbind("change");
										$(objval).change(function() {
											$.initCommonCheck(this, "change",
													this_data);
										});
									}
									if (val1 = "focusout") {
										$(objval).unbind("focusout");
										$(objval).focusout(function() {
											$.initCommonCheck(this, "focusout",
													this_data);
										});
									}
								});
							});
						}
					}
				}
			}
		});

	},
	initCommonCheck : function(obj, event, jsonObj) {

		var objid = $(obj).attr("id");

		var val = jsonObj;
		if (!isNull(val)) {

			if (!isNull(val.float_id)) {
				float_id = val.float_id;
				$.removeFloatMessage([float_id]);
			} else {
				float_id = "";
				$.removeFloatMessage([float_id]);
			}
			if (val.name == objid) {
				// ============
				if ($("#" + objid).length != 0) {
					if (val.nullCheck) {
						if ($("#" + val.name).formDataCheck({
									message : val.nullCheckMessage,
									selfClass : val.selfClass
								}, event)) {
							jqcheckResult = false;
						} else {
							if ($("#" + val.name).formDataCheck(
									val.checkFormat, event, val.selfClass)) {
								jqcheckResult = false;
								// return false;
							} else {
								jqcheckResult = true;

							}
						}
					} else {
						if ($("#" + val.name).formDataCheck(val.checkFormat,
								event, val.selfClass)) {

							jqcheckResult = false;
							// return false;
						} else {
							jqcheckResult = true;

						}
						if (isNull($("#" + val.name).vcval())) {
							$.removeFloatMessage([val.name]);
						}
					}
					if (!isNull(val.linkCheck)) {
						$(val.linkCheck).each(function(key, val_link) {
							if (!isNull($("#" + val_link.name).vcval())) {
								if (val_link.nullCheck) {
									if ($("#" + val_link.name).formDataCheck({
												message : val_link.nullCheckMessage,
												selfClass : val_link.selfClass
											}, event)) {
										jqcheckResult = false;
									} else {
										if ($("#" + val_link.name)
												.formDataCheck(
														val_link.checkFormat,
														event,
														val_link.selfClass)) {
											jqcheckResult = false;
											// return false;
										} else {
											jqcheckResult = true;

										}
									}
								} else {
									if ($("#" + val_link.name).formDataCheck(
											val_link.checkFormat, event,
											val_link.selfClass)) {
										jqcheckResult = false;
										// return false;
									} else {
										jqcheckResult = true;

									}
								}
							}
						});
					}
				} else {
					if ($("input[name='" + objid + "']").length > 0) {
						var nowObj = $("input[name='" + val.name + "']")[0];

						if (val.nullCheck) {
							if ($(nowObj).formDataCheck({
										message : val.nullCheckMessage,
										selfClass : val.selfClass
									}, event)) {
								jqcheckResult = false;
							} else {
								if ($(nowObj).formDataCheck(val.checkFormat,
										event, val.selfClass)) {
									jqcheckResult = false;
									// return false;
								} else {
									jqcheckResult = true;

								}
							}
						} else {
							if ($(nowObj).formDataCheck(val.checkFormat, event,
									val.selfClass)) {
								jqcheckResult = false;
								// return false;
							} else {
								jqcheckResult = true;

							}
						}
						if (!isNull(val.linkCheck)) {
							var val_link = val.linkCheck;
							if (val_link.nullCheck) {
								if ($(nowObj).formDataCheck({
											message : val_link.nullCheckMessage,
											selfClass : val_link.selfClass
										}, event)) {
									jqcheckResult = false;
								} else {
									if ($(nowObj).formDataCheck(
											val_link.checkFormat, event,
											val_link.selfClass)) {
										jqcheckResult = false;
										// return false;
									} else {
										jqcheckResult = true;

									}
								}
							} else {
								if ($(nowObj).formDataCheck(
										val_link.checkFormat, event,
										val_link.selfClass)) {
									jqcheckResult = false;
									// return false;
								} else {
									jqcheckResult = true;

								}
							}
						}
					}
				}

				// =========
			}
		}
		// });
		float_id = "";
	},
	commonCheck : function(data) {
		if (!isNull(data.float_id)) {
			float_id = data.float_id;
			$.removeFloatMessage([float_id]);
		} else {
			float_id = "";
			$.removeFloatMessage([float_id]);
		}

		var checkr = true;
		if ($("#" + data.name).length != 0) {
			if (data.nullCheck) {
				if ($("#" + data.name).formDataCheck({
							message : data.nullCheckMessage,
							selfClass : data.selfClass
						})) {
					checkr = false;
				} else {

					if ($("#" + data.name).formDataCheck(data.checkFormat, "",
							data.selfClass)) {
						checkr = false;
						// return false;
					}
				}
			} else {
				if ($("#" + data.name).formDataCheck(data.checkFormat, "",
						data.selfClass)) {
					checkr = false;
					// return false;
				}
			}
		} else {
			if ($("input[name='" + data.name + "']").length > 0) {
				var nowObj = $("input[name='" + data.name + "']")[0];

				if (data.nullCheck) {
					if ($(nowObj).formDataCheck({
								message : data.nullCheckMessage,
								selfClass : data.selfClass
							})) {
						checkr = false;
					} else {

						if ($(nowObj).formDataCheck(data.checkFormat, "",
								data.selfClass)) {
							checkr = false;
							// return false;
						}
					}
				} else {
					if ($(nowObj).formDataCheck(data.checkFormat, "",
							data.selfClass)) {
						checkr = false;
						// return false;
					}
				}
			}
		}
		float_id = "";
		return checkr;
	},
	allArray : function(arr1, arr2) {
		var all = new Array();
		var i = 0;
		$(arr1).each(function(key, val) {
					all[i] = val;
					i++;
				});
		$(arr2).each(function(key, val) {
					all[i] = val;
					i++;
				});
		return all;
	},
	arrayToArray : function(arr1, arr2) {
		$(arr2).each(function(key, val) {
					arr1.push(val);
				});
		return arr1;
	},
	showFloatHtmlSelf : function(obj, message) {
		if (!isNull(float_id)) {
			obj = $("#" + float_id);
		}

		obj.prop("placeholder", message);
		obj.val("");

	},
	removeFloatMessage : function(ids) {
		$(ids).each(function(key, val) {
					if (!isNull(val)) {
						if (!isSelfCssStyle) {
							//$("#" + val).css("border", "1px solid #CCCCCC");
						}
						$("#" + val).parent().find(".errorCon").remove();
					}
				});
	},
	removeAllFloatMessage : function() {
		$(".errorCon").prev().attr("style", "");
		$(".errorCon").remove();
	}
});
