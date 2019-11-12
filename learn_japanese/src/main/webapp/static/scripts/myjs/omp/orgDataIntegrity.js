$(function(){
	$.ajax({
  		type:'post',
  	 	url:base+"/show.action",
		data:{code:'org_data_integrity'},
   		success:function(data){
			 var arr=$.parseJSON(data);
			 var json = arr.orginfo;
			 var l = json.length;
			 var num = 0;
			 for(var i=0;i<l;i++){  
				   for(var key in json[i]){  
				       //alert(key+":"+json[i][key]); 
					   if(json[i][key].replace(/[ ]/g,"") != ""){
						   num += 1;
					   }
				   }  
				}  
			 if(num != 6){
	        	  $("#width").width((num * 45.5)+"px");
	        	  $("#dataIntegrity").show();
	           }else{
	        	  $("#width").width("273px");
	        	  $("#dataIntegrity").hide();
	           }
	           if(num!=6 && Math.round(num * 16.6) < 100){
	        	  $("#percent").html(Math.round(num * 16.6)+"%"); 
	           }else{
	        	   $("#percent").html("100%");  
	           }
	           
		}
      });
});