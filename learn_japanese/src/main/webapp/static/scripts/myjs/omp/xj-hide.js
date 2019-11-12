function doSearch(){
	var form = document.getElementById("form");
  	form.submit();
}

function goPage(newPage, pageCount) {
	if (isIntNum(newPage)) {
		//top.art.dialog({time: 2,icon: 'warning',lock:true,content: "数字格式不正确！"});
		alert("数字格式不正确！");
		return;
	}
	if (newPage > pageCount) {
		//top.art.dialog({time: 2,icon: 'warning',lock:true,content: "页码太大！不能超过"+pageCount});
		alert("页码太大！不能超过"+pageCount);
		return;
	}
	var pageNum = document.getElementById("pageNo");
	pageNum.value = newPage;
	doSearch();
}
function skipPage(pageCount){
	var newPage = $("#pageint").val();
	goPage(newPage,pageCount);
}

function isIntNum(value) {
	if (/^[1-9]{1}[0-9]{0,8}$/.test(value)) {
		return false;
	} else {
		return true;
	}
}

//判断字符长度
function len(s) {
	var l = 0;
	var a = (s+"").split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}

function get_host_url(){
	var url = "http://xj.fulong.cn";
	return url;
}

//在线客服地址
function get_ocs_url(){
	var url = "http://xj.fulong.cn";
	return url;
}

//cas
function get_cas_url(){
	var url ="/zhirong.cas";
	return url;
}
//omp
function get_omp_url(){
	var url ="/zhirong.cas";
	return url;
}
//www
function get_www_url(){
	var url ="/zhirong.www";
	return url;
}
//kms
function get_kms_url(){
	var url ="/kms";
	return url;
}

//cas
function get_cas_url(){
	var url ="/zhirong.cas";
	return url;
}