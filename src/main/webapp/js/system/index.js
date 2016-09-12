/* zTree插件加载目录的处理  */
//使用了ztree的插件 进行树的处理和演示
/*
 var setting = {	};
 var zNodes =[
 { name:"业务办理", open:true,
 children: [
 { name:"员工管理"},
 { name:"部门管理"},
 { name:"角色管理"},
 { name:"权限管理"},
 ]},
 ];

 $(document).ready(function(){
 $.fn.zTree.init($("#dleft_tab1"), setting, zNodes);
 });
 */
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
	callback: {
		onClick: function(event,treeId,treeNode){
			//console.debug(treeNode.action)
			if(treeNode.action){
				$("#here_area").html("当前位置：系统&nbsp;>&nbsp;"+treeNode.name);
				$("#rightMain").prop("src",treeNode.action+".action")
			}
		}
	},
	async:{
		enable:true,
		url:"/systemMenu_loadMenuByParentSn.action",
		autoParam:["sn=qo.parentSn"]
	}
};
//
var zNodes ={
    'business':[
        {id:1,pId:0, name:"业务办理",isParent:true,sn:"business"}
    ],
    'systemManage':[
        { id:2, pId:0, name:"系统管理",isParent:true,sn:"system"}
    ],
    'charts':[
        { id:3, pId:0, name:"报表管理",isParent:true,sn:"chart"}
    ]
};
//根据获取的参数进行获取
function loadMenus(sn){
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}
//加载当前日期
function loadDate(){
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag){
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if( flag==true ){	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width:'280px'});
        $('#top_nav').css({width:'77%', left:'304px'});
        $('#main').css({left:'280px'});
    }else{
        if ( left_menu_cnt.is(":visible") ) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width:'60px'});
            $('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
            $('#main').css({left:'60px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width:'280px'});
            $('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
            $('#main').css({left:'280px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
    }
}

$(function(){
    loadMenus("business");
    //-------------------------------------------------
    //为菜单的链接设置点击事件,把a标签上的data-url值设置给<iframe>元素的src属性.
    $("#TabPage2 li").click(function(){
        //为每个匹配的元素添加指定的类名。
        //清除所有的图标选中的状态
        $.each($("#TabPage2 li"), function(index, item){
            $(item).removeClass("selected"),
                $(item).children("img").prop("src","/images/common/"+(index+1)+".jpg")
        });

        //下面的代码 点击后会改变图标但是点击两个会改变两个
        $(this).addClass("selected");
        $(this).children("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
        //给树上面添加和更改图片的事件
        $("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png");
        
        loadMenus($(this).data("rootmenu"))
    },
    //-------------------------------------------------
    //加载日期
    loadDate(),
    // 显示侧边栏
    switchSysBar(true),
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    }))
});