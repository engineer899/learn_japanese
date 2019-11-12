var Mphones = true;// 手机号
var passwords = true;// 密码
var usernames = true;// 用户名
var email = true;
var legalPerson = true;
var orgLinkman = true;
var orgLinkmanPhone = true;
var orgFax = true;
var orgWeb = true;
var orgAddress = true;
var orgPostcode = true;
var qqs = true;
var regTypes = true;
var areas = true;
var registerTimes = true;
var industrys = true;
var holdingTypes = true;
var regMoney = true;
var orgNames = true;
var orgCodes = true;
var regestNos = true;
var userids = true;
var editors = true;
var files = true;// 相关证件附件
var filezz = true;// 营业执照
var filelogo = true;// 企业logo
var ownwins = true;
var orgEmployeeNums = true;// 员工数量

// 窗口选择artdialog
function openOwnWin() {
	var url = "/zhirong.cas/show.action?code=own_win_choose";
	art.dialog.open(url, {
		title : "所属窗口",
		width : 600,
		height : 505,
		lock : true,
		window : 'top',
		fixed : true,
		close : function() {
			if ($("#ownWinName").val() == "") {
				$('#ownWinInfo').html("请选择所属窗口");
				ownwins = false;
			} else {
				$('#ownWinInfo').html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				ownwins = true;
			}
		}
	});
}

/*$(document).ready(function() {
	var editor = new UE.ui.Editor();
	editor.render("editor");
	editor.ready(function() {
		updateParentIframeHeight();
	})
	// 得到uuid
	var uuid = "";
	$.ajax({
		url : '/zhirong.cas/requirment/getUUID.action',
		type : 'post',
		dataType : 'text',
		data : '',
		success : function(msg) {
			if (msg != "") {
				$("#uuid").attr("value", msg);
			}
		},
		error : function() {
			var dialog = art.dialog({
				title : '出错了',
				content : '系统出现错误请联系管理员！',
				icon : 'succeed',
				left : '40%',
				time : 1
			});
		}
	});
})*/

