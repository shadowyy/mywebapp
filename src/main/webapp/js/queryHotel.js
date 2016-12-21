/**
 * Created by yy on 2016/12/20.
 */
$(function () {
    var getData = function () {
        $.postJson("/hotel/query", $('form').serializeObject(), function (json) {
            if (json.flag) {
                $('#table_local').DataTable({
                    data: json.data,
                    columns: [
                        { data: 'name' },
                        { data: 'address' },
                        { data: 'gender' },
                        { data: 'mobile' },
                        { data: 'tel' },
                        { data: 'version' }
                    ]
                });
            } else {
                layer.msg(json.msg);
            }
        });
    };
    $('#but_submit').click(getData);
}());