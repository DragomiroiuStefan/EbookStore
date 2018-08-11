<%-- 
    Document   : userAdministration
    Created on : Aug 10, 2018, 10:02:42 PM
    Author     : stefan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");%>

<%-- 
TODO: 
<c:if test="${!correctAccess}">
    <jsp:forward page="admin"/>
</c:if>
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User administration:</h1>
        <form action="useradmin" method="POST">
            <table>
                <tr>
                    <th>Select<th>
                    <th>Ssn</th>
                    <th>Username</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="user" items="${allUsers}">
                    <tr>
                        <td><input type="checkbox" name="user" value="${user.ssn}" /></td>
                        <td><c:out value="${user.ssn}"/></td>
                        <td><c:out value="${user.username}"/></td> 
                        <td><c:out value="${user.role}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <label for="ssn">ssn: </label>
            <input type="text" name="ssn"><br>
            <label for="username">username: </label>
            <input type="text" name="username"><br>
            <label for="password">password: </label>
            <input type="password" name="password"><br>
            Role:
            <input type="radio" name="role" value="admin" />Admin
            <input type="radio" name="role" value="user" checked="true"/>User <br>
            <input type="submit" name="insert" value="Insert" />
            <input type="submit" name="update" value="Update" />
            <input type="submit" name="delete" value="Delete" />
        </form>
    </body>
</html>
