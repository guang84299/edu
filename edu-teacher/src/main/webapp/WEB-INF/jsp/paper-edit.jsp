<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<form id="paperEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
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
	            <td>班级:</td>
	            <td>
	            	<select name="tclassId" value="" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px">
			    <c:forEach items="${tclasss}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			    </select>
	            </td>
	        </tr>
	        
	         <tr>
	            <td>考题:</td>
	            <td>
	           	 <input type="button" name="type_1" value="合格考"/>
	           	 <input type="button" name="type_2" value="等级考"/>
	           	 <input type="button" name="type_3" value="竞赛考"/>
	            </td>
	        </tr>
	        
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	    <input type="hidden" name="id"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="papersubmitForm()">发布</a>
	</div>
</div>
<div id="paperItemEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/paper/question/tolist?paperId='" style="width:90%;height:90%;padding:10px;">
</div>
<script type="text/javascript">
	
	$("#paperEditForm [name=type_1]").click(function(){
		var id = $("#paperEditForm [name=id]").val();
		$('#paperItemEditWindow').window({href:"/paper/question/tolist?paperId="+id});
		$("#paperItemEditWindow").window({
    		onLoad :function(){
    			//回显数据
    			$("#paperquestionList-type").val("1");
    			$("#paperquestionList-paperId").val(id);
    		}
    	}).window("open");
	});
	
	$("#paperEditForm [name=type_2]").click(function(){
		var id = $("#paperEditForm [name=id]").val();
		$('#paperItemEditWindow').window({href:"/paper/question/tolist?paperId="+id});
		$("#paperItemEditWindow").window({
    		onLoad :function(){
    			//回显数据
    			$("#paperquestionList-type").val("2");
    			$("#paperquestionList-paperId").val(id);
    		}
    	}).window("open");
	});
	
	$("#paperEditForm [name=type_3]").click(function(){
		var id = $("#paperEditForm [name=id]").val();
		$('#paperItemEditWindow').window({href:"/paper/question/tolist?paperId="+id});
		$("#paperItemEditWindow").window({
    		onLoad :function(){
    			//回显数据
    			$("#paperquestionList-type").val("3");
    			$("#paperquestionList-paperId").val(id);
    		}
    	}).window("open");
	});
	
	function papersubmitForm(){
		if(!$('#paperEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		var paper = {};
		paper.id = $("#paperEditForm [name=id]").val();
		paper.subjectId = $("#paperEditForm [name=subjectId]").val();
		paper.tclassId = $("#paperEditForm [name=tclassId]").val();
		paper.teacherId = $("#paperEditForm [name=teacherId]").val();
		var datas = JSON.stringify(paper);
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/edit",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','修改成功!','info',function(){
						$("#paperEditWindow").window('close');
						$("#paperList").datagrid("reload");
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
