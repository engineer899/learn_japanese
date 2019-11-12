/*!
 * 
 *
 * jquery.fulongiframe.js 1.0
 *
 *
 * jQuery JavaScript Library v1.11.0
 *
 * author：黄正元
 *
 * Date: 2015-01-11
 */
 ;(function($){
	//文档的高度修改后，调用此方法，使父页面适应此高度
	updateParentIframeHeight = function() { 
		init_parent_iframeHeight();
	};
	
	function init_parent_iframeHeight(){
		if($("#fulongcontainer").length == 0){
			$("body").html("<div id='fulongcontainer'>" + $("body").html() + "</div>");
		}
		var dHeight =  $("#fulongcontainer").outerHeight(true) + 30;
		if($("#fulongiframe").length > 0){
			$("#fulongiframe").remove();
		}
		
		$("body").append('<iframe id="fulongiframe" style="display:none" src="/zhirong.cas/pages/system/agent.html#'+dHeight+'" scrolling="no" height="0px" width="0px"></iframe>');
	}
	$(function(){
		init_parent_iframeHeight();
	})
	
 
 })(jQuery)

