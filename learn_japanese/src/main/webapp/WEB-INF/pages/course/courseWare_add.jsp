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
            <td class="tdleft"><span style="color:red;">*</span>课件名称：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="divdata"></textarea>
                    <input type="text" name="courseWare_name" id="courseWare_name" lay-verify="required" autocomplete="off" class="layui-input" value="${result.video_name}"  maxlength="30">
                </div>
            </td>

            <td class="tdleft"><span style="color:red;">*</span>课件课时：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="text"   name="courseWare_num" id="courseWare_num"
                           lay-verify="required" autocomplete="off" class="layui-input"   maxlength="30">
                </div>
            </td>
</tr>
<tr>
            <td class="tdleft"><span style="color:red;">*</span>上传课件：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="file" id="file" name="file"  />
                </div>
            </td>

        </tr>
    </table>
    <div class="layui-form-item" style="margin-top:15px;text-align: center;">
        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="save('${pd.course_id}');" type="button"><i class="layui-icon layui-icon-form"></i>保存</button>
        <button class="layui-btn layui-btn-sm" onclick="parent.layer.closeAll();" type="button"><i class="layui-icon layui-icon-close"></i>取消</button>
    </div>
</form>
<script>

    layui.use(['form','laydate'], function(){
    });

    function save(id){
        var formData = new FormData($( "#uploadForm" )[0]);
        console.log(formData);
        if ($('#courseWare_name').val() == '') {
            layer.alert("课件名称不能为空");
            return false;
        }
        if ($('#courseWare_num').val() == '') {
            layer.alert("课件课时不能为空");
            return false;
        }
        if ($('#file').val() == '') {
            layer.alert("请选择需要上传的课件");
            return false;
        }
        layer.load();
        $.ajax({
            url: contextPath+'/manage/courseController/addCourseWare?course_id='+id,
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
                } else{
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