function checkfile(ab) {
	// 避免把这三项错误数据提交数据类型与数据库不匹配出错
	if (!regMoney) {
		$("#regMoney").val("");
	}
	if (!scaleEmployeeNums) {
		$("#scaleEmployeeNum").val("");
	}
	if (!scaleIncomes) {
		$("#scaleIncome").val("");
	}
	// ab 1-相关证件 2-营业执照
	var checkForm = document.getElementById("fm");
	checkForm.action = "/zhirong.cas/ent/checkUploadFile.action?OorE=E&fileFlag=" + ab + "&file="+$("#file").val();
	if (ab == '1') {
		$("#fileInfo").html("");
		checkForm.target = "Upload";
	} else if (ab == '2') {
		$("#filezzInfo").html("");
		checkForm.target = "Uploadzz";
	} else if (ab == '3') {
		$("#logoInfo").html("");
		checkForm.target = "Uploadlogo";
	}
	ab = "";
	updateParentIframeHeight();
	checkForm.submit();
}
function checkfile1(ab){
	// 获取子窗口iframe的对象，Upload为iframe的id
	var iframe = "";
	var fvalue = "";
	var fInfoId = "";
	var info = "";
	var fsize = '';
	var allowTypes = ['jpg','jpeg','png','gif','pdf'];
	if (ab == '1') {
		iframe = window.frames['Upload'];
		fvalue = $("#org_img").val();
		var obj = document.getElementById('org_img');
		fsize =  obj.files[0].size;
		fInfoId = "fileInfo";
		info = "请上传相关证件";
	} else if (ab == '2') {
		iframe = window.frames['Uploadzz'];
		fvalue = $("#licence_img").val();
		var obj = document.getElementById('licence_img');
		fsize =  obj.files[0].size;
		fInfoId = "filezzInfo";
		info = "请上传营业执照";
	} else if (ab == '3') {
		iframe = window.frames['Uploadlogo'];
		fvalue = $("#org_logo").val();
		var obj = document.getElementById('org_logo');
		fsize =  obj.files[0].size;
		fInfoId = "logoInfo";
		info = "";
	}
	$('#' + fInfoId).html("");
	if(fvalue == ''){
		if(ab!='3'){
			$('#' + fInfoId).html(info);
			return false;
		}else{
			return true;
		}
	}else if(fsize > 2097152){
		$('#' + fInfoId).html("上传文件必须小于2M");
		return false;
	}else if(!validFileType(fvalue, allowTypes)){
		$('#' + fInfoId).html("上传的文件格式不正确！");
		return false;
	}else{
		return true;
	}
	//检验文件类型
	function validFileType(fileName,allowTypes){
		var fileType = fileName.substr(fileName.lastIndexOf('.') + 1).toLowerCase();
		for(var i = 0;i < allowTypes.length;i++){
			if(fileType == allowTypes[i]){
				return true;
			}
		}
		return false;
	}
}
function fileVali(ab) {
	// 获取子窗口iframe的对象，Upload为iframe的id
	var iframe = "";
	var fvalue = "";
	var fInfoId = "";
	var info = "";
	var fsize = '';
	var allowTypes = ['jpg','jpeg','png','gif','pdf'];
	if (ab == '1') {
		iframe = window.frames['Upload'];
		fvalue = $("#org_img").val();
		var obj = document.getElementById('org_img');
		fsize =  obj.files[0].size;
		fInfoId = "fileInfo";
		info = "请上传相关证件";
	} else if (ab == '2') {
		iframe = window.frames['Uploadzz'];
		fvalue = $("#licence_img").val();
		var obj = document.getElementById('licence_img');
		fsize =  obj.files[0].size;
		fInfoId = "filezzInfo";
		info = "请上传营业执照";
	} else if (ab == '3') {
		iframe = window.frames['Uploadlogo'];
		fvalue = $("#org_logo").val();
		var obj = document.getElementById('org_logo');
		fsize =  obj.files[0].size;
		fInfoId = "logoInfo";
		info = "";
	}
	$('#' + fInfoId).html("");
	if(fvalue == ''){
		if(ab!='3'){
			$('#' + fInfoId).html(info);
			return false;
		}else{
			return true;
		}
	}else if(fsize > 2097152){
		$('#' + fInfoId).html("上传文件必须小于2M");
		return false;
	}else if(!validFileType(fvalue, allowTypes)){
		$('#' + fInfoId).html("上传的文件格式不正确！");
		return false;
	}else{
		return true;
	}
	//检验文件类型
	function validFileType(fileName,allowTypes){
		var fileType = fileName.substr(fileName.lastIndexOf('.') + 1).toLowerCase();
		for(var i = 0;i < allowTypes.length;i++){
			if(fileType == allowTypes[i]){
				return true;
			}
		}
		return false;
	}
/*	if (fvalue == "") {
		if (ab != '3') {
			$('#' + fInfoId).html(info);
			updateParentIframeHeight();
			return false;
		} else {
			updateParentIframeHeight();
			return true;
		}
	} else if (iframe.document.getElementById("mms").value == '1') {
		updateParentIframeHeight();
		return true;
	} else {
		updateParentIframeHeight();
		return false;
	}*/
}

