/**
 * Created by wh-pc on 2017/8/31.
 */
'use strict';

$(document).ready( function() {
    var tId = $('#tId').text();

    $.get('/YourAnswer/UserTopicRelation/' + tId,
        function(data,status){
            if(status) {
                var relation = data.value;
                if(relation === 10) {
                    $('#followBtn').text("已关注");
                    $('#followBtn').addClass("am-active");
                } else {
                    $('#followBtn').text("关注话题");
                    $('#followBtn').removeClass("am-active");
                }
            }
        }
    );

    $.ajax({
        type: 'GET',
        url: '/YourAnswer/api/Topic/' + tId,
        success: function (data) {
            if (data.resultCode === 200) {
                var topic = data.value;
                showTopic(topic);
            } else {
                window.location.href = "/YourAnswer/wrongInfo";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

    function showTopic(topic) {
        document.title = topic.tName + "---Answer";
        new Vue({
            el: '#app1',
            data: {
                topic : topic
            },
            methods: {
                followTopic: function () {
                    event.preventDefault();
                    $.post('/YourAnswer/UserTopicRelation/' + tId,
                        {
                            tId: tId
                        },
                        function (data, status) {
                            if(status) {
                                var relation = data.value;
                                if(relation === 10) {
                                    $('#followBtn').text("已关注");
                                    $('#followBtn').addClass("am-active");
                                } else if(relation === -5) {
                                    $('#followTopicMsg').text("登录后才能关注！");
                                    $('#followBtn').addClass("am-active");
                                } else{
                                    $('#followBtn').text("关注话题");
                                    $('#followBtn').removeClass("am-active");
                                }
                            }
                        }
                    );
                }
            }
        });
    };


});
