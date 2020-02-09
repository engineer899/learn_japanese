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
    <input type="text" name="chapter_id"  value="${pd.chapter_id}" style="display: none">

    <table class="layui-table" style="width:99%;margin:0px 5px 0px 5px;">
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>日本语：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="japanese"    id="japanese" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>中文意思：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="chinese" id="chinese" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>假名：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="kana" id="kana"    lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>音调：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="tone" id="tone" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>

        </tr>
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>词性：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="attribute" id="attribute" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>音频：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="file" id="voice_url" name="voice_url" />
                </div>
            </td>
        </tr>
        <%--<tr>--%>
        <%--<td class="tdleft"><span style="color:red;">*</span>例句1：</td>--%>
        <%--<td colspan="3">--%>
        <%--<textarea id="sentence_01" name="sentence_01" style="width:100%;height:50px;"></textarea>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="tdleft"><span style="color:red;">*</span>翻译1：</td>--%>
        <%--<td colspan="3">--%>
        <%--<textarea id="translate_01" name="translate_01" style="width:100%;height:50px;"></textarea>--%>
        <%--</td>--%>
        <%--</tr>--%>
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
        // if ($('#word_title').val() == '') {
        //     layer.alert("单词标题不能为空");
        //     return false;
        // }
        // if ($('#japanese').val() == '') {
        //     layer.alert("日语单词不能为空");
        //     return false;
        // }
        // if ($('#kana').val() == '') {
        //     layer.alert("假名不能为空");
        //     return false;
        // }
        // if ($('#chinese').val() == '') {
        //     layer.alert("中文意思不能为空");
        //     return false;
        // }
        // if ($('#attribute').val() == '') {
        //     layer.alert("词性不能为空");
        //     return false;
        // }
        // if ($('#file').val() == '') {
        //     layer.alert("请选择需要上传的音频");
        //     return false;
        // }
        // if ($('#sentence_01').val() == '') {
        //     layer.alert("例句不能为空");
        //     return false;
        // }
        // if ($('#translate_01').val() == '') {
        //     layer.alert("翻译不能为空");
        //     return false;
        // }
        layer.load();
        $.ajax({
            url: contextPath+'/manage/wordController/addWord',
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