<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>Lection</title>

    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/jquery-ui.js"></script>

    <script>
        $(function(){
            console.log("ready");
            setInterval('updateText()', 3000);
        });

        function updateText(){
            var id = $("#lectionId").val();
            console.log("id = " + id);
            $.ajax({
                type: "POST",
                url: "lectionView",
                dataType: "json",
                data: {lectionId: id},
                success: function (data) {
                    console.log("2 = " + data.text);
                    var textElem = $("textarea[name='text']");
                    $(textElem).empty();
                    $(textElem).text(data.text);
                }
            });
        }
    </script>

</head>
<body>
<input type="hidden" name="lectionId" id="lectionId" value="${lection.id}">
<h1>Lection ${lection.name}</h1>
<br/>
<br>
<p><h1>Text</h1></p>
<textarea name="text" rows="15" cols="100">${lection.text}</textarea>
<br>

</body>
</html>
