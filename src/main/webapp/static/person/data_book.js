$(function () {
    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
    });
    $("[data-toggle='tooltip']").tooltip();
    queryPageList();
});

function queryPageList() {

    var date = $('#countTime').val();

    if(!date){
        alert('请选择统计日期');
        return;
    }

    var foodTime = $('#foodTimeKind').val();
    var foodKind = $('#foodKind').val();

    var content = $('#searchContent').val();

    $.ajax({
        url: basePath + '/order/data-list',
        type: 'post',
        data: {foodKind: foodKind,date: date,foodTime: foodTime,content: content},
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



}