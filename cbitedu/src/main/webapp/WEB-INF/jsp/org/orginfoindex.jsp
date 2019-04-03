<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<script type="text/javascript">
		var zTree,treeNodes;
		var tree_id = "";
		var frist_id="";
		var tree_name="";
		var wins;
		$(function() {  
			$('body').layout({ applyDefaultStyles: true });
			load_tree();
			load_first();
		}); 
		//加载首页面
        function load_first()
        {
        	$.ajax({
				url: "${ctx}/tsysOrgController.do?method=rootinfo",
				//data:{id:tree_id},
				type: 'post',
				dataType: 'json',					
				success:function(data){
					frist_id=data.treeid;
					var url="${ctx}/tsysOrgController.do?method=tomodify&op=select&id="+data.treeid;
					$("#centerIframe").attr("src",url);	
				}
			});
        	
        }
       
		//加载树
		function load_tree() {
			var setting = {
					check: { 
						enable: false,
					  	chkboxType: {"Y":"", "N":""}
					},
					edit: {
						enable: false,
						showRemoveBtn: false,
						showRenameBtn: false
					},
					view: {
						dblClickExpand: false
					},
					data: {
						simpleData: {
							enable: true,
							idKey: "id",   
							pIdKey: "pid",   
							rootPId: 0 
						}
					},
					callback: {
						onClick: treeClick                        
					}
				};
				
				//获取数据 机构树
				$.ajax({
	            	type:'post',
	            	url:'${ctx}/tsysOrgController.do?method=queryOrgList',
	            	dataType:'json',
	            	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	            	timeout:7000,
	            	error: function(){
	            		alert('系统繁忙,请稍后再试');
	    			},
	            	success: function(data){
	    				treeNodes = data;
	    				$.fn.zTree.init($("#orginfo_tree"), setting, treeNodes);
	    				zTree = $.fn.zTree.getZTreeObj("orginfo_tree");
	    				myExpandNode();
	    			}
	    		});
				
				//新增
				$("#btn_add").click(
						function() {
							
							if(tree_id=="" && frist_id!=0)
							{
								jQuery.messager.alert('提示:','请选择上级部门!','info');
								return;
							}
							var url="${ctx}/tsysOrgController.do?method=tomodify&op=saveOrg&id="+tree_id;
							$("#centerIframe").attr("src",url);		
							
				});
				//修改
				$("#btn_edit").click(
						function() {
							if(tree_id=="")
							{
								jQuery.messager.alert('提示:','请选择一条记录!','info');
								return;
							}
							
							var url="${ctx}/tsysOrgController.do?method=tomodify&op=modify&id="+tree_id;
							$("#centerIframe").attr("src",url);		
							
				});
				//删除
				/*
				$("#btn_delete").click(
						
						function() {
							if(tree_id=="")
							{
								jQuery.messager.alert('提示:','请选择一条记录!','info');
								return;
							}
							jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){
								if(event){
									$.ajax({
										url: "${ctx}/tsysOrgController.do?method=del",
										data:{id:tree_id},
										type: 'post',
										dataType: 'json',					
										success:function(data){
											if(data.success == 0){
												jQuery.messager.alert('提示:',data.msg, '', function(){
													reloadlist(); // 只刷新列表就可以了
												});
											}else{
												jQuery.messager.alert('提示:',data.msg, 'warning');
											}
										}
									});
								}
								});		
				});*/
				
			}

			/*
			 * 展开根节点
			 */
			function myExpandNode(){
				var nodes = zTree.getNodes();
				if(nodes.length == 1){
					zTree.expandNode(nodes[0], true,false ,true,false);
				}
			}

			//点击树
			
			function treeClick(e, treeId, treeNode){
				tree_id = treeNode.id;
				tree_name=treeNode.name;
				
				var url="${ctx}/tsysOrgController.do?method=tomodify&op=select&id="+tree_id;
				$("#centerIframe").attr("src",url);
			}
			var reloadlist=function (){
				load_tree();
			   
			}
			
			function to_sort()
			{
			    wins = parent.sy.modalDialog({
					title : "机构排序",
					url: '${ctx}/tsysOrgController.do?method=tomove',
							
				     onLoad:function(){
						var execWin=$(wins).find("iframe").get(0).contentWindow;//子页面的window对象
						var execBody=execWin.document.body;//子页面的body DOM对象
						$(execBody).find("#btn_save").click(function(){
							execWin.save_sort(reloadlist);
							
						});
						
					}
				});
			}
			
			function del()
			{
				if(tree_id=="")
				{
					jQuery.messager.alert('提示:','请选择一条记录!','info');
					return;
				}
				jQuery.messager.confirm('提示:','你确认要删除吗?',function(event){
					if(event){
						$.ajax({
							url: "${ctx}/tsysOrgController.do?method=del",
							data:{id:tree_id},
							type: 'post',
							dataType: 'json',					
							success:function(data){
								if(data.success == 0){
									jQuery.messager.alert('提示:',data.msg, '', function(){
										reloadlist(); // 只刷新列表就可以了
									});
								}else{
									jQuery.messager.alert('提示:',data.msg, 'warning');
								}
							}
						});
					}
					});	
				
			}
			
			
			
			
		</script>
	</head>
	<body>
		<div data-options="region:'west', fit:false" style="width:257px;">
		    <div id="toolbar_area" border="false">
		    <cbitedu:auth operate_code="ADD" module_code="T_SYS_ORG" id="btn_add"
					icon="icon-add"  cssClass="easyui-linkbutton"
					value="button.add" type="link" plain="true"/>
			<cbitedu:auth operate_code="EDIT" module_code="T_SYS_ORG" id="btn_edit"
					icon="icon-edit"  cssClass="easyui-linkbutton"
					value="button.edit" type="link" plain="true"/>
			<cbitedu:auth operate_code="DELETE" module_code="T_SYS_ORG" id="btn_delete"
					icon="icon-remove"  onclick="del();" cssClass="easyui-linkbutton"
					value="button.remove" type="link" plain="true"/>
			<cbitedu:auth operate_code="ORDER" module_code="T_SYS_ORG" id="btn_move"
					icon="icon-remove"  onclick="to_sort();" cssClass="easyui-linkbutton"
					value="button.move" type="link" plain="true"/>	 
		    </div>
			<div class="zTreeDemoBackground left">
				<ul id="orginfo_tree" class="ztree"></ul> 
			</div>
		</div>
	    <div data-options="region:'center',title:'机构详细信息'">
			<iframe id="centerIframe" name="centerIframe" src=""
				style="width: 100%;height: 100%" frameBorder="no" scrolling="no"></iframe>
		</div>
		
	</body>
</html>