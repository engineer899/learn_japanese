//粘贴源码
UE.commands['uploadimage'] = {
	execCommand : function(){
		openImage();
	}
};
UE.commands['uploadfile'] = {
	execCommand : function(){
		openAttach();
	}
};

var omp_url="/zhirong.cas";
function openImage(){
	var url= omp_url+"/document/image_manager.action";
	art.dialog.open(url,{
		title:'图片上传',
		width:800,
		height:600,
		lock:true,
		fixed:true,
		window : 'top',
		close:function(){
			var data = art.dialog.data('imagepath');
			art.dialog.removeData('imagepath');
			if(data){
				var editor = UE.getEditor('editor');
				editor.execCommand('inserthtml',data);
			}
			//bindContentblocksEvent();
		}
	});
}

function openAttach(){
	var url= omp_url+"/document/attach_manager.action";
	art.dialog.open(url,{
		title:'附件上传',
		width:500,
		height:450,
		lock:true,
		fixed:true,
		window : 'top',
		close:function(){
			var data = art.dialog.data('attachpath');
			art.dialog.removeData('attachpath');
			if(data){
				var editor = UE.getEditor('editor');
				editor.execCommand('inserthtml',data);
			}
			//bindContentblocksEvent();
		}
	});
}