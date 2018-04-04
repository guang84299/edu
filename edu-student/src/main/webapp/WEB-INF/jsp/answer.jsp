<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div style="padding:10px 10px 10px 10px">
	 <table cellpadding="5" id="answerEditForm">
	 	<c:forEach items="${items}" var="val">
	    		<tr>
            		<td>题目:</td>
	            <td> ${val.context }</td>
	        </tr>
	        <c:choose>
	        		<c:when test="${val.type == 1 }">
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
	        		</c:when>
	        		
	        		<c:when test="${val.type == 2 }">
	        			<tr class="question_single">
	        				<td>答案:</td>
	        				<td>
	        					<input name="answer_${val.paperItemId }" type="radio" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }" <c:if test="${val.paperAnswerItem.answer == 'a' }">checked="checked"</c:if> value="a" style="width:20px;"/>A
				            <input name="answer_${val.paperItemId }" type="radio" <c:if test="${val.paperAnswerItem.answer == 'b' }">checked="checked"</c:if> value="b" style="width:20px;"/>B
				            <input name="answer_${val.paperItemId }" type="radio" <c:if test="${val.paperAnswerItem.answer == 'c' }">checked="checked"</c:if> value="c" style="width:20px;"/>C
				            <input name="answer_${val.paperItemId }" type="radio" <c:if test="${val.paperAnswerItem.answer == 'd' }">checked="checked"</c:if> value="d" style="width:20px;"/>D
	        				</td>
	        			</tr>
	        		</c:when>
	        		
	        		<c:when test="${val.type == 3 }">
	        			<tr class="question_mutli">
	        				<td>答案:</td>
	        				<td>
	        					<input name="answer_${val.paperItemId }" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }" <c:if test="${fn:contains(val.paperAnswerItem.answer, 'a') }">checked="checked"</c:if> type="checkbox" value="a" style="width:20px;"/>A
				            <input name="answer_${val.paperItemId }" type="checkbox" <c:if test="${fn:contains(val.paperAnswerItem.answer, 'b') }">checked="checked"</c:if> value="b" style="width:20px;"/>B
				            <input name="answer_${val.paperItemId }" type="checkbox" <c:if test="${fn:contains(val.paperAnswerItem.answer, 'c') }">checked="checked"</c:if> value="c" style="width:20px;"/>C
				            <input name="answer_${val.paperItemId }" type="checkbox" <c:if test="${fn:contains(val.paperAnswerItem.answer, 'd') }">checked="checked"</c:if> value="d" style="width:20px;"/>D
	        				</td>
	        			</tr>
	        		</c:when>
	        		
	        		<c:when test="${val.type == 5 }">
	        			<tr class="question_zhuguan">
	        				<td>答案:</td>
	        				<td>
	        					<input name="answer_${val.paperItemId }" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }"  type="hidden" value="${val.paperAnswerItem.answer }" style="width:20px;"/>
	        					<img alt="" src="${val.paperAnswerItem.answer }" class="uploadimg_${val.paperItemId }" <c:if test="${val.paperAnswerItem.answer == null}">style="display:none;"</c:if> width="100px">
	        					<div class="uploaddiv_${val.paperItemId }" <c:if test="${val.paperAnswerItem.answer != null}">style="display:none;"</c:if>>
	        						<input type="file" class="uploadfile_${val.paperItemId }">
		        					<button data-paperItemId="${val.paperItemId }" >上传</button>
	        					</div>
	        				</td>
	        			</tr>
	        		</c:when>
	        		
	        </c:choose>
	        
	    </c:forEach>
	    </table>
	<div style="padding:5px">
		<c:choose>
			<c:when test="${items == null}">
				作业都做完了
			</c:when>
			<c:otherwise>
				<a href="javascript:void(0)"class="easyui-linkbutton"  onclick="answersubmitForm()">提交作业</a>
			</c:otherwise>
		</c:choose>	    
	</div>
</div>

