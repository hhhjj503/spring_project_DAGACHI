<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
function fetchme(mapping) {
	fetch(mapping).then( function(response) { response.text().then( function(text) { 
	document.querySelector('article').innerHTML = text;
	
	 }) 
 })
} 
</script>
</head>
<body>


	<!-- text().then(function(text(객체생성))) -->

 <input type="button" value="fetch"
 onclick="fetchme('comment/commentWrite')" />

<a href="#three" >go three</a>
<p>
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!
sassaklcascascklacnascnmnzxm,cnzxm,cncm,x
qwwqqwqq!!!!!!!!!!!!!!!1qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
qwwqqwqq!!!!!!!!!!!!!!!1
</p>


<article style="width: 800px;height: 200px; background-color: blue;">

</article>	
</body>
</html>