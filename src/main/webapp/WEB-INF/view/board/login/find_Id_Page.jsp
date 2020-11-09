<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이디 찾기</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script>
function sendingNumber() {
	var email = $("input[name=emailBox]").val().trim();
	if(email == "") {
		alert("Email을 입력해주세요.");
		$("input[name=emailBox]").focus();
		return false;
	} else {
		alert("인증번호가 메일로 전송되었습니다.");
		$.ajax({
			url:"id_ChkUp",
			data: {admin_email : email}
			});
	}
}

function numberCodeChk() {
	var code = $("input[name=code_Number]").val().trim();
	var html = "";
	if(code == "") {
	alert("인증번호를 입력해주세요.");
	$("input[name=code_Number]").focus();
	} else {
		$.ajax({
			url:"id_Chk",
			data: {request_id_Chk : code},
			success : function(data) {
				if(data != 0) {
					$('#idBox').html('').attr('padding-left: 15px');
					html += "<b>아이디는</b> " + data + " <b>입니다.</b>";
					$('#idBox').html(html);
					html = "";
				} else {
					alert("인증번호가 일치하지 않습니다.");
					} 
				}
			});
	}
}
</script>
</head>
<body>
</br>
<div id="idBox" style="padding-left: 60px;padding-top: 32px;">
이메일 : <input id="emailBox" name="emailBox" type="text" style="height: 15px;">
		<input type="button" style="background-color: #665F79;margin-left: 4px;
		color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
		value="인증번호 전송" onclick="sendingNumber()"></br></br>
인증번호 : <input type="text" id="code_Number" name="code_Number" style="height: 15px;">
<input type="button" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="확인" onclick="numberCodeChk()">
			</br>
</div>
</body>
</html>