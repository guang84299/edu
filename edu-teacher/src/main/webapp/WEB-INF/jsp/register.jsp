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
    <div id="regbg" class="easyui-dialog" title="教师注册" data-options="modal:true,closable:false,draggable:false" style="width:500px;height:550px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名: <input name="name" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	密码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="required:true" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机号: <input name="phone" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	学校: <input name="scholl" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级:
	            	<select name="gradeId" value="" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px">
			    <c:forEach items="${grades}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			    </select>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学科:
	            	<select name="subjectId" value="" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px">
			    <c:forEach items="${subjects}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			    </select>
	            </div>
	        </div>
	        <div style="margin-bottom:20px;">
	            <div>
	            	教师资格证号: <input name="idcard" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value=""/>
	            </div>
	        </div>
	        <div>
	            <a id="register" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 100px">注册</a>
	        </div>
       	</div>
    </div>
    
    <script type="text/javascript">
    	$("#register").click(function(){
    		var teacher = {};
    		teacher.name = $("#regbg [name=name]").val();
    		teacher.password = $("#regbg [name=password]").val();
    		teacher.phone = $("#regbg [name=phone]").val();
    		teacher.scholl = $("#regbg [name=scholl]").val();
    		teacher.gradeId = $("#regbg [name=gradeId]").val();
    		teacher.subjectId = $("#regbg [name=subjectId]").val();
    		teacher.idcard = $("#regbg [name=idcard]").val();
    		
    		if(!$("#regbg").form('validate')){
    			$.messager.alert('提示','表单还未填写完成!');
    			return ;
    		}
    		
    		 var datas = JSON.stringify(teacher);
    		$.ajax({
				type: "post",
				data: datas,
				url: "/teacher/register",
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