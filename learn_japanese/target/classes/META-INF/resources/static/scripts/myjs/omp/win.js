var base = "http://" + window.location.host;
/*var img = "<img src=\'" + base + "/omp/omp/images/accept.png\'>";*/
var img = "<img src=\'"+base+"/zhirong.cas/static/images/accept.png\'>";
var usernames = true;
var phones = true;
var passwords = true;
var conpwds = true;
var names = true;
var identifications = true;
var emails = true;
var winNames = true;
var winCodes = true;
var legalPersons = true;
var winLinkmans = true;
var winLinkmanPhones = true;
var winFaxs = true;
var winEmails = true;
var winWebs = true;
var winAddresss = true;
var winPostcodes = true;
var winDescriptions = true;
var winOrgtypes = true;
var winArea2s = true;
var winTypes = true;
var qqs = true;
//判断字符串长度，汉字占两个字符
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
function checkName(usreId) {
	var username = $("#username").val();
	var id = $("#username").attr("defaultValue").replace(/(^\s*)|(\s*$)/, "");
	var name = /^([a-zA-Z0-9]){4,16}$/;
	if (username == "") {
		$("#sp1").css("color", "#ff0000");
		$("#sp1").html("请输入用户名");
		if ($("#sp1").html() == img) {
			$("#sp1").css("color", "#ff0000");
			$("#sp1").html("请输入用户名");
		}
		usernames = false;
		return false;
	}
	if (!name.test(username)) {
		$("#sp1").css("color", "#ff0000");
		$("#sp1").html("请输入正确的用户名");
		if ($("#sp1").html() == img) {
			$("#sp1").css("color", "#ff0000");
			$("#sp1").html("请输入正确的用户名");
		}
		usernames = false;
		return false;
	}
	$.ajax({
		type : 'post',
		url : '/zhirong.cas/reg/checkUserName?username=' + username
				+ '&id=' + id,
		async : false,
		success : function(msg) {
			var msg1='该手邮箱已存在';
			var msg2='未做修改';
			if (msg != "") {
				if(msg == usreId){
					$("#sp1").html(msg2);
					usernames = true;
				 }else{
					 $("#sp1").css("color", "#ff0000");
						$("#sp1").html(msg);
						if ($("#sp1").html() == img) {
							$("#sp1").css("color", "#ff0000");
							$("#sp1").html(msg1);
						}
						usernames = false;
						return false;
				 }
				
			} else{
				$("#sp1").html(img);
				usernames = true;
			}
		},
		error : function() {
			$("#sp1").css("color", "#ff0000");
			$("#sp1").html("验证出错");
			if ($("#sp1").html() == img) {
				$("#sp1").css("color", "#ff0000");
				$("#sp1").html("验证出错");
			}
			usernames = false;
			return false;
		}
	})
}

