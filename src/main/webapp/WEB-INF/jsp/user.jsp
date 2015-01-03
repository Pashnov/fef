<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html xmlns:c="">
<head>
    <title>User info</title>
</head>
<body>

<h1>User info</h1>
<br/>
<br>
<p><h1>info</h1></p>
<br>
<br>
<table>
    <tr>
        <td><b>id</b></td>
        <td>${user.id}</td>
    </tr>
    <tr>
        <td><b>first name</b></td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td><b>last name</b></td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td><b>email</b></td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td><b>role</b></td>
        <td>${user.role}</td>
    </tr>
    <tr>
        <td><b>course</b></td>
        <td>${user.course}</td>
    </tr>
</table>

</body>
</html>
