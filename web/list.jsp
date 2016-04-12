<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>
    The contacts from the list are:
</p>

<p>
    <c:forEach var="contact" items="${allContacts}">
<table>
    <tr>
        <td>
            <label>FirstName</label>
        </td>
        <td>
            <label>${contact.firstName}</label>
        </td>
    </tr>
    <tr>
        <td><label>LastName</label></td>
        <td><label>${contact.lastName}</label></td>
    </tr>
    <tr>
        <td><label>CompanyName</label></td>
        <td><label>${contact.company}</label></td>
    </tr>
    <tr>
        <c:forEach var="phoneNum" items="${phoneNumber}">
    <tr>
        <td>${phoneNum}</td>
    </tr>
    </c:forEach>
    </tr>

</table>

</c:forEach>
</p>

</body>
</html>
