var img = "<img src='" + base + "/omp/images/accept.png'>";
var pattern = new RegExp("[`~%!@#^=''?~！@#￥……&——‘”“'？*()（），,。.、]");

function checkName() {
	var text = $("#name").val();
	$("#nationMsg").html("");
	if (text.length > 20) {
		$("#nameMsg").html("姓名不能超过20个字");
		return false;
	} else if (pattern.test(text)) {
		$("#nameMsg").html("不能输入特殊符号");
		return false;
	} else if (text == '') {
		$("#nameMsg").html("姓名不能为空");
		return false;
	} else {
		$("#nameMsg").html(img);
		return true;
	}
}

/*function checkSex() {
	var sex = $("#sex").val();
	if (sex == '') {
		$("#sexMsg").html("性别不能为空");
		return false;
	} else {
		$("#sexMsg").html(img);
		return true;
	}
}*/

function checkAge() {
	var age = $("#age").val();
	var intRule = /^-?\d+$/;
	if (age == '') {
		$("#ageMsg").html("");
		return true;
	} else if (age && !intRule.test(age)) {
		$("#ageMsg").html("年龄必须为整数");
		return false;
	} else if (age.length > 2) {
		$("#ageMsg").html("年龄不能超过99");
		return false;
	} else {
		$("#ageMsg").html(img);
		return true;
	}
}

function checkNation() {
	var text = $("#nation").val();
	$("#nationMsg").html("");
	if (text.length > 30) {
		$("#nationMsg").html("民族不能超过30个字");
		return false;
	} else if (pattern.test(text)) {
		$("#nationMsg").html("不能输入特殊符号");
		return false;
	} else if (text == '') {
		$("#nationMsg").html("");
		return true;
	} else {
		$("#nationMsg").html(img);
		return true;
	}
}

// 判断联系电话
function checkPhone() {
	var text = $("#tel").val();
	if (/(^1[358]\d{9}$)|(\d{3}-|\d{4}-)(\d{8}|\d{7})$/.test(text)) {
		$("#telMsg").html(img);
		return true;
	} else if (text == '') {
		$("#telMsg").html("联系电话不能为空");
		return false;
	} else {
		$("#telMsg").html("联系电话不正确");
		updateParentIframeHeight();
		return false;
	}
}

// 判断邮箱
function checkEmail() {
	var email = $("#email").val();
	if (/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
			.test(email)) {
		$("#emMsg").html(img);
		return true;
	} else if (email == '') {
		$("#emMsg").html("邮箱不能为空！");
		return false;
	} else {
		$("#emMsg").html("邮箱格式不正确！");
		return false;
	}
}

function checkSchool() {
	var others = $("#finishSchool").val();
	if (others == '') {
		$("#finishMsg").html("");
		return true;
	} else if (others.length > 50) {
		$("#finishMsg").html("毕业学校不能超过50个字");
		return false;
	} else {
		$("#finishMsg").html(img)
		return true;
	}
}

function checkProfession() {
	var others = $("#profession").val();
	if (others == '') {
		$("#proMsg").html("");
		return true;
	} else if (others.length > 32) {
		$("#proMsg").html("专业不能超过32个字");
		return false;
	} else {
		$("#proMsg").html(img)
		return true;
	}
}

function checkHighEducation() {
	var others = $("#highEducation").val();
	if (others == '') {
		$("#highMsg").html("");
		return true;
	} else if (others.length > 32) {
		$("#highMsg").html("最高学历不能超过32个字");
		return false;
	} else {
		$("#highMsg").html(img)
		return true;
	}
}

function checkJob() {
	var others = $("#job").val();
	if (others == '') {
		$("#jobMsg").html("");
		return true;
	} else if (others.length > 64) {
		$("#jobMsg").html("技术职称不能超过64个字");
		return false;
	} else {
		$("#jobMsg").html(img)
		return true;
	}
}

function checkDuties() {
	var others = $("#duties").val();
	if (others == '') {
		$("#dubMsg").html("");
		return true;
	} else if (others.length > 64) {
		$("#dubMsg").html("职务不能超过64个字");
		return false;
	} else {
		$("#dubMsg").html(img)
		return true;
	}
}
// 行业
function checkIndustryCode(value) {
	if (value.value != '') {
		$("#inMsg").html(img);
		return true;
	} else {
		$("#inMsg").html("");
		return true;
	}
}

function checkFax() {
	var fax = $("#fax").val();
	var zuoji = /^\d{3,4}-\d{7,8}$/;
	if (fax == "") {
		$("#faxMsg").html("");
		return true;
	} else if (!zuoji.test(fax)) {
		$("#faxMsg").html("传真须符合格式：xxx(x)-xxxxxxx(x)");
		return false;
	} else {
		$("#faxMsg").html(img);
		return true;
	}
}

function checkPost() {
	var postCode = $("#postCode").val();
	var exp = /^[1-9][0-9]{5}$/;
	if (postCode == "") {
		$("#postMsg").html("");
		return true;
	} else if (!exp.test(postCode)) {
		$("#postMsg").html("邮政编码格式不正确");
		return false;
	} else {
		$("#postMsg").html(img);
		return true;
	}
}

