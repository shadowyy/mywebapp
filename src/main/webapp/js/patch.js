/**
 * Created by alice on 2016/11/7.
 */

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$.postJson = function (url, data, success) {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        url: url,
        data: JSON.stringify(data),
        success: success,
        error: function (data) {
            if (data.status === 400) {
                layer.msg('param problem');
            }
            if (data.status === 500) {
                layer.msg('server problem');
            }
        }
    });
};