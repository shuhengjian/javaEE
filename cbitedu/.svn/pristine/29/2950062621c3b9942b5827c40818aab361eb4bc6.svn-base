<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/ui_widget/ztree/css/demo.css">
	<!-- ueditor begin-->
	<script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/news.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/ui_widget/ueditor/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        .clear {
            clear: both;
        }
         body{
        	overflow: scroll;
        }
    </style>
    
<script type="text/javascript">
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
				event.target).parents("#menuContent").length > 0)) {
			hideMenu();
		}
	}

	function reloadList(){
		parent.reloadlist();
	}
	function back_click() {
		parent.closeDialog();
	}
	
	let formData = new FormData($("#ttyproductForm")[0]);
	$(function(){
		
		//实例化编辑器
		 var ue = UE.getEditor('editor');
		//隐藏存放新闻内容的textarea
		 $("#productDetails").hide();
		//获得textarea新闻内容
		var productDetails = $("#productDetails").text();
		//将内容注入文本编辑器
		UE.getEditor("editor").addListener("ready", function () {
			UE.getEditor('editor').setContent(productDetails);
		});
		
		
		/*图片预览*/
		$("#fileUrl").change(function(){
		    var objUrl = getObjectURL(this.files[0]) ;
		    console.log("objUrl = "+objUrl) ;
		    if (objUrl) 
		    {
		      $("#img_show").attr("src", objUrl);
		      $("#img_show").removeClass("hide");
		    }
		  }) ;
		  //建立一個可存取到該file的url
		  function getObjectURL(file) 
		  {
		    var url = null ;
		    if (window.createObjectURL!=undefined) 
		    { // basic
		      url = window.createObjectURL(file) ;
		    }
		    else if (window.URL!=undefined) 
		    {
		      // mozilla(firefox)
		      url = window.URL.createObjectURL(file) ;
		    } 
		    else if (window.webkitURL!=undefined) {
		      // webkit or chrome
		      url = window.webkitURL.createObjectURL(file) ;
		    }
		    return url ;
		  }
		  
	        ////////////////////////////////////////////////图片上传//////////////////////////////////////////////
	        //声明变量
	        var $button = $('#upload'),
	            //选择文件按钮
	            $file = $("#productDetailsPicture"),
	            //回显的列表
	            $list = $('.file-list'),
	            //选择要上传的所有文件
	            fileList = [];
	        //当前选择上传的文件
	        var curFile;
	        // 选择按钮change事件，实例化fileReader,调它的readAsDataURL并把原生File对象传给它，
	        // 监听它的onload事件，load完读取的结果就在它的result属性里了。它是一个base64格式的，可直接赋值给一个img的src.
	        $file.on('change', function (e) {
	        	console.log(1)
	            //上传过图片后再次上传时限值数量
	            var numold = $('li').length;
	            if(numold >= 6){
	            	jQuery.messager.alert('提示:','最多上传6张图片','');
	               this.outerHTML=this.outerHTML;
	                return;
	            }
	            //限制单次批量上传的数量
	            var num = e.target.files.length;
	            var numall = numold + num;
	            if(num >6 ){
	            	jQuery.messager.alert('提示:','最多上传6张图片','');
	              this.outerHTML=this.outerHTML;
	               return;
	            }else if(numall > 6){
	            	jQuery.messager.alert('提示:','最多上传6张图片','');
	             this.outerHTML=this.outerHTML;
	                return;
	            }
	            //原生的文件对象，相当于$file.get(0).files;//files[0]为第一张图片的信息;
	            curFile = this.files;
	            //formData.append("productDetailsPicture",curFile[0]);
	            //curFile = $file.get(0).files;
	            //console.log(curFile);
	            //将FileList对象变成数组
	            fileList = fileList.concat(Array.from(curFile));
	            //console.log(fileList);
	            for (var i = 0,len = curFile.length; i < len ;i++) {
	            	formData.append("productDetailsPicture",curFile[i]);
	                reviewFile(curFile[i])
	            }
	                console.log(curFile);
	            $('.file-list').fadeIn(2500);
	        })


	        function reviewFile(file) {
	            //实例化fileReader,
	            var fd = new FileReader();
	            //获取当前选择文件的类型
	            var fileType = file.type;
	            //调它的readAsDataURL并把原生File对象传给它，
	            fd.readAsDataURL(file);//base64
	            //监听它的onload事件，load完读取的结果就在它的result属性里了
	            fd.onload = function () {
	                if (/^image\/[jpeg|png|jpg|gif]/.test(fileType)) {
	                    $list.append('<li style="border:solid red px; margin:5px 5px;" class="file-item"><img src="' + this.result + '" alt="" height="70"><span class="file-del">删除</span></li>').children(':last').hide().fadeIn(2500);
	                } else {
	                    $list.append('<li class="file-item"><span class="file-name">' + file.name + '</span><span class="file-del">删除</span></li>')
	                }

	            }
	        }

	        //点击删除按钮事件：
	        $(".file-list").on('click', '.file-del', function () {
	            let $parent = $(this).parent();
	            console.log($parent);
	            let index = $parent.index();
	            fileList.splice(index, 1);
	            $parent.fadeOut(850, function () {
	                $parent.remove()
	            });
	            //$parent.remove()
	        });
	})
	
	function save_click() {
	    		$("#productDetails").html(UE.getEditor('editor').getContent());//将内容再存入 textarea中
	    		$("#ttyproductForm").validationEngine({ promptPosition:"bottomRight"});
	    		var bool = $("#ttyproductForm").validationEngine('validate');
	    		console.log($("[name='productDetailsPicture']"));
	    		
	    		/* 品牌判断 brand */
	    		var slt=document.getElementById("productBrand");
		        if(slt.value==""){
		        	jQuery.messager.alert('提示:',"产品品牌不能为空",'');
		            return;
		        }
	    		
	            /* 价格判断 */
	    		let MPrice = $("#productMarketPrice").val().trim();
	    		let FPrice = $("#productFlatlyPrice").val().trim();
	    		let MP = parseInt(MPrice);
	    		let FP = parseInt(FPrice);
	    		if(MP!=null && FP>MP){
	    			jQuery.messager.alert('提示:',"一口价不能大于市场价",'');
	    			return;
	    		}
	    		if(MP<0){
	    			jQuery.messager.alert('提示:',"市场价不能小于0",'');
	    			return;
	    		}
	    		if(FP<0){
	    			jQuery.messager.alert('提示:',"一口价不能小于0",'');
	    			return;
	    		}

	    		if (bool == true) {
	    			formData.append("productId", $("[name='productId']").val());
	    			formData.append("productName", $("[name='productName']").val());
	    			formData.append("productMarketPrice", $("[name='productMarketPrice']").val());
	    			formData.append("productFlatlyPrice", $("[name='productFlatlyPrice']").val());
	    			formData.append("productRemark", $("[name='productRemark']").val());
	    			formData.append("productColor", $("[name='productColor']").val());
	    			formData.append("product_cover_picture", $("[name='product_cover_picture']")[0].files[0]);
	    			formData.append("brandId", $("[name='brandId']").val());
	    			formData.append("priceId", $("[name='ttyPrice.priceId']").val());
	    			formData.append("typeId", $("[name='ttyType.typeId']").val());
	    			formData.append("orgId", $("[name='orgId']").val());
	    			formData.append("product_cover_picture", $("[name='product_cover_picture']")[0].files[0]);
	    			formData.append("productDetails", UE.getEditor('editor').getContent());
	    			$.ajax({
	    				url : "${ctx}/ttyBackProductController.do?method=ajaxSave",
	    				type : "POST",
	    				data : formData,
	    				processData : false,
	    	            contentType : false,
	    				dataType : 'json',
	    				error : function() {
	    					jQuery.messager.alert('提示:','发生错误!', '');
	    				},
	    				success : function(data) {
	    					if (data.flag) {
	    						jQuery.messager.alert('提示:', data.msg, 'info', function(){
	    							reloadList();
	    							back_click();
	    						});
	    					} else {
	    						jQuery.messager.alert('提示:', data.msg, 'info');
	    					}
	    				}
	    			});
	    		} else {
	    			jQuery.messager.alert('提示:', '您的表单输入校验不通过，请重填!', 'info');
	    		}
	    		
	    	}
	        
	
	
	 //图片预览判断
		function imgPreview(fileDom) {
			var path = $("#fileUrl").val();
		    extStart = path.lastIndexOf('.');
		    ext = path.substring(extStart,path.length).toUpperCase();
		    if(ext !== '.PNG' && ext !== '.JPG' && ext !== '.JPEG' && ext !== '.GIF' && ext !== '.BMP'){
		    	jQuery.messager.alert('提示:','请上传正确格式的图片','');
		        $("#fileUrl").val("");
		        return;
		      }
			let reader = new FileReader();
			let file = fileDom.files[0];
			reader.onload = function(e){
				let img = document.getElementById("img_show");
				img.src = e.target.result;
			}
			reader.readAsDataURL(file);
		}
