$(document).ready(function() {

    $('#editor').change(function() {
        var qDetail = editor.txt.html();
        $('#qDetail').text(qDetail);
    });

    $('#submitBtn').focus(function() {
        var qDetail = editor.txt.html();
        $('#qDetail').text(qDetail);
    });


    $('#submitBtn').on("click",function () {
        var qTitle = $('#qTitle').val();
        var qDetail = editor.txt.html();
        var qTopic = $('#qTopic').val();

        $.ajax({
            url: "/api/YourAnswer/Question",
            type: 'POST',
            data: {
             'qTitle': qTitle,
             'qDetail': qDetail
            },
            success: function (data) {
                if(data == -1) {
                    window.location.href = "/YourAnswer/login";
                } else if(data == 0) {
                    $('#addQuestionMessage').addClass("alert-warning");
                    $('#addQuestionMessage').text("已存在类似问题！");
                } else {
                    window.location.href="/YourAnswer/Question/" + data;
                }
            },
            error: function (data) {
                window.location.href="/YourAnswer/makeQuestion";
                $('#addQuestionMessage').addClass("alert-warning");
                $('#addQuestionMessage').text("暂时无法提问！");
            }
        });
    });

});