$(function () {

    queryOrderList();

});

var orderCache;
var lastStatus = -1;

function switchOrderStatusBtn(orderStatus) {
    var $activeBtn = $('#statusBtnGroup button[status='+orderStatus+']');
    $activeBtn.addClass('active');
    $activeBtn.siblings().removeClass('active');
}
function queryOrderList(orderStatus) {
    orderStatus = orderStatus == null ? lastStatus : orderStatus;
    lastStatus = orderStatus;
    switchOrderStatusBtn(orderStatus);
    $.ajax({
        url: basePath + 'order/list',
        dataType: 'json',
        type: 'get',
        data: {orderStatus: orderStatus},
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
                var str = "<tr style='border-bottom: solid silver " + (index > 0 ? "1px" : "0") + "'>";
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
    if (!window.confirm("确定取消订单吗")) {
        return;
    }
    var order = orderCache[index];
    var $processbar = $('#processbar');
    $processbar.modal('show');
    $.ajax({
        url: basePath + 'order/cancel',
        dataType: 'json',
        type: 'post',
        contentType : "application/json;charset=UTF-8",
        data: JSON.stringify(order),
        success: function (msg) {
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }

            setTimeout(function () {
                $processbar.modal('hide');
                queryOrderList();
            }, 1000);
        },
        error: function () {
            alert('请求失败');
        }
    })
}


function delOrder(index) {
    if (!window.confirm("确定删除该订单吗")) {
        return;
    }
    var order = orderCache[index];
    var $processbar = $('#processbar');
    $processbar.modal('show');
    $.ajax({
        url: basePath + 'order/del',
        dataType: 'json',
        type: 'post',
        contentType : "application/json;charset=UTF-8",
        data: JSON.stringify(order),
        success: function (msg) {
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }

            setTimeout(function () {
                $processbar.modal('hide');
                queryOrderList();
            }, 1000);
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