function checkRess() {
	var others = $("#address").val();
	if (others == '') {
		$("#ressMsg").html("");
		$("#ressMsg").html(img);
		return true;
	} else if (others.length > 64) {
		$("#ressMsg").html("通信地址不能超过64个字");
		return false;
	} else {
		$("#ressMsg").html(img);
		return true;
	}
}

function checkWorkUnit() {
	var others = $("#workUnit").val();
	if (others == '') {
		$("#workUnitMsg").html("工作单位不能为空");
		return true;
	} else if (others.length > 64) {
		$("#workUnitMsg").html("工作单位不能超过64个字");
		return false;
	} else {
		$("#workUnitMsg").html(img)
		return true;
	}
}
function checkField() {
	var text = $("#expFieldType").val();
	if (text.length > 500) {
		$("#expFieldMsg").html("擅长领域不能超过500个字");
		return false;
	} else if (text == '') {
		$("#expFieldMsg").html("擅长领域不能为空");
		return false;
	} else {
		$("#expFieldMsg").html(img);
		return true;
	}
}

function checkEducation() {
	var text = $("#education").val();
	if (text.length > 500) {
		$("#edMsg").html("教育经历不能超过500个字");
		updateParentIframeHeight();
		return false;
	} else if (text == '') {
		$("#edMsg").html("");
		return true;
	} else {
		$("#edMsg").html(img);
		return true;
	}
}

function checkWork() {
	var text = $("#expWork").val();
	if (text.length > 500) {
		$("#expWorkMsg").html("工作经历不能超过500个字");
		return false;
	} else if (text == '') {
		$("#expWorkMsg").html("");
		return true;
	} else {
		$("#expWorkMsg").html(img);
		return true;
	}
}

function checkCase() {
	var text = $("#case").val();
	if (text.length > 500) {
		$("#caseMsg").html("案例经验不能超过500个字");
		return false;
	} else if (text == '') {
		$("#caseMsg").html("");
		return true;
	} else {
		$("#caseMsg").html(img);
		return true;
	}
}

// 判断专家简介
function checkEditor() {
	var text = UE.getEditor('editor').getContentTxt();
	$("#textArea").html("");
	if (text == '') {
		$("#textArea").html("专家简介不能为空");
		return false;
	} else if (text.replace(/(^\s*)|(\s*$)/g, "") == "") {
		$("#textArea").html("不能全为空格");
		return false;
	} else {
		$("#textArea").html(img);
		return true;
	}
}

function checkOwnWinAndMajor() {
	var ownWin = $("#ownWin").val();
	var majorType = $("#majorType").val();
	if (ownWin == '') {
		$("#ownWinInfo").html("专业系统分类不能为空");
		return false;
	}
	if (majorType == '') {
		$("#majorTypeInfo").html("所属窗口分类不能为空");
		return false;
	}
	return true;
}

var ownwins = true;
var majorTypes = true;

// 窗口选择artdialog
function openOwnWin() {
	var url = base + "/show.action?code=own_win_choose";
	art.dialog.open(url, {
		title : "所属窗口",
		width : 600,
		height : 505,
		lock : true,
		window : 'top',
		fixed:true,
		close : function() {
			if ($("#ownWinName").val() == "") {
				$('#ownWinInfo').html("请选择所属窗口");
				ownwins = false;
			} else {
				$('#ownWinInfo').html(img);
				ownwins = true;
			}
		}
	});
}
function openMajorType() {
	var url = base + "/show.action?code=pas_major_type";
	art.dialog.open(url, {
		title : "专业系统分类",
		width : 600,
		height : 300,
		lock : true,
		window : 'top',
		fixed:true,
		close : function() {
			if ($("#majorTypeName").val() == "") {
				$('#majorTypeInfo').html("请选择专业系统分类");
				majorTypes = false;
			} else {
				$('#majorTypeInfo').html(img);
				majorTypes = true;
			}
		}
	});
}
function openField(){
	var expFieldTypes = true;
	var expFieldType = document.getElementById("expFieldType").value;	
	var url = base+"/expert/expert!getExpertDomain.action?expFieldType="+expFieldType;
	art.dialog.open(url,{
		title:"专家领域",
		width:700,
	   	height:500,
	   	lock:true,
	   	window: 'top',
	   	fixed:true,
	   	close:function(){
			if ($("#expField").val() == "") {
				$('#expFieldMsg').html("请选择擅长领域");
				 expFieldTypes = false;
			} else {
			$('#expFieldMsg').html("");
				$('#expFieldInfo').html(img);
				  expFieldTypes = true;
			}
	   	}
	});
	return expFieldTypes;
}
function checkFiled() {
	var ownWin = $("#expField").val();	
	if (ownWin == '') {
		$("#expFieldInfo").html("所属领域不能为空");
		return false;
	}
	return true;
}

