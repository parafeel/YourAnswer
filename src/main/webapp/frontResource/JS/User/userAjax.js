$(document).ready( function() {

    $('#userLogout').on("click",function(event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/YourAnswer/api/userLogout',
            success: function (data) {
                var rs = eval(data);
                if (rs.resultCode === 200) {
                    window.location.href = "/YourAnswer/index";
                }
                if (rs.resultCode === 400) {
                    window.location.href = "/YourAnswer/login";
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        })

    });

});



