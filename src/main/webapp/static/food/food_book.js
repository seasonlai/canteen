$(function () {

    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
    });

    queryList();

});


var foodsCache;

function queryList() {

    var kind = $('#searchKind').val();
    var name = $('#searchContent').val();

    $.ajax({
        url: basePath + 'food/food_list',
        dataType: 'json',
        data: {foodName: name, kind: kind},
        type: 'post',
        success: function (msg) {
            var $gallery = $('#gallery-wrapper');
            $gallery.empty();
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            var foods = msg.data;
            foodsCache = msg.data;
            var foodStr = '<div class="row gallery-row">';
            foods.forEach(function (food, index) {
                var str = "";
                str += '<div class="col-md-3">';
                str += '<div class="img-box">';
                str += '<img width="280" height="280" src="' + food.foodImage + '"';
                str += ' class="thumbnail img-responsive"/>';
                str += '<div class="text-left">';
                str += '<span style="padding-left: 5px"><STRONG>' + food.foodName + '</STRONG></span>';
                str += '<span style="padding-left: 12px"><small>￥' + food.foodPrice + '</small></span>';
                str += '<span style="float: right;padding-right: 10px;cursor: pointer" onclick="sureToCard(' + index + ')"><i class="fa fa-shopping-cart"></i></span>';
                str += '</div>';
                str += '</div>';
                str += '</div>';
                foodStr += str;
            });
            foodStr += '</div>';
            $gallery.html(foodStr);
            foodsCache = foods;
        },
        error: function () {
            alert('请求失败');
        }
    })
}


function sureToCard(index) {
    var food = foodsCache[index];
    $('#modal_title').html(food.foodName);
    $('#myModal #foodImg').attr('src', food.foodImage);
    $('#myModal').modal('show');

}

function addCount() {

    var $count = $("#myModal #foodCount");
    var count = $count.val();
    try {
        var number = new Number(count);
        $count.val(number >= 1 ? number + 1 : 1);
    } catch (e) {
        $count.val(1);
    }
}
function cutCount() {
    var $count = $("#myModal #foodCount");
    var count = $count.val();
    try {
        var number = new Number(count);
        $count.val(number > 1 ? number - 1 : 1);
    } catch (e) {
        $count.val(1);
    }
}

function addToCard() {
    var $myModal = $('#myModal');
    var date = $myModal.find("#foodDate").val();
    if (!date) {
        alert('日期为空')
        return
    }

    var time = $myModal.find("#foodTime").val();

    var count = $myModal.find("#foodCount").val();
    try {
        if (new Number(count) <= 0) {
            alert('数量必须大于0');
        }
    } catch (e) {
        alert('数量格式不对');
        return
    }

    $.ajax({
        url: basePath + 'food/food_list',
        dataType: 'json',
        data: {foodName: name, kind: kind},
        type: 'post',
        success: function (msg) {


        },
        error: function () {
            alert("请求失败")
        }
    })

}