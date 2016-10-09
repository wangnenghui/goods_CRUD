 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.mldn.service.impl.GoodServiceImpl"%>
<%@page import="cn.mldn.util.factory.ServiceFactory"%>
<%@page import="cn.mldn.service.IGoodsService"%>
<%@page import="java.util.UUID"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="cn.mldn.vo.Goods"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");

%>
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body> 
<%
	String url=basePath+"goods_list.jsp";
	String msg="商品信息修改失败！";
	SmartUpload smart=new SmartUpload();
	smart.initialize(config, request, response);
	smart.upload();
	Goods vo=new Goods();
	vo.setGid(Integer.parseInt(smart.getRequest().getParameter("gid")));
	vo.setTitle(smart.getRequest().getParameter("title"));
	vo.setPrice(Double.parseDouble(smart.getRequest().getParameter("price")));
	vo.setIid(Integer.parseInt(smart.getRequest().getParameter("iid")));
	vo.setPhoto(smart.getRequest().getParameter("oldpic"));
	String tids[]=smart.getRequest().getParameterValues("tid");
	
	Set<Integer> allTids=new HashSet<Integer>();
	for(int x=0;x<tids.length;x++){
		allTids.add(Integer.parseInt(tids[x]));
	}
	vo.setTids(allTids);
	if(smart.getFiles().getSize()>0){
		if("nophoto.jpg".equals(vo.getPhoto())){
			if(smart.getFiles().getFile(0).getContentType().contains("image")){
				String fileName=UUID.randomUUID()+"."+smart.getFiles().getFile(0).getFileExt();
				vo.setPhoto(fileName);
			}
		}
	} 
	IGoodsService goodsService=ServiceFactory.getInstance(GoodServiceImpl.class);
	if(goodsService.edit(vo)){
		if(smart.getFiles().getSize()>0){
			String filePath=this.getServletContext().getRealPath("/upload/goods/")+vo.getPhoto();
			smart.getFiles().getFile(0).saveAs(filePath);
		}
		msg="商品修改成功！";
	}
 %>
 <jsp:include page="/pages/plugins/time_div.jsp">
 	<jsp:param value="<%=url %>" name="url"/>
 	<jsp:param value="<%=msg %>" name="msg"/>
 </jsp:include>
</body>







</html>