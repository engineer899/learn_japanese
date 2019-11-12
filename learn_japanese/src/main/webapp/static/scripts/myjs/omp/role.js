// JavaScript Document
//角色管理
window.onload=function(){
	var abtn1=document.getElementById('user-btn');
	var adiv1=document.getElementById('user-message');
	abtn1.onmouseover=function(){
		if(adiv1.style.display="none",abtn1.className="user-icon"){
			adiv1.style.display="block";
			abtn1.className="user-icon2"
			}else{
				adiv1.style.display="none";
				abtn1.className="user-icon";
				}
	}
	abtn1.onmouseout=function(){
		if(adiv1.style.display="block",abtn1.className="user-icon2"){
			adiv1.style.display="none";
			abtn1.className="user-icon"
			}else{
				adiv1.style.display="block";
				abtn1.className="user-icon2";
				}
	}
		adiv1.onmouseover=function(){
		if(adiv1.style.display="none",abtn1.className="user-icon"){
			adiv1.style.display="block";
			abtn1.className="user-icon2"
			}else{
				adiv1.style.display="none";
				abtn1.className="user-icon";
				}
	}
	adiv1.onmouseout=function(){
		if(adiv1.style.display="block",abtn1.className="user-icon2"){
			adiv1.style.display="none";
			abtn1.className="user-icon"
			}else{
				adiv1.style.display="block";
				abtn1.className="user-icon2";
				}
	}
  var adiv2=document.getElementById("label");
  var abtn2=adiv2.getElementsByTagName("a");
  if(abtn2.length<5) return;
  for( var i=0; i<abtn2.length;i++){
	  abtn2[i].onclick=function(){
		  for(var j=0;j<abtn2.length;j++){
			  abtn2[j].className="";
			  }
			  this.className="select";
		  }
	  }
	  
	  
	var abtn3=document.getElementById('nav-btn1');
	var adiv3=document.getElementById('nav-bar');
	  	abtn3.onmousemove=function (){
		if(adiv3.style.display="none"){
			adiv3.style.display="block";
			}else{
				adiv3.style.display="none";

				}
	}
		abtn3.onmouseout=function(){
		if(adiv3.style.display="block"){
			adiv3.style.display="none";
			}else{
				adiv3.style.display="block";
				}
	}
		  	adiv3.onmousemove=function (){
		if(adiv3.style.display="none"){
			adiv3.style.display="block";
			}else{
				adiv3.style.display="none";

				}
	}
		adiv3.onmouseout=function(){
		if(adiv3.style.display="block"){
			adiv3.style.display="none";
			}else{
				adiv3.style.display="block";
				}
	}
}