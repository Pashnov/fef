<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

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
                <td>${requestScope.user.id}</td>
            </tr>
            <tr>
                <td><b>first name</b></td>
                <td>${requestScope.user.firstName}</td>
            </tr>
            <tr>
                <td><b>last name</b></td>
                <td>${requestScope.user.lastName}</td>
            </tr>
            <tr>
                <td><b>email</b></td>
                <td>${requestScope.user.email}</td>
            </tr>
            <tr>
                <td><b>role</b></td>
                <td>${requestScope.user.role}</td>
            </tr>
            <tr>
                <td><b>yearOfStudy</b></td>
                <td>${requestScope.user.yearOfStudy}</td>
            </tr>
        </table>
    </div>
</div>

<script src="js/active-tab.js"></script>
</body>
</html>
