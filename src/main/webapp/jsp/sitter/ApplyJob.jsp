<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Apply To Job</h1>
<hr><br><br>

<html:form action="sitter/applyJob" method="POST">

<table border="0" align="center">
<tr>
<td>Enter Expected Pay*:</td>
<td><html:text property="expectedPay"/></td>
<td><font color="red"><html:errors property="expectedPay"/></font></td>
</tr>

<tr>
<td><html:submit value="Apply"/></td>
</tr>

<html:hidden property="jobId" value="${param.jobId}"/>

</table>

</html:form>

</body>
</html>