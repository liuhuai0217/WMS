jQuery.ajaxSettings.traditional = true;
//对分页的进行编写
$(function(){

    //进行页面翻页的操作
    $(".btn_page").click(function(){
        var currentPage = $(this).data("page")||$(":input[name='qo.currentPage']").val();
        $(":input[name='qo.currentPage']").val(currentPage);
        $("#searchForm").submit();
    });
    //更改当前页面显示的数量当改变了页面显示的数量时 强制转到第1页
    $(":input[name='qo.pageSize']").change(function(){
        $(":input[name='qo.currentPage']").val("1");
        $("#searchForm").submit();
    })
    //手动跳转到多少页
    $(".btn_go").click(function(){
        $("#searchForm").submit();
    })

});


//点击新增进行人员的增加,点击按钮打开页面
$(function(){
    $(".btn_input").click(function(){
        //当在list页面点击了新增的按钮以后,进行页面跳转
        window.location.href = $(this).data("url");
    });
});
/** table鼠标悬停换色* */
$(function() {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function() {
        $(this).css({background : "#CDDAEB"});
        $(this).children('td').each(function(index, ele){
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function() {
        $(this).css({background : "#FFF"});
        $(this).children('td').each(function(index, ele){
            $(ele).css({color: "#909090"});
        });
    });
});

//进行全选或者全不选
$(function () {
    $("#all").click(function () {
        $(".acb").prop("checked", this.checked);
    });

    //绑定按钮点击事件
    $(".btn_batch_delete").click(function () {
        /*  var ids = [];//被选中的复选框
         $.each($(".acb:checked"), function (index, item) {
         //console.debug(arguments);
         ids[index] = $(item).data("eid");
         });
         //console.debug(ids);*/

        var ids = $.map($(".acb:checked"), function (item) {
            return $(item).data("eid");
        });
        if (ids.length == 0) {
            $.dialog({
                title:'操作提示',
                icon:'face-simle',
                content:'请选择要删除的数据',
                callback:true,
                ok:true,
            })
            return;
        }
        $.dialog({
            title:'操作提示',
            icon:'face-smile',
            content:'你真的要删除吗?',
            cancel:true,
            ok:function(){
                var dialog = $.dialog({
                    title:'操作提示',
                    icon:"face-smile",
                })
                $.get($(".btn_batch_delete").data("url"), {ids: ids}, function () {

                    dialog.content("删除成功").button({
                        name:'知道了',
                        callback:function(){
                            window.location.reload();
                        }
                    })
                });
            }
        })
    });
});
//关于删除的操作
$(function(){
    $(".btn_delete").click(function(){
        var url  = $(this).data("url");
        $.dialog({
            title:'操作提示',
            icon:'face-smile',
            content:'你真的要删除吗?',
            cancel:true,
            ok:function(){
                var dialog = $.dialog({
                    title:"操作提示",
                    icon:"face-smile",
                });
                $.get(url,function(data){
                    dialog.content(data).button({
                        name:'知道了',
                        callback:function(){
                            window.location.reload();
                        }
                    })
                })
            }
        })
    })
})
//进行角色左右的互换
$(function(){
    $("#selectAll").click(function(){
        $(".all_List option").appendTo($(".selected_List"));
    })

    $("#select").click(function(){
        $(".all_List option:selected").appendTo($(".selected_List"));
    })

    $("#deselectAll").click(function(){
        $(".selected_List option").appendTo($(".all_List"));
    })

    $("#deselect").click(function(){
        $(".selected_List option:selected").appendTo($(".all_List"));
    })
    //自权限列表中去除已经分配的权限
    var ids =$.map($(".selected_List option"),function(item){
        return item.value;
    })
    $.each($(".all_List option"),function(index,item){
        var id = item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    })
    //经过测试在提交时 只有选中的才会提交 所以测试需要进行设置 把已经选着出来的数据进行设置属性
    //为选中的状态
})
//进行菜单左右的互换
$(function(){
    $("#selectAll1").click(function(){
        $(".all_menus_List option").appendTo($(".selected_menus_List"));
    })

    $("#select1").click(function(){
        $(".all_menus_List option:selected").appendTo($(".selected_menus_List"));
    })

    $("#deselectAll1").click(function(){
        $(".selected_menus_List option").appendTo($(".all_menus_List"));
    })

    $("#deselect1").click(function(){
        $(".selected_menus_List option:selected").appendTo($(".all_menus_List"));
    })
    //自权限列表中去除已经分配的权限
    var ids =$.map($(".selected_menus_List option"),function(item){
        return item.value;
    })
    $.each($(".all_menus_List option"),function(index,item){
        var id = item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    })
    //经过测试在提交时 只有选中的才会提交 所以测试需要进行设置 把已经选着出来的数据进行设置属性
    //为选中的状态

})
$(function(){
    $("#editForm").submit(function(){
        $(".selected_List option").prop("selected",true);
        $(".selected_menus_List option").prop("selected",true);
    })})