function checkPhone() {
	var phone = $("#phone").val();
	var id = $("#phone").attr("defaultValue").replace(/(^\s*)|(\s*$)/, "");
	var tel = /^1[3|4|5|6|7|8|9][0-9]\d{8}$/;
	if (phone == "") {
		$("#sp2").css("color", "#ff0000");
		$("#sp2").html("请输入手机号码");
		if ($("#sp2").html() == img) {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("请输入手机号码");
		}
		phones = false;
		return false;
	}
	if (!tel.test(phone)) {
		$("#sp2").css("color", "#ff0000");
		$("#sp2").html("请输入正确的手机号码");
		if ($("#sp2").html() == img) {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("请输入正确的手机号码");
		}
		phones = false;
		return false;
	}
	$.ajax({
		type : 'post',
		url : '/zhirong.cas/prov/checkPhone.action?phone=' + phone
				+ '&id=' + id,
		async : false,
		success : function(msg) {
			if (msg != "") {
				$("#sp2").css("color", "#ff0000");
				$("#sp2").html(msg);
				if ($("#sp2").html() == img) {
					$("#sp2").css("color", "#ff0000");
					$("#sp2").html(msg);
				}
				phones = false;
				return false;
			} else {
				$("#sp2").html(img);
				phones = true;
			}
		},
		error : function() {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("验证出错");
			if ($("#sp2").html() == img) {
				$("#sp2").css("color", "#ff0000");
				$("#sp2").html("验证出错");
			}
			phones = false;
			return false;
		}
	})
}
function checkPhone1() {
	var phone = $("#phone").val();
	var id = $("#phone").attr("defaultValue").replace(/(^\s*)|(\s*$)/, "");
	var tel = /^1[3|4|5|6|7|8|9][0-9]\d{8}$/;
	if (phone == "") {
		$("#sp2").css("color", "#ff0000");
		$("#sp2").html("请输入手机号码");
		if ($("#sp2").html() == img) {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("请输入手机号码");
		}
		phones = false;
		return false;
	}
	if (!tel.test(phone)) {
		$("#sp2").css("color", "#ff0000");
		$("#sp2").html("请输入正确的手机号码");
		if ($("#sp2").html() == img) {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("请输入正确的手机号码");
		}
		phones = false;
		return false;
	}
	$.ajax({
		type : 'post',
		url : '/zhirong.cas/prov/checkPhone?phone=' + phone
				+ '&id=' + id,
		async : false,
		success : function(msg) {
			if (msg != "") {
				$("#sp2").css("color", "#ff0000");
				$("#sp2").html(msg);
				if ($("#sp2").html() == img) {
					$("#sp2").css("color", "#ff0000");
					$("#sp2").html(msg);
				}
				phones = false;
				return false;
			} else {
				$("#sp2").html(img);
				$("#winLinkmanPhone").val(phone);
				phones = true;
			}
		},
		error : function() {
			$("#sp2").css("color", "#ff0000");
			$("#sp2").html("验证出错");
			if ($("#sp2").html() == img) {
				$("#sp2").css("color", "#ff0000");
				$("#sp2").html("验证出错");
			}
			phones = false;
			return false;
		}
	})
}
function checkPwd() {
	var password = $("#password").val();
	var pwd = /^([a-zA-Z0-9]){6,16}$/;
	if (password == "") {
		$("#sp3").css("color", "#ff0000");
		$("#sp3").html("请输入密码");
		if ($("#sp3").html() == img) {
			$("#sp3").css("color", "#ff0000");
			$("#sp3").html("请输入密码");
		}
		passwords = false;
		return false;
	}
	if (!pwd.test(password)) {
		$("#sp3").css("color", "#ff0000");
		$("#sp3").html("请输入6-16位数字或者字母");
		if ($("#sp3").html() == img) {
			$("#sp3").css("color", "#ff0000");
			$("#sp3").html("请输入6-16位数字或者字母");
		}
		passwords = false;
		return false;
	} else {
		$("#sp3").html(img);
		passwords = true;
	}
}

function confirmPwd() {
	var conpwd = $("#repwd").val();
	var pwd = /^([a-zA-Z0-9]){6,16}$/;
	if (conpwd == "") {
		$("#sp4").css("color", "#ff0000");
		$("#sp4").html("请输入确认密码");
		if ($("#sp4").html() == img) {
			$("#sp4").css("color", "#ff0000");
			$("#sp4").html("请输入确认密码");
		}
		conpwds = false;
		return false;
	}
	if (!pwd.test(conpwd)) {
		$("#sp4").css("color", "#ff0000");
		$("#sp4").html("请输入6-16位数字或者字母");
		if ($("#sp4").html() == img) {
			$("#sp4").css("color", "#ff0000");
			$("#sp4").html("请输入6-16位数字或者字母");
		}
		passwords = false;
		return false;
	}
	var password = $("#password").val();
	if (conpwd != password) {
		$("#sp4").css("color", "#ff0000");
		$("#sp4").html("两次密码输入不一致");
		if ($("#sp4").html() == img) {
			$("#sp4").css("color", "#ff0000");
			$("#sp4").html("两次密码输入不一致");
		}
		conpwds = false;
		return false;
	} else {
		$("#sp4").html(img);
		conpwds = true;
	}
}

