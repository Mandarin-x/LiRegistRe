<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>"></base>


<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add New Reader</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script>
            function checkPhone(){
                var account = form1.Account.value;
                if(!(/^1[3456789]\d{9}$/.test(account))){
                    alert("手机号码有误，请重填");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div class="title">
            <h3>Add New Reader</h3>
        </div>
        <div class="form" >
            <form name="form1" method="get" action="">
                <input type="text" placeholder="Account" required="required" name="Account">
                <br/><br/>
                <input type="email" placeholder="Email" required="required" name="Email">
                <br/><br/>
                <input type="submit" value="Submit" onclick="checkPhone()">
            </form>
        </div>

		ifRegistSuc = ${ifRegistSuc} <br/>
		
		<c:choose>
		
			<c:when test="${ifRegistSuc==0}">
				<span>=======(弹出窗口或跳转页面)提示 ：  未注册成功，该用户的id或Email已被注册过=======</span>
			</c:when>
		
			<c:when test="${ifRegistSuc==1}">
				<span>=======(弹出窗口或跳转页面)提示 ：  该读者账户创建成功=======</span>
			</c:when>
			
			<c:otherwise></c:otherwise>
			
		</c:choose>
		
    </body>
</html>