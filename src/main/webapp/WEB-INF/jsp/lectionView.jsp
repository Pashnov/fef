<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>

<html>
<head>
    <title>Lection</title>

    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />

    <script src="js/jquery-2.1.3.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/wiris_editor.js"></script>
    <!--<script src="http://www.wiris.net/demo/editor/editor"></script>-->

    <script>

        var editor;
        window.onload = function () {
				var currentlanguage = 'ru';

				if (com.wiris.jsEditor.defaultBasePath) {
					editor = com.wiris.jsEditor.JsEditor.newInstance({
						'language': 'ru'
					});
				}
				else {
					editor = new com.wiris.jsEditor.JsEditor('../resources');
				}

				editor.insertInto(document.getElementById('editorContainer'));
		}

        //editor;

        //window.onload = function () {
          //editor = com.wiris.jsEditor.JsEditor.newInstance({'language': 'ru'});
           //editor.insertInto(document.getElementById('editorContainer'));
        //}

        $(function(){
            console.log("ready");
            setInterval('updateText()', 3000);
        });

        function updateText(){
            var id = $("#lectionId").val();
            console.log("id = " + id);
            //alert("id = " + id);
            $.ajax({
                type: "POST",
                url: "lectionView",
                dataType: "json",
                data: {lectionId: id},
                success: function (data) {
                    var text = data.text;
                    console.log("text =============== = " + text);
                    //var textElem = $("textarea[name='text']");
                    //$(textElem).empty();
                    //$(textElem).text(data.text);
                    //alert("text =============== " + text);
                    editor.setMathML(text);
                    //editor.setMathML('<math xmlns="http://www.w3.org/1998/Math/MathML"><mi>f</mi><mi>f</mi><mi>f</mi><mi>f</mi><mi>f</mi></math>');
                    //editor.setMathML("<math><mfrac><mn>1</mn><mi>x</mi></mfrac></math>");
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

<div id="editorContainer" style="width : 900px "></div>

</body>
</html>
