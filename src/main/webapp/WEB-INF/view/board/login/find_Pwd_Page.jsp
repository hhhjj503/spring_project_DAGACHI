<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<style type="text/css">
 #sending {
 background-color: #665F79;
 margin-left: 4px;
color: white;
border-radius: 5px 5px 5px 5px;
padding-left: 10px;
padding-right: 10px;"
 }
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script>
function changePwd() {
	var idBox = $("input[name=idBox]").val().trim();
	var emailBox = $("input[name=emailBox]").val().trim();
	var sending = false;
	if(idBox == "") {
		alert("아이디를 입력해주세요.");
		$("input[name=idBox]").focus();
		return false;
	} else if(emailBox == "") {
		alert("이메일을 입력해주세요.");
		$("input[name=emailBox]").focus();
		return false;
	} else {
		$.ajax({
			url:"changePwd",
			data: {request_id : idBox,
				request_email : emailBox},
			success: function(data) {
				if(data == "no_Id") {
					alert("해당 아이디가 존재하지 않습니다.");
				} else if(data == "no_email") {
					alert("존재하지 않는 이메일 입니다.");
				} else {
					alert("인증번호가 메일로 전송되었습니다.");
					
					}
				}
			});
		}
}


function chk() {
	var idBox = $("input[name=idBox]").val().trim();
	var emailBox = $("input[name=emailBox]").val().trim();
	var code_Number = $("input[name=code_Number]").val().trim();
	
	if(code_Number == "") {
		alert("인증번호를 입력해주세요.");
		$("input[name=code_Number]").focus();
	} else {
		$.ajax({
			url : "changePwd_2",
			data : { request_id: idBox, request_email : emailBox ,pwdCode : code_Number },
			success: function(data) {
				console.log(data);
			if(data[0] == "fail") {
					alert("인증번호가 일치하지 않습니다.");
					} else if(data[0] == "success") {
						var html = "";
						html += "변경할 비밀번호를 입력해주세요</br>";
						html += "<input type='password' id='changePwd' name='changePwd' style='margin-top:10px'/>";
						html += "<input type='button' id='sending' name='sending' value='확인' onclick='changePwd_3()'>";
						html += "<input type='hidden' id='hiddenId' name='hiddenId' value='"+ data[1]+"' />";
						html +=	"<input type='hidden' id='hiddenEmail' name='hiddenEmail' value='"+ data[2]+"' />";
						$("#pwdBox").html(html);
					}
				}
			});	
	}
}

function changePwd_3() {
	var changePwd = $("input[name=changePwd]").val().trim();
	var hdId = $('input[name=hiddenId]').val().trim();
	var hdEmail = $('input[name=hiddenEmail]').val().trim();
	if(changePwd== "") {
		alert("변경할 비밀번호를 입력해주세요.");
		} else {
		$.ajax({
		url : "changePwd_3",
		data : { request_id: hdId, request_email : hdEmail ,pwdCode : changePwd },
		success: function(data) {
			if(data == "success"){
			alert("비밀번호가 변경되었습니다.");
			window.close();
			}
		}
		});
	}
}
</script>
</head>
<body>
</br>
<div id="pwdBox" style="padding-left: 60px;padding-top: 32px;">
아이디 : <input id="idBox" name="idBox" type="text" style="height: 15px;"></br></br>
이메일 : <input type="text" id="emailBox" name="emailBox" style="height: 15px;">
<input type="button" style="background-color: #665F79;margin-left: 4px;
		color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
		value="인증번호 전송" onclick="changePwd()"></br></br>
인증번호 : <input type="text" id="code_Number" name="code_Number" style="height: 15px;">
<input type="button" style="background-color: #665F79;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
			value="확인" onclick="chk()">
			</br>

</div>
</body>
</html>