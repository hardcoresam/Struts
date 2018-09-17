<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Edit Your Job Here</h1>
<hr><br><br>

<html:form action="seeker/alterJob" method="POST">

<table border="0" align="center">
<tr>
<td>Enter Title:*</td>
<td><html:text property="title"/></td>
<td><font color="red"><html:errors property="title"/></font></td>
</tr>

<tr>
<td>Enter Pay Per Hour:*</td>
<td><html:text property="payPerHour"/></td>
<td><font color="red"><html:errors property="payPerHour"/></font></td>
</tr>

<tr>
<td>Enter Start Time:*</td>
<td><input type="time" name="startTime" value="<bean:write name="PostJob" property="startTime"/></td>
<td><font color="red"><html:errors property="startTime"/></font></td>
</tr>

<tr>
<td>Enter End Time:*</td>
<td><input type="time" name="endTime" value="<bean:write name="PostJob" property="endTime"/>"></td>
<td><font color="red"><html:errors property="endTime"/></font></td>
</tr>

<tr>
<td>Enter Start Date:*</td>
<td><input type="date" name="startDate" value="<bean:write name="PostJob" property="startDate"/>"></td>
<td><font color="red"><html:errors property="startDate"/></font></td>
</tr>

<tr>
<td>Enter End Date:*</td>
<td><input type="date" name="endDate" value="<bean:write name="PostJob" property="endDate"/>"></td>
<td><font color="red"><html:errors property="endDate"/></font></td>
</tr>

<tr>
<td><html:submit value="Edit Job"/></td>
</tr>

</table>

</html:form>
</body>
</html>