$(function () {
    var data = {
        id: 1,
        age: 22,
        name: "jack"
    };
    // $.getJSON("/mywebapp/list/queryUser", JSON.stringify(data), function (json) {
    //     $('#info').html(json);
    // });
    $.ajax({
        type: "GET",
        url: "/mywebapp/list/queryUser",
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function (json) {
            $('#info').html(json);
        }
    });
}());

