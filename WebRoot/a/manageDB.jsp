<%@ page language="java"
	import="java.util.*,com.phn.bean.Users,java.io.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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

<title>公告管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./CSS/manage.css" />
<script type="text/javascript" src="./JS/jquery-1.11.0.js"></script>
<style type="text/css">
body,html {
	width: 1004px;
	padding: 0px;
	margin: 0px auto; /*设置居中  */
	text-align: center; /*兼容性更好  */
	font-family: "微软雅黑";
}

.pageNav {
	width: 550px;
	height: 30px;
	margin-top: 15px;
	margin-bottom: 5px;
	float: left;
}

.pageGo {
	width: 120px;
	height: 25px;
	line-height: 25px;
	margin-top: 15px;
	margin-bottom: 5px;
	float: left;
	font-size: 13px;
}

.pageNav a button {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: white;
	border: 1px solid silver;
}

.pageNav a button:HOVER {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #A2C1DE;
	border: 1px solid silver;
}

.pageNav button {
	width: 30px;
	height: 25px;
	font-size: 14px;
	font-family: 微软雅黑;
	background-color: #EFF4FB;
	border: 1px solid silver;
}

table tr td {
	border-bottom: 1px solid silver;
	border-right: 1px solid silver;
	padding-left: 3px;
}

.annoStyle {
	width: 790px;
	margin-left: 10px;
	margin-right: 5px;
}

.annoStyle a:link,.annoStyle a:visited {
	color: blue;
	text-decoration: none;
} /* 未被访问的链接 */ /*已被访问的链接 */
.annoStyle a:hover {
	color: #2979BF;
	font-weight: bolder;
	text-decoration: none;
} /* 鼠标指针移动到链接上 */
.butt {
	background-color: #6699CC;
	width: 120px;
	height: 30px;
	margin-top: 10px;
	margin-bottom: 10px;
	border: 0;
	color: white;
	font-size: 18px;
	border: 0;
}

.butt:hover {
	background-color: #71AAE3;
	border: 0;
}
</style>
<script type="text/javascript">
$(function(){
	$("#backup").click(function(){
		//alert("nihao")
		location.href="${ctx}/backupAndRestoreAction_backup.action";
	});
});
var message="${msg01}";
if(message!=""){
	alert(message);
}
</script> 
</head>
<%
	Users user = (Users) session.getAttribute("tu");
	if (user == null) {
		PrintWriter pw = response.getWriter();
		pw.println("<script type='text/javascript'>alert('未登录或登录已失效！请登录！');window.location.href = '"+basePath+"login.jsp';</script>");

	} else if (user.getRoleId() == 0) {
		PrintWriter pw = response.getWriter();
		pw.println("<script type='text/javascript'>alert('权限不够！切换账号登录');window.location.href = '"+basePath+"login.jsp';</script>");
	} else {
%>
<body>
	<div class="body">
		<div class="top">
			<img alt="" src="image/manager_top.jpg" />
		</div>
		<div class="left">
			<jsp:include page="./left.jsp"></jsp:include>
		</div>
		<div class="right">
			<div align="center" style="font-size: 24px;margin-top: 10px;">数 据 库
				 管 理</div>
			<div style="text-align: center;">
				<input id="backup" type="button" value="数据库备份" class="butt" style="width: 100px;"/>
				<form method="post" enctype="multipart/form-data" action="<%=basePath%>backupAndRestoreAction_restore.action">
					<!-- <div class="form-group">
						<div class="label">
							<label for="upfile">恢复数据需要选择f盘上的sql文件</label>
						</div>
						<div class="field">
							<a class="button input-file border-main" href="javascript:void(0);" id="alink"> +
								请选择上传文件 <input size="100" type="file" name="sqlfile" id="sqlfile">
							</a>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
							<button id="restore" class="button bg-main icon-check-square-o" type="submit">开始恢复</button>
						</div>
					</div> -->
					<div>
						<input type="text" class="inputText" style="width: 160px;" id="f_file"> 
						<input type="button" value="选择" class="butt"
							style="width: 50px;" onClick="fu.click()"> 
						<input name="sqlfile" type="file" id="fu"
							onchange="f_file.value=this.value" style="display:none">
						<div style="color: black;">请上传sql格式文件</div>
							<input type="submit" value="恢复" class="butt"
							style="height: 30px;width: 80px;font-size: 16px;" />
					</div>
					
					
						
					
					<!-- <p align="left" style="margin-left: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;允许上传sql格式文件!!!</p> -->
				</form>
			</div>

			

		</div>
	</div>

</body>
<%
	}
%>
</html>
