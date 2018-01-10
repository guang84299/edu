<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="questionSingleEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	    		<tr>
	            <td>题目:</td>
	            <td><input class="easyui-textbox" type="text" name="context" data-options="width:280,multiline:true,min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	        <tr class="question_single">
	            <td>答案:</td>
	            <td>
		            <input name="answer" type="radio" value="a" style="width:20px;"/>A
		            <input name="answer" type="radio" value="b" style="width:20px;"/>B
		            <input name="answer" type="radio" value="c" style="width:20px;"/>C
		            <input name="answer" type="radio" value="d" style="width:20px;"/>D
	            </td>
	        </tr>
	        <tr>
	            <td>困难度:</td>
	            <td>
	            <input name="difficult" type="radio" value="0" checked="checked" style="width:20px;"/>简单
		        <input name="difficult" type="radio" value="1" style="width:20px;"/>一般
		        <input name="difficult" type="radio" value="2" style="width:20px;"/>困难
	            </td>
	        </tr>
	        <tr>
	            <td>分数:</td>
	            <td><input class="easyui-textbox" type="text" name="score" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
			<tr>
	            <td>答题时间:</td>
	            <td><input class="easyui-textbox" type="text" name="normalTime" data-options="min:1,max:99999999,precision:0,required:true" />秒</td>
	        </tr>
	        <tr class="question_xuanxiang">
	            <td>选项A:</td>
	            <td><input class="easyui-textbox" type="text" name="choiceA" data-options="precision:0,required:true" /></td>
	        </tr>
	        <tr class="question_xuanxiang">
	            <td>选项B:</td>
	            <td><input class="easyui-textbox" type="text" name="choiceB" data-options="precision:0,required:true" /></td>
	        </tr>
	        <tr class="question_xuanxiang">
	            <td>选项C:</td>
	            <td><input class="easyui-textbox" type="text" name="choiceC" data-options="precision:0,required:true" /></td>
	        </tr>
	        <tr class="question_xuanxiang">
	            <td>选项D:</td>
	            <td><input class="easyui-textbox" type="text" name="choiceD" data-options="precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>知识点:</td>
	            <td><input class="easyui-textbox" type="text" name="knowledgePoint" data-options="width:280,multiline:true,min:1,max:99999999,precision:0" /></td>
	        </tr>
	    </table>
	    <input type="hidden" name="id"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionSingleForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	function submitQuestionSingleForm(){
		if(!$('#questionSingleEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var question = {};
		question.id = $("#questionSingleEditForm [name=id]").val();
		question.context = $("#questionSingleEditForm [name=context]").val();
		question.answer = $("#questionSingleEditForm [name=answer]:checked").val();
		question.difficult = $("#questionSingleEditForm [name=difficult]:checked").val();
		question.score = $("#questionSingleEditForm [name=score]").val();
		question.normalTime = $("#questionSingleEditForm [name=normalTime]").val();
		question.knowledgePoint = $("#questionSingleEditForm [name=knowledgePoint]").val();
		question.choiceA = $("#questionSingleEditForm [name=choiceA]").val();
		question.choiceB = $("#questionSingleEditForm [name=choiceB]").val();
		question.choiceC = $("#questionSingleEditForm [name=choiceC]").val();
		question.choiceD = $("#questionSingleEditForm [name=choiceD]").val();
		
		var datas = JSON.stringify(question);
		$.ajax({
			type: "post",
			data: datas,
			url: "/question/editsingle",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改成功!','info',function(){
						$("#questionSingleEditWindow").window('close');
						$("#questionSingleList").datagrid("reload");
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
