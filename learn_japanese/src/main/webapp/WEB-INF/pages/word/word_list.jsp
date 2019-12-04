<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>单词添加</title>
    <%@ include file="../allresource.jsp"%>
    <script src="${ctp}/static/scripts/myjs/word.js"></script>
</head>
<script>
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form = layui.form;
        // parent.layer.load();

        table.render({
            elem: '#test'
            ,url:'${ctp}/manage/wordController/queryWordInfoJson'
            ,cols: [[
                {type:'numbers',width:'4%', title: '序号'}
                ,{field:'word_title',width:'10%',  title: '单词标题'}
                ,{field:'japanese',width:'10%', title: '日语单词'}
                ,{field:'kana',width:'10%', title: '假名'}
                ,{field:'chinese',width:'10%',  title: '中文'}
                ,{field:'attribute',width:'10%',  title: '词性',templet: '#AUDIT_STATUS'}
                ,{field:'voice_url',width:'15%',  title: '音频url'}
                ,{field:'add_time',width:'15%',  title: '添加时间'}
                ,{title:'操作',width:'15%',templet: '#caozuo', unresize: true}
            ]]
            ,page: true //开启分页
            ,limit:10 //每页最大数
            ,request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'showCount' //每页数据量的参数名，默认：limit
            }
            ,id: 'testReload'
            ,done: function(){//数据渲染完的回调方法
                parent.layer.closeAll();
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var video_name = $('#video_name').val();
                var video_type = $('#video_type').val();
                var state = $('#state').val();
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        video_name: video_name,
                        video_type: video_type,
                        state: state
                    }
                });
            }
        };
        $('.demoTable #query').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
            parent.layer.load();
        });
    });
</script>
<script type="text/html" id="caozuo">
     <a class='layui-btn layui-btn-xs' onclick="queryVideoDetails('{{d.word_id}}')">详情</a>
     <a class='layui-btn layui-btn-normal layui-btn-xs' onclick="updateVideo('{{d.word_id}}')">编辑</a>
     <a class='layui-btn layui-btn-xs layui-btn-danger'  onclick="deleteWordById('{{d.word_id}}')">删除</a>


</script>

<script type="text/html" id="AUDIT_STATUS">
    {{# if(d.video_type == '0'){ }}
    <span style="color: #002828;">零基础</span>
    {{# }else if(d.video_type == '1'){ }}
    <span style="color: #46990c;">N1级别</span>
    {{# }else if(d.video_type == '2'){ }}
    <span style="color: #3838aa;">N2级别</span>
    {{# }else if(d.video_type == '3'){ }}
    <span style="color: #cc0000;">N3级别</span>
    {{# }else{ }}
    <span style="color: #aaa;">待审核</span>
    {{# } }}
</script>

<script type="text/html" id="ENABLE_PLATFORM">
    {{# if(d.state == '0'){ }}               <!--根据结果设置视频状态 -->
    <span style="color: #339933;">启用</span><br>
    {{# }else{ }}
    <span style="color: #cc0000;">停用</span><br>
    {{# } }}
</script>

<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="demoTable" style="text-align: right;margin-bottom: 10px;">
                            <span>&nbsp;&nbsp;视频名称&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入视频名称"  name="video_name" id="video_name" class="layui-input">
                            </div>
                            <span>&nbsp;&nbsp;视频类别&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline" style="text-align: left;width:120px;">
                                <select id="video_type" name="video_type">
                                    <option value="">请选择</option>
<%--                                    <c:forEach var="record" items="${video_type}">--%>
<%--                                        <option value="${record.SERVICE_TYPE_CODE}">${record.SERVICE_TYPE_NAME}</option>--%>
<%--                                    </c:forEach>--%>
                                    <option value="0">零基础</option>
                                    <option value="1">N1</option>
                                    <option value="2">N2</option>
                                    <option value="3">N3</option>
                                </select>
                            </div>
                            <span>&nbsp;&nbsp;视频状态&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline" style="text-align: left;width:120px;">
                                <select id="state" name="state">
                                    <option value="">请选择</option>
                                    <option value="0">启用</option>
                                    <option value="1">停用</option>

                                </select>
                            </div>
                            <a class="layui-btn layui-btn-sm layui-btn-normal" onclick="addWord()" style="float:left;"> <i class="layui-icon layui-icon-add-1"></i>新增</a>
                            <button type="button" class="layui-btn layui-btn-sm " id="query" data-type="reload"><i class="layui-icon layui-icon-search"></i>查询</button>
                        </div>
                    </form>
                    <table class="layui-hide" id="test" ></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
