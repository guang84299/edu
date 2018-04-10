<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<div style="padding:10px 10px 10px 10px">
	<form id="paperAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
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
	         <tr>
	            <td>班级:</td>
	            <td>
	            	<select name="tclassId" value="" style="width:200px;height:32px">
			    <c:forEach items="${teacherSubjects}" var="val">
			      <option value="${val.id }">${val.tclass }</option>
			     </c:forEach>
			    </select>
	            </td>
	        </tr>
	        
	        
	      
	    </table>
	    <input type="hidden" name="teacherId" value="${teacher.id}"/>
	    <input type="hidden" name="schoolId" value="${teacher.schoolId}"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="tclassAddsubmitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">

	$("[name=subjectId]").change(function(){
		var datas = {teacherId:$("[name=teacherId]").val(),schoolId:$("[name=schoolId]").val(),subjectId:$("[name=subjectId]").val()};
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/getTeacherSubject",
			/* contentType : "application/json;charset=UTF-8", */
			dataType : "json",
			success: function(data) { 
				var html = '';
				for(var i=0;i<data.length;i++)
				{
					html += '<option value="' + data[i].id + '">' + data[i].tclass + '</option>';
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
		if(!$('#paperAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var tclass = {};
		tclass.subjectId = $("[name=subjectId]").val();
		tclass.teacherSubjectId = $("[name=tclassId]").val();
		tclass.teacherId = $("[name=teacherId]").val();
		var datas = JSON.stringify(tclass);
		$.ajax({
			type: "post",
			data: datas,
			url: "/paper/add",
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
		
	}
	
	function tclassAddclearForm(){
		$('#paperAddForm').form('reset');
	}
</script>
