<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<h1>Edit user</h1>
<form:form method="post" action="updateUser" modelAttribute="user">
    <table>
        <tr>
            <td>Id :</td>
            <td><form:hidden path="id"/></td>
        </tr>
        <tr>
            <td>Firstname :</td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td>Lastname :</td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>
</html>