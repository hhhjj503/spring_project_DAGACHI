<%@page import="java.util.function.Function"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="../board/comment/commentWrite.jsp" %>


<!DOCTYPE html>
<html>
<head>
<title>공지사항</title>
<link href="../resources/Notice_Detail.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    a {
    display: inline-block;
    display: block;
    }
</style> 

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<script>

function chk() {

	var d = document.forms.noticeUpdate;

	if(d.owner_Notice_Title.value.trim() == "") {
		alert("제목을 입력해주세요");
		return false;
		}
	else if(d.owner_Notice_Content.value.trim() == "") {
		alert("내용을 입력해주세요");
		return false;
		}
	else {
		alert("수정되었습니다.")
		}
	
}

function del() {
	
	if(confirm("공지를 삭제하시겠습니까?") == true) {
		var num = $("input[name=owner_Notice_Num]").val();
		location.href='noticeDel?owner_Notice_Num='+ num;
		return true;
		} else {
		return false;
			}
}

	$(function() {
			var owner_Notice_Num = $("input[name=owner_Notice_Num]").val();	
			$.ajax({
				url: 'setNumber',
				type : 'get',
				data : {owner_Notice_Num : owner_Notice_Num }
				});	
			}
		)
		
 </script>
 
</head>
<body>

<div id="back"> 
 
 <!-- 메뉴 버튼 -->
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

<!-- 상위로고 -->
<img src="../resources/images/logo/capture.PNG" style="position: absolute;
margin-left: 45%;border-radius: 0px 0px 20px 20px;">
<!-- 하위로고 -->
<img src="../resources/images/logo/KakaoTalk_20200821_105423387.jpg" style="position: absolute;
margin-left: 35%;border-radius: 150px 150px 150px 150px;position: absolute;
margin-top: 47%;z-index: 17;opacity: 1;background-color: #E98583;">

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" >
<form id="noticeUpdate" action="noticeUpdate" method="post" onsubmit="return chk()" >

 	<table style="margin-top: -40px;" align="center" >
 	<thead>
 		<th style="background-color: #665F79;border-radius: 10px 10px 10px 10px;height: 25px;
 		color: white;">공지사항</th>
	</thead>
	
	<td>
	<p style="padding-right: 4px; padding-left: 4px;"><b> 제목 :</b>
	<input type="text" id="owner_Notice_Title" name="owner_Notice_Title"
	style="width: 400px;background:none;border:none;outline: none;height: 20px;font-weight: bolder;font-size: medium;"
	readonly="readonly" value="${choose.owner_Notice_Title}">
	<b>
	<input type="text" id="owner_Notice_Num" name="owner_Notice_Num" style="width: 30px; float: right; background-color: #D0CECD;text-align: center;"
	value="${choose.owner_Notice_Num}" readonly="readonly">
	</b></p>
	
	<p style="padding-right: 4px; padding-left: 4px;" >
	<b> 작성자 : </b> <input type="text" id="admin_Num" name="admin_Num" value="${choose.admin_Num}" readonly="readonly"
	style="background:none;border:none;outline: none;height: 20px;font-weight: bolder;" >
	<br/></p>
       <!-- 내용 div 태그 -->
        <div style="width:790px;resize: none;height: 290px;overflow:scroll;overflow: auto;
        border: 0.5px solid;padding-left:10px;padding-top:10px;border-color: #665F79;" rows="20px">       
        ${choose.owner_Notice_Content}</div>
 	<br/>
 	</table>
 </form>
 </div>
 </div>
 </div> 

	<c:choose>
	<c:when test="${log_In_Admin.admin_Num == choose.admin_Num }">
	<!-- 목록버튼 -->
	<div style="padding-right: 4px; padding-left: 4px;margin-top: 704px;margin-left: 1223px;position: absolute;z-index: 50" align="right"> 
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
	value="목록" onclick="location.href='a_noticeList?p=${p}'" >
 	</div>
	<!-- 수정/삭제 버튼 -->
	<div style="position: absolute;margin-top: 37%;margin-left: 67.3%;z-index: 35;"> <!-- 수정/삭제 전송 div -->
	<form action="noticeInfo" method="get">
	<input type="hidden" id="noticeNum" name="noticeNum" value="${choose.owner_Notice_Num}" />
	<input type="submit" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
	value="수정/삭제" />
 	</form>
 	</div>
	</c:when>
	<c:otherwise>
	<!-- 목록버튼 -->
	<div style="padding-right: 4px; padding-left: 4px;margin-top: 704px;margin-left: 1307px;position: absolute;z-index: 50" align="right"> 
 	<input type="button" style="background-color: #665F79;float: right;margin-left: 4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;"
	value="목록" onclick="location.href='a_noticeList?p=${p}'" >
 	</div>
	</c:otherwise>
 	</c:choose>
 	
 	
 <!-- 첨부파일목록 -->
 <c:if test="${count > 0}">
		<input type="hidden" value="${count}" id="fileCount" />
		
		<div id="fileList" name="fileList"
		style="position: absolute;background-color: white;border-radius: 10px 10px 10px 10px;width:230px;z-index: 40;
		margin-left: 77.3%;margin-top: 15.5%;height: auto;overflow: auto;"  >
		
		<table style="border-radius: 10px 10px 10px 10px;width: 230px;" >
		
		<c:forEach items="${fileDtos}" var="files" varStatus="status">
		<tr style="width: 200px;word-break:break-all;float: left;">
		<td style="width: 200px;height: 20px;vertical-align: middle;">
		<a href='down?notice_Num=${choose.owner_Notice_Num}&&file_No=${files.file_No}'
		style="color: black;position:absolute;padding-left: 10px;padding-right: 10px;float: left;">${files.org_File_Name}
		</a></td>
		
		<tr><td>
		</br>
		</td></tr>
		
		</c:forEach>
		</table>
		</div>
	</c:if>
 
	
<div id="comment" name="comment" style="position: absolute;"></div>

</body>
</html>