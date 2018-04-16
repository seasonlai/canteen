$(function () {
    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
    });
    $("[data-toggle='tooltip']").tooltip();
});



function addData(){

    var $dataModify = $('#dataModify');

    $dataModify.find('input').val(null);

    $dataModify.modal('show');
}

function updateData(){

}

function delData(){

}



function modifyData(){

}