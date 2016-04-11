<%--
  Created by IntelliJ IDEA.
  User: birsan
  Date: 4/11/2016
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submitted page</title>
</head>
<body>
<p>You have successfully submitted!</p>
<p>The registered contact info is:</p>
<ol>
  <li>First name:<%=request.getAttribute("firstName")
  %></li>
  <li>Last name:<%=request.getAttribute("lastName")
  %></li>
  <li>Phone number:<%=request.getAttribute("phoneNumber")
  %></li>

</ol>

</body>
</html>
