$(function () {
    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
        // defaultDate : new Date()
    });
    $('.datepicker').datepicker('setDate', new Date());
    $("[data-toggle='tooltip']").tooltip();
    queryPageList();
});

function queryPageList(num) {

    num = num == null ? 1 : num;

    var date = $('#countTime').val();

    if (!date) {
        alert('请选择统计日期');
        return;
    }
    //
    // var foodTime = $('#foodTimeKind').val();
    // var foodKind = $('#foodKind').val();

    var content = $('#searchContent').val();

    $.ajax({
        url: basePath + '/order/data-list',
        type: 'post',
        data: {
            // foodKind: foodKind,
            date: date,
            // foodTime: foodTime,
            content: content,
            pageNum: num,
            pageSize: 10
        },
        dataType: 'json',
        success: function (msg) {
            if (msg.code != 0) {
                alert(msg.msg);
                return;
            }

            var page = msg.data;

            var totalPageCount = page.totalPageCount;

            var curPageNo = page.currentPageNo;

            var totalDataCount = page.totalCount;

            // dataCache = page.data;

            var $tBody = $('#tBody');
            $tBody.empty();

            var dataStr;
            //数据处理
            for (var i = 0; i < page.data.length; i++) {
                var orderTmp = page.data[i];
                dataStr += '<tr>';
                dataStr += '<td>';
                dataStr += '<span>' + (i + 1) + '</span>';
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += orderTmp.food.foodName;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += orderTmp.foodCount;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += orderTmp.orderId;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += orderTmp.orderDate;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += '<small>￥</small>'+orderTmp.foodTotalPrice;
                dataStr += '</td>';
                dataStr += '</tr>';
            }
            $tBody.html(dataStr);

            //分页处理
            $('#dataCount').html(totalDataCount);
            $('#pageCount').html(totalPageCount);
            var $splitBar = $('#splitBar');
            $splitBar.empty();
            var splitBarStr = '<li><a href="#" ' + (page.hasPreviousPage ? 'onclick="queryPageList(' + (curPageNo - 1) + ')"' : '') + '>&laquo;</a></li>';
            if (totalPageCount <= 5) {
                for (var i = 1; i <= totalPageCount; i++) {
                    splitBarStr += '<li ' + (i == curPageNo ? 'class="active" style="disabled:true;"' : '') + '>';
                    splitBarStr += '<a href="#" ' + (i != curPageNo ? 'onclick="queryPageList(' + i + ')"' : '') + '>' + i + '</a>';
                    splitBarStr += '</li>';
                }
            } else {
                var i = curPageNo > 3 ? curPageNo - 2 : 1;
                for (; i < curPageNo + 3 && i <= totalPageCount; i++) {
                    splitBarStr += '<li ' + (i == curPageNo ? 'class="active" style="disabled:true;"' : '') + '>';
                    splitBarStr += '<a href="#" ' + (i != curPageNo ? 'onclick="queryPageList(' + i + ')"' : '') + '>' + i + '</a>';
                    splitBarStr += '</li>';
                }
                if (i < totalPageCount)
                    splitBarStr += '...';
            }
            splitBarStr += '<li><a href="#" ' + (page.hasNextPage ? 'onclick="queryPageList(' + (curPageNo + 1) + ')"' : '') + '>&raquo;</a></li>';
            $splitBar.html(splitBarStr);
        },
        error: function () {
            alert('请求失败');
        }
    })
}

function getFoodTime(kind) {

    for (var i in foodTimes) {

        if (foodTimes[i].code == kind)
            return foodTimes[i].name;

    }

    return "&nbsp;";
}

function getFoodKind(kind) {

    for (var i in foodKinds) {

        if (foodKinds[i].kindCode == kind)
            return foodKinds[i].kindName;

    }
    return "&nbsp;";
}