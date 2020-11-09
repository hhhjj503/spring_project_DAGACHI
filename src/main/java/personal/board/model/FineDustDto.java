package personal.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineDustDto {

	String pm10Value; //미세먼지농도
	String pm10Value24; //24시간예측농도
	String pm25Value; //미세먼지농도
	String pm25Value24; //24시간예측농도
	
	String pm10Grade; //미세먼지 24시간등급
	String pm10Grade1h; //미세먼지 1시간 등급
	
	
}
