<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/Carousel.css" rel="stylesheet" type="text/css">
<link href="../resources/Main.css" rel="stylesheet" type="text/css">
<meta charset="EUC-KR">
<title>홈페이지 메인</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function(){
		var duration = 200;
		var $side = $('#sidebar');
		var $sidebt = $side.find('button').on('mousedown', function(){
			$side.toggleClass('open')

			if($side.hasClass('open')) {
				$side.stop(true).animate({left:'0px'}, duration).blur();
			}else{
				$side.stop(true).animate({left:'-250px'}, duration).blur();
			};	
		});
	});
</script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
<script src="../resources/jquery.cloud9carousel.js"></script> 
<script src="../resources/jquery.reflection.js"></script>
<script>
$(function() {
var showcase = $("#showcase")
showcase.Cloud9Carousel( {
yPos: 62,
yRadius: 70,
xRadius:600,
mirrorOptions: {
gap: 0.5,
height: 0.2
},
buttonLeft: $(".nav > .left"),
buttonRight: $(".nav > .right"),
autoPlay: true,
bringToFront: true,
/* onRendered: showcaseUp<a href="https://www.jqueryscript.net/time-clock/">date</a>d */
onLoaded: function() {
showcase.css( 'visibility', 'visible' )
showcase.css( 'display', 'none' )
showcase.fadeIn( 1500 )
}
})

function showcaseUpdated( showcase ) {
var title = $('#item-title').html(
$(showcase.nearestItem()).attr( 'alt' ))
var c = Math.cos((showcase.floatIndex() % 1) * 2 * Math.PI)
title.css('opacity', 0.5 + (0.5 * c))
}

// Simulate physical button click effect
$('.nav > button').click( function( e ) {
var b = $(e.target).addClass( 'down' )
setTimeout( function() { b.removeClass( 'down' ) }, 80 )
})

$(document).keydown( function( e ) {

switch( e.keyCode ) {
/* left arrow */
case 37:
$('.nav > .left').click()
break

/* right arrow */
case 39:
$('.nav > .right').click()
}
} )
})
</script>

</head>
<body>

<div class="box" style="z-index: 20;position: absolute;">
  <select style="border-radius: 10px 10px 10px 10px;font-weight: 550;">
    <option>서울</option>
    <option>경기</option>
    <option>인천</option>
    <option>강원</option>
    <option>부산</option>
    <option>제주</option>
  </select>
</div>
<div class="box2" style="z-index: 20;position: absolute;">
  <select style="border-radius: 10px 10px 10px 10px;font-weight: 550;">
    <option>한식</option>
    <option>중식</option>
    <option>일식</option>
    <option>양식</option>
    <option>분식</option>
    <option>카페</option>
  </select>
</div>

<input type="text" id="searchbox" style="position: absolute;margin-left: 47%;margin-top:7.65%; z-index: 21;
outline: none;width: 435px;height: 40px;border-radius: 10px 10px 10px 10px;background-color: white;border-color: #F2C8C8;
font-size: x-large; border: no">

<div style="position: absolute;margin-left: 69.65%;margin-top: 7.6%;">
<button style="float: right;
margin-left: 20px;margin-right: 40px;border-radius: 10px;background: #665F79;
position: absolute; float: right;z-index: 17;" id="search">검색</button></div>


<div style="position: absolute;margin-left: 84.5%;margin-top: 2%;">
<button style="float: right;
margin-left: 20px;margin-right: 40px;border-radius: 10px;background: #665F79;
position: absolute; float: right;z-index: 17;border-radius: 30px 30px 30px 30px;" id="login">로그인</button></div>

<div style="position: absolute;margin-left: 90%;margin-top: 2%;">
<button style="float: right;
margin-left: 20px;margin-right: 40px;border-radius: 10px;background: #665F79;
position: absolute; float: right;z-index: 17;border-radius: 30px 30px 30px 30px;" id="signup">회원가입</button></div>
 
<div style="position: absolute;margin-left: 24%;margin-right: 17%;
margin-top: 7%;">
<button style=" width: 1000px;height:70px;border-radius: 10px;
/* margin-top: 133px; */border: none;outline: 0;background-color: #FFFFFF"></button>
</div>
<div id="sideWrap" 
		style="background: rgba(243,129,129,0.9); margin-top: -20px;
  		/* background: -webkit-linear-gradient(top, rgba(252, 227, 138, 0.9), rgba(243,129,129,0.9)); */
   		/* background: -o-linear-gradient(top, rgba(252, 227, 138, 0.9), rgba(243,129,129,0.9)); */
   		/* background: linear-gradient(to bottom, rgba(252, 227, 138, 0.9), rgba(243,129,129,0.9)); */"> 
   		</div>
   	
   	<div style="margin-left: 96%;">
   	<img src="../resources/images/logo/capture.PNG" style="position: absolute;
   margin-top: -10px; border-radius: 20px 20px 0 0;">
   </div>
		<aside id="sidebar">
                  <ul style="margin-left: 2px;">
                     <li><b>예약하기</b></li>
                     <li><b>내주변 음식점 보기</b></li>
                     <li><b>내정보</b></li> 
                     <li><b>문의사항</b></li>
                     <br/>
                     <br/>
                  </ul>
                  <button id="menubtn" style="opacity: 1;
                  border-radius: 0px 20px 20px 0px;"> 
                  <img src="../resources/images/logo/qqq.png" style="width:70px; height: 70px;
                  margin-right: -40%;">
                  </button>
                  </aside>
			
	<!-- </div> -->
	<div align="center" id="showcase" style="position: absolute;z-index: 1;position: absolute; margin-top: 135px;
	background-color: #FFFFFF;outline: 0;box-shadow: 0px 0px 0px 0px;width: 75%;margin-left: 12.5%;
	border-radius: 20px 20px 20px 20px;/* background-image: url('../resources/images/bg.jpg') */;border-color: black;border: 10px;
	background-repeat: no-repeat;background-size : cover;">
		<img class="cloud9-item" name="image" src="../resources/images/image_bar.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/image_dining02.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius:10px 10px 10px 10px;" alt="Alt" >
		<img class="cloud9-item" name="image" src="../resources/images/image_restaurant03.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/image_cafe.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/image_garden.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/image_china.JPG" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/restaurant_01.jpg" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/restaurant_01.jpg" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
		<img class="cloud9-item" name="image" src="../resources/images/dubai.jpg" width="330" height="230" style="margin-top: 68px;
		position: absolute;border-radius: 10px 10px 10px 10px;" alt="Alt">
	</div>
	 
	<p id="item-title" style="height: 90px;">&nbsp;</p> 
	
	<div style="position: absolute;">
	<ul style="margin-top: -9.5%; padding-bottom: 2px;">
	<li style="list-style: none;color: white;font-size: 12px;
	padding-left: 770px;">[About Us] | [Customer Service] | [Subscribe to RSS]</li> <br/>
	<li style="list-style: none;color: white;font-size: 12px;
	padding-left: 660px;">[Site Map] | [Search Terms] | [Advanced Search] | [Orders & Returns] | [Help] | [Contact Us]</li> <br/>
	 <li style="list-style: none;color: white;font-size: 12px;
	 padding-left: 840px;">Copyright Information 2014</li>
	 </ul> 
	</div>
 
</body>
</html>
