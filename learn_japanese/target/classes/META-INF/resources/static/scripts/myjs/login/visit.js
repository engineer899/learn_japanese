//调用新浪IP查询，获得当前IP位置
//document.write("<script src='http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js'></script>");

//跨域，必须手动修改路径名
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var localhostPaht = "";
    if(pathName == '/'){
    	localhostPaht = curWwwPath
    }else{
    	 var pos=curWwwPath.indexOf(pathName);
    	 localhostPaht=curWwwPath.substring(0,pos);
    }
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

//全局变量
var visit_type='1';
var visit_besite="100000";
var visit_id = "";
var visit_js_count = 0;
$(function(){
	//var id='';
	//var type='1';
	
	//var url=window.document.location.pathname;
	//var p1 = url.lastIndexOf("/");
	//var p2 = url.lastIndexOf(".");
	//var str = url.substring(p1+1,p2);
	//if(str.indexOf ("channelCode")!= -1){
	//	type='2';
	//	id = str.substring(str.indexOf ("channelCode") + 11);
	//}else if(str.lastIndexOf ("js") != -1){
	//	type='3';
	//	id=str.substring(str.lastIndexOf ("js") + 2);;
	//}else if(str.length == 32){
	//	type='3';
	//	id=str;
	//}
//	alert(window.location.hash);//1、
//	alert(window.location.host);//1、localhost:8080
//	alert(window.location.hostname);//1、localhost
//	alert(window.location.href);//http://1、localhost:8080/gdll/html/test.html
//	alert(window.location.location);//1、undefine
//	alert(window.location.pathname);//1、/gdll/html/test.html
//	alert(window.location.port);//1、8080
//	alert(window.location.protocol);//1、http
//	alert(window.location.search);//1、
	//2、
	//2、localhost:8080
	//2、localhost
	//2、http://localhost:8080/gdll/show.action?code=visittotal&siteid=100000
	//2、
	//2、/gdll/show.action
	//2、8080
	//2、http
	//2、?code=visittotal&siteid=100000
	/*
	var s1 = "/";//首页
	var s2 = "/100001.shtml";//站点首页
	var s3 = "/100001/10000.shtml";//栏目页
	var s4 = "/100001/2013/1231231232132xxxx.shtml";//新闻页
	var s5 = "http://www.xxxx.gov.cn";
	
	var s6 = "/index";//首页
	var s7 = "/show.action?code=index&siteid=100001";//站点首页
	var s8 = "/show.action?code=index&siteid=100001&channelid=10000";//栏目页
	var s9 = "/show.action?code=index&siteid=100001&channelid=10000&newsid=xxxxxxxx";//新闻页
	*/
	var urlpathname=window.document.location.pathname;
	
	
	if(urlpathname.indexOf(".shtml") > 0 || urlpathname == '/'){//静态化链接
		var p_site = /^\/\d{6}\.shtml&/;//站点首页-表达式
		var p_channel = /^\/\d{6}\/\d{5}\.shtml&/;//栏目页-表达式
		var p_news = /^\/\d{6}\/\d{4}\/\w{32}\.shtml&/;//新闻页-表达式
		//首页:/,/index.shtml,
		if(urlpathname =='/' || urlpathname == '/index.shtml'){//网站首页，总站
			
		}else if(p_site.test(urlpathname)){//站点首页
			visit_type='1';
			visit_besite=urlpathname.substring(1,6);
			
		}else if(p_channel.test(urlpathname)){//栏目页
			visit_type='2';
			visit_besite=urlpathname.substring(1,6);
			visit_id=urlpathname.substring(9,13);
		}else if(p_news.test(urlpathname)){//新闻页
			visit_type='2';
			visit_besite=urlpathname.substring(1,6);
			visit_id=urlpathname.substring(14,46);
		}
	}else{//动态链接
		//站点ID：siteid,栏目ID：channelid,新闻ID：newsid 必须符合规则（否则适当调整代码）
		var urlsearch = window.document.location.search;
		//无参数默认为首页
		if(urlsearch.indexOf("?") < 0 || urlsearch == ""){
			
		}else{  
				var strs = urlsearch.substring(1).split("&");   
				for(var i = 0; i < strs.length;i++){   
					if(strs[i].split("=")[0]=='siteid'){//站点首页
						visit_type="1";
						visit_besite=strs[i].split("=")[1];
					    
					};
					if(strs[i].split("=")[0]=='channelid'){//栏目页
						visit_type="2";
						visit_id=strs[i].split("=")[1];
					    
					}
					if(strs[i].split("=")[0]=='newsid'){//新闻页
						visit_type="3";
						visit_id=strs[i].split("=")[1];
					    break;//检测为新闻页时 退出循环
					}
				}   
			
		}
	}
	
	var referee = document.referrer;//来源分析
	if(referee == "" ){
		referee="directaccess";
	}else if(referee.indexOf("www.baidu.com") > 0 ){
		referee="baidu";
	}else if(referee.indexOf("www.google.com") > 0){
		referee="google";
	}else if(referee.length > 127){
		referee="other";
	}
	$.ajax({
		type : "get",
		url : "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js",
		dataType:'script',
		success:function(data){
			var s = {"log.siteId":visit_besite,
					 "log.visitorId":getCookie('visitorId'),
					 "log.accessId" : getCookie('visitAccess') ,
					 "log.type" : visit_type , 
					 "log.webpageId" : visit_id , 
					 "log.source" : referee , 
					 "log.country" : encodeURIComponent(remote_ip_info["country"]) , 
				 	 "log.province" : encodeURIComponent(remote_ip_info["province"]),
					 "log.city" : encodeURIComponent(remote_ip_info["city"]),
					 "log.os" : detectOS() ,
					 "log.browser" : detectBrowser() , 
					 "log.equipment" : detectEquipment()                              
					};
			if(visit_js_count == 0){
				visit_js_count++;
				$.ajax({
					type : "get",
					url : getRootPath() + "/counter/counter!setCounter.action",
					dataType:'jsonp',
					data:s,
					jsonp: "callbackparam_setdate", //服务端用于接收callback调用的function名的参数
					jsonpCallback: "success_jsonpCallback_setdate", //callback的function名称
					success : function(json){
						if(json.visitorId != ""){
							addCookie("visitorId",json.visitorId);
						}
						if(json.accessId != ""){
							addCookieNoExpires("visitAccess",json.accessId);
						}
					}
					
				});
			}
			
		}
		
	});
	
	
});

