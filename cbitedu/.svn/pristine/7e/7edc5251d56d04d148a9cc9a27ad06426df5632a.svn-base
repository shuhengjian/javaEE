<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="Mon, 04 Dec 1999 21:29:02 GMT"/>			
<title>创蓝IT教育项目实战平台</title>
<link href="<%=request.getContextPath()%>/css/login.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ui_widget/easyui/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loginName").focus(function(){
			if(this.value=='请输入用户名')this.value='';
		}).blur(function(){
			if(this.value=='')this.value='请输入用户名';
		});
		$("#yzm").focus(function(){
			if(this.value=='请输入验证码')this.value='';
		}).blur(function(){
			if(this.value=='')this.value='请输入验证码';
		});
		
		ReRandomCode();
	});
	
	function login(){
		if($("#loginName").val()=="")
		{
			alert("用户名不能为空");
			$("#loginName").focus();
			return false;
		}
		
		
		if($("#password").val()=="")
		{
			alert("密码不能为空");
			$("#password").focus();
			return false;
		}
		
		
		if($("#yzm").val()=="")
		{
			alert("验证码不能为空");
			$("#yzm").focus();
			return false;
		} 
		
		$.post("<%=request.getContextPath()%>/index.do?method=login", 
				{ 
					loginName: $("#loginName").val(), 
				  	password: $("#password").val(),
				  	yzm:$("#yzm").val()
				},
			   function(data){
			     if(data.result==0){ 
			    	 window.location.href= "<%=request.getContextPath()%>/index.do?method=toIndex";
			     }else{
			    	 alert(data.msg);
			    	 $("#loginName").focus();
			     }
			   },"json"
	   	);
		
		return false;
	}
	
	function ReRandomCode()
	{
		var o=document.getElementById("img_yzm");
		var url = "<%=request.getContextPath()%>/index.do?method=randomCode&radom=" + Math.random();
		o.src = url;
	}

	function resetPage() {
		$("#loginName").val("");
		$("#password").val("");
		$("#yzm").val("");
	}
	document.onkeydown=function(e){
			var ev = window.event||e;
			var src = ev.srcElement||ev.target;
			if(src.readOnly||!(src.tagName=="INPUT"|| src.tagName=="TEXTAREA")){
				return false;
			}
		}
</script>
</head>
<body onkeypress="if (event.keyCode == 13) login();">
<div class="main">
  <div class="top"><img src="images/cp-logo.png"  height="56" /></div>
  <div class="cent">
     <div class="logintable" >
       <table width="373" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="21%" class="usename">用户名：</td><td><label for="textfield"></label>
      <input name="loginName" id="loginName" type="text" class="logininput" value="admin" /></td>
  </tr>
   <tr>
    <td class="usename">密&nbsp;&nbsp;&nbsp;码：</td><td><label for="textfield"></label>
      <input name="password" id="password" type="password"  class="logininput" value="123456"/></td>
  </tr>
   <tr>
    <td class="usename">验证码：</td><td  class="imgcent"><label for="textfield"></label>
    <input name="yzm" id="yzm" type="text" / class="loginyanzhengma" style="float:left;/">&nbsp;
     <img id="img_yzm" onclick="ReRandomCode(this);" src=""
					style="cursor: hand" width="73" height="30" class="imgy" /> </td>
  </tr>    
   <tr>  
    <td colspan="2" class="loginbut">    
    <a href="#"><img src="images/login_butbg_03.jpg" width="193" height="45" onclick="login()" /></a></td>
  </tr>   
</table>
    </div>
     <div class="loginfoot">技术支持：创蓝研发团队</div> 
  </div>
</div>


</body>
</html>
