var img = "<img src='" + window.base + "/static/images/icon3.jpg'>";
function checktype(value2) {
	value1 = value2.value;
	if (value1 == '9') {
		document.getElementById("type1").innerHTML = '必须选择付款方式！';
		document.getElementById("zzjgdm").style.display = "none";
		document.getElementById("gszch").style.display = "none";
		document.getElementById("sfzh*").style.display = "none";
		document.getElementById("zzjgdm*").style.display = "none";
		document.getElementById("gszch*").style.display = "none";
	}
	if (value1 == '0') {
		document.getElementById("priceStart1").innerHTML = "";
		document.getElementById("priceEnd1").innerHTML = "";
		document.getElementById("type1").innerHTML = '';
		document.getElementById("zzjgdm").style.display = "";
		document.getElementById("zzjgdm*").style.display = "";
		document.getElementById("gszch").style.display = "none";
		document.getElementById("gszch*").style.display = "none";
		document.getElementById("sfzh*").style.display = "none";
	}
	if (value1 == '1') {
		document.getElementById("specificPrices1").innerHTML = "";

		document.getElementById("type1").innerHTML = '';
		document.getElementById("zzjgdm").style.display = "none";
		document.getElementById("zzjgdm*").style.display = "none";
		document.getElementById("gszch").style.display = "";
		document.getElementById("gszch*").style.display = "";
		document.getElementById("sfzh*").style.display = "none";
	}
	if (value1 == '2') {

		document.getElementById("specificPrices1").innerHTML = "";
		document.getElementById("priceStart1").innerHTML = "";
		document.getElementById("priceEnd1").innerHTML = "";

		document.getElementById("type1").innerHTML = '';
		document.getElementById("zzjgdm").style.display = "none";
		document.getElementById("gszch").style.display = "none";
		document.getElementById("zzjgdm*").style.display = "none";
		document.getElementById("gszch*").style.display = "none";
		document.getElementById("sfzh*").style.display = "";
	}
}

function checkType(inputId, spanId, errorTitle) {
    var value = $(inputId).val();
	alert(value);
    $(spanId).html("");
    if (value == "") {
        $(spanId).html(errorTitle + "不能为空");
        return false;
    } else if (value.replace(/(^\s*)|(\s*$)/g, "") == "") {
        $(spanId).html("不能全为空格");
        return false;
    } else {
        $(spanId).html(img);
        //设置请求方法
		alert(value);
        return true;
    }
}

function imgName() {
	var imgName = document.getElementById("serviceImgName").value.replace(
			/(^\s*)|(\s*$)/, "");
	if (imgName == "") {
		$("#imgName").html("");
		return true;
	} else {
		if (imgName.length > 64) {
			$("#imgName").html("图片名称不能超过 64个汉字！");
			return false;
		} else {
			$("#imgName").html(img);
			return true;
		}
	}
}
function selectBox() {
	var objs = document.getElementsByName("targetList");
	for (var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			$("#target").html(img)
			return true;
		} else {
			$("#target").html("服务对象不能为空");
		}
	}
}

function selectBox1() {
	var objs = document.getElementsByName("modeList");
	for (var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			$("#mode").html(img)
			return true;
		} else {
			$("#mode").html("服务方式不能为空");
		}
	}
}
function selectBox2() {
	var objs = document.getElementsByName("reqModeList");
	var flag = $("#boxOther").prop('checked');
	for (var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			if (objs[i].value == '07') {//其他被选中
				$("#others").show();
				return checkOther();
			} else { //其他未被选中
				$("#others").hide();
				//$("#others").attr("value","");
				$("#reqMode").html(img)
				return true;
			}
		} else {
			$("#reqMode").html("主要服务方式不能为空");
		}
	}
}
function selectBox3() {
	var objs = document.getElementsByName("mainServiceMode");
	var flag = $("#boxOther").prop('checked');
	for (var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			if (objs[i].value == '07') {//其他被选中
				$("#others").show();
				return checkOther();
			} else { //其他未被选中
				$("#others").hide();
				$("#others").attr("value", "");
				$("#reqMode").html(img)
				return true;
			}
		} else {
			$("#reqMode").html("主要服务方式不能为空");
		}
	}
}
//判断其他
function checkOther() {
	var others = $("#others").val();
	if (others == '') {
		$("#reqMode").html("您选择的主要服务方式为其他方式，请在输入框内填写详情，不大于15个字");
		return false;
	} else if (others.length > 15) {
		$("#reqMode").html("其他服务方式不能大于15个字");
		return false;
	} else {
		$("#reqMode").html(img)
		return true;
	}
}