function hidd(value) {
	if (value == '10' || value == '20' || value == '30' || value == '50') {
		$("#gszch").hide();
		$("#sfzh").hide();
		$("#zzjgdm").show();
		$("#info")
				.html('企业、事业单位、社会团体、民办非企业上传组织机构代码复印件,必须上传小于2M的jpg、jpeg、png、gif、pdf格式复印件图片');
	}
	if (value == '40') {
		$("#gszch").show();
		$("#sfzh").hide();
		$("#zzjgdm").hide();
		$("#info").html('个体工商户上传工商注册复印件,必须上传小于2M的jpg、jpeg、png、gif、pdf格式复印件图片');
	}
	if (value == '99') {
		$("#gszch").hide();
		$("#sfzh").show();
		$("#zzjgdm").hide();
		$("#info").html("请上传身份证复印件,必须上传小于2M的jpg、jpeg、png、gif、pdf格式复印件图片");
	}
}
function addImg(id, obj) {
	if (obj != "") {
		if (id == 'regType') {
			regTypes = true;
		} else if (id == 'areainfo') {
			areas = true;
		} else if (id == 'industryInfo') {
			industrys = true;
		} else if (id == 'holdingTypeInfo') {
			holdingTypes = true;
		}
		$("#" + id).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
	} else {
		if (id == 'regType') {
			regTypes = false;
			$("#" + id).html("请选择企业注册类型");
		} else if (id == 'areainfo') {
			areas = false;
			$("#" + id).html("请选择所在地区");
		} else if (id == 'industryInfo') {
			industrys = false;
			$("#" + id).html("请选择所属行业");
		} else if (id == 'holdingTypeInfo') {
			holdingTypes = false;
			$("#" + id).html("请选择控股情况");
		}
	}
}
function regTypeChange(tag, obj) {
	var op = "<option value=''>请选择</option>";
	if (obj == "") {
		if (tag == '2') {
			$("#regType2").html(op);
			$("#regType").html("请选择企业注册类型");
			$("#regType3").html(op);
			$("#regType3").show();
			regTypes = false;
			updateParentIframeHeight();
		} else if (tag == '3') {
			$("#regType").html("请选择企业注册类型");
			$("#regType3").html(op);
			$("#regType3").show();
			regTypes = false;
			updateParentIframeHeight();
		}
	} else {
		$.ajax({
			type : "post",
			url : "/zhirong.cas/ent/queryOrgType.action",
			data : {
				code : 'reg_type',
				superCode : obj
			},
			success : function(data) {
				data = JSON.parse(data);
				if (tag == '2') {
					var str = '<option value="">请选择</option>';
					for(var i = 0; i < data.length; i++){
						str += '<option value="'+data[i].REG_CODE+'">'+data[i].REG_NAME+'</option>';
					}
					$("#regType2").html(str);
					$("#regType").html("请选择企业注册类型");
					$("#regType3").html(op);
					$("#regType3").show();
					regTypes = false;
					updateParentIframeHeight();
				} else if (tag == '3') {
					if (data.length <= 0) {
						$("#regType3").hide();
						$("#regType3").val("");// 清空三级原有数据
						$("#regType").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						regTypes = true;
					} else {
						$("#regType").html("请选择企业注册类型");
						$("#regType3").show();
						str = '<option value="">请选择</option>';
						for(var i = 0; i < data.length; i++){
							str += '<option value="'+data[i].REG_CODE+'">'+data[i].REG_NAME+'</option>';
						}
						$("#regType3").html(str);
						regTypes = false;
					}
				}
			}
		});
	}
}
function industryChange(tag, obj) {
	var op = "<option value=''>请选择</option>";
	if (obj == "") {
		if (tag == '2') {
			$("#industry2").html(op);
			$("#industryInfo").html("请选择所属行业");
			$("#industry3").html(op);
			$("#industry4").html(op);
			industrys = false;
			updateParentIframeHeight();
		} else if (tag == '3') {
			$("#industryInfo").html("请选择所属行业");
			$("#industry3").html(op);
			$("#industry4").html(op);
			industrys = false;
			updateParentIframeHeight();
		} else if (tag == '4') {
			$("#industry4").html(op);
			$("#industryInfo").html("请选择所属行业");
			industrys = false;
			updateParentIframeHeight();
		}
	} else {
		$.ajax({
			type : "post",
			url : "/zhirong.cas/ent/queryIndustry.action",
			data : {
				code : 'industry_select',
				superCode : obj
			},
			success : function(data) {
				data = JSON.parse(data);

				if (tag == '2') {

					var str = '<option value="">请选择</option>';
					for(var i = 0; i < data.length; i++){
						str += '<option value="'+data[i].INDUSTRY_CODE+'">'+data[i].INDUSTRY_NAME+'</option>';
					}
					$("#industry2").html(str);
					$("#industryInfo").html("请选择所属行业");
					$("#industry3").html(op);
					$("#industry4").html(op);
					$("#industry4").show();
					industrys = false;
					updateParentIframeHeight();
				} else if (tag == '3') {

					$("#industry4").show();
					var str = '<option value="">请选择</option>';
					for(var i = 0; i < data.length; i++){
						str += '<option value="'+data[i].INDUSTRY_CODE+'">'+data[i].INDUSTRY_NAME+'</option>';
					}
					$("#industry3").html(str);
					$("#industryInfo").html("请选择所属行业");
					$("#industry4").html(op);
					industrys = false;
				} else if (tag == '4') {

					if (data.length <= 0) {
						$("#industry4").hide();
						$("#industry4").val("");// 清空四级原有数据
						$("#industryInfo").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						industrys = true;
					} else {
						$("#industryInfo").html("请选择所属行业");
						$("#industry4").show();
						var str = '<option value="">请选择</option>';
						for(var i = 0; i < data.length; i++){
							str += '<option value="'+data[i].INDUSTRY_CODE+'">'+data[i].INDUSTRY_NAME+'</option>';
						}
						$("#industry4").html(str);
						industrys = false;
					}
				}
			}
		});
	}
}
function areaChange(tag, obj) {
	var op = "<option value=''>请选择</option>";
	if (obj == "") {
		$("#areainfo").html("请选择所在地区");
		areas = false;
		updateParentIframeHeight();
	} else {
		if(obj=='999900'|| obj==999900){
			$("#areaxj").hide();
			$("#area2").hide();
			$("#areainfo").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			areas = true;
		}else {
			$.ajax({
				type : "post",
				url : "/zhirong.cas/ent/queryArea.action",
				data : {
					code : 'area_select',
					superCode : obj
				},
				success : function(data) {
					data = JSON.parse(data);
					var str = '<option value="">请选择</option>';
					if(data.length > 0){
						$("#area2").show();
						if(tag=='2'){
							var st="<option value=''>请选择</option>";
							for(var i=0; i<data.length; i++){
								str += '<option value="'+data[i].AREA_CODE+'">'+data[i].AREA_NAME+'</option>';
							}
							$("#areainfo").html("请选择所在地区");
							$("#area2").html(str);
							areas = false;
						}
						if(tag=='1'){
                            $("#areaxj").show();
                            $("#area2").show();
							var st="<option value=''>请选择</option>";
							for(var i=0; i<data.length; i++){
								str += '<option value="'+data[i].AREA_CODE+'">'+data[i].AREA_NAME+'</option>';
							}
							$("#areainfo").html("请选择所在地区");
							$("#areaxj").html(str);
							areas = false;
						}
					}else{
						areas = true;
						$("#area2").hide();
						$("#areainfo").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
					}

					updateParentIframeHeight();
				}
			});
		}
	}
}

