<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">List Of Applications</h1>
<hr><br><br>

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

<td>${application.expectedPay}</td>
<td>${application.job.payPerHour}</td>

<td>${application.job.title}</td>



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
