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
				    
					<div class="zTreeDemoBackground left">
						<ul id="demo_tree" class="ztree"></ul>
					</div>
					</td>
				    <td width="30ox"><fmt:message key="org.tagettree" /></td>
				    <td>
				    
					<div class="zTreeDemoBackground left">
						
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
	var zTree, treeModelNodes, user_zTree, user_treeNode;
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
			url : '${ctx}/tsysUserinfoController.do?method=userListByOrg&orgId='+'${orgId}',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				treeModelNodes = data;
				zTree = $.fn.zTree.init($("#demo_tree"), setting, treeModelNodes);
				zTree.expandAll(true);
			}
		});
	}
	//加载部门树
	function load_org_tree() {
		var setting = {
				edit: {
					drag: {
						inner:false
						//prev: dropPrev
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
			url : '${ctx}/tsysUserinfoController.do?method=userListByOrg&orgId='+'${orgId}',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			timeout : 7000,
			error : function() {
				alert('系统繁忙,请稍后再试');
			},
			success : function(data) {
				user_treeNodes = data;
				$(user_treeNodes).each(function(){
					if(this.pid==-1)
						{
						this.drag=false;
						}
					else
					this.drag=true;
				});
				$.fn.zTree.init($("#preview_tree"), setting, user_treeNodes);
				user_zTree = $.fn.zTree.getZTreeObj("preview_tree");
				user_zTree.expandAll(true);
			}
		});
		
	}
	
	
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {

				return false;
			} 
		}
		return true;
	}
	
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		if(targetNode.pid==0||targetNode==null)
		{
			return false;
		}
		return true;
	}
    
	function save_sort(reloadlist)
    {
    	ztree_paramer();
		var user_str=getString(NodeArr);
		$.ajax({
			url: "${ctx}/tsysUserinfoController.do?method=move",
			data:{userlist:user_str},
			type: 'post',
			dataType: 'json',					
			success:function(data){
				if(data.success){
					jQuery.messager.alert('提示:', data.msg,'info');
					load_demo_tree();
					reloadlist();
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
    	var nodes = user_zTree.getNodes(); 
    	for (var i = 0; i < nodes.length; i++) { 
    		var node = new Object(); 
            if(nodes[i].pid!=0)
            {
            	node.id=nodes[i].pid;
            	node.sortnum = i+1;
            	NodeArr.push(node);
            	
            }
            else
            {
            	node.sortnum=0;
            }
    	
    		if (nodes[i].children != null && nodes[i].children.length > 0) { 
    			ForeachFindChildNode(nodes[i], NodeArr,node.sortnum+1);
    			
    		}
    	}
    	
    }
    
    function ForeachFindChildNode(nodes, NodeArr,sortnum) { 
    	for (var i = 0; i < nodes.children.length; i++) {
    		var node = new Object(); 
    		node.id = nodes.children[i].id;
    		node.sortnum = sortnum+i;
    		NodeArr.push(node);   
    	}
    }
    
    function getString(userArray)
    {
    	
      var return_str="";
   	  var user_str="";
   	  for(var i=0;i<userArray.length;i++)
   		  {
   		    var user=new Object();
   		    user=userArray[i];
   		    user_str=user.id+"@"+user.sortnum;
   		    if(i!=(userArray.length-1))
   		    return_str=return_str+user_str+",";
   		    else
   		    return_str=return_str+user_str;
   		  
   		  }
   	  return return_str;
    }
   


   
</script>