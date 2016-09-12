$(function(){
	$(".btn_reload").click(function(){
		var url  = $(this).data("url");
		$.dialog({
			title:'操作提示',
			icon:'face-smile',
			content:'加载会消耗较长的时间,请稍等',
			cancel:true,
			ok:function(){
				var dialog = $.dialog({
					title:"操作提示",
					icon:"face-smile",
				});
				$.get(url,function(){
					dialog.content("加载成功").button({
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