<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="studenttclassList" title="科目列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/student_tclass/list?studentId=${student.id}',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">科目ID</th>
            <th data-options="field:'tclassId',width:200">科目代码</th>
            <th data-options="field:'created',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<script>

    function getSelectionsIds(){
    	var itemList = $("#studenttclassList");
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
        	$(".tree-title:contains('新增科目')").parent().click();
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
               	$.post("/student_tclass/delete",params, function(data){
          			if(data.state == 200){
          				$.messager.alert('提示','删除成功!',undefined,function(){
          					$("#studenttclassList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    }];
</script>