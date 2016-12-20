/**
 * Created by yy on 2016/12/20.
 */
$(function () {
    var getData = function () {
        $.postJson("/list/queryUser", $('form').serializeObject(), function (json) {
            if (json.flag) {
                $('#info').html(JSON.stringify(json.data));
            } else {
                layer.msg(json.msg);
            }
        });
    };
    $('button[type="submit"]').click(getData);
}());