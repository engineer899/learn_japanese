<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <meta charset="UTF-8">
    <title>课程视频上传</title>
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
    <form action="/learn/video/add" method="POST" enctype="multipart/form-data">
        <div class="wrapper">
            <p>上传课程的类别：</p>
            <select name="video_type">
                <option value="0">零基础~N1</option>
                <option value="1">N1</option>
                <option value="2">N2</option>
                <option value="3">N3</option>
                <option value="4">N4</option>
            </select>
        </div>
        <div class="wrapper">
            <p>第几课时：</p>
            <select name="video_num">
                <option value="1">第1课</option>
                <option value="2">第2课</option>
                <option value="2">第3课</option>
                <option value="4">第4课</option>
                <option value="5">第5课</option>
                <option value="6">第6课</option>
                <option value="7">第7课</option>
                <option value="8">第8课</option>
            </select>
        </div>
        <div class="wrapper">
            <p>课程名：</p>
            <input type="text" name="video_name">
        </div>
        <div class="wrapper">
            <p>选择文件:</p>
            <input type="file" name="file">
        </div>
        <div>
            <input type="submit" value="tijiao">
        </div>
    </form>
</div>


</body>
</html>