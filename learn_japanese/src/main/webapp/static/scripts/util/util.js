//去掉标签，截取前200字符串，多处的省略号
function getSimpleText(html) {
	var reg = new RegExp("<.+?>", "g"); 	//匹配html标签的正则表达式，"g"是搜索匹配多个符合的内容
	var text = html.replace(reg, ''); 		//执行替换成空字符
	
	return text.substring(0,200) + "......";
}

function getSimpleTextT(html) {
	var reg = new RegExp("<.+?>", "g"); 	//匹配html标签的正则表达式，"g"是搜索匹配多个符合的内容
	var text = html.replace(reg, ''); 		//执行替换成空字符
	
	return text.substring(0,90) + "......";
}

//当前时间
function currentTime(){
	var d = new Date(),
	str = '';
    str += d.getFullYear()+'-';
    str += d.getMonth() + 1+'-';
    str += d.getDate()+' ';
    str += d.getHours()+':'; 
    str += d.getMinutes()+':';  
    str += d.getSeconds()+''; 
    
    return str; 
}

//根据参数名称得到地址栏上的参数 
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(decodeURI(r[2])); return null; //返回参数值
}


//截取前6位，然后后面的变成*代替
function encryptionStr(str, num){
   	var pledblicno = str.substr(0, num);
   	
   	for(var i=0; i<str.length-num; i++){
	   	pledblicno += "*";
  	}
   	
   	return pledblicno;
}

//数组去重复
function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    
    return result;
}

//判断值是否为空
function isEmpty(value) {
    if (value == null || value == "" || value == "undefined" || value == undefined || value == "null" || value == "(null)" || value == 'NULL' || typeof (value) == 'undefined') {
        return true;
    } else {
        value = value + "";
        value = value.replace(/\s/g, "");
        if (value == "") {
            return true;
        }
        return false;
    }
}

//判断值是否不为空
function isNotEmpty(value) {
    if (value == null || value == "" || value == "undefined" || value == undefined || value == "null" || value == "(null)" || value == 'NULL' || typeof (value) == 'undefined') {
        return false;
    } else {
        value = value + "";
        value = value.replace(/\s/g, "");
        if (value == "") {
            return false;
        }
        return true;
    }
}