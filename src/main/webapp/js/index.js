$(function () {
    var getData=function(){
        var data = {
            id: 1,
            age: -1,
            name: "jack"
        };


        $.ajax({
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            url: "/list/testValid",
            data: JSON.stringify(data),
            success: function (json) {
                $('#info').html(JSON.stringify(json));
            }
        });
    };
    getData();
    $("button").click(getData);
}());