</script>


</head>
<body>
	<!-- 表单布局设计begin -->
	<cbitedu:form bean="ttyProduct" scope="request">
		<form action="${ctx}/ttyBackProductController.do?method=ajaxSave" method="post" name="ttyproductForm" id="ttyproductForm" enctype="multipart/form-data">
			<input type="hidden" name="productId" value="${ttyProduct.productId }" />
			<div class="easyui-layout easyui-panel">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
						<!-- 产品名称 -->
						<td><fmt:message key="product.productNames" /></td>
						<td>
							<input type="text" id="productName" oninput="if(value.length>15)value=value.slice(0,50)" value="${ttyProduct.productName }" name="productName" class="text validate[required,maxSize[15]]" />
							<font color="red">*</font>
						</td>
						<!-- 类型 -->
						<td><fmt:message key="product.ttyType" /></font></td>
						<td>
							<!-- <input type="text" id="ttyType" name="ttyType" class="text validate[required,maxSize[15]]" /> -->
							<select name="ttyType.typeId" style="width : 160px;height:auto">
								<!-- 中规车 -->
	                   			<optgroup  label="中规车">
		                   			<c:forEach items="${TtyType }" var="t">
			                   			<c:if test="${t.typeStart == 1 }">
				                   			<option value="${t.typeId}" <c:if test="${ttyProduct.ttyType.typeId==t.typeId }">selected="selected"
					                   			</c:if>  >
					                   			${t.typeName}
				                   			</option>
				                   		</c:if>
			                   		</c:forEach>
		                   		</optgroup>
		                   		<!-- 平行车 -->
		                   		<optgroup label="平行车">
			                   		<c:forEach items="${TtyType }" var="t">
				                   		<c:if test="${t.typeStart==2 }">
				                   			<option value="${t.typeId}" <c:if test="${ttyProduct.ttyType.typeId==t.typeId }">selected="selected"
				                   				</c:if>  >
				                   				${t.typeName}
			                   				</option>
			                   			</c:if> 
		                   			</c:forEach>
		                   		</optgroup>
		                   	</select>
		                   	<font color="red">*
						</td>
					</tr>
					<tr>
						<!-- 市场价 -->
						<td><fmt:message key="product.productMarketPrice" /></td>
						<td>
							<input type="number" id="productMarketPrice" oninput="if(value.length>15)value=value.slice(0,15)" value="${ttyProduct.productMarketPrice }" name="productMarketPrice" class="text validate[required,maxSize[15]]" placeholder="单位(w)" />
							<font color="red">*</font>
						</td>
						
						<!-- 商家 -->
						<td><fmt:message key="product.tsysOrg" /></td>
						<td>
							<!-- <input type="text" id="tsysOrg" name="tsysOrg" class="text validate[required,maxSize[15]]" /> -->
							<select name="orgId" style="width : 160px;height:auto">
								<c:forEach items="${orgList }" var="o">
		                   			<option value="${o.orgId}" <c:if test="${ttyProduct.tsysOrg.orgId==o.orgId }">selected="selected"</c:if> > 
		                   			${o.orgName }</option>
		                   		</c:forEach>
							</select>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<!-- 一口价 -->
						<td><fmt:message key="product.productFlatlyPrice" /></td>
						<td>
							<input type="number" id="productFlatlyPrice" oninput="if(value.length>15)value=value.slice(0,15)" value="${ttyProduct.productFlatlyPrice }" name="productFlatlyPrice" class="text validate[required,onlyNumberSp,maxSize[15]]" placeholder="单位(w)"/>
							<font color="red">*</font>
						</td>
						
						<!-- 品牌 -->
						<td><fmt:message key="product.ttyBrand" /></td>
						<td>
							<!-- <input type="text" id="ttyBrand" name="ttyBrand" class="text validate[required,maxSize[15]]" /> -->
							<select name="brandId" style="width : 160px;height:auto" class="brand" id="productBrand">
								<c:forEach items="${ttyBrand }" var="b">
		                   			<option value="${b.brandId}" <c:if test="${ttyProduct.ttyBrand.brandId==b.brandId }">selected="selected"</c:if> >
		                   			 ${b.brandName}</option>
		                   		</c:forEach>
							</select>
							<font color="red">*</font>
						</td>
					</tr>
					
					<tr>
						<!-- 颜色 -->
						<td style="vertical-align:top"><fmt:message key="product.productColor" /></td>
						<td style="vertical-align:top">
							<input type="text" id="productColor" oninput="if(value.length>5)value=value.slice(0,15)" value="${ttyProduct.productColor }" name="productColor" class="text validate[required,maxSize[15]]" />
							<font color="red">*</font>
						</td>
						
						<!-- 封面图 -->
						<td style="vertical-align:top"><fmt:message key="product.productCoverPicture" /></td>
						<td>
							<input type="file" id="fileUrl"  name="product_cover_picture" class="form-control" onchange="imgPreview(this)" placeholder="请选择图片" style="width : 160px;height:auto;vertical-align:top"/>
							<img id="img_show" name="img_show" style="width : 70px;height:50px"/>
							<!-- <font color="red">*</font> -->
							<!-- <img style="width : 70px;height:auto"/> -->
						</td>
						
					</tr>
					
					
					<!-- 详情4图 -->
					<tr>
						<td>详情图</td>
						<td colspan="3">
							<input type="file" name="productDetailsPicture"  id="productDetailsPicture" onchange="imgproductDetails(this)" multiple="multiple" placeholder="请选择图片"/>
							<ul class="file-list ">
						</td>
					</tr>
					<tr>
						<!-- 优势/介绍 -->
						<td style="vertical-align:top"><fmt:message key="product.productRemark" /></td>
						<td colspan="3">
							<%-- <input type="text" id="productRemark" value="${ttyProduct.productRemark }" name="productRemark" class="text validate[required,maxSize[100]]" /> --%>
							<textarea style="width: 700px;height: 50px;" id="productRemark" value="${ttyProduct.productRemark }" name="productRemark" class="text validate[required,maxSize[100]]"></textarea>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
					
					<tr>
						<!-- 详情 -->
					<td><fmt:message key="product.productDetails" /></td>
						<td colspan="3">
							<textarea id="productDetails" name="productDetails"></textarea>
								<script id="editor" type="text/plain" style="width:700px;height:100px;"></script>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<!-- 表单布局设计end -->
		<!-- 表单内的按钮设计begin -->
		<div align="center" class="foot-buttons" style="margin-top: 5px">
			<a id="btn_save" onclick="save_click();" class="easyui-linkbutton" iconCls="icon-save">
				<fmt:message key="button.save" />
			</a>
			<a id="btn_save" onclick="back_click();" class="easyui-linkbutton" iconCls="icon-back">
				<fmt:message key="button.back" />
			</a>
		</div>
	</cbitedu:form>
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; height : 140px; width: 160px; " ></ul>
	</div>
</body>
<style>
    .file-list {
        height: 90px;
        display: none;
        list-style-type: none;
    }
    .file-list img {
        max-width: 70px;
        vertical-align: middle;
    }
    .file-list .file-item {
        float: left;
        margin-left: 20px;
    }
    .file-list .file-item .file-del {
        display: block;
        margin-left: 20px;
        cursor: pointer;
    }
</style>
</html>