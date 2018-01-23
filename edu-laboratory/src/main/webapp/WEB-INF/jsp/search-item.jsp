<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <a class="easyui-linkbutton" onclick="importItems()">一键导入题库到索引库</a>
</div>
<script type="text/javascript">
	function importItems() {
		$.post("/search/importall",null,function(data){
			if(data.state == 200){
				$.messager.alert('提示','导入索引库成功！');
			} else {
				$.messager.alert('提示','导入索引库失败！');
			}
		});
	}
</script>