<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">List Of Applications</h1>
<hr><br><br>

<c:if test = "${param.apply eq 'true'}">
    <div align="center">You have applied for the job Successfully</div>
</c:if>
<br>

<c:if test = "${param.delete eq 'true'}">
    <div align="center">Application was deleted Successfully</div>
</c:if>
<br>

<c:if test = "${param.delete eq 'false'}">
    <div align="center">You are not authorized to delete this Application</div>
</c:if>
<br>


<div align="center">${requestScope.success}</div>
<br>

<table border="1" align="center">

<tr>
<th>Title</th>
<th>Expected Pay</th>
<th>Pay Per Hour</th>
<th>Action</th>
<th>Message</th>
</tr>

<c:forEach items="${requestScope.listOfApplications}" var="application">

<tr>
<td>${application.job.title}</td>
<td>${application.expectedPay}</td>
<td>${application.job.payPerHour}</td>


<form action="${pageContext.request.contextPath}/sitter/deleteApplication.do" method="POST"
      onsubmit="return confirm('Do you really want to delete this Application?');">
    <input type = "hidden" name = "applicationId" value = "${application.applicationId}" >
    <td>
        <input type= "submit" value= "Delete">
    </td>
</form>


<form action="${pageContext.request.contextPath}/member/sendMessage.do" method="POST">
    <input type = "hidden" name = "seekerId" value = "${application.job.seeker.memberId}" >
    <td>
        <input type= "submit" value= "Message Him">
    </td>
</form>

</tr>

</c:forEach>

</table>
</body>
</html>
