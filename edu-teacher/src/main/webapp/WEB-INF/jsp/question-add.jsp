<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">

	<form id="questionAddFormSel" class="itemForm" method="post">
	    <table cellpadding="5">
	    		<tr>
	            <td>题库类型:</td>
	            <td>
	            		<select class="easyui-combobox" name="type" data-options="width:100" style="margin-left:30px;">
		               <option value="0">判断题</option>
		               <option value="1">单选题</option>
		               <option value="2">多选题</option>
		               <option value="3">主观题</option>
	               </select>
	            </td>
	        </tr>
	      </table>
	</form>
	  
	<form id="questionAddForm0" class="itemForm" method="post">
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
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	
	<form id="questionAddForm1" class="itemForm" method="post">
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
	        <tr class="question_single">
	            <td>答案:</td>
	            <td>
		            <input name="answer-single" type="radio" value="a" checked="checked" style="width:20px;"/>A
		            <input name="answer-single" type="radio" value="b" style="width:20px;"/>B
		            <input name="answer-single" type="radio" value="c" style="width:20px;"/>C
		            <input name="answer-single" type="radio" value="d" style="width:20px;"/>D
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
	  <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	
	<form id="questionAddForm2" class="itemForm" method="post">
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
	  <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
		
		
	<form id="questionAddForm3" class="itemForm" method="post">
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
	        <tr class="question_zhuguan">
	            <td>答案:</td>
	            <td><input class="easyui-textbox" type="text" name="answer" data-options="width:280,multiline:true,min:1,max:99999999,precision:0" /></td>
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
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	        	
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitForm(){
		var type = $("#questionAddFormSel .easyui-combobox").val();
		var formName = "#questionAddForm" + type;
		//有效性验证
		if(!$(formName).form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var question = {};
		question.context = $(formName + " [name=context]").val();
		question.difficult = $(formName + " [name=difficult]:checked").val();
		question.score = $(formName + " [name=score]").val();
		question.normalTime = $(formName + " [name=normalTime]").val();
		question.knowledgePoint = $(formName + " [name=knowledgePoint]").val();
		question.gradeId = $(formName + " [name=gradeId]").val();
		question.subjectId = $(formName + " [name=subjectId]").val();
		question.teacherId = $(formName + " [name=teacherId]").val();
		if(type == 0)
		{
			question.type = 1;
			question.answer = $(formName + " .question_judge [name=answer]:checked").val();
			question.answer = parseInt(question.answer);
		}
		else if(type == 1)
		{
			question.type = 2;
			question.answer = $(formName +  " .question_single [name=answer-single]:checked").val();
			question.choiceA = $(formName + " [name=choiceA]").val();
			question.choiceB = $(formName + " [name=choiceB]").val();
			question.choiceC = $(formName + " [name=choiceC]").val();
			question.choiceD = $(formName + " [name=choiceD]").val();
		}
		else if(type == 2)
		{
			question.type = 3;
			question.answer = "";
			$(formName + " .question_multi [name=answer-multi]:checked").each(function(){ 
				question.answer+=$(this).val()+","; 
			});
			if(question.answer != "" && question.answer.length > 0)
			{
				question.answer = question.answer.substr(0,question.answer.length-1);
			}
			question.choiceA = $(formName + " [name=choiceA]").val();
			question.choiceB = $(formName + " [name=choiceB]").val();
			question.choiceC = $(formName + " [name=choiceC]").val();
			question.choiceD = $(formName + " [name=choiceD]").val();
		}
		else if(type == 3)
		{
			question.type = 5;
			question.answer = $(formName + " [name=answer]").val();
		}
		
		var datas = JSON.stringify(question);
		$.ajax({
			type: "post",
			data: datas,
			url: "/question/add",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','新增题库成功!');
					clearForm();
				}
				else
				{
					$.messager.alert('错误',"新增题库失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
	}
	
	function clearForm(){
		$('#questionAddForm0').form('reset');
		$('#questionAddForm1').form('reset');
		$('#questionAddForm2').form('reset');
		$('#questionAddForm3').form('reset');
	}
	
	$(function(){
		$("#questionAddForm1").hide();
		$("#questionAddForm2").hide();
		$("#questionAddForm3").hide();
		$("#questionAddFormSel .easyui-combobox").combobox({
			onChange: function (n,o) {
				if(n == 0)
				{
					$("#questionAddForm0").show();
					$("#questionAddForm1").hide();
					$("#questionAddForm2").hide();
					$("#questionAddForm3").hide();
				}
				else if(n == 1)
				{
					$("#questionAddForm0").hide();
					$("#questionAddForm1").show();
					$("#questionAddForm2").hide();
					$("#questionAddForm3").hide();
				}
				else if(n == 2)
				{
					$("#questionAddForm0").hide();
					$("#questionAddForm1").hide();
					$("#questionAddForm2").show();
					$("#questionAddForm3").hide();
				}
				else if(n == 3)
				{
					$("#questionAddForm0").hide();
					$("#questionAddForm1").hide();
					$("#questionAddForm2").hide();
					$("#questionAddForm3").show();
				}
			}
		});
		
	});
</script>
