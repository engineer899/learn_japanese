function deleteWordById(id){
    layer.confirm('是否删除？', {icon: 3, title:'提示'}, function(index){
        //执行删除
        $.ajax({
            url : contextPath + '/manage/wordController/deleteWordById',
            timeout : 300000,
            dataType : "json",
            type : "post",
            data : {
                "word_id" : id,
            },
            success : function(data) {
                if(data.status == "success"){
                    layer.alert("删除成功",{icon: 1,closeBtn: 0},function(index){
                        setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
                        layer.closeAll('iframe');//关闭弹窗
                    });
                    //置为删除
                }else{
                    layer.alert("删除失败",{icon: 2,closeBtn: 0},function(index){
                        setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
                        layer.closeAll('iframe');//关闭弹窗
                    });
                }
            }
        })
        layer.close(index);
    });
}


//打开新增单词页面
function addWord(id){
    layer.open({
        type: 2,
        title: '新增视频',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/wordController/word_add?chapter_id='+id
    });
}
//打开修改视频页面
function updateVideo(id){
    layer.open({
        type: 2,
        title: '修改视频信息',
        scrollbar: false, //父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  //笼罩层透明度
        maxmin: false,//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content:  contextPath + '/manage/videoController/video_update?id='+id
    });
}
//打开视频详情页面
function queryVideoDetails(id){
    layer.open({
        type: 2,
        title: '查看视频详情',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/videoController/video_details?id='+id
    });
}




