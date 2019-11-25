<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true"%>
<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String baseurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>问卷</title>
    <base target="_blank">
    <link href="<%=path%>/static/styles/mycss/omp/xj-index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/static/scripts/myjs/login/artDialog.js"></script>
    <script type="text/javascript" src="<%=path%>/static/scripts/myjs/login/iframeTools.js"></script>
    <link rel="stylesheet" href="<%=path%>/static/plugins/bootstrap/css/bootstrap.min.css">
    <script src="<%=path%>/static/scripts/util/jquery.min.js"></script>
    <script src="<%=path%>/static/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var questionnaire_id;
        window.onbeforeunload = function() {
            return "确认离开当前页面吗？未保存的数据将会丢失";
        }
        var QU_ORDERBYID = 1;
        $(document).ready(function () {
            //创建
            $("#create").click(function () {
                $.ajax({
                    url: "<%=path%>/test/addTest",
                    data: {"test_name": $("#test_name").val(),"test_type":$("#test_type").val(),"test_grade":$("#test_grade").val()},
                    type: "POST",
                    success: function (signal) {
                        if (signal != "fail") {
                            questionnaire_id = signal;
                            QU_ORDERBYID = 1
                            $("#second").show();
                            $("#tool").show();
                            $("#first").hide();
                            $("#id").val(signal);
                            $("#id").css("value", signal);
                            $("#preview").append('<a href="<%=path%>/questionnaire/show/'+signal+'">预览当前问卷</a>');
                        } else {
                            alert("登录失效，请重新登录");
                        }
                    }
                });
            });

            //加
            $("#add").click(function () {
                $("#option").append('<div class="form-group"><input type="text" class="form-control option" placeholder="请输入选项"></div>');
            });
            $("#addother").click(function () {
                $("#second").append('    <div class="item">\n' +
                    '                 <div id="preview"></div>\n' +
                    '                 <input type="text" name="qnid" id="id" value="0" style="display: none;"/>\n' +
                    '                 <div class="form-group">\n' +
                    '                    <label for="content">题目：</label>\n' +
                    '                    <input type="text" class="form-control" id="content" placeholder="请输入题目">\n' +
                    '                 </div>\n' +
                    '                <%--<input type="radio" value="1" name="type" checked/>单选--%>\n' +
                    '                <%--<input type="radio" value="0" name="type"/>多选--%>\n' +
                    '                 <div id="option">\n' +
                    '                    <div class="form-group">\n' +
                    '                        <text>A:</text><input type="text" class="form-control option" placeholder="请输入选项内容">\n' +
                    '                    </div>\n' +
                    '                    <div class="form-group">\n' +
                    '                        <text>B:</text><input type="text" class="form-control option" placeholder="请输入选项内容">\n' +
                    '                    </div>\n' +
                    '                    <div class="form-group">\n' +
                    '                        <text>C:</text><input type="text" class="form-control option" placeholder="请输入选项内容">\n' +
                    '                    </div>\n' +
                    '                    <div class="form-group">\n' +
                    '                        <text>D:</text><input type="text" class="form-control option" placeholder="请输入选项内容">\n' +
                    '                    </div>\n' +
                    '                 </div>\n' +
                    '                    <button type="button"  class="btn btn-default de">删除题目</button>\n' +
                    '\n' +
                    '                </div>');
            });


            $("#finish").click(function () {
                window.location.href='<%=path%>/show.action?code=questionnaire_list';
            });

            $("#next").click(function () {
                if($(".option").val()==""||$("#body").val()==""){
                    alert("请填写完整！");
                    return false;
                }
                var arr = new Array();
                var i = 0;
                var type;
                var isOther= 0;
                $(".option").each(function () {
                    arr[i] = $(this).val();
                    i++;
                    if ($(this).val() == '其他'){
                        isOther ++;
                    }
                    if (isOther>=2){
                        return true;
                    }
                });
                if (isOther>=2){
                    alert("其他选项只能有一个");
                    return false;
                }
                if ($("input[name='type']:checked").val() == "1") {
                    type = 1;
                } else {
                    type = 2;
                }
                $.ajax({
                    url: "<%=path%>/questionnaire/addQuestion",
                    type: "POST",
                    traditional: true,
                    data: {"QNID": $("#id").val(), "QU_TITELE": $("#body").val(), "QU_TYPE": type, "options": arr,"QU_ORDERBYID":QU_ORDERBYID},
                    success: function (data) {
                        if (data == "成功") {
                            alert("保存成功！");
                            $("#option .option").each(function () {
                                if ( $(this).val() == '其他'){
                                    $(this).remove();
                                }

                            });
                            $(".option").val("");
                            $("#body").val("");

                            QU_ORDERBYID++
                        } else {
                            alert("出错！");
                        }
                    }
                });
            });

            $(".de").click(function() {
                    // 使用siblings获取被点击元素之外的同级元素，然后使用remove()删除
                    $(this).parent().remove();
            });

        });






    </script>




</head>

<body>
<!--中间内容-->
<div class="main" id="fulongcontainer" style="height: 500px;overflow:auto">
    <div class="content-fr">
        <div class="title">
            <div class="fl">创建习题</div>
            <div class="fr"><a href="javascript:history.go(-1);" class="back-btn">返回</a></div>
        </div>

        <div class="container" >
            <div id="first">
                <div class="form-group">
                    <label for="test_grade">练习等级：</label>
                    <select id="test_grade"  class="form-control">
                        <option value="0">零基础</option>
                        <option value="1">N1</option>
                        <option value="2">N2</option>
                        <option value="3">N3</option>
                        <option value="4">N4</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="test_type">练习等级：</label>
                    <select id="test_type" class="form-control">
                        <option value="01">单词</option>
                        <option value="02">句子</option>
                        <option value="03">阅读</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="test_name">练习名称：</label>
                    <input type="text" class="form-control" id="test_name" placeholder="此处输入名称">
                </div>

                <button id="create" class="btn btn-default" style="margin-top: 10px">创建问卷</button>
            </div>
            <div id="second" style="display:none;">
                <div class="item">
                 <div id="preview"></div>
                 <input type="text" name="qnid" id="id" value="0" style="display: none;"/>
                 <div class="form-group">
                    <label for="content">题目：</label>
                    <input type="text" class="form-control" id="content" placeholder="请输入题目">
                 </div>
                <%--<input type="radio" value="1" name="type" checked/>单选--%>
                <%--<input type="radio" value="0" name="type"/>多选--%>
                 <div id="option">
                    <div class="form-group">
                        <text>A:</text><input type="text" class="form-control option" placeholder="请输入选项内容">
                    </div>
                    <div class="form-group">
                        <text>B:</text><input type="text" class="form-control option" placeholder="请输入选项内容">
                    </div>
                    <div class="form-group">
                        <text>C:</text><input type="text" class="form-control option" placeholder="请输入选项内容">
                    </div>
                    <div class="form-group">
                        <text>D:</text><input type="text" class="form-control option" placeholder="请输入选项内容">
                    </div>
                 </div>
                    <button type="button"  class="btn btn-default de">删除题目</button>

                </div>

            </div>
            <div id="tool" style="display:none;">
                <%--<button type="button" id="add" class="btn btn-default">增加选项</button>--%>
                <button type="button" id="addother" class="btn btn-default">增加题目</button>
                <button type="button" id="next" class="btn btn-default">保存题目</button>
                <button type="button" id="finish" class="btn btn-default">关闭</button>
            </div>
        </div>
    </div>

</div>

</body>


</html>