function checkTrueName() {
	var name = $('#name').val().replace(/(^\s*)|(\s*$)/, "");
	var re = /^[^\|"'<>|@|~|`|!|#|$|^|&|*|()|=|||{}|:|;|，|,|￥|……|&|*|（）|——|【】|‘|；|：|”|“|。|、|？|\\|//|\[|\]]*$/;
	if (name == "") {
		$("#sp5").css("color", "#ff0000");
		$('#sp5').html("请输入姓名");
		if ($("#sp5").html() == img) {
			$("#sp5").css("color", "#ff0000");
			$("#sp5").html("请输入姓名");
		}
		names = false;
		return false;
	}
	if (!re.test(name)) {
		$("#sp5").css("color", "#ff0000");
		$('#sp5').html("请输入正确的姓名,不能包含特殊字符");
		if ($("#sp5").html() == img) {
			$("#sp5").css("color", "#ff0000");
			$("#sp5").html("请输入正确的姓名,不能包含特殊字符");
		}
		names = false;
		return false;
	} else {
		if (name.length > 12 || name.length < 2) {
			$('#sp5').css("color", "#ff0000");
			$('#sp5').html("姓名长度应在2-12个字符之间");
			if ($("#sp5").html() == img) {
				$("#sp5").css("color", "#ff0000");
				$("#sp5").html("姓名长度应在2-12个字符之间");
			}
			names = false;
			return false;
		} else {
			$('#sp5').html(img);
			names = true;
		}
	}
}
function checkTrueName1() {
	var name = $('#name').val().replace(/(^\s*)|(\s*$)/, "");
	var re = /^([\u4E00-\u9FA5]|\w)*$/;
	if (name == "") {
		$("#sp5").css("color", "#ff0000");
		$('#sp5').html("请输入姓名");
		if ($("#sp5").html() == img) {
			$("#sp5").css("color", "#ff0000");
			$("#sp5").html("请输入姓名");
		}
		names = false;
		return false;
	}
	if (!re.test(name)) {
		$("#sp5").css("color", "#ff0000");
		$('#sp5').html("请输入正确的姓名,不能包含特殊字符");
		if ($("#sp5").html() == img) {
			$("#sp5").css("color", "#ff0000");
			$("#sp5").html("请输入正确的姓名,不能包含特殊字符");
		}
		names = false;
		return false;
	} else {
		if (name.length > 12 || name.length < 2) {
			$('#sp5').css("color", "#ff0000");
			$('#sp5').html("姓名长度应在2-12个字符之间");
			if ($("#sp5").html() == img) {
				$("#sp5").css("color", "#ff0000");
				$("#sp5").html("姓名长度应在2-12个字符之间");
			}
			names = false;
			return false;
		} else {
			$('#sp5').html(img);
			$('#winLinkman').val(name);
			names = true;
		}
	}
}
function checkIdentify() {
	var identification = $("#identification").val();
	var id = $("#identification").attr("defaultValue").replace(/(^\s*)|(\s*$)/,
			"");
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if (identification == "") {
		$('#sp6').css("color", "#ff0000");
		$('#sp6').html("请输入身份证号");
		if ($("#sp6").html() == img) {
			$("#sp6").css("color", "#ff0000");
			$("#sp6").html("请输入身份证号");
		}
		identifications = false;
		return false;
	} else if (!reg.test(identification)) {
		$('#sp6').css("color", "#ff0000");
		$('#sp6').html("请输入正确的身份证号");
		if ($("#sp6").html() == img) {
			$("#sp6").css("color", "#ff0000");
			$("#sp6").html("请输入正确的身份证号");
		}
		identifications = false;
		return false;
	} else {
		$
				.ajax({
					type : 'post',
					url : '/zhirong.cas/prov/checkIdentification.action?identification='
							+ identification + '&id=' + id,
					async : false,
					success : function(msg) {
						if (msg != "") {
							$("#sp6").css("color", "#ff0000");
							$("#sp6").html(msg);
							if ($("#sp6").html() == img) {
								$("#sp6").css("color", "#ff0000");
								$("#sp6").html(msg);
							}
							identifications = false;
							return false;
						} else {
							$('#sp6').html(img);
							identifications = true;
						}
					},
					error : function() {
						$("#sp6").css("color", "#ff0000");
						$("#sp6").html("验证出错");
						if ($("#sp6").html() == img) {
							$("#sp6").css("color", "#ff0000");
							$("#sp6").html("验证出错");
						}
						identifications = false;
						return false;
					}
				})
	}
}

function checkEmail() {
	var myEmail = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var id = $("#email").attr("defaultValue").replace(/(^\s*)|(\s*$)/, "");
	if ($("#email").val() == "") {
		$('#sp7').css("color", "#ff0000");
		$('#sp7').html("请输入邮箱");
		if ($("#sp7").html() == img) {
			$("#sp7").css("color", "#ff0000");
			$("#sp7").html("请输入邮箱");
		}
		emails = false;
		return false;
	} else if (!myEmail.test($("#email").val())) {
		$('#sp7').css("color", "#ff0000");
		$('#sp7').html("请输入正确的邮箱");
		if ($("#sp7").html() == img) {
			$("#sp7").css("color", "#ff0000");
			$("#sp7").html("请输入正确的邮箱");
		}
		emails = false;
		return false;
	} else {
		$.ajax({
			type : 'post',
			url : '/zhirong.cas/prov/checkEmail.action?email='
					+ $("#email").val() + '&id=' + id,
			async : false,
			success : function(msg) {
				if (msg != "") {
					$("#sp7").css("color", "#ff0000");
					$("#sp7").html(msg);
					if ($("#sp7").html() == img) {
						$("#sp7").css("color", "#ff0000");
						$("#sp7").html(msg);
					}
					emails = false;
					return false;
				} else {
					$('#sp7').html(img);
					emails = true;
				}
			},
			error : function() {
				$("#sp7").css("color", "#ff0000");
				$("#sp7").html("验证出错");
				if ($("#sp7").html() == img) {
					$("#sp7").css("color", "#ff0000");
					$("#sp7").html("验证出错");
				}
				emails = false;
				return false;
			}
		})
	}
}

function checkEmail1() {
	var myEmail = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var id = $("#email").attr("defaultValue").replace(/(^\s*)|(\s*$)/, "");
	if ($("#email").val() == "") {
		$('#sp7').css("color", "#ff0000");
		$('#sp7').html("请输入邮箱");
		if ($("#sp7").html() == img) {
			$("#sp7").css("color", "#ff0000");
			$("#sp7").html("请输入邮箱");
		}
		emails = false;
		return false;
	} else if (!myEmail.test($("#email").val())) {
		$('#sp7').css("color", "#ff0000");
		$('#sp7').html("请输入正确的邮箱");
		if ($("#sp7").html() == img) {
			$("#sp7").css("color", "#ff0000");
			$("#sp7").html("请输入正确的邮箱");
		}
		emails = false;
		return false;
	} else {
		$.ajax({
			type : 'post',
			url : '/zhirong.cas/reg/checkUserEmail?email='
					+ $("#email").val() + '&id=' + id,
			async : false,
			success : function(msg) {
				if (msg != "") {
					$("#sp7").css("color", "#ff0000");
					$("#sp7").html(msg);
					if ($("#sp7").html() == img) {
						$("#sp7").css("color", "#ff0000");
						$("#sp7").html(msg);
					}
					emails = false;
					return false;
				} else {
					$('#sp7').html(img);
					$('#winEmail').val($("#email").val());
					emails = true;
				}
			},
			error : function() {
				$("#sp7").css("color", "#ff0000");
				$("#sp7").html("验证出错");
				if ($("#sp7").html() == img) {
					$("#sp7").css("color", "#ff0000");
					$("#sp7").html("验证出错");
				}
				emails = false;
				return false;
			}
		})
	}
}

function checkAll(flag, obj, spi) {
	if (flag == 1) {
		var winId = $("#" + obj.id).attr("defaultValue").replace(
				/(^\s*)|(\s*$)/, "");
		var winName = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^([\u4E00-\u9FA5]|\w)*$/;
		if (winName == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("窗口平台名称不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("窗口名称不能为空");
			}
			winNames = false;
			return false;
		} else if (!re.test(winName)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("窗口平台名称不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("窗口名称不能包含特殊字符");
			}
			winNames = false;
			return false;
		} else if (getByteLen(winName) > 100) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("窗口平台名称不能超过50个汉字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("窗口名称不能超过50个汉字");
			}
			winNames = false;
			return false;
		} else {
			$.ajax({
				type : 'post',
				url : '/zhirong.cas/prov/checkWinName.action',
				data:{
					winId:winId,
					winName:winName
				},
				async : false,
				success : function(msg) {
					if (msg != "") {
						$("#" + spi).css("color", "#ff0000");
						$("#" + spi).html(msg);
						if ($("#" + spi).html() == img) {
							$("#" + spi).css("color", "#ff0000");
							$("#" + spi).html(msg);
						}
						winNames = false;
						return false;
					} else {
						$("#" + spi).html(img);
						winNames = true;
					}
				},
				error : function() {
					$("#" + spi).css("color", "#ff0000");
					$("#" + spi).html("验证出错");
					if ($("#" + spi).html() == img) {
						$("#" + spi).css("color", "#ff0000");
						$("#" + spi).html("验证出错");
					}
					winNames = false;
					return false;
				}
			})
		}
	}
	if (flag == 2) {
		var orgName = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^([\u4E00-\u9FA5]|\w)*$/;
		if (orgName == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("");
			}
			orgNames = true;
		} else if (!re.test(orgName)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("承建单位名称不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("承建单位名称不能包含特殊字符");
			}
			orgNames = false;
			return false;
		} else if (getByteLen(orgName) > 100) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("承建单位名称不能超过50个汉字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("承建单位名称不能超过50个汉字");
			}
			orgNames = false;
			return false;
		} else {
			$("#" + spi).html(img);
			orgNames = true;
		}
	}
	if (flag == 3) {
		var winId = $("#" + obj.id).attr("defaultValue").replace(
				/(^\s*)|(\s*$)/, "");
		//var code = /^\d{9}$/;
		var code = /^[0-9A-Z]+$/;
		var winCode = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		if (winCode == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("组织机构代码不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("组织机构代码不能为空");
			}
			winCodes = false;
			return false;
		} else if(getByteLen(winCode)>64 || !code.test(winCode)){
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("组织机构代码长度为32个字符且只可为数字或大写拉丁字母");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("组织机构代码长度为32个字符且只可为数字或大写拉丁字母");
			}
			winCodes = false;
			return false;
		/*} else if (!code.test(winCode)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("组织机构代码只可为数字或大写拉丁字母");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("组织机构代码只可为数字或大写拉丁字母");
			}
			winCodes = false;
			return false;*/
		} else {
			$.ajax({
				type : 'post',
				url : '/zhirong.cas/prov/checkWinCode.action?id='
						+ winId + '&winCode=' + winCode,
				async : false,
				success : function(msg) {
					if (msg != "") {
						$("#" + spi).css("color", "#ff0000");
						$("#" + spi).html(msg);
						if ($("#" + spi).html() == img) {
							$("#" + spi).css("color", "#ff0000");
							$("#" + spi).html(msg);
						}
						winCodes = false;
						return false;
					} else {
						$("#" + spi).html(img);
						winCodes = true;
					}
				},
				error : function() {
					$("#" + spi).css("color", "#ff0000");
					$("#" + spi).html("验证出错");
					if ($("#" + spi).html() == img) {
						$("#" + spi).css("color", "#ff0000");
						$("#" + spi).html("验证出错");
					}
					winCodes = false;
					return false;
				}
			})
		}
	}
	if (flag == 4) {
		var legalPerson = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^[^\|"'<>|@|~|`|!|#|$|^|&|*|()|=|||{}|:|;|，|,|￥|……|&|*|（）|——|【】|‘|；|：|”|“|。|、|？|\\|//|\[|\]]*$/;
		if (legalPerson == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("法人代表不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("法人代表不能为空");
			}
			legalPersons = false;
			return false;
		} else if (!re.test(legalPerson)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("法人代表不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("法人代表不能包含特殊字符");
			}
			legalPersons = false;
			return false;
		} else if (getByteLen(legalPerson) > 32) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("法人代表字数不能超过16个汉字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("法人代表字数不能超过16个汉字");
			}
			legalPersons = false;
			return false;
		} else {
			$("#" + spi).html(img);
			legalPersons = true;
		}
	}
	if (flag == 5) {
		var winLinkman = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^[^\|"'<>|@|~|`|!|#|$|^|&|*|()|=|||{}|:|;|，|,|￥|……|&|*|（）|——|【】|‘|；|：|”|“|。|、|？|\\|//|\[|\]]*$/;
		if (winLinkman == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("联系人不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("联系人不能为空");
			}
			winLinkmans = false;
			return false;
		} else if (!re.test(winLinkman)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("联系人不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("联系人不能包含特殊字符");
			}
			winLinkmans = false;
			return false;
		} else if (getByteLen(winLinkman) > 32) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("联系人字数不能超过16个汉字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("联系人字数不能超过16个汉字");
			}
			winLinkmans = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winLinkmans = true;
		}
	}
	if (flag == 6) {
		var winLinkmanPhone = $("#" + obj.id).val()
				.replace(/(^\s*)|(\s*$)/, "");
		var phone = /^(0[0-9]{2,3}-)?([1-9][0-9]{6,7})+(-[0-9]{1,4})?$|(^1[3|4|5|6|7|8|9][0-9]\d{8}$)/;
		if (winLinkmanPhone == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("联系电话不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("联系电话不能为空");
			}
			winLinkmanPhones = false;
			return false;
		} else if (!phone.test(winLinkmanPhone)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi)
					.html(
							"联系电话格式不正确：（座机）须符合格式：xxx(x)-xxxxxxx(x)，（手机）须符合格式：xxxxxxxxxxx");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi)
						.html(
								"联系电话格式不正确：（座机）须符合格式：xxx(x)-xxxxxxx(x)，（手机）须符合格式：xxxxxxxxxxx");
			}
			winLinkmanPhones = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winLinkmanPhones = true;
		}
	}
	if (flag == 7) {
		var winFax = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var winFax1 = /^\d{3,4}-\d{7,8}$/;
		if (winFax == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("");
			}
			winFaxs = true;
		} else if (!winFax1.test(winFax)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("传真须符合格式：xxx(x)-xxxxxxx(x)");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("传真须符合格式：xxx(x)-xxxxxxx(x)");
			}
			winFaxs = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winFaxs = true;
		}
	}
	if (flag == 8) {
		var winEmail = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var myEmail = /^([a-zA-Z0-9]+[-|\_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if (winEmail == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("邮箱不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("邮箱不能为空");
			}
			winEmails = false;
			return false;
		} else if (!myEmail.test(winEmail)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("请输入正确的邮箱");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("请输入正确的邮箱");
			}
			winEmails = false;
			return false;
		} else {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html(img);
			winEmails = true;
		}
	}
	if (flag == 9) {
		var winWeb = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var exp = /^([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
		if (winWeb == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("");
			}
			winWebs = true;
		} else if (!exp.test(winWeb)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("请输入正确的网址");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("请输入正确的网址");
			}
			winWebs = false;
			return false;
		} else if (getByteLen(winWeb) > 128) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("网址不大于128个字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("网址不大于128个字符");
			}
			winWebs = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winWebs = true;
		}
	}
	if (flag == 10) {
		var winAddress = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^([\u4E00-\u9FA5]|\w)*$/;
		if (winAddress == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("通讯地址不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("通讯地址不能为空");
			}
			winAddresss = false;
			return false;
		} else if (!re.test(winAddress)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("通讯地址不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("通讯地址不能包含特殊字符");
			}
			winAddresss = false;
			return false;
		} else if (getByteLen(winAddress) > 256) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("通讯地址只能输入128个字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("通讯地址只能输入128个字");
			}
			winAddresss = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winAddresss = true;
		}
	}
	if (flag == 11) {
		var winPostcode = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var exp = /^[1-9][0-9]{5}$/;
		if (winPostcode == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("");
			}
			winPostcodes = true;
		} else if (!exp.test(winPostcode)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("请输入正确的邮政编码");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("请输入正确的邮政编码");
			}
			winPostcodes = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winPostcodes = true;
		}
	}
	if (flag == 12) {
		var winDescription = $("#" + obj).val().replace(/(^\s*)|(\s*$)/, "");
		if (winDescription == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("平台介绍不能为空");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("平台介绍不能为空");
			}
			winDescriptions = false;
			return false;
		} else if (getByteLen(winDescription) > 1000) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("只能输入500字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("只能输入500字");
			}
			winDescriptions = false;
			return false;
		} else {
			$("#" + spi).html(img);
			winDescriptions = true;
		}
	}
	if (flag == 13) {
		var qq = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
		var re = /^[^\|"'<>|@|~|`|!|#|$|^|&|*|()|=|||{}|:|;|，|,|￥|……|&|*|（）|——|【】|‘|；|：|”|“|。|、|？|\\|//|\[|\]]*$/;
		var qqva = /^[0-9]*$/;
		if (qq == "") {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("");
			}
			qqs = true;
		} else if (!re.test(qq)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("输入内容不能包含特殊字符");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("输入内容不能包含特殊字符");
			}
			qqs = false;
			return false;
		} else if (!qqva.test(qq)) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("QQ号码必须是数字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("QQ号码必须是数字");
			}
			qqs = false;
			return false;
		} else if (getByteLen(qq) > 13 || getByteLen(qq) < 4) {
			$("#" + spi).css("color", "#ff0000");
			$("#" + spi).html("QQ号码应为4-13位数字");
			if ($("#" + spi).html() == img) {
				$("#" + spi).css("color", "#ff0000");
				$("#" + spi).html("QQ号码应为4-13位数字");
			}
			qqs = false;
			return false;
		} else {
			$("#" + spi).html(img);
			qqs = true;
		}
	}
}

