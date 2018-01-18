<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<div style="padding:5px" id="paper-check" data-paid="${paid}">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="paperchecksubmitForm()">批改</a>
	</div>
</div>

<script type="text/javascript">

	function paperchecksubmitForm(){		
		var paid = $("#paper-check").attr("data-paid");
		var datas = {};
		datas.paperAnswerId = parseInt(paid);
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/check",
			dataType : "json",//回调
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','批改成功!','info',function(){
						$("#papercheckEditWindow").window('close');
						$("#papercheckList").datagrid("reload");
					});
				}
				else
				{
					$.messager.alert('错误',"批改失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});

	}
</script>
