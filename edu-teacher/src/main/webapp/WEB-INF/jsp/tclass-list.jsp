<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="tclassList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/tclass/list?teacherId=${teacher.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">班级ID</th>
            <th data-options="field:'name',width:200">班级名称</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="tclassEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/tclass/toedit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#tclassList");
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
        	$(".tree-title:contains('新增班级')").parent().click();
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
        	
        	$("#tclassEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#tclassList").datagrid("getSelections")[0];
        			$("#tclassEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
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
          					$("#tclassList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    }];
</script>