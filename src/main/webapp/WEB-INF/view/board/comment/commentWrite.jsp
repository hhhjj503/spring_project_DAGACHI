<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공지사항</title>
<% 
	Date today = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
%>

<style type="text/css">
	#commentPaging {
	z-index: 48;
	background-color:#665F79;
	align:center;
	position:absolute;
	padding:10px;
	border-radius: 10px;
	text-align: center;
	float:left;
	margin-top: 20%;
	}
	
 a{
 text-decoration: none;
 text-align: center;
 color: white;
 float: center;
 }
 
 a:hover { text-decoration:underline;}
 a:actived {text-decoration:underline;}	
 
 
 text {
 	color: black;
 	font-weight: bold;
 }
 
 #b {
 	background-color: #D0CECD;
 	width:40px;
 	text-align: center;
 	position: absolute;
 	border-radius: 5px 5px 5px 5px;
 }
 
 table {
 	background-color: white;
 }
 
 date {
 	float: right;
 }
 writer {
 	padding-left: 50px;
 }
 
 #bnUpdate {
  float: right;
  background-color: transparent;
  border: none;
  outline: none;
 }
 
 #bnDel {
 float: right;
 background-color: transparent;
 border: none;
 outline: none;
 }
 
 #bnUpdate:hover {text-decoration: underline;}
 #bnDel:hover {text-decoration: underline;}
 
 
</style>

<script  src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
//댓글등록
	function insertcmt() { 

		var d = document.forms.commentWrite;

		var cmtWriter = d.cmtWriter.value.trim();
		var cmtPassword = d.cmtPassword.value.trim();
		var cmtContents = d.cmtContents.value.trim();
		var cmtDate = d.cmtDate;				
	
	if(cmtWriter == "") {
		alert("작성자를 입력해주세요");
		d.cmtWriter.focus();
		return false;
		}
	else if(cmtPassword == "") {
		alert("비밀번호를 입력해주세요");
		d.cmtPassword.focus();
		return false;
		}
	else if(cmtContents == "") {
		alert("내용을 입력해주세요");
		d.cmtContents.focus();
		return false;
		} else {
		
		$.ajax({
			url: 'commentAdd',
			type : 'post',
			data : $('#commentWrite').serialize() ,
			success : function() {
				$('#cmtWriter').val('');
				$('#cmtPassword').val('');
				$('#cmtContents').val('');
				alert("댓글이 등록되었습니다.");
				getCommentList();
				getCnt();
				return true;
			} ,
			error : function() {alert("등록실패"); }
			});
			
		}
	
	}
//화면뜰시 페이지 자동 댓글불러오기
$(function() {
	getCommentList();
})


	var html = "";
	var begin = 0;
	var end = 0;
	var current = 0;	
//댓글목록
function getCommentList() {

	$.ajax({
	    url: 'getComment',
	    type: 'get',
	    contentType: 'application/json' ,
	    success: function(data) {		
			if(data[0].count > 0) {
			for(i = 0; i < data[0].list.commentList.length; i++) {
				html += "<div><div id='b'><strong>" + data[0].list.commentList[i].cmtNumber; 
				html += "</div><writer>" +data[0].list.commentList[i].cmtWriter + "</writer>";
				html += "<date>" + data[0].list.commentList[i].cmtDate + "</date></br>";
				html += "<table style='padding-bottom: 10px;padding-top:8px;width: 700px;'>";
				html += "<tr style='700px;'><td style='word-break:break-all'>" + data[0].list.commentList[i].cmtContents + "</tr>";
				html += "<tr><td><button id='bnDel' onclick='cmtDel("+ data[0].list.commentList[i].cmtNumber +")'>삭제</button>";
				html += "<button id='bnUpdate' onclick='cmtUp("+ data[0].list.commentList[i].cmtNumber + ")'>수정</button>";
				html += "</td></tr></div></table></div></br>";
				html += "<div id='num" + data[0].list.commentList[i].cmtNumber + "' align='right'></div></br>";
				/* html += "<div id='upForm" + data[0].list.commentList[i].cmtNumber + "' align='right'></div>"; */
				}
			$('#commentList').html(html);
			html ="";				
			} else {
				html += "<div style='text-align:center'>";
				html += "<table ><h5><string>댓글이 없습니다.<h5><string></table>";
				html += "</div>";
				$('#commentList').html(html);
				html ="";
				}
		}
		})
	}
