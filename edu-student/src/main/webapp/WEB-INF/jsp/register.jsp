<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理系统</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.3/themes/icon.css" />
<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
</head>
<body>
  <body style="background-color: #F3F3F3">
    <div id="regbg" class="easyui-dialog" title="学生注册" data-options="modal:true,closable:false,draggable:false" style="width:400px;height:400px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            姓&nbsp;&nbsp;名: <input name="name" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="required:true" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            手机号: <input name="phone" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div>
	            <a id="register" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 60px">注册</a>
	        </div>
       	</div>
    </div>
    
    <script type="text/javascript">
    	$("#register").click(function(){
    		var student = {};
    		student.name = $("#regbg [name=name]").val();
    		student.password = $("#regbg [name=password]").val();
    		student.phone = $("#regbg [name=phone]").val();
    		
    		if(!$("#regbg").form('validate')){
    			$.messager.alert('提示','表单还未填写完成!');
    			return ;
    		}
    		
    		 var datas = JSON.stringify(student);
    		$.ajax({
				type: "post",
				data: datas,
				url: "/student/register",
				contentType : "application/json;charset=UTF-8",//发送数据的格式
				dataType : "json",//回调
				success: function(data) { 
					if(data.state == 200)
					{
						window.location.href="/toLogin";
					}
					else
					{
						$.messager.alert('错误',"手机号或密码不正确！");
					}
				}, 
				error: function(e) { 
					$.messager.alert('错误',"网络错误");
				} 
			});
    	});
    </script>
</body>
</html>