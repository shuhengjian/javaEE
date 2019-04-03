<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script type="text/javascript">
var moduleTree;		
var zTree,treeNodes;
$(function() {  

	$('body').layout({ applyDefaultStyles: true });
	load_tree();
});  

//加载菜单树
function load_tree() {
	var setting = {
			check: { 
				enable: false,
			  	chkboxType: {"Y":"", "N":""}
			},
			edit: {
				enable: true,
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
					pIdKey: "pId",   
					rootPId: 0 
				}
			},
			callback: {
				onClick: treeClick                        
			}
		};
		
		//获取数据
		$.ajax({
        	type:'post',
        	url:'${ctx}/tsysModuleController.do?method=moduleTree',
        	dataType:'json',
        	contentType: "application/x-www-form-urlencoded; charset=utf-8",
        	timeout:7000,
        	error: function(){
        		alert('系统繁忙,请稍后再试');
			},
        	success: function(data){
				treeNodes = data;
				moduleTree=$.fn.zTree.init($("#module_tree"), setting, treeNodes);
				moduleTree = $.fn.zTree.getZTreeObj("module_tree");
				var nodes = moduleTree.getNodeByParam("id", '${tsysModule.moduleId}', null);
				moduleTree.expandNode(nodes, true, false ,true,false);
				moduleTree.selectNode(nodes);
			}
		});
}

</script>
<body>
	<ul id="module_tree" class="ztree" style="border: 0px; background-color: #FFFFFF;"></ul>  
</body>
</html>
