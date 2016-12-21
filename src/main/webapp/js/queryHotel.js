/**
 * Created by yy on 2016/12/20.
 */
$(function () {
    $('#table_local').DataTable({
        // ajax: function (data, callback, settings) {
        //     // $.postJson('/hotel/query', $('form').serializeObject(), function (json) {debugger
        //     //     callback(json.data);
        //     // });
        // },
        "bProcessing": true,
        "bServerSide": true,
        // "sAjaxSource": "/hotel/query",
        "fnServerData": function ( sSource, aoData, fnCallback ) {
            // /* Add some extra data to the sender */
            // aoData.push( { "name": "more_data", "value": "my_value" } );
            // $.getJSON( sSource, aoData, function (json) {
            //     /* Do whatever additional processing you want on the callback, then tell DataTables */
            //     fnCallback(json)
            // } );
            $.postJson('/hotel/query', $('form').serializeObject(), function (json) {debugger
                fnCallback(json.data);
            });
        },
        columns: [
            {data: 'name'},
            {data: 'address'},
            {data: 'gender'},
            {data: 'mobile'},
            {data: 'tel'},
            {data: 'version'}
        ]
    });
    // $('#but_submit').click(getData);
}());