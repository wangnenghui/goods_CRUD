 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "goods_add_do.jsp" ;

%>
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript.jsp"/>
</head>
<body> 

<form action="goods_add_do.jsp" method="post" enctype="multipart/form-data">
<table border="1" style="width:100%;">
	<tr>
		<td colspan="2">添加商品信息</td>
	</tr>
	<tr>
		<td>商品名称：</td>
		<td><input type="text" id="title" name="title"></td>
	</tr>
	<tr>
		<td>商品分类：</td>
		<td>
			<select id="iid" name="iid">
			
					<option value="1">图书音像</option>
			
					<option value="2">办公用品</option>
			
					<option value="3">家居生活</option>
			
					<option value="4">厨房家电</option>
			
					<option value="5">电子设备</option>
			
			</select>
		</td>
	</tr>
	<tr>
		<td>商品价格：</td>
		<td><input type="text" id="price" name="price"></td>
	</tr>
	<tr>
		<td>商品图片：</td>
		<td><input type="file" id="photo" name="photo"></td>
	</tr>
	<tr>
		<td>商品标签：</td>
		<td>
			
				<input type="checkbox" id="tid" name="tid" value="1">高端
			
				<input type="checkbox" id="tid" name="tid" value="2">奢华
			
				<input type="checkbox" id="tid" name="tid" value="3">性价比高
			
				<input type="checkbox" id="tid" name="tid" value="4">免费
			
				<input type="checkbox" id="tid" name="tid" value="5">耐用
			
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="增加">
			<input type="reset" value="重置">
		</td>
	</tr>
</table>
</form>
</body>
</html>