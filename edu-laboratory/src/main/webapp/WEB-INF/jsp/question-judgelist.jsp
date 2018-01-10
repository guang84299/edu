<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="questionJudgeList" title="题库列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/question/judgelist',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">题库ID</th>
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
<div id="questionJudgeEditWindow" class="easyui-window" title="编辑" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/question/toeditjudge'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#questionJudgeList");
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
        	$(".tree-title:contains('新增题库')").parent().click();
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
        	
        	$("#questionJudgeEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#questionJudgeList").datagrid("getSelections")[0];
        			if(data.answer)
        				data.answer = 1;
        			else
        				data.answer = 0;
        			$("#questionJudgeEditForm").form("load",data);
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
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的题库吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
               	$.post("/question/deletejudge",params, function(data){
          			if(data.state == 200){
          				$.messager.alert('提示','删除成功!',undefined,function(){
          					$("#questionJudgeList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    }
    ];
</script>