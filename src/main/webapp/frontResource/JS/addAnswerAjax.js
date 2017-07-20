$(document).ready(function() {
    $('#editoraContent').change(function() {
        var aContent = editoraContent.txt.html();
        $('#aContent').text(aContent);
    });
    $('#addAnswerBtn').click(function () {
        var aContent = editoraContent.txt.html();
        $('#aContent').text(aContent);
    })
});