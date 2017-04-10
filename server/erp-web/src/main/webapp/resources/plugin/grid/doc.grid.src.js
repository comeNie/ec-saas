/**
 * @FileName: doc-grid.js
 * @Description: doc项目定制开发grid组件
 * @author 张超
 */
$.fn.extend({
	docgrid : function(options) {

		var defaultOption = {
			url : "",// 请求地址
			params : null,// 参数
			initParams : null,// 回显参数
			colNames : null,// 标题名字
			colModel : null,// 内容设置
			rowNum : 10,// 每页显示行数量
			rowList : [10, 20, 30],// 每页显示数量变化select使用
			sortname : '',// 默认排序字段
			sortorder : '',// 默认desc排序方式
			trId : "",// trID使用，与json数据中KEY对应
			needCheckBox : true,// 是否需要多选框
			needIndex : false,// 是否需要序号
			showCheckboxCondition : null,// {key:"",value:""}
			cListKey : "",//
			needPage : true,// 是否需要分页窗口
			pageId : "",// 分页容器标签ID
			firstNull : false,
			height : 410,
			onAllCheck : function(ischeck) {
			},
			loadComplate : function(obj, jsonList) {

			}
		}
		// 初始化设置参数
		var option_ = $.extend(defaultOption, options);

		// 定义当前对象内部使用参数
		var this_ = $(this);

		var thisObj = this;

		thisObj.optionObj_ = option_;

		var thisId = $(this).attr("id");
		// 清空table内容
		this_.empty();
		var messageId = "message_" + thisId;
		$("#" + messageId).remove();

		// this_
		// .after("<div id=\""
		// + messageId
		// + "\" style=\"padding:10px;padding-left:20px;color:red;\"></div>");

		// 校验标题和内容数量是否一致
		if (option_.colNames == null || option_.colModel == null
				|| option_.colNames.length != option_.colModel.length) {
			alert("标题和内容数量为空或不一致，请检查数据。");
			return null;
		}

		// 初始化表格标题标签
		function initTitles() {
			var titleStart = "<div class=\"g-f-h\"><table class=\"tablelist tablelist-title\"><thead><tr>";

			var titleEnd = "</tr></thead></table></div>";

			for (var i = 0; i < option_.colNames.length + 1; i++) {
				if (i == 0) {
					if (option_.needIndex) {
						titleStart += "<th width=\"20\">&nbsp;</th>";
					}

					if (option_.needCheckBox) {
						titleStart += "<th width=\"20\" align=\"center\"><input type=\"checkbox\" class=\"docgBox docgBoxHead\" name=\"docgBoxHead_"
								+ thisId + "\"  value=\"\"></th>";
					}
				} else {

					var width = option_.colModel[i - 1].width;
					if (isNull(width)) {
						width = "";
					}

					var isIn = false;
					if (option_.colModel[i - 1].hidden) {
						titleStart += "<th width=\"" + width
								+ "\" style=\"display:none;\"  class=\"th_"
								+ option_.colModel[i - 1].name + "\">"
								+ option_.colNames[i - 1] + "</th>";
					} else {
						if (option_.colModel[i - 1].sortable) {
							var th_class = "def";
							if (option_.colModel[i - 1].name == option_.sortname) {
								th_class = option_.sortorder;
							}

							titleStart += "<th width=\""
									+ width
									+ "\" id=\"th_"
									+ option_.colModel[i - 1].name
									+ "\" style=\"cursor:pointer\" class=\"sord "
									+ th_class + " th_"
									+ option_.colModel[i - 1].name
									+ "\" sortname=\""
									+ option_.colModel[i - 1].index + "\">"
									+ option_.colNames[i - 1] + "<i></i></th>";
						} else {
							titleStart += "<th width=\"" + width
									+ "\" class=\"th_"
									+ option_.colModel[i - 1].name + "\">"
									+ option_.colNames[i - 1] + "</th>";
						}
					}
				}
			}
			this_.append(titleStart + titleEnd);
			this_.find(".docgBoxHead").click(function() {
						var ischeck = $(this).prop("checked");
						var docgBoxs = this_.find(".docgBox");
						docgBoxs.each(function(key, val) {
									if (!$(val).prop("disabled")) {
										$(val).prop("checked", ischeck)
									}
								});

						option_.onAllCheck(ischeck);
					});

			this_.find(".sord").click(function() {

				thisObj.sortname = $(this).attr("sortname");
				if ($(this).hasClass("desc")) {
					this_.find(".sord").removeClass("asc").removeClass("desc")
							.addClass("def");
					$(this).removeClass("def");
					$(this).addClass("asc");
					thisObj.sortorder = "asc";
				} else {
					this_.find(".sord").removeClass("asc").removeClass("desc")
							.addClass("def");
					$(this).removeClass("def");
					$(this).addClass("desc");
					thisObj.sortorder = "desc";
				}
				thisObj.nowPage = 1;
				thisObj.initContent(thisObj.paramsCatch, true);

				if (!isNull(thisObj.pageObj)) {
					thisObj.pageObj.reload();
				}

			});
		}

		this.nowRowNum = option_.rowNum;
		this.nowPage = 1;
		this.total = 1;

		this.records = 0;

		this.sortname = option_.sortname;
		this.sortorder = option_.sortorder;
		this.rowObj = null;

		this.isInit = true;
		this.isPageInit = true;
		this.isInputPage = false;

		this.paramsCatch = null;
		this.pageObj = null;

		this.isFirst = true;

		// 生成表格内容
		this.initContent = function(params, moreClick, reloadFun, initType) {
			$("#" + messageId).remove();
			// ajax获取需要显示的json数据
			var resultJson = null;
			var paramOpt = null;

			var pageParam = null;
			if (option_.needPage) {
				pageParam = {
					length : this.nowRowNum,
					page : this.nowPage,
					sidx : this.sortname,// 默认排序字段
					sord : this.sortorder
					// 默认desc排序方式
				}
			} else {
				pageParam = {
					sidx : this.sortname,// 默认排序字段
					sord : this.sortorder
					// 默认desc排序方式
				}
			}

			if (isNull(params)) {
				paramOpt = sysExtend(pageParam, option_.params);
				if (thisObj.isInit && thisObj.isPageInit) {
					paramOpt = sysExtend(paramOpt, option_.initParams);
				}
			} else {
				paramOpt = sysExtend(option_.params, params);
				paramOpt = sysExtend(paramOpt, pageParam);
			}

			if (!isNull(thisObj.paramsCatch)) {
				paramOpt = sysExtend(paramOpt, thisObj.paramsCatch);
			}

			if (option_.firstNull && thisObj.isFirst) {
				var listJson = option_.cListKey;

				resultJson = {
					"total" : 0,
					"page" : 1,
					"records" : 0,
					"length" : 0,
					"begin" : 0,
					"sidx" : "cg_date",
					"sord" : "asc",
					"oper" : null,
					listJson : null
				};
				initResultToDom();
				thisObj.isFirst = false;
			} else {
				$.systemAjax({
							url : option_.url,
							type : "post",
							data : paramOpt,
							dataType : "json",
							isAlert : true,
							needSuccessMethod : true,
							// moreClick : moreClick,
							// loadingId : messageId,
							async : true,
							success : function(data) {
								thisObj.rowObj = data;
								resultJson = thisObj.rowObj;
								initResultToDom();

								if (!isNull(reloadFun)) {
									reloadFun();
								}
							}
						});
			}

			function initResultToDom() {

				var isError = false;
				if (!isNull(resultJson) && resultJson.resultType != "exception"
						&& resultJson.resultType != "validate") {

					thisObj.total = resultJson.total;
					thisObj.records = resultJson.records;

					var contentJson = resultJson.jsonList;
					this_.find(".g-f-m").remove();

					var contentStart = "<div class=\"g-f-m\" style=\"height:auto;\"><div class=\"g-f-c\"><table class=\"tablelist\"><tbody>";
					var contentEnd = "</tbody></table></div></div>";

					if (isNull(contentJson)) {
						this_
								.after("<div id=\""
										+ messageId
										+ "\" style=\"padding:10px;padding-left:20px;color:red;border: 1px solid #cbcbcb;\"></div>");
						$("#" + messageId).html("没有查询到符合条件的数据！");
						$("#" + option_.pageId).hide();
						contentJson = [];
					}

					var bgClass = "";

					for (var i = 0; i < contentJson.length; i++) {
						if (i % 2 != 0) {
							bgClass = "odd";
						} else {
							bgClass = "";
						}

						contentStart += "<tr id=\"tr_" + thisId + "_"
								+ contentJson[i][option_.trId] + "\" class=\""
								+ bgClass + "\">"

						if (option_.needIndex) {
							contentStart += "<td width=\"20\">" + (i + 1)
									+ "</td>";
						}

						if (option_.needCheckBox) {
							var inputBox = "";
							var isShow = true;
							if (!isNull(option_.showCheckboxCondition)) {
								for (var a = 0; a < option_.showCheckboxCondition.length; a++) {
									var sc = option_.showCheckboxCondition[a];
									if (contentJson[i][sc.key] == sc.value) {
										isShow = false;
									}
								}
							}

							if (isShow) {
								inputBox = "<input type=\"checkbox\" class=\"docgBox\" name=\"docgBox_"
										+ thisId
										+ "\" value=\""
										+ contentJson[i][option_.trId] + "\">";
							} else {
								inputBox = "<input type=\"checkbox\" class=\"docgBox\" disabled=\"disabled\" name=\"docgBox_"
										+ thisId
										+ "\" value=\""
										+ contentJson[i][option_.trId] + "\">";
							}
							contentStart += "<td width=\"20\" align=\"center\">"
									+ inputBox + "</td>";
						} else {
							contentStart += "";
						}
						$(option_.colModel).each(function(k, v) {

							var width = v.width;
							var align = v.align;
							if (isNull(width)) {
								width = "";
							}
							if (isNull(align)) {
								align = "";
							}

							var isIn = false;
							for (var key in contentJson[i]) {
								if (key == v.name) {
									isIn = true;
									var nowCm = v;
									if (!isNull(nowCm)) {
										// 日期格式化 2000-12-12 or 2000-12-12
										// 12:12:00
										var resultC = contentJson[i][key];

										if (nowCm.date) {
											resultC = dateToString(resultC);
										} else if (nowCm.dateTime) {
											resultC = dateTimeToString(resultC);
										}
										// 自定义方法修改内容
										if (!isNull(nowCm.renderFun)) {
											resultC = nowCm.renderFun(
													contentJson[i], resultC);
										}

										// 定义链接地址
										if (isNull(nowCm.renderFun)
												&& !isNull(nowCm.link)) {
											var mlink = nowCm.link;
											var href = "";
											var target = "";
											var pram = mlink.pram;
											var mhref = mlink.href;
											if (mlink.type == "javascript") {
												var prams = [];
												if (!isNull(pram)) {
													for (var mk in pram) {
														var pramMk = pram[mk];
														if (mk.indexOf("this") != -1) {
															prams
																	.push("'"
																			+ contentJson[i][pramMk]
																			+ "'");
														} else {
															prams.push("'"
																	+ pramMk
																	+ "'");
														}
													}

													href = "javascript:"
															+ mhref + "("
															+ prams.join(",")
															+ " );";;
												} else {
													href = "javascript:"
															+ mhref + "();";
												}
											}
											if (mlink.type == "url") {

												if (!isNull(mlink.target)) {
													target = mlink.target;
												}

												var prams = [];
												if (!isNull(pram)) {
													for (var mk in pram) {
														var pramMk = pram[mk];
														if (mk.indexOf("this") != -1) {
															prams
																	.push(pramMk
																			+ "="
																			+ contentJson[i][pramMk]);
														} else {
															prams.push(mk + "="
																	+ pramMk);
														}
													}

													if (mhref.indexOf("?") == -1) {
														href = mhref
																+ "?"
																+ prams
																		.join("&");
													} else {
														href = mhref
																+ "&"
																+ prams
																		.join("&");
													}
												}
											}

											resultC = "<a href=\"" + href
													+ "\" target=\"" + target
													+ "\">" + resultC + "</a>";
										}

										if (isNull(resultC + "")) {
											resultC = "&nbsp;";
										}

										if (!isNull(resultC)
												&& /^-?\d+\.\d+$/.test(resultC)) {
											resultC = toDecimal(resultC);
										} else if (nowCm.fixed) {
											resultC = toDecimal(resultC);
										}

										if (nowCm.hidden) {
											contentStart += "<td align=\""
													+ align
													+ "\" width=\""
													+ width
													+ "\" style=\"display:none;\" id=\"td_"
													+ nowCm.name
													+ "\" class=\"td_"
													+ nowCm.name + "\">"
													+ resultC + "</td>";
										} else {
											contentStart += "<td align=\""
													+ align + "\"  width=\""
													+ width + "\" id=\"td_"
													+ nowCm.name
													+ "\" class=\"td_"
													+ nowCm.name + "\">"
													+ resultC + "</td>";
										}
									}
									return;
								}
							}

							if (!isIn) {
								if (!/^act\d*$/.test(v.name)) {
									if (v.hidden) {
										contentStart += "<td align=\""
												+ align
												+ "\"  width=\""
												+ width
												+ "\" style=\"display:none;\" id=\"td_"
												+ nowCm.name + "\" class=\"td_"
												+ nowCm.name + "\"></td>";
									} else {
										// 自定义方法修改内容
										var resultC = "";
										if (!isNull(v.renderFun)) {
											resultC = v.renderFun(
													contentJson[i], null);
										}
										contentStart += "<td align=\"" + align
												+ "\"  width=\"" + width
												+ "\" id=\"td_" + v.name
												+ "\" class=\"td_" + v.name
												+ "\">" + resultC + "</td>";
									}
								}
							}

						});

						$(option_.colModel).each(function(k, v) {
							if (/^act\d*$/.test(v.name)) {
								var width = v.width;
								if (isNull(width)) {
									width = "";
								}

								var nowCmAct = v;
								var resultC = "";
								// 自定义方法修改内容
								if (!isNull(nowCmAct.renderFun)) {
									resultC = nowCmAct.renderFun(
											contentJson[i], resultC);
									contentStart += "<td align=\""
											+ nowCmAct.align + "\"  width=\""
											+ width + "\" id=\"td_"
											+ nowCmAct.name + "\" class=\"td_"
											+ nowCmAct.name + "\">" + resultC
											+ "</td>";
								} else {

									// 定义链接地址
									if (!isNull(nowCmAct.link)) {
										$(nowCmAct.link).each(function(k1, v1) {
											var mlink = v1;
											var href = "";
											var target = "";
											var pram = mlink.pram;
											var mhref = mlink.href;

											if (!isNull(mlink.target)) {
												target = mlink.target;
											}

											if (mlink.type == "javascript") {
												var prams = [];
												if (!isNull(pram)) {
													for (var mk in pram) {
														var pramMk = pram[mk];
														if (mk.indexOf("this") != -1) {
															prams
																	.push("'"
																			+ contentJson[i][pramMk]
																			+ "'");
														} else {
															prams.push("'"
																	+ pramMk
																	+ "'");
														}
													}

													href = "javascript:"
															+ mhref + "("
															+ prams.join(",")
															+ " );";;
												} else {
													href = "javascript:"
															+ mhref + "();";
												}
											}
											if (mlink.type == "url") {
												var prams = [];
												if (!isNull(pram)) {
													for (var mk in pram) {
														var pramMk = pram[mk];
														if (mk.indexOf("this") != -1) {
															prams
																	.push(pramMk
																			+ "="
																			+ contentJson[i][pramMk]);
														} else {
															prams.push(mk + "="
																	+ pramMk);
														}
													}

													if (mhref.indexOf("?") == -1) {
														href = mhref
																+ "?"
																+ prams
																		.join("&");
													} else {
														href = mhref
																+ "&"
																+ prams
																		.join("&");
													}
												}
											}
											resultC += "<a href=\"" + href
													+ "\" target=\"" + target
													+ "\">" + mlink.linkText
													+ "</a>&nbsp;&nbsp;";
										});
									}

									contentStart += "<td align=\""
											+ nowCmAct.align + "\" width=\""
											+ width + "\" id=\"td_"
											+ nowCmAct.name + "\" class=\"td_"
											+ nowCmAct.name + "\">" + resultC
											+ "</td>";

								}
							}
						});

						contentStart += "</tr>"
					}

					if (contentJson.length != 0) {
						this_.append(contentStart + contentEnd);
					}
				} else {
					this_.find(".g-f-m").remove();
					this_
							.after("<div id=\""
									+ messageId
									+ "\" style=\"padding:10px;padding-left:20px;color:red;\"></div>");
					$("#" + messageId).html("没有查询到符合条件的数据！");

					$("#" + option_.pageId).hide();
					isError = true;
				}

				$("#" + thisId + " tbody tr").bind('mouseover', function() {
					$(this).addClass('tr-hover').find('td:first')
							.addClass('td-first');
				}).bind('mouseout', function() {
					$(this).removeClass('tr-hover').find('td:first')
							.removeClass('td-first');
				});

				if (!isNull(contentJson) && contentJson.length != 0) {
					$("#" + messageId).remove();
					$("#" + option_.pageId).show();
					$("#" + option_.pageId + "_grid_records")
							.html(thisObj.records);
				}

				if (initType == "lengthChange" && !isError) {
					thisObj.pageObj.reload();
				}

				if (!thisObj.isInit) {
					option_.loadComplate(this, resultJson);

					return thisObj;
				}

				if (option_.needPage && !isError) {
					thisObj.isInit = false;
					thisObj.pageObj = $("#" + option_.pageId).docgridPage({
								gridObj : thisObj,
								rowNum : option_.rowNum,
								rowList : option_.rowList
							})

				}

				if (!option_.needPage) {
					$("#" + option_.pageId)
							.html("<span style=\"padding-left:10px;\" class=\"mr10 fl\">共"
									+ thisObj.records + "条记录</span>");
				}

				thisObj.initAllIds = function() {
					var ids = [];
					$(this_.find("tr")).each(function(k, v) {
						var id = "";
						if (!isNull($(v).attr("id"))) {
							var id = $(v).attr("id").replace(
									"tr_" + thisId + "_", "");
						}
						if (!isNull(id)) {
							ids.push(id);
						}
					})
					return ids;
				}

				thisObj.initAllCheckIds = function() {
					var idList = [];
					var objs = this_.find(".docgBox:checked")
							.not(".docgBoxHead");
					objs.each(function(k, v) {
								var id = $(v).val();
								idList.push(id);
							});
					return idList;
				}

				option_.loadComplate(this, resultJson);
				thisObj.reload = function(params) {
					thisObj.isPageInit = false;
					thisObj.paramsCatch = params;

					$("#" + messageId).remove();

					this.nowPage = 1;

					var th_class = "def";

					this_.find(".sord").removeClass("asc").removeClass("desc")
							.addClass("def");
					this_.find(".docgBox").attr("checked", false);

					this_.find("#th_" + option_.sortname).removeClass("asc")
							.removeClass("desc").removeClass("def")
							.addClass(option_.sortorder);

					this.initContent(params, true, function() {

						if (!isNull(thisObj.pageObj) && !isError) {
							thisObj.pageObj.reload();
						}
							// option_.loadComplate(thisObj);
						});

				}

				thisObj.reloadHandle = function(params) {

					thisObj.paramsCatch = params;

					$("#" + messageId).remove();

					var th_class = "def";

					this_.find(".sord").removeClass("asc").removeClass("desc")
							.addClass("def");
					this_.find(".docgBox").attr("checked", false);

					this_.find("#th_" + option_.sortname).removeClass("asc")
							.removeClass("desc").removeClass("def")
							.addClass(option_.sortorder);

					this.initContent(params, true, function() {
								// option_.loadComplate(thisObj);
							});
				}

				thisObj.getRowData = function(id) {
					var key = option_.trId
					var row = null;
					$(thisObj.rowObj.jsonList).each(function(k, v) {
								if (v[key] == id) {
									row = v;
									return false;
								}
							});
					return row;
				}
			}
		}

		// 执行方法
		initTitles();
		this.initContent();

		return this;

	},

	docgpReload : function(options) {

	},
	docgridPage : function(options) {
		var this_ = $(this);
		var thisId = $(this).attr("id");
		this_.empty();
		var gridObj = options.gridObj;
		this.pageObj = null;

		function initPageSelectHtml() {
			var pageSelect = "<div class=\"gd_input_page\" style=\"width:240px;height:30px;\"><div style=\"float:left;\">"
					+ "每页显示："
					+ "<select name=\"docGps\" id=\"docGps_"
					+ thisId
					+ "\" class=\"docGridPageSelect jdsele\">";

			var rowList = options.rowList;

			$(rowList).each(function(key, val) {
				pageSelect += "<option value=\"" + val + "\">" + val
						+ "条</option>";
			});

			pageSelect += "</select></div>";
			pageSelect += "<div style=\"padding-left:10px;float:left;\">共<b id=\""
					+ thisId
					+ "_grid_records\">"
					+ gridObj.records
					+ "</b>条记录</div></div>";

			this_.append(pageSelect);

			$("#docGps_" + thisId).change(function() {
				gridObj.nowRowNum = $(this).val();
				gridObj.nowPage = 1

				if (gridObj.isPageInit) {
					gridObj.initContent(gridObj.optionObj_.initParams, null,
							null, "lengthChange");
				} else {
					gridObj.initContent(null, null, null, "lengthChange");
				}

			});
		}

		function initPageNumHtml() {
			var pageSelect = "<ul class=\"paginList docpnhtml" + thisId + "\">";
			pageSelect += "</ul>";
			this_.append(pageSelect);
			initPageNum();
		}

		this.reload = function() {
			initPageNum();
		}

		function initPageNum() {
			/**
			 * 暂无需求 var pageSelect = "<div
			 * style=\"padding-left:10px;float:left;\">"; pageSelect += "<span>共" +
			 * gridObj.total + "页</span>"; pageSelect += "<span>"; pageSelect +=
			 * "到第<input type=\"text\" maxlength=\"10\" value=\"\"
			 * class=\"itxt-4 t-c ml5 mr5 docpInput\" style=\"width:40px;\">页";
			 * pageSelect += "</span>"; pageSelect += "<span>"; pageSelect += "<input
			 * type=\"button\" value=\"确定\" class=\"btn-vice docpBtn\"
			 * style=\"padding-left:12px;padding-right:12px;\">"; pageSelect += "</span></div>";
			 * 
			 * $(".gd_input_page").append(pageSelect);
			 */
			initPageNums();
		}

		var startNum = 1;
		var lessBtnNum = 3;
		var lesEndNum = 4;

		var initPageNums = function() {

			$(".docpnhtml" + thisId).empty();

			this.pageObj = $(".docpnhtml" + thisId).pagination(gridObj.records,
					{
						items_per_page : gridObj.nowRowNum,
						num_display_entries : 5,
						current_page : 0,
						num_edge_entries : 2,
						link_to : "javascript:void(0);",
						prev_text : "<span class=\"pagepre\">&nbsp;</span>",
						next_text : "<span class=\"pagenxt\">&nbsp;</span>",
						ellipse_text : "...",
						prev_show_always : true,
						next_show_always : true,
						callback : function() {
							return false;
						},
						clickPage : function(page_id) {
							gridObj.nowRowNum = $("#" + thisId
									+ " .docGridPageSelect").val();
							gridObj.nowPage = page_id + 1;
							if (gridObj.isPageInit) {
								gridObj
										.initContent(gridObj.optionObj_.initParams);
							} else {
								gridObj.initContent();
							}
						}
					});
			$("#" + thisId + " .docpBtn").unbind("click");
			$("#" + thisId + " .docpBtn").click(function() {
				gridObj.nowRowNum = $("#" + thisId + " .docGridPageSelect")
						.val();
				var pageNum = $("#" + thisId + " .docpInput").val();
				if (isNull(pageNum)) {
					$.systemAlert("请填写页数！");
					return;
				}
				if (!/^\d+$/.test(pageNum)) {
					$.systemAlert("请输入正整数。");
					return;
				}

				if (Number(pageNum) > Number(gridObj.total)
						|| Number(pageNum) == 0) {
					$.systemAlert("您输入的页码不存在，请重新输入。");
					return;
				}

				gridObj.nowPage = $("#" + thisId + " .docpInput").val();
				gridObj.isInputPage = true;
				var clickThis = this;
				gridObj.initContent(null, null, function() {
							pageObj.selectPage(pageNum - 1, clickThis)
						});
			});
		}

		initPageSelectHtml();
		initPageNumHtml();

		return this;
	}
});

// 日期转字符串格式
function dateTimeToString(str) {
	if (!isNull(str)) {
		var dd = new Date(str);
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1;// 获取当前月份的日期
		var d = dd.getDate();
		if (m < 10) {
			m = "0" + m;
		}
		if (d < 10) {
			d = "0" + d;
		}
		var h = dd.getHours();
		if (h < 10) {
			h = "0" + h;
		}

		var mi = dd.getMinutes();
		if (mi < 10) {
			mi = "0" + mi;
		}
		var s = dd.getSeconds();
		if (s < 10) {
			s = "0" + s;
		}
		return y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
	} else {
		return "";
	}
}