function selectAllckdiv1(obj) {
	var allInput = document.getElementsByName("targetList");
	var loopTime = allInput.length;
	for (i = 0; i < loopTime; i++) {
		if (allInput[i].type == "checkbox") {
			allInput[i].checked = obj.checked;

			if (obj.checked) {
				document.getElementById("target").innerHTML = img;

			} else {
				document.getElementById("target").innerHTML = '请选择服务对象！';
			}

		}
	}
}

//判断标题
function checkTitle() {
	var text = $("#title").val();
	text = text.replace(/\s/g, "");
	$("#tit").html("");
	if (text.length > 60) {
		$("#tit").html("服务名称不能超过60个字");
		return false;
	} else if (text == '') {
		$("#tit").html("服务名称不能为空");
		return false;
	} else {
		$("#tit").html(img);
		return true;
	}
}
function checkst() {
	var text = $("#starttime").val();
	$("#st").html("");
	if (text == '') {
		$("#st").html("服务开始时间不能为空");
		return false;
	} else {
		checkdate();
		$("#st").html(img);
		return true;
	}
}
function checket() {
	var text = $("#endtime").val();
	$("#et").html("");
	if (text == '') {
		$("#et").html("服务结束时间不能为空");
		return false;
	} else {
		$("#et").html(img);
		checkdate();
		return true;
	}
}
function checkdate() {
	var starttime = $("#starttime").val().replace(/\-/g, '/');
	var endtime = $("#endtime").val().replace(/\-/g, '/');
	if (starttime > endtime) {
		$("#et").html("结束日期不能小于开始日期");
		return false;
	} else {
		$("#et").html(img);
		$("#st").html(img);
	}
	return true;
}
function checkNTitle() {
	var text = $("#ntitle").val();
	$("#ntit").html("");
	if (text.length > 60) {
		$("#ntit").html("服务名称不能超过60个字");
		return false;
	} else if (text == '') {
		$("#ntit").html("服务名称不能为空");
		return false;
	} else {
		$("#ntit").html(img);
		return true;
	}
}
function checkLinkMan() {
	var text = $("#linkman").val();
	var pattern = new RegExp("[`~%!@#^=''?~！@#￥……&——‘”“'？*()（），,。.、]");
	$("#link").html("");
	if (text.length > 32) {
		$("#link").html(" 联系人不能超过16个字");
		return false;
	} else if (pattern.test(text)) {
		$("#link").html(" 不能输入特殊符号");
		return false;
	} else if (text == '') {
		$("#link").html("联系人不能为空");
		return false;
	} else {
		$("#link").html(img);
		return true;
	}
}
//判断联系电话     
function checkPhone() {
	if (/(^1[34578]\d{9}$)|(\d{3}-|\d{4}-)(\d{8}|\d{7})$/.test($("#phone").val())) {
		$("#pho").html(img);
		return true;
	} else {
		$("#pho").html(
				" 电话不正确！格式：(固定电话)***(*)-*******(*)/(手机)*********** (11位)");
		updateParentIframeHeight();
		return false;
	}
}
//判断具体价格
function checkPrice() {
	var text = $("#specificPrices").val();
	var patten = /^([1-9]\d{0,9})$|^(0|[1-9]\d{0,9})\.(\d{1,2})$/;
	$("#specificPrices1").html("");
	if (text == "") {
		if (document.getElementById("type").value == '0') {
			$("#specificPrices1").html("具体价格不能为空");
			return false;
		}
	} else if (!patten.test(text)) {
		$("#specificPrices1").html("具体价格值不正确,请按提示填写");
		return false;
	} else if (text.length > 10) {
		$("#specificPrices1").html("具体价格值不能超过10位");
		return false;
	} else {
		$("#specificPrices1").html(img);
		return true;
	}
}
//判断价格区间开始
function checkPrice1() {
	var text = $("#priceStart").val();
	var patten = /^([1-9]\d{0,9})$|^(0|[1-9]\d{0,9})\.(\d{1,2})$/;
	$("#priceStart1").html("");
	if (text == "") {
		if (document.getElementById("type").value == '1') {
			$("#priceStart1").html("价格区间不能为空");
			return false;
		}
	} else if (!patten.test(text)) {
		$("#priceStart1").html("价格区间值不正确,请按提示填写");
		return false;
	} else if (text.length > 10) {
		$("#priceStart1").html("价格区间值不能超过10位");
		return false;
	} else {
		$("#priceStart1").html(img);
		return true;
	}
}
//判断价格区间结束
function checkPrice2() {
	var text = $("#priceEnd").val();
	var patten = /^([1-9]\d{0,9})$|^(0|[1-9]\d{0,9})\.(\d{1,2})$/;
	$("#priceEnd1").html("");
	if (text == "") {
		if (document.getElementById("type").value == '1') {
			$("#priceEnd1").html("价格区间不能为空");
			return false;
		}
	} else if (!patten.test(text)) {
		$("#priceEnd1").html("价格区间值不正确,请按提示填写");
		return false;
	} else if (text.length > 10) {
		$("#priceEnd1").html("价格区间值不能超过10位");
		return false;
	} else {
		$("#priceEnd1").html(img);
		return true;
	}
}
//判断邮箱
function checkEmail() {
	if (/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
			.test($("#email").val())) {
		$("#ema").html(img);
		return true;
	} else {
		$("#ema").html(" 邮箱格式不正确！");
		return false;
	}
}

