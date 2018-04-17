$(function () {
    var curKind = $('#countKindList>li:first>a:first').trigger("click");
    // $('#countKindBtn').html(curKind + '<span class="caret"></span>')


});


function refreshChart(kindCode, kindName) {
    $('#countKindBtn').html(kindName + '<span class="caret"></span>')
    queryCountData(kindCode);

}

function queryCountData(kindCode) {
    $.ajax({
        url: basePath + '/data/data-count',
        type: 'post',
        data: {kind: kindCode},
        dataType: 'json',
        success: function (msg) {
            Morris.Area({
                element: 'morris-area-chart',
                data:msg.data,
                xkey: 'period',
                ykeys: ['iphone', 'ipad', 'itouch'],
                labels: ['iPhone', 'iPad', 'iPod Touch'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });
        },
        error: function () {
            alert('请求失败');
        }
    })


}