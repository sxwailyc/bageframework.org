<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>英语100分</title>

<link href="/css/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/css/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/css/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/plugin/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/css/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lt IE 9]><script src="/js/speedup.js" type="text/javascript"></script><script src="js/jquery-1.11.3.min.js" type="text/javascript"></script><![endif]-->
<!--[if gte IE 9]><!--><script src="/js/jquery-2.1.4.min.js" type="text/javascript"></script><!--<![endif]-->

<script src="/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/plugin/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="/plugin/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/plugin/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>


<script src="/js/dwz/dwz.core.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.util.date.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.validate.method.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.barDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.drag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.tree.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.accordion.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.ui.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.theme.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.navTab.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.tab.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.resize.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.dialog.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.cssTable.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.stable.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.taskBar.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.ajax.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.pagination.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.database.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.datepicker.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.effects.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.panel.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.checkbox.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.history.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.combox.js" type="text/javascript"></script>
<script src="/js/dwz/dwz.print.js" type="text/javascript"></script>

<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.js"></script>
<script src="/js/rpc.js"></script>
<script src="/js/angular.curd.grid.js"></script>

<!-- 可以用dwz.min.js替换前面全部dwz.*.js (注意：替换时下面dwz.regional.zh.js还需要引入)
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="/js/dwz/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/js/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:true,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/css/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body>
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo2" href=""></a>
				<ul class="nav">
					<li><a href="changepwd.html" target="dialog" width="600">设置</a></li>
					<li><a href="login.html">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
                 <%@include file="left.inc.jsp"%>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">

                        </div>
						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2010 <a href="demo_page2.html" target="dialog">duowan</a> 京ICP备15053290号-2</div>

</body>
</html>