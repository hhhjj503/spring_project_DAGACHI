<%@page import="java.util.function.Function"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>관리자 등록</title>
<link href="../resources/Admin_Detail_Page.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script>
function chk() {

	var d = document.forms.adminInsert;


	 //사용자ID
	 if(d.admin_Id.value.trim() == "") {
		 alert("사용자ID를 입력해 주세요.");
		d.admin_Id.focus();
		return false;
	}
	//아이디 중복확인
	else if(chkResult == false) {
		alert("아이디 중복확인을 해주세요");
		 return false;
	}
	//사용자명
	else if(d.admin_Name.value.trim() == "") {
		 alert("관리자명을 입력해 주세요.");
		d.admin_Name.focus();
		return false;
	}
	//암호
	else if(d.admin_Password.value.trim() == "") {
		 alert("암호를 입력해 주세요.");
		d.admin_Password.focus();
		return false;
	}
	//암호확인
	else if(d.admin_Passwordchk.value.trim() =="") {
		alert("암호확인란을 입력해주세요.")
		d.admin_Passwordchk.focus();
		return false;
		}
	//암호 , 암호확인 비교
	else if(d.admin_Password.value.trim() != d.admin_Passwordchk.value.trim()) {
		alert("암호와 암호확인이 동일하지 않습니다.")
		return false;
		}
	//E-mail
	else if(d.admin_Email.value.trim() == "") {
		 alert("E-mail을 입력해 주세요.");
		d.admin_Email.focus();
		return false;
	}
	//E-mail 중복확인
	else if(emailChkResult == false ){
		alert("Email 중복확인을 해주세요.");
		return false;
	}
	//연락처
	else if(d.admin_PhoneNumber.value.trim() == "") {
		 alert("연락처를 입력해 주세요.");
		d.admin_PhoneNumber.focus();
		return false;
	}
	//그룹
	else if(d.dept.value.trim() == "") {
		 alert("사용자 그룹을 입력해 주세요.");
		d.dept.focus();
		return false;
	}	

	else {
		alert("등록되었습니다.")
		}
}

function insert() {
	alert("등록되었습니다.")
}



//비밀번호확인
$(function() {
	$("#chksuccess").hide();
	$("#chkfail").hide();
	$("input").keyup(function() {
		var pwd1 = $("#admin_Password").val().replace(/ /g, '');
		var pwd2 = $("#admin_Passwordchk").val().replace(/ /g, '');
		if(pwd1.trim() == "" && pwd2.trim() == "") {
			$("#chksuccess").hide();
			$("#chkfail").hide();
		} else if(pwd1 != "" || pwd2 != "") {
				if(pwd1 == pwd2) {
						$("#chksuccess").show();
						$("#chkfail").hide();
					}
				else {
						$("#chksuccess").hide();
						$("#chkfail").show();
					}
				}
		});
});

//id 중복확인 div 숨기기(기본)
$(document).ready(function() {
	var id = $("div[name=keyChk]");
	
	$("div[name=keyChk]").hide();
	$("div[name=keyChkOk]").hide();
	
});
//id값 한글입력불가
$(document).ready(function(){
	  $("input[name=admin_Id]").keyup(function(event){ 
	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
	    var inputVal = $(this).val();
	    $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
	   }
	  });
	});
//가입시 id입력란 문자 변경될경우
$(function() {
	$("input[name=admin_Id]").keyup(function() {
			var inputVal = $(this).val();
		if(inputVal == "") {
			chkResult = false;
			$("div[name=keyChk]").hide();
			$("div[name=keyChkOk]").hide();
		} else{
			$("div[name=keyChk]").show();
			$("div[name=keyChkOk]").hide();
		}
		if(chkResult == true) {
			chkResult = false;
			alert("아이디가 변경되었습니다 중복확인을 다시진행해주세요");
			$("div[name=keyChk]").show();
			$("div[name=keyChkOk]").hide();
			}
	});
	$("input[name=admin_Id]").keyChange
});
//ID중복확인버튼
var chkResult = false;
function duplicateChk() {
	var chk = document.getElementById("admin_Id").value.trim();
	if(chk == "") {
		alert("사용자ID를 입력해주세요");
		$("input[name=admin_Id]").focus();
		return false;
	} else {
		$.ajax({
		url :'duplicateChk',
		type:'post',
		data: {admin_Id :chk},
		success: function(data) {
			if(data == 0) {
				alert("사용가능한 아이디 입니다");
				chkResult = true;
				$("div[name=keyChk]").hide();
				$("div[name=keyChkOk]").show();
				return true;
				} else {
				alert("중복된 아이디 입니다.");
				$("div[name=keyChk]").show();
				$("div[name=keyChkOk]").hide();
				chkResult = false;
				return false;
				}
			}
		});
	}
}

