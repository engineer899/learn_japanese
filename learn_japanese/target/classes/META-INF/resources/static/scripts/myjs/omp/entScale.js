var img = "<img src='/zhirong.cas/static/images/omp/accept.png'/>";
var scaleIndustrys=true;
var scaleEmployeeNums=true;
var scaleIncomes=true;
function checkIndustry(){
	 var indus = $('#scaleIndustry').val();
	 if(indus==''){
	   $('#scaleIndustry1').html("请选择行业类别");
	   scaleIndustrys=false;
	   return false;
	 }else{
	   $('#scaleIndustry1').html(img);
	   scaleIndustrys=true;
	   return true;
	 }
}
function checkEmpNum(){
	var emp=$('#scaleEmployeeNum').val();
	if(emp==''){
        $('#scaleEmployeeNum1').html("从业人数不能为空");
        scaleEmployeeNums=false;
        return false;
    }else if(!isInt(emp) || emp.length>7){
    	$('#scaleEmployeeNum1').html("从业人数必须是正整数且位数不超过7位");
    	scaleEmployeeNums=false;
    	return false;
    }else{
    	$('#scaleEmployeeNum1').html(img);
    	scaleEmployeeNums=true;
    	return true;
    }
}
function checkScaleIncome(){
	var inc=$('#scaleIncome').val();
	var exp = /^\d{1,10}(\.\d{1,2})?$/;
	 if(inc==''){
	    $('#scaleIncome1').html("营业收入不能为空");
	    scaleIncomes=false;
	    return false;
	 }else if(!exp.test(inc)){
	    $('#scaleIncome1').html("收入不超过10位数值且小数点后保留两位");
	    scaleIncomes=false;
	    return false;
	 }else{
	    $('#scaleIncome1').html(img);
	    scaleIncomes=true;
	    if(inc >= 2000){
	      $("#entScale").val("1");
	    }else{
	      $("#entScale").val("2");
	    }
	    return true;
	 }
}
function cclteOrgScale(){ 
	var empval = new RegExp("^[0-9]*[1-9][0-9]*$");
	var incval = new RegExp("^[0-9]*[1-9][0-9]*$|^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$");
    var scale=$('#ent.orgScale').val();
    var indus = $('#scaleIndustry').val();
    var emp=$('#scaleEmployeeNum').val();
    var inc=$('#scaleIncome').val();
    switch(indus)
	{	
		case '0000000001':
			if(inc<50){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc<500&&inc>=50){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=500&&inc<20000){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=20000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000002':
			if(emp<20||inc<300){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<300)&&(inc>=300&&inc<40000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((inc>=300&&inc<2000) && (emp>=20&&emp<1000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=300&&emp<1000)&&(inc>=2000&&inc<40000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=1000||inc>=40000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000003':
			if(inc<300){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=300&&inc<6000){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=6000&&inc<80000){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=80000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			
		break;
		
		case '0000000004':
			if(emp<5||inc<1000){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=5&&emp<20)&&(inc>=1000&&inc<40000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=5&&emp<200)&&(inc>=1000&&inc<5000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<200)&&(inc>=5000&&inc<40000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=200||inc>=40000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		
		break;
		
		case '0000000005':
			if(emp<10||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<50)&&(inc>=100&&inc<20000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<300)&&(inc>=100&&inc<500)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=50&&emp<300)&&(inc>=500&&inc<20000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300||inc>=20000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000006':
			if(emp<20||inc<200){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<300)&&(inc>=200&&inc<30000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<1000)&&(inc>=200&&inc<3000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=300&&emp<1000)&&(inc>=3000&&inc<30000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=1000||inc>=30000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000007':
			if(emp<20||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<100)&&(inc>=100&&inc<30000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<200)&&(inc>=100&&inc<1000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<200)&&(inc>=1000&&inc<30000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=200||inc>=30000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000008':
			if(emp<20||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<300)&&(inc>=100&&inc<30000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=20&&emp<1000)&&(inc>=100&&inc<2000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=300&&emp<1000)&&(inc>=2000&&inc<30000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=1000||inc>=30000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000009':
			if(emp<10||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<100)&&(inc>=100&&inc<10000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<300)&&(inc>=100&&inc<2000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<300)&&(inc>=2000&&inc<10000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300||inc>=10000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000010':
			if(emp<10||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<100)&&(inc>=100&&inc<10000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<300)&&(inc>=100&&inc<2000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<300)&&(inc>=2000&&inc<10000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300||inc>=10000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000011':
			if(emp<10||inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<100)&&(inc>=100&&inc<100000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<2000)&&(inc>=100&&inc<1000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<2000)&&(inc>=1000&&inc<100000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=2000||inc>=100000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000012':
			if(emp<10||inc<50){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<100)&&(inc>=50&&inc<10000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=10&&emp<300)&&(inc>=50&&inc<1000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<300)&&(inc>=1000&&inc<10000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300||inc>=10000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000013':
			if(inc<100){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=100&&inc<1000){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=1000&&inc<200000){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(inc>=200000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000014':
			if(emp<100||inc<500){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<300)&&(inc>=500&&inc<5000)){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=100&&emp<1000)&&(inc>=500&&inc<1000)){
				var	radiovalue='3';
				getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if((emp>=300&&emp<1000)&&(inc>=1000&&inc<5000)){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=1000||inc>=5000){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000015':
			if(emp<10){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=10&&emp<100){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=100&&emp<300){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
		
		case '0000000016':
			if(emp<10){
			var	radiovalue='4';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=10&&emp<100){
			var	radiovalue='3';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=100&&emp<300){
			var	radiovalue='2';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
			if(emp>=300){
			var	radiovalue='1';
			getRadioBoxValue('ent.orgScale', radiovalue);
			}
		break;
	}


 }
function getRadioBoxValue(radioName, radiovalue)    {  
    var obj = document.getElementsByName(radioName); 
    //alert(obj);
     for(i = 0; i < obj.length; i++){ 
    	// alert(obj[i].value == radiovalue);
       if(obj[i].value == radiovalue){
       obj[i].checked = true;
       }
     }      
  return true;
} 
//校验输入是整数
function isInt(s){
  var pattern = /^\d+\d*$/;
  if(pattern.exec(s)){
   return true;
  }else{
   return false;
  }
 }
