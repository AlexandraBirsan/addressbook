<%@ include file="header.jsp" %>
<body>
<form action="update" method="post" enctype="multipart/form-data">
  <table>
    <tr>
      <td><label>FirstName: </label></td>
      <td><input type="text" name="firstName" value="${contact.firstName}"/></td>
    </tr>
    <tr>
      <td><label>LastName: </label></td>
      <td><input type="text" name="lastName" value="${contact.lastName}"/></td>
    </tr>
    <tr>
      <td><label>Company: </label></td>
      <td><input type="text" name="company" value="${contact.company}"/></td>
    </tr>
    <tr>
      <td><label>Profile picture: </label></td>
      <td><input type="file" name="picture" accept="image/*" /></td>
    </tr>
    <tr>
      <td><label>PhoneNumber: </label></td>
      <td>
        <table id="phoneNumTable">
          <tr>
            <td><input type="text" name="phoneNumber"/></td>
            <td>
              <input type="button" onclick="addPhoneNumber()" value="+"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="submit" value="Submit"/>
</form>
</body>
</html>
