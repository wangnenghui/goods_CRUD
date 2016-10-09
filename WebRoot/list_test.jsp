 <%@page import="cn.mldn.vo.Goods"%>
<%@page import="cn.mldn.service.impl.GoodServiceImpl"%>
<%@page import="cn.mldn.dao.impl.GoodsDAOImpl"%>
<%@page import="cn.mldn.util.factory.ServiceFactory"%>
<%@page import="cn.mldn.service.IGoodsService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript.jsp"/>
</head>
<body> 
<%
	int currentPage=1;
	int lineSize=10;
	String column=null;
	String keyWord=null;
	String columnData="商品名称：title";
	int allRecorders=0 ;
 %>
 <%
 	try{
 		currentPage=Integer.parseInt(request.getParameter("cp"));
 	}catch(Exception e){}
 	try{
 		lineSize=Integer.parseInt(request.getParameter("ls"));
 	}catch(Exception e){}
 	column=request.getParameter("col");
 	keyWord=request.getParameter("kw");
  %>
 <%
 	String url=basePath+"goods_list.jsp";
 	String ediUrl=basePath+"goods_edit.jsp";
 	IGoodsService goodsService=ServiceFactory.getInstance(GoodServiceImpl.class);
 	Map<String,Object> map=goodsService.list(column, keyWord, currentPage, lineSize);
 	allRecorders=(Integer) map.get("goodsCount");
 	List<Goods> allGoods=(List<Goods>)map.get("allGoods");
 	Iterator<Goods> iter=allGoods.iterator();
  %>
<form action="goods_add_do.jsp" method="post" enctype="multipart/form-data">
<jsp:include page="/pages/plugins/split_page_bar_plugin.jsp">
	<jsp:param name="url" value="<%=url%>"/>
	<jsp:param name="currentPage" value="<%=currentPage%>"/>
	<jsp:param name="lineSize" value="<%=lineSize%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
	<jsp:param name="column" value="<%=column%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
</jsp:include>
<div>
<table border="1" style="width:100%;">
	<tr>
		<td><input type="checkbox" id="selall"></td>
		<td>商品名称</td>
		<td>商品价格</td>
	</tr>
<%
	while(iter.hasNext()){
		Goods vo=iter.next();
%>
<tr>
	<td><input type="checkbox" id="gid"></td>
	<td><%=vo.getTitle() %></td>
	<td><%=vo.getPrice() %></td>
</tr>
<%
}
 %>	
</div>
<div><input type="button" value="删除所选商品信息" id="deleteBut"></div>
<jsp:include page="/pages/plugins/split_page_bar_plugin.jsp">
	<jsp:param name="url" value="<%=url%>"/>
	<jsp:param name="currentPage" value="<%=currentPage%>"/>
	<jsp:param name="lineSize" value="<%=lineSize%>"/>
	<jsp:param name="keyWord" value="<%=keyWord%>"/>
	<jsp:param name="column" value="<%=column%>"/>
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
</jsp:include>
</table>
</form>
</body>
</html>