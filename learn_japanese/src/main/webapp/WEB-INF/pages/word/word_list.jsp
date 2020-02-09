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
            ,url:'${ctp}/manage/wordController/queryWordInfoJson?chapter_id=${pd.chapter_id}'
            ,cols: [[
                {type:'numbers',width:'4%', title: '序号'}
                ,{field:'japanese',width:'10%', title: '日语'}
                ,{field:'kana',width:'10%', title: '假名'}
                ,{field:'tone',width:'15%',  title: '音调'}
                ,{field:'chinese',width:'10%',  title: '中文'}
                ,{field:'attribute',width:'10%',  title: '词性'}
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
            // ,done: function(){//数据渲染完的回调方法
            //     parent.layer.closeAll();
            // }
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


<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="demoTable" style="text-align: right;margin-bottom: 10px;">
                            <span>&nbsp;&nbsp;中文&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入中文"  name="chinese" id="chinese" class="layui-input">
                            </div>
                            <span>&nbsp;&nbsp;日语&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入日语"  name="japanese" id="japanese" class="layui-input">
                            </div>
                            <span>&nbsp;&nbsp;假名&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入假名"  name="kana" id="kana" class="layui-input">
                            </div>
                            <span>&nbsp;&nbsp;词性&nbsp;&nbsp;:</span>
                            <div class="layui-input-inline">
                                <input  placeholder="请输入词性"  name="attribute" id="attribute" class="layui-input">
                            </div>

                            <a class="layui-btn layui-btn-sm layui-btn-normal" onclick="addWord('${pd.chapter_id}')" style="float:left;"> <i class="layui-icon layui-icon-add-1"></i>新增</a>
                            <button type="button" class="layui-btn layui-btn-sm " id="query" data-type="reload"><i class="layui-icon layui-icon-search"></i>查询</button>
                        </div>
                    </form>

                    <form id="upform" enctype='multipart/form-data' style="display:none;">
                        <div class="form-group">
                            <label for="upteainput">上传文件</label>
                            <input id="upteainput" name="file" type="file" class="form-control-file">
                        </div>
                    </form>
                    <button id="uptea" type="button" class="layui-btn layuiadmin-btn-list" >上传excel</button>


                    <table class="layui-hide" id="test" ></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>

    $("#uptea").click(function () {
        $("#upteainput").click();
    });
    //下面是ajax上传文件的代码。
    $("#upteainput").change(function () {//如果上传文件的input内容发生了变化
        $val = $("#upteainput").val();
        if ($val != '') {//要上传的文件名不为空
            $data = new FormData($("#upform")[0]);//创建一个formdata对象
            $.ajax({
                url: "${ctp}/manage/wordController/excelImport?chapter_id=${pd.chapter_id}",
                type: "POST",
                data: $data,
                processData: false,
                contentType: false,
                dataType: "text",
                error: function () {
                    alert('请求错误！');
                },
                success: function (data) {//如果调用成功
                    console.log(data);
                    if (data != "Successful import") {
                        window.location.reload();
                        if (data == 'File format error') {
                            alert("文件格式错误,请上传xls或xlsx文件！");
                        } else if(data == 'excel is null'){
                            alert("文件内容为空！");
                        }else if(data == 'Import failed'){
                            alert("系统错误，导入失败！");
                        }else if(data == "model error"){
                            alert("请先下载导入模板,用导入模板导入数据！");
                        }else if(data== 'error msg'){
                            alert("部分导入，点击返回导入失败数据！");
                            window.location.href="${pageContext.request.contextPath }/licenceController/returnExcel";
                        }
                    }else{
                        alert("数据导入成功!");
                    }

                }
            })
        }
    });
</script>
