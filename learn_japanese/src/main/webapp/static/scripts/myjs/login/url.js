//omp
function get_omp_url(){
	var url ="/zhirong.cas";
	return url;
}

function get_cas_url(){
	var url ="/zhirong.cas";
	return url;
}
//omp
function get_uc_url(){
	var url ="/uc";
	return url;
}
//www
function get_www_url(){
	var url ="/zhirong.www";
	return url;
}
//log
function get_log_url(){
	var url ="/log";
	return url;
}


function get_host_url(p){
	var url ="http://localhost:"+window.location.port;
	//var url ="http://192.168.10.165";
//	if(p){
//	if(p =='uc' || p =='oms'|| p =='log' ||  p =='dc'){
//			url = "http://fwpt.smexj.gov.cn";
//		}else if(p =='ocs'){
//			url = "http://202.100.180.24";
//		}
		
//	}
	return url;
	
}