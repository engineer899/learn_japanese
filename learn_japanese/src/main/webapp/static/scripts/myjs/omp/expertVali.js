var Mphones = true;// 手机号
var passwords = true;// 密码
var usernames = true;// 用户名
var email = true;
var legalPerson = true;
var orgLinkman = true;
var orgLinkmanPhone = true;
var img = "<img src='" + base + "/omp/images/accept.png'>";

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
				$('#' + Elid1).html(img);
			} else {
				document.getElementById(Elid1).innerHTML = xmlhttp.responseText;
			}
		}
	}
	if (tag == true) {
		var valueName = $('#' + Elid).val();
		valueName = valueName.replace(/\s/g, "");// 去掉空字符
		// alert(valueName);
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
				url : base + '/expert/expert!checkName.action',
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
						$('#' + Elid1).html(img);
						usernames = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					usernames = false;
				}
			});
		} else if (num == '1') {
			var tel = /^([0-9]){11}$/;
			if (valueName == "") {
				$('#' + Elid1).html("手机号码不能为空");
				Mphones = false;
				return false;
			} else if (!tel.test(valueName)) {
				$('#' + Elid1).html("请输入正确的手机号码");
				Mphones = false;
				return false;
			}
			$.ajax({
				url : base + '/expert/expert!checkPhone.action',
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
						$('#' + Elid1).html(img);
						$("#tel").val($("#Mphone").val());
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
				url : base + '/expert/expert!checkEmail.action?email='
						+ $("#Memail").val(),
				type : 'post',
				dataType : 'text',
				async : false,
				success : function(msg) { // 相当于java中try语句块的用法
					if (msg != "") {
						$('#' + Elid1).html("该邮箱已经存在");
						email = false;
					} else {
						$('#' + Elid1).html(img);
						$("#email").val($("#Memail").val());
						email = true;
					}
				},
				error : function() {
					$('#' + Elid1).html("验证出错");
					email = false;
					return false;
				}
			});
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
				$('#' + Elid1).html(img);
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
				$('#' + Elid1).html(img);
				passwords = true;
			}
		}
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