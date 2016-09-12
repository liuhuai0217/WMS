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
	$("#editForm").submit(function(){
		$(".selected_List option").prop("selected",true);
	})
})