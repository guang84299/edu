<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<form id="questionJudgeEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	    		 <tr>
            		<td>年级:</td>
	            <td>
	            	<select name="gradeId" value="" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px">
				    <c:forEach items="${grades}" var="val">
				      <option value="${val.id }">${val.name }</option>
				     </c:forEach>
			    </select>
	            </td>
	        </tr>
	        <tr>
            		<td>学科:</td>
	            <td>
	            <select name="subjectId" value="" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px">
			    <c:forEach items="${subjects}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			    </select>
	            </td>
	        </tr>
	    		 <tr>
            		<td>题目:</td>
	            <td><input class="easyui-textbox" type="text" name="context" data-options="width:280,multiline:true,min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	        <tr class="question_judge">
	            <td>答案:</td>
	            <td>
		            <input name="answer" type="radio" value="1" checked="checked" style="width:20px;"/>对
		            <input name="answer" type="radio" value="0" style="width:20px;"/>错
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
	        <tr>
	            <td>知识点:</td>
	            <td><input class="easyui-textbox" type="text" name="knowledgePoint" data-options="width:280,multiline:true,min:1,max:99999999,precision:0" /></td>
	        </tr>
	    </table>
	    <input type="hidden" name="id"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionJudgeForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	function submitQuestionJudgeForm(){
		if(!$('#questionJudgeEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var question = {};
		question.id = $("#questionJudgeEditForm [name=id]").val();
		question.gradeId = $("#questionJudgeEditForm [name=gradeId]").val();
		question.subjectId = $("#questionJudgeEditForm [name=subjectId]").val();
		question.context = $("#questionJudgeEditForm [name=context]").val();
		question.answer = $("#questionJudgeEditForm [name=answer]:checked").val();
		question.answer = parseInt(question.answer);
		question.difficult = $("#questionJudgeEditForm [name=difficult]:checked").val();
		question.score = $("#questionJudgeEditForm [name=score]").val();
		question.normalTime = $("#questionJudgeEditForm [name=normalTime]").val();
		question.knowledgePoint = $("#questionJudgeEditForm [name=knowledgePoint]").val();
		var datas = JSON.stringify(question);
		
		$.ajax({
			type: "post",
			data: datas,
			url: "/question/editjudge",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改成功!','info',function(){
						$("#questionJudgeEditWindow").window('close');
						$("#questionJudgeList").datagrid("reload");
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
