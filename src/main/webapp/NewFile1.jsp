<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>

var config = [
	{field : "name", width : 100},
	{field : "phoneNumber", width : 100},
	{field : "address", width : 200}
	];

var data = [
	{"name" : "Ariana", "phoneNumber" : 01077778888, "address" : "강남"},
	{"name" : "Kesha", "phoneNumber" : 0107755888, "address" : "강남"},
	{"name" : "Rihanna", "phoneNumber" : 0107788888, "address" : "강남"}
];

window.onload = function() {
	var singer = $(#singer); //id가 singer 인 태그를 참조
	var table = $("<table>").appendTo(singer);
	table.css("border" : "3px red solid");
	var thead = $("<thead>").appendTo(table);
	
	$.each(config, function(index, row) {
	var th = $("<th>").appendTo(thead);
	th.html(row[fieldInfo.field]);
	});
	
	$.each(config, function(index, row){
	
	
		});

	
}

</script> 
</head>
<body>
<div id="singer" class="singer"></div>


</body>
</html>