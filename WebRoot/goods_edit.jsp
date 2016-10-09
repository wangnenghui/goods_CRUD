<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.mldn.vo.*" %>
<%@ page import="cn.mldn.util.factory.*" %>
<%@ page import="cn.mldn.service.*" %>
<%@ page import="cn.mldn.service.impl.*" %>
<%@page import="com.jspsmart.upload.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl=basePath + "goods_edit_do.jsp";
%>
<html>
<head>
	<title>商品信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/util.js"></script>
<link rel="stylesheet" type="text/css" href="css/mldn.css">
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body> 
<% 
	int gid=Integer.parseInt(request.getParameter("gid"));
	//通过业务层取得所有增加时所需要的数据
	IGoodsService goodsService=ServiceFactory.getInstance(GoodServiceImpl.class);
	Map<String,Object> map=goodsService.editPre(gid); 
	
	List<Item> allItems=(List<Item>)map.get("allItems");
	List<Tag> allTags=(List<Tag>)map.get("allTags");
	
	Goods vo=(Goods)map.get("goods");
	Iterator<Item> iterItem=allItems.iterator();
	Iterator<Tag> iterTag=allTags.iterator();
	Map<Integer,String> tidsMap=new HashMap<Integer,String>();
	Iterator<Integer> iterTid=vo.getTids().iterator();
	
	while(iterTid.hasNext()){
		tidsMap.put(iterTid.next(),"checked");
	}
%>

<form action=<%= editUrl%> method="post" enctype="multipart/form-data">
<table border="1" style="width:100%;">
	<tr>
		<td colspan="2">编辑商品信息</td>
	</tr>
	<tr>
		<td style="width:10%">商品名称：</td>
		<td style="width:50%"><input type="text" id="title" name="title" value="<%= vo.getTitle()%>"></td>
		<td style="whidth:40%"rowspan="5"> <img src="upload/goods/<%= vo.getPhoto()%>" style="width:50%"></td>
	</tr>
	<tr>
		<td>商品分类：</td>
		<td>
			<select id="iid" name="iid">
			<% 
				while(iterItem.hasNext()){
					Item item=iterItem.next();
			%>
			
					<option value="<%= item.getIid()%>" <%= vo.getIid().equals(item.getIid())?"selected":""%>><%= item.getTitle()%></option>
			<% 
				}
			%>
			</select>
		</td>
	</tr>
	<tr>
		<td>商品价格：</td>
		<td><input type="text" id="price" name="price" value="<%= vo.getPrice()%>"></td>
	</tr>
	<tr>
		<td>商品图片：</td>
		<td><input type="file" id="photo" name="photo"></td>
	</tr>
	<tr>
		<td>商品标签：</td>
		<td>
			<% 
				while(iterTag.hasNext()){
					Tag tag=iterTag.next();			
			%>
				<input type="checkbox" id="tid" name="tid" value="<%= tag.getTid()%>"  <%= tidsMap.get(tag.getTid()) != null ? tidsMap.get(tag.getTid()) : ""%>><%= tag.getTitle()%>
			<% 
				}
			%>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" id="gid" name="gid" value="<%= vo.getGid()%>">
			<input type="hidden" id="oldpic" name="oldpic" value="<%= vo.getPhoto()%>">
			<input type="submit" value="编辑">
			<input type="reset" value="重置">
		</td>
	</tr>
</table>
</form>
</body>
</html>