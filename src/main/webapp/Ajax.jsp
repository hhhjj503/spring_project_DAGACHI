<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%-- <% String cc = "dd"; %>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	$(function() {
		
	$("#btn").on("click", function() {
		var dat = { "id" :  "ee", "password" : "dddd" }
		
	$.ajax({
		url: "homepage/Add",
		type: "get",
		data: dat,
		success : function(data) {
			alert("성공!");
			$("#text").text(data);
			},
		error : function() {
			alert("실패!");
			}
			
			});
		});
	});
	
</script>
 --%> 
 

</head>
<body>
<a onclick="document.querySelector('contents').innerHTML ='<h2>HTML</h2>HTML is.....';">HTML</a>
<a onclick="document.querySelector('contents').innerHTML = '<h2>CSS<h2>CSS is.....';">CSS</a>
<a onclick="document.querySelector('contents').innerHTML ='<h2>AJAX</h2>AJAX is.....';">AJAX</a>

<h2>CSS.....is.....</h2>

<contents>

</contents>
</body>
</html>