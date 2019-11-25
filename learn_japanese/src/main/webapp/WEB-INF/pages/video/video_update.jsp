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
            <td class="tdleft"><span style="color:red;">*</span>视频名称：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="divdata"></textarea>
                    <input type="text" name="video_name" id="video_name" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>视频类型：</td>
            <td style="width:30%;">
                <select id="video_type" name="video_type" lay-verify="required">
                    <option value="">请选择课程类型</option>
                    <option <c:if test="${course.KIND == '零基础'}">SELECTED</c:if> value="0">零基础</option>
                    <option <c:if test="${course.KIND == 'N1'}">SELECTED</c:if> value="1">N1</option>
                    <option <c:if test="${course.KIND == 'N2'}">SELECTED</c:if> value="2">N2</option>
                    <option <c:if test="${course.KIND == 'N3'}">SELECTED</c:if> value="3">N3</option>
                </select>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>视频课时：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <select id="video_num" name="video_num" lay-verify="required">
                        <option value="">请选择课时</option>
                        <option <c:if test="${course.KIND == '第一课时'}">SELECTED</c:if> value="1">第一课时</option>
                        <option <c:if test="${course.KIND == '第二课时'}">SELECTED</c:if> value="2">第二课时</option>
                        <option <c:if test="${course.KIND == '第三课时'}">SELECTED</c:if> value="3">第三课时</option>
                        <option <c:if test="${course.KIND == '第四课时'}">SELECTED</c:if> value="4">第四课时</option>
                    </select>
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>上传视频：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="file" id="file" name="file" value="" />
                </div>
            </td>
        </tr>
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>课程知识点：</td>
            <td colspan="3">
                <textarea id="course_knowledge" name="course_knowledge" style="width:100%;height:400px;"></textarea>
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
        if ($('#video_name').val() == '') {
            layer.alert("视频名称不能为空");
            return false;
        }
        if ($('#video_type').val() == '') {
            layer.alert("视频类型不能为空");
            return false;
        }
        if ($('#video_num').val() == '') {
            layer.alert("视频课时不能为空");
            return false;
        }
        if ($('#file').val() == '') {
            layer.alert("请选择需要上传的视频");
            return false;
        }
        layer.load();
        $.ajax({
            url: contextPath+'/video/add',
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
                    parent.layer.alert("保存失败",{icon: 2,closeBtn: 0},function(index) {
                        parent.location.reload();
                    });
                }
            }
        })
    }


</script>


</body>
</html>