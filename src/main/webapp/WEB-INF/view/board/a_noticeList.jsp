<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<link href="../resources/Notice_Admin_MainCSS.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    #listtable tr:nth-child(even) {
    	background-color: #E4E4E4;
    }
    
    input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
	}
</style> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

	function chk() {
		var d = document.forms.searchForm;

		if(d.searchstr.value.trim() == "") {
				alert("검색어를 입력해주세요");
				d.searchstr.focus();
				return false;
		}
		
		var num = $('select[name=search]').val();
		if(num == 0) {
			var str = $('input[name=searchstr]').val();
			if(isNaN(str) == true) {
				alert('작성자는 숫자만 입력가능합니다.');
				$('input[name=searchstr]').val("");
				return false;
				}
			}
	}
</script>
<title>게시판</title>
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

<c:if test="${log_In_Admin != null }" > 
	<div style="position: absolute;background-color: white;width: 250px;
	height: 60px;border-radius: 10px 10px 10px 10px;margin-left: 1215px;margin-top:16px; padding-left: 20px;">
	<div style="position: absolute;margin-top: 15px;">
	<b><a href="adminLoginUpdateDelete?admin_Id='${log_In_Admin.admin_Id}'" style="color: black;">
	${log_In_Admin.admin_Name}</a></b> 님 안녕하세요
	<input type="button" id="logout" name="logout" style="background-color: #665F79;height: 30px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value= "로그아웃"
			onclick="location.href='logout'">
	</div>
	</div>
</c:if>

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" style="margin-left: 0%;">

<!-- 검색Form -->
<div style="position: absolute;margin-top: 0.75%;margin-left: 60%;">
	<form id="searchForm" name="searchForm" action="a_noticeListSearch" onsubmit="return chk()" >

			<select name="search" style="float:left;margin-right: 5px;margin-top: 3px;outline: none;">
				<option value=0>작성자</option>
				<option value=1>제목</option>
			</select>
			<input type="submit" style="background-color: #665F79;float: right;margin-left: 4px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value= "검색" >
			<input type="text" id="searchstr" name="searchstr"
			style="float:right;margin-top: 1.4px;background-color: #E8F0FE">
	</form>
</div>

<!-- 목록 table -->
			<table border="1" align="center" id="listtable"
			style="background-color: white;margin-top: -2%;">
			<thead>		
			<tr>
			<td colspan="1"><input type="button" style="background-color: #665F79;color: white;
			border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;height: 30px;"
			value="새 공지 작성" onclick="location.href='noticeWriteForm'" /></td>
			<td colspan="4">
			</td>
			</tr>
			</thead>
			
			<tr> 
			<th style="border: none;">번호</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;width: 70px;height: 38px;">작성자</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;width: 400px;" >제목</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;margin-left: 10px;margin-right: 10px;" >등록일</th>
			<th style="border: none;height: 6px;margin-left: 10px;margin-right: 10px;" ></th>
			</tr>
			</thead> 
			
			<c:if test="${list.count == 0}">
			<tr>
			<td colspan="5" align="center" >작성된 글이 없습니다.</td>
			</tr>
			</c:if>
		
		<c:forEach items="${list.ownerList}" var="result" varStatus="status">
		<c:if test="${result.pub == true }"> 
		<tr>
			<td align="center" id="noticeNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 28px;">${result.owner_Notice_Num}</td>
			<td align="center" id="adminNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 26px;">${result.admin_Num}</td>
			<td align="center" id="title" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 26px;">${result.owner_Notice_Title}</td>
			<td align="center" id="day"
			style="border-style: none;height: 26px;padding-left: 10px;padding-right: 10px;">
			<fmt:formatDate value="${result.owner_Notice_Created_Day}" pattern="yyyy/MM/dd" />
			</td>
			<td align="center" style="border: none;">
			<input type="button" value="공지읽기" style="height: 26px;margin-left: 10px;margin-right: 10px;background-color: #665F79;
			color: white;border-radius: 5px 5px 5px 5px;"
			onclick="location.href='noticeRead?noticeNum=${result.owner_Notice_Num}&&admin_Num=${result.admin_Num}&&p=${p}'"/></td>
		</tr>
		</c:if>
		</c:forEach>
		</table>

		<p align="center">
		<c:if test="${list.count > 0}">
  	<c:if test="${list.p.beginPageNumber > 10}">
			<a href="a_noticeList?p=${list.p.beginPageNumber-1}">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${list.p.beginPageNumber}" end="${list.p.endPageNumber}">
		<a href="a_noticeList?p=${pno}">[${pno}]</a>
		</c:forEach>
		<c:if test="${list.p.endPageNumber < list.p.totalPageCount}">
			<a href="a_noticeList?p=${list.p.endPageNumber + 1}">다음</a>
		</c:if>
	</c:if>
		</p>

		</div>
</div>
    
    </div><!--back  -->
</body>
</html>
