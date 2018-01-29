<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<table cellpadding="5" >
		<c:forEach items="${items}" var="val">
	    		<tr>
            		<td>题目:</td>
	            <td> ${val.context }</td>
	        </tr>
	        <tr class="question_zhuguan">
     				<td>答案:</td>
     				<td>
     					<input name="answer_${val.paperItemId }" data-paperItemId="${val.paperItemId }" data-paperAnswerId="${val.paperAnswerId }"  type="hidden" value="${val.paperAnswerItem.answer }" style="width:20px;"/>
     					<img alt="" src="${val.paperAnswerItem.answer }" class="uploadimg_${val.paperItemId }" <c:if test="${val.paperAnswerItem.answer == null}">style="display:none;"</c:if> width="100px">
     					<div class="uploaddiv_${val.paperItemId }" <c:if test="${val.paperAnswerItem.answer != null}">style="display:none;"</c:if>>
     						没有作答
     					</div>
     				</td>
      		</tr>
	    </c:forEach>
	</table>
	<div style="padding:5px" id="paper-check" data-paid="${paid}">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="paperchecksubmitForm()">批改</a>
	</div>
</div>

<script type="text/javascript">

	function paperchecksubmitForm(){		
		var paid = $("#paper-check").attr("data-paid");
		var datas = {};
		datas.paperAnswerId = parseInt(paid);
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/check",
			dataType : "json",//回调
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','批改成功!','info',function(){
						$("#papercheckEditWindow").window('close');
						$("#papercheckList").datagrid("reload");
					});
				}
				else
				{
					$.messager.alert('错误',"批改失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});

	}
</script>
