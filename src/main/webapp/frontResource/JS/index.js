"use strict";

$(document).ready(function () {
    $.get('/YourAnswer/api/FollowingFeed',
        function(data,status){
            if(status) {
                var operations = data.value;
                showFeed(operations);
            }
        }
    );

    function showFeed(operations) {
        Vue.prototype.onlyShow= function (value) {
            if(value.length > 200) {
                var val = value.replace(/<.*?>/ig,"").substring(0,200) + "......";
                return val;
            } else {
                return value;
            }
        };

        new Vue({
            el: '#app',
            data: {
                operations : operations,
                isShow:false
            }
        });
    }
});


