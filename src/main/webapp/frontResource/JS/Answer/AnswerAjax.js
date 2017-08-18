'use strict';

$(document).ready(function() {

    $('#updateAnswerBtn').on("click",function(event) {
        var aId = $('#aId').val();
        var aContent = editoraContent.txt.html();
        var aBelongToQuestionId = $('#qId').val();
        alert(aId);
        alert(aContent);
        alert(aBelongToQuestionId);
        if(aContent.length > 1) {
            $.ajax({
                type: 'POST',
                url: '/YourAnswer/api/Answer/' + aId,
                data: {
                    _method : "PUT",
                    "aId": aId,
                    "aContent": aContent,
                    "aBelongToQuestionId": aBelongToQuestionId
                },
                success: function (data) {
                    if(data.resultCode === 200) {
                        alert(data.resultCode);
                        var rs = eval(data);
                        var answer = rs.value;
                        window.location.href = "/YourAnswer/Question/" + answer.aBelongToQuestionId + "/Answer/" + answer.aId;
                    } else {
                        $('#updateAnswerMessage').addClass("alert-warning");
                        $('#updateAnswerMessage').text("未修改成功！");
                    }
                },
                error: function (e) {
                    alert("false");
                    alert('Error: ' + e);
                }
            });
        } else {
            $('#updateAnswerMessage').addClass("alert-warning");
            $('#updateAnswerMessage').text("输入的字符过少");
        }
    });

});