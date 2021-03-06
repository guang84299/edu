<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<select name="subjectId" style="width:100px">
		<option value="0">学科</option>
		<c:forEach items="${subjects}" var="val">
	      <option value="${val.id }">${val.name }</option>
	     </c:forEach>
	</select>
	<select name="difficult"  style="width:100px">
		<option value="0">难度</option>
		<option value="1">简单</option>
		<option value="2">一般</option>
		<option value="3">困难</option>
	</select>
	Date From: <input class="easyui-datebox" style="width:100px" name="fromTime">
	To: <input class="easyui-datebox" style="width:100px" name="toTime">
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" name="search">Search</a>
</div>
<div class="ct-chart ct-perfect-fourth"></div>
<script>


 var load = function(params)
 {
	 $.post("/sta/checkstar",params, function(data){
			var datas = {labels:[],series:[[]]};
			for(var i=0;i<data.length;i++)
			{
				datas.labels.push(data[i].school);
				datas.series[0].push(data[i].star);
			}
		
			new Chartist.Bar('.ct-chart', datas);
			
		}); 
 }

	var params = {};
	load(params);
	
	var stringToDate  =  function(fDate){  
	    var fullDate = fDate.split("-");  
	    return new Date(fullDate[0], fullDate[1]-1, fullDate[2], 0, 0, 0);  
	  } 

	$("[name=search]").click(function(){
		var params = {};
		params.subjectId = $("[name=subjectId]").val();
		params.difficult = $("[name=difficult]").val();
		params.fromTime = $("[name=fromTime]").val();
		params.toTime = $("[name=toTime]").val();
		
		if(params.fromTime)
			params.fromTime = stringToDate(params.fromTime).getTime();
		if(params.toTime)
			params.toTime = stringToDate(params.toTime).getTime();
		
		load(params);
	});
</script>