$(function() { // ul/li的折叠效果
	$(".list_menu ul > li > a.selected").parent().parent().show();
	$(".list_menu h3").click(function() {
		$(this).next().stop().slideToggle();
		$(this).siblings().next("ul").stop().slideUp();
	});
	// 导航栏切换
	// $(".list_menu a").each(function() {
	// $this = $(this);
	// if($this[0].href==String(window.location)) {
	// $(".list_menu > ul > li > a").removeClass("selected");
	// $this.addClass("selected").parent().parent("ul").css("display",
	// "block").siblings("ul").css("display", "none");
	// $this.parents(".list_menu_container").show();
	// }
	// });
	// 一级菜单的active 点击切换
	$(".iconbox").on("click", function() {
		$(".iconbox").removeClass("active").css("color", "#c0c0cc");
		$(this).addClass('active').css("color", "#e65545");
		var id = $(this).attr("id");
		//清除子菜单选中状态
		$("."+id).find("a").removeClass("selected");
		//清除二级菜单选中状态
		$("."+id+" > ul").css("display","none");
		//默认让第一个二级菜单选中
		$("."+id+" > ul:first").css("display","block");
		//默认让第一个二级菜单的第一个三级子菜单选中
		$("."+id+" > ul:first > li:first > a").addClass("selected");
	});

	// 二级菜单 子级a标签 active点击切换
	$(".list_menu a").click(function() {
//		$(".list_menu > ul > li > a").removeClass("selected");
		$(this).parent().parent().parent().find("a").removeClass("selected");
		$(this).addClass("selected");
	});

	// 数据中心显示
	$("#dataManage").on("click", function() {
		$("#dataDiv").show().siblings(".list_menu_container").hide();
		$(".data2").css('background-position', '0 -438');
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -308');
		$(".business2").css('background-position', '0 -376');
		$('.hospital').css('background-position', '0 -508');
	});
	
	// 内容管理显示
	$("#homeManage").on("click", function() {
		$("#homeDiv").show().siblings(".list_menu_container").hide();
		$(".data2").css('background-position', '0 -461');
		$(".home2").css('background-position', '0 -213');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -308');
		$(".business2").css('background-position', '0 -376');
		$('.hospital').css('background-position', '0 -508');
	});

	// 话题组显示
	$("#socialityManage").on("click", function() {
		$("#socialityDiv").show().siblings(".list_menu_container").hide();
		$(".data2").css('background-position', '0 -461');
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -237');
		$(".user").css('background-position', '0 -308');
		$(".business2").css('background-position', '0 -376');
		$('.hospital').css('background-position', '0 -508');
	});

	// 咨询管理
	$("#consultantManage").on("click", function() {
		$("#consultantDiv").show().siblings(".list_menu_container").hide();
		$(".data2").css('background-position', '0 -461');
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -308');
		$('.business2').css('background-position', '0 -399');
		$('.hospital').css('background-position', '0 -508');
	});
	
	// 医院管理
	$("#hospitalManage").on("click", function() {
		$("#hospitalDiv").show().siblings(".list_menu_container").hide();
		$(".data2").css('background-position', '0 -461');
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -308');
		$('.business2').css('background-position', '0 -376');
		$('.hospital').css('background-position', '0 -484');
	});

	// 广告推送显示
	$("#advertisement").on("click", function() {
		//
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -173');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -308');
		$(".business2").css('background-position', '0 -376')

	});

	// 用户反馈显示
	$("#userFeedback").on("click", function() {
		//
		$(".home2").css('background-position', '0 -149');
		$(".adver").css('background-position', '0 -192');
		$(".social").css('background-position', '0 -261');
		$(".user").css('background-position', '0 -285');
		$(".business2").css('background-position', '0 -376')

	});

	// 管理菜单导航切换
	// $(".left_menu_icon a").each(function() {
	// $this = $(this);
	// if($this[0].href==String(window.location)) {
	// //更改当前点击的样式
	// $(this).parent().addClass("active").siblings().removeClass("active");
	// //获取元素ID
	// var id = $(this).parent().attr("id");
	// //通过ID获取value值
	// var value = $("#"+id+"").attr("value");
	// alert(value);
	// //首页管理
	// if(value == 1) {
	// console.log($("#homeDiv > a > div").html());
	// $("#homeDiv").show();
	// } else if(value == 2) { //社交管理
	// console.log($("#socialityDiv > a > div").html());
	// $("#socialityDiv").show();
	// }
	// }
	// });
});