//댓글수정폼열기
function cmtUp(no) {
	var no = no;
	var html = "";
	$('#num' + no).html(html);
	var num = "#num";
	num +=  no;
	
	html += "수정 비밀번호입력 ";
	html += "<input type='password' id='cmtUpPwd" + no + "' name='cmtUpPwd" + no + "'/>";
	html += "<button onclick='upSend(" + no + ")'>확인</button>";
	html += "<button onclick='upClose("+ no +")'>닫기</button>";
	$(num).html(html);
	html = "";
}
//댓글수정폼닫기
function upClose(no) {
	var html = "";
	var num = "#num";
	num += no;
	$(num).html(html);
}
//댓글수정전송
function upSend(no) {
	var cmtNumber = no;
	var inputName = "cmtUpPwd" + cmtNumber;
	var cmtPassword = $('input[name='+inputName +']').val();
	var getPwd;
	if(cmtPassword == "") {
		alert("비밀번호를 입력해주세요.");
		$('input[name='+inputName +']').focus();
		} else {
			$.ajax({
				url:'getPwd',
				data:{ cmtNumberS : cmtNumber } ,
				type:'post',
				success: function(data) {
					getPwd = data;
					if(getPwd == cmtPassword) {
						$.ajax({
							url:'getContents',
							data:{ cmtNumberS : cmtNumber },
							success : function(data) {
							var html ='';
							$('#num'+ cmtNumber).html(html);
					html += "<textarea id='text" + cmtNumber + "'maxlength=200 style='width:700px;height:40px;resize:none;outline:none;'>"+ data + "</textarea>";
					html += "<button onclick='upComment("+ cmtNumber + ")' style='float:right'>확인</button> ";
					html += "<button onclick='upClose("+ no +")' >닫기</button>";
							$('#num'+ cmtNumber).html(html);
								}
							});
					} else {
					alert("비밀번호가 일치하지 않습니다.");
						}
				},
				error:function(error) {
					alert('비밀번호가 일치하지 않습니다.');
					}
			});
		}
}
//수정전송시 컨트롤러로 가는 메서드
function upComment(cmtNumber) {
	var contents = $('#text' + cmtNumber).val();
	var cmtNumber = cmtNumber;
	if(contents == "") {
		alert('댓글을 입력해주세요');
		$('#text' + cmtNumber).focus();
		return
		} else {
	$.ajax({
		url:'upComment',
		type:'get',
		data: { cmtNumberS : cmtNumber,
			cmtContents : contents },
		success: function() {
			alert('수정되었습니다.');
			getCommentList();
			}
		});
	}
}
//댓글삭제폼열기
function cmtDel(no) {
	var no = no;
	var html = "";
	$('#num' + no).html(html);
	var num = "#num";
	num +=  no;

	html += "삭제 비밀번호입력 ";
	html += "<input type='password' id='cmtDelPwd" + no + "' name='cmtDelPwd" + no + "'/>";
	html += "<button onclick='delSend(" + no + ")'>확인</button>";
	html += "<button onclick='delClose("+ no +")'>닫기</button>";
	$(num).html(html);
	html = "";
}
//댓글삭제폼닫기
function delClose(no) {
	var html = "";
	var num = "#num";
	num += no;
	$(num).html(html);
}
//댓글삭제전송(비로그인)
function delSend(no) {
	var cmtNumber = no;
	var inputName = "cmtDelPwd" + cmtNumber;
	var cmtPassword = $('input[name='+inputName +']').val();
	var getPwd;
	if(cmtPassword == "") {
		alert("비밀번호를 입력해주세요.");
		$('input[name='+inputName +']').focus();
		} else {
			$.ajax({
				url:'getPwd',
				data:{ cmtNumberS : cmtNumber } ,
				type:'post',
				success: function(data) {
					getPwd = data;
					if(getPwd == cmtPassword) {
						$.ajax({
							url:'cmtDel',
							data:{ cmtNumberS : cmtNumber },
							success : function() {
							alert("댓글이 삭제되었습니다.");
							getCommentList();
							getCnt();
								}
							});
					} else {
					alert("비밀번호가 일치하지 않습니다.");
						}
				},
				error:function(error) {
					alert('비밀번호가 일치하지 않습니다.');
					}
			});
		}
}
function loginCmtDel(no) {
	var cmtNumber = no;
	if(confirm("댓글을 삭제하시겠습니까?")) {
		$.ajax({
		url:'loginCmtDel',
		data:{ cmtNumberS : cmtNumber },
		success : function() {
		alert("댓글이 삭제되었습니다.");
		getCommentList();
		getCnt();
		}
		});//ajax
	} else {
		} 
}
//댓글 paging 숫자
function commentpage() {
	$.ajax({
	    url: 'getComment',
	    type: 'get',
	    contentType: 'application/json',
	    success: function(data) {	
	    	console.log(data);
	    	if(data[0].count > 0 ) {
	    	/* html += "<p id='cmtpage'>" */
					if(data[0].list.p.beginPageNumber > 10) { 
						html += "<a href='javascript' onclick='cmtlistback(this)'>이전</a"; 
						}
					for(i = data[0].list.p.beginPageNumber ; i <= data[0].list.p.endPageNumber ; i++ ) {
					
	html += "<a  href='javascript:void(0)' id='cmt"+ i +"' onclick='cmtlist(this)'style='padding-left:10px;padding-right:10px;'>"+ i +" " +"</a>";
					}
					if(data[0].list.p.beginPageNumber > 10) { 
						html += "<a href='javascript:void(0)' onclick='cmtlistforward(this)'>다음</a>"; 
						}
				/* html += "</p>"; */
				$('#commentPaging').html(html);
				html = "";
				}
	    	}
	});
}
//댓글 페이지 뒤로가기
function cmtlistback(num) {
	var pagenum = $(num).text();
	$.ajax({
		url: "getComment",
		data :{ pageNumChange : pagenum-1 },
		type : 'get'
		});
}
//paging 숫자클릭 댓글목록
function cmtlist(num) {

	var pagenum = $(num).text();
	$.ajax({
		url: "getComment",
		data :{ pageNum :pagenum},
		type : 'get',
		contentType: 'application/json',
		success : function(data) {		
			if(data[0].count > 0) {
				//세션이 없을경우
					for(var i = 0; i < data[0].list.commentList.length; i++) {
						html += "<div><div id='b'><strong>" + data[0].list.commentList[i].cmtNumber; 
						html += "</div><writer>" +data[0].list.commentList[i].cmtWriter + "</writer>";
						html += "<date>" + data[0].list.commentList[i].cmtDate + "</date></br>";
						html += "<table style='padding-bottom: 10px;padding-top:8px;width: 700px;'>";
						html += "<tr style='700px;'><td style='word-break:break-all'>" + data[0].list.commentList[i].cmtContents + "</tr>";
						html += "<tr><td><button id='bnDel' onclick='cmtDel("+ data[0].list.commentList[i].cmtNumber +")'>삭제</button>";
						html += "<button id='bnUpdate' onclick='cmtUp("+ data[0].list.commentList[i].cmtNumber + ")'>수정</button>";
						html += "</td></tr></div></table></div></br>";
						html += "<div id='num" + data[0].list.commentList[i].cmtNumber + "' align='right'></div></br>";
					}
						$('#commentList').html(html);
						html ="";				
				} else {
						html += "<div>";
						html += "<table><h5><string>댓글이 없습니다.<h5><string></table>";
						html += "</div>";
						$('#commentList').html(html);
						html ="";
				}
				//세션이 있을경우
				/* if(sess != null) {
					for(var i = 0; i < data[0].list.commentList.length; i++) {
						html += "<div><div id='b'><strong>" + data[0].list.commentList[i].cmtNumber;
						html += "</div><writer>" +data[0].list.commentList[i].cmtWriter + "</writer>";
						html += "<date>" + data[0].list.commentList[i].cmtDate + "</date></br>";
						html += "<table style='padding-bottom: 10px;padding-top:8px;width: 700px;'>";
						html += "<tr style='700px;'><td style='word-break:break-all'>" + data[0].list.commentList[i].cmtContents + "</tr>";
						html += "<tr><td><button id='bnDel' onclick='loginCmtDel(" + data[0].list.commentList[i].cmtNumber + ")'>삭제</button>";
						html += "<button id='bnUpdate' onclick='loginCmtUp("+ data[0].list.commentList[i].cmtNumber +")'>수정</button>";
						html += "</td></tr></div></table></div></br>";
						html += "<div id='num" + data[0].list.commentList[i].cmtNumber + "' align='right'></div></br>";
					}
				} else {
					html += "<div>";
					html += "<table><h5><string>댓글이 없습니다.<h5><string></table>";
					html += "</div>";
					$('#commentList').html(html);
					html ="";
				} */
			} ,
		error : function() {
			alert("실패");
			}
		});
}
//댓글 앞쪽가기
function cmtlistforward() {
	$.ajax({
		url: "getComment",
		data :{ pageNumChange : current+1 },
		type : 'get'
		});	
}
//댓글갯수 가져오기
function getCnt() {
	$.ajax({
		url:'getCnt',
		success: function(data) {
			$('#getCnt').val('댓글( ' + data + ')');
			}
		});
}
$(function() {
	commentpage();
	getCnt();
})