function getCookie(name){ 
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name){
			return arr[1]; 
		}
	} 
	return ""; 
}


function addCookie(name,value){ 
	var cookieString=name+"="+escape(value); 

	//过期时间一年
	var Days = 365;   
	var exp = new Date();    
	exp.setTime(exp.getTime() + Days*24*60*60*1000);   
	cookieString=cookieString+"; expires="+exp.toGMTString()+";path=/";
	document.cookie=cookieString; 
}
function addCookieNoExpires(name,value){ 
	var cookieString=name+"="+escape(value) + ";path=/";  
	document.cookie=cookieString; 
} 
//获取访问数  跨域
function visitNum(siteId,webpageId,domId){
	// 页面中获取次数：<div id="s"><script>visitNum('100000','','s')</script></div>
	$.ajax({
		type : "get",
		async:true,
		url : getRootPath() + "/counter/counter!getCounter.action",
		dataType:'jsonp',
		data:{"siteId":siteId,"webpageId":webpageId,"domId":domId},
		jsonp: "callbackparam" + domId, //服务端用于接收callback调用的function名的参数
		jsonpCallback: "success_jsonpCallback" + domId, //callback的function名称
		success : function(json){
			if(json.num != ''){
				$("#"+domId).html(json.num);
			}
			
		}
		
	});
}
//获取全站访问量
function visitAll(domId){
	visitNum("","",domId);	
}
//获取当前站点访问量
function visitSite(siteid,domId){
	visitNum(siteid,"",domId);
}
//获取新闻访问量
function visitNews(siteid,newsid,domId){
	visitNum(siteid,newsid,domId);
	
}

