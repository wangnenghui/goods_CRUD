<%@page import="com.jspsmart.upload.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.mldn.vo.*"%>
<%@ page import="java.io.*"%>
<%@ page import="cn.mldn.service.*"%>
<%@ page import="cn.mldn.service.impl.*"%>
<%@ page import="cn.mldn.util.factory.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8") ;	// 只要是JSP页面就必须存在此程序代码
	String url=basePath + "goods_list.jsp";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>JSP + Oracle实战开发</title>
	<script type="text/javascript" src="js/util.js"></script>
	<link rel="stylesheet" type="text/css" href="css/mldn.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<% 
		String ids=request.getParameter("ids");
		String result[]=ids.split("\\|");
		Set<Integer> gids=new HashSet<Integer>();
		Set<String> photos=new HashSet<String>();
		for(int x=0;x<result.length;x++){
			String temp[]=result[x].split(":");
			photos.add(temp[1]);
			gids.add(Integer.parseInt(temp[0]));
		}
		String msg="商品删除失败！";
		IGoodsService goodsService=ServiceFactory.getInstance(GoodServiceImpl.class);
		
		if(goodsService.remove(gids)){//删除成功
			Iterator<String> iter=photos.iterator();
			while(iter.hasNext()){
				String fileName=iter.next();
				if(!("nophoto.jpg".equals(fileName))){
					String filePath=this.getServletContext().getRealPath("/upload/goods/") + fileName;
					File file=new File(filePath);
					if(file.exists()){
						file.delete();
					}
				}				
			}
			msg="商品删除成功。";
		}
	%>
	<jsp:include page="/pages/plugins/time_div.jsp">
		<jsp:param value="<%= url%>" name="url"/>
		<jsp:param value="<%= msg%>" name="msg"/>
	</jsp:include>
</body>
</html>