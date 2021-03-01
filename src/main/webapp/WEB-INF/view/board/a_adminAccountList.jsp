<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>관리자 목록</title>
<link href="../resources/Notice_Admin_MainCSS.css" rel="stylesheet" type="text/css" />
<% Date now = new Date();%> 
<style type="text/css">
     
    #listtable tr:nth-child(odd) {
    	background-color: #E4E4E4;
    }
    
    input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
	}
		
</style> 

<script type="text/javascript">
	var chk = function() {

		var b = document.forms.search;
		if(b.admin_Num.value.trim() == "") {
			alert("관리자 번호(adminNo)를 입력해주세요. 숫자만 입력가능합니다.");
			return false;
			}
		}
</script>

</head>
<body>
<c:if test="${log_In_Supervisor == null }" > 
	<script>
	alert("관리자만 이용하실 수 있습니다.");
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

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" >
<form id="search" name="search" method="get" action="a_adminAccountListSearch" onsubmit="return chk()">
	<table border="" align="center" id="listtable" style="background-color: white;margin-top: -2%;width: 800px;">
			<thead>
			<th style="float: right;background-color: white;">
			<input type="button" style="background-color: #665F79;color: white;
			border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;height: 30px;"
			value="신규 관리자 등록" onclick="location.href='adminWriteForm'"></th>
			<th colspan="5" style="background-color: white;">
			<input type="submit" style="background-color: #665F79;color: white;
			border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;height: 25px;float: right;" 
			value="관리자번호 검색" />
			<input type="number" id="admin_Num" name="admin_Num" style="float: right;margin-right: 4px;margin-top: 2px;
			background-color: #E8F0FE;"/>
			<input type="hidden" value="${date}">
			</th>
				<tr style="height: 40px;">
				<th style="border: none;padding-left: 10px;padding-right: 10px;width:80px;">adminNo</th>
				<th style="border: none;padding-left: 10px;padding-right: 10px;width:200px;">그룹명</th>
				<th style="border: none;padding-left: 10px;padding-right: 10px;">관리자명</th>
				<th style="border: none;padding-left: 10px;padding-right: 10px;">권한</th>
				<th style="border: none;padding-left: 10px;padding-right: 10px;">등록일</th>
				<th style="border: none;">수정/삭제</th>
				</tr>
			</thead>
			
			<c:if test="${adminlist.count == 0}">
			<tr>
			<td colspan="6" align="center" >등록된 관리자가 없습니다.</td>
			</tr>
			</c:if>
			
		<c:forEach items="${adminlist.adminList}" var="result" varStatus="status">
		<tr>
			<td align="center" style="border: none;">${result.admin_Num}</td>
			<td align="center" style="border: none;padding-left: 10px;padding-right: 10px;height: 28px;"><b>${result.dept }</b></td>
			<td align="center" style="border: none;padding-left: 10px;padding-right: 10px;height: 28px;" >${result.admin_Name}</td>
			<td align="center" style="border: none;padding-left: 10px;padding-right: 10px;height: 28px;width: 100px;" >${result.admin_Author}</td>
			<td align="center" style="border: none;padding-left: 10px;padding-right: 10px;height: 28px;" id="date" name="date">
			<fmt:formatDate value="<%= now %>" pattern="yyyy/MM/dd" />
			</td>
			<td align="center" style="border: none;">
			<input type="button" style="height: 25px;background-color: #665F79;
			color: white;border-radius: 5px 5px 5px 5px;margin-left: 10px;margin-right: 10px;"
			value="수정/삭제"
			onclick="location.href='adminInfo?admin_Num=${result.admin_Num}'"/>
			</td>
		</tr>
		</c:forEach>
		</table>
		
		<p align="center">
	<c:if test="${adminlist.count > 0}">
   	 	<c:if test="${adminlist.p.beginPageNumber > 10}">
			<a href="a_adminAccountList?p=${adminlist.p.beginPageNumber-1}">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${adminlist.p.beginPageNumber}" end="${adminlist.p.endPageNumber}">
		<a href="a_adminAccountList?p=${pno}">[${pno}]</a>
		</c:forEach>
		<c:if test="${adminlist.p.endPageNumber < adminlist.p.totalPageCount}">
			<a href="a_adminAccountList?p=${adminlist.p.endPageNumber + 1}">다음</a>
		</c:if>
	</c:if>
		</p>
</form>
</div>
</div>
</div>
</body>
</html>