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
    <input type="text" name="type_id"  value="${pd.type_id}" style="display: none">
    <table class="layui-table" style="width:99%;margin:0px 5px 0px 5px;">
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>标题：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="title"   id="title" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>作者：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <input type="text" name="author"   id="author" lay-verify="required" autocomplete="off" class="layui-input" value=""  maxlength="30">
                </div>
            </td>
        </tr>
        <tr>
            <td class="tdleft"><span style="color:red;">*</span>内容：</td>
            <td colspan="3">
                <textarea id="content"  name="content" lay-verify="content" class="layui-textarea"  style="display: none;"></textarea>
            </td>
        </tr>




    </table>
    <div class="layui-form-item" style="margin-top:15px;text-align: center;">
        <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="save();" type="button"><i class="layui-icon layui-icon-form"></i>保存</button>
        <button class="layui-btn layui-btn-sm" onclick="parent.layer.closeAll();" type="button"><i class="layui-icon layui-icon-close"></i>取消</button>
    </div>
</form>
<script>

    var layedit,index;
    layui.use('layedit', function(){
        layedit = layui.layedit;
        layedit.set({
            uploadImage: {
                url: contextPath+'/manage/knowController/uploadImage' //接口url
                ,type: 'post' //默认post
            }
        });
        index=layedit.build('content'); //建立编辑器
        //自定义验证规则
    });

    function save(){
        layedit.sync(index);
        var formData = new FormData($( "#uploadForm" )[0]);
        console.log(formData);
        // layer.load();
        $.ajax({
            url: contextPath+'/manage/knowController/addKnow',
            type: "post",
            data: formData,
            async: true,
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