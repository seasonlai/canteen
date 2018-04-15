var modifyType;

$(function () {
    $("#foodModify").ajaxForm(foodModify);

})


function publishFood() {
    modifyType = '1';
    var $foodModify = $('#foodModify');
    $foodModify.find('#food_modify_title').html('发布新餐品');
    $foodModify.find('input').val('');
    $foodModify.modal('show');
}


function updateFood(obj) {
    modifyType = '2';
}


function foodModify() {
    var $foodModify = $('#foodModify');
    var img = $foodModify.find('#foodImg').val();
    if(modifyType=='1'&&!img){
        alert('图片不能为空');
        return;
    }
    var foodName = $foodModify.find('#foodName').val();
    if(modifyType=='1'&&!foodName){
        alert('名称不能为空');
        return;
    }
    var foodPrice = $foodModify.find('#foodPrice').val();
    if(modifyType=='1'&&!foodPrice){
        alert('价格不能为空');
        return;
    }
    var foodKind = $foodModify.find('#foodKind').val();
    $('#foodForm').ajaxSubmit({
        url:basePath+'food/food_edit-add.html',
        type: "post", /*设置表单以post方法提交*/
        dataType: "json", /*设置返回值类型为文本*/
        success:function (data) {
            if(data.code!=0){
               alert(data.msg);
                return;
            }
            $foodModify.modal('hide');
            alert('添加成功')
        },
        error:function (msg) {
            alert(msg)
        }
    })
}