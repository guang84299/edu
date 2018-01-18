<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="tclassAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>班级名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclassAddsubmitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclassAddclearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function tclassAddsubmitForm(){
		//有效性验证
		if(!$('#tclassAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var tclass = {};
		tclass.name = $("[name=name]").val();
		tclass.teacherId = $("[name=teacherId]").val();
		var datas = JSON.stringify(tclass);
		
		$.ajax({
			type: "post",
			data: datas,
			url: "/tclass/add",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','新增成功!');
				}
				else
				{
					$.messager.alert('错误',"新增失败！");
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
	
	function tclassAddclearForm(){
		$('#tclassAddForm').form('reset');
	}
</script>
