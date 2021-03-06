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
    <h1 align="center">Welcome ${sessionScope.member.firstName}</h1>
  </div>
</div>

<jsp:include page="/jsp/seeker/Header.jsp"/>

<c:if test = "${param.jobPosted eq 'true'}">
    <div class="alert alert-success" align="center">Job Was Posted Successfully</div>
</c:if>

<c:if test = "${param.editProfile eq 'true'}">
    <div class="alert alert-success" align="center">Your Profile was Updated Successfully</div>
</c:if>

<div align="center">${requestScope.success}</div>
<br>

<div class="container">
<div align="center">
<html:link action="seeker/postJob" styleClass="btn btn-link">Post Job</html:link>
</div><br>

<div align="center">
<html:link action="seeker/listJobs" styleClass="btn btn-link">List Jobs</html:link>
</div><br>

<div align="center">
<html:link action="member/messages" styleClass="btn btn-link">Messages</html:link>
</div><br>

<div align="center">
<html:link action="member/edit" styleClass="btn btn-link">Edit Profile</html:link>
</div><br>

<div align="center">
<html:link action="member/logOut" styleClass="btn btn-link">Log Out</html:link>
</div><br>

<div align="center">
<html:link action="member/closeAccount" styleClass="btn btn-link" onclick="return confirm('Do you really want to close your Account?');">Close Account
</html:link>
</div><br>

</div>
</body>
</html>