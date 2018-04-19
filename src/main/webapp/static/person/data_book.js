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
    
}