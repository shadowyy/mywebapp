/**
 * Created by yy on 2016/12/20.
 */
$(function () {
    var getData = function () {
        $.postJson("/hotel/query", $('form').serializeObject(), function (json) {
            if (json.flag) {
                json.data;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                $('#table_hotel').DataTable();
            } else {
                layer.msg(json.msg);
            }
        });
    };
    $('button[type="submit"]').click(getData);
}());