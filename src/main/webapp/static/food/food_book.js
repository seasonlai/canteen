$(function () {

    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
    });

    queryList();
    // var tomorrow = new Date();
    // tomorrow.setDate(tomorrow.getDate() + 1);
    // var s1 = ;
    $('#foodDate').datepicker('setStartDate',getDateStr(1));


    $('#myModal #foodCount').bind('input propertychange', function () {
        var a = $('#myModal #foodCount').val();
        var price = $('#myModal #foodPrice').html();
        $('#myModal #foodTotalPrice').html(a*price);
    });

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
                str += '<div class="img-box text-center thumbnail">';
                str += '<img width="280" height="280" src="' + food.foodImage + '"';
                str += ' class="img-responsive text-center" style="margin-bottom:20px"/>';
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
    $('#myModal #modal_title').html(food.foodName);
    $('#myModal #foodPrice').html(food.foodPrice);
    $('#myModal #foodTotalPrice').html(food.foodPrice);
    $('#myModal #foodImg').attr('src', food.foodImage);
    $('#myModal #foodId').val(food.foodId);
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
    var a = $count.val();
    var price = $('#myModal #foodPrice').html();
    $('#myModal #foodTotalPrice').html(a*price);
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
    var a = $count.val();
    var price = $('#myModal #foodPrice').html();
    $('#myModal #foodTotalPrice').html(a*price);
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

    var foodId = $myModal.find('#foodId').val();
    var foodTotalPrice = $myModal.find('#foodTotalPrice').html();
    $.ajax({
        url: basePath + 'shopcar/add',
        dataType: 'json',
        data: {foodTime: time, foodCount: count,
            foodId:foodId,foodTotalPrice:foodTotalPrice,
            subscribeDate:date
        },
        type: 'post',
        success: function (msg) {
            if(msg.code!='0'){
                alert(msg.msg);
                return;
            }
            $myModal.modal('hide');
            alert('添加成功');
        },
        error: function () {
            alert("请求失败")
        }
    })

}

function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate() + AddDayCount);   //获取AddDayCount天后的日期
    var year = dd.getFullYear();
    var mon = dd.getMonth()+1;                             //获取当前月份的日期
    var day = dd.getDate();
    return year + "-" + mon + "-" + day;
}