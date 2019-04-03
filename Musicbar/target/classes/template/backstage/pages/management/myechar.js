//数据统计
$(function(){
layui.use(['form','element'],function(){
	let form = layui.form;
	let resVal;//全局变量
	let formerVal;
	let date = (new Date()).format("yyyy年MM月dd日hh小时mm分ss秒");
	//综合统计图
	let Comprehensive = echarts.init(document.getElementById('comprehensive'));
	function mainTotal(year){
		Comprehensive.showLoading();
		$.post("manageQueryTotal",{"year":year},function(res){
			Comprehensive.hideLoading();
			res =JSON.parse(res).data;
			let legendData = (function(res){
				var arr = new Array();
				let orderTotal = 0;
				let moneyTotal = 0;
				let numTotal = 0;
				for(i in res){
					if(typeof(res[i].orderTotal)=="undefined"){
						res[i].orderTotal = 0;
					}
					if(typeof(res[i].moneyTotal)=="undefined"){
						res[i].moneyTotal = 0;
					}
					if(typeof(res[i].numTotal)=="undefined"){
						res[i].numTotal = 0;
					}
					orderTotal += Number(res[i].orderTotal);
					moneyTotal += Number(res[i].moneyTotal);
					numTotal += Number(res[i].numTotal);
				}
				arr.push("");
				arr.push("");
				arr.push("销售量(共"+numTotal+"件)");
				arr.push("营业额(共"+moneyTotal+"元)");
				arr.push("订单数(共"+orderTotal+"个)");
				return arr;
			})(res);
			Comprehensive.setOption ({
				title: {
					text:"数据截止"+date,
					x : "center",
					textStyle: {
				        fontSize: 14,
				        color: '#c9a655'          // 主标题文字颜色
				    },
				},
				tooltip : {
				    trigger: 'axis'
				},
				toolbox: {
				    show : true,
				    x : 'right',
				    feature : {
				        mark : {show: true},
				        magicType : {show: true, type: ['line', 'bar']},
				        saveAsImage : {show: true}
				    }
				},
				legend:{
					data:legendData
					//data :['','','销售量(件)','营业额(元)','订单数(个)']
				},
				calculable : true,
				xAxis : [
				    {
				        type : 'category',
				        boundaryGap : false,
				        data : (function(res){
				        	let str = new Array();
				        	for(i in res){
				        		str.unshift(res[i].startTime);
				        	}
				        	return str;
				        })(res)
				    }
				],
				yAxis : [
				    {	name : "销售量(件)",
				        type : 'value',
				        position :'left',
				        axisLabel: {
				            formatter: '{value}',
				            textStyle:{
				                color:'#0B438B'
				            }
				        }
				   },
				   {	name : '营业额(元)',
				        type : 'value',
				        position :'right',
				        axisLabel: {
				            formatter: '{value}',
				            textStyle:{
				                color:'#ffb980'
				            }
				        }
				   },
				   {	name : '订单数(个)',
				        type : 'value',
				        position :'right',
				        offset : 80,
				        axisLabel: {
				            formatter: '{value}',
				            textStyle:{
				                color:'#b2d8f4'
				            }
				        }
				    }
				],
				series : [
				    {
				        name:legendData[2],
				        type:'line',
				        //stack: '总量',
				        yAxisIndex: 0,
				        itemStyle : {  
				            normal : {  
				                color:'#0B438B',  //圈圈的颜色
				                lineStyle:{  
				                    color:'#0B438B'  //线的颜色
				                }  
				            }  
				        },
				        data:(function(res){
				        	let str = new Array();
				        	for(i in res){
				        		if(typeof(res[i].numTotal)=="undefined"){
				        			res[i].numTotal = 0;
				        		}
				        		str.unshift(res[i].numTotal);
				        	}
				        	return str;
				        })(res)
				    },
				    {
				        name:legendData[3],
				        type:'line',
				        //stack: '总量',
				        yAxisIndex: 1,
				        itemStyle : { 
				            normal : {  
				                color:'#ffb980',  //圈圈的颜色
				                lineStyle:{  
				                    color:'#ffb980'  //线的颜色
				                }  
				            }  
				        },
				        data:(function(res){
				        	let str = new Array();
				        	for(i in res){
				        		if(typeof(res[i].moneyTotal)=="undefined"){
				        			res[i].moneyTotal = 0;
				        		}
				        		str.unshift(res[i].moneyTotal);
				        	}
				        	return str;
				        })(res)
				    },
				    {
				        name:legendData[4],
				        type:'line',
				        //stack: '总量',
				        yAxisIndex: 2,
				        itemStyle : {  
				            normal : {  
				                color:'#b2d8f4',  //圈圈的颜色
				                lineStyle:{  
				                    color:'#b2d8f4'  //线的颜色
				                    }  
				                }  
				            },
				            data:(function(res){
					        	let str = new Array();
					        	for(i in res){
					        		if(typeof(res[i].orderTotal)=="undefined"){
					        			res[i].orderTotal = 0;
					        		}
					        		str.unshift(res[i].orderTotal);
					        	}
					        	return str;
					        })(res)
				        }
				    ]
				},true)
		}).error(function(xhr,errorText,errorType){
			alert("加载失败");
		})
	}
	//默认加载
	mainTotal(null);
	
	//统计子项
	Comprehensive.on('click', function (param) {
		if(param.name.indexOf("-") < 0){
			if(param.name.indexOf("月") >=0 | isNaN(param.name)){
				//未执行代码，防止报错			
			}else {
				mainTotal(param.name);
			}
		}else{
			if(param.name.split("-").length == 3){
				//未执行代码，防止报错	
			}else if(param.seriesName.indexOf("销售量") <= -1 && param.name.split("-").length != 3){
	        	Comprehensive.showLoading();
	        	$.post("manageQueryTotal",{
	        		"month" : param.name
	        	},function (res){
	        		Comprehensive.hideLoading();
	        		res =JSON.parse(res).data;
	        		let legendData = (function(res){
	    				var arr = new Array();
	    				let orderTotal = 0;
	    				let moneyTotal = 0;
	    				let numTotal = 0;
	    				for(i in res){
							if(typeof(res[i].moneyTotal)=="undefined"){
	    						res[i].moneyTotal = 0;
	    					}
	    					if(typeof(res[i].moneyTotal)=="undefined"){
	    						res[i].moneyTotal = 0;
	    					}
	    					orderTotal += Number(res[i].orderTotal);
	    					moneyTotal += Number(res[i].moneyTotal);
	    				}
	    				arr.push("");
	    				arr.push("");
	    				if(param.seriesName.indexOf("营业额") > -1){
	    					arr.push("营业额(共" + moneyTotal+")");
	    				}else{
	    					arr.push("订单数(共" + orderTotal+")");
	    				}
	    				return arr;
	    			})(res);
	        		Comprehensive.setOption ({
	        			title: {
	        				text:"数据截止"+date,
	        				x : "center",
	        				textStyle: {
	        			        fontSize: 14,
	        			        color: '#c9a655'          // 主标题文字颜色
	        			    },
	        			},
	        			tooltip : {
	        			    trigger: 'axis'
	        			},
	        			toolbox: {
	        			    show : true,
	        			    x : 'right',
	        			    feature : {
	        			        mark : {show: true},
	        			        magicType : {show: true, type: ['line', 'bar']},
	        			        saveAsImage : {show: true}
	        			    }
	        			},
	        			legend: {
	        			    data:legendData
	        			},
	        			calculable : true,
	        			xAxis : [
	        			    {
	        			        type : 'category',
	        			        boundaryGap : false,
	        			        data : (function(res){
	        			        	let str = new Array();
	        			        	for(i in res){
	        			        		str.unshift(res[i].startTime);
	        			        	}
	        			        	return str;
	        			        })(res),
	        			    }
	        			],
	        			yAxis : [
	        			    {	name : param.seriesName.indexOf("营业额") > -1 ? "营业额(元)" : "订单数(个)",
	        			        type : 'value',
	        			        position :'left',
	        			        axisLabel: {
	        			            formatter: '{value}',
	        			            textStyle:{
	        			                color:(function(){
	        			                	return param.seriesName.indexOf("营业额") > -1 ?  '#ffb980' : '#b2d8f4';
	        			                })(),
	        			            }
	        			        }
	        			   }
	        			],
	        			series : [
	        			    {
	        			        name: legendData[2],
	        			        type:'line',
	        			        //stack: '总量',
	        			        //yAxisIndex: 0,
	        			        itemStyle : {  
	        			            normal : {  
	        			                color : (function(){
	        			                			return param.seriesName.indexOf("营业额") > -1 ?  '#ffb980' : '#b2d8f4';
	        			                		})(),  //圈圈的颜色
	        			                lineStyle:{  
	        			                    color:(function(){
			            			                	return param.seriesName.indexOf("营业额") > -1 ?  '#ffb980' : '#b2d8f4';
			            			                })() //线的颜色
	        			                }  
	        			            }  
	        			        },
	        			        data:(function(res){
	        			        	let str = new Array();
	        			        	if(param.seriesName.indexOf("营业额") > -1){
	        			        		for(i in res){
	            			        		if(typeof(res[i].moneyTotal)=="undefined"){
	            			        			res[i].moneyTotal = 0;
	            			        		}
	            			        		str.unshift(res[i].moneyTotal);
	            			        	}
	        			        	}else{
	        			        		for(i in res){
	            			        		if(typeof(res[i].orderTotal)=="undefined"){
	            			        			res[i].orderTotal = 0;
	            			        		}
	            			        		str.unshift(res[i].orderTotal);
	            			        	}
	        			        	}
	        			        	return str;
	        			        })(res)
	        			    }
	        			  ]
	        			},true)
	        	}).error(function(xhr,errorText,errorType){
	        		alert("加载失败");
	        	})
	        }else if(param.seriesName.indexOf("销售量") >= 0 && param.name.split("-").length != 3){
	        	$.ajax({
		                type : "post", 
		                url : "query_num", 
		                data : {"month" : param.name},
		                dataType : "json",
		                success : function (res){
		                	Comprehensive.hideLoading();
		            		res =res.data;
		            		resVal = res;
		            		$("#num").css("display","");
		            		$("#num select").html("");
		            		$("#num dl").html("");
		            		let arr = new Array();
		            		$("#num select").html("");
		                	$("#num dl").html("");
		                	$("#num").removeAttr("hidden");
		            		for(i in res){
		            			if(arr.indexOf(res[i].typeName) == -1){
		            				arr.push(res[i].typeName);
		            				$("#num select").append("\
		        							<option value=\'"+res[i].typeName+"\'>"+res[i].typeName+"</option>\
		                					");
		        					$("#num dl").append("\
		        					<dd value=\'"+res[i].typeName+"\'>"+res[i].typeName+"</dd>\
		        					")
		            			}
		            		}
		            		form.render();
		            		let legendData = (function(res){
		        				var arr = new Array();
		        				let goodsNum = 0;
		        				for(i in res){
		        					if($("#num select").val()==res[i].typeName){
		        						goodsNum += Number(res[i].goodsNum);
		        					}
		        				}
		        				arr.push("");
		        				arr.push("");
		        				arr.push("销售量(共" + goodsNum+")");
		        				return arr;
		        			})(res);
		            		Comprehensive.setOption ({
		            			title: {
		            				text:"数据截止"+date,
		            				x : "center",
		            				textStyle: {
		            			        fontSize: 14,
		            			        color: '#c9a655'          // 主标题文字颜色
		            			    },
		            			},
		            			tooltip : {
		            			    trigger: 'axis'
		            			},
		            			legend: {
		            				selectedMode:false,
		            			    data:legendData
		            			},
		            			calculable : true,
		            			xAxis : [
		            			    {
		            			        type : 'category',
		            			        boundaryGap : true,
		            			        data : (function(res){
		            			        	let str = new Array();
		            			        	for(i in res){
		            			        		let val = $("#num select").val();
		            			        		if(val == res[i].typeName){
		            			        			str.unshift(res[i].goodsName);
		            			        		}
		            			        	}
		            			        	return str;
		            			        })(res),
		            			    }
		            			],
		            			yAxis : [
		            			    {	name : "销售量",
		            			        type : 'value',
		            			        position :'left',
		            			        axisLabel: {
		            			            formatter: '{value}',
		            			            textStyle:{
		            			                color : '#0B438B'
		            			            }
		            			        }
		            			   }
		            			],
		            			series : [
		            			    {
		            			        name: legendData[2],
		            			        type:'bar',
		            			        clickable:false,
		            			        //stack: '总量',
		            			        //yAxisIndex: 0,
		            			        itemStyle : {
		            			            normal : {
		            			                color : '#0B438B',  //圈圈的颜色
		            			                lineStyle:{  
		            			                    color: '#0B438B', //线的颜色
		            			                }  
		            			            }  
		            			        },
		            			        data:(function(res){
		            			        	let str = new Array();
		            			        		for(i in res){
		            			        			let val = $("#num select").val();
		            			        			if(val == res[i].typeName){
		            			        				str.unshift(res[i].goodsNum);
		            			        			}
		                			        	}
		            			        	return str;
		            			        })(res)
		            			    }
		            			  ]
		            			},true)
		            	},
	        	error : function(xhr,errorText,errorType){
	        				alert("加载失败");
	        			}
	          }); 
	        }
		}
    });
	//销售量统计
    form.on('select(typeName)',function(data){
    	let val = data.value;
    	let legendData = (function(res){
			var arr = new Array();
			let goodsNum = 0;
			for(i in res){
				if(val==res[i].typeName){
					goodsNum += Number(res[i].goodsNum);
				}
			}
			arr.push("");
			arr.push("");
			arr.push("销售量(共" + goodsNum+")");
			return arr;
		})(resVal);
    	Comprehensive.setOption ({
    		legend: {
			    data:legendData
			},
    		series : [{
    			name: legendData[2],
			 	data:(function(res){
		        	let str = new Array();
		        		for(i in res){
		        			if(val == res[i].typeName){
		        				str.unshift(res[i].goodsNum);
		        			}
		        	}
		        	return str;
		        })(resVal)
    		}]
    	})
    });
    //往年对比,数据统计，2年对比下拉
    let first =  $("#formerYears").html();
    form.on('select(formerYears)',function(data){
    	$("#formerYears").html(first);
    	$("#num").attr("hidden","hidden");
    	form.render();
    	let val = data.value;
    	if(val == "数据统计"){
    		mainTotal(null);
    	}else if(val == "往年对比"){
    		former(val);
    		formerYears();
    	}else if(val == "往年月对比"){
    		former(val);
    		$("#formerYears select:eq(2) option").each(function(index,ele){
    			if(Number($(ele).val()) <= Number($("#formerYears select:eq(1)").val())){
    				$(ele).attr("disabled",true);
    			}
    			if(Number($(ele).val()) == Number($("#formerYears select:eq(1)").val()) + 1 ){
    				$(ele).prop("selected",true);
    			}
    		});
    		form.render();
    		towYears();
    	}
    })
    
    //往年对比换种类
    form.on('select(description)',function(data){
		description = data.value;
		let minTime = $("#formerYears select:eq(1)").val();
    	let maxTime = $("#formerYears select:eq(2)").val();
    	if($("#formerYears select:eq(0)").val().indexOf("往年月对比") < 0){
    		backCallFormerYears(formerVal,minTime,maxTime,description);
    	}else{
    		backCallTowYears(formerVal,minTime,maxTime,description);
    	}
    })
    //切换开始年
    form.on('select(startTime)',function(data){
    	description = data.value;
    	$("#formerYears select:eq(2) option").each(function(index,ele){
    		if($("#formerYears select:eq(0)").val().indexOf("往年月对比") < 0){
    			if(Number($(ele).val()) < Number(description)){
        			$(ele).attr("disabled",true);
            	}else{
            		$(ele).removeAttr("disabled");
            	}
    		}else{
    			if(Number($(ele).val()) <= Number(description)){
        			$(ele).attr("disabled",true);
            	}else{
            		$(ele).removeAttr("disabled");
            	}
    		}
    		
    		form.render();
    	})
    })
    //切换结束年，发起请求
    form.on('select(endTime)',function(data){
    	if($("#formerYears select:eq(0)").val().indexOf("往年月对比") < 0){
    		formerYears();
    	}else{
    		towYears();
    	}
    	
    })
    function former(val){
    	$("#formerYears option").each(function(index,ele){
    		if($(ele).val() == val){
    			$(ele).prop("selected",true);
    			return;
    		}
    	}) 
    	$("#formerYears").append('<div class="layui-input-inline layui-col-md2 layui-col-md-offset1">\
						<select lay-filter="startTime" name="cateid" >\
						</select>\
						<div class="layui-unselect layui-form-select">\
							<div class="layui-select-title">\
								<input type="text" placeholder="2019年3月" value="2019年3月" readonly="" class="layui-input layui-unselect"><i class="layui-edge">\
								</i>\
							</div>\
							<dl class="layui-anim layui-anim-upbit" style="">\
							</dl>\
						</div>\
					</div>\
					<div class="layui-input-inline layui-col-md2">\
						<select lay-filter="endTime" name="cateid" >\
						</select>\
						<div class="layui-unselect layui-form-select">\
							<div class="layui-select-title">\
								<input type="text" placeholder="2019年3月" value="2019年3月" readonly="" class="layui-input layui-unselect"><i class="layui-edge">\
								</i>\
							</div>\
							<dl class="layui-anim layui-anim-upbit" style="">\
							</dl>\
						</div>\
					</div>\
					<div class="layui-input-inline layui-col-md2 layui-col-md-offset3">\
						<select lay-filter="description" name="cateid" >\
			    			<option >销售量</option>\
							<option >营业额</option>\
    						<option >订单数</option>\
						</select>\
						<div class="layui-unselect layui-form-select">\
							<div class="layui-select-title">\
								<input type="text" placeholder="2019年3月" value="2019年3月" readonly="" class="layui-input layui-unselect"><i class="layui-edge">\
								</i>\
							</div>\
							<dl class="layui-anim layui-anim-upbit" style="">\
				    			<dd lay-value="销售量" class="layui-this">销售量</dd>\
								<dd lay-value="营业额" class="">营业额</dd>\
    							<dd lay-value="订单数" class="">订单数</dd>\
							</dl>\
						</div>\
					</div>');
    	form.render();
    	
    	//加载时间
    	$.ajax({
            type : "post", 
            url : "query_time",
            dataType : "json",
            async : false,
            success : function (res){
            	res = res.data;
            	let i = res.minTime;
            	while(i <= res.maxTime){
            		//开始时间框
            		$("#formerYears select:eq(1)").append("\
    					<option value=\'"+i+"\'>"+i+"</option>\
        			");
    				$("#formerYears dl:eq(1)").append("\
    					<dd value=\'"+i+"\'>"+i+"</dd>\
    				");
    				//结束时间框
    				$("#formerYears select:eq(2)").append("\
						<option value=\'"+i+"\'>"+i+"</option>\
    				");
					$("#formerYears dl:eq(2)").append("\
						<dd value=\'"+i+"\'>"+i+"</dd>\
					");
            		i++;
            	}
            	form.render();
            }
    	});
    }
    
  //往年对比图表
	function formerYears(){
    	let minTime = $("#formerYears select:eq(1)").val();
    	let maxTime = $("#formerYears select:eq(2)").val();
    	let description = $("#formerYears select:eq(3)").val();
    	Comprehensive.showLoading();
    	$.ajax({
            type : "post",
            url : "query_formerYears",
            dataType : "json",
            data : {"minTime":minTime,"maxTime":maxTime},
            //async : false,
            success : function (res){
            	res = res.data;
            	backCallFormerYears(res,minTime,maxTime,description);
            	formerVal = res;
            }
    	})
	}
	//往年对比回调函数
	function backCallFormerYears(res,minTime,maxTime,description){
		legendData = (function(description){
			let str = new Array();
			let numTotal = 0;
			let moneyTotal = 0;
			let orderTotal = 0;
			for(let i in res){
				if(typeof(res[i].numTotal)=="undefined"){
					res[i].moneyTotal = 0;
				}
				if(typeof(res[i].moneyTotal)=="undefined"){
					res[i].moneyTotal = 0;
				}
				if(typeof(res[i].orderTotal)=="undefined"){
					res[i].moneyTotal = 0;
				}
				numTotal += Number(res[i].numTotal);
				moneyTotal += Number(res[i].moneyTotal);
				orderTotal += Number(res[i].orderTotal);
			}
			str.unshift("");
			str.unshift("");
			if(description == "销售量"){
				str.push("销售量(共"+numTotal+")");
    		}else if(description == "营业额"){
    			str.push("营业额(共"+moneyTotal+")");
    		}else if(description == "订单数"){
    			str.push("订单数(共"+orderTotal+")");
    		}
			return str;
    	})(description),
		Comprehensive.hideLoading();
		Comprehensive.setOption ({
			title : {
				text:"数据截止"+date,
				x : "center",
				textStyle: {
			        fontSize: 14,
			        color: '#c9a655'          // 主标题文字颜色
			    },
			},
			legend: {
			    data:legendData,
			},
			tooltip : {
			    trigger: 'axis'
			},
			toolbox: {
			    show : true,
			    x : 'right',
			    feature : {
			        mark : {show: true},
			        magicType : {show: true, type: ['line', 'bar']},
			        saveAsImage : {show: true}
			    }
			},
			calculable : true,
			xAxis : [
			    {
			        type : 'category',
			        boundaryGap : true,
			        data : (function(res){
            			var arr = new Array();
            			minTime = Number(minTime);
            			maxTime = Number(maxTime);
            			while(minTime <= maxTime) {
            				arr.push(minTime);
            				minTime++;
            			}
            			return arr;
            		})(res),
			    }
			],
			yAxis : [
			    {	name : description,
			        type : 'value',
			        position :'left',
			        axisLabel: {
			            formatter: '{value}',
			            textStyle:{
			            	color : (function(description){
			            		if(description == "销售量"){
			            			return '#0B438B';
			            		}else if(description == "营业额"){
			            			return '#ffb980';
			            		}else if(description == "订单数"){
			            			return '#b2d8f4';
			            		}
			            	})(description)
			            }
			        }
			   }
			],
			series : [{
			        name:legendData[2],
			        type:'bar',
			        itemStyle : {
			            normal : {  
			                color:(function(description){
			            		if(description == "销售量"){
			            			return '#0B438B';
			            		}else if(description == "营业额"){
			            			return '#ffb980';
			            		}else if(description == "订单数"){
			            			return '#b2d8f4';
			            		}
			            	})(description),  //圈圈的颜色
			                lineStyle:{  
			                    color:(function(description){
				            		if(description == "销售量"){
				            			return '#0B438B';
				            		}else if(description == "营业额"){
				            			return '#ffb980';
				            		}else if(description == "订单数"){
				            			return '#b2d8f4';
				            		}
				            	})(description)  //线的颜色
			                }  
			            }  
			        },
			        data:(function(res){
			        	let str = new Array();
			        	if(description == "销售量"){
			        		for(let i in res){
			        			str.push(res[i].numTotal);
				        	}
	            		}else if(description == "营业额"){
	            			for(let i in res){
			        			str.push(res[i].moneyTotal);
				        	}
	            		}else if(description == "订单数"){
	            			for(let i in res){
			        			str.push(res[i].orderTotal);
				        	}
	            		}
			        	return str;
			        })(res)
			    }]
			},true);
	}
	
	//往年月对比图表
	function towYears(){
    	let minTime = $("#formerYears select:eq(1)").val();
    	let maxTime = $("#formerYears select:eq(2)").val();
    	let description = $("#formerYears select:eq(3)").val();
    	Comprehensive.showLoading();
    	$.ajax({
            type : "post",
            url : "query_towYears",
            dataType : "json",
            data : {"minTime":minTime,"maxTime":maxTime},
            //async : false,
            success : function (res){
            	Comprehensive.hideLoading();
            	res = res.data;
            	backCallTowYears(res,minTime,maxTime,description);
            	formerVal = res;
            }
    	})
	}
	
	
	//往年月对比回调函数
	function backCallTowYears(res,minTime,maxTime,description){
		minTime = Number(minTime);
		legendData = (function(description){
			let str = new Array();
			let numTotal = 0;
			let moneyTotal = 0;
			let orderTotal = 0;
			str.push("");
			str.push("");
			for(let i in res){
				for(let j in res[i]){
					if(typeof(res[i][j].numTotal)=="undefined"){
						res[i][j].numTotal = 0;
					}
					if(typeof(res[i][j].moneyTotal)=="undefined"){
						res[i][j].moneyTotal = 0;
					}
					if(typeof(res[i][j].orderTotal)=="undefined"){
						res[i][j].orderTotal = 0;
					}
					numTotal += Number(res[i][j].numTotal);
					moneyTotal += Number(res[i][j].moneyTotal);
					orderTotal += Number(res[i][j].orderTotal);
				}
				if(description == "销售量"){
					str.push((minTime + Number(i)) + "销售量(共"+numTotal+")");
	    		}else if(description == "营业额"){
	    			str.push((minTime + Number(i)) +"营业额(共"+moneyTotal+")");
	    		}else if(description == "订单数"){
	    			str.push((minTime + Number(i)) +"订单数(共"+orderTotal+")");
	    		}
			}
			return str;
    	})(description),
		Comprehensive.setOption({
			title : {
				text:"数据截止"+date,
				x : "center",
				textStyle: {
			        fontSize: 14,
			        color: '#c9a655'          // 主标题文字颜色
			    },
			},
			legend: {
			    data:legendData,
			},
			tooltip : {
			    trigger: 'axis',
			    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
			},
			toolbox: {
			    show : true,
			    x : 'right',
			    feature : {
			        mark : {show: true},
			        magicType : {show: true, type: ['line', 'bar']},
			        saveAsImage : {show: true}
			    }
			},
			calculable : true,
			xAxis : [
			    {
			        type : 'category',
			        boundaryGap : true,
			        data : (function(res){
            			var arr = new Array();
            			let i = 1;
            			maxTime = Number(maxTime);
            			while(i <= 12) {
            				arr.push(i+"月");
            				i++;
            			}
            			return arr;
            		})(res),
			    }
			],
			yAxis : [
			    {	name : description,
			        type : 'value',
			        position :'left',
			        axisLabel: {
			            formatter: '{value}',
			            textStyle:{
			            	color : (function(description){
			            		if(description == "销售量"){
			            			return '#0B438B';
			            		}else if(description == "营业额"){
			            			return '#ffb980';
			            		}else if(description == "订单数"){
			            			return '#b2d8f4';
			            		}
			            	})(description)
			            }
			        }
			   }
			],
			series : (function (res,description){
					let color = (function(description){
				            		if(description == "销售量"){
				            			return '#0B438B';
				            		}else if(description == "营业额"){
				            			return '#ffb980';
				            		}else if(description == "订单数"){
				            			return '#b2d8f4';
				            		}
				            	})(description);
					let json = [];
					let seriesJson;
					for(let i in res){
						let str = new Array();
						let name = legendData[2 + Number(i)];
						let data = (function(res,description){
								for(let j in res[i]){
									if(description == "销售量"){
						        		str.push(res[i][j].numTotal);
				            		}else if(description == "营业额"){
						        		str.push(res[i][j].moneyTotal);
				            		}else if(description == "订单数"){
						        		str.push(res[i][j].orderTotal);
				            		}
								}
					        	return str;
					        })(res,description)
					    seriesJson = {"clickable":false,"type":'bar',"name":name,"itemStyle" : {"normal" : {"color":color,"lineStyle":{"color":color}}},"data":data};
						json.push(seriesJson);
					}
					return json;
				})(res,description)
			},true);
	}
})
});