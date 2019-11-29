<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="${pageContext.request.contextPath }/static/scripts/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/plugins/layuiadmin/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath }/static/plugins/layuiadmin/layui/lay/modules/layer.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/plugins/layuiadmin/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/plugins/layuiadmin/layui/css/modules/layer/default/layer.css">
    <title>登录</title>
    <style type="text/css">
        .main_box{
            height: 100vh;
            background: url(${pageContext.request.contextPath }/static/images/back.png) no-repeat;
            background-size: 100%;
        }
        .login_box {
            width: 28%;
            height: 48%;
            background: #fff;
            border-radius: 4px;
            box-shadow: 0 5px 16px rgba(147,92,0,0.35);
            position: relative;
            top: 28%;
            left: 36%;
        }
        .login_title {
            font-size: 2.3vh;
            color: #5B91D7;
            font-weight: bold;
            padding: 10% 0 6% 0;
            width: 75%;
            margin: auto;
        }
        .login_list {
            display: block;
            width: 75%;
            margin: auto;
        }
        .login_list input{padding-left: 35px;}
        .login_list li:before {
            content: '';
            width: 36px;
            height: 36px;
            display: block;
            z-index: 10;
            position: absolute;
            background: url(${pageContext.request.contextPath }/static/images/yh.png) no-repeat 8px 8px;
            background-size: 53%;
        }
        /*.user input{
            background: url(static/portal/img/yh.png) no-repeat 0px 0px;
        }*/
        .pwd{
            padding-top: 10%;
        }
        .login_reg{
            height: 80px;
            line-height: 80px;
            width: 75%;
            margin: auto;
            text-align: right;
        }
        .login_btns {
            text-align: center;
            line-height: 40px;
        }
        input.btn_login {
            outline: none;
            width: 75%;
            height: 38px;
            font: 16px/42px 'Microsoft YaHei';
            font-weight: 400;
            overflow: hidden;
            border: none;
            border-radius: 2px;
            cursor: pointer;
            color: #fff;
            background: #5B91D7;
        }
    </style>
</head>

<body>
<div class="main_box">
    <div class="login_box">
        <h1 class="login_title">用户登录</h1>
        <ul class="login_list">
            <li class="user"><input type="text" class="layui-input" id="logName" name="username" placeholder="用户名\邮箱\手机"></li>
            <li class="pwd"><input type="password" class="layui-input" id="unEncryptPwd" name="password" placeholder="密码"></li>
        </ul>
        <p class="login_reg">
            <a href="${pageContext.request.contextPath }/reg/regPerson.action" class="fR" title="立即注册" style="color:#5B91D7;">立即注册 》</a>
        </p>
        <p class="login_btns">
            <input type="button" class="btn_login" value="登 录" onclick="login();">
        </p>
    </div>
</div>
<script type="text/javascript">
    function login(){
        var str="";
        var username=$("#logName").val();
        var password=$("#unEncryptPwd").val();
        if(username=="" || username==null ||username ==undefined){
            layer.confirm('账号不能为空,重新输入？', {
                btn: ['是','否'] //按钮
            }, function(){
                location.reload();
            });
            return false;
        }else if(password=="" || password==null ||password ==undefined){
            // str="密码不能为空！";
            layer.confirm('密码不能为空,重新输入？', {
                btn: ['是','否'] //按钮
            }, function(){
                location.reload();
            });
            $("#mas").html(str);
            return false;
        }else{
            var re = /^1\d{10}$/;
            var ree = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            var phone = "";
            var email = "";
            if(re.test(username)){
                phone = $("#logName").val();
                username = "";
            }else if(ree.test(username)){
                email = $("#logName").val();
                username = "";
            }
            $.ajax({
                url: '${pageContext.request.contextPath }/doLogin2'
                , type: 'post'
                , data: {
                    'mobile': phone,
                    'email': email,
                    'username': username,
                    'password': password
                }
                , dataType: "text"
                , success: function (res) {

                    if (res == "0") {
                        //str = "该用户不存在！"
                        layer.confirm('该用户不存在,重新输入', {
                            btn: ['是','否'] //按钮
                        }, function(){
                            location.reload();
                        });
                    } else if (res == "1") {
                        //str = "用户名或密码或邮箱有误！"
                        layer.confirm('用户名或密码或邮箱有误!', {
                            btn: ['是','否'] //按钮
                        }, function(){
                            location.reload();
                        });
                    }
                    if (res == "2") {
                        window.location.href = "${pageContext.request.contextPath}/index"
                    }
                    $("#mas").html(str);
                }

            });

        }
    }
</script>
</body>
</html>