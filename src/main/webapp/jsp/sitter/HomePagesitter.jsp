<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Welcome ${sessionScope.member.firstName}</h1>
<hr><br><br>

<div align="center">${requestScope.success}</div>
<br><br>

<div align="center">
<html:link action="sitter/listActiveJobs">List Active Jobs</html:link>
</div>
<br><br>

<div align="center">
<html:link action="sitter/listApplication">List Applications</html:link>
</div>
<br><br>

<div align="center">
<html:link action="sitter/listApplication">Delete Application</html:link>
</div>
<br><br>

<div align="center">
<html:link action="member/edit">Edit Profile</html:link>
</div>
<br><br>

<div align="center">
<html:link action="member/logOut">Log Out</html:link>
</div>
<br><br>

<div align="center">
<html:link action="member/closeAccount" onclick="return confirm('Do you really want to close your Account?');">Close Account
</html:link>
</div>
<br><br>

</body>
</html>