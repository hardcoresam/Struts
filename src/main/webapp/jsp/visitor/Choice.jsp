<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Welcome</h1>
<hr><br><br>

<font color="red"><div align="center">${errorType}</div></font>
<br>

<form action="${pageContext.request.contextPath}/visitor/register.do" method="POST">
<div align="center"><input type = "submit" name="type" value="seeker"></div>
<br><br><br>
<div align="center"><input type = "submit" name="type" value="sitter"></div>
</form>

</body>
</html>