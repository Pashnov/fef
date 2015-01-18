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

<form action="createMaterial" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label>name</label></td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><label>description</label></td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td><label>photo</label></td>
            <td><input type="file" name="photo"></td>
        </tr>
        <tr>
            <td><label>file</label></td>
            <td><input type="file" name="file"></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="create">
</form>

    </div>
</div>

<script src="js/active-tab.js"></script>

</body>
</html>
