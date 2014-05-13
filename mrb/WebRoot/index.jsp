<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 最新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet"
			href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

		<!-- 可选的Bootstrap主题文件（一般不用引入） -->
		<link rel="stylesheet"
			href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script
			src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
		<script language="javascript">
        
        var xmlHttp;
		function createXMLHttpRequest()
		{
			if(window.ActiveXObject)
			{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			else if(window.XMLHttpRequest)
			{
				xmlHttp = new XMLHttpRequest();
			}
		}
		
		//以JSON串表示用户
		function createJson()
		{
			var username = document.myform.operator_id.value;	
			//将JavaScript的数组转换为JSON串返回
			return JSON.stringify(username);				
		}
		
		//发送异步请求
		function sendInfo()
		{
			createXMLHttpRequest();
			var jsonUser = createJson();
			
			var url = "/checkLogin" ;
			xmlHttp.open("post", url, true);	
	    	xmlHttp.onreadystatechange = handleStateChange;
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttp.send(jsonUser);
		}
		
		//处理响应数据
		function handleStateChange()
		{
			if(xmlHttp.readyState == 4)
			{
				if(xmlHttp.status == 200)
				{
					parseResults();
				}
			}
		}
		
		//解析服务器响应的JOSN串
		function parseResults()
		{
			//将服务器发回的JSON串转换为JavaScript中的数组
			var result = eval('(' + xmlHttp.responseText + ')');
			
			if (result == "Y") 
			{
				document.getElementById("resInfo").innerHTML = "";
				
			}
			else if (result == "N") 
			{
				document.getElementById("operator_id").value = "";
				document.myform.operator_id.focus();
				document.getElementById("resInfo").innerHTML = "<font color=red>用户名不存在</font>";
			}		
		}
		</script>
	</head>

	<body>
		[
		<br>
		<br>
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">
					用户名或手机号
				</label>
				<div class="col-sm-4">
					<input type="email" class="form-control" id="inputEmail3"
						placeholder="例如 abc 或 15900001234">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">
					密&nbsp;&nbsp;码
				</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<div class="checkbox">
						<label>
							<input type="checkbox">
							记住我
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<button type="submit" class="btn btn-success">
						登&nbsp;&nbsp;陆
					</button>
				</div>
			</div>
		</form>

		<br>
		<br>
		<form class="form-horizontal" role="form" action="user/register.do"
			method="post">
			<div class="form-group">
				<label for="user" class="col-sm-2 control-label">
					用户名或手机号
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="user"
						placeholder="例如 abc 或 15900001234">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-sm-2 control-label">
					密&nbsp;&nbsp;码
				</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="pwd" placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd2" class="col-sm-2 control-label">
					重复密码
				</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="pwd2"
						placeholder="">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<div class="checkbox">
						<label>
							<input type="checkbox">
							我已经阅读并且同意了《XXX用户协议》
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<button type="submit" class="btn btn-success">
						注&nbsp;&nbsp;册
					</button>
				</div>
			</div>
		</form>

	</body>
</html>
