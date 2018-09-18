<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html>
<html>
<body>
<h1 align="center">Edit Your Profile Here</h1>
<hr><br><br>

<html:form action="member/editProfile" method="POST">

<table border="0" align="center">

<tr>
<td>Enter First Name:*</td>
<td><html:text property="firstName"/></td>
<td><font color="red"><html:errors property="firstName"/></font></td>
</tr>

<tr>
<td>Enter Last Name:</td>
<td><html:text property="lastName"/></td>
<td><font color="red"><html:errors property="lastName"/></font></td>
</tr>

<tr>
<td>Enter Phone Number:*</td>
<td><html:text property="phoneNumber"/></td>
<td><font color="red"><html:errors property="phoneNumber"/></font></td>
</tr>

<tr>
<td>Enter Address:*</td>
<td><html:textarea property="address" rows="4" cols="40"/></td>
<td><font color="red"><html:errors property="address"/></font></td>
</tr>

<!--
<bean:write name="AlterProfile" property="experience"/>
<bean:write name="AlterProfile" property="type"/>

${AlterProfile.type}
${AlterProfile.expectedPay}
-->

<c:choose>
    <c:when test='${AlterProfile.type == "seeker"}'>
        <tr>
        <td>Enter No of Children:*</td>
        <td><html:text property="noOfChildren"/></td>
        <td><font color="red"><html:errors property="noOfChildren"/></font></td>
        </tr>

        <tr>
        <td>Enter Spouse Name:*</td>
        <td><html:text property="spouseName"/></td>
        <td><font color="red"><html:errors property="spouseName"/></font></td>
        </tr>
    </c:when>

    <c:when test='${AlterProfile.type == "sitter"}'>
        <tr>
        <td>Enter Experience:*</td>
        <td><html:text property="experience"/></td>
        <td><font color="red"><html:errors property="experience"/></font></td>
        </tr>

        <tr>
        <td>Enter Expected Pay:*</td>
        <td><html:text property="expectedPay"/></td>
        <td><font color="red"><html:errors property="expectedPay"/></font></td>
        </tr>
    </c:when>
</c:choose>

<tr>
<td><html:submit value="Edit User"/></td>
</tr>


<!-- Find out why if we dont write this field why exception is coming.  WHY WHY WHY WHY-->
<html:hidden property="type"/>
<html:hidden property="memberId"/>

</table>

</html:form>
</body>
</html>