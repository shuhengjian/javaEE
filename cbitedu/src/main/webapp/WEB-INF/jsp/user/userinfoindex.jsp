<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<script type="text/javascript">
		var uzTree,utreeNodes;
		$(function() {  
			$('body').layout({ applyDefaultStyles: true });
			load_tree();
		});  
		//加载根节点
        function load_rootNode()
        {
        	$.ajax({
				url: "${ctx}/tsysOrgController.do?method=rootinfo",
				//data:{id:tree_id},
				type: 'post',
				dataType: 'json',					
				success:function(data){
					var frist_id=data.treeid;
					var node = uzTree.getNodeByTId(frist_id);
					uzTree.selectNode(node);//设置根节点为选中状态
					var url="${ctx}/tsysUserinfoController.do?method=user_list&org_id="+node.id;
					$("#centerIframe").attr("src",url);
				}
			});
        }
		//加载指定节点
        function loadNode(tnode)
        {
			uzTree.selectNode(tnode);//设置根节点为选中状态
			var url="${ctx}/tsysUserinfoController.do?method=user_list&org_id="+tnode.id;
			$("#centerIframe").attr("src",url);
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
//	            	url:'${ctx}/tsysUserinfoController.do?method=queryOrgList',
	            	url:'${ctx}/tsysOrgController.do?method=queryOrgList',
	            	dataType:'json',
	            	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	            	timeout:7000,
	            	error: function(){
	            		alert('系统繁忙,请稍后再试');
	    			},
	            	success: function(data){
	            		utreeNodes = data;
	    				$.fn.zTree.init($("#uorginfo_tree"), setting, utreeNodes);
	    				uzTree = $.fn.zTree.getZTreeObj("uorginfo_tree");
	    				myExpandNode();
	    				//load_rootNode();//加载根节点
	    				var node = uzTree.getNodeByTId("1");
	    				loadNode(node);//此方法利用ztree的方法.不用再向服务器请求数据
	    				
	    			}
	    		});
			}

			/*
			 * 展开根节点
			 */
			function myExpandNode(){
				var nodes = uzTree.getNodes();
				if(nodes.length == 1){
					uzTree.expandNode(nodes[0], true, false ,true,false);
				}
			}

			//点击树
			var tree_id = "";
			function treeClick(e, treeId, treeNode){
				tree_id = treeNode.id;
				//alert(treeNode.tId);
				//alert(tree_id);
				var url="${ctx}/tsysUserinfoController.do?method=user_list&org_id="+tree_id;
				$("#centerIframe").attr("src",url);
			}

			//查询
			var dict_name = "";
			function query_dic() {
				dict_name = $("#dict_name").val();
				load_tree();
			}
	
		</script>
	</head>
	<body>
		<div data-options="region:'west', fit:false" style="width: 252px;">
			<div class="zTreeDemoBackground left">
				<ul id="uorginfo_tree" class="ztree"></ul> 
			</div>
		</div>
		<div data-options="region:'center'">
			<iframe id="centerIframe" name="centerIframe" src=""
				style="width: 100%;height: 100%" frameBorder="no" scrolling="no"></iframe>
		</div>
		
		
	</body>
</html>