<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>Groups</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
</head>
<body role="document" type-page="group">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Groups</h1>
        <br/>
        <br>
        <table class="table table-responsive">
            <tr>
                <th><b>#        </b></th>
                <th><b>name         </b></th>
                <th><b>creation data  </b></th>
                <th><b>is active</b></th>
            </tr>

            <c:forEach var="group" items="${groups}">
                <tr>
                    <th><a href="group?groupId=${group.id}">${group.id}</a></th>
                    <th>${group.name}</th>
                    <th>${group.creationDate}</th>
                    <th><input type="checkbox" ${group.isActive() ? "class='active' checked" : ""}></th>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script src="js/active-tab.js"></script>
</body>
</html>
