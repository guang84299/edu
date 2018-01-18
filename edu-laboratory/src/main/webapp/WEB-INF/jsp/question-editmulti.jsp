<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<form id="questionMultiEditForm" class="itemForm" method="post">
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
	        <tr class="question_multi">
	            <td>答案:</td>
	            <td>
		            <input name="answer-multi" type="checkbox" value="a" style="width:20px;"/>A
		            <input name="answer-multi" type="checkbox" value="b" style="width:20px;"/>B
		            <input name="answer-multi" type="checkbox" value="c" style="width:20px;"/>C
		            <input name="answer-multi" type="checkbox" value="d" style="width:20px;"/>D
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
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuestionMultiForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	
	function submitQuestionMultiForm(){
		if(!$('#questionMultiEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var question = {};
		question.id = $("#questionMultiEditForm [name=id]").val();
		question.gradeId = $("#questionMultiEditForm [name=gradeId]").val();
		question.subjectId = $("#questionMultiEditForm [name=subjectId]").val();
		question.context = $("#questionMultiEditForm [name=context]").val();
		question.difficult = $("#questionMultiEditForm [name=difficult]:checked").val();
		question.score = $("#questionMultiEditForm [name=score]").val();
		question.normalTime = $("#questionMultiEditForm [name=normalTime]").val();
		question.knowledgePoint = $("#questionMultiEditForm [name=knowledgePoint]").val();
		question.choiceA = $("#questionMultiEditForm [name=choiceA]").val();
		question.choiceB = $("#questionMultiEditForm [name=choiceB]").val();
		question.choiceC = $("#questionMultiEditForm [name=choiceC]").val();
		question.choiceD = $("#questionMultiEditForm [name=choiceD]").val();
		question.answer = "";
		$("#questionMultiEditForm [name=answer-multi]:checked").each(function(){ 
			question.answer+=$(this).val()+","; 
		});
		if(question.answer != "" && question.answer.length > 0)
		{
			question.answer = question.answer.substr(0,question.answer.length-1);
		}
		
		var datas = JSON.stringify(question);
		$.ajax({
			type: "post",
			data: datas,
			url: "/question/editmulti",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改成功!','info',function(){
						$("#questionMultiEditWindow").window('close');
						$("#questionMultiList").datagrid("reload");
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
