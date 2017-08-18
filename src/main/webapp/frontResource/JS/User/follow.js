"use strict";
var pointUserId = $('#pointUserId').text();

$(document).ready(

    $.get('/YourAnswer/UserRelation/' + pointUserId,
        function(data,status){
            if(status ==="success" && data.userRelationType === -1) {
                $('#followBtn').hide();
            } else if(status ==="success" && data.userRelationType === 10) {
                $('#followBtn').text("已关注");
                $('#followBtn').addClass(" active");
            } else if(status ==="success") {
                $('#followBtn').text("关注");
                $('#followBtn').removeClass(" active");
            }
            $('#following').text(data.following);
            $('#followed').text(data.followed);
        }
    ),

    $('#followBtn').on("click",function (event) {
        event.preventDefault();
        $.post('/YourAnswer/UserRelation/' + pointUserId,
            {
                pointUserId: pointUserId,
                relationType: 10
            },
            function (data, status) {
                var followedCount = $('#followed').text();
                if (status === "success" && data === 10) {
                    $('#followBtn').text("已关注");
                    $('#followBtn').addClass(" active");
                    followedCount = parseInt(followedCount) + 1;
                    $('#followed').text(followedCount);
                } else if (status === "success" && data === 0) {
                    $('#followBtn').text("关注");
                    $('#followBtn').removeClass(" active");
                    followedCount = parseInt(followedCount) - 1;
                    $('#followed').text(followedCount);
                } else if (status === "success" && data === -5) {
                    $('#followBtn').text("关注");
                    $('#followBtn').removeClass(" active");
                    alert("您还未登录！")
                } else if (status === "success" && data === -10) {
                    alert("操作未成功！")
                }
            })
    }),

    $('#followingBtn').click(function (e) {
        $.get('/YourAnswer/api/user/' + pointUserId + '/Following',
            function(data,status){
                if(status ==="success") {
                    var users = data.value;
                    showFollowing(users);
                };
            }
        )
    }),

    $('#followedBtn').click(function (e) {
        $.get('/YourAnswer/api/user/' + pointUserId + '/Followed',
            function(data,status){
                if(status ==="success") {
                    var users = data.value;
                    showFollowed(users);
                };
            }
        )
    })


);

function showFollowing(users) {
    new Vue({
        el: '#app1',
        data: {
            users: users
        }
    });
};

function showFollowed(users) {
    new Vue({
        el: '#app2',
        data: {
            users: users
        }
    });
};