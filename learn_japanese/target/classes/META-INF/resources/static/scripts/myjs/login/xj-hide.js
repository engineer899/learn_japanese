

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
	
}

$(function(){
	$("#navbar").mouseenter(function(){
		$("#nav-bar").show();
		
	}).mouseleave(function(){
		$("#nav-bar").hide();
		
	});
	
	
	
	
	
})

