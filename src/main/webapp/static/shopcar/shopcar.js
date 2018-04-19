$(function () {


    $('#myShopCart').hide();

    queryShopCarList();


});


var shopCarCache;

function queryShopCarList() {
    $.ajax({
        url: basePath + 'shopcar/list',
        dataType: 'json',
        type: 'get',
        success: function (msg) {
            var $shopCarBody = $('#shopCarBody');
            $shopCarBody.empty();
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            var shopCars = msg.data;
            var foodStr = '';
            shopCars.forEach(function (shop, index) {
                var str = "<tr>";
                str += '<td align="center">';
                str += '<input type="checkbox" onclick="selectCar(this,' + index + ')" index="' + index + '">';
                str += '</td>';
                str += '<td>';
                str += '<img width="100" height="100" src="' + shop.food.foodImage + '"/>';
                str += '<span style="padding-left: 10px;">' + shop.food.foodName + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + shop.foodCount + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span><small>￥</small>' + shop.food.foodPrice + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + shop.subscribeDate + '</span></br>';
                str += '<span>' + getFoodTime(shop.foodTime) + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span><small>￥</small>' + shop.foodTotalPrice + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<button class="btn btn-primary btn-sm" onclick="submitOrder('+index+')">下单</button>';
                str += '</td>';
                str += '</tr>';
                foodStr += str;
            });
            foodStr += '</div>';
            $shopCarBody.html(foodStr);
            shopCarCache = shopCars;
        },
        error: function () {
            alert('请求失败');
        }
    })
}


function selectCar(obj, index) {

    var status = $(obj).prop('checked');
    var price = new Number(shopCarCache[index].foodTotalPrice);
    var $totalPrice = $('#totalPrice');
    var pre = new Number($totalPrice.html());
    $totalPrice.html(status ? pre + price : pre - price);
}

function getFoodTime(kind) {

    for (var i in foodTime) {

        if (foodTime[i].code == kind)
            return foodTime[i].name;

    }

    return "&nbsp;";
}

function submitOrder(index) {
    var shopCars=[]
    if(!index){
        var checkItems = $('#shopCarBody tr').find('input:checked[type="checkbox"]');
        if(!checkItems||checkItems.length<=0){
            alert('请选择餐品项');
            return;
        }
        checkItems.each(function () {
            shopCars.push(shopCarCache[$(this).attrs('index')]);
        })

    }else {
        shopCars.push(shopCarCache[index]);
    }

    $.ajax({
        url: basePath + 'order/submit',
        dataType: 'json',
        type: 'post',
        data:shopCars,
        success: function (msg) {
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            $('#totalPrice').html(0);
            queryShopCarList();
        },
        error: function () {
            alert('请求失败');
        }
    })
}


function checkAll() {
    var status = $('#checkAllBtn').prop('checked');
    var shopcars = $('#shopCarBody tr').find('input[type="checkbox"]');
    shopcars.prop('checked', status);
    if (!status) {
        $('#totalPrice').html(0);
        return;
    }
    var total = 0;
    shopcars.each(function () {
        total += shopCarCache[$(this).attr('index')].foodTotalPrice;
    });

    $('#totalPrice').html(total);
}