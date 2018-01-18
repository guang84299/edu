<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="tclassEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	    		<tr>
	            <td>班级名称:</td>
	            <td><input class="easyui-textbox" type="text" name="name" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	    <input type="hidden" name="id"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclasssubmitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	function tclasssubmitForm(){
		if(!$('#tclassEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var tclass = {};
		tclass.id = $("#tclassEditForm [name=id]").val();
		tclass.name = $("#tclassEditForm [name=name]").val();
		var datas = JSON.stringify(tclass);
		$.ajax({
			type: "post",
			data: datas,
			url: "/tclass/edit",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改成功!','info',function(){
						$("#itemEditWindow").window('close');
						$("#tclassList").datagrid("reload");
					});
				}
				else
				{
					$.messager.alert('错误',"修改失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
	}
</script>
