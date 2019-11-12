//判断不能为空
function isNull(value){
	if(value=="" || isNull2(value) == false){
		return false;
	}else{
		return true;
	}
}
function isNull2(value){
	if(/^\s*$/.test(value)){
		//alert("空格在前");
		return false;
	}
	return true;
}
//判断长度
function isLength(value,le){
	if(value.length>le){
		return false;
	}else{
		return true;
	}
}
//判断姓名格式
function isName(value){
	if(value != ""){
		if((!/^[\u4e00-\u9fa5]{1,15}$/.test(value)) && (!/^[A-Z][a-zA-Z]{0,29}$/.test(value))){
			return false;
		}
	}
	return true;
}
//判断身份证号码格式
function isIdCardNum(value){
	if(value != ""){
		if(!/^\d{15}(\d\d[0-9xX])?$/.test(value)){
			return false;
		}
	}
	return true;
}
//判断email格式
function isEmail(value){
	if(value!=""){
		if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
			return false;
		}
	}
	return true;
}
//判断电话号码格式
function isPhotoNew(value){
	if(value!=""){
		if(!/^(\d{4}-|\d{3}-)?(\d{8}|\d{7})$/.test(value)){
			return false;
		}
	}
	return true;
}
//判断电话号码格式
function isPhoto(value){
	if(value!=""){
		if(!/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})—(\d{7,8})|(\d{4}|\d{3})－(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{4}|\d{3})—(\d{7,8})—(\d{4}|\d{3}|\d{2}|\d{1})|(\d{4}|\d{3})－(\d{7,8})－(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})－(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})—(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value)){
			return false;
		}
	}
	return true;
}

//判断传真格式
function isFax(value){
	if(value!=""){
		if(!/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})—(\d{7,8})|(\d{4}|\d{3})－(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{4}|\d{3})—(\d{7,8})—(\d{4}|\d{3}|\d{2}|\d{1})|(\d{4}|\d{3})－(\d{7,8})－(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})－(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})—(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value)){
			return false;
		}
	}
	return true;
}

//判断邮编格式
function isPostCode(value){
	if(value!=""){
		if(!/^\d{6}$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断手机格式
function isMobile(value){
	if(value!=""){
		if(!/^(13[0-9]|15[0|3|6|8|9])\d{8}$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断year
function isYear(value){
	if(value!=""){
		if(!/^[1-9][0-9]{3}$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断为非负整数(0+正整数)
function isInt(value){
//	value = value.replace(/[ ]/g,""); //替换所有空格！
	if(value!=""){
		if(!/^[0-9]*$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断为非负数字（包含小数）
function isNum(value){
	if(value!=""){
		if(!/^\d+(\.\d+)?$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断数字
function isAllNum(value){
	if(value!=""){
		if(!/^[-]?\d+(\.\d+)?$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断行业代码
function isTradeCode(value){
	if(value!=""){
		if(!/^[a-zA-Z0-9_]{2,11}$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断组织机构代码
function isUnitCode(value){
	if(value!=""){
		if(!/^[a-zA-Z0-9_]{9,9}$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断网址
function isURL(value){
	if(value!=""){
		if(!/^[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^\"\"])*$/.test(value)){
				return false;
		}
	}
	return true;
}

//判断用户名是否合法，字母和数字，4-25之间
function isLegal(value){
	if(value!=""){
		if(!/^(\w{4,25})$/.test(value)){
			return false;
		}
	}
	return true;
}


//判断营业执照编号是否合法，字母和数字，4-30之间
function unitNo(value){
	if(value!=""){
		if(!/^(\w{4,30})$/.test(value)){
			return false;
		}
	}
	return true;
}

//首字符为空格
function isSpace(value){
	if(value!=""){
		if(/^\s*$/.test(value)){
			return false;
		}
	}
	return true;
}

//判断用户名是否合法，字母和数字，4-20之间
function isPwd(value){
	if(value!=""){
		if(!/^(\w{4,20})$/.test(value)){
			return false;
		}
	}
	return true;
}


function isPwd1(value){
	if(value!=""){
		if(!/^(\w{1,9})$/.test(value)){
			return false;
		}
	}
	return true;
}

function isPwd2(value){
	if(value!=""){
		if(!/^(\w{9,9})$/.test(value)){
			return false;
		}
	}
	return true;
}

function len(s) {
	var l = 0;
	var a = s.split("");
	for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
			l++;
		} else {
			l+=2;
		}
	}
	return l;
}


//判断日期
function isDate(sDate){
    var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
    var iaDate = new Array(3);
    var year, month, day;

    if (sDate == "") return true;
    iaDate = sDate.toString().split("-");
    if (iaDate.length != 3) return false;
    if (iaDate[1].length > 2 || iaDate[2].length > 2) return false;
    if (!isInt(iaDate[0]) || !isInt(iaDate[1]) || !isInt(iaDate[2])){
    	return false;
    }
    year = parseFloat(iaDate[0]);
    month = parseFloat(iaDate[1]);
    day = parseFloat(iaDate[2]);

    if (year < 1900 || year > 2100) return false;
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
    if (month < 1 || month > 12) return false;
    if (day < 1 || day > iaMonthDays[month - 1]) return false;
    return true;
}

//字符串转日期
function strToDate(str){
	var arys= new Array();
	arys=str.split('-');
	var newDate=new Date(arys[0],arys[1],arys[2]); 
	return newDate;
}

//日期比较,判断date2是否大于date1
function dateCompare(date1,date2){
    try{
        var dt1 = strToDate(date1);
        var dt2 = strToDate(date2);
        if ( dt2 >= dt1){
        	return true;
        }else{
        	return false;
        }
    }
    catch(e){
    	//alert("日期格式不正确");
        return false;
    }
}

//判断日期YYYY-MM格式
function isDateYM(sDate){
    var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
    var iaDate = new Array(2);
    var year, month;

    if (sDate == "") return true;
    iaDate = sDate.toString().split("-");
    if (iaDate.length != 2) return false;
    if (iaDate[1].length > 2) return false;
    if (!isInt(iaDate[0]) || !isInt(iaDate[1])){
    	return false;
    }
    year = parseFloat(iaDate[0]);
    month = parseFloat(iaDate[1]);

    if (year < 1900 || year > 2100) return false;
    if (month < 1 || month > 12) return false;
    return true;
}
//字符串转日期YYYY-MM
function strToDateYM(str){
	var arys= new Array();
	arys=str.split('-');
	var newDate=new Date(arys[0],arys[1]); 
	return newDate;
}
//日期比较,判断date2是否大于date1 YYYY-MM
function dateCompareYM(date1,date2){
    try{
        var dt1 = strToDateYM(date1);
        var dt2 = strToDateYM(date2);
        if ( dt2 >= dt1){
        	return true;
        }else{
        	return false;
        }
    }
    catch(e){
    	//alert("日期格式不正确");
        return false;
    }
}
function dateCompare(startdate,enddate)   
{   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();     
	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
	if(starttimes>=lktimes){return false;   }   
	else{return true;  } 
}  


