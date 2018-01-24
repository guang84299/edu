<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="questionMultiList" title="题库列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/question/list',method:'get',pageSize:30,toolbar:toolbar">
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
               	$.post("/question/delete",params, function(data){
          			if(data.state == 200){
          				$.messager.alert('提示','删除成功!',undefined,function(){
          					$("#questionMultiList").datagrid("reload");
          				});
          			}
          		}); 
        	    }
        	});
        }
    }];
</script>