function checkLength() {
	var text = $("#charge").val();
	if (text.length > 500) {
		$("#char").html(" 收费标准不能超过500个字");
		updateParentIframeHeight();
		return false;
	}  else {
		$("#char").html(img);
		return true;
	}
}
function checkNLength() {
	var text = $("#ncharge").val();
	if (text.length > 500) {
		$("#nchar").html(" 服务描述不能超过500个字");
		return false;
	} else if (text == '') {
		$("#nchar").html(" 服务描述不能为空");
		return false;
	} else {
		$("#nchar").html(img);
		return true;
	}
}

function checkserviceType2() {
	var bigType = $("#bigType").val();
	var smallType = $("#smallType").val();
	if (bigType == '' || smallType == '' || bigType == '请选择'
			|| smallType == '请选择') {
		$("#ser2").html("服务类别不能为空");
		return false;
	} else {
		$("#ser2").html(img);
		return true;
	}
}
//判断服务介绍
function checkLength1() {
	var text = UE.getEditor('editor').getContentTxt();
	$("#process").html("");
	if (text == "") {
		$("#process").html("服务介绍不能为空");
		return false;
	} else if (text.replace(/(^\s*)|(\s*$)/g, "") == "") {
		$("#process").html("不能全为空格");
		return false;
	} else {
		$("#process").html(img);
		return true;
	}
}
//判断不能为空
function checkRequired(inputId, spanId, errorTitle) {
    $(spanId).html("");
    if ($(inputId).val() == "") {
        $(spanId).html(errorTitle + "不能为空");
        return false;
    } else if ($(inputId).val().replace(/(^\s*)|(\s*$)/g, "") == "") {
        $(spanId).html("不能全为空格");
        return false;
    } else {
        $(spanId).html(img);
        return true;
    }
}

// //验证项目对接数
// function numRequired(inputId, spanId, errorTitle) {
// 	alert(1111)
//     $(spanId).html("");
//     if ($(inputId).val() == "") {
//         $(spanId).html(errorTitle + "不能为空");
//         return false;
//     } else if ($(inputId).val().replace(/(^\s*)|(\s*$)/g, "") == "") {
//         $(spanId).html("不能全为空格");
//         return false;
//     } else {
//         $(spanId).html(img);
//         return true;
//     }
// }

