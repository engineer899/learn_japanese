$(document).ready(function(){
	var x = 10;
	var y = 20;
	var screenWidth = $(document.body).width();//浏览器时下窗口文档body的宽度
	var imgWigth=0;//图片宽度
	var imgHeight=0;//图片高度
	$("a.tooltip").mouseover(function(e){
		//this.myTitle = this.title;
		//this.title = "";	
		//var imgTitle = this.myTitle? "<br/>" + this.myTitle : "";
		var tooltip = "<div id='tooltip'><img src='"+ this.href +"' style='max-width:500px; max-height:500px;'/><\/div>"; //创建 div 元素
		$("body").append(tooltip);
		 imgWidth = $("#tooltip").width();
		 imgHeight = $("#tooltip").height();
		//把它追加到文档中						 
		$("#tooltip")
			.css({
				"top": (e.pageY-imgHeight-y) + "px",
				"left": ((screenWidth-imgWidth)/2) + "px"
			}).show("fast");	  //设置x坐标和y坐标，并且显示
    }).mouseout(function(){
		//this.title = this.myTitle;	
		$("#tooltip").remove();	 //移除 
    }).mousemove(function(e){
		$("#tooltip")
			.css({
				"top": (e.pageY-imgHeight-y) + "px",
				"left": ((screenWidth-imgWidth)/2) + "px"
			});
	});
})