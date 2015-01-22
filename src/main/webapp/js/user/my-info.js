$(function(){
        $(".clickHide").on('click', function(e) {
                $("#hidePass").hide();
                $("#showPass").show();
        });

        $(".clickShow").on('click', function(e) {
                $("#hidePass").show();
                $("#showPass").hide();
        });

        $("table input[name='password'][type='password']").on('input', function(){
            var valPass = $(this).val();
            $("table input[name='password'][type='text']").val(valPass);
        });

        $("table input[name='password'][type='text']").on('input', function(){
            var valPass = $(this).val();
            $("table input[name='password'][type='password']").val(valPass);
        });

        $("table tr td input[name='password'][type='password']").blur(function(){checkPass()});
        $("table tr td input[name='password'][type='text']").blur(function(){checkPass()});
        $("table tr td input[name='passwordConfirm']").blur(function(){checkPass()});

});

function checkPass(){
    var pass = $("table tr td input[name='password'][type='password']").val();
    var passConf = $("table tr td input[name='passwordConfirm']").val();
    var elemMessage = $("form > h3 > span");
    if(pass == passConf){
        $(elemMessage).text('');
    }else{
        $(elemMessage).text('password is different ')
    }
}