$('.tab-but li').on("click", function() {
	$(this).siblings().removeClass("tab_active");
	$(this).addClass("tab_active");
});
// 健康指导2弹窗新增填写框
$(function() {
	$('.addjk').click(
			function() {
				var jk_val = '<li>'
						+ '<label class="label ie_6_7_top">标题</label>'
						+ '<input type="text" class="shortinput"/>' + '</li>'
						+ '<li>' + '<label class="label label-t">提示</label>'
						+ '<textarea class="pop_textarea"></textarea>'
						+ '</li>';
				$('.jiank_tig').append(jk_val);
			});
});
// table tr列表删除按钮js
$(".delBtn").click(function() {
	$(this).parent().parent("tr").attr("id", "target");
	layer.alert('要删除这条记录么？', {
		icon : 0,
		title : '提示',
		btn : [ '删除' ],
		btnAlign : 'c',
		yes : function(index, layero) {
			$("#target").remove();
			layer.close(index);
			layer.msg('操作成功', {
				time : 600
			});
		},
		// 键盘 回车 ECS控制弹窗~
		success : function(layero, index) {
			$(document).on('keydown', function(e) {
				if ($(".layui-layer").hasClass("layui-layer")) {
					if (e.keyCode == 13) {
						layer.close(index);
						$("#target").remove();
						for (var i = 0; i < 1; i++) {
							layer.msg('操作成功', {
								time : 600
							});
						}
					}
					if (e.keyCode == 27) {
						layer.close(index);
					}
				}
			});
		}
	});
});
// table 推广弹框
function generalize() {
	layer.alert($("#generalize").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 恢复显示
function trShowTrue() {
	layer.alert($("#trShowTrue").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 禁言
function bannedture() {
	layer.alert($("#bannedture").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
function bannedfalse() {
	layer.alert($("#bannedfalse").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 禁用
function forbiddenTrue() {
	layer.alert($("#forbiddenTrue").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
function forbiddenFalse() {
	layer.alert($("#forbiddenTrue").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 设置管理员
function adminTrue() {
	layer.alert($("#adminTrue").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
function adminFalse() {
	layer.alert($("#adminFalse").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 设为推荐
function recommendTrue() {
	layer.alert($("#recommendTrue").val(), {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
// table 按钮回馈弹窗
function dialog(event) {
	layer.alert(event, {
		title : '提示',
		btn : [ '确定' ],
		btnAlign : 'c',
		yes : function() {
			layer.msg('操作成功', {
				time : 600
			});
		}
	});
}
$("#list_menu_btn").click(function() {
	if ($(".list_menu_container").css("left") == '0px') {
		$(".list_menu_container").css("left", "-200px");
		$("#list_menu_btn").css("left", "80px").text(">");
		$(".content").css("margin-left", "80px");
	} else {
		$(".list_menu_container").css("left", "0px");
		$("#list_menu_btn").css("left", "280px").text("<");
		$(".content").css("margin-left", "280px");
	}
});
$(".iconbox").click(function() {
	$(".list_menu_container").css("left", "0px");
	$("#list_menu_btn").css("left", "280px").text("<");
	$(".content").css("margin-left", "280px");
});
// 表格单双行颜色交替js hover JS
$(document).ready(function() {
	var atr = $('.table tbody tr');
	var arr = [ '#fff', '#fafafa' ];
	for (var i = 0; i < atr.length; i++) {
		atr[i].index = i;
		atr[i].style.background = arr[i % arr.length];
		atr[i].onmouseover = function() {
			this.style.background = '#edf5ff';
		};
		atr[i].onmouseout = function() {
			this.style.background = arr[this.index % arr.length];
		};
	}
});

/*******************************************************************************
 * huangzg 时间格式化
 * 
 * @param window
 * @param $
 */
(function(window, $) {
	/**
	 * 对Date的扩展，将 Date 转化为指定格式的String
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符 年(y)可以用 1-4
	 * 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 用法: (new Date()).format() ==> 2015-10-12
	 * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2015-10-12 2:20:16.725 (new
	 * Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2015-10-12 02:19:13.305 (new
	 * Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2015-10-12 一 14:19:29 (new
	 * Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2015-10-12 周一 02:19:47 (new
	 * Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2015-10-12 星期一 02:20:02
	 */
	Date.prototype.format = function(fmt) {
		// 默认格式：yyyy-MM-dd
		fmt = fmt || 'yyyy-MM-dd';
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
			"H+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		var week = {
			"0" : "\u65e5",
			"1" : "\u4e00",
			"2" : "\u4e8c",
			"3" : "\u4e09",
			"4" : "\u56db",
			"5" : "\u4e94",
			"6" : "\u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt
					.replace(
							RegExp.$1,
							((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
									: "\u5468")
									: "")
									+ week[this.getDay() + ""]);
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	};
})(window, jQuery);
