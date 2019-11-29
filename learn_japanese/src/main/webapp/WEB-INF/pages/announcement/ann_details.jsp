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
            <td class="tdleft"><span style="color:red;"style="width:90%;">*</span>公告标题：</td>
            <td style="width:100%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="divdata"></textarea>
                    <input type="text"  readonly="readonly"  name="ann_title" id="ann_title" lay-verify="required" autocomplete="off" class="layui-input" value="${result.ann_title}"  maxlength="30">
                </div>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>添加时间：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="addtime"></textarea>
                    <input type="text"  readonly="readonly"  name="ann_title" id="add_time"  autocomplete="off" class="layui-input" value="${result.add_time}"  maxlength="30">
                </div>
            </td>


        <tr>
            <td class="tdleft"><span style="color:red;">*</span>公告内容：</td>
            <td colspan="3">
                <textarea id="ann_content" name="ann_content" readonly="readonly" style="width:100%;height:249px; ">${result.ann_content}</textarea>
            </td>
        </tr>
    </table>
    <div class="layui-form-item" style="margin-top:15px;text-align: center;">
        <button class="layui-btn layui-btn-sm" onclick="parent.layer.closeAll();" type="button"><i class="layui-icon layui-icon-close"></i>返回</button>
    </div>
</form>
<script>

    layui.use(['form','laydate'], function(){
    });
</script>


</body>
</html>