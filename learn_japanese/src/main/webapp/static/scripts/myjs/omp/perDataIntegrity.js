$(function(){
	$.ajax({
  		type:'post',
  	 	url:base+"/show.action",
		data:{code:'per_data_integrity'},
   		success:function(data){
			 var arr=$.parseJSON(data);
			 var json = arr.perinfo;
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
			 if(num != 8){
	        	  $("#width").width((num * 34)+"px");
	        	  $("#dataIntegrity").show();
	           }else{
	        	  $("#width").width("273px"); 
	        	  $("#dataIntegrity").hide();
	           }
	           
	           $("#percent").html(Math.round(num * 12.5)+"%");
		}
      });
});