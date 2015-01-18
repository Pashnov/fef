<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html>
<head>
    <title>Login</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
    <link href="css/user/signin.css" rel="stylesheet">
</head>
<body>


<div class="container">

    <form class="form-signin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <span style="color: red ${messageForLogin == true ? ";display : block" : ";display : none"} ">
            please check to correct fields
        </span>
        <label for="firstName" class="sr-only">First name</label>
        <input type="text"  name="firstName" id="firstName" class="form-control" placeholder="First name" required autofocus>

        <label for="lastName" class="sr-only">Email address</label>
        <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last name" required autofocus>

        <label for="password" class="sr-only">Password</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="Password">

        <button class="btn btn-lg btn-primary btn-block " type="submit">Sign in</button>
    </form>

</div>

</body>
</html>
