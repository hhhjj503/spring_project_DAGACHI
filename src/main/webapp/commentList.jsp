<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>댓글 작성</title>
<% 
	Date today = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
%>
<script  src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	function insertcmt() { 

		var d = document.forms.commentWrite;

		var cmtWriter = d.cmtWriter.value.trim();
		var cmtPassword = d.cmtPassword.value.trim();
		var cmtContents = d.cmtContents.value.trim();
		var cmtDate = d.cmtDate;				
	
	if(cmtWriter == "") {
		alert("작성자를 입력해주세요");
		return false;
		}
	else if(cmtPassword == "") {
		alert("비밀번호를 입력해주세요");
		return false;
		}
	else if(cmtContents == "") {
		alert("내용을 입력해주세요");
		return false;
		} else {
		
		$.ajax({
			url: "commentAdd",
			type : 'post',
			data : $('#commentWrite').serialize() ,
			success : function() {
				$('#cmtWriter').val('');
				$('#cmtPassword').val('');
				$('#cmtContents').val('');
				alert("댓글이 등록되었습니다.");
			} ,
			error : function() {alert("등록실패"); }
			});
			
		return true;
		}
		
	}


	var cmtlist;
	
$(function() {
	getCommentList();
})
	
function getCommentList() {

	$.ajax({
	    url: 'getComment',
	    type: 'get',
	    contentType: 'application/json' ,
	    success: function(data) {
			/* alert(data[0].count + '댓글자료 전송완료'); */
			console.log(data);


			if(data[0].count > 0) {
			cmtlist = data;
			}
			/* var html;
			if(data[0].count > 0) {
			
			for(i = 0; i < data[0].commentList.length;i++) {
				html += "<div style='position: absolite;'>";
				html += "<table id='cmtTable' name='cmtTable'><h6><strong>"+ data[0].commentList[i].cmtWriter +"<strong><h6>";
				html += "<input type='text' value=" + data[0].commentList[i].cmtNumber + "/>"
				html += "<"
				html += "

				cmtContents
					cmtDate: 1599577200000
					cmtPassword: ";;"
					cmtWriter
					owner_Notice_Num: 7
				}
			} else {}  */ 
			
		    }
		})
}

</script>

</head>
<body style="width: 800px;height: 200px;">
 
<div style="position: absolute;z-index: 35;background-color: white;margin-top: 39%;margin-left: 29%;padding: 15px;
border-radius: 0 0 20px 20px;padding-right: 70px;box-shadow: 0 1px 1px 1px grey;">
	
<form id="commentWrite" name="commentWrite"  method="post" >
	
	
	작성자: <input type="text" id="cmtWriter" name="cmtWriter" style="width: 142px;"/>
	비밀번호 : <input type="password" id="cmtPassword" name="cmtPassword" style="margin-bottom: 10px;width: 142px;"/>
	
	<input type="text" id="front" name="front" value="<%= df.format(today) %>"
	style="width: 70px;background-color: #D0CECD;padding-left: 10px;padding-right:10px;outline:none;border:none;
	height: 20px;margin-left: 7px;" readonly="readonly" />
	<br>
		
	<textarea rows="3" cols="80" id="cmtContents" name="cmtContents" style="resize: none;"></textarea>
	
	<input type="button" style="background-color: #665F79;float: right;margin-left: 8px;margin-bottom:4px;
	color: white;border-radius: 5px 5px 5px 5px;padding-left: 10px;padding-right: 10px;position: absolute;
	margin-top: 5%;" value="등록" onclick="insertcmt()" />
		
</form>
</div>

<div>
	<Table>
	<tr>작성자 입니다</tr><br/>
	댓글내용 입니다.<tr><td></td></tr>
	</Table>
</div>
<div>
	<Table>
	<h5><strong>작성자 입니다.</strong></h5>
	댓글내용 입니다.<tr><td></td></tr><br/>
	날짜입니다 번호입니다
	
	</Table>
	<div>
	<table>
	<h5><string>등록된 댓글이 없습니다.</string></h5>
	</table>
	</div>
</div>
</body>
</html>