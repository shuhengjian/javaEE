			/*修改添加*/
		    $("#save").click(function(){
		    	 var formData = new FormData($( "#newsForm" )[0]);
		    	if($("input[name='goodsName']").val()==""){
		    		layer.msg('商品名称不能为空', {icon: 6}); 
		    		return false;
		    	}
		    	if($("input[name='goodsName']").val().length > 20){
		    		layer.msg('商品名称字数不能超过20', {icon: 6}); 
		    		return false;
		    	}
		    	if($("#se").val()==""){
		    		layer.msg('商品分类不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("input[name='goodsCode']").val() ==""){
		    		layer.msg('商品编号不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("select[name='goodsUnits']").val()==""){
		    		layer.msg('商品单位不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("input[name='goodsQuantity']").val() ==""){
		    		layer.msg('商品净含量不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("input[name='goodsPrice']").val() ==""){
		    		layer.msg('商品价格不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("select[name='goodsStandard']").val()==""){
		    		layer.msg('商品规格不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("input[name='goodsInventoryWarning']").val() ==""){
		    		layer.msg('商品预警不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("input[name='goodsStock']").val() ==""){
		    		layer.msg('商品库存不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("select[name='goodsState']").val()==""){
		    		layer.msg('商品状态不能为空', {icon: 6});
		    		return false;
		    	}
		    	if($("select[name='goodsSpecial']").val()==""){
		    		layer.msg('商品是否特价不能为空', {icon: 6});
		    		return false;
		    	}
		    	$.ajax({
		    		url:"goods_save",
		    		data:formData,
		    		type:"post",
		    		dataType:"json",
		    		 processData: false,  
		             contentType: false,
		    		success:function(res){
		    			if(res.code==200){
							layer.alert(res.msg, {
		                        title: "提示",
		                        btn: ['确定']
		                    },function (index, item) {
		                    	parent.location.href="goods_stockList";//刷新页面
								x_admin_close();//关闭窗口
		                    });
						}else if(res.code==300){
							layer.alert(res.msg, {
		                        title: "提示",
		                        btn: ['确定']
		                    },function (index, item) {
		                    	parent.location.href="goods_querylist";//刷新页面
								x_admin_close();//关闭窗口
		                    });
						}else{
							layer.alert(res.msg);//失败提示
						}
		    			
		    		}
		    	})
		    })
		    function x_admin_close() {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}