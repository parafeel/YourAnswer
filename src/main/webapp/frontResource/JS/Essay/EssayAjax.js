$(document).ready(function() {

    $('#addEssayBtn').on("click",function(event) {
        var essayTitle = $('#essayTitle').val();
        var essayContent = $('#editor').summernote('code');
        var tags=$('#demo1').tagEditor('getTags')[0].tags;
        if(essayTitle.length > 1 && essayContent.length > 1) {
            $.ajax({
                type: 'POST',
                url: '/YourAnswer/api/Essay',
                data: {
                    "essayTitle": essayTitle,
                    "essayContent": essayContent,
                    "essayTags":essayTags
                },
                success: function (data) {
                    alert("success:" + data);
                    if(data.resultCode === 200) {
                        var rs = eval(data);
                        var essay = rs.value;
                        window.location.href = "/YourAnswer/Essay/" + essay.essayId;
                    } else if(data.resultCode === 222) {
                        $('#addEssayMessage').addClass("alert-warning");
                        $('#addEssayMessage').text("已存在相同标题随笔！");
                    } else {
                        $('#addEssayMessage').addClass("alert-warning");
                        $('#addEssayMessage').text("未发布成功,请先登录！");
                    }
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        } else {
            $('#addEssayMessage').addClass("alert-warning");
            $('#addEssayMessage').text("输入的字符过少");
        }
    });

    $('#updateEssayBtn').on("click",function(event) {
        var essayId = $('#essayId').val();
        var essayTitle = $('#essayTitle').val();
        var essayContent = $('#editor').summernote('code');
        if(essayTitle.length > 1 && essayContent.length > 1) {
            $.ajax({
                type: 'POST',
                url: '/YourAnswer/api/Essay/' + essayId,
                data: {
                    _method : "PUT",
                    "essayId": essayId,
                    "essayTitle": essayTitle,
                    "essayContent": essayContent
                },
                success: function (data) {
                    alert("success:" + data);
                    if(data.resultCode === 200) {
                        var rs = eval(data);
                        var essay = rs.value;
                        window.location.href = "/YourAnswer/Essay/" + essay.essayId;
                    } else if(data.resultCode === 222) {
                        $('#addEssayMessage').addClass("alert-warning");
                        $('#addEssayMessage').text("已存在相同标题随笔！");
                    } else {
                        $('#addEssayMessage').addClass("alert-warning");
                        $('#addEssayMessage').text("未发布成功,请先登录！");
                    }
                },
                error: function (e) {
                    alert('Error: ' + e);
                }
            });
        } else {
            $('#addEssayMessage').addClass("alert-warning");
            $('#addEssayMessage').text("输入的字符过少");
        }
    });

});