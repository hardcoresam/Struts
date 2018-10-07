<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <h1 align="center">List Of Jobs</h1>
  </div>
</div>

<jsp:include page="/jsp/seeker/Header.jsp"/>

<c:if test = "${param.editJob eq 'true'}">
    <div class="alert alert-success" align="center">Job Was Edited Successfully</div>
</c:if>

<c:if test = "${param.wrongJobId eq 'true'}">
    <div class="alert alert-danger" align="center">You are not authorized to do this Operation</div>
</c:if>

<c:if test = "${param.delete eq 'true'}">
    <div class="alert alert-success" align="center">Job was Deleted Successfully</div>
</c:if>

<div align="center"><b>${successMsg}</b></div>
<br>

<c:choose>
    <c:when test = "${fn:length(listOfJobs) == 0}">
        <h3><center>There are no Jobs posted by you.</center></h3>
        <h4><center>Do you want to Post one?</center></h4>
        <center><html:link action="seeker/postJob" styleClass="btn btn-link"><h4>Post Job</h4></html:link></center>
    </c:when>

<c:otherwise>
<div class="container">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Title</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Edit Job</th>
        <th>List Applications</th>
        <th>Close Job</th>
      </tr>
      </thead>

    <tbody>
    <c:forEach items="${requestScope.listOfJobs}" var="job">
    <tr>
        <td>${job.title}</td>
        <td>${job.startTime}</td>
        <td>${job.endTime}</td>
        <td>${job.startDate}</td>
        <td>${job.endDate}</td>

        <form action="${pageContext.request.contextPath}/seeker/editJob.do" method="POST">
            <input type = "hidden" name = "jobId" value = "${job.jobId}" >
            <td><input type= "submit" value= "Edit"></td>
        </form>

        <form action="${pageContext.request.contextPath}/seeker/listApplications.do" method="POST">
            <input type = "hidden" name = "jobId" value = "${job.jobId}">
            <td><input type= "submit" value= "List Applications"></td>
        </form>

        <form action="${pageContext.request.contextPath}/seeker/deleteJob.do" method="POST"
            onsubmit="return confirm('Do you really want to delete the Job?');">
            <input type = "hidden" name = "jobId" value = "${job.jobId}">
            <td><input type= "submit" value= "Delete"></td>
        </form>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</c:otherwise>
</c:choose>

</body>
</html>