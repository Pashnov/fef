<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag body-content="empty" %>

<li id="home">
    <a href='<c:url value="/"/> '>Home</a>
</li>
<li id="material">
    <a href="materials">Material</a>
</li>
<li id="user">
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a href="users">Users</a>
    </c:if>
    <c:if test="${sessionScope.user.role eq 'LECTURER'}">
        <a href="users">User</a>
    </c:if>
    <c:if test="${sessionScope.user.role eq 'STUDENT'}">
        <a href="myInfo">User</a>
    </c:if>
</li>
<li id="lection">
    <a href="#">Lection</a>
</li>
