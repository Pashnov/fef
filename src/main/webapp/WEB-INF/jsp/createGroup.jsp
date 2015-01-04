<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>create group</title>

    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />

    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/group/fieldChooser.min.js"></script>

    <script>
        $(function () {
            var $sourceFields = $("#sourceFields");
            var $destinationFields = $("#destinationFields");
            var $chooser = $("#fieldChooser").fieldChooser(sourceFields, destinationFields);
        });
    </script>

</head>
<body>

<h1>create group</h1>
<br/>
<br>

<hr>
free students = ${freeStudents}
<hr>
<br>
name group&nbsp;<input type="text" name="name" form="createUser">
<br>
<div id="fieldChooser" tabIndex="1">
    <div id="sourceFields">
        <c:forEach var="student" items="${freeStudents}">
        <div>
            <input type="hidden" value="${student.id}" name="id">
            ${student.firstName}&nbsp;${student.lastName}
        </div>
        </c:forEach>
    </div>
    <form id="createUser" action="" method="post">
        <div id="destinationFields">
        </div>
        <input type="submit" value="save">
    </form>
</div>

</body>
</html>
