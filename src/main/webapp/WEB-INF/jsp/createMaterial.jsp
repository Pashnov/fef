<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head><title>Generated Entities</title></head>
<body>

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


</body>
</html>
