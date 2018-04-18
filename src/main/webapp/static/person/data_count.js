$(function () {
    var curKind = $('#countKindList>li:first>a:first').trigger("click");
    // $('#countKindBtn').html(curKind + '<span class="caret"></span>')


});


function refreshChart(kindCode, kindName) {
    $('#countKindBtn').html(kindName + '<span class="caret"></span>')
    queryCountData(kindCode);

}


function queryCountData(kindCode) {
    $('#morris-area-chart').empty();
    $.ajax({
        url: basePath + '/data/data-count',
        type: 'post',
        data: {kind: kindCode},
        dataType: 'json',
        success: function (msg) {
            if(msg.code!=0){
                alert(msg.msg);
                return;
            }
            new Morris.Line({
                element: 'morris-area-chart',
                data:msg.data,
                xkey: 'dataDate',
                ykeys: ['actualNum', 'estimateNum'],
                labels: ['实际用餐数', '预估用餐数'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });
        },
        error: function () {
            alert('请求失败');
        }
    })

    // new Morris.Line({
    //     // ID of the element in which to draw the chart.
    //     element: 'morris-area-chart',
    //     // Chart data records -- each entry in this array corresponds to a point on
    //     // the chart.
    //     data: [
    //         { year: '2010-10-01', value: 20 },
    //         { year: '2010-10-02', value: 10 },
    //         { year: '2010-10-03', value: 5 },
    //         { year: '2010-10-04', value: 5 },
    //         { year: '2010-10-05', value: 20 }
    //     ],
    //     // The name of the data record attribute that contains x-values.
    //     xkey: 'year',
    //     // A list of names of data record attributes that contain y-values.
    //     ykeys: ['value'],
    //     // Labels for the ykeys -- will be displayed when you hover over the
    //     // chart.
    //     labels: ['Value']
    // });
}