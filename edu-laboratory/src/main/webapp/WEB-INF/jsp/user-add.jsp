<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="userAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	        <tr>
	            <td>密码:</td>
	            <td><input class="easyui-textbox" type="password" name="password" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>角色:</td>
	            <td>
	               <select class="easyui-combobox" name="role"><option value="1">普通管理员  </option><option value="0">超级管理员  </option></select>
	            </td>
	        </tr>

	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#userAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var user = {};
		user.name = $("[name=name]").val();
		user.password = $("[name=password]").val();
		user.role = $("[name=role]").val();
		var datas = JSON.stringify(user);
		//alert(datas);
		$.ajax({
			type: "post",
			data: datas,
			url: "user/add",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','新增用户成功!');
				}
				else
				{
					$.messager.alert('错误',"新增用户失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		/* alert($("#userAddForm").serialize());
		$.post("/user/add",$("#userAddForm").serialize(), function(data){
			if(data.state == 200){
				$.messager.alert('提示','新增用户成功!');
			}
		}); */
		
		
	}
	
	function clearForm(){
		$('#userAddForm').form('reset');
	}
</script>
