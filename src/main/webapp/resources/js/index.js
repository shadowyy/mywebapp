$(function () {
    var data = {
        id: 1,
        age: 22,
        name: "jack"
    };
    $("button").click(function(){
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            url: "/mywebapp/list/queryUser",
            data: JSON.stringify(data),
            success: function (json) {
                $('#info').html(json);
            }
        });
    });

}());

