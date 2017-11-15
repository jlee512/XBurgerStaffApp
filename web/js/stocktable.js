$(document).ready( function() {
    $.ajax({
        url: '/stock_counts',
        async: true,
        type: 'GET',
        success: function (msg) {
            var stockNames = [];
            var stockCounts = [];

            for (var i in msg) {
                var val = msg[i];
                for (var j in val) {
                    var name = j;
                    var count = val[j];
                    
                }

            }

            console.log(stockNames);
            console.log(stockCounts);

        },
        error: function () {
            console.log("Error")
        }
    })
});


