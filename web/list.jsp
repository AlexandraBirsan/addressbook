<%@ include file="header.jsp" %>
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
        <th style="text-align:left;vertical-align:top;padding:0">PhoneNumbers</th>
    </tr>
    <c:forEach var="contact" items="${allDTOContacts}">

        <tr>
            <td>
                <label>${contact.firstName}</label>
            </td>

            <td><label>${contact.lastName}</label></td>
            <td><label>${contact.company}</label></td>
            <td>
                <label>${contact.phoneNumber}</label>
            </td>
            <td>
                <form method="post" action="delete">
                    <input type="hidden" name="deletedId" value="${contact.id.toString()}"/>
                    <input type="submit" value="-"/>
                </form>
            </td>
        </tr>


    </c:forEach>
</table>
</c:when>
<c:otherwise>
    <p>There are no registered contacts. </p>
</c:otherwise>
</c:choose>
</p>

</body>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</html>
