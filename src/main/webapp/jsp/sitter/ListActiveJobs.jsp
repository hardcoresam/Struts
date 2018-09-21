<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">List Of Active Jobs</h1>
<hr><br><br>

<c:if test = "${param.jobNotValid eq 'true'}">
    <div align="center">You are not authorized to apply for this Job</div>
</c:if>
<br>

<table border="1" align="center">

<tr>
<th>Title</th>
<th>Pay Per Hour</th>
<th>Start Date</th>
<th>End Date</th>
<th>Start Time</th>
<th>End Time</th>
<th>Apply To Job</th>
</tr>

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
    <td>
        <input type= "submit" value= "Apply">
    </td>
</form>

</tr>

</c:forEach>

</table>
</body>
</html>
