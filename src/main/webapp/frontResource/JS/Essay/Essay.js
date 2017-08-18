'use strict';

$(document).ready( function() {
    var essayId = $('#essayId').text();
    $.ajax({
        type: 'GET',
        url: '/YourAnswer/api/Essay/' + essayId,
        success: function (data) {
            var data = data;
            if (data.resultCode === 200) {
                var essay = data.value;
                showEssay(essay);
            } else {
                window.location.href = "/YourAnswer/wrongInfo";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });


    function showEssay(essay) {
        document.title = essay.essayTitle;
        new Vue({
            el: '#app1',
            data: {
                essay : essay
            }
        });
    }
});