</script>

</head>
<body>
<div id="commentForm" style="position: absolute;z-index: 0;background-color:#E4E4E4;margin-top: 48%;margin-left: 33%;
padding: 20px; border-radius: 15px 15px 15px 15px;border: 1px solid #G2G2G2;padding-right: 75px;">

<c:if test="${log_In_Admin == null}" >
<form id="commentWrite" name="commentWrite"  method="post" >
	
	작성자 :  <input type="text" id="cmtWriter" name="cmtWriter" style="width: 142px;outline: none;" maxlength="20"/>
	비밀번호 : <input type="password" id="cmtPassword" name="cmtPassword" style="margin-bottom: 10px;width: 142px;outline: none;"
	maxlength="20" />
	
	<input type="text" id="front" name="front" value="<%= df.format(today) %>"
	style="width: 70px;background-color: white;padding-left: 10px;padding-right:10px;outline:none;border:none;
	height: 20px;margin-left: 7px;float: right;" readonly="readonly" />
	<br>
		
	<textarea rows="3" cols="80" id="cmtContents" name="cmtContents" style="resize: none;outline: none;border-color: #665F79;"
	maxlength="200"></textarea>
	
	<input type="button" style="background-color: #665F79;float: right;margin-left: 12px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;position: absolute;
	margin-top: 1%;height: 40px;" value="등록" onclick="insertcmt()" />
	
