<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/e3.css" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
	.loginout
	{
		color:#ff0000;
	}
	.loginout:hover
	{
	  cursor: pointer;
	  color:#000;
	}
</style>
</head>
<body class="easyui-layout">
    <!-- 头部标题 -->
	<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3"> 
		<span class="northTitle">教研室端</span>
	    <span class="loginInfo">登录用户：${user.name}&nbsp;&nbsp;角色：<c:if test="${user.role == 0}">系统管理员</c:if><c:if test="${user.role == 1}">普通管理员</c:if>&nbsp;&nbsp;<span class="loginout">退出</span></span>
	</div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'user/toadd'}">新增用户</li>
	         		<li data-options="attributes:{'url':'user/tolist'}">查询用户</li>
	         	</ul>
         	</li>
         	<li>
         		<span>题库管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/question/toadd'}">新增题库</li>
	         		<li data-options="attributes:{'url':'/question/tolist'}">查询题库</li>
	         		<li data-options="attributes:{'url':'/question/toimport'}">批量导入</li>
	         	</ul>
         	</li>
         	<li>
         		<span>分类管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/question/category'}">题库分类</li>
	         	</ul>
         	</li>
         	
         	<li>
         		<span>索引库管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/search/toimportall'}">同步索引</li>
	         	</ul>
         	</li>
         	
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	<h3>管理系统</h3>
		    </div>
		</div>
    </div>
    <!-- 页脚信息 -->
	<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
		<span id="sysVersion">系统版本：V1.0</span>
	    <span id="nowTime"></span>
	</div>
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
	
	$(".loginout").click(function(){
		var params = {"token":$.cookie('token') };
		$.post("/user/loginout",params, function(data){
			if(data.state == 200){
				window.location.href="/toLogin";
			}
		});
	});
});
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>