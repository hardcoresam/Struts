<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Error</h1>
<hr><br><br>

<div align = "center">You are not authorized to view this page.</div>
<br>


<html:link action="visitor/login">Home/Login</html:link>


<form action="${pageContext.request.contextPath}/jsp/visitor/Login.jsp" method="POST">
<div align ="center"><input type="submit" value="Home/Login"></div>
</form>
</body>
</html>