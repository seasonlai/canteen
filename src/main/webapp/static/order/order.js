$(function () {

    queryShopCarList();

});

var orderCache;

function queryShopCarList() {
    $.ajax({
        url: basePath + 'order/list',
        dataType: 'json',
        type: 'get',
        success: function (msg) {
            var $orderBody = $('#orderBody');
            $orderBody.empty();
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }
            var orders = msg.data;
            var orderStr = '';
            orders.forEach(function (order, index) {
                // if(index)
                var str = "<tr style='border-bottom: solid silver "+index>0?"1px":"0"+"'>";
                str += '<td>';
                str += '<span>' + order.orderId + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<img width="100" height="100" src="' + order.food.foodImage + '"/>';
                str += '<span style="padding-left: 10px;">' + order.food.foodName + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + order.foodCount + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + order.subscribeDate + '</span></br>';
                str += '<span>' + getFoodTime(order.foodTime) + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span>' + order.orderDate + '</span>';
                str += '</td>';
                var status = getOrderStatus(order.orderStatus);
                str += '<td>';
                str += '<span class="label ' + status.label + '">' + status.name + '</span>';
                str += '</td>';
                str += '<td>';
                str += '<span><small>￥</small>' + order.foodTotalPrice + '</span>';
                str += '</td>';
                str += '<td>';
                if (order.orderStatus == '1')
                    str += '<button class="btn btn-default btn-sm" onclick="cancelOrder(' + index + ')">取消订单</button></br>';
                str += '<button class="btn btn-default btn-sm" onclick="delOrder(' + index + ')">删除订单</button>';
                str += '</td>';
                str += '</tr>';
                orderStr += str;
            });
            $orderBody.html(orderStr);
            orderCache = orders;
        },
        error: function () {
            alert('请求失败');
        }
    })
}

function cancelOrder(index) {

}


function delOrder(index) {

}


function getFoodTime(kind) {

    for (var i in foodTime) {

        if (foodTime[i].code == kind)
            return foodTime[i].name;

    }

    return "&nbsp;";
}

function getOrderStatus(kind) {
    if (kind == null)
        return "&nbsp;";

    var obj = {};
    for (var i in orderStatus) {
        if (orderStatus[i].code == kind) {
            obj.name = orderStatus[i].name;
            break;
        }
    }

    switch (kind) {
        case 0://已完成
            obj.label = "label-success";
            break;
        case 1://未完成
            obj.label = "label-primary";
            break;
        case 2://已取消
            obj.label = "label-danger";
            break;
    }

    return obj;
}
