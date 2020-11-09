<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
function cmtlistback() {
	$.ajax({
		url: "getComment",
		data :{ pageNumChange : current-1 },
		type : 'get'
		});
}
function cmtlist(pno) {
	$.ajax({
		url: "getComment",
		data :{ pageNum :pno },
		type : 'get',
		contentType: 'application/json',
		success : function(data) {
			alert("전송성공");
			console.log(data)
			},
		error : function() {
			alert("실패");
			}
		});
	getCommentList();
}
function cmtlistforward() {
	$.ajax({
		url: "getComment",
		data :{ pageNumChange : current+1 },
		type : 'get'
		});
}
var cmthtml = "";
var pno = 0;
function commentpage() {
	$.ajax({
	    url: 'getComment',
	    type: 'get',
	    contentType: 'application/json',
	    success: function(data) {	
	    	console.log(data);
	    	cmthtml += "<p>";
			if(data[0].count > 0 ) {
					if(data[0].list.p.beginPageNumber > 10) {
						cmthtml += "<a href='' onclick='cmtlistback()'>이전</a";
					}

					for(i = data[0].list.p.beginPageNumber ; i <= data[0].list.p.endPageNumber ; i++ ) {
						cmthtml += "<a  href='' onclick='cmtlist(${pno})'>[i]</a>";
					}
					
					if(data[0].list.p.beginPageNumber > 10) {
						cmthtml += "<a href='' onclick='cmtlistforward()'>다음</a>";
					}
					cmthtml += "</p>";
				}
			$('#commentPaging').html(cmthtml);
			cmthtml = "";
	    	}
	});
}

$(function() {
	commentpage();
})

/* <c:if test= "${data[0].list.count > 0}" >
				<c:if test= "data[0].list.p.beginPageNumber > 10">
						html +=	"<a href=''>이전</a>";
				</c:if>
				<c:if test="data[0].list.p.endPageNumber < data[0].list.p.totalPageCount">
					html += "<a href=''>다음</a>";
				</c:if>
			</c:if> */
</script>
</head>
<body>
<div id="commentPaging" name="commentPaging"
style="position: absolute;background-color: white;z-index: 44"></div>

</body>
</html>