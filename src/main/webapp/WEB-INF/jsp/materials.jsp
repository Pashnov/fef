<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head><title>Generated Entities</title></head>
<body>

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

</body>
</html>
