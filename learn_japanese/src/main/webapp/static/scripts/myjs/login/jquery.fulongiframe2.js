/*!
 * 
 *
 * jquery.fulongiframe2.js 1.0
 *
 *专为在线客服设计
 * jQuery JavaScript Library v1.11.0
 *
 * author：黄正元
 *
 * Date: 2015-01-11
 */
 ;(function($){
	//文档的高度修改后，调用此方法，使父页面适应此高度
	updateKfIframeHeight = function(scale) { 
		$("body").append('<iframe id="fulongkfiframe" style="display:none" src="localhost:8080/zhirong.cas/pages/system//agentzxkf.html#'+scale+'" scrolling="no" height="0px" width="0px"></iframe>');
	};
 })(jQuery)

