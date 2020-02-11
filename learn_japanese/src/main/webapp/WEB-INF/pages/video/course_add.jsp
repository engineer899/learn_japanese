<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="../allresource.jsp"%>
    <style>
        .tdleft{background-color: #FBFBFB;width:20%;text-align: right;}
    </style>
</head>
<body>
<form class="layui-form" enctype="multipart/form-data"  method= "post" id="uploadForm">
    <table class="layui-table" style="width:99%;margin:0px 5px 0px 5px;">
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>课程类型：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="type"   id="type" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>

            <td class="tdleft"><span style="color:red;">*</span>课程名称：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="course_name" id="course_name" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;"></span>课程介绍：</td>
            <td colspan="3">
                <textarea id="introduce" name="introduce" style="width:100%;height:200px;"></textarea>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>课程图片：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="file" name="image_url" id="image_url"    lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
        </tr>

    </table>
    <div class="layui-form-item" style="margin-top:15px;text-align: center;">
        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="save();" type="button"><i class="layui-icon layui-icon-form"></i>保存</button>
        <button class="layui-btn layui-btn-sm" onclick="parent.layer.closeAll();" type="button"><i class="layui-icon layui-icon-close"></i>取消</button>
    </div>
</form>
<script>

    layui.use(['form','laydate'], function(){
    });

    function save(){
        var formData = new FormData($( "#uploadForm" )[0]);
        console.log(formData);
        // layer.load();
        $.ajax({
            url: contextPath+'/manage/videoController/addCourse',
            type: "post",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,

            success: function (data) {
                console.log(data);
                var jsondata= JSON.parse(data);
                layer.closeAll('loading');
                if(jsondata.message == "success"){
                    parent.layer.alert("保存成功",{icon: 1,closeBtn: 0},function(index) {
                        parent.location.reload();
                    });
                }else{
                    parent.layer.alert(jsondata.message,{icon: 2,closeBtn: 0},function(index) {
                        parent.location.reload();
                    });
                }
            }
        })
    }
</script>


</body>
</html>