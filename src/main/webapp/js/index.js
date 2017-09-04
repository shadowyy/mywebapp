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
    getData();
    $("button").click(getData);
}());