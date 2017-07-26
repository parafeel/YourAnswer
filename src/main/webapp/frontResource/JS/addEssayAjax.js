$(document).ready(function() {

    $('#editor').change(function() {
        var essayContent = editor.txt.html();
        $('#essayContent').text(essayContent);
    });

    $('#submitBtn').focus(function() {
        var essayContent = editor.txt.html();
        $('#essayContent').text(essayContent);
    });


});