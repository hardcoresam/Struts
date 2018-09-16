<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Apply To Job</h1>
<hr><br><br>

<html:form action="sitter/applyJob" method="POST">

<table border="1" align="center">
<tr>
<td>Enter Expected Pay*:</td>
<td><html:text property="expectedPay"/></td>
<font color="red"><html:errors property="expectedPay"/></font>
</tr>

<tr>
<td><html:submit value="Apply"/></td>
</tr>

<!-- Check this,bcoz from the second time there will be no param.jobId anymore bcoz request will not be there anymore.
But after the first time we will populate this inside ApplyJobForm. so check whether we will get the value from that form or not. -->

<html:hidden property="jobId" value="${param.type}"/>

</table>

</html:form>

</body>
</html>