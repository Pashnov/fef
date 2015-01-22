<%@ include file="/WEB-INF/jspf/lib/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/lib/taglib.jspf" %>

<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Lections</title>
    <%@ include file="/WEB-INF/jspf/lib/styleScriptLinks.jspf" %>

</head>
<body role="document" type-page="lection">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container  theme-showcase" role="main">

    <div class="jumbotron">
        <div class="page-header">
            <h1>All lections</h1>
        </div>

        <div class="row">


            <div class="col-xs-4" id="lection-subject">
                <div id="lection-searcher-name">
                    <h3>Select subjects</h3>
                    <input class="form-control" type="text" placeholder="search by name" oninput="functionSearch(this)" >
                </div>
                <h3>Select subjects</h3>
            </div>
            <div class="col-xs-6">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Lecturer</th>
                        <th>Subject</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lections}" var="lection">
                        <tr>
                            <td class="name">
                                <c:if test="${sessionScope.user.role eq 'LECTURER'}">
                                    <a href="lection?lectionId=${lection.id}">${lection.name}</a>
                                </c:if>
                                <c:if test="${sessionScope.user.role eq 'STUDENT'}">
                                    <a href="lectionView?lectionId=${lection.id}">${lection.name}</a>
                                </c:if>
                            </td>
                            <td>${lection.lecture.firstName}&nbsp;${lection.lecture.lastName}</td>
                            <td class="subject">${lection.subject.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>


<script src="js/active-tab.js"></script>
<script>
    $(function(){
        var checkbox = $("#lection-subject");
        var arrCheckbox;
        var arr = $("table tbody .subject").map(function() {
                    return $(this).text();
              }).get();
        var arrUnique = [];
        for(var i = 0, j= 0; i< arr.length; i++){
            if(!contains(arrUnique, arr[i])){
                arrUnique[j++] = arr[i];
            }
        }
        for(var i = 0, j= 0; i< arrUnique.length; i++){
            $(checkbox).append(
                '<div class="checkbox ">' +
                    '<label>' +
                        '<input type="checkbox" onchange="setCheckbox()" value="' +
                            arrUnique[i] +
                        '">' +
                            arrUnique[i] +
                        '</input>' +
                    '</label>' +
                '</div>'
            );

        }
    });

    function contains(a, obj) {
        var i = a.length;
        while (i--) {
           if (a[i] === obj) {
               return true;
           }
        }
        return false;
    }

    function setCheckbox(){
        $("#lection-searcher-name input").val("");

        var arrValueTextCheckbox = $("div label input[type='checkbox']:checked").map(function() {
            return $(this).val();
        }).get();

        $("table tbody .subject").each(function(){

            if(contains(arrValueTextCheckbox, $(this).text() )){
                $(this).closest("tr").show();
            }else{
                $(this).closest("tr").hide();
            }

            if(arrValueTextCheckbox.length == 0){
                $(this).closest("tr").show();
            }
        });
    }

    function functionSearch(input){

        $("div label input[type='checkbox']:checked").attr('checked', false);

        var value = $(input).val();

        var arrValueTextCheckbox = $("table tbody .name").map(function() {
            return $(this).text();
        }).get();

        $("table tbody .name").each(function(){
            var str = $(this).text();
            if(str.search(value) != -1){
                $(this).closest("tr").show();
            }else{
                $(this).closest("tr").hide();
            }

            if(value.length == 0){
                $(this).closest("tr").show();
            }
        });
    }

</script>
</body>
</html>
