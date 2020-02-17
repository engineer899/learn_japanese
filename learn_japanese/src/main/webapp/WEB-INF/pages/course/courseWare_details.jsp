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
                    <input type="text"  readonly="readonly"  name="video_name" id="video_name" lay-verify="required" autocomplete="off" class="layui-input" value="${result.video_name}"  maxlength="30">
                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>视频类型：</td>
            <td style="width:30%;">
                <input type="text"  readonly="readonly"  name="series" id="series"
                       lay-verify="required" autocomplete="off" class="layui-input" value="${result.series}"  maxlength="30">
<%--                <select id="video_type" name="video_type" lay-verify="required">--%>

<%--                    <c:if test="${result.video_type == '0'}"> <option  value="0">零基础</option></c:if>--%>
<%--                    <c:if test="${result.video_type == '1'}"> <option  value="0">N1</option></c:if>--%>
<%--                    <c:if test="${result.video_type == '2'}"> <option  value="0">N2</option></c:if>--%>
<%--                    <c:if test="${result.video_type == '3'}"> <option  value="0">N3</option></c:if>--%>
<%--                </select>--%>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>视频课时：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="text"  readonly="readonly"  name="video_num" id="video_num"
                           lay-verify="required" autocomplete="off" class="layui-input" value="第${result.video_num}课时"  maxlength="30">
<%--                        <c:forEach  items="${result.video_num}"  var="i"  begin="1">--%>
<%--                        <c:if test="${result.video_num== i}">i课时</c:if>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${result.video_num == '2'}"><option  value="1">第二课时</option></c:if>--%>
<%--                        <c:if test="${result.video_num == '3'}"><option  value="1">第三课时</option></c:if>--%>
<%--                        <c:if test="${result.video_num == '4'}"><option  value="1">第四课时</option></c:if>--%>

                </div>
            </td>
            <td class="tdleft"><span style="color:red;">*</span>上传视频：</td>
            <td>
                <div class="layui-input-block" style="margin-left:0!important;">
                    <input type="file" id="file" name="file" value="${result.video_url}" />
                </div>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>添加时间：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="addtime"></textarea>
                    <input type="text"  readonly="readonly"  name="video_name" id="add_time"  autocomplete="off" class="layui-input" value="${result.add_time}"  maxlength="30">
                </div>
            </td>

            <td class="tdleft"><span style="color:red;">*</span>观看人数：</td>
            <td style="width:30%;">
                <div class='layui-input-block' style='margin-left:0!important;'>
                    <!-- 内容 -->
                    <textarea style="display:none"   id="browsenum"></textarea>
                    <input type="text"  readonly="readonly"  name="video_name" id="browse_num"  autocomplete="off" class="layui-input" value="${result.browse_num}"  maxlength="30">
                </div>
            </td>
        </tr>

        <tr>
            <td class="tdleft"><span style="color:red;">*</span>课程知识点：</td>
            <td colspan="3">
                <textarea id="video_knowledge" name="video_knowledge" readonly="readonly" style="width:100%;height:300px; ">${result.video_knowledge}</textarea>
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