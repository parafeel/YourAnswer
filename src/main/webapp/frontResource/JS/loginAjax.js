$(document).ready( function() {
    $('#captchaImage').click(function()
    {
        $('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());
    });


    $('#login').on("click",function(event) {

        $('#login').click(checkLogin);
        /**
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'userLogin',
            data: {
                "uEmail": $('#ulEmail').val(),
                "uPassword": $('#ulPassword').val(),
                "verificationCode": $('#VerificationCode').val()
            },
            success: function (data) {

            }, error: function (e) {
                alert('Error: ' + e);
            }
        }) **/
    });
});

function checkLogin() {
    var ulEmail = $('#ulEmail').val();
    var ulPassword = $('#ulPassword').val();
    var verificationCode = $('#VerificationCode').val();
    if( ulEmail.length == 0 || ulPassword == 0 ) {
        $('#loginMessage').addClass("alert-warning");
        $('#loginMessage').html("请输入账号密码！");
        return false;
    }
    if(verificationCode.length != 6 ) {
        $('#loginMessage').addClass("alert-warning");
        $('#loginMessage').html("验证码不正确！");
        return false;
    }
    if(ulPassword.length < 6) {
        $('#loginMessage').addClass("alert-warning");
        $('#loginMessage').html("密码格式不正确!");
        return false;
    }
    return true;
}

function loadData(data) {
    $.each(data, function (index, item) {
        //循环获取数据

        alert(item);
    });
}