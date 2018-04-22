$(function () {
   queryPageList();
});


function queryPageList(num) {
    num = num || num < 1 ? num : '1';

    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();

    $.ajax({
        url: basePath + "user/user-list",
        data: {pageNo: num, pageSize: 10},
        dataType: 'json',
        type: 'post',
        success: function (msg) {
            if (msg.code != '0') {
                alertWindow(msg.msg);
                return;
            }
            var page = msg.data;

            var totalPageCount = page.totalPageCount;

            var curPageNo = page.currentPageNo;

            dataCache = page.data;

            var $tBody = $('#tBody');
            $tBody.empty();

            var dataStr;
            //数据处理
            for (var i = 0; i < page.data.length; i++) {
                dataStr += '<tr>';
                dataStr += '<td>';
                dataStr += '<span>' + (i + 1) + '</span>';
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].userName;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].password;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].userType == 1?"普通用户":"管理员";
                dataStr += '</td>';
                dataStr += '</tr>';
            }
            $tBody.html(dataStr);

            //分页处理
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
            alertWindow('请求失败')
        }
    })

}
