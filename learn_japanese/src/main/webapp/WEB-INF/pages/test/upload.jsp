<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta charset="UTF-8">
    <title>习题上传</title>
    <style type="text/css">
        .wrapper{
            width: 80%;
            margin: 30px auto;
            padding: 30px ;
            border: 1px solid aquamarine ;
        }

    </style>
</head>

<body>
<div>
    <form action="/learn/course/add" method="POST" enctype="multipart/form-data">
        <div class="wrapper">
            <p>练习等级：</p>
            <select name="test_grade">
                <option value="0">零基础</option>
                <option value="1">N1</option>
                <option value="2">N2</option>
                <option value="3">N3</option>
                <option value="4">N4</option>
            </select>
        </div>
        <div class="wrapper">
            <p>练习类型：</p>
            <select name="test_type">
                <option value="01">单词</option>
                <option value="02">句子</option>
                <option value="03">阅读</option>
            </select>
        </div>

        <div class="wrapper">
            <p>练习名称：</p>
            <input type="text" name="test_name">
        </div>


        <div>
            <input type="submit" value="提交">
        </div>
    </form>

    <!--中间内容-->
    <div class="main" id="fulongcontainer" style="height: 500px;overflow:auto">
        <div class="content-fr">
            <div class="title">
                <div class="fl">创建问卷</div>
                <div class="fr"><a href="javascript:history.go(-1);" class="back-btn">返回</a></div>
            </div>

            <div class="container">
                <div id="first">
                    <div class="form-group">
                        <label for="title">请输入主题</label>
                        <input type="text" class="form-control" id="title" placeholder="此处输入主题">
                    </div>
                    <label for="editor">请输入说明</label>
                    <script id="editor" name="caseDetail" type="text/plain" >

                    </script>

                    <button id="create" class="btn btn-default" style="margin-top: 10px">创建问卷</button>
                </div>
                <div id="second" >
                    <div id="preview"></div>
                    <input type="text" name="qnid" id="id" value="0" style="display: none;"/>
                    <div class="form-group">
                        <input type="text" class="form-control" id="body" placeholder="请输入题目">
                    </div>
                    </br>
                    <input type="radio" value="1" name="type" checked/>单选
                    <input type="radio" value="0" name="type"/>多选
                    <div id="option">
                        <div class="form-group">
                            <input type="text" class="form-control option" placeholder="请输入选项">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control option" placeholder="请输入选项">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control option" placeholder="请输入选项">
                        </div>

                    </div>
                    <button type="button" id="de" class="btn btn-default">减少选项</button>
                    <button type="button" id="add" class="btn btn-default">增加选项</button>
                    <button type="button" id="addother" class="btn btn-default">增加其他选项</button>
                    <button type="button" id="next" class="btn btn-default">保存题目</button>
                    <button type="button" id="finish" class="btn btn-default">关闭</button>
                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>