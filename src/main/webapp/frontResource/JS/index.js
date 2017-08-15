"use strict";

$(document).ready(function () {
    $.get('/YourAnswer/api/FollowingFeed',
        function(data,status){
            if(status) {
                showData(data);
            }
        }
    )
});

function showData(data) {
    $.each(data, function (index, operation) {
        var operationType = operation.operationType;
        var operationId = operation.operationId;
        if(operationType == 100) {
            $.get('/YourAnswer/api/onlyQuestion/' + operationId,
                function(currentQuestion,status){
                    if(status ==="success") {
                        // new Vue({
                        //     el: '#app1',
                        //     data: {
                        //         currentQuestion
                        //     }
                        // });
                        var date=new Date(currentQuestion.qMadeDate).toLocaleString();
                        var list = $('#list');
                        list.append(
                            '<div><div class="highlight" style="background-color: #f6f6f6;">' +
                            '<div class="form-group smallInfo" style="color:#D0D0D0">' +
                            '<h6 style="color:#999999"><a href="user/' + currentQuestion.qMadeByUserId +
                            '" target="_blank">' + currentQuestion.qMadeByUser.uName + '</a> &nbsp;提出了问题：</h6>' +
                            '</div> <hr> <div class="form-group">' +
                            '<h3><a href="Question/' + currentQuestion.qId + ' "> ' + currentQuestion.qTitle + '</a></h3>' +
                            '</div> <div class="form-group"> <p>' + currentQuestion.qDetail + '</p> </div> <div' +
                            ' class="form-group"  style="color:#999999">' +
                            '<hr><h6>发布于：' + date +'</div></div></div> <br>'
                        );
                    }
                }
            );
        }
        if(operationType == 101) {
            $.get('/YourAnswer/api/onlyAnswer/' + operationId,
                function(currentAnswer,status){
                    if(status ==="success") {
                        var date=new Date(currentAnswer.aMadeDate).toLocaleString();
                        var list = $('#list');
                        list.append(
                            '<div><div class="highlight" style="background-color: #f6f6f6;">' +
                            '<div class="form-group smallInfo" style="color:#D0D0D0">' +
                            '<h6 style="color:#999999"><a href="user/' + currentAnswer.aMadeByUserId +
                            '" target="_blank">' + currentAnswer.aMadeByUser[uName] + '</a> &nbsp;回答了问题：</h6>' +
                            '</div> <hr> <div class="form-group">' +
                            '<h3><a href="Question/'+currentAnswer.aBelongToQuestionId+'/Answer/'+currentAnswer.aId+' "> '
                            + currentAnswer.aBelongToQuestion.qTitle + '</a></h3>' +
                            '</div> <div class="form-group"> <p>' + currentAnswer.aContent + '</p> </div> <div' +
                            ' class="form-group"  style="color:#999999">' +
                            '<hr><h6>发布于：' + date +'</div></div></div> <br>'
                        );
                    }
                }
            );
        }
        if(operationType == 102) {
            $.get('/YourAnswer/api/onlyEssay/' + operationId,
                function(currentEssay,status){
                    if(status ==="success") {
                        var date=new Date(currentEssay.essayMadeDate).toLocaleString();
                        var text = currentEssay.essayContent;
                        var flag = text.length > 100;
                        var insteadContent,showContent;
                        var essayId = currentEssay.essayId;
                        if(flag){
                            insteadContent = text.substring(0,150);
                            showContent = insteadContent+'<a href=" Essay/' + essayId +'" target="_blank">阅读全文</a>';
                        }
                        var list = $('#list');
                        list.append(
                            '<div><div class="highlight" style="background-color: #f6f6f6;">' +
                            '<div class="form-group smallInfo" style="color:#D0D0D0">' +
                            '<h6 style="color:#999999"><a href="user/' + currentEssay.essayMadeByUserId +
                            '" target="_blank">' + currentEssay.essayMadeByUser.uName+ '</a> &nbsp;发表了随笔：</h6>' +
                            '</div> <hr> <div class="form-group">' +
                            '<h3><a href="Essay/' + currentEssay.essayId + ' "> ' + currentEssay.essayTitle + '</a></h3>' +
                            '</div> <div id="essay " class="form-group" > <p id="essayContent">' + showContent + '</p>' +
                            ' </div>' +
                            ' <div' + ' class="form-group"  style="color:#999999">' +
                            '<hr><h6>发布于：' + date +'</div></div></div> <br>'
                        );

                    }
                }
            );
        }


    });

    $(function(){

        /**
        var flag = text.length > 200 ? true : false;

        if(flag){
            $(".essay ").html("");
            $(".essay").append("<p>" + text.substring(0, 20)
                + "<span id='hide' style='display:none'>" + text.substring(20) + "</span>"
                + "<a href='javascript:;' id='open'>...显示全部</a></p>");
        }

        $("#open").click(function(){
            if(flag){
                $("#open").text("...隐藏");
                $("#hide").show();
                flag = false;
            } else{
                $("#open").text("...显示全部");
                $("#hide").hide();
                flag = true;
            }

        });**/
    });

}
