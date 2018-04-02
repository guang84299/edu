<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="studentList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/student/list?teacherId=${teacher.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'studentTclassId',width:60">ID</th>
        		<th data-options="field:'name',width:200">名字</th>
            <th data-options="field:'subject',width:200">学科</th>
            <th data-options="field:'tclass',width:200">班级</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
            
            <th data-options="field:'subjectId',width:200,hidden:true" ></th>
            <th data-options="field:'tclassId',width:200,hidden:true"></th>
            <th data-options="field:'state',width:200,hidden:true"></th>
        </tr>
    </thead>
</table>

<script>

    function getSelectionsIds(){
    	var itemList = $("#studentList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].studentTclassId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
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
               	$.post("/student/delete",params, function(data){
          			if(data.state == 200){
          				$.messager.alert('提示','删除成功!',undefined,function(){
          					$("#studentList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    }];
</script>