<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>微课视频</title>
    <%@ include file="../allresource.jsp"%>
    <script src="${ctp}/static/scripts/myjs/video.js"></script>
</head>
<script>
    layui.use(['table','form'], function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${ctp}/manage/videoController/queryVideoInfoListPage?course_id=${pd.course_id}'
            ,cols: [[
                {type:'numbers',width:'5%', title: '序号'}
                ,{field:'video_url',width:'20%',  title: '视频URL'}
                ,{field:'video_name',width:'15%', title: '视频名称'}
                ,{field:'add_time',width:'15%', title: '添加时间'}
                ,{field:'browse_num',width:'9%',  title: '观看次数'}
                ,{field:'video_num',width:'9%',  title: '视频课时'}
                ,{field:'state',width:'9%', title: '视频状态',templet: '#ENABLE_PLATFORM'}
                ,{title:'操作',width:'18%',templet: '#caozuo', unresize: true}
            ]]
            ,page: true //开启分页
            ,limit:10 //每页最大数
            ,request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'showCount' //每页数据量的参数名，默认：limit
            }
            ,id: 'testReload'
            // ,done: function(){//数据渲染完的回调方法
            //     // parent.layer.closeAll();
            // }
        });
        var $ = layui.$, active = {
            reload: function(){
                var video_name = $('#video_name').val();

                var state = $('#state').val();
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        video_name: video_name,

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
    <a class='layui-btn layui-btn-xs' onclick="queryVideoDetails('{{d.id }}')">详情</a>
    <a class='layui-btn layui-btn-normal layui-btn-xs' onclick="updateVideo('{{d.id }}')">编辑</a>
    {{# if(d.state == '0'){ }}
    <a class='layui-btn layui-btn-xs layui-btn-danger'  onclick="enableORstopVideo('{{d.id}}','1')">停用</a>
    {{# }else{ }}
    <a class='layui-btn layui-btn-xs' onclick="enableORstopVideo('{{d.id}}','0')">启用</a>
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

                            <span>&nbsp;&nbsp;视频状态&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline" style="text-align: left;width:120px;">
                                <select id="state" name="state">
                                    <option value="">请选择</option>
                                    <option value="0">启用</option>
                                    <option value="1">停用</option>

                                </select>
                            </div>
                            <a class="layui-btn layui-btn-sm layui-btn-normal" onclick="addVideo('${pd.course_id}')" style="float:left;"> <i class="layui-icon layui-icon-add-1"></i>新增</a>
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
