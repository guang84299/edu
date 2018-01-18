<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="studenttclassAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>科目代码:</td>
	            <td><input class="easyui-textbox" type="text" name="tclassId" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="studentId" value="${student.id}"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="studenttclassAddsubmitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="studenttclassAddclearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function studenttclassAddsubmitForm(){
		//有效性验证
		if(!$('#studenttclassAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var studenttclass = {};
		studenttclass.tclassId = $("[name=tclassId]").val();
		studenttclass.studentId = $("[name=studentId]").val();
		studenttclass.teacherId = $("[name=teacherId]").val();
		var datas = JSON.stringify(studenttclass);
		
		$.ajax({
			type: "post",
			data: datas,
			url: "/student_tclass/add",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','新增成功!');
				}
				else
				{
					$.messager.alert('错误',data.msg);
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
		
	}
	
	function studenttclassAddclearForm(){
		$('#studenttclassAddForm').form('reset');
	}
</script>
