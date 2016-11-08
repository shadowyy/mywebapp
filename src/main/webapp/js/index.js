$(function () {
    var errorFunc = function (data) {
        if (data.status === 400) {
            layer.msg('参数有问题');
        }
        if (data.status === 500) {
            layer.msg('服务器有问题');
        }
    };

    var getData = function () {
        var data = {
            // id: 1,
            // age: -1,
            // name: "jack"
        };

        $.ajax({
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            url: "/list/testValid",
            data: JSON.stringify($.extend(data, $('form').serializeObject())),
            success: function (json) {
                if (json.flag) {
                    $('#info').html(JSON.stringify(json.data));
                } else {
                    layer.msg(json.msg);
                }
            },
            error: errorFunc
        });
    };


    getData();
    $("button").click(getData);
}());

