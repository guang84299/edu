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
    <div id="loginbg" class="easyui-dialog" title="管理员登录" data-options="modal:true,closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input name="name" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="" value=""/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
	        </div>
       	</div>
    </div>
    
    <script type="text/javascript">
    	$("#login").click(function(){
    		var user = {};
    		user.name = $("#loginbg [name=name]").val();
    		user.password = $("#loginbg [name=password]").val();
    		
    		if(user.username=="" || user.password==""){
    			$.messager.alert('错误',"用户名密码不能为空！");
    			return ;
    		}
    		// var datas = JSON.stringify(user);
    		$.ajax({
				type: "post",
				data: user,
				url: "user/login",
				//contentType : "application/json;charset=UTF-8",//发送数据的格式
				dataType : "json",//回调
				success: function(data) { 
					//$.cookie('token', data.data, { expires: 7 }); 
					if(data.state == 200)
					{
						$.cookie('token-laboratory', data.data); 
						window.location.href="/";
					}
					else
					{
						$.messager.alert('错误',"用户名或密码不正确！");
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