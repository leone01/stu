<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 输出100-300之间所有的偶数 -->
	<!-- 如果当前数字的位置是3的倍数，则红色显示 -->
	<c:forEach begin="100" end="300" step="2"
	   var="i"  varStatus="vs">
	   <c:if test="${vs.count % 3==0}" var="flag">
	      <font style="color:red">${i} &nbsp;&nbsp;</font>
	   </c:if>
	   
	   <c:if test="${!flag}">
	      ${i} &nbsp;&nbsp;
	   </c:if>
	   
		
	</c:forEach>
	
	
	
	
	
</body>
</html>









