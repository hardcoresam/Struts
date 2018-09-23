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
    <h1 align="center">Post Your Job Here</h1>
  </div>
</div>

<html:form action="seeker/postJobAction" method="POST">
<div class="container">
<div class="row">
<div class="col-xs-5 col-xs-offset-4">

  <div class="form-group">
    <label>Title:</label>
	<html:text property="title" styleClass="form-control"/>
	<font color="red"><html:errors property="title"/></font>
  </div>
  <div class="form-group">
    <label>Pay Per Hour:</label>
  	<html:text property="payPerHour" styleClass="form-control"/>
  	<font color="red"><html:errors property="payPerHour"/></font>
  </div>
  <div class="form-group">
    <label>Start Time:</label>
  	<input type="time" name="startTime" value="${PostJob.startTime}" class="form-control">
  	<font color="red"><html:errors property="startTime"/></font>
  </div>
  <div class="form-group">
    <label>End Time:</label>
    <input type="time" name="endTime" value="${PostJob.endTime}" class="form-control">
  	<font color="red"><html:errors property="endTime"/></font>
  </div>
  <div class="form-group">
    <label>Start Date:</label>
    <input type="date" name="startDate" value="${PostJob.startDate}" class="form-control">
    <font color="red"><html:errors property="startDate"/></font>
  </div>
  <div class="form-group">
    <label>End Date:</label>
    <input type="date" name="endDate" value="${PostJob.endDate}" class="form-control">
    <font color="red"><html:errors property="endDate"/></font>
  </div>

    <html:submit value="Post Job" styleClass="btn btn-default"/>

</div>
</div>
</div>
</html:form>

</body>
</html>