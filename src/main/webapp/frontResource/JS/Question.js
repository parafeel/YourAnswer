
var qId = $('#qId').text();

$(document).ready( function() {

    $.get('/YourAnswer/api/Question/' + qId,
        function(data,status){
            if(status) {
                // var question = data;
                // var qAnswers = question.qAnswers[0];
                showQuesion(data);
            } else {
                alert("failed");
            }
        }
    );

    function showQuesion(data) {
        document.title = data.qTitle;
        new Vue({
            el: '#app1',
            data: {
                question : data,
                answerList: data.qAnswers
            }
        });
    }

    $('#addAnswerBtn').on("click",function(event) {
        var aContent = editoraContent.txt.html();
        if(aContent.length > 1) {
            $.ajax({
                type: 'POST',
                url: '/YourAnswer/api/Answer/' + qId,
                data: {
                    "aContent": editoraContent.txt.html(),
                    "aBelongToQuestionId": qId
                },
                success: function (data) {
                    alert("success:" + data);
                    if(data == -1) {
                        window.location.href = "/YourAnswer/login";
                    } else if(data == 0) {
                        $('#addAnswerMessage').addClass("alert-warning");
                        $('#addAnswerMessage').text("您已回答过此问题！");
                    } else {
                        var aId = data;
                        window.location.href="/YourAnswer/Question/" + qId + "/Answer/" + aId;
                    }
                },
                error: function (e) {
                    alert("fail:" + data);
                    alert('Error: ' + e);
                }
            });
        } else {
            $('#addAnswerMessage').addClass("alert-warning");
            $('#addAnswerMessage').text("最少需要输入10个字符！");
        }
    });

});
