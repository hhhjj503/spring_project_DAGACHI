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
<title>공지사항 수정</title>
<link href="../resources/Notice_Detail.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<script>

function chk() {
	var d = document.forms.noticeUpdate;
	
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
		alert("수정되었습니다.")
		return true;
		}
}

function del() {
var d = document.forms.noticeUpdate;
	
	if(confirm("공지를 삭제하시겠습니까?") == true) {
		var num = $("input[name=owner_Notice_Num]").val();
		location.href='noticeDel?owner_Notice_Num='+ num;
		alert("삭제되었습니다.");
		return true;
		}
}

function fileDelOne(file_No) {
	var num = $('#file_No' + file_No).val();
	var notice_Num = $('#notice_Num').val();
	$.ajax({
		url : 'fileDelOne',
		data : {notice_Num : notice_Num,
				file_No : num },
		success: function() {
			alert("성공");
			}
		});
}
function ChkCnt() {
	var count = $('input[name=fileCount]').val();
	if(count >= 3 ) {
		alert("파일업로드는 3개 까지만 가능합니다.");
		return false;
		} else {
			return true;}
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
margin-top: 47%;z-index: 17;opacity: 1;background-color: #E98583;">
<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" >
<form id="noticeUpdate" action="noticeUpdate" method="post" onsubmit="return chk()" enctype="multipart/form-data" >
 	<table style="margin-top: -40px;" align="center" >
 	<thead>
 		<th style="background-color: #665F79;border-radius: 10px 10px 10px 10px;height: 25px;
 		color: white;">공지사항 수정</th>
	</thead>
	<td>
	<p style="padding-right: 4px; padding-left: 4px;"><b> 제목 :</b>
	<input type="text" id="owner_Notice_Title" name="owner_Notice_Title" style="width: 400px;" value="${choose.owner_Notice_Title}">
	<b>
	<input type="text" id="owner_Notice_Num" name="owner_Notice_Num" style="width: 30px; float: right; background-color: #D0CECD;text-align: center;"
	value="${choose.owner_Notice_Num}" readonly="readonly">
	</b></p>
	
	<p style="padding-right: 4px; padding-left: 4px;" >
	<b> 작성자 : </b> <input type="text" id="admin_Num" name="admin_Num" value="${choose.admin_Num}" readonly="readonly"
	style="background:none;border:none;outline: none;height: 20px;font-weight: bolder;">
	<br/></p>
	<!-- 체크 에디터  본문내용-->
	<script src="../resources/ckeditor/ckeditor.js"></script>
	<textarea class="form-control" id="owner_Notice_Content" name="owner_Notice_Content"
	style="width:800px;resize: none;font-size: 16px;" rows="20px" cols="80px">
	${choose.owner_Notice_Content}
	</textarea>
	<script type="text/javascript">
                  CKEDITOR.replace('owner_Notice_Content', 
                         {height: 270 , width:800 , fontSize_default : '16px' });
                  </script>
 	<br/>
 	<input type="hidden" id="noticeNum" name="noticeNum" value="${choose.owner_Notice_Num}" />
	<input type="hidden" id="adminpwd" name="adminpwd" value="${adminpwd}"/>
	<input type="hidden" id="admin_Num" name="admin_Num" value="${choose.admin_Num}" />
	
	<!-- 수정, 삭제, 취소, 업로드 버튼 -->
 	<p style="padding-right: 4px; padding-left: 4px;margin-top: -0.3%;" align="right">
	
 	<input type="submit" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value="수정">
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
	value="삭제" onclick="del()">
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
	value="취소" onclick="location.href='goNoticeRead?noticeNum=${choose.owner_Notice_Num}'">
 	</p>
 	</table>
 </form>
 
 </div>
 </div>
 </div>
 
<!-- 파일업로드 input-->
 <form  name="noticeFileUpdate" action="fileUpload" enctype="multipart/form-data" onsubmit="return ChkCnt()" method="post">
<div style="position: absolute;background-color: white;border-radius: 10px 10px 10px 10px;z-index: 40;
margin-top: 40%;margin-left: 35%;width: 600px;height: 50px;">
<div style="margin-top: 15px;">
<input type="submit" style="float: right;margin-right: 20px;"/>
<input type="file" id="files" name="files" style="float: left;width: 500px;margin-left: 20px;
background-color: #E2E2E2;border: 0.5px;"/>
<input type="hidden" value="${choose.owner_Notice_Num}" name="noticeNum"/>
<input type="hidden" value="${choose.admin_Num}" name="admin_Num"/>
</div>
</div>

</form>
 
<!-- 첨부파일목록 삭제 -->
<c:if test="${count > 0}">
		<input type="hidden" value="${count}" id="fileCount" name="fileCount" />
		<input type="hidden" value="${choose.owner_Notice_Num}" id="notice_Num" name="notice_Num" />
		<input type="hidden" value="${files.file_No}" id="file_No${files.file_No}" name="file_No${files.file_No}" />
		
		<div id="fileList" name="fileList"
		style="position: absolute;background-color: white;border-radius: 10px 10px 10px 10px;width:230px;z-index: 40;
		margin-left: 76.7%;margin-top: 15.5%;height: auto;padding-bottom: 10px;overflow: auto;vertical-align: middle;"  >
		
		<table style="border-radius: 10px 10px 10px 10px;width: 230px;padding-bottom: 10px;" >
		
		<c:forEach items="${fileDtos}" var="files" varStatus="status">
		<tr style="width: 230px;word-break:break-all;">
		<td style="width: 220px;height: 20px;vertical-align: middle;">
		<a href='down?notice_Num=${choose.owner_Notice_Num}&&file_No=${files.file_No}'
		style="color: black;position:absolute;padding-left: 10px;float: center;text-align: center;vertical-align: middle;">${files.org_File_Name}
		</a></td>
		
		<tr><td>
		<input type="button" style="float:center;height: 25px;width: 50px;align-content: center;align-items: center;margin-top: 20px;margin-left: 10px;"
		onclick="location.href='fileDelOne?notice_Num=${choose.owner_Notice_Num}&&file_No=${files.file_No}'" value="삭제"/>
		</td></tr>
		
		</c:forEach>
		</table>
		</div>
	</c:if>

</body>
</html>