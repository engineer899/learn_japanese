	var pltsPop=null;
	var pltsoffsetX = 12; // 弹出窗口位于鼠标左侧或者右侧的距离；3-12 合适
	var pltsoffsetY = 15; // 弹出窗口位于鼠标下方的距离；3-12 合适
	var pltsTitle="";
	var tag="false";
	document.write('<div id= "pltsTipLayer"  style="display: none;position: absolute; z-index:10001; "></div>');
	function pltsinits()
	{
 		document.onmouseover = plts;
 		document.onmousemove = moveToMouseLoc;
	}
	function plts()
	{ 
	var event =window.event||arguments[0];
	
	var o = event.srcElement || event.target;
        if (o.alt != null && o.alt != "") {
            o.dypop = o.alt;
            o.alt = ""
        }
        if (o.title != null && o.title != "") {
            o.dypop = o.title;
            o.title = ""
        }
        pltsPop=o.dypop;
 	if(pltsPop!=null&&pltsPop!=""&&typeof(pltsPop)!="undefined")
 	{
	 pltsTipLayer.style.left=-1000;
	 pltsTipLayer.style.display='';
	 var Msg=pltsPop.replace(/\n/g,"<br>");
	 Msg=Msg.replace(/\0x13/g,"<br>");
	 var re=/\{(.[^\{]*)\}/ig;
	 if(!re.test(Msg))pltsTitle="";
	 else{
	 re=/\{(.[^\{]*)\}(.*)/ig;
	 pltsTitle=Msg.replace(re,"$1")+" ";
	 re=/\{(.[^\{]*)\}/ig;
	 Msg=Msg.replace(re,"");
	 Msg=Msg.replace("<br>","");
	 }
 var content1 =
 '<table style="FILTER:alpha(opacity=90);border: 1px solid #cccccc" id="toolTipTalbe" cellspacing="1" cellpadding="0"><tr><td width="100%"><table bgcolor="#ffffff" cellspacing="0" cellpadding="0">'+
 '<tr id="pltsPoptop"><td height="20" bgcolor="#0094bb"><font color="#ffffff"><b><p id="topleft" align="left">↖'+pltsTitle+'</p><p id="topright" align="right" style="display:none">'+pltsTitle+'↗</font></b></font></td></tr>'+
 '<tr><td "+attr+" style="padding-left:10px;padding-right:10px;padding-top: 8px;padding-bottom:6px;line-height:140%">'+Msg+'</td></tr>'+
 '<tr id="pltsPopbot" style="display:none"><td height="20" bgcolor="#0094bb"><font color="#ffffff"><b><p id="botleft" align="left">↙'+pltsTitle+'</p><p id="botright" align="right" style="display:none">'+pltsTitle+'↘</font></b></font></td></tr>'+
 '</table></td></tr></table>';
 
 
 var content =
	 '<div  style="border:1px solid #f1d68e; background:#fefdf2;padding:0 10px; float:left; line-height:24px;position:relative;  font-size:13px; color:#333;" id="toolTipTalbe" cellspacing="1" cellpadding="0">'+
	 '<i style="position:absolute; top:-16px; left:10px;"><img src="/zhirong.cas/static/images/omp/title.gif" /></i>'+
	 '<div id="pltsPoptop"><p id="topleft" align="left">'+pltsTitle+'</p><p id="topright" align="right" style="display:none">'+pltsTitle+'</font></b></font></div>'+
	 '<div><p>'+Msg+'</p></div>'+
	 '<div id="pltsPopbot" style="display:none"><p id="botleft" align="left" >'+pltsTitle+'</p><p id="botright" align="right" style="display:none">'+pltsTitle+'</font></b></font>'+
	 '</div><\div>';
 pltsTipLayer.innerHTML=content;
 toolTipTalbe.style.width=Math.min(pltsTipLayer.clientWidth,document.body.clientWidth/2.2);
 moveToMouseLoc();
 return true;
 }
 else
 {
 pltsTipLayer.innerHTML='';
 pltsTipLayer.style.display='none';
 return true;
 }
}
function moveToMouseLoc()
{
	var MouseY;
	var event =window.event || arguments[0];//arguments[0]火狐下获取event事件
 if(pltsTipLayer.innerHTML=='')return true;

var MouseX=event.x ? event.x : event.pageX;

// var MouseY=event.y ? event.y : event.pageY;
 if(event.y==undefined){
	 MouseY=event.pageY;
	 tag="true";//火狐下MouseY为鼠标所在位置到window顶端位置距离+滑动距离
 }else{
	 MouseY=event.y;//IE下MouseY仅为鼠标所在位置到window顶端位置距离
 }
 var popHeight=pltsTipLayer.clientHeight;
 var popWidth=pltsTipLayer.clientWidth;

 if(MouseY+pltsoffsetY+popHeight>document.body.clientHeight)
 {
 popTopAdjust=-popHeight-pltsoffsetY*1.5;
 pltsPoptop.style.display="none";
 pltsPopbot.style.display="";
 }
 else
 { 
 popTopAdjust=0;
 pltsPoptop.style.display="";
 pltsPopbot.style.display="none";
 }
 if(MouseX+pltsoffsetX+popWidth>document.body.clientWidth)
 {
 popLeftAdjust=-popWidth-pltsoffsetX*2;
 topleft.style.display="none";
 botleft.style.display="none";
 topright.style.display="";
 botright.style.display="";
 }
 else
 {
 popLeftAdjust=0;
 topleft.style.display="";
 botleft.style.display="";
 topright.style.display="none";
 botright.style.display="none";
 }
 var scrollTop;
    if(document.body.scrollTop != 0){
    	scrollTop=document.body.scrollTop;
    }else if(document.documentElement.scrollTop != 0){
    	scrollTop = document.documentElement.scrollTop;
    }else{
    	scrollTop=0;
    }
 pltsTipLayer.style.left =  MouseX+pltsoffsetX+popLeftAdjust+"px";
 if(tag=="true"){//火狐下
	 pltsTipLayer.style.top =  MouseY+pltsoffsetY+popTopAdjust+"px";
 }else if(tag="false"){//IE及360下
	 pltsTipLayer.style.top =  MouseY+pltsoffsetY+scrollTop+popTopAdjust+"px";
 }
 
 return true;
}
pltsinits();
 