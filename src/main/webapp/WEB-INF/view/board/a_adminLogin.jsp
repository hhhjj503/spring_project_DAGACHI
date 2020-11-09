<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>DAGACHI</title>
<link href="../resources/Admin_Detail_Page.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script>

function finding_Id() {
	window.open("find_Id_Page","아이디 찾기", "width=500, height=200, scrollbars=false, resizable=false, toolbars=false, menubar=false");
}

function finding_Pwd() {
	window.open("find_Pwd_Page","비밀번호 찾기", "width=500, height=250, scrollbars=false, resizable=false, toolbars=false, menubar=false");
}

function loginCheck() {
	var admin_id = $("input[name=admin_id]").val().trim();
	var admin_password = $("input[name=admin_password]").val().trim();

	if(admin_id == "") {
		alert("아이디를 입력해주세요.");
		$("input[name=admin_id]").focus();
		return false;
		}
	else if(admin_password == "") {
		alert("비밀번호를 입력해주세요");
		$("input[name=admin_password]").focus();
		return false;
		}
	else {
		$.ajax({
			url : "login",
			data: {admin_id : admin_id , admin_password : admin_password},
			success : function(data) {
				if(data == "id_false") {
					alert("아이디 또는 비밀번호가 일치하지 않습니다.");
					return false;
					} 
				if(data == "pwd_false") {
					alert("아이디 또는 비밀번호가 일치하지 않습니다.");
					return false;
					}
				if(data == "success") {
					return true;
					}
				}
			});
		}
}
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
margin-top: 36%;z-index: 17;opacity: 1;background-color: #E98583;">

<div id="tata" style="border-radius: 30px 30px 30px 30px;height:300px;margin-top: 15%;">

<div id="list" >
<button style="background-color: #B2B2B2;height: 50px;width:400px;border-radius: 25px 25px 25px 25px;
font-size: large;font-weight:normal;margin-left: 100px;outline: none;border: none;color: white;margin-top: -5px;">
WELCOME TO DAGACHI</button>	
<div id="loginBox"style="position: absolute;margin-left: 140px;margin-top: 20px;
border-radius: 30px 30px 30px 30px;align-items: center;" >

<form action="loginChk" method="post" onsubmit="return loginCheck();">
<input type="text" id="admin_id" name="admin_id" style="height:25px;width:320px; margin-bottom: 10px;" placeholder="아이디"/><br/>
<input type="password" id="admin_password" name="admin_password" style="height:25px;width:320px;margin-bottom: 10px;" placeholder="비밀번호"/>
<input type="submit" style="background-color: #665F79;height:40px;width:330px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="로그인"><br/> 
</form>		

		<input type="button" style="background-color: #665F79;margin-left: 4px;
		color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
		value="관리계정 가입" onclick="location.href='adminWriteForm'"> 
			
		<input type="button" style="background-color: #665F79;margin-left: 4px;margin-top:10px;
		color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
		value="아이디찾기" onclick="finding_Id()">
			
		<input type="button" style="background-color: #665F79;margin-left: 4px;
		color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
		value="비밀번호찾기" onclick="finding_Pwd()">
		
</div><!-- list -->

</div><!-- listback -->

</div><!-- tata -->

</div>

</body>
</html>