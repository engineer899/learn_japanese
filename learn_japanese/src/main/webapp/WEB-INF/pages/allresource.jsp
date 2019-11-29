<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<!-- 添加头元素 -->
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">


<!-- 设置绝对位置 -->
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!-- 引用css -->
<link rel="stylesheet" href="${ctp}/static/plugins/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${ctp}/static/plugins/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="${ctp}/static/plugins/layer-v2.4/layer.css">
<%--<link rel="stylesheet" href="${ctp }/static/plugins/zTree/css/metroStyle/metroStyle.css" type="text/css">--%>

<script src="${ctp}/static/layuiadmin/layui/layui.js"></script>						<!-- layui插件 -->
<script src="${ctp}/static/scripts/util/jquery-1.9.1.min.js"></script>				<!-- jquery插件 -->
<script src="${ctp}/static/scripts/util/util.js"></script>
<script src="${ctp}/static/plugins/layer-v2.4/layer.js"></script>

<!-- 内部js -->
<script type="text/javascript">
    //设置项目绝对路径
    var contextPath ="${ctp}";
</script>


