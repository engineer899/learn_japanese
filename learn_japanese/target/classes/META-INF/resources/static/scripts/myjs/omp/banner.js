$(function(){
	var wwwUrl = get_www_url();
	var topUrl="";
	var footUrl="";
	$(function(){
		if(top_flag=='1'){  //创业
			topUrl = wwwUrl+"/show.action?code=cefe_home_top&status=04";
			footUrl= wwwUrl+"/show.action?code=cefe_home_foot";
			$("#frame2").css("height","150px");
			$("#frame2").css("min-width","1200px");
				//头部
			$.ajax({
				url : topUrl,
				type : 'POST',
				success : function(data) {
					$(".top6").html(data);
				}
			});
		}else if(top_flag=='2'){   //人才
			topUrl = wwwUrl+"/show.action?code=hr_home_top&status=08";
			footUrl= wwwUrl+"/show.action?code=hr_home_foot";
			$("#tt").removeClass("t1");
			$("#frame").attr("src",topUrl);
			$("#frame").css("height","185px");
			$("#frame").css("min-width","1200px");
			$("#frame2").css("height","120px");
			$("#frame2").css("min-width","1200px");
		}else if(top_flag=='3'){     //管理咨询
			topUrl = wwwUrl+"/show.action?code=con_home_top&status=07";
			footUrl= wwwUrl+"/show.action?code=con_home_foot";
			$("#tt").removeClass("t1");
			$("#frame").attr("src",topUrl);
			$("#frame").css("height","227px");
			$("#frame").css("min-width","1000px");
			$("#frame2").css("height","120px");
			$("#frame2").css("min-width","1200px");
		}else{
			var wwwurl = wwwUrl+"/show.action?code=web_home_top&status=07";
			$("#frame").attr("src",wwwurl);
			$("#frame2").attr("src",wwwUrl+"/show.action?code=web_home_foot");
		}
		$("#frame2").attr("src",footUrl);
	});
});