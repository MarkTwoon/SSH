<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   out.println("*********" + path);
    out.println("********" + basePath);
%>
<%--
  Created by IntelliJ IDEA.
  User: zengsm
  Date: 2017/12/6
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
 
<%@ taglib uri="/struts-tags" prefix="s" %>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
<body>
<div>
    <form action="login" method="post">
        用户名 : <input type="text" name="username"/>
        <br/>
        密码 : <input type="password" name="password"/>
        <br/>
        <input type="submit" value="Login"/>
        <input type="reset" value="Reset"/>
    </form>
</div>

<span><%=request.getAttribute("message") == null ? "" : request.getAttribute("message")%><br></span>
</body>
</html>
