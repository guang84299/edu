<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="papercheckList" title="作业列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/paper/check/list?teacherId=${teacher.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">作业ID</th>
        		<th data-options="field:'studentName',width:200">学生</th>
            <th data-options="field:'subject',width:200">学科</th>
            <th data-options="field:'tclass',width:200">班级</th>
            <th data-options="field:'stateStr',width:200">答题进度</th>
            <th data-options="field:'checkStateStr',width:200">批改进度</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <!-- <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th> -->
            
            <th data-options="field:'subjectId',width:200,hidden:true" ></th>
            <th data-options="field:'tclassId',width:200,hidden:true"></th>
            <th data-options="field:'state',width:200,hidden:true"></th>
        </tr>
    </thead>
</table>
<div id="papercheckEditWindow" class="easyui-window" title="批改" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/paper/tocheck'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#papercheckList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'批改',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个!');
        		return ;
        	}
 
        	$('#papercheckEditWindow').window({href:"/paper/tocheck?paperAnswerId="+ids});
        	$("#papercheckEditWindow").window({
        		/* onLoad :function(){
        			//回显数据
        			var data = $("#papercheckList").datagrid("getSelections")[0];
        			$("#paperEditForm").form("load",data);        			
        		} */
        	}).window("open");
        }
    }];
</script>