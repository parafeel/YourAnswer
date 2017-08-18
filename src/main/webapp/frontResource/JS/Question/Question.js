'use strict';

$(document).ready( function() {

    var qId = $('#qId').text();
    $.ajax({
        type: 'GET',
        url: '/YourAnswer/api/Question/' + qId,
        success: function (data) {
            if (data.resultCode === 200) {
                var question = data.value;
                showQuesion(question);
            } else {
                window.location.href = "/YourAnswer/wrongInfo";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

    function showQuesion(question) {
        document.title = question.qTitle + "---YourAnswer";
        new Vue({
            el: '#app1',
            data: {
                question : question,
                answerList: question.qAnswers
            }
        });
    }


    //Ajax添加Answer
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
                    if(data.resultCode === 200) {
                        var answer = data.value;
                        window.location.href="/YourAnswer/Question/" + answer.aBelongToQuestionId + "/Answer/" + answer.aId;
                    } else if(data.resultCode === 222) {
                        $('#addAnswerMessage').addClass("alert-warning");
                        $('#addAnswerMessage').text("您已回答过此问题！");
                    } else if(data.resultCode === 400){
                        $('#addAnswerMessage').addClass("alert-warning");
                        $('#addAnswerMessage').text("您似乎未登录！");
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
