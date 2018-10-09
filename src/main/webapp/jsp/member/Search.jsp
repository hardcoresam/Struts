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

<style>
#myInput {
<!-- Ask Kranthi How to get this image out there and also make the Search text box a bit smaller and also in the center -->
  background-image: url('/src/main/webapp/jsp/member/searchicon.png');
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}
</style>
</head>

<body onload="javascript:hideTable()">

<script>

function hideTable(){
document.getElementById('myTable').style.visibility = "hidden";
}

function showTable(){
document.getElementById('myTable').style.visibility = "visible";
}

function noResults() {
document.getElementById('noResults').style.visibility = "visible";
document.getElementById('noResults').innerHTML="No Results to show";
}

function hideHeader() {
document.getElementById('noResults').style.visibility = "hidden";
}

function myFunction() {
  hideHeader();
  showTable();
  var input,filter, table, tr, td, i;
  input = document.getElementById("myInput");

  var temp = document.getElementById("myInput").value;
  var count=0;

  if(temp.length == 0) {
  	hideTable();
  }

  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
        count++;
      } else {
        tr[i].style.display = "none";
      }
    }
  }

  if(count==0) {
  	hideTable();
  	noResults();
  }

}
</script>

<div class="container">
  <div class="jumbotron">
    <h1 align="center">Search here</h1>
  </div>
</div>

<jsp:include page="/jsp/member/Header.jsp"/>

<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search by Email here..">

<font color="red" align="center"><h2 id="noResults"></h2></font>

<div class="container">
<table id="myTable" class="table table-striped">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Phonenumber</th>
        <th>Address</th>
        <c:choose>
            <c:when test="${listOfMembers[0].type eq 'SEEKER'}">
                 <th>No Of Children</th>
                 <th>Spouse Name</th>
            </c:when>
            <c:otherwise>
                 <th>Experience</th>
                 <th>Expected Pay</th>
            </c:otherwise>
        </c:choose>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listOfMembers}" var="member">
     <tr>
        <td>${member.firstName}</td>
        <td>${member.lastName}</td>
        <td>${member.email}</td>
        <td>${member.phoneNumber}</td>
        <td>${member.address}</td>
        <c:choose>
            <c:when test="${member.type eq 'SEEKER'}">
                 <td>${member.noOfChildren}</td>
                 <td>${member.spouseName}</td>
            </c:when>
            <c:otherwise>
                 <td>${member.experience}</td>
                 <td>${member.expectedPay}</td>
            </c:otherwise>
        </c:choose>
     </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>
