$(function () {
    queryList();
});



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
            var $gallery = $('#gallery-wrapper');
            $gallery.empty();
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            var foods = msg.data;
            var foodStr='<div class="row gallery-row">';
            foods.forEach(function (food, index) {
                var str = "";
                str += '<div class="col-md-3">';
                str += '<div class="img-box">';
                str += '<img width="280" height="280" src="' + food.foodImage + '"';
                str += ' class="thumbnail img-responsive"/>';
                str += '<div class="text-left">';
                str += '<span style="padding-left: 5px"><STRONG>' + food.foodName + '</STRONG></span>';
                str += '<span style="padding-left: 12px"><small>￥' + food.foodPrice + '</small></span>';
                str += '<span style="float: right;padding-right: 10px"><i class="fa fa-shopping-cart"></i></span>';
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