/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-10-17 11:15:51
 * @version $Id$
 */

$(document).ready(function() {
    var urlstr = window.location.href;
    $('#navbar a').each(function() {
        if(urlstr.indexOf($(this).attr('href')) > -1) {
            $(this).parent().addClass('active');
        } 
    });
});















