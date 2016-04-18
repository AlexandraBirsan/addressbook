<script src="scripts/create.js" type="text/javascript"></script>
<script src="scripts/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/main.css">
<%@ include file="header.jsp" %>
<body>
<form action="update" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="hidden" name="updatedId" value="${toBeUpdatedContact.id}"></td>
        </tr>
        <tr>
            <td><label>FirstName: </label></td>
            <td><input type="text" name="firstName" value="${toBeUpdatedContact.firstName}"/></td>
        </tr>
        <tr>
            <td><label>LastName: </label></td>
            <td><input type="text" name="lastName" value="${toBeUpdatedContact.lastName}"/></td>
        </tr>
        <tr>
            <td><label>Company: </label></td>
            <td><input type="text" name="company" value="${toBeUpdatedContact.company}"/></td>
        </tr>
        <tr>
            <td><label>Profile picture: </label></td>
            <td>
                <table>
                    <tr>
                        <c:choose>
                            <c:when test="${fn:length(toBeUpdatedContact.photo)>0}">
                                <td><img class="photo"
                                         src="data:${toBeUpdatedContact.contentType};base64, ${toBeUpdatedContact.photo}"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td><img class="photo" src="res/images/defaultPhoto.png"></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td><input type="file" name="picture" accept="image/*"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td><label>PhoneNumber: </label>
                <input type="button" onclick="addPhoneNumber()" value="+"/>
            </td>
            <td>
                <table id="phoneNumTable">
                    <c:forEach var="phoneNumber" items="${toBeUpdatedContact.phoneNumber}" varStatus="loop">
                        <tr id="${loop}">
                            <td><input type="text" name="phoneNumber" value="${phoneNumber.number}"/></td>
                            <td><input type="button" onclick="deleteRow('phoneNumTable',${loop.index})" value="delete">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
