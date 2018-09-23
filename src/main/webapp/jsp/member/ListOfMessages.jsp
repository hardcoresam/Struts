<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
   function isEmpty() {
       var x = document.forms["myForm"]["message"].value;
       if (x == "") {
           alert("Please enter something to send!");
           return false;
       }
   }
</script>

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
<table class="table table-striped">
    <thead>
      <tr>
        <th>Message</th>
        <th>Username</th>
        <th>Time</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listOfMessages}" var="message">
     <tr>
        <td>${message.content}</td>
        <td>${message.member.firstName}</td>
        <td>${message.time}</td>
     </tr>
    </c:forEach>
    </tbody>
</table>
</div><br>

<form class="form-inline" name="myForm" action="${pageContext.request.contextPath}/member/storeMessage.do"
    method="POST" onsubmit="return isEmpty()">
<div class="container">
<div class="row">
<div class="col-xs-5 col-xs-offset-4">

    <input type = "hidden" name="conversationId" value="${conversationId}">

    <div class="form-group">
        <label>Enter Your Message:</label>
    	<textarea rows="4" cols="50" name="message" class="form-control"></textarea>
    </div><br><br>

    <input type= "submit" value= "Send" class="btn btn-default"><br><br>

</div>
</div>
</div>
</form>

</body>
</html>