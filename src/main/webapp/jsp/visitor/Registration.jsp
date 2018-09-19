<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>


<!-- This is how you should use Bootstrap (Kranthi)
<head>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
-->


<body>
<h1 align="center">Registration Page</h1>
<hr><br><br>

<!-- BOOTSTRAP
<div class="container">
<div class="row">
<div class="col-xs-4 col-xs-offset-4">
<form action="${pageContext.request.contextPath}/visitor/registration.do" method="POST">

  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" class="form-control" id="email">
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <input type="password" class="form-control" id="pwd">
  </div>
  <div class="checkbox">
    <label><input type="checkbox"> Remember me</label>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
</div>
</div>
</div>

-->

<html:form action="visitor/registration" method="POST">

<table border="0" align="center">
<tr>
<td>Enter First Name:*</td>
<td><html:text property="firstName"/></td>
<td><font color="red"><html:errors property="firstName"/></font></td>
</tr>

<tr>
<td>Enter Last Name:</td>
<td><html:text property="lastName"/></td>
<td><font color="red"><html:errors property="lastName"/></font></td>
</tr>

<tr>
<td>Enter Email:*</td>
<td><html:text property="email"/></td>
<td><font color="red"><html:errors property="email"/></font></td>
</tr>

<tr>
<td>Enter Phone Number:*</td>
<td><html:text property="phoneNumber"/></td>
<td><font color="red"><html:errors property="phoneNumber"/></font></td>
</tr>

<tr>
<td>Enter Address:*</td>
<td><html:textarea property="address" rows="4" cols="40"/></td>
<td><font color="red"><html:errors property="address"/></font></td>
</tr>



<tr>
<td><html:hidden property="type"/></td>
<td><font color="red"><html:errors property="type"/></font></td>
</tr>



<c:choose>
    <c:when test='${Registration.type=="seeker"}'>
        <tr>
        <td>Enter No of Children:*</td>
        <td><html:text property="noOfChildren"/></td>
        <td><font color="red"><html:errors property="noOfChildren"/></font></td>
        </tr>

        <tr>
        <td>Enter Spouse Name:*</td>
        <td><html:text property="spouseName"/></td>
        <td><font color="red"><html:errors property="spouseName"/></font></td>
        </tr>
    </c:when>

    <c:when test='${Registration.type=="sitter"}'>
        <tr>
        <td>Enter Experience:*</td>
        <td><html:text property="experience"/></td>
        <td><font color="red"><html:errors property="experience"/></font></td>
        </tr>

        <tr>
        <td>Enter Expected Pay:*</td>
        <td><html:text property="expectedPay"/></td>
        <td><font color="red"><html:errors property="expectedPay"/></font></td>
        </tr>
    </c:when>
</c:choose>

<tr>
<td>Enter Password:*</td>
<td><html:password property="password"/></td>
<td><font color="red"><html:errors property="password"/></font></td>
</tr>

<tr>
<td><html:submit value="Register"/></td>
</tr>

</table>

</html:form>

</body>
</html>