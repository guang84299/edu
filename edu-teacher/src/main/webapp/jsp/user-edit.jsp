<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
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
	    <input type="hidden" name="id"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	function submitForm(){
		if(!$('#itemeEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var user = {};
		user.id = $("#itemeEditForm [name=id]").val();
		user.name = $("#itemeEditForm [name=name]").val();
		user.password = $("#itemeEditForm [name=password]").val();
		user.role = $("#itemeEditForm [name=role]").val();
		var datas = JSON.stringify(user);
		$.ajax({
			type: "post",
			data: datas,
			url: "/user/edit",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改用户成功!','info',function(){
						$("#itemEditWindow").window('close');
						$("#itemList").datagrid("reload");
					});
				}
				else
				{
					$.messager.alert('错误',"修改用户失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
	}
</script>
