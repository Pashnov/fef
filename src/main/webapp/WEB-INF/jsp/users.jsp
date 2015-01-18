<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html xmlns:c="">
<head>
    <title>Users</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
</head>
<body role="document" type-page="user">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Generated Entities</h1>
        <br/>
        <br>
        <table>
            <thead>
            <tr>
                <th>id</th>
                <th>first name</th>
                <th>last name</th>
                <th>email</th>
                <th>role</th>
                <th>yearOfStudy</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><a href="user?id=${user.id}">id</a></td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.yearOfStudy}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="js/active-tab.js"></script>
</body>
</html>
