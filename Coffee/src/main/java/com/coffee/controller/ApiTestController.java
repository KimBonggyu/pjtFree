package com.coffee.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apiTest")
public class ApiTestController {

	/*
	 * api 테스트 화면 이동
	 */
	@GetMapping(value = {"/", "/apiTest"})
	public String apiTest() {
		
		return "/page/apiTest/apiTest";
	}
	
	@GetMapping("/postTrackInfo")
	public String postTrackInfo() throws Exception{
		
		String result = "";
		
		try {
			
			// 1. URL 세팅
			StringBuilder urlBuilder = new StringBuilder("http://openapi.epost.go.kr/trace/retrieveLongitudinalCombinedService/retrieveLongitudinalCombinedService/getLongitudinalCombinedList");
			
			// 2. 오픈 API 요청 규격에 맞는 파라미터 세팅
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + "dAkFsAblXe8piTieCK5euQim2%2FwdrcmrLRTRXgTNiPmWt14f3IDvH5CODalUeBeB74ESSGLEN4Hmj4FGKfZ23Q%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("rgist", "UTF-8") + "=" + "8690770027579");	// 등기번호 샘플데이터
			
			// 3. URL 객체 생성
	        URL url = new URL(urlBuilder.toString());
	        
	        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        // 5. 통신을 위한 메소드 세팅
	        conn.setRequestMethod("GET");
	        
	        // 6. 통신을 위한 Content-type 세팅
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        // 7. 통신 응답 코드 확인
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        // 8. 전달받은 데이터를 BufferedReader 객체로 세팅
	        BufferedReader rd;
	        
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장
	        StringBuilder sb = new StringBuilder();
	        String line;
	        
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        // 10. 객체 해제.
	        rd.close();
	        conn.disconnect();
	        
	        // 11. 전달받은 데이터 확인.
	        System.out.println(sb.toString());
			
		} catch (Exception e) {
			throw new Exception("e", e);
		}
		
		return result;
	}
}
