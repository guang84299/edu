<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	 <table cellpadding="5" id="answerEditForm">
	 	<c:forEach items="${items}" var="val">
	    		<tr>
            		<td>题目:</td>
	            <td> ${val.context }</td>
	        </tr>
	        <tr class="question_judge">
	            <td>答案:</td>
	            <td>
	            		<c:choose>
	            			<c:when test="${val.paperAnswerItem != null }">
	            				<input name="answer_${val.paperItemId }" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }" type="radio" value="1" <c:if test="${val.paperAnswerItem.answer == '1' }">checked="checked"</c:if> style="width:20px;"/>对
		           	 		<input name="answer_${val.paperItemId }" type="radio" value="0" <c:if test="${val.paperAnswerItem.answer == '0' }">checked="checked"</c:if> style="width:20px;"/>错
	            			</c:when>
	            			<c:otherwise>
	            				<input name="answer_${val.paperItemId }" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }" type="radio" value="1" style="width:20px;"/>对
		           	 		<input name="answer_${val.paperItemId }" type="radio" value="0" style="width:20px;"/>错
	            			</c:otherwise>
	            		</c:choose>
	            </td>
	        </tr>
	        </c:forEach>
	    </table>
	<div style="padding:5px">
		<c:choose>
			<c:when test="${items == null}">
				作业都做完了
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="answersubmitForm()">提交作业</a>
			</c:otherwise>
		</c:choose>	    
	</div>
</div>

<script type="text/javascript">
	
	function answersubmitForm(){
		/* if(!$('#answerEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		} */
		var paper_answer_items = [];
		$("#answerEditForm .question_judge").each(function(){
			var paper_answer_item = {};
			var paperItemId = $(this).find("input:eq(0)").attr("data-paperItemId");
			var paperAnswerId = $(this).find("input:eq(0)").attr("data-paperAnswerId");
		    	var an = $(this).find("input:eq(0)").attr("name");
		    	var answer = $("#answerEditForm [name="+an+"]:checked").val();
		    	if(answer != null && answer != undefined)
	    		{
		    		paper_answer_item.paperItemId = paperItemId;
			    	paper_answer_item.paperAnswerId = paperAnswerId;
			    	paper_answer_item.answer = answer;
			    	paper_answer_items.push(paper_answer_item);
	    		}
		  });
		var datas = JSON.stringify(paper_answer_items);
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/answer",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','提交成功!');
				}
				else
				{
					$.messager.alert('错误',"提交失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
	}
</script>
