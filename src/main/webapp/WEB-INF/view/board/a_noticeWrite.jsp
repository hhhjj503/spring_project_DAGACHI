<%@page import="java.util.function.Function"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.*, java.text.*"  %>

<!DOCTYPE html>
<html>
<head>
<title>공지사항 작성</title>
<link href="../resources/Notice_Detail.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
//공백chk
function chk() {

	var d = document.forms.noticeWrite;
  	if(d.owner_Notice_Title.value == "") {
			alert("제목을 입력해주세요");
			d.owner_Notice_Title.focus();
			return false;
		}
	 if(CKEDITOR.instances.owner_Notice_Content.getData() == "") {
		CKEDITOR.instances.owner_Notice_Content.focus();
		alert("내용을 입력해주세요");
		return false;
		}
	else {
		alert("공지사항이 등록되었습니다.");
		return true;
		} 
}

function goLogin() {
	window.location.href="a_adminLogin";
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
margin-top: 47%;z-index: 10;opacity: 1;background-color: #E98583;">
<div id="tata" style="border-radius: 0px 0px 30px 30px;">



<div id="list" >
<form action="noticeWrite" method="post" onsubmit="return chk()">
 	<table style="margin-top: -40px;" align="center" >
 	<thead>
 	<tr>
 		<th style="background-color: #665F79;border-radius: 10px 10px 10px 10px;height: 25px;
 		color: white;">새 공지사항 작성</th></tr>
	</thead>
	<tr>
	<td>
	<p style="padding-right: 4px; padding-left: 4px;"><b> 제목 :</b>
	<input type="text" id="owner_Notice_Title" style="width: 400px;" name="owner_Notice_Title">
	<b>
	<input type="text" style="float: right;width: 80px;background-color: #D0CECD;padding-left: 10px;border-radius: 10px 10px 10px 10px;
	height: 20px;"
	value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>" readonly="readonly"></b>
	</p>
	<p style="padding-right: 4px; padding-left: 4px;">
	<b> 작성자 : </b><input type="text" id="admin_Num" name="admin_Num" style="background-color: white;outline: none;border: none;" value="${log_In_Admin.admin_Num}"
	readonly="readonly" ><br/></p>
	 
	<script src="../resources/ckeditor/ckeditor.js"></script>
	<textarea class="form-control" id="owner_Notice_Content" name="owner_Notice_Content" style="width:800px;resize: none;" rows="20px" cols="80px">
	</textarea>
            <script type="text/javascript">
                  CKEDITOR.replace('owner_Notice_Content', 
                          {height: 270,width:800 ,});
            </script>              
	<br/> 
 	<p style="padding-right: 4px; padding-left: 4px;margin-left: 43%;margin-top: -0.3%;">
 	<input type="submit" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value="등록" > 
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value="목록" onclick="history.go(-1)">
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value="취소" onclick="history.go(-1)">
 	</p>
 	</td>
 	</table>
	
	<div style="position: absolute;background-color: white;border-radius: 10px 10px 10px 10px;
		margin-top: 5%;margin-left: 20%;width: 600px;height: 50px;z-index: 1;">
		<div style="margin-top: 15px;">
		<input type="submit" style="float: right;margin-right: 20px;"/>
		<input type="file" id="files" name="files" style="float: left;width: 500px;margin-left: 20px;
		background-color: #E2E2E2;border: 0.5px;"/>
		<input type="hidden" value="${choose.owner_Notice_Num}" name="noticeNum"/>
		<input type="hidden" value="${choose.admin_Num}" name="admin_Num"/>
		</div>
	</div>
	
 </form>
 

 </div>
 </div>
 </div>
</body>
</html>