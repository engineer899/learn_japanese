<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>微课视频</title>
    <%@ include file="../allresource.jsp"%>
    <script src="${ctp}/static/scripts/myjs/ann.js"></script>
</head>
<script>
    layui.use(['table','form'], function(){
        var table = layui.table;
        var form = layui.form;
        // parent.layer.load();

        table.render({
            elem: '#test'
            ,url:'${ctp}/manage/manageAnnController/queryAnnInfoJson'
            ,cols: [[
                {type:'numbers',width:'5%', title: '序号'}
                ,{field:'ann_title',width:'55%', title: '公告标题'}
                ,{field:'add_time',width:'20%', title: '添加时间'}
                ,{title:'操作',width:'20%',templet: '#caozuo', unresize: true}
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
                var ann_title = $('#ann_title').val();
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        ann_title: ann_title,
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
    <a class='layui-btn layui-btn-xs' onclick="queryAnnDetails('{{d.id }}')">详情</a>

    <a class='layui-btn layui-btn-normal layui-btn-xs' onclick="deleteAnn('{{d.id}}')">删除</a>
</script>



<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="demoTable" style="text-align: right;margin-bottom: 10px;">
                            <span>&nbsp;&nbsp;公告标题&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入公告标题"  name="ann_title" id="ann_title" class="layui-input">
                            </div>


                            <a class="layui-btn layui-btn-sm layui-btn-normal" onclick="addAnn()" style="float:left;"> <i class="layui-icon layui-icon-add-1"></i>新增</a>
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
