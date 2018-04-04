<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <div id="loginbg" class="easyui-dialog" title="教师登录" data-options="modal:true,closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	手机号: <input name="phone" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="required:true" value=""/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:60px;height:32px;margin-left: 40px">登录</a>
	            <a id="register" class="easyui-linkbutton" href="/toRegister" iconCls="icon-ok" style="width:60px;height:32px;margin-left: 10px">注册</a>
	            <a id="forget" class="easyui-linkbutton" iconCls="icon-ok" style="width:80px;height:32px;margin-left: 10px">忘记密码</a>
	        </div>
       	</div>
    </div>
    
    <script type="text/javascript">
   
    	$("#login").click(function(){
    		var user = {};
    		user.phone = $("#loginbg [name=phone]").val();
    		user.password = $("#loginbg [name=password]").val();
    		
    		if(user.phone=="" || user.password==""){
    			$.messager.alert('错误',"手机号密码不能为空！");
    			return ;
    		}
 
    	//	var datas = JSON.stringify(user);
    		$.ajax({
				type: "post",
				data: user,
				url: "/teacher/login",
				//contentType : "application/json;charset=UTF-8",//发送数据的格式
				dataType : "json",//回调
				success: function(data) { 
					//$.cookie('token', data.data, { expires: 7 }); 
					if(data.state == 200)
					{
						$.cookie('token-teacher', data.data); 
						window.location.href="/";
					}
					else
					{
						if(data.msg && data.msg.length>4)
							$.messager.alert('错误',data.msg);
						else
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