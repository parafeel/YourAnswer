$(document).ready(function() {

    //因为需要跳转页面，Ajax反而没form方便
    $('#editor').change(function() {
        var qDetail = editor.txt.html();
        $('#qDetail').text(qDetail);
    });

    $('#submitBtn').focus(function() {
        var qDetail = editor.txt.html();
        $('#qDetail').text(qDetail);
    });

/**
    $('#submitBtn').click(function () {
        var qTitle = $('#qTitle').val();
        var qDetail = editor.txt.html();
        var qTopic = $('#qTopic').val();


        $.ajax({
             type: 'POST',
             url: "addQuestion",
             contentType: 'application/json',
             dataType: "json",
             data: {
                 'qTitle': qTitle,
                 'qDetail': qDetail
             },
             success: function (data) {
                 var questionId = data.qId;
                 window.location.href="Question/" + questionId;
             },
             error: function (data) {
                 window.location.href="addQuestion";
             }
         });
    });
**/

});