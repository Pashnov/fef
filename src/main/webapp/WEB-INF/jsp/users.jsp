<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html xmlns:c="">
<head>
    <title>Users</title>
</head>
<body>

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
        <th>course</th>
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
                <td>${user.course}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
