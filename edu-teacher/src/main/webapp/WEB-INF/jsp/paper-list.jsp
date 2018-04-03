<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="paperList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/paper/list?teacherId=${teacher.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">作业ID</th>
            <th data-options="field:'subject',width:200">学科</th>
            <th data-options="field:'tclass',width:200">班级</th>
            <th data-options="field:'stateStr',width:200">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
            
            <th data-options="field:'subjectId',width:200,hidden:true" ></th>
            <th data-options="field:'tclassId',width:200,hidden:true"></th>
            <th data-options="field:'state',width:200,hidden:true"></th>
        </tr>
    </thead>
</table>
<div id="paperEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/paper/toedit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#paperList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增作业')").parent().click();
        }
    },{
        text:'编辑',
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
        	
        	$("#paperEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#paperList").datagrid("getSelections")[0];
        			$("#paperEditForm").form("load",data);        			
        		}
        	}).window("open");
        }
    }/* ,{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的班级吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
               	$.post("/tclass/delete",params, function(data){
          			if(data.state == 200){
          				$.messager.alert('提示','删除成功!',undefined,function(){
          					$("#paperList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    } */];
</script>