function holdingtypeChange(tag, obj) {
	var op = "<option value=''>请选择</option>";
	if (obj == "") {
		if (tag == '2') {
			$("#holdingType2").html(op);
			$("#holdingTypeInfo").html("请选择控股情况");
			$("#holdingType3").show();
			$("#holdingType3").html(op);
			holdingTypes = false;
		} else if (tag == '3') {
			$("#holdingType3").show();
			$("#holdingTypeInfo").html("请选择控股情况");
			$("#holdingType3").html(op);
			holdingTypes = false;
		}
	} else {
		$.ajax({
			type : "post",
			url : "/zhirong.cas/ent/queryHoldingType.action",
			dataType : "html",
			data : {
				code : 'holdingtype_select',
				superCode : obj
			},
			success : function(data) {
				data = JSON.parse(data);
				if (tag == '2') {
					if(data.length >0){
						var str = '<option value="">请选择</option>';
						for(var i = 0; i < data.length; i++){
							str += '<option value="'+data[i].HOLDING_CODE+'">'+data[i].HOLDING_NAME+'</option>';
						}
						$("#holdingType2").show();
						$("#holdingType2").html(str);
						$("#holdingTypeInfo").html("请选择控股情况");
						$("#holdingType3").show();
						$("#holdingType3").html(op);
						holdingTypes = false;
					}else{
						$("#holdingTypeInfo").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						$("#holdingType2").hide();
						$("#holdingType3").hide();
					}
				} else if (tag == '3') {
					if (data.length > 0) {
						var str = '<option value="">请选择</option>';
						for(var i = 0; i < data.length; i++){
							str += '<option value="'+data[i].HOLDING_CODE+'">'+data[i].HOLDING_NAME+'</option>';
						}
						$("#holdingType3").show();
						$("#holdingTypeInfo").html("请选择控股情况");
						$("#holdingType3").html(str);
						holdingTypes = false;

					} else if(data.length <32){
						$("#holdingType3").hide();
						$("#holdingType3").val("");// 清空原有三级数据
						$("#holdingTypeInfo").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						holdingTypes = true;
					}
				}
			}
		});
	}
}
function losecur(num, Elid, Elid1, msg) {
	if ($('#' + Elid).val() == '') {
		document.getElementById(Elid1).innerHTML = msg;
	}
}

