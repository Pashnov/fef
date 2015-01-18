<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html xmlns:c="">
<head>
    <title>User info</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
</head>
<body role="document" type-page="user">

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>User info</h1>
        <br/>
        <br>

        <p>

        <h1>info</h1></p>
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
                <td><b>yearOfStudy</b></td>
                <td>${user.yearOfStudy}</td>
            </tr>
        </table>
    </div>
</div>
<script src="js/active-tab.js"></script>

</body>
</html>
