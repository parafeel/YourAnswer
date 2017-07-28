"use strict";

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        headers: {
            Accept: 'application/json; charset=utf-8',
            "Content-Type": "application/json; charset=utf-8"
        },
        url: '/YourAnswer/index',
        success: function (data) {
            loadData(data);
        }, error: function (e) {
            alert('Error: ' + e);
        }
    })
});

function loadData(data) {
    $.each(data, function (index, question) {
        //循环获取数据
        var qId = question.qId;
        var qTitle = question.qTitle;
        var qDetail = question.qDetail;
        var qMadeByUserId = question.qMadeByUserId;
        var qMadeByUser = question.qMadeByUser;
        var qMadeDate = question.qMadeDate;
        var uName = qMadeByUser.uName;
        var date=new Date(qMadeDate).toLocaleString();

        var list = $('#list');
        list.append(
            '<div><div class="highlight" style="background-color: #f6f6f6;">' +
            '<div class="form-group smallInfo" style="color:#D0D0D0">' +
            '<h6 style="color:#999999"><a href="user/' + qMadeByUserId +
            '" target="_blank">' + uName + '</a> &nbsp;提出了问题：</h6>' +
            '</div> <hr> <div class="form-group">' +
            '<h3><a href="Question/' + qId + ' "> ' + qTitle + '</a></h3>' +
            '</div> <div class="form-group"> <p>' + qDetail + '</p> </div> <div class="form-group"  style="color:#999999">' +
            '<hr><h6>发布于：' + date +'</div></div></div> <br>'
        );
    });
}