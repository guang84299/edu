<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="padding:10px 10px 10px 10px">
	<form id="tclassAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>学校:</td>
	            <td>
	            		<select name="schoolId" value=""  style="width:200px;height:32px">
				    <c:forEach items="${schools}" var="val">
				      <option value="${val.id }">${val.name }</option>
				     </c:forEach>
				    </select>
	            </td>
	        </tr>
	        <tr>
	            <td>年级:</td>
	            <td>
	            		<select name="gradeId" value="" style="width:200px;height:32px">
				    <c:forEach items="${grades}" var="val">
				      <option value="${val.id }">${val.name }</option>
				     </c:forEach>
				    </select>
	            </td>
	        </tr>
	        <tr>
	            <td>班级:</td>
	            <td>
	            		<select name="tclassId" value=""  style="width:200px;height:32px">
				    <c:forEach items="${tclasss}" var="val">
				      <option value="${val.id }">${val.name }</option>
				     </c:forEach> 
				    </select>
	            </td>
	        </tr>
	        <tr>
	            <td>学科:</td>
	            <td>
	            		<select name="subjectId" value=""  style="width:200px;height:32px">
				    <c:forEach items="${subjects}" var="val">
				      <option value="${val.id }">${val.name }</option>
				     </c:forEach>
				    </select>
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclassAddsubmitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclassAddclearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	$("[name=gradeId]").change(function(){
		var datas = {schoolId:$("[name=schoolId]").val(),gradeId:$("[name=gradeId]").val()};
		$.ajax({
			type: "post",
			data: datas,
			url: "/tsubject/getclass",
			/* contentType : "application/json;charset=UTF-8", */
			dataType : "json",
			success: function(data) { 
				var html = '';
				for(var i=0;i<data.length;i++)
				{
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
				}
				$("[name=tclassId]").html(html);
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
	});
	
	//提交表单
	function tclassAddsubmitForm(){
		//有效性验证
		if(!$('#tclassAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var tclass = {};
		tclass.schoolId = $("[name=schoolId]").val();
		tclass.gradeId = $("[name=gradeId]").val();
		tclass.tclassId = $("[name=tclassId]").val();
		tclass.subjectId = $("[name=subjectId]").val();
		tclass.teacherId = $("[name=teacherId]").val();
		var datas = JSON.stringify(tclass);
		$.ajax({
			type: "post",
			data: datas,
			url: "/tsubject/add",
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success: function(data) { 
				if(data.state == 200)
				{
					$.messager.alert('提示','新增成功!');
				}
				else
				{
					$.messager.alert('错误',"新增失败！");
				}
			}, 
			error: function(e) { 
				$.messager.alert('错误',"网络错误");
			} 
		});
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		/* alert($("#userAddForm").serialize());
		$.post("/user/add",$("#userAddForm").serialize(), function(data){
			if(data.state == 200){
				$.messager.alert('提示','新增用户成功!');
			}
		}); */
		
		
	}
	
	function tclassAddclearForm(){
		$('#tclassAddForm').form('reset');
	}
</script>