// 判断字符串长度，汉字占两个字符
function getByteLen(val) {
	var len = 0;
	for (var i = 0; i < val.length; i++) {
		var a = val.charAt(i);
		if (a.match(/[^\x00-\xff]/ig) != null) {
			len += 2;
		} else {
			len += 1;
		}
	}
	return len;
}
function immediately(obj, num, Elid, Elid1) {
	// var re = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
    // var valueName=$('#'+Elid).val();
	// if (valueName == "") {
	// 	$('#' + Elid1).html("企业名称不能为空");
	// 	orgNames = false;
	// 	return false;
	// } else if (re.test(valueName)) {
	// 	$('#' + Elid1).html("输入内容不能包含特殊字符");
	// 	orgNames = false;
	// 	return false;
	// } else if (getByteLen(valueName) > 128) {
	// 	$('#' + Elid1).html("企业名称字数不大于64个字");
	// 	orgNames = false;
	// 	return false;
	// }
	// $.ajax({
	// 	url : '/zhirong.cas/ent/checkEntName.action',
	// 	type : 'post',
	// 	dataType : 'text',
	// 	data:{
	// 		entName:valueName
	// 	},
	// 	async : false,
	// 	success : function(msg) { // 相当于java中try语句块的用法
	// 		if (msg != "") {
	// 			$('#' + Elid1).html(msg);
	// 			orgNames = false;
	// 		} else {
	// 			$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
	// 			orgNames = true;
	// 		}
	// 	},
	// 	error : function() {
	// 		$('#' + Elid1).html("验证出错");
	// 		orgNames = false;
	// 		return false;
	// 	}
	// });

	var element = document.getElementById(Elid);

	if ("\v" == "v") {
		element.onpropertychange = checkthis;
	} else {
		element.addEventListener("input", checkthi(obj, num, Elid, Elid1),
				false);
	}
	function checkthis() {
		checkAll(obj, num, Elid, Elid1);
	}
	function checkthi(obj, num, Elid, Elid1) {
		checkAll(obj, num, Elid, Elid1);
	}
}
function checkWord(jid, jname, jvalue) {
	var re = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;

	if (re.test(jvalue)) {
		$('#' + jid).html("输入内容不能包含特殊字符");
		jname = false;
		return;
	}
}
function checkAll(obj, num, Elid, Elid1) {
	var tag = true;
	/*
	 * var re =/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im; if(num=='2' || num ==
	 * '222' || num == '8' || num=='10' || num=='11' || num=='14'){
	 *
	 * }else{ if(re.test(obj.value)){ tag = false; } }
	 */
	var re = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (xmlhttp.responseText == '5') {
				orgNames = true;
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			} else if (xmlhttp.responseText == '10') {
				orgCodes = true;
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			} else if (xmlhttp.responseText == '11') {
				regestNos = true;
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			} else if (xmlhttp.responseText == '12') {
				userids = true;
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			} else {
				if (num == '5') {
					orgNames = false;
				} else if (num == '17') {
					orgCodes = false;
				} else if (num == '18') {
					regestNos = false;
				} else if (num == '19') {
					userids = false;
				}
				document.getElementById(Elid1).innerHTML = xmlhttp.responseText;
			}
		}
	}
	if (tag == true) {
		var valueName = $('#' + Elid).val();
		valueName = valueName.replace(/\s/g, "");// 去掉空字符
		if (num == '0') {
			var name = /^([a-zA-Z0-9]){4,16}$/;
			if (valueName == "") {
				$('#' + Elid1).html("用户名不能为空");
				usernames = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				usernames = false;
				return false;
			} else if (!name.test(valueName)) {
				$('#' + Elid1).html("请输入4-16位数字或字母");
				usernames = false;
				return false;
			}
			$.ajax({
				url : '/zhirong.cas/reg/checkUserName.action',
				type : 'post',
				dataType : 'text',
				async : false,
				data : {
					username : valueName
				},
				success : function(msg) { // 相当于java中try语句块的用法
					if (msg != "") {
						$('#' + Elid1).html("该用户名称已经存在");
						usernames = false;
					} else {
						$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						usernames = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					usernames = false;
				}
			});
		} else if (num == '1') {
			//var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var myreg =/(^1[34578]\d{9}$)|(\d{3}-|\d{4}-)(\d{8}|\d{7})$/
			var tel = /^([0-9]){11}$/;
			if (valueName == "") {
				$('#' + Elid1).html("手机号码不能为空");
				Mphones = false;
				return false;
			} else if (!myreg.test(valueName)) {
				$('#' + Elid1).html("请输入正确的手机号码");
				Mphones = false;
				return false;
			}
			$.ajax({
				url : '/zhirong.cas/reg/checkPhone.action',
				type : 'post',
				dataType : 'text',
				async : false,
				data : {
					phone : valueName
				},
				success : function(msg) { // 相当于java中try语句块的用法
					if (msg != "") {
						$('#' + Elid1).html("该手机号码已经存在");
						Mphones = false;
					} else {
						$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						$("#orgLinkmanPhone").val(valueName);
						Mphones = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					Mphones = false;
				}
			});
		} else if (num == "2") {
			var myEmail = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (valueName == "") {
				$('#' + Elid1).html("常用邮箱不能为空");
				email = false;
				return false;
			} else if (!myEmail.test(valueName)) {
				$('#' + Elid1).html("请输入正确的邮箱");
				email = false;
				return false;
			}
			$.ajax({
				url : '/zhirong.cas/reg/checkUserEmail.action',
				type : 'post',
				dataType : 'text',
				async : false,
				data : {
					email :  $("#email").val()
				},
				success : function(msg) { // 相当于java中try语句块的用法
					if (msg != "") {
						$('#' + Elid1).html("该邮箱已经存在");
						email = false;
					} else {
						$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						email = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					email = false;
					return false;
				}
			});
		} else if (num == "222") {
			var myEmail = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (valueName == "") {
				$('#' + Elid1).html("");
				email = true;
				return false;
			} else if (!myEmail.test(valueName)) {
				$('#' + Elid1).html("请输入正确的邮箱");
				email = false;
				return false;
			} else {
				email = true;
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			}
		} else if (num == '3') {
			var pwd = /^([a-zA-Z0-9]){6,16}$/;
			if (valueName == "") {
				$('#' + Elid1).html("密码不能为空");
				passwords = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				passwords = false;
				return false;
			} else if (!pwd.test(valueName)) {
				$('#' + Elid1).html("请输入6-16位数字或者字母");
				passwords = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				passwords = true;
			}
		} else if (num == '4') {
			var pwd = /^([a-zA-Z0-9]){6,16}$/;
			if (valueName == "") {
				$('#' + Elid1).html("密码不能为空");
				passwords = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				passwords = false;
				return false;
			} else if (!pwd.test(valueName)) {
				$('#' + Elid1).html("请输入6-16位数字或者字母");
				passwords = false;
				return false;
			} else if ($('#password').val() != valueName) {
				$('#' + Elid1).html("两次密码输入不一致");
				passwords = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				passwords = true;
			}
		} else if (num == "52") {
			if (valueName == "") {
				$('#' + Elid1).html("企业名称不能为空");
				orgNames = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				orgNames = false;
				return false;
			} else if (getByteLen(valueName) > 128) {
				$('#' + Elid1).html("企业名称字数不大于64个字");
				orgNames = false;
				return false;
			}
			$.ajax({
				url : '/zhirong.cas/ent/checkEntName.action?entName='
						+ valueName,
				type : 'post',
				dataType : 'text',
				async : false,
				success : function(msg) { // 相当于java中try语句块的用法
					if (msg != "") {
						/*$('#' + Elid1).html(msg);*/
						orgNames = false;
					} else {
						$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
						orgNames = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					orgNames = false;
					return false;
				}
			});
		} else if (num == "5") {
			if (valueName == "") {
				$('#' + Elid1).html("企业名称不能为空");
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				orgNames = false;
				return false;
			} else {
				var num1 = "5";
				$.ajax({
					url : "/zhirong.cas/ent/checkEnt.action",
					data:{
                        value:obj.value,
                        num:num1
					},
					type : 'post',
					dataType : 'text',
					async : false,
					success : function(msg) { // 相当于java中try语句块的用法
						if (msg != "") {
							$('#' + Elid1).html(msg);
							orgNames = false;
						} else {
							$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
							orgNames = true;
						}
					},
					error : function() {
						$('#' + Elid1).html("验证出错");
						orgNames = false;
						return false;
					}
				});

			}
		} else if (num == "6") {
			if (valueName == "") {
				$('#' + Elid1).html("法人代表不能为空");
				legalPerson = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				legalPerson = false;
				return false;
			} else if (getByteLen(valueName) > 32) {
				$('#' + Elid1).html("法人代表字数不大于16个字");
				legalPerson = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				legalPerson = true;
			}
		} else if (num == "7") {
			if (valueName == "") {
				$('#' + Elid1).html("联系人不能为空");
				orgLinkman = false;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				orgLinkman = false;
				return false;
			} else if (getByteLen(valueName) > 32) {
				$('#' + Elid1).html("联系人字数不大于16个字");
				orgLinkman = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgLinkman = true;
			}
		} else if (num == "8") {
			var zuoji = /^\d{3,4}-\d{7,8}$/;
			var tel = /^([0-9]){11}$/;
			if (valueName == "") {
				$('#' + Elid1).html("联系方式（座机或手机）不能为空");
				orgLinkmanPhone = false;
				return false;
			} else if (!zuoji.test(valueName) && !tel.test(valueName)) {
				$('#' + Elid1).html("请输入手机/座机：xxx(x)-xxxxxxx(x)");
				updateParentIframeHeight();
				orgLinkmanPhone = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgLinkmanPhone = true;
			}
		} else if (num == "9") {
			// checkWord(Elid1,orgFax,valueName);
			if (valueName == "") {
				$('#' + Elid1).html("");
				orgFax = true;
				return false;
			} else if (getByteLen(valueName) != 11) {
				$('#' + Elid1).html("联系电话（手机）须符合格式：xxxxxxxxxxx");
				orgFax = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgFax = true;
			}
		} else if (num == '10') {
			var exp = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
			if (valueName == "") {
				$('#' + Elid1).html("");
				orgWeb = true;
				return false;
			} else if (!exp.test('http://' + valueName)) {
				$('#' + Elid1).html("请输入正确的网址");
				orgWeb = false;
				return false;
			} else if (getByteLen(valueName) > 128) {
				$('#' + Elid1).html("输入网址不大于128个字符");
				orgWeb = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgWeb = true;
			}
		} else if (num == "11") {
			var zuoji = /^\d{3,4}-\d{7,8}$/;
			if (valueName == "") {
				$('#' + Elid1).html("");
				orgFax = true;
				return false;
			} else if (!zuoji.test(valueName)) {
				$('#' + Elid1).html("传真须符合格式：xxx(x)-xxxxxxx(x)");
				updateParentIframeHeight();
				orgFax = false
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgFax = true;
			}
		} else if (num == "12") {
			if (valueName == "") {
				$('#' + Elid1).html("通讯地址不能为空");
				return false;
				orgAddress = false;
			} else if (getByteLen(valueName) > 256) {
				$('#' + Elid1).html("通讯地址字数不大于128个字");
				orgAddress = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgAddress = true;
			}
		} else if (num == "13") {
			var exp = /^[1-9][0-9]{5}$/;
			if (valueName == "") {
				$('#' + Elid1).html("");
				orgPostcode = true;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				orgPostcode = false;
				return false;
			} else if (!exp.test(valueName)) {
				$('#' + Elid1).html("邮政编码格式不正确");
				orgPostcode = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgPostcode = true;
			}
		} else if (num == "14") {
			var exp = /^\d{1,10}(\.\d{1,2})?$/;
			if (valueName == "") {
				// $('#'+Elid1).html("注册资本不能为空");
				regMoney = true;
				// return false;
			} else if (!exp.test(valueName)) {
				$('#' + Elid1).html("资本不超过10位数值且小数点后保留两位");
				updateParentIframeHeight();
				regMoney = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				regMoney = true;
			}
		} else if (num == "15") {
			if (valueName == "") {
				$('#' + Elid1).html("");
				return false;
			} else if (getByteLen(valueName) > 900) {
				$('#' + Elid1).html("企业介绍字数不大于500");
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
			}
		} else if (num == "17") {
			if (valueName == "") {
				$('#' + Elid1).html("组织机构代码不能为空");
				return false;
			}else if(getByteLen(valueName)>64){
				$('#' + Elid1).html("组织机构代码不能超过64个字符");
				return false;
			} else {
				var num1 = "10";
				$.ajax({
					url : "/zhirong.cas/ent/checkEnt.action",
                    data:{
                        value:obj.value,
                        num:num1,
                        password:document.getElementById("orgtype").value
                    },
					type : 'post',
					dataType : 'text',
					async : false,
					success : function(msg) { // 相当于java中try语句块的用法
						if (msg != "") {
							$('#' + Elid1).html(msg);
							orgNames = false;
						} else {
							$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
							orgNames = true;
						}
					},
					error : function() {
						$('#' + Elid1).html("验证出错");
						orgNames = false;
						return false;
					}
				});
			}
		} else if (num == "18") {
			if (valueName == "") {
				$('#' + Elid1).html("工商注册号不能为空");
				return false;
			} else {
				var num1 = "11";
				$.ajax({
					url : "/zhirong.cas/ent/checkEnt.action",
                    data:{
                        value:obj.value,
                        num:num1,
                        password:document.getElementById("orgtype").value
                    },
					type : 'post',
					dataType : 'text',
					async : false,
					success : function(msg) { // 相当于java中try语句块的用法
						if (msg != "") {
							$('#' + Elid1).html(msg);
							orgNames = false;
						} else {
							$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
							orgNames = true;
						}
					},
					error : function() {
						$('#' + Elid1).html("验证出错");
						orgNames = false;
						return false;
					}
				});
			}
		} else if (num == "19") {
			if (valueName == "") {
				$('#' + Elid1).html("身份证号不能为空");
				return false;
			} else {
				var num1 = "12";
				$.ajax({
					url : "/zhirong.cas/ent/checkEnt.action",
                    data:{
                        value:obj.value,
                        num:num1,
                        password:document.getElementById("orgtype").value
                    },
					type : 'post',
					dataType : 'text',
					async : false,
					success : function(msg) { // 相当于java中try语句块的用法
						if (msg != "") {
							$('#' + Elid1).html(msg);
							orgNames = false;
						} else {
							$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
							orgNames = true;
						}
					},
					error : function() {
						$('#' + Elid1).html("验证出错");
						orgNames = false;
						return false;
					}
				});
			}
		} else if (num == "26") {
			var qqva = /^[0-9]*$/;
			if (valueName == "") {
				$('#' + Elid1).html("");
				qqs = true;
				return false;
			} else if (re.test(valueName)) {
				$('#' + Elid1).html("输入内容不能包含特殊字符");
				qqs = false;
				return false;
			} else if (!qqva.test(valueName)) {
				$('#' + Elid1).html("QQ号码必须是数字");
				qqs = false;
				return false;
			} else if (getByteLen(valueName) > 13 || getByteLen(valueName) < 4) {
				$('#' + Elid1).html("QQ号码应为4-13位数字！");
				qqs = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				qqs = true;
			}
		} else if (num == "27") {
			if (valueName == "") {
				$('#' + Elid1).html("员工数量不能为空");
				orgEmployeeNums = false;
				return false;
			} else if (!isInt(valueName) || valueName.length > 7) {
				$('#' + Elid1).html("员工数量必须是整数且位数不超过7位");
				orgEmployeeNums = false;
				return false;
			} else {
				$('#' + Elid1).html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
				orgEmployeeNums = true;
			}
		}
	}
}
// 校验开业时间
function validate(obj) {
	if (obj != '') {
		$('#registerTime1').html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
		registerTimes = true;
	} else {
		// $('#registerTime1').html("开业时间不能为空");
		// $('#registerTime').focus();
		// registerTimes=false;
		registerTimes = true;
	}
}
// 校验富文本
function checkUeditor() {
	var text = UE.getEditor('editor').getContentTxt();
	$("#textArea").html("");
	if (text == "") {
		$("#textArea").html("企业介绍不能为空");
		// editors=false;
		return false;
	} else {
		$("#textArea").html("<img src='/zhirong.cas/static/images/omp/accept.png'/>");
		return true;
	}
}
// 校验输入是整数
function isInt(s) {
	var pattern = /^\d+\d*$/;
	if (pattern.exec(s)) {
		return true;
	} else {
		return false;
	}
}

