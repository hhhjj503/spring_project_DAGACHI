package personal.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineDustDto {

	String pm10Value; //�̼�������
	String pm10Value24; //24�ð�������
	String pm25Value; //�̼�������
	String pm25Value24; //24�ð�������
	
	String pm10Grade; //�̼����� 24�ð����
	String pm10Grade1h; //�̼����� 1�ð� ���
	
	
}