//email중복확인버튼
var emailChkResult = false;
function emailDuplicateChk() {
	var emailChk = document.getElementById("admin_Email").value.trim();
	if(emailChk == "") {
		alert("Email을 입력해주세요");
		$("input[name=admin_Email]").focus();
		return false;
	} else {
		$.ajax({
			url:'emailDuplicateChk',
			data: { admin_Email :emailChk},
			success: function(data) {
				if(data == 0) {
					alert("사용가능한 Email 입니다.");
					emailChkResult = true;
					} else {
					alert("중복된 Email 입니다.");
					emailChkResult = false;
					}
				}
			});
		}
}
//가입시 Email입력란 문자 변경될경우
$(function() {
	$("input[name=admin_Email]").keyup(function() {
			var emailInputVal = $(this).val();
		if(emailInputVal == "") {
			emailChkResult = false;
		}
		if(emailChkResult == true) {
			emailChkResult = false;
			alert("Email정보가 변경되었습니다 중복확인을 다시진행해주세요");
			}
	});
});
</script>
</head>
<body>
<div id="back">    
 
<div id="menu">
<ul class="main">
<li>회원관리
	<ul class="sub">
		<li><a href="#">공지사항</a></li>
		<li><a href="#">관리자 목록</a></li>
	</ul>
</li>
<li>가맹점 관리
	<ul class="sub">
		<li><a href="#">공지사항</a></li>
		<li><a href="#">관리자 목록</a></li>
	</ul>
</li>
<li>관리자 정보
	<ul class="sub">
		<li><a href="a_noticeList">공지사항</a></li>
		<li><a href="a_adminAccountList">관리자 목록</a></li>
	</ul>
</li>
</ul>
</div>

<img src="../resources/images/logo/capture.PNG" style="position: absolute;
margin-left: 45%;border-radius: 0px 0px 20px 20px;">

<img src="../resources/images/logo/KakaoTalk_20200821_105423387.jpg" style="position: absolute;
margin-left: 35%;border-radius: 150px 150px 150px 150px;position: absolute;
margin-top: 47%;z-index: 17;opacity: 1;background-color: #E98583;">

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" >
	
	
	<div id="pwdChk" style="position: absolute;margin-top: -2%;width: 250px;margin-left: 41%;margin-top: 40.8%;">
	<div id="chksuccess" name="chksuccess" style="position: absolute;color: green;">암호가 일치합니다</div>
	<div id="chkfail" name="chkfail" style="position: absolute;color: red;">암호가 동일하지 않습니다</div>
	</div>
	<div id="keyChk" name="keyChk" style="position: absolute;color: red;
	margin-left: 42%;margin-top: 17.2%;">영문과 숫자만 입력해주세요</div>
	<div id="keyChkOk" name="keyChkOk" style="position: absolute;color: green;
	margin-left: 42%;margin-top: 17.2%;">사용가능 아이디입니다</div>

<input id="idChk" type="button" style="background-color: #665F79;margin-left: 73%;margin-top:13.4%;
position:absolute;color: white;border-radius: 5px 5px 5px 5px;
padding-left: 10px;padding-right: 10px;"
value="중복확인" onclick="duplicateChk()">

<input id="emailChk" type="button" style="background-color: #665F79;margin-left: 73%;margin-top:45%;
position:absolute;color: white;border-radius: 5px 5px 5px 5px;
padding-left: 10px;padding-right: 10px;"
value="중복확인" onclick="emailDuplicateChk()">

<form id="adminInsert" action="adminWrite" method="post" onsubmit="return chk()">
	<table align="center" style="width: 500px;">
		<thead>
		<tr>
		<th border="2" colspan="2"
		style="background-color: #665F79;height: 50px;color: white;
		border-radius: 20px 20px 20px 20px;"><b>신규 관리자 등록</b></th>
		</tr>
		</thead>
		 
		<td align="left;">
		<p style="padding-right: 4px; padding-left: 4px;align: center;margin-left: 120px;margin-top: 22px;">
		<b>사용자 ID</b> <input type="text" id="admin_Id" name="admin_Id" style="height: 20px;" onclick="keyChk()" ><br/><br/>
		<b>관리자명</b> <input type="text" id="admin_Name" name="admin_Name" style="height: 20px;"> <br/><br/>
		<b>암호</b> <input type="password" id="admin_Password" name="admin_Password" style="height: 20px;"><br/><br/>
		<b>암호확인</b> <input type="password" id="admin_Passwordchk" name="admin_Passwordchk" style="height: 20px;">
		<br/><br/>
		<b>E-mail</b> <input type="text" id="admin_Email" name="admin_Email" style="height: 20px;width:190px;"> <br/><br/>
		<b>연락처</b> <input type="text" id="admin_PhoneNumber" name="admin_PhoneNumber" style="height: 20px;" maxlength="10"> <br/><br/>
		<b>사용 여부</b>   <select id="admin_Author" name="admin_Author" style="vertical-align: middle;padding-bottom:3px;" >	
		<option id="employee" value="사원" style="height: 20px;">사원</option>
		<option id="supervisor" value="슈퍼바이저" style="height: 20px;">슈퍼바이저</option>
		</select><br/><br/>
		<b>사용자 그룹</b> <input type="text" id="dept" name="dept" style="height: 20px;">
		</p>
		
		<p align="center" style="margin-top: -14px;">
		<br/>
		<input type="submit" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="등록" >
		<input type="button" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="취소" onclick="history.go(-1)">
		</p>
		</td>
			
	</table>
</form>
</div>
</div>
</div>
</body>
</html>