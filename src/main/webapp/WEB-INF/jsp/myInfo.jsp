<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html>
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

        <form method="post" class="form-inline">
            <table class="table">
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
                    <td><input type="text" value="${user.email}" name="email"></td>
                </tr>

                <tr>
                    <td><b>role</b></td>
                    <td>${user.role}</td>
                </tr>
                <tr>
                    <td><b>yearOfStudy</b>&nbsp;&nbsp;(<h4 style="display: inline" ><b>${user.yearOfStudy}</b></h4>)</td>
                    <td><select name="yearOfStudy" class="form-control">
                        <option value="${user.yearOfStudy}">no change</option>
                        <option value="I">I</option>
                        <option value="II">II</option>
                        <option value="III">III</option>
                        <option value="IV">IV</option>
                        <option value="V">V</option>
                        <option value="VI">VI</option>
                    </select></td>
                </tr>
                <tr>
                    <td><b>password</b></td>
                    <td>
                        <div id="showPass" style="display: none;" >
                            <input class="form-control" type="text" value="${user.password}" name="password">
                            <span class="clickShow"><i class=" glyphicon glyphicon-eye-close"></i></span>
                        </div>
                        <div id="hidePass" >
                            <input  class="form-control" type="password" value="${user.password}" name="password">
                            <span class="clickHide"><i class=" glyphicon glyphicon-eye-open"></i></span>
                        </div>
                    </td>

                </tr>
                <tr>
                    <td><b>password confirm</b></td>
                    <td>
                        <input class="form-control" type="password" value="${user.password}" name="passwordConfirm">
                    </td>
                </tr>
            </table>
            <input type="submit" value="save" class="btn btn-info">
            <h3><span style="color: red"></span></h3>
        </form>
    </div>
</div>
<script src="js/active-tab.js"></script>
<script src="js/user/my-info.js"></script>
</body>
</html>