<script type="text/javascript">
	
	var timestamp=new Date().getTime();

	$("#answerEditForm .question_zhuguan button").click(function(){
		var paperItemId = $(this).attr("data-paperItemId");
		var fileName = $("#answerEditForm .question_zhuguan .uploadfile_"+paperItemId).val();
		if(fileName == "" || fileName == null || fileName == undefined)
		{
			$.messager.alert('提示','请选择图片!');
			return;
		}
		var fix = fileName.substr(fileName.lastIndexOf("."),fileName.length);
		fix = fix.toLowerCase();
		if(fix != ".png" && fix != ".jpeg" && fix != ".jpg")
		{
			$.messager.alert('提示','文件格式不正确!');
			return;
		}
		var file = $("#answerEditForm .question_zhuguan .uploadfile_"+paperItemId).prop('files');
		var data = new FormData();  
		data.append("file", file[0]);
		$.ajax({  
	        data: data,  
	        type: "POST",  
	        url: '/paper/answer/upload',
	        cache: false,  
	        contentType: false,  
	        processData: false,  
	        success: function(data) { 
				if(data.state == 200)
				{
					$("#answerEditForm .question_zhuguan [name=answer_"+paperItemId+"]").val(data.msg);
					$("#answerEditForm .question_zhuguan .uploaddiv_"+paperItemId).hide();
					$("#answerEditForm .question_zhuguan .uploadimg_"+paperItemId).attr("src",data.msg);
					$("#answerEditForm .question_zhuguan .uploadimg_"+paperItemId).show();
				}
				else
				{
					$.messager.alert('错误',"上传失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
	    }); 	
		
		var maxTime = 0;
		var t = $(this).attr("data-time");
		if(t && parseInt(t) > maxTime)
			maxTime  = parseInt(t);
		
		var time=new Date().getTime();
		$(this).attr("data-time",time-timestamp+maxTime);
		timestamp = time;
	});
	
	$("#answerEditForm .question_judge input").click(function(){
		var times = $(this).siblings("[data-time]");
		var maxTime = 0;
		if(times)
		{
			for(var i=0;i<times.length;i++)
			{
				var t = parseInt($(times[i]).attr("data-time"));
				if(t > maxTime)
					maxTime  = t;
			}
		}
		var t = $(this).attr("data-time");
		if(t && parseInt(t) > maxTime)
			maxTime  = parseInt(t);
		
		var time=new Date().getTime();
		$(this).attr("data-time",time-timestamp+maxTime);
		timestamp = time;
	});
	
	$("#answerEditForm .question_single input").click(function(){
		var times = $(this).siblings("[data-time]");
		var maxTime = 0;
		if(times)
		{
			for(var i=0;i<times.length;i++)
			{
				var t = parseInt($(times[i]).attr("data-time"));
				if(t > maxTime)
					maxTime  = t;
			}
		}
		var t = $(this).attr("data-time");
		if(t && parseInt(t) > maxTime)
			maxTime  = parseInt(t);
		
		var time=new Date().getTime();
		$(this).attr("data-time",time-timestamp+maxTime);
		timestamp = time;
	});
	
	$("#answerEditForm .question_mutli input").click(function(){
		var times = $(this).siblings("[data-time]");
		var maxTime = 0;
		if(times)
		{
			for(var i=0;i<times.length;i++)
			{
				var t = parseInt($(times[i]).attr("data-time"));
				if(t > maxTime)
					maxTime  = t;
			}
		}
		var t = $(this).attr("data-time");
		if(t && parseInt(t) > maxTime)
			maxTime  = parseInt(t);
		
		var time=new Date().getTime();
		$(this).attr("data-time",time-timestamp+maxTime);
		timestamp = time;
	});

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
		    	var answerTime = parseInt($("#answerEditForm [name="+an+"]:checked").attr("data-time"));
		    	if(!answerTime)
		    		answerTime = 0;
		    	if(answer != null && answer != undefined)
	    		{
		    		paper_answer_item.paperItemId = paperItemId;
			    	paper_answer_item.paperAnswerId = paperAnswerId;
			    	paper_answer_item.answer = answer;
			    	paper_answer_item.answerTime = answerTime;
			    	paper_answer_items.push(paper_answer_item);
	    		}
		  });
		$("#answerEditForm .question_single").each(function(){
			var paper_answer_item = {};
			var paperItemId = $(this).find("input:eq(0)").attr("data-paperItemId");
			var paperAnswerId = $(this).find("input:eq(0)").attr("data-paperAnswerId");
		    	var an = $(this).find("input:eq(0)").attr("name");
		    	var answer = $("#answerEditForm [name="+an+"]:checked").val();
		    var answerTime = parseInt($("#answerEditForm [name="+an+"]:checked").attr("data-time"));
			if(!answerTime)
	    		answerTime = 0;
		    	if(answer != null && answer != undefined)
	    		{
		    		paper_answer_item.paperItemId = paperItemId;
			    	paper_answer_item.paperAnswerId = paperAnswerId;
			    	paper_answer_item.answer = answer;
			    	paper_answer_item.answerTime = answerTime;
			    	paper_answer_items.push(paper_answer_item);
	    		}
		  });
		$("#answerEditForm .question_mutli").each(function(){
			var paper_answer_item = {};
			var paperItemId = $(this).find("input:eq(0)").attr("data-paperItemId");
			var paperAnswerId = $(this).find("input:eq(0)").attr("data-paperAnswerId");
		    	var an = $(this).find("input:eq(0)").attr("name");
		    	var answer = "";
		    	var maxTime = 0;
		    	$("#answerEditForm [name="+an+"]:checked").each(function(){ 
		    			answer+=$(this).val()+","; 
		    			var t = $(this).attr("data-time");
		    			if(t && parseInt(t) > maxTime)
		    				maxTime = parseInt(t);
				});
		    	if(answer != "" && answer.length > 0)
			{
		    		answer = answer.substr(0,answer.length-1);
		    		
		    		paper_answer_item.paperItemId = paperItemId;
			    	paper_answer_item.paperAnswerId = paperAnswerId;
			    	paper_answer_item.answer = answer;
			    	paper_answer_item.answerTime = maxTime;
			    	paper_answer_items.push(paper_answer_item);
			}
		  });
		
		$("#answerEditForm .question_zhuguan").each(function(){
			var paper_answer_item = {};
			var paperItemId = $(this).find("input:eq(0)").attr("data-paperItemId");
			var paperAnswerId = $(this).find("input:eq(0)").attr("data-paperAnswerId");
		    	var an = $(this).find("input:eq(0)").attr("name");
		    	var answer = $("#answerEditForm [name="+an+"]").val();
		    	var answerTime = parseInt($(this).find("button:eq(0)").attr("data-time"));
		    	if(!answerTime)
		    		answerTime = 0;
		    	if(answer != "" && answer.length > 0)
			{
		    		paper_answer_item.paperItemId = paperItemId;
			    	paper_answer_item.paperAnswerId = paperAnswerId;
			    	paper_answer_item.answer = answer;
			    	paper_answer_item.answerTime = answerTime;
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
