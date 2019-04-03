<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
	<div align="center" class="foot-buttons" style="margin-top: 5px">
		<a id="btn_save" onclick="" class="easyui-linkbutton"
			iconCls="icon-save"><fmt:message key="button.save" /></a>
	    <a id="btn_search" onclick="load_org_tree();" class="easyui-linkbutton"
	    iconCls="icon-query"><fmt:message key="button.reset" /></a>
	</div>
			<table class="FormView" border="0" >
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td width="30ox"><fmt:message key="org.selecttree" /></td>
					<td>
				    
					<div class="zTreeDemoBackground left" style="overflow-y:auto; overflow-x:auto; width:260px; height:400px;">
						<ul id="demo_tree" class="ztree"></ul>
					</div>
					</td>
				    <td width="30ox"><fmt:message key="org.tagettree" /></td>
				    <td>
				    
					<div class="zTreeDemoBackground left" style="overflow-y:auto; overflow-x:auto; width:260px; height:400px;">
						
						<ul id="preview_tree" class="ztree"></ul>
						 
					</div>
				    </td>
				</tr>
			</table>		
<script type="text/javascript">
	$(function() {
		//渲染样式
		$.parser.parse();
		load_demo_tree();
		load_org_tree();
	});
	var zTreedemo, treeModelNodes, org_zTree, org_treeNode;
	//加载菜单菜单树
	function load_demo_tree() {
		var setting = {
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
				}
		};

		//获取数据
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysOrgController.do?method=queryOrgList',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				treeModelNodes = data;
				zTreedemo = $.fn.zTree.init($("#demo_tree"), setting, treeModelNodes);
				zTreedemo.expandAll(true);
			}
		});
	}
	//加载部门树
	function load_org_tree() {
		var setting = {
				edit: {
					drag: {
						inner:dropInner,
						prev: dropPrev
						
						
					},
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false
				},
				data: {
					simpleData: {
						enable: true,
						dropRoot:false,
						idKey: "id",   
						pIdKey: "pid",   
						rootPId: 0 
					}
				},
				callback: {
					beforeDrag: beforeDrag,
					beforeDrop: beforeDrop
				}
		};
		//获取数据 机构树
		$.ajax({
			type : 'post',
			url : '${ctx}/tsysOrgController.do?method=queryOrgList',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				org_treeNodes = data;
				$(org_treeNodes).each(function(){
					this.drag=true;
				});
				$.fn.zTree.init($("#preview_tree"), setting, org_treeNodes);
				org_zTree = $.fn.zTree.getZTreeObj("preview_tree");
				org_zTree.expandAll(true);
			}
		});
		
	}
	
	
	function beforeDrag(treeId, treeNodes) {
        for (var i = 0, l = treeNodes.length; i < l; i++) {
            if (treeNodes[i].drag === false) {
                return false;
            }
        }
        return true;
    }
    function beforeDrop(treeId, treeNodes, targetNode, moveType) {
    	if(targetNode.pid==0||targetNode==null)
		{
			return false;
		}
		return true;
    }	
    
    function dropInner(treeId, nodes, targetNode) {
    	if(targetNode==null || targetNode.pid==0)
    	{
    		return false;
    	}
		return true;
	}
    function dropPrev(treeId, nodes, targetNode) {
    	if(targetNode==null || targetNode.pid==0)
    	{
    		return false;
    	}
		
		return true;
	}
    
   
    
    function save_sort(reloadlist)
    {
    	ztree_paramer();
    	
		var org_str=getString(NodeArr);
		$.ajax({
			url: "${ctx}/tsysOrgController.do?method=move",
			data:{orglist:org_str},
			type: 'post',
			dataType: 'json',					
			success:function(data){
				if(data.success){
					$.messager.alert('提示:', data.msg,'info',function(){
						load_demo_tree();
						reloadlist();
					});
				}else{
					jQuery.messager.alert('提示:', data.msg,'info');
				}
			}
		});
    	
    }
    
    var NodeArr = new Array(); 
    function ztree_paramer()
    {
    	if(NodeArr.length>0)
    	{
    		NodeArr = null;
    		NodeArr = new Array();
    	}
    	var nodes = org_zTree.getNodes(); 
    	for (var i = 0; i < nodes.length; i++) { 
    		var node = new Object(); 
    		node.id = nodes[i].id;
    		if(nodes[i].pid==0)
    		{
    			node.pid="-1";
    		}
    		else
    		{
    			node.pid=nodes[i].pid;
    		}
    		node.sortnum = i+1;
    		node.ancestry="."+nodes[i].id+".-1.";
    		NodeArr.push(node); 
    		if (nodes[i].children != null && nodes[i].children.length > 0) { 
    			ForeachFindChildNode(nodes[i], NodeArr,node.sortnum+1,node.ancestry);
    			
    		}
    	}
    	
    	
    }
    
    function ForeachFindChildNode(nodes, NodeArr,sortnum,ancestry) { 
    	for (var i = 0; i < nodes.children.length; i++) {
    		var node = new Object(); 
    		node.id = nodes.children[i].id;
    		node.pid = nodes.children[i].pid;
    		node.sortnum = sortnum;
    		node.ancestry="."+nodes.children[i].id+ancestry;
    		NodeArr.push(node);  
    		if (nodes.children[i].children != null && nodes.children[i].children.length > 0) { 
    			sortnum=ForeachFindChildNode(nodes.children[i], NodeArr,node.sortnum+1,node.ancestry); 
    		}
    		else
    		{
    			sortnum++;
    		}
    		
          
    	}
    	return sortnum;
    }
    
    function getString(orgArray)
    {
    	
      var return_str="";
   	  var org_str="";
   	  for(var i=0;i<orgArray.length;i++)
   		  {
   		    var org=new Object();
   		    org=orgArray[i];
   		    org_str=org.id+"@"+org.pid+"@"+org.sortnum+"@"+org.ancestry;
   		    if(i!=(orgArray.length-1))
   		    return_str=return_str+org_str+",";
   		    else
   		    return_str=return_str+org_str;
   		  
   		  }
   	  return return_str;
    }
   


   
</script>