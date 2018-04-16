$(function () {
    var curKind = $('#countKindList>li:first>a:first').trigger("click");
    // $('#countKindBtn').html(curKind + '<span class="caret"></span>')


});


function refreshChart(kindCode,kindName) {
    $('#countKindBtn').html(kindName + '<span class="caret"></span>')
    queryCountData(kindCode);

}

function queryCountData(kindCode){




}