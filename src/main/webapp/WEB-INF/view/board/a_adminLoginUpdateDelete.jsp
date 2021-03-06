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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<script>

//alert check function
function chk() {

	var d = document.forms.adminUpdate;
	
	 //사용자명
	 if(d.admin_Name.value.trim() == "") {
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
	//E-mail
	else if(d.admin_Email.value.trim() == "") {
		 alert("E-mail을 입력해 주세요.");
		d.admin_Email.focus();
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
		alert("수정되었습니다.")
		}
}


function num() {
	var d = document.forms.adminUpdate;
	var dd = $("input[name=admin_Id]").val();
	var nn = $("input[name=admin_Num]").val();
	
	alert("아이디 " + dd + " 님의 관리자 번호는 "+ nn +" 번 입니다.");
}

function del() {

	var num = $("input[name=admin_Num]").val();
	var html ="";
	if(confirm("회원탈퇴 하시겠습니까?") == true) {
		 html += "<input type='password' id='delAdmin' name='delAdmin' style='margin-left:15px;margin-top:10px;'/>";
		 html += "<button id='delOk' >확인</button>";
		 html += "<button id='delClose' >닫기</button>";
		 $("#delAdminForm").html(html);
		 html ="";
	}
}

</script>

</head>
<body>

<c:if test="${log_In_Admin == null }" >
	<script>
	alert("로그인 후 이용하실 수 있습니다.");
	window.location.href="a_adminLogin";
	</script>	
</c:if>

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


<div id="profile" style="z-index: 10;width: 400px;height: 400px;background-color: white;
border-radius: 20px 20px 20px 20px;position: absolute;margin-left: 2.5%;margin-top: 12%">
<img alt="profileImage" src="${profile}" width="350px" height="350px" border="1"
style="margin-left: 25px;margin-top: 10px;">	

<form action="adminProfile" method="post" enctype="multipart/form-data" >
	<input type="file" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;width: 200px;margin-left: 25px;
			padding-top: 3px;padding-bottom: 3px" name="file">
	<input type="hidden" value="${log_In_Admin.admin_Id}" name="admin_Id">
	<input type="submit" value="업로드" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;">
	<input type="button" value="삭제" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			onclick="location.href='adminProfileDel?admin_Id=${log_In_Admin.admin_Id}'">
</form>

</div>

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" >

<form id="adminUpdate" action="adminUpdate" method="post" onsubmit="return chk()">
	<table align="center" style="width: 500px;">
		<thead>
		<tr>
		<tr>
		<th border="2" colspan="2"
		style="background-color: #665F79;height: 50px;color: white;
		border-radius: 20px 20px 20px 20px;"><b>관리자 정보 수정</b></th>
		</tr>
		</tr>
		</thead>
		
		<td align="left;" >
		<p style="padding-right: 4px; padding-left: 4px;align: center;margin-left: 120px;">
		<b>사용자 ID : </b> <input type="text" id="admin_Id"name="admin_Id" value="${log_In_Admin.admin_Id}" readonly="readonly"
		style="background-color: #D0CECD;height: 20px;background: none;border: none;outline: none;font-size:medium;" />
		
		<!-- 관리자 번호 -->
		<input type="text" id="admin_Num" name="admin_Num"value="${log_In_Admin.admin_Num}"
		style="width: 25px;background-color: #D0CECD;text-align: center;" readonly="readonly" onclick="num()" /><br/><br/>
		
		<b>관리자명 :</b> <input type="text" id="admin_Name" name="admin_Name" style="height: 20px;background: none;border: none;outline: none;"
		value="${log_In_Admin.admin_Name}" readonly="readonly"> <br/><br/>
		<b>암호</b> <input type="password" id="admin_Password" name="admin_Password" style="height: 20px;" value="${log_In_Admin.admin_Password}"><br/><br/>
		<b>E-mail</b> <input type="text" id="admin_Email" name="admin_Email" style="height: 20px;" value="${log_In_Admin.admin_Email}"> <br/><br/>
		<b>연락처</b> <input type="text" id="admin_PhoneNumber" name="admin_PhoneNumber" style="height: 20px;" value="${log_In_Admin.admin_PhoneNumber}"><br/><br/>
		<b>사용 여부</b>
		<select id="admin_Author" name="admin_Author" style="vertical-align: middle;padding-bottom:3px;height: 20px;" >	
		<option id="employee" value="사원" style="height: 20px;">사원</option>
		<option id="supervisor" value="슈퍼바이저" style="height: 20px;">슈퍼바이저</option>
		</select><br/><br/>
		<b>사용자 그룹</b> <input type="text" style="height: 20px;" value="${log_In_Admin.dept}" name="dept" >
		<br/><br/>	
			<p align="center">
		<input type="submit" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="수정">
			
		<input type="button" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="탈퇴" name="delete" onclick="del()">
			
		<input type="button" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="취소" onclick="location.href='a_noticeList'"></td>
			</p>
		</p>
	</table>
	
</form>
</div>
</div>



</body>
</html>