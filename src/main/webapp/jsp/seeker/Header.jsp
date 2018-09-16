<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<head>
<style>
.right{ float: right; }
</style>
</head>
<body>
<h1>Minicare</h1>

<div class="right">
<html:link action="seeker/homePage">Home</html:link>
</div>

<div class="right">
<html:link action="member/logOut">Log Out</html:link>
</div>

</body>
</html>