/*$(function() {
	$.ajax({
		type : "post",
		url : base + "/show.action?code=public_dic_parentbase",
		data : {
			dictableName : 'dic_service_type',
			supercode : $("#serviceType1").val()
		},
		success : function(data) {
			$("#serviceType1").html(data);
		}
	});

})*/
function subarea() {
	$.ajax({
		type : "post",
		url : base + "/show.action?code=public_dic_parentbase",
		data : {
			dictableName : 'dic_service_type',
			supercode : $("#serviceType1").val(),
			industry : $("#serviceType2").val()
		},
		success : function(data) {
			$("#serviceType2").html(data);
		}
	});
}
function subForm(obj) {
	var flag = true;
	if ($("#title").val() == "") {
		$("#tit").html(" 服务名称不能为空");
		flag = false;
	}
	if ($("#linkman").val() == "") {
		$("#link").html(" 联系人不能为空");
		flag = false;
	}
	if ($("#phone").val() == '') {
		$("#pho").html(" 联系电话不能为空");
		updateParentIframeHeight();
		flag = false;
	}
	if ($("#email").val() == '') {
		$("#ema").html(" 请填写邮箱！");
		flag = false;
	}
//	if ($("#charge").val() == '') {
//		$("#char").html(" 请填写收费标准！");
//		updateParentIframeHeight();
//		flag = false;
//	}
	if ($("#serviceType2").val() == '' || $("#serviceType2").val() == '请选择') {
		$("#ser2").html(" 请填写服务类别！");
		flag = false;
	}
	if ($("#type").val() == '' || $("#type").val() == '请选择') {
		$("#type2").html(" 请填写付款方式！");
		flag = false;
	}

	if (document.getElementById("type").value == '9') {
		document.getElementById("type1").innerHTML = '必须选择付款方式！'
		document.getElementById("type").focus();
		flag = false;
	}
	if (document.getElementById("type").value == '0') {
		if (!checkPrice()) {
			flag = false;
		}
	}
	if (document.getElementById("type").value == '1') {
		if (checkPrice1() && checkPrice2()) {
			var priceEnd110 = document.getElementById("priceEnd").value;
			var priceStart110 = document.getElementById("priceStart").value;
			if (priceEnd110 < priceStart110) {
				document.getElementById("priceEnd1").innerHTML = '价格上限不应小于价格下限！';
				document.getElementById("priceEnd").focus();
				flag = false;
			}
		} else {
			flag = false;
		}
	}
	if (flag == false) {
		return false;
	}
	if($("#specificPrices").val()!=''){
		document.getElementById("zzjgdm*").style.display = "none";
	}
	if($("#priceStart").val()!='' && $("#priceEnd").val()!=''){
		document.getElementById("gszch*").style.display = "none";
	}
	
	$("#auditStatus").attr("value", obj);
	if (checkTitle() && checkLinkMan() && checkPhone() && checkEmail()
			&& imgName() && checkLength() && checkserviceType2()
			&& checkLength1() && selectBox() && selectBox1() && selectBox2()
			&& imgName()) {
		
		$("#fm").submit();
		
		/*$("#fm").ajaxSubmit({
			type : 'POST',
			url : 'uploadImage.action',
			success : function(data) {
				if (data == '') {
					alert("操作成功!");
					
					window.location = get_omp_url()+'/show.action?code=org_ser_list';
				} else {
					alert(data);
					return false;
				}
			}
		});*/
	}
}
// 表单验证
function myForm() {
    var flag = true;
    if ($("#title").val() == "") {
        $("#tit").html(" 服务名称不能为空");
        flag = false;
    }
    if ($("#source").val() == "") {
        $("#sou").html(" 来源不能为空");
        flag = false;
    }
    if ($("#typeParent").val() == "") {
        $("#tpt").html(" 分类（大类）不能为空");
        flag = false;
    }
    if ($("#type").val() == "") {
        $("#typ").html(" 分类（小类）不能为空");
        flag = false;
    }
    var text = UE.getEditor('editor').getContentTxt();
    if (text == "") {
        $("#con").html(" 内容不能为空");
        flag = false;
    }else {
        $("#con").html(img);
	}
	// // // 开始时间
    // var starttime = $("#declareStartDate").val();
    // var endtime = $("#declareEndDate").val();
	//
    // if(starttime==''){
    //     $("#starttime1").html("活动开始时间不能为空");
    //     flag = false;
    // }else{
    //     if(endtime==''){
    //         $("#starttime1").html(img);
    //     }else{
    //         $("#starttime1").html("");
    //         var flags = checkdateTime();
    //         if(flags){
    //             $("#starttime1").html(img);
    //             $("#endtime1").html(img);
    //         }else{
    //             flag = false;
    //         }
    //     }
	//
    // }
    // // 结束时间
    // var endtime = $("#declareEndDate").val();
    // if(endtime==''){
    //     $("#endtime1").html("结束时间不能为空");
    //     flag = false;
    // }else{
    //     checkdateTime();
    //     if(flag){
    //         $("#endtime1").html(img);
    //     }
    // }
	// var starttime;
	// var endtime;
    // // 时间区间
	// if( typeof $("#declareStartDate").val() == undefined){
	// 	starttime = $("#declareStartDate").val().replace(/\-/g,'/');
	// 	endtime = $("#declareEndDate").val().replace(/\-/g,'/');
	// }
	//
    // if(starttime != '' && endtime != ''){
    //     if(starttime > endtime){
    //         $("#endtime1").html("结束日期不能小于开始日期");
    //         flag = false;
    //     }else{
    //         $("#endtime1").html(img);
    //         $("#starttime1").html(img);
    //     }
    // }
    if(flag){
		$("#form").submit();
	}

}