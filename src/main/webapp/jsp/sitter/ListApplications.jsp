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
    <h1 align="center">List of Applications</h1>
  </div>
</div>

<c:if test = "${param.apply eq 'true'}">
    <div align="center" class="alert alert-success">You have applied for the job Successfully</div>
</c:if>

<c:if test = "${param.delete eq 'true'}">
    <div align="center" class="alert alert-success">Application was deleted Successfully</div>
</c:if>

<c:if test = "${param.delete eq 'false'}">
    <div align="center" class="alert alert-danger">You are not authorized to delete this Application</div>
</c:if>

<div class="container">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Title</th>
        <th>Expected Pay</th>
        <th>Pay Per Hour</th>
        <th>Action</th>
        <th>Message</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listOfApplications}" var="application">
    <tr>
        <td>${application.job.title}</td>
        <td>${application.expectedPay}</td>
        <td>${application.job.payPerHour}</td>

        <form action="${pageContext.request.contextPath}/sitter/deleteApplication.do" method="POST"
            onsubmit="return confirm('Do you really want to delete this Application?');">
            <input type = "hidden" name = "applicationId" value = "${application.applicationId}" >
            <td><input type= "submit" value= "Delete"></td>
        </form>

        <form action="${pageContext.request.contextPath}/member/sendMessage.do" method="POST">
            <input type = "hidden" name = "memberId" value = "${application.job.seeker.memberId}" >
            <td><input type= "submit" value= "Message Him"></td>
        </form>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>