package personal.board.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



@Controller
public class FineDustController {
	
	@RequestMapping("FineDustMain")
	public String FineDustMain() {
		return "personal/FineDustMain"; 
	}
	
	@GetMapping("fineDust")
	@ResponseBody
	public Object FineDustRequest(@RequestParam(value="stationName") String stationName) throws UnsupportedEncodingException {
		String key = "XHdBPXDvwDyK51xmj8Onfl76PpmSE%2FWvQxPvMt6ZZPCWoJYOMney38kmg%2Bto%2Bxp%2F7IXlQjS%2FQLcmSnnh%2BnsTmw%3D%3D";
		String url =
		"http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";
		String decodeServiceKey = URLDecoder.decode(key,"UTF-8");
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application","json",Charset.forName("UTF-8")));
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				 .queryParam("ServiceKey",decodeServiceKey)
				 .queryParam("numOfRows",1)
				 .queryParam("pageNo", 1)
				 .queryParam("stationName", stationName)
				 .queryParam("dataTerm", "DAILY")
				 .queryParam("ver", "1.3")
				 .build(false);
		 
		Object response =
				restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
		System.out.println(response);
		return response;
	}
}
