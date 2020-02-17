<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="utf-8">
    <title>奇迹日语后台管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layuiadmin/style/admin.css" media="all">
    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
    <script src="${pageContext.request.contextPath}/static/scripts/util/jquery-1.9.1.min.js"></script>				<!-- jquery插件 -->

    <script src="${pageContext.request.contextPath}/static/layuiadmin/layui/layui.js"></script>

</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header" style="background-color: #009688;">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible" style="color: #fff;"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3" style="color: #fff;"></i>
                    </a>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>

                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>管理员</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="set/user/info.html">基本资料</a></dd>
                        <dd><a lay-href="set/user/password.html">修改密码</a></dd>
                        <hr>
                        <dd layadmin-event="logout" style="text-align: center;"><a>退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" style="font-size:13px;background-color: #009688;">
                    <span>奇迹日语后台管理系统</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">

                    <li data-name="home" class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;" lay-tips="微课视频" >
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>微课视频</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="nav">
                                <a  lay-href="${pageContext.request.contextPath}/manage/videoController/course_list">视频管理</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="单词测试" >
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>单词测试</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="button">
                                <a  lay-href="${pageContext.request.contextPath}/manage/wordController/course_list">课程管理</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="小知识管理" >
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>小知识管理</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="button">
                                <a  lay-href="${pageContext.request.contextPath}/manage/knowController/type_list">小知识管理</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="交流圈" lay-direction="2">
                            <i class="layui-icon layui-icon-component   "></i>
                            <cite>交流圈</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="button">
                                <a lay-href="data/all_configure.html">动态管理</a>
                            </dd>
                            <dd data-name="nav">
                                <a lay-href="data/all_result.html">评论管理</a>
                            </dd>
                            <dd data-name="tabs">
                                <a lay-href="data/single_configure.html">回复管理</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="日语课堂" lay-direction="2">
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>日语课堂</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="nav">
                                <a lay-href="${pageContext.request.contextPath}/manage/courseController/course_list">课堂管理</a>
                            </dd>
                        </dl>
                    </li>

                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="公告" lay-direction="2">
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>公告</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="nav">
                                <a  lay-href="${pageContext.request.contextPath}/manage/annController/ann_list">管理公告</a>
                            </dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="data/datatrace.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="${pageContext.request.contextPath}/manage/videoController/course_list" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<%--<script src="${pageContext.request.contextPath }/static/plugins/layuiadmin/layui/layui.js"></script>--%>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use('index');
</script>


</body>
</html>


