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
            setInterval('updateText()', 400);
        });

        function updateText(){
            var id = $("#lectionId").val();
            var text = $("textarea[name='text']").val();
            console.log("id = " + id + ", text = " + text);
            $.ajax({
                type: "POST",
                url: "lection",
                dataType: "json",
                data: {lectionId: id, text: text},
                success: function (data) {
                    console.log("2 = " + data.param);
                    if(!data.param){
                        console.log("check network from param = " + data.param);
                    }
                },
                error: function(){
                    console.log("check network");
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
