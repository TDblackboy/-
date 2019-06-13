/**
 * 	异常访问
 */
$(function() {
	
	//loadExceptionData();//加载初始异常数据
	//dividePage();
	fenyeLoadExceptionData();//分页显示
});

//初始加载数据
function loadExceptionData(){
	$.ajax({
		type:"post",
		async:true,   
		url:"../ExceptionDataService",
		data:{"type":"all","value":"10"},//提取10条数据
		dataType:"json",
		success:function(data){
			$('#exception-tbody').html("");
			toHTMLQ(data);
		},
		error:function(){
			alert("无法查看全部数据");
		},
	});
}

//输入查询
function getException(type){

	if(type==1){
		var value=$("#search-input-exception").val();
		$.ajax({
			type:"post",
			async:true,   
			url:"../ExceptionDataService",
			data:{"type":type,"value":value},
			dataType:"json",
			success:function(data){
				$('#fenye_table_tbody').html("");
				toHTMLFenye(data);
				dividePage();
			},
			error:function(){
				alert("数据加载失败........");
			},
		});
	}
}

//将异常数据插入到html
function toHTMLQ(data){
	$.each(data,function(index,value){//遍历json数组
		//alert(value);
		var n=index+1;
		var td0="<td>"+n+"."+"</td>";
		var td1="<td>"+value['location']+"</td>";
		var td2="<td>"+value['persons']+"</td>";
		var td3="<td>"+value['datatime']+"</td>";
		var td4="";
		if(value['state']==0){
			td4="<td><span class='label label-success'>"+"正常"+"</td>";
		}else{
			td4="<td><span class='label label-danger'>"+"异常"+"</td>";
		}
		var one="<tr>"+td0+td1+td2+td3+td4+"</tr>";
		$("#exception-tbody").append(one);
	});
}

/****分页 ：每页条数，当前页数，前一页，后一页****/
//初始加载数据
function fenyeLoadExceptionData(){
	$.ajax({
		type:"post",
		async:true,   
		url:"../ExceptionDataService",
		data:{"type":"all","value":"0"},//提取10条数据
		dataType:"json",
		success:function(data){
			$('#fenye_table_tbody').html("");
			toHTMLFenye(data);
			dividePage();
		},
		error:function(){
			alert("无法查看全部数据");
		},
	});
}

function toHTMLFenye(data){
	$.each(data,function(index,value){//遍历json数组
		//alert(value);
		var n=index+1;
		var td0="<td>"+n+"."+"</td>";
		var td1="<td>"+value['location']+"</td>";
		var td2="<td>"+value['persons']+"</td>";
		var td3="<td>"+value['datatime']+"</td>";
		var td4="";
		if(value['state']==0){
			td4="<td><span class='label label-success'>"+"正常"+"</td>";
		}else{
			td4="<td><span class='label label-danger'>"+"异常"+"</td>";
		}
		var one="<tr>"+td0+td1+td2+td3+td4+"</tr>";
		$("#fenye_table_tbody").append(one);
	});
}

function dividePage(){
	/* $('#fenye_table').DataTable({
	      'paging'      : true,
	      'lengthChange': true,
	      'searching'   : false,
	      'ordering'    : false,
	      'info'        : false,
	      'autoWidth'   : true
	   })*/
}

/********图形展示分析结果********/

