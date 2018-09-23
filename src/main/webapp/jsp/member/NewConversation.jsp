<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
  <div class="jumbotron">
    <h1 align="center">Messaging</h1>
  </div>
</div>

<div class="container">
<table class="table table-striped" align="center">
    <thead>
      <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.newConversation}" var="member">
    <tr>
        <td>${member.firstName}</td>
        <td>${member.email}</td>
        <td>${member.phoneNumber}</td>

    <form action="${pageContext.request.contextPath}/member/sendMessage.do" method="POST">
        <input type = "hidden" name = "memberId" value = "${member.memberId}">
        <td><input type= "submit" value= "Message Him"></td>
    </form>

    </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>