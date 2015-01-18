<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html>
<head>
    <title>Generated Entities</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
</head>
<body role="document" type-page="home">

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Index page</h1>
        <br/>
        <a href="materials">materials</a><br>
        <a href="createMaterial">createMaterial</a><br>
        <a href="users">users</a><br>
        <a href="createUser">create User</a><br>
        <a href="createGroup">create Group</a><br>
        <a href="lection?lectionId=1">lection</a><br>
        <a href="lectionView?lectionId=1">lection view</a><br>
        <a href="login">login</a><br>
        <a href="myInfo">my info</a><br>
    </div>
</div>

<script src="js/active-tab.js"></script>

</body>
</html>
