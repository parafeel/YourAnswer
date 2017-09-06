/**
 * Created by wh-pc on 2017/9/5.
 */
'use strict';

$(document).ready( function() {

    $.get('/YourAnswer/api/User/FollowingTopics',
        function(data,status){
            if(status) {
                var topics = data.value;
                showTopics(topics);
            }
        }
    );

    function showTopics(topics) {
        new Vue({
            el: '#app1',
            data: {
                topics : topics,
                indexTopic : topics[0]
            },
            methods: {
                showTopicFeed: function (topic) {
                    event.preventDefault();
                    this.indexTopic = topic;
                }
            }
        });
    };

    $(function () {
        $('#myTab li:eq(0) a').tab('show');
    });

});