//非跨域 state:1 数字，2：图片
function visitNumNoDomain(type,siteId,webpageId,domId,state,picarray){
	$.ajax({
		type : "get",
		url : getRootPath() + "/counter/counter!getCounter.action",
		dataType:'jsonp',
		data:{"siteId":siteId,"webpageId":webpageId,"domId":domId},
		jsonp: "callbackparam" + domId, //服务端用于接收callback调用的function名的参数
		jsonpCallback: "success_jsonpCallback" + domId, //callback的function名称
		success : function(json){
			if(json.num != ''){
				if(state == '1'){
					var arrnum = json.num.split("");
					var str = "";
					for(var el in arrnum){
						str += "<img src='" + picarray[arrnum[el]] + "'/>";
					}
					$("#"+domId).html(str);
				}else{
					$("#"+domId).html(json.num);
				}
				
				
			}
		}
		
	});
}



//获取客户端操作系统
function detectOS() {  
    var sUserAgent = navigator.userAgent;
    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");  
    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");  
    if (isMac) return "Mac";  
    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;  
    if (isUnix) return "Unix";  
    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);  
    if (isLinux) return "Linux";  
    if (isWin) {  
        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;  
        if (isWin2K) return "Win2000";  
        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;  
        if (isWinXP) return "WinXP";  
        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;  
        if (isWin2003) return "Win2003";  
        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;  
        if (isWinVista) return "WinVista";  
        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;  
        if (isWin7) return "Win7";  
    }  
    return "other";  
} 

//获取客户端当前浏览器的类型
function detectBrowser(){
	
	var navObj = navigator.userAgent.toLowerCase();
	if (nav = navObj.match(new RegExp("msie ([\\d.]+)"))) {
	    if (parseInt(nav[1]) == 6) {return "IE6";}
	    else if (parseInt(nav[1]) == 7) {return "IE7";}
	    else if (parseInt(nav[1]) == 8) {return "IE8";}
	    else if (parseInt(nav[1]) == 9) {return "IE9";}
	    else if (parseInt(nav[1]) == 10) {return "IE10";}
	    else{ return "IE"}
	}
	else if (nav = navObj.match(new RegExp("firefox\/([\\d.]+)"))) {
	    return "Firefox";
	}
	else if (nav = navObj.match(new RegExp("chrome\/([\\d.]+)"))) {
	    return "Chrome";
	}
	else if (nav = navObj.match(new RegExp("opera.([\\d.]+)"))) {
	    return "Opera";
	}
	else if (nav = navObj.match(new RegExp("version\/([\\d.]+).*safari"))) {
	    return "Safari";
	}else{
		return "other"
	}
}

//判断访问网站的设备
function detectEquipment() { 
	var sUserAgent = navigator.userAgent.toLowerCase(); 
	if(sUserAgent.match(/ipad/i) == "ipad"){
		return "ipad";
	}else if(sUserAgent.match(/iphone os/i) == "iphone os"){
		return "iphone";
	}else if(sUserAgent.match(/midp/i) == "midp"){
		return "midp";
	}else if(sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4"){
		return "uc7";
	}else if(sUserAgent.match(/ucweb/i) == "ucweb"){
		return "uc";
	}else if(sUserAgent.match(/android/i) == "android"){
		return "android";
	}else if(sUserAgent.match(/windows ce/i) == "windows ce"){
		return "windows ce";
	}else if(sUserAgent.match(/windows mobile/i) == "windows mobile"){
		return "windows mobile";
	}else{
		return "pc";
	}
} 