</form> <!-- 댓글 등록폼(비로그인) -->
</c:if>

<c:if test="${log_In_Admin != null}">
<form id="commentWrite" name="commentWrite"  method="post" >
	
	작성자 :
	<input type="text" id="cmtWriter" name="cmtWriter" style="width: 142px;outline: none;border: none;background: none;"
	maxlength="20" value="${log_In_Admin.admin_Id }" readonly="readonly" />
	
	<input type="hidden" id="cmtPassword" name="cmtPassword" style="margin-bottom: 10px;width: 142px;outline: none;"
	maxlength="20" value="8081505569a68462file"/>
	
	<input type="text" id="front" name="front" value="<%= df.format(today) %>"
	style="width: 70px;background-color: white;padding-left: 10px;padding-right:10px;outline:none;border:none;
	height: 20px;margin-left: 7px;float: right;" readonly="readonly" /><br/>
	<br>
		
	<textarea rows="3" cols="80" id="cmtContents" name="cmtContents" style="resize: none;outline: none;border-color: #665F79;"
	maxlength="200"></textarea>
	
	<input type="button" style="background-color: #665F79;float: right;margin-left: 12px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;position: absolute;
	margin-top: 1%;height: 40px;" value="등록" onclick="insertcmt()" />
	
</form> <!-- 댓글 등록폼(로그인) -->
</c:if>

</div>

	<div id="comment" style="z-index: 48;position: absolute;margin-top: 58.2%;margin-left: 31.2%;align-content: center;" >
	
	<div id="commentList" name="commentList" style="position: absolute;
	z-index: 40;background-color: #G2G2G2;border-radius: 10px;
	padding-left:20px;padding-right:20px;padding-top:20px;margin-bottom:30px;/* box-shadow: 3px 2px 2px 2px grey; */width: 700px;
	border: 8px solid #D3D3D3;">
	
	</div><!-- 댓글목록 -->

	</div>
	
	<!-- comment로 스크롤이동 버튼 -->
	<button style="position:fixed;bottom:30px;right:30px;background-color: #665F79;float: right;margin-left: 8px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right:">
	<a href="#commentForm" style="font-size:large; ;">Comment</a>
	</button>

	<!-- 댓글()개수표시 버튼 -->
	<input id="getCnt" type="button" style="background-color: #665F79;float: left;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;position: absolute;
	margin-top: 36.65%;margin-left: 29.5%;z-index: 37;outline: none;" onclick="location.href='#commentForm'"/>
 
 	<!-- 댓글 페이징 -->
	<div id="commentPaging" name="commentPaging"
	style="position:absolute; color: white;z-index: 48;
	margin-top:55.5%;margin-left:50%;
	background-color: #665F79;border-radius: 20px 20px 20px 20px;">
	</div> 

</body>
</html>