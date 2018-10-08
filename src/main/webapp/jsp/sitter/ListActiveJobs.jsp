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
    <h1 align="center">List of Active Jobs</h1>
  </div>
</div>

<jsp:include page="/jsp/member/Header.jsp"/>

<c:if test = "${param.jobNotValid eq 'true'}">
    <div class="alert alert-success" align="center">You are not authorized to apply for this Job</div>
</c:if>

<div class="container">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Title</th>
        <th>Pay Per Hour</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Apply To Job</th>
      </tr>
    </thead>

    <tbody>
    <c:forEach items="${requestScope.listOfActiveJobs}" var="job">
    <tr>
        <td>${job.title}</td>
        <td>${job.payPerHour}</td>
        <td>${job.startDate}</td>
        <td>${job.endDate}</td>
        <td>${job.startTime}</td>
        <td>${job.endTime}</td>

        <form action="${pageContext.request.contextPath}/sitter/applyJob.do" method="POST">
            <input type = "hidden" name = "jobId" value = "${job.jobId}" >
        <td><input type= "submit" value= "Apply"></td>
        </form>

    </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>