var modifyType;

$(function () {
    $("#foodModify").ajaxForm(foodModify);
    queryList();
});


function publishFood() {
    modifyType = '1';
    var $foodModify = $('#foodModify');
    $foodModify.find('#food_modify_title').html('发布新餐品');
    $foodModify.find('input').val(null);
    $foodModify.modal('show');
}


function updateFood(index) {
    modifyType = '2';

    var food = foodsCache[index];

    var $foodModify = $('#foodModify');

    $foodModify.find('input').val(null);

    $foodModify.find('#foodName').val(food.foodName);
    $foodModify.find('#foodPrice').val(food.foodPrice);
    $foodModify.find('#foodKind').val(food.foodKind.kindCode);
    $foodModify.find('#foodId').val(food.foodId);
    $foodModify.modal('show');
}

function deleteFood(index) {

    var food = foodsCache[index];
    if(!confirm("确定删除改商品吗？")){
        return;
    }

    $.ajax({
        url: basePath + 'food/food_edit-del',
        dataType: 'json',
        data: {foodId: food.foodId},
        type: 'post',
        success: function (msg) {
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            alert('删除成功');
            queryList();
        },
        error:function () {
            alert('请求失败');
        }
    });
}

function foodModify() {
    var $foodModify = $('#foodModify');
    var img = $foodModify.find('#foodImg').val();
    if (modifyType == '1' && !img) {
        alert('图片不能为空');
        return;
    }
    var foodName = $foodModify.find('#foodName').val();
    if (!foodName) {
        alert('名称不能为空');
        return;
    }
    var foodPrice = $foodModify.find('#foodPrice').val();
    if (!foodPrice) {
        alert('价格不能为空');
        return;
    }
    var foodKind = $foodModify.find('#foodKind').val();
    $('#foodForm').ajaxSubmit({
        url: basePath + (modifyType == '1'?'food/food_edit-add':'food/food_edit-update'),
        type: "post", /*设置表单以post方法提交*/
        dataType: "json", /*设置返回值类型为文本*/
        success: function (data) {
            if (data.code != 0) {
                alert(data.msg);
                return;
            }
            $foodModify.modal('hide');
            alert('添加成功');
            queryList();
        },
        error: function (msg) {
            alert("请求失败")
        }
    })
}
var foodsCache;

function queryList() {

    var kind = $('#searchKind').val();
    var name = $('#searchContent').val();

    $.ajax({
        url: basePath + 'food/food_list',
        dataType:'json',
        data: {foodName: name, kind: kind},
        type:'post',
        success: function (msg) {
            var $gallery = $('#gallery-wrappe');
            $gallery.empty();
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            var foods = msg.data;
            var foodStr='<div class="row gallery-row">';
            foods.forEach(function (food, index) {
                var str = "";
                str += '<div class="col-md-3 img-container">';
                str += '<div class="img-box">';
                str += '<span class="icon edit" onclick="updateFood('+index+')">';
                str += '<a data-toggle="modal"><i class="gallery-edit"></i></a>';
                str += '</span>';
                str += '<span class="icon trash" onclick="deleteFood('+index+')">';
                str += '<i class="gallery-trash"></i>';
                str += '</span>';
                str += '<img width="280" height="280" src="' + food.foodImage + '"';
                str += ' class="thumbnail img-responsive"/>';
                str += '<div class="text-left">'
                str += '<span style="padding-left: 5px">' + food.foodName + '</span>';
                str += '<span style="float: right;padding-right: 12px">￥' + food.foodPrice + '</span>';
                str += '</div>';
                str += '</div>';
                str += '</div>';
                foodStr+=str;
            });
            foodStr+='</div>';
            $gallery.html(foodStr);
            foodsCache = foods;
        },
        error:function(){
            alert('请求失败');
        }
    })
}
