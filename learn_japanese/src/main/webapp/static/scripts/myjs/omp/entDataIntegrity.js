$(function(){
	$.ajax({
  		type:'post',
  	 	url:base+"/show.action",
		data:{code:'ent_data_integrity'},
   		success:function(data){
			 var arr=$.parseJSON(data);
			 var json = arr.entinfo;
			 var l = json.length;
			 var num = 0;
			 var orgtype = '';
			 for(var i=0;i<l;i++){  
				   for(var key in json[i]){  
				       //alert(key+":"+json[i][key]); 
					   if(key=='org_type'){
						   orgtype = json[i][key];
					   }
					   if(orgtype!=''){
						   //判断是否对组织机构代码计数
						   if(key=='org_code'){
							   if(orgtype == '10' || orgtype == '20' || orgtype == '30' || orgtype == '50'){
								   
							   }else{
								   continue;
							   }
						   }
						   //判断是否对工商注册号计数
						   if(key=='registered_no'){
							   if(orgtype == '40'){
								   
							   }else{
								   continue;
							   }
						   }
						   //判断是否对身份证号计数
						   if(key=='identity_num'){
							   if(orgtype == '99'){
								   
							   }else{
								   continue;
							   }
						   }
					   }else{//单位性质为空时只对组织机构代码计数
						   if(key=='registered_no'){
								   continue;
						   }
						   if(key=='identity_num'){
								   continue;
						   }
					   }
					   if(json[i][key] != ""){
						   num += 1;
					   }
				   }  
				}  
			 if(num != 26 && num < 26){
	        	  $("#width").width((num * 10.5)+"px");
	        	  $("#dataIntegrity").show();
	           }else{
	        	  $("#width").width("273px"); 
	        	  $("#dataIntegrity").hide();
	           }
	           if(Math.round(num * 3.84)<100){
	        	   $("#percent").html(Math.round(num * 3.84)+"%");
	           }else{
	        	   $("#percent").html("100%");
	           }
	           
		}
      });
});