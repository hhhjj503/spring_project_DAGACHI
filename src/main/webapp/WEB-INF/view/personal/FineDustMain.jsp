<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>미세먼지</title>
<style>
	
	#background {
	margin-left: 19.5%;
    margin-top: 3.5%;
    background-color: antiquewhite;
    width: 1200px;
    height: 800px;
    background-image: url("../resources/images/skyunsplash.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    border-radius: 10px;
	}
	
	#searchBox {
	padding-left: 495px;
	
	}
	
	#youmiB {
	background-color: gray;
    width: 1000px;
    height: 500px;
    position: absolute;
    margin-left: 100px;
    margin-top: 200px;
    border-radius: 25px;
    opacity: 0.4;
	} 
	
	#youmi {
	margin-left: 550px;
    padding-top: 65px;
    color: white;
    text-shadow: 3px 3px 8px gray;
	}
	
	#gu {
	margin-left: 585px;
    margin-top: 90px;
    margin-bottom: 10px;
    position: absolute;
    text-shadow: 4px 4px 8px gray;
    color:  white;
	}
	
	#results{
	text-align: center;
    margin-left: 490px;
    margin-top: 140px;
    width: 240px;
    height: 180px;
    position: absolute;
    text-shadow: 4px 4px 8px gray;
    color: white;
	}
	
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
function fineDust() {
	var stationName = $("input[name=stationName]").val().trim();
	$.ajax({
		url:"fineDust",
		data: {stationName : stationName},
		type: "get",
		success: function(data){
			var document = data;
			var result = document.documentElement;
			console.log(result);
			html ="";
			var totalCount = result.getElementsByTagName("totalCount")[0].textContent;
			if(totalCount == 0) {
            	html += "<br/>검색은 구를 기준으로 해주세요.<br/> ex) 강남 -> 강남구";
            	$("#results").html(html);
            	return;
            	}
			var pm10Value = result.getElementsByTagName("pm10Value")[0].textContent; //미세먼지농도
			var pm10Value24 = result.getElementsByTagName("pm10Value24")[0].textContent; //24시간예측농도
			var pm25Value = result.getElementsByTagName("pm25Value")[0].textContent; //미세먼지농도
			var pm25Value24 = result.getElementsByTagName("pm25Value24")[0].textContent; //24시간예측농도
			var pm10Grade = result.getElementsByTagName("pm10Grade")[0].textContent; 
            var pm10Grade1h = result.getElementsByTagName("pm10Grade1h")[0].textContent; // [0].textContent
            var dataTime = result.getElementsByTagName("dataTime")[0].textContent;
            var mangName = result.getElementsByTagName("mangName")[0].textContext;
        	
        	if(pm10Value == "-" && pm10Value == "-" && pm25Value =="-" && pm25Value24 == "-") {
				html += "<br/>" + stationName + " 의 전송받은 미세먼지 데이터가 없습니다.";
				$("#results").html(html);
				$("#gu").html("");
				return;
                } else {
            html += "<b>" + stationName + "</b><br/>";  
            $("#gu").html(html);
            html ="";
           	html += "미세먼지 농도 : " + pm10Value +" ㎍/㎥<br/><br/>";
           	html += "24시간 예측농도 : " + pm10Value24 +" ㎍/㎥<br/><br/>";
           	html += "초미세먼지 농도 : " +  pm25Value +" ㎍/㎥<br/><br/>";
           	html += "24시간 예측농도 : " + pm25Value24 +" ㎍/㎥<br/><br/>";
           	var grade24Hour;
           	if(pm10Grade == "1") {
           		grade24Hour = "(좋음)";
               	} else if (pm10Grade == "2") {
               		grade24Hour = "(보통)";
                   	} else if (pm10Grade == "3") {
                   		grade24Hour = "(나쁨)";
                   		} else if (pm10Grade == "4") {
                   			grade24Hour = "(매우나쁨)";
                  		}
           	html += "24시간등급 : " + pm10Grade + " " + grade24Hour+ "<br/><br/>";
        	var gradeHour;
           	if(pm10Grade == "1") {
           		gradeHour = "(좋음)";
               	} else if (pm10Grade == "2") {
               		gradeHour = "(보통)";
                   	}else if (pm10Grade == "3") {
                   		gradeHour = "(나쁨)";
                   		}else if (pm10Grade == "4") {
                   			gradeHour = "(매우나쁨)";
                  		}
            html += "최근 1시간 등급  : "+ pm10Grade1h+" " + gradeHour +"<br/><br/>";
            html += "측정시각 : " + dataTime +"<br/><br/>";
            $("#results").html(html);
                }
            }
        });
		}

</script>
</head>
<body>
<div id="background">

<div id="youmiB"></div>


<div id="youmi"><h2 >YOU ＆  MI</h2></div>
<div id="searchBox" >
<input type="text" id="stationName" name="stationName" placeholder="구를 입력해주세요. ex)강남구" style="width: 200px; height: 20px;">
<input type="button" value="검색" onclick="fineDust()" />
</div>
<div id='gu' ></div>
<div id="results" name="results" >
<b>미세먼지 정보를 알려드립니다.<br/><br/>
검색창에 구를 입력해주세요.
</b>

</div>

</div>
</body>
</html>