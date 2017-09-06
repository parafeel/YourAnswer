'use strict';

$(document).ready(function() {
    var qTopics = [];

    $('#addQuestionBtn').on("click",function () {
        var qTitle = $('#qTitle').val();
        var qDetail = editor.txt.html();
        if( qTitle == "" || qTitle.trim() == "" || qTitle==null) {
            $('#addQuestionMessage').addClass("alert-warning");
            $('#addQuestionMessage').text("标题不能为空！");
            return false;
        }
        if( editor.txt.text() == "" || editor.txt.text().trim() == "" ) {
            $('#addQuestionMessage').addClass("alert-warning");
            $('#addQuestionMessage').text("问题详情不能为空！");
            return false;
        }
        $.ajax({
            url: "/YourAnswer/api/Question",
            type: 'POST',
            data: {
             'qTitle': qTitle,
             'qDetail': qDetail,
             'qTopics': qTopics
            },
            success: function (data) {
                var question = data.value;
                if(data.resultCode === 200) {
                    window.location.href="/YourAnswer/Question/" + question.qId;
                } else {
                    $('#addQuestionMessage').addClass("alert-warning");
                    $('#addQuestionMessage').text("提问未成功！");
                }
            },
            error: function () {
                window.location.href="/YourAnswer/makeQuestion";
                $('#addQuestionMessage').addClass("alert-warning");
                $('#addQuestionMessage').text("暂时无法提问！");
            }
        });
    });

    new Vue({
        el: '#app',
        data: {
            topics:[],
            selectedData:[],
            endData:[]
        },
        methods: {
            showSearchData: function () {
                var _self = this;
                var keywords = $('#searchTopic').val();
                if(keywords != null && keywords != ''&& keywords.trim() != '') {
                    $.get('/YourAnswer/api/Topic?keywords=' + keywords,
                        function(data,status){
                            if(status) {
                                var topics = data;
                                if(topics == null || topics == undefined || topics == '') {
                                    _self.topics = [];
                                    $('#searchTopicMsg').text("未找到相关话题，可前往话题广场添加");
                                } else if(keywords == "") {
                                    $('#searchTopicMsg').html("");
                                }else {
                                    _self.topics = topics;
                                    $('#searchTopicMsg').html("");
                                }
                            }
                        }
                    );
                }
            },
            LeftToRight: function(topic) {
                this.topics.removeByValue(topic);
                if(!this.selectedData.hasThisVal(topic) && this.selectedData.length <=4) {
                    this.selectedData.push(topic);
                } else if(!this.selectedData.hasThisVal(topic) && this.selectedData.length >4) {
                    $('#searchTopicMsg').text("最多只能添加五个相关话题");
                }
            },
            RightToLeft: function(sData) {
                this.selectedData.removeByValue(sData);
                // this.topics.push(sData);
            },
            listTopic: function () {
                this.endData = this.selectedData;
                qTopics = this.selectedData;
            }
        }
    });

    Array.prototype.removeByValue = function(val) {
        for(var i=0; i<this.length; i++) {
            if(this[i] == val) {
                this.splice(i, 1);
                break;
            }
        }
    };
    Array.prototype.hasThisVal = function(val) {
        for(var i=0; i<this.length; i++) {
            if(this[i] == val) {
                return true;
            }
        }
        return false;
    };
});