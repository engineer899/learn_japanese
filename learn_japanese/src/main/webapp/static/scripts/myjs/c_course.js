function deleteCourse(course_id){
    layer.confirm('是否删除？', {icon: 3, title:'提示'}, function(index){
        //执行删除
        $.ajax({
            url : contextPath + '/manage/courseController/deleteCourse',
            timeout : 300000,
            dataType : "json",
            type : "post",
            data : {
                "course_id" : course_id,
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


//打开新增课程页面
function addCourse(){
    layer.open({
        type: 2,
        title: '新增课程',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/courseController/course_add'
    });
}

//打开课件页面
function courseWareList(id){
    layer.open({
        type: 2,
        title: '课件列表',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['95%', '95%'],  //大小
        content: contextPath + '/manage/courseController/courseWare_list?course_id='+id
    });
}






//打开修改课程页面
function updateCourse(id){
    layer.open({
        type: 2,
        title: '修改课程信息',
        scrollbar: false, //父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  //笼罩层透明度
        maxmin: false,//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content:  contextPath + '/manage/videoController/course_update?course_id='+id
    });
}
//打开课程详情页面
function queryCourseDetails(id){
    layer.open({
        type: 2,
        title: '查看课程详情',
        scrollbar: false, 	//父页面是否有滚动条
        shadeClose: false,  //点击其他区域关闭弹窗
        shade: 0.5,  		//笼罩层透明度
        maxmin: false,		//是否显示最大化按钮
        area: ['100%', '100%'],  //大小
        content: contextPath + '/manage/videoController/course_details?course_id='+id
    });
}





