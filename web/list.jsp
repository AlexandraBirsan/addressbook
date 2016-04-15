<%@ include file="header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<body>
<p>
    <c:choose>
    <c:when test="${fn:length(allDTOContacts)>0}">
<p>
    The contacts from the list are:
</p>
<table id="myTable">
    <tr>
        <th><label>FirstName</label></th>
        <th><label>LastName</label></th>
        <th><label>CompanyName</label></th>
        <th>PhoneNumbers</th>
    </tr>
    <c:forEach var="contact" items="${allDTOContacts}">
        <tr>
            <td>
                <label>${contact.firstName}</label>
            </td>
            <td><label>${contact.lastName}</label></td>
            <td><label>${contact.company}</label></td>
            <td>
                <label>${contact.phoneNumber} </label>
            </td>
            <td>
                <form method="post" action="delete">
                    <input type="hidden" name="deletedId" value="${contact.id}"/>
                    <input type="submit" value="-"/>
                </form>
            </td>
            <c:choose>
                <c:when test="${fn:length(contact.photo)>0}">
                    <td><img class="photo" src="data:${contact.contentType};base64, ${contact.photo}" /></td>
                </c:when>
                <c:otherwise>
                    <td><img class="photo" src="res/images/defaultPhoto.png" ></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</c:when>
<c:otherwise>
    <p>There are no registered contacts. </p>
</c:otherwise>
</c:choose>
</body>
</html>
