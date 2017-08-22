$(document).ready( function() {
    $('#userPwd').on("click",function(event) {
        var flag = checkPassword();
        if (flag) {
            var uId = $('#uId').val();
            $.ajax({
                type: 'POST',
                url: '/YourAnswer/api/userPwd',
                data: {
                    "uId": $('#uId').val(),
                    "uEmail": $('#uEmail').val(),
                    "uPassword": $('#uPassword').val(),
                    "uNewPassword1": $('#uNewPassword1').val()

                },
                success: function (data) {
                    var flag = data.flag;
                    if (flag) {
                        window.location.href = "/YourAnswer/user/" + uId;
                    } else {
                        $('#updateUserPwdMessage').addClass("alert-warning");
                        $('#updateUserPwdMessage').text("更改密码失败！");
                    }
                },
                error: function () {
                    $('#updateUserPwdMessage').addClass("alert-warning");
                    $('#updateUserPwdMessage').text("更改密码失败！");
                }
            })
        }
    });


    $('#userSetting').on("click",function(event) {
        var uId = $('#uId').val();
        $.ajax({
            type: 'POST',
            url: '/YourAnswer/api/userSetting',
            data: {
                "uId":$('#uId').val(),
                "uName": $('#uName').val(),
                "uWord": $('#uWord').val(),
                "uResidence": $('#uResidence').val(),
                "uProfession": $('#uProfession').val()

            },
            success: function (data) {
                var flag = data.flag;
                if(flag) {
                    window.location.href = "/YourAnswer/user/" + uId;
                } else {
                    $('#updateUserMessage').addClass("alert-warning");
                    $('#updateUserMessage').text("更改用户资料失败！");
                }
            },
            error: function () {
                $('#updateUserMessage').addClass("alert-warning");
                $('#updateUserMessage').text("更改用户资料失败！");
            }
        })
    });


    $('#btnTest').on("click",function() {
        this.$avatarModal = $("body").find('#avatar-modal');
        this.$avatarForm = this.$avatarModal.find('.avatar-form');
        var url = this.$avatarForm.attr('action');
        alert(url);
    })

});

function checkPassword() {
    var uNewPassword1 = $('#uNewPassword1').val();
    var uNewPassword2 = $('#uNewPassword2').val();
    if(uNewPassword1.length < 6) {
        $('#updateUserSecurityMessage').addClass("alert-warning");
        $('#updateUserSecurityMessage').html("输入的密码格式不对！");
        return false;
    } else if(uNewPassword1 !== uNewPassword2) {
        $('#updateUserSecurityMessage').addClass("alert-warning");
        $('#updateUserSecurityMessage').html("两次输入的密码不同！");
        return false;
    }
    return true;
}
$(function () {
    $("#uploadImg").click(function() {
        $("#file").click();
    });
});
