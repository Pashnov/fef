<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>Generated Entities</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
</head>
<body role="document" type-page="material">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
<h1>Generated Entities</h1>
<br/>
<br>
<table>
    <tr>
        <th>photo</th>
        <th>name</th>
        <th>description</th>
        <th>file_name</th>
    </tr>

    <c:forEach var="material" items="${materials}">
    <tr>
        <th><img style="width: 150px;" src="getPhoto?id=${material.id}"></th>
        <th>${material.name}</th>
        <th>${material.description}</th>
        <th><a href="getMaterial?id=${material.id}" download="${material.fileName}"> ${material.fileName}</a></th>

    </tr>
    </c:forEach>
</table>
    </div>
</div>

<script src="js/active-tab.js"></script>
</body>
</html>