function areaChange(tag, obj) {
	var op = "<option value=''>请选择</option>";
	if (obj == "") {
		$("#winArea2").html(op);
		$("#sp13").css("color", "#ff0000");
		$("#sp13").html("请选择");
		areas = true;
	} else {
		$.ajax({
			type : "post",
			url : base + "/zhirong.cas/prov/County.action",
			dataType : "html",
			data : {
				code : 'area_select',
				superCode : obj
			},
			success : function(data) {
				var data=JSON.parse(data);
				var st="<option value=''>请选择</option>";
				for(var i=0;i<data.length;i++){
					st+='<option value="'+data[i].AREA_CODE+'">'+data[i].AREA_NAME+'</option>;';
				}
				$('#winArea2').html(st);
				//$('#winArea2').html(data);
				areas = false;
			}
		});
	}
}

function addImg(obj, spi) {
	var select_value = $("#" + obj.id).val().replace(/(^\s*)|(\s*$)/, "");
	if (select_value == "") {
		$("#" + spi).css("color", "#ff0000");
		$("#" + spi).html("请选择");
		if (spi == 'sp10') {
			winOrgtypes = false;
		}
		if (spi == 'sp13') {
			winArea2s = false;
		}
		if (spi == 'sp21') {
			winTypes = false;
		}
		return false;
	} else {
		$("#" + spi).html(img);
		if (spi == 'sp10') {
			winOrgtypes = true;
		}
		if (spi == 'sp13') {
			winArea2s = true;
		}
		if (spi == 'sp21') {
			winTypes = true;
		}
	}
}
