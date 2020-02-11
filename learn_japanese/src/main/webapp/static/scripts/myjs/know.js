//打开新增类型页面
function addType(){
    layer.open({
        type: 2,
        title: '新增类型',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/knowController/type_add'
    });
}





//打开新增知识页面
function addKnow(id){
    layer.open({
        type: 2,
        title: '新增小知识',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/knowController/know_add?type_id='+id
    });
}



//打开单词列表页面
function knowList(id){
    layer.open({
        type: 2,
        title: '新增章节',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['85%', '85%'],  //大小
        content: contextPath + '/manage/knowController/know_list?type_id='+id
    });
}




