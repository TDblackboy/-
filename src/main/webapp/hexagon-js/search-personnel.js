/**
 * 	条件查询疏导人员js
 *  传参：type - name / location
 */

function getPersnonel(data){
	var key=data;
	var value=$("#search-input-monitor").val();
	//测试
	//alert(value);
	//alert(key);
	
	$.ajax({
		type:"post",//提交方式
		async:true,   //是否为异步请求
		url:"../MonitorService",//提交到servlet的地址
		data:{"type":key,"value":value},//向后台传的参数 
		dataType:"json",//后台返回的数据类型
		success:function(data){//后台返回成功是执行的函数，data是返回值
			//alert("ok")//成功时执行函数的代码 
			$('#personnel-tbody').html("");
			toHTML(data);
		},
		error:function(){//错误时执行的代码
			alert("无法查看全部数据");
		},
	});
}

function toHTML(data){
	$.each(data,function(index,value){//遍历json数组
		//alert(value['name']);
		//alert(index);
		var td1="<td>"+value['id']+"</td>";
		var td2="<td>"+value['name']+"</td>";
		var td3="<td>"+value['tel']+"</td>";
		var td4="";
		if(value['state']==0){
			td4="<td><span class='label label-success'>"+"未工作"+"</td>";
		}else{
			td4="<td><span class='label label-danger'>"+"工作中"+"</td>";
		}
		var td5="<td>"+value['location']+"</td>";
		var one="<tr>"+td1+td2+td3+td4+td5+"</tr>";
		//alert(one);
		$("#personnel-tbody").append(one);
	});
}