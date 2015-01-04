<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html xmlns:c="">
<head>
    <title>User info</title>
</head>
<body>

<h1>Group info</h1>
<br/>
<br>
<p><h1>info</h1></p>
<br>
<br>
<table>
    <tr>
        <td><b>id</b></td>
        <td>${group.id}</td>
    </tr>
    <tr>
        <td><b>name</b></td>
        <td>${group.name}</td>
    </tr>
    <tr>
        <td><b>creation date</b></td>
        <td>${group.creationDate}</td>
    </tr>
    <tr>
        <td><b>is active</b></td>
        <td>${group.isActive()}</td>
    </tr>
</table>
<br>
<br>
<h1>people</h1>
<br>
<br>
${grg.users}
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
    <c:forEach var="user" items="${group.users}">
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

</body>
</html>
