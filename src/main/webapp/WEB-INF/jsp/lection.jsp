<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html>
<head>
    <title>Lection</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>
    <script src="js/wiris_editor.js"></script>

    <script>

        var editor;
        window.onload = function () {
            editor = com.wiris.jsEditor.JsEditor.newInstance({'language': 'ru'});
            editor.insertInto(document.getElementById('editorContainer'));



        }

        function setValueFromDB(){
            alert("set");
            var id = $("#lectionId").val();
            console.log("id = " + id);
            $.ajax({
                type: "POST",
                url: "lectionView",
                dataType: "json",
                data: {lectionId: id},
                success: function (data) {
                    var text = data.text;
                    console.log("text =============== = " + text);
                    editor.setMathML(text);
                },
                error: function(){
                    console.log("error");
                    setValueFromDB();
                }
            });
        }

        $(function () {
            console.log("ready");
//            $.Deferred();
            $.when(setValueFromDB()).done(setInterval('updateText()', 450));
        });

        function updateText() {
            console.log("update");
            var id = $("#lectionId").val();
            var text = editor.getMathML();
            console.log("id = " + id + ", text = " + text);
            console.log("=================" + editor.getMathML() + "=============");
            $.ajax({
                type: "POST",
                url: "lection",
                dataType: "json",
                data: {lectionId: id, text: text},
                success: function (data) {
                    console.log("2 = " + data.param);
                    if (!data.param) {
                        console.log("check network from param = " + data.param);
                    }
                },
                error: function () {
                    console.log("check network");
                }
            });
        }
    </script>

</head>
<body role="document" type-page="lection">
<input type="hidden" name="lectionId" id="lectionId" value="${lection.id}">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container theme-showcase" role="main">

    <div class="jumbotron">
        <h2>Lection ${lection.name}</h2>

        <div class="row">
            <div class="col-lg-12 row" style="height: 450px;">
                <div id="editorContainer"></div>
            </div>
        </div>
    </div>

    <div class="">
        <h2>Comments</h2>
    </div>
    <div class="row">
        <form method="post" action="createLectionComment">
            <input type="hidden" name="lectionId" value="${lection.id}">

            <div class="col-sm-2 form-group">
                <input class="form-control btn btn-success" type="submit" value="send">
            </div>
            <div class="col-sm-10 form-group">
                <input class="form-control" type="text" name="text" placeholder="text for comment">
            </div>
        </form>
    </div>
    <br>

    <div class="row">

        <c:forEach items="${listComment}" var="comment">
            <div class="col-sm-2 form-group">
                <input type="text" class="btn btn-info form-control" value="${comment.user.firstName}&nbsp;${comment.user.lastName}">
            </div>
            <div class="col-sm-8 form-group">
                <span type="text" class="form-control btn btn-default active">${comment.text}</span>
            </div>
            <div class="col-sm-2 form-group">
                <input type="text" class="btn btn-default form-control" value="${comment.creationDate}">
            </div>
        </c:forEach>

    </div>


</div>


<script src="js/active-tab.js"></script>
</body>
</html>
