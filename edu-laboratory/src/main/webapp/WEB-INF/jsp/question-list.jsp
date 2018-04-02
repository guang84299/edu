<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="easyui-datagrid" id="questionMultiList" title="题库列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/question/list',method:'get',pageSize:30,toolbar:'#tb'">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">题库ID</th>
        		<th data-options="field:'type',width:200,formatter:E3.formatQuestionType">类型</th>
            <th data-options="field:'context',width:200">题目</th>
            <th data-options="field:'answer',width:100,formatter:E3.formatQuestionAnswer">答案</th>
            <th data-options="field:'score',width:100">分数</th>
            <th data-options="field:'normalTime',width:100">答题时间</th>
            <th data-options="field:'knowledgePoint',width:100">知识点</th>
            <th data-options="field:'difficult',width:60,align:'center',formatter:E3.formatQuestionDifficult">困难度</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			<!-- Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px"> -->
			<select name="subjectId" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">学科</option>
				<c:forEach items="${subjects}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			</select>
			<select name="gradeId" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">年级</option>
				<c:forEach items="${grades}" var="val">
				      <option value="${val.id }">${val.name }</option>
				</c:forEach>
			</select>
			<select name="type" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">类型</option>
				<option value="1">判断</option>
				<option value="2">单选</option>
				<option value="3">多选</option>
				<option value="4">填空</option>
				<option value="5">主观</option>
			</select>
			<select name="difficult" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="-1">难度</option>
				<option value="0">简单</option>
				<option value="1">一般</option>
				<option value="2">困难</option>
			</select>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
	</div>
<div id="questionMultiEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/question/toedit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#questionMultiList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    $("#tb [iconCls='icon-add']").click(function(){
    		$(".tree-title:contains('新增题库')").parent().click();
    });
    $("#tb [iconCls='icon-edit']").click(function(){
	    	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一个才能编辑!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','只能选择一个!');
	    		return ;
	    	}
	    	
	    	$("#questionMultiEditWindow").window({
	    		onLoad :function(){
	    			//回显数据
	    			$("#questionEditForm1").hide();
	    			$("#questionEditForm2").hide();
	    			$("#questionEditForm3").hide();
	    			var data = $("#questionMultiList").datagrid("getSelections")[0];
	    			if(data.type == 1)
	 			{
	    				if(data.answer == '1')
	        				data.answer = 1;
	        			else
	        				data.answer = 0;	
	    				$("#questionEditForm1").form("load",data);
	    				$("#questionEditForm1").show();
	 			}
	    			else if(data.type == 2)
	    			{
	    				$("#questionEditForm2").form("load",data);
	    				$("#questionEditForm2").show();
	    			}
	    			else if(data.type == 3)
	    			{
	    				var answer = data.answer;
	        			$("#questionEditForm3").form("load",data);
	        			$("#questionEditForm3").show();
	        			if(answer != "" && answer.length > 0)
	      			{
	      				var answers = answer.split(",");
	      				for(var i=0;i<answers.length;i++)
	      				{
	      					$("#questionEditForm3 [name=answer-multi]").each(function(){ 
	      						if($(this).val() == answers[i])
	      						{
	      							$(this).attr("checked",true);
	      						}
	      					});
	      				}
	      			}
	    			}
	    			
	    		}
	    	}).window("open");
	});
    $("#tb [iconCls='icon-remove']").click(function(){
	    	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的题库吗？',function(r){
	    	    if (r){
	    	    	var params = {"ids":ids};
	           	$.post("/question/delete",params, function(data){
	      			if(data.state == 200){
	      				$.messager.alert('提示','删除成功!',undefined,function(){
	      					$("#questionMultiList").datagrid("reload");
	      				});
	      			}
	      		}); 
	    	    }
	    	});
	});
    
    $("#tb [iconCls='icon-search']").click(function(){
    		var subjectId = $("#tb [name='subjectId']").val();
    		var gradeId = $("#tb [name='gradeId']").val();
    		var type = $("#tb [name='type']").val();
    		var difficult = $("#tb [name='difficult']").val();
    		
	    	$('#questionMultiList').datagrid({url:'/question/list',    
	    		queryParams:{subjectIds:subjectId,gradeIds:gradeId,types:type,difficults:difficult, website:'/question/list' }
	    	});
	});
  
</script>