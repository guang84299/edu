<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="easyui-datagrid" id="itemList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/teacher/list',method:'get',pageSize:30,toolbar:'#tb'">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">教师ID</th>
            <th data-options="field:'name',width:100">教师姓名</th>
            <!-- <th data-options="field:'password',width:100">用户密码</th> -->
            <th data-options="field:'school',width:100">学校</th>
            <th data-options="field:'grade',width:100">年级</th>
            <th data-options="field:'subject',width:100">学科</th>
            <th data-options="field:'tclass',width:100">任教班级</th>
            <th data-options="field:'paperState',width:150,align:'center'">批改作业数 / 布置作业数</th>
            <th data-options="field:'stateStr',width:60,align:'center'">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  id="t_edit" plain="true">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" id="t_disable" plain="true">禁用</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" id="t_enable" plain="true">启用</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" id="t_check" plain="true">审核</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" id="t_refuse" plain="true">拒绝</a>
		</div>
		<div>
			<!-- Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px"> -->
			<select name="schoolId" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">学校</option>
				<c:forEach items="${schools}" var="val">
				      <option value="${val.id }">${val.name }</option>
				</c:forEach>
			</select>
			<select name="gradeId" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">年级</option>
				<c:forEach items="${grades}" var="val">
				      <option value="${val.id }">${val.name }</option>
				</c:forEach>
			</select>
			<select name="subjectId" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="0">学科</option>
				<c:forEach items="${subjects}" var="val">
			      <option value="${val.id }">${val.name }</option>
			     </c:forEach>
			</select>
			
			
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
	</div>
<div id="itemEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/teacher/toedit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    $("#t_disable").click(function(){
     	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中用户!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定禁用ID为 '+ids+' 的教师吗？',function(r){
	    	    if (r){
	    	    	var params = {"ids":ids};
	            	$.post("/teacher/disable",params, function(data){
	        			if(data.state == 200){
	        				$.messager.alert('提示','禁用教师成功!',undefined,function(){
	        					$("#itemList").datagrid("reload");
	        				});
	        			}
	        		});
	    	    }
	    	});
	});
    
    $("#t_enable").click(function(){
     	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中用户!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定启用ID为 '+ids+' 的教师吗？',function(r){
	    	    if (r){
	    	    	var params = {"ids":ids};
	            	$.post("/teacher/enable",params, function(data){
	        			if(data.state == 200){
	        				$.messager.alert('提示','启用教师成功!',undefined,function(){
	        					$("#itemList").datagrid("reload");
	        				});
	        			}
	        		});
	    	    }
	    	});
	});
    
    $("#t_check").click(function(){
     	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中用户!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定审核ID为 '+ids+' 的教师通过吗？',function(r){
	    	    if (r){
	    	    	var params = {"ids":ids};
	            	$.post("/teacher/check",params, function(data){
	        			if(data.state == 200){
	        				$.messager.alert('提示','审核教师成功!',undefined,function(){
	        					$("#itemList").datagrid("reload");
	        				});
	        			}
	        		});
	    	    }
	    	});
	});
    
    $("#t_refuse").click(function(){
     	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中用户!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定拒绝ID为 '+ids+' 的教师通过吗？',function(r){
	    	    if (r){
	    	    	var params = {"ids":ids};
	            	$.post("/teacher/refuse",params, function(data){
	        			if(data.state == 200){
	        				$.messager.alert('提示','拒绝教师成功!',undefined,function(){
	        					$("#itemList").datagrid("reload");
	        				});
	        			}
	        		});
	    	    }
	    	});
	});
    
    $("#tb [iconCls='icon-search']").click(function(){
		var subjectId = $("#tb [name='subjectId']").val();
		var gradeId = $("#tb [name='gradeId']").val();
		var schoolId = $("#tb [name='schoolId']").val();
		
    	$('#itemList').datagrid({url:'/teacher/list',    
    		queryParams:{subjectIds:subjectId,gradeIds:gradeId,schoolIds:schoolId, website:'/teacher/list' }
    	});
});
    
    
  
</script>