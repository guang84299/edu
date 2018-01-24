<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding:10px 10px 10px 10px">
	<form id="searchForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>搜索题目:</td>
	            <td><input class="easyui-textbox" type="text" name="keyword" data-options="min:1,max:99999999,precision:2,required:true" />
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchsubmitForm()">搜索</a>
	</div>
	<hr>
	<div id="searchResult">
		
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function searchsubmitForm(){
		//有效性验证
		if(!$('#searchForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var search = {};
		search.keyword = $("[name=keyword]").val();
		search.teacherId = $("[name=teacherId]").val();
	//	var datas = JSON.stringify(search);
		
		$.ajax({
			type: "post",
			data: search,
			url: "/question/search",
			//contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				//$.messager.alert('提示',data);
				$("#searchResult").html("");
				for(var i=0;i<data.length;i++)
				{
					var v = data[i];
					var html = "<div>" + v.context + "</div><hr>"
					$("#searchResult").append(html);
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
		
		
		
	}
	
</script>
