<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
  <div class="jumbotron">
    <h1 align="center">Edit Your Profile Here</h1>
  </div>
</div>

<html:form action="member/editProfile" method="POST">

<div align="center"><font color="red"><html:errors property="type"/></font></div>

<div class="container">
<div class="row">
<div class="col-xs-5 col-xs-offset-4">

  <div class="form-group">
    <label>First Name:</label>
	<html:text property="firstName" styleClass="form-control"/>
	<font color="red"><html:errors property="firstName"/></font>
  </div>
  <div class="form-group">
    <label>Last Name:</label>
	<html:text property="lastName" styleClass="form-control"/>
	<font color="red"><html:errors property="lastName"/></font>
  </div>
  <div class="form-group">
    <label>Phone Number:</label>
	<html:text property="phoneNumber" styleClass="form-control"/>
	<font color="red"><html:errors property="phoneNumber"/></font>
  </div>
  <div class="form-group">
    <label>Address:</label>
	<html:textarea property="address" rows="4" cols="40" styleClass="form-control"/>
	<font color="red"><html:errors property="address"/></font>
  </div>

<c:choose>
  <c:when test='${Registration.type=="seeker"}'>
  <div class="form-group">
    <label>No of Children:</label>
	<html:text property="noOfChildren" styleClass="form-control"/>
	<font color="red"><html:errors property="noOfChildren"/></font>
  </div>
  <div class="form-group">
    <label>Spouse Name:</label>
	<html:text property="spouseName" styleClass="form-control"/>
	<font color="red"><html:errors property="spouseName"/></font>
  </div>
  </c:when>

  <c:when test='${Registration.type=="sitter"}'>
  <div class="form-group">
    <label>Experience:</label>
	<html:text property="experience" styleClass="form-control"/>
	<font color="red"><html:errors property="experience"/></font>
  </div>
  <div class="form-group">
    <label>Expected Pay:</label>
	<html:text property="expectedPay" styleClass="form-control"/>
	<font color="red"><html:errors property="expectedPay"/></font>
  </div>
  </c:when>
</c:choose>

  <html:submit value="Edit User" styleClass="btn btn-default"/>
  <html:hidden property="type"/>
  <br><br><br>

</div>
</div>
</div>
</html:form>

</body>
</html>