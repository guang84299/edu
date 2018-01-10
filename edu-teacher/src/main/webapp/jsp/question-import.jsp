<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">

	<form id="questionImportFormSel" class="itemForm" method="post" enctype ="multipart/form-data">
	    <table cellpadding="5">
	    		<tr>
	            <td>Excel:</td>
	            <td>
	            		<input type="file" id="uploadfile">
	            </td>
	        </tr>
	      </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionImportForm()">上传</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitQuestionImportForm(){
		var fileName = $("#uploadfile").val();
		if(fileName == "" || fileName == null || fileName == undefined)
		{
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		var fix = fileName.substr(fileName.lastIndexOf("."),fileName.length);
		if(fix != ".xls" && fix != ".xlsx")
		{
			$.messager.alert('提示','文件格式不正确!');
			return;
		}
		
	  var file = $("#uploadfile").prop('files');
	  var data = new FormData();  
	  data.append("excel", file[0]);
	  $.ajax({  
	        data: data,  
	        type: "POST",  
	        url: '/question/import',
	        cache: false,  
	        contentType: false,  
	        processData: false,  
	        success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','导入成功!');
					clearForm();
				}
				else
				{
					$.messager.alert('错误',"导入题库失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
	    }); 
	};
</script>
