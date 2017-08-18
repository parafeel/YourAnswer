'use strict';

$(document).ready(function() {

    $('#addQuestionBtn').on("click",function () {
        var qTitle = $('#qTitle').val();
        var qDetail = editor.txt.html();
        var qTopic = $('#qTopic').val();
        $.ajax({
            url: "/YourAnswer/api/Question",
            type: 'POST',
            data: {
             'qTitle': qTitle,
             'qDetail': qDetail
            },
            success: function (data) {
                var question = data.value;
                if(data.resultCode === 200) {
                    window.location.href="/YourAnswer/Question/" + question.qId;
                } else {
                    $('#addQuestionMessage').addClass("alert-warning");
                    $('#addQuestionMessage').text("提问未成功！");
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