$(function () {
    $('.datepicker').datepicker({
        autoclose: true,
        format: "yyyy-mm-dd",
        orientation: 'bottom',
        todayHighlight: true,
        language: "zh-CN"
    });
    $("[data-toggle='tooltip']").tooltip();
    queryPageList();
});

var modifyType;
function addData() {

    var $dataModify = $('#dataModify');

    $dataModify.find('input').val(null);

    modifyType = '1';
    $dataModify.modal('show');
}

function updateData() {

    modifyType = '2';
    var $checked = $('#tb_data tr[class!="headTitle"]').find('input:checked');

    if ($checked.length !== 1) {
        alert('请选择一个进行修改')
        return
    }

    $checked = $($checked[0]);
    var data = dataCache[new Number($checked.attr('index'))];
    var $dataModify = $('#dataModify');
    $dataModify.find('input').val(null);
    $dataModify.find('#dataDate').val(data.dataDate);
    $dataModify.find('#dataPerson').val(data.actualNum);
    $dataModify.find('#dataId').val(data.dataId);
    $dataModify.modal('show');
}

function delData() {
    var $checked = $('#tb_data tr[class!="headTitle"]').find('input:checked');
    if ($checked.length <= 0)
        return;
    if (!confirm('确定删除所选数据吗?'))
        return;

    var delItem = [];
    $checked.each(function () {
        var data = dataCache[new Number($(this).attr('index'))];
        delItem.push(data);
    });
    console.log(JSON.stringify(delItem));
    $.ajax({
        url: basePath + "data/data-del",
        contentType : "application/json;charset=UTF-8",
        data: JSON.stringify(delItem),
        dataType: 'json',
        type: 'post',
        success: function (msg) {
            if (msg.code != '0') {
                alert(msg.msg);
                return;
            }
            queryPageList();
            setTimeout(function () {
                alert('删除成功');
            }, 100);
        },
        error: function () {
            alert('请求失败')
        }
    });
}


function modifyData() {
    var $dataModify = $('#dataModify');

    var date = $dataModify.find('#dataDate').val();
    if (!date) {
        alert('日期不能为空');
        return;
    }
    var num = $dataModify.find('#dataPerson').val();
    if (!num) {
        alert('人数不能为空');
        return;
    }
    try {
        new Number(num)
    } catch (e) {
        alert('数字不合法');
        return;
    }
    var dataId = $dataModify.find('#dataId').val();
    $.ajax({
        url: basePath + "data/data-modify",
        data: {type: modifyType, dataId: dataId, actualNum: num, dataDate: date},
        dataType: 'json',
        type: 'post',
        success: function (msg) {
            if (msg.code != '0') {
                alert(msg.msg);
                return;
            }
            $dataModify.modal('hide');
            queryPageList();
            setTimeout(function () {
                alert('保存成功');
            }, 100);
        },
        error: function () {
            alert('请求失败')
        }
    })
}

var dataCache;

function queryPageList(num) {
    num = num || num < 1 ? num : '1';

    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();

    $.ajax({
        url: basePath + "data/data-list",
        data: {pageNo: num, pageSize: 10, startTime: startTime, endTime: endTime},
        dataType: 'json',
        type: 'post',
        success: function (msg) {
            if (msg.code != '0') {
                alert(msg.msg);
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
                dataStr += '<td align="center">';
                dataStr += '<input type="checkbox" index="' + i + '">';
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += '<span>' + (i + 1) + '</span>';
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].actualNum;
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].estimateNum ? page.data[i].estimateNum : '- -';
                dataStr += '</td>';
                dataStr += '<td>';
                dataStr += page.data[i].dataDate;
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
            alert('请求失败')
        }
    })

}

function clearInput(id) {
    $('#' + id).val(null);
}

function checkAll() {
    var status = $('#checkAllBtn').prop('checked');
    $('#tBody tr').find('input[type="checkbox"]').prop('checked', status);
}