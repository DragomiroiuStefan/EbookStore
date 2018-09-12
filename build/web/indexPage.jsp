<%-- 
    Document   : index
    Created on : Jul 28, 2018, 6:27:18 PM
    Author     : stefan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate"); %>

<%-- 
TODO: 
<c:if test="${!correctAccess}">
    <jsp:forward page="index"/>
</c:if>
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to Electronic Books Store</h1>
        <c:if test="${!loggedIn}">
            <form action="login" method="POST">
                <label for="username">username: </label>
                <input type="text" name="username"><br>
                <label for="password">password: </label>
                <input type="password" name="password"><br><br>
                <c:out value="${message}"/><br>
                <input type="submit" value="Login">            
            </form>
            <p>Don't have an account.<a href="signUpPage.jsp"> Sign up</a></p>
        </c:if>
        <c:if test="${loggedIn}">
            <h3>Welcome, <c:out value="${username}" /></h3>
            <c:if test="${isAdmin}">
            <form action="admin" method="POST">
                <input type="submit" value="Admin">                
            </form>
            </c:if>
            <form action="logout" method="POST">
                <input type="submit" value="Logout">
            </form>
        </c:if>

        <br>
        <h3> Ebooks: </h3>
        <table>
            <tr>
                <th>Isbn</th>
                <th>Name</th>
                <th>Type</th>
                <th>Quality</th>
                <th>Pages</th>
                <th>Genre</th>
                <th>Authors</th> 
            </tr>
            <c:forEach var="ebook" items="${requestScope.allEbooks}">
                <tr>
                    <td><c:out value="${ebook.isbn}"/></td>
                    <td><c:out value="${ebook.name}"/></td> 
                    <td><c:out value="${ebook.type}"/></td>
                    <td><c:out value="${ebook.quality}"/></td>
                    <td><c:out value="${ebook.pages}"/></td> 
                    <td><c:out value="${ebook.genre}"/></td>
                    <td>
                        <c:forEach var="author" items="${ebook.authors}">
                            <c:out value="${author.firstName}"/>
                            <c:out value="${author.familyName}"/>,
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
