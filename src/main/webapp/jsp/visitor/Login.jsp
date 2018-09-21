<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Welcome</h1>
<hr><br><br>
<h3 align="center">Login Here</h3>
<br>

<c:if test = "${param.logout eq 'true'}">
    <div align="center">You Have Successfully Logged Out.</div>
</c:if>
<br>


<c:if test = "${param.close eq 'true'}">
    <div align="center">You have successfully closed your Account</div>
</c:if>
<br>


<html:form action="visitor/login" method="POST">

<table border="0" align="center">

<tr>
<td><font color="red"><c:out value='${loginError1}'/></font></td>   <!-- Check this. This is there in LoginFilter. -->
</tr>

<tr>
<td><font color="red"><c:out value='${loginError}'/></font></td>
</tr>

<tr>
<td>Enter Email:*</td>
<td><html:text property="email"/></td>
<td><font color="red"><html:errors property="email"/></font></td>
</tr>

<tr>
<td>Enter Password:*</td>
<td><html:password property="password"/></td>
<td><font color="red"><html:errors property="password"/></font></td>
</tr>

<tr>
<td><html:submit value="Login"/></td>
</tr>

</html:form>
</table>
<br><br>

<div align = "center">New User ?</div>
<div align = "center"><html:link action="visitor/choice">Register</html:link></div>

</body>
</html>