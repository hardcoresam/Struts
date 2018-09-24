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
    <h1 align="center">Welcome</h1>
    <h3 align="center">Login Here</h3>
  </div>
</div>

<c:if test = "${param.logout eq 'true'}">
    <div class="alert alert-success" align="center">You Have Successfully Logged Out.</div>
</c:if>

<c:if test = "${param.close eq 'true'}">
    <div class="alert alert-success" align="center">You have successfully closed your Account</div>
</c:if>

<html:form action="visitor/login" method="POST">

<div class="container">
<div class="row">
<div class="col-xs-5 col-xs-offset-4">

  <font color="red">${loginError}</font>

  <div class="form-group">
    <label>Email:</label>
	<html:text property="email" styleClass="form-control"/>
	<font color="red"><html:errors property="email"/></font>
  </div>
  <div class="form-group">
    <label>Password:</label>
	<html:password property="password" styleClass="form-control"/>
	<font color="red"><html:errors property="password"/></font>
  </div>

  <html:submit value="Login" styleClass="btn btn-default"/>
  <br><br><br>

</div>
</div>
</div>

</html:form>

<div align = "center">New User ?</div>
<div align = "center"><html:link action="visitor/choice">Register</html:link></div>

</body>
</html>