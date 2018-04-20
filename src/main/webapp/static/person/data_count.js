$(function () {
    var curKind = $('#countKindList>li:first>a:first').trigger("click");
    // $('#countKindBtn').html(curKind + '<span class="caret"></span>')


});


function refreshChart(kindCode, kindName) {
    $('#countKindBtn').html(kindName + '<span class="caret"></span>')
    queryCountData(kindCode);

}

var logFilePath;

function queryCountData(kindCode) {
    $('#morris-area-chart').empty();
    $('#processbar').modal('show');
    $.ajax({
        url: basePath + '/data/data-count',
        type: 'post',
        data: {kind: kindCode},
        dataType: 'json',
        success: function (msg) {
            if (msg.code != 0) {
                alertWindow(msg.msg);
                return;
            }
            logFilePath = msg.data.logFilePath;
            new Morris.Area({
                element: 'morris-area-chart',
                data: msg.data.dataList,
                xkey: 'dataDate',
                ykeys: ['actualNum', 'estimateNum'],
                labels: ['实际用餐数', '预估用餐数'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });

            $('#processbar').modal('hide');
        },
        error: function () {
            $('#processbar').modal('hide');
            alertWindow('请求失败');
        }
    })

}


function getCountLogFile() {
    window.open(basePath + logFilePath);
}