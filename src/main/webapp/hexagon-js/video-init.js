/**
 * 		
 */
$(function() {
	
	flotRealTimeData();
	pie();//加载环状图表
	line();//加载线性图表
	
});


function flotRealTimeData() {
	// We use an inline data source in the example, usually data would
	// be fetched from a server
	var data = [], totalPoints = 100

	function getRandomData() {

		if (data.length > 0)
			data = data.slice(1)

			// Do a random walk
		while (data.length < totalPoints) {

			var prev = data.length > 0 ? data[data.length - 1] : 50, y = prev
					+ Math.random() * 10 - 5

			if (y < 0) {
				y = 0
			} else if (y > 100) {
				y = 100
			}

			data.push(y)
		}

		// Zip the generated y values with the x values
		var res = []
		for (var i = 0; i < data.length; ++i) {
			res.push([ i, data[i] ])
		}

		return res
	}

	var interactive_plot = $.plot('#line-chart-flow #interactive', [ getRandomData() ], {
		grid : {
			borderColor : '#f3f3f3',
			borderWidth : 1,
			tickColor : '#f3f3f3'
		},
		series : {
			shadowSize : 0, // Drawing is faster without shadows
			color : '#3c8dbc'
		},
		lines : {
			fill : true, // Converts the line chart to area chart
			color : '#3c8dbc'
		},
		yaxis : {
			min : 0,
			max : 100,
			show : true
		},
		xaxis : {
			show : true
		}
	})

	var updateInterval = 500 // 每隔x毫秒提取数据

	var realtime = 'on' // If == to on then fetch data every x seconds. else
						// stop fetching
	function update() {

		interactive_plot.setData([ getRandomData() ])

		// Since the axes don't change, we don't need to call plot.setupGrid()
		// 由于坐标轴不变，我们不需要调用plot.setupgrid（）。
		interactive_plot.draw()
		if (realtime === 'on')
			setTimeout(update, updateInterval)
	}

	// INITIALIZE REALTIME DATA FETCHING
	if (realtime === 'on') {
		update()
	}

	// REALTIME TOGGLE
	$('#realtime .btn').click(function() {
		if ($(this).data('toggle') === 'on') {
			realtime = 'on'
		} else {
			realtime = 'off'
		}
		update()
	})

	/*
	 * END INTERACTIVE CHART
	 */
}

//环状图表
function pie(){
	//请求数据
	$.ajax({
		type:"post",
		async:true,   
		url:"../ExceptionDataService",
		data:{"type":"3"},//后台获取分析数据
		dataType:"json",
		success:function(datas){
			//alert(datas[0]['count']);
			pieDisplay(datas);//调用相关的图形展示方法
		},
		error:function(){
			alert("数据加载失败.....");
		},
	});
	
}

//装载pie数据
function pieDisplay(result){
	var pieChartCanvas = $('#pieChart').get(0).getContext('2d');
    var pieChart = new Chart(pieChartCanvas);
    var PieData = [
      {
        value    : result[0]['count'],
        color    : '#f56954',
        highlight: 'red',
        label    : result[0]['name']+"占"+result[0]['percent']
      },
      {
        value    : result[1]['count'],
        color    : '#00a65a',
        highlight: '#006600',
        label    : result[1]['name']+"占"+result[1]['percent']
      },
      {
        value    : result[2]['count'],
        color    : '#f39c12',
        highlight: '#ff6600',
        label    : result[2]['name']+"占"+result[2]['percent']
      },
      {
        value    : result[3]['count'],
        color    : '#3399cc',
        highlight: '#003366',
        label    : result[3]['name']+"占"+result[3]['percent']
      }
    ];
    var pieOptions     = {
      //Boolean - Whether we should show a stroke on each segment
      segmentShowStroke    : true,
      //String - The colour of each segment stroke
      segmentStrokeColor   : '#fff',
      //Number - The width of each segment stroke
      segmentStrokeWidth   : 2,
      //Number - The percentage of the chart that we cut out of the middle
      percentageInnerCutout: 50, // This is 0 for Pie charts
      //Number - Amount of animation steps
      animationSteps       : 100,
      //String - Animation easing effect
      animationEasing      : 'easeOutBounce',
      //Boolean - Whether we animate the rotation of the Doughnut
      animateRotate        : true,
      //Boolean - Whether we animate scaling the Doughnut from the centre
      animateScale         : false,
      //Boolean - whether to make the chart responsive to window resizing
      responsive           : true,
      // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
      maintainAspectRatio  : true,
      //String - A legend template
      legendTemplate       : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
    };
    //Create pie or douhnut chart
    // You can switch between pie and douhnut using the method below.
    pieChart.Doughnut(PieData, pieOptions);
}

//线性图表
function line(){
	$.ajax({
		type:"post",
		async:true,   
		url:"../ExceptionDataService",
		data:{"type":"4"},
		dataType:"json",
		success:function(datas){
			lineDisplay(datas);
		},
		error:function(){
			alert("线性图加载失败......");
		},
	});
	
}

//装载line数据
function lineDisplay(datas) {

	//105 - 1； 106-2；111 -3；112-4；
	var p1 = [], p2 = [], p3 = [], p4 = []
	var index = 0;
	for (var i = 0; i < 12; i++) {
		p1.push([ datas[i]['p' + i], datas[i]['number' + i] ])//0-11;
		p2.push([ datas[i + 12]['p' + (i + 12)],datas[i + 12]['number' + (i + 12)] ])//12-23;
		p3.push([ datas[i + 24]['p' + (i + 24)],datas[i + 24]['number' + (i + 24)] ])//23-35;
		p4.push([ datas[i + 36]['p' + (i + 36)],datas[i + 36]['number' + (i + 36)] ])//36-47;
	}

	var line_data1 = {
		data : p1,
		color : '#00a65a'
	}
	var line_data2 = {
		data : p2,
		color : '#f39c12'
	}

	var line_data3 = {
		data : p3,
		color : 'red'
	}
	var line_data4 = {
		data : p4,
		color : '#00c0ef'
	}

	$.plot('#line-chart-result', [ line_data1, line_data2, line_data3, line_data4 ], {
		grid : {
			hoverable : true,
			borderColor : '#f3f3f3',
			borderWidth : 1,
			tickColor : '#f3f3f3'
		},
		series : {
			shadowSize : 0,
			lines : {
				show : true
			},
			points : {
				show : true
			}
		},
		lines : {
			fill : false,
			color : ['#3c8dbc', '#f56954', 'blue', 'yellow']
		},
		yaxis : {
			show : true
		},
		xaxis : {
			show : true
		}
	})
	
	//Initialize tooltip on hover
	$('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
		position : 'absolute',
		display : 'none',
		opacity : 0.8
	}).appendTo('body')
	
	$('#line-chart-result').bind('plothover',function(event, pos, item) {
				if (item) {
					var x = item.datapoint[0].toFixed(2)
					var y = item.datapoint[1].toFixed(2)
					x=parseInt(x)
					y=parseInt(y)
					//item.series.label + ' of ' + x + ' = ' + y
					$('#line-chart-tooltip').html( x + ' 月： ' + y + '次').css({
						top : item.pageY + 5,
						left : item.pageX + 5
					}).fadeIn(200)
				} else {
					$('#line-chart-tooltip').hide()
				}
	})

}

