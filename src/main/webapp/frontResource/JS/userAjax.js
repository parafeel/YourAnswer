$(document).ready( function() {

    $('#userLogout').on("click",function(event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/YourAnswer/api/userLogout',
            success: function (data) {
                if (data === true) {
                    alert("true");
                    window.location.href = "/YourAnswer/index";
                }
                if (data === false) {
                    alert("您并未登录！");
                    window.location.href = "/YourAnswer/index";
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        })

    });



});

