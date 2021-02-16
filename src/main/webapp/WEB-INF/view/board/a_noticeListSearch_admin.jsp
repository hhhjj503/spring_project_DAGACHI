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
<title>게시판</title>
<link href="../resources/Notice_Admin_MainCSS.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    #listtable tr:nth-child(odd) {
    	background-color: #E4E4E4;
    }
</style> 
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

<c:if test="${log_In_Supervisor != null }" > 
	<div style="position: absolute;background-color: white;width: 300px;
	height: 60px;border-radius: 10px 10px 10px 10px;margin-left: 1165px;margin-top:16px; padding-left: 20px;">
	<div style="position: absolute;margin-top: 15px;">
	<b><a href="adminLoginUpdateDelete?admin_Id='${log_In_Supervisor.admin_Id}'" style="color: black;">
	${log_In_Supervisor.admin_Name}</a></b> 관리자님 안녕하세요
	<input type="button" id="logout" name="logout" style="background-color: #665F79;height: 30px;
			color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;" value= "로그아웃"
			onclick="location.href='logout'">
	</div>
	</div>
</c:if>

<div id="tata" style="border-radius: 0px 0px 30px 30px;">

<div id="list" style="margin-left: 0%">
<form action="a_noticeListSearch_admin" method="post">
	<table border="1" align="center" id="listtable"
			style="background-color: white;margin-top: -2%;">
	
			<thead>
			<tr style="width: 150px;">
			<td colspan="1">
			<input type="button" style="background-color: #665F79;color: white;
			border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;height: 30px;"
			onclick="location.href='a_noticeList_admin'" value="공지사항 이동">
			</td>
			<td colspan="6" style="background-color: white;">
			</td>
			</tr>
			
			<tr> 
			<th style="border: none;">번호</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;width: 70px;height: 38px;">작성자</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;width: 400px;" >제목</th>
			<th style="border: none; padding-left: 10px;padding-right: 10px;" >등록일</th>
			<th style="border: none;height: 6px;padding-left: 5px;padding-right: 5px;" >수정/삭제</th>
			<th style="border: none;height: 6px;padding-left: 5px;padding-right: 5px;" >O</th>
			<th style="border: none;height: 6px;padding-left: 5px;padding-right: 5px;" >X</th>
			</tr>
			</thead>  
			
			<c:if test="${searchList.count == 0}">
			<tr>
			<td colspan="6" align="center" >작성된 글이 없습니다.</td>
			</tr>
			</c:if>
			
	<c:if test="${search == 0}" >
	<c:forEach items="${searchList.ownerList}" var="result" varStatus="status">
		
		<c:set var="open" value="checked"/>
		<c:if test="${result.pub != true}">
		<c:set var="open" value=""/>
		</c:if>
		<tr>
			<td align="center" id="noticeNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 28px;">${result.owner_Notice_Num}</td>
			<td align="center" id="adminNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;"><b>${result.admin_Num}</b></td>
			<td align="center" id="title" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;">${result.owner_Notice_Title}</td>
			<td align="center" id="day" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;">
			<fmt:formatDate value="${result.owner_Notice_Created_Day}" pattern="yyyy/MM/dd" />
			</td>
			<td align="center" id="upDel" style="border: none;">
			<input type="button" value="공지읽기"
			style="background-color: none;height: 25px;background-color: #665F79;
			color: white;border-radius: 5px 5px 5px 5px;margin-left: 10px;margin-right: 10px;margin-top: 2px;"
			onclick=
			"location.href='searchRead?noticeNum=${result.owner_Notice_Num}&&searchstr=${searchstr}&&admin_Num=${result.admin_Num}&&search=${search}'" /></td>
			<td style="text-align: center;">
			<input type="checkbox" name="ids" value="${result.owner_Notice_Num}" checked="${result.pub ? chk : ''}" ></td>
			<td style="text-align: center;">
			<input type="checkbox" name="ids" value="${result.owner_Notice_Num}"></td>
			</tr>
	</c:forEach>
	</c:if>
	
	<c:if test="${search == 1}" >
	<c:forEach items="${searchList.ownerList}" var="result" varStatus="status">
		
		<c:set var="open" value="checked"/>
		<c:if test="${result.pub != true}">
		<c:set var="open" value=""/>
		</c:if>
		
		<tr>
			<td align="center" id="noticeNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 28px;">${result.owner_Notice_Num}</td>
			<td align="center" id="adminNum" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;">${result.admin_Num}</td>
			<td align="center" id="title" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;"><b>${result.owner_Notice_Title}</b></td>
			<td align="center" id="day" style="padding-left: 10px;padding-right: 10px;border-style: none;height: 25px;">
			<fmt:formatDate value="${result.owner_Notice_Created_Day}" pattern="yyyy/MM/dd" />
			</td>
			<td align="center" id="upDel" style="border: none;">
			<input type="button" value="공지읽기"
			style="background-color: none;height: 25px;background-color: #665F79;
			color: white;border-radius: 5px 5px 5px 5px;margin-left: 10px;margin-right: 10px;margin-top: 2px;"
			onclick=
			"location.href='searchRead?noticeNum=${result.owner_Notice_Num}&&searchstr=${searchstr}&&admin_Num=${result.admin_Num}&&search=${search}'" /></td>
			<td style="text-align: center;">
			<input type="checkbox" name="ids" value="${result.owner_Notice_Num}" ${open} ></td>
			<td style="text-align: center;">
			<input type="checkbox" name="ids" value="${result.owner_Notice_Num}"></td>
			</tr>
	</c:forEach>
	</c:if>
	
		</table>
		</form>
		
		<p align="center">
		<c:if test="${searchList.count > 0}">
		
  		<c:if test="${searchList.p.beginPageNumber > 10}">
			<a href="a_noticeListSearch_admin?p=${searchList.p.beginPageNumber-1}&&searchstr=${searchstr}&&search=${search}">이전</a>
		</c:if>
		
		<c:forEach var="pno" begin="${searchList.p.beginPageNumber}" end="${searchList.p.endPageNumber}">
		<a href="a_noticeListSearch_admin?p=${pno}&&searchstr=${searchstr}&&search=${search}">[${pno}]</a>
		</c:forEach>
		
		<c:if test="${searchList.p.endPageNumber < searchList.p.totalPageCount}">
			<a href="a_noticeListSearch_admin?p=${searchList.p.endPageNumber + 1}&&searchstr=${searchstr}&&search=${search}">다음</a>
		</c:if>
		
		</c:if> 
		</p>
		
		
	</div>
	</div>
	</div>
</body>
</html>
