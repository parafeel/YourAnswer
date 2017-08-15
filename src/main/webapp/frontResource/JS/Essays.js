$(document).ready( function() {

    $.get('/YourAnswer/api/Essays',
        function(data,status){
            if(status) {
                if(data.resultCode === 200) {
                    var essays = data.value;
                    showData(essays);
                }
            }
        }
    )

    function showData(essays) {
        new Vue({
            el: '#app',
            data: {
                essays
            }
        });
        Vue.filter('time', function (value) {
            return goodTime(value);
        });

    }
});
