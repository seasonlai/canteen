// $(function() {
//     $('#side-menu').metisMenu();
// });

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function () {
    $(window).bind("load resize", function () {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function () {
        return this.href == url;
    }).addClass('active').parent().addClass('active');

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }

    $('li.user-avatar').mouseover(function () {
        $(this).addClass('open');
    }).mouseout(function () {
        $(this).removeClass('open');
    });


});

$.ajaxSetup({
//设置ajax请求结束后的执行动作
    complete: function (XMLHttpRequest, textStatus) {
// 通过XMLHttpRequest取得响应头，sessionstatus
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
        if (sessionstatus == "TIMEOUT") {
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            win.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
        }
    }

});


function sureWindow(msg) {

    var fail;

    var success;

    bootbox.confirm({
        title: "确认框",
        message: msg,
        size: "small",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> 取消'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> 确定'
            }
        },
        callback: function (result) {
            if (result) {
                if (typeof success == 'function') {
                    success();
                }
            } else {
                if (typeof fail == 'function') {
                    fail();
                }
            }
            // console.log('This was logged in the callback: ' + result);
        }

    });

    return {
        sucess: function (fuc) {
            success = fuc;
        },
        fail: function (fuc) {
            fail = fuc;
        }
    }

}

function alertWindow(msg) {

    var success;

    bootbox.alert({
        title: "提示框",
        message: msg,
        size: "small",
        buttons: {
            ok: {
                label: '<i class="fa fa-check"></i> 确定'
            }
        },
        callback: function () {
            if (typeof success == 'function') {
                success();
            }
        }

    });

    return {
        on: function (fuc) {
            success = fuc;
        }
    }
}

function toHome() {
    $('.side-nav>li:first>a>i').click();
}