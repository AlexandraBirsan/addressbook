<%--
  Created by IntelliJ IDEA.
  User: birsan
  Date: 4/11/2016
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<html>
<body >
<p>You have successfully submitted!</p>
<p>The registered contact info is:</p>
<!--
<ol>
  <li>First name:%{contact.firstName}
  %></li>
  <li>Last name:<%=request.getAttribute("lastName")
  %></li>
  <li>Phone number:<%=request.getAttribute("phoneNumber")
  %></li>

</ol>
-->

<p>Where to go?</p>
<a href="create.jsp">Create contact</a><br/>
<a href="list">List all contacts</a>

</body>
</html>
