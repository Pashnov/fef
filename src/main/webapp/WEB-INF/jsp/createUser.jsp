<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>create User</title>
</head>
<body>
<br>
<h1>Create user</h1>
<br>
<form action="" method="post">
<table>
    <tr>
        <td>first name</td>
        <td><input type="text" name="firstName"></td>
    </tr>
    <tr>
        <td>last name</td>
        <td><input type="text" name="lastName"></td>
    </tr>
    <tr>
        <td>role</td>
        <td><select name="role">
            <option value="STUDENT">STUDENT</option>
            <option value="LECTURER">LECTURER</option>
            <option value="ADMIN">ADMIN</option>
        </select></td>
    </tr>
    <tr>
        <td><input type="submit" value="create"></td>
    </tr>
</table>
</form>
</body>
</html>
