<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="paperList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/paper/list?studentId=${student.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'paperId',width:60">作业ID</th>
            <th data-options="field:'subject',width:200">学科</th>
            <th data-options="field:'tclass',width:200">班级</th>
            <th data-options="field:'stateStr',width:200">完成度</th>
            <th data-options="field:'checkStateStr',width:200">批改进度</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <!-- <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th> -->
        </tr>
    </thead>
</table>
<div id="paperEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/paper/toanswer'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#paperList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].paperId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'快速答题',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('做作业')").parent().click();
        }
    },{
        text:'开始答题',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个才能答题!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个!');
        		return ;
        	}
        	$('#paperEditWindow').window({href:"/paper/toanswer?paperId="+ids});
        	$("#paperEditWindow").window({
        		onClose :function()
        		{
        			$("#paperEditWindow").html("");
        		}
        		/* onLoad :function(){
        			//回显数据
        			var data = $("#paperList").datagrid("getSelections")[0];
        			$("#paperEditForm").form("load",data);        			
        		} */
        	}).window("open");
        }
    }];
</script>