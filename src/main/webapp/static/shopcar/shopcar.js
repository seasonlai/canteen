$(function () {

    queryShopCarList();

});


var shopCarCache;

function queryShopCarList() {
    $.ajax({
        url: basePath + 'shopcar/list',
        dataType: 'json',
        data: {foodName: name, kind: kind},
        type: 'post',
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
                str += '<input type="checkbox" onclick="checkEvent(this)"/>'
                str += '</td>';
                str += '<td>';
                str += '<img width="280" height="280" src="' + shop.food.foodImage + '"/>';
                str += '<span>' + shop.food.foodName + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + shop.foodCount + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + shop.foodTotalPrice + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + getFoodTime(shop.foodTime) + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<button class="btn btn-primary">预约</button>';
                str += '</td>';
                str += '</tr>';
                foodStr += str;
            });
            foodStr += '</div>';
            $shopCarBody.html(foodStr);
            foodsCache = shopCars;
        },
        error: function () {
            alert('请求失败');
        }
    })
}


function getFoodTime(kind) {

    for (var i in foodTime) {

        if (foodTime[i].code == kind)
            return foodTime[i].name;

    }

    return "&nbsp;";
}