<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Welcome ${sessionScope.member.firstName}</h1>
<hr><br><br>

<jsp:include page="/jsp/seeker/Header.jsp"/>     //Change this position and include this in every jsp.
<br>

<div align="center">${requestScope.success}</div>
<br>

<div align="center">
<html:link action="seeker/postJob">Post Job</html:link>
</div>
<br><br>

<div align="center">
<html:link action="seeker/listJobs">List Jobs</html:link>
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