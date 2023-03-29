package com.bbodeum.payment.control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.apply.service.ApplyService;

@RestController
public class PaymentController {
	
	@PostMapping(value = "payment/verify/webhook", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> webhookEndpint(@RequestBody JSONObject requestObj) throws Exception{
		//{ "imp_uid": "imp_1234567890", "merchant_uid": "order_id_8237352", "status": "paid" }' { NotificationURL }
		if(requestObj.get("status").equals("paid")) {
			requestObj.put("success", true);
			return new ResponseEntity<>(requestObj, HttpStatus.OK); 	
		} else {
			return new ResponseEntity<>("결제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "payment/verify/post", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postVerify(@RequestBody JSONObject requestObj) throws Exception{
		System.out.println("사후검증 시작");
		/** 토큰 발급 */
		String requestURL = "https://api.iamport.kr/users/getToken";
		//api용 키값
		String imp_key = URLEncoder.encode("3764804583583474", "UTF-8");
		String imp_secret = URLEncoder.encode("umFs5BdAi0f0f22O0lKjAadG8wYSaCBVal0n1Mls9grX2I0mcd1uBIHEq4uoKyOLrXEmrL30FEJh3Yr5", "UTF-8");
		JSONObject json = new JSONObject();
		json.put("imp_key", imp_key);
		json.put("imp_secret", imp_secret);
		
		String token = getToken(json, requestURL);
		System.out.println("사후검증 도큰 발급");
		
		/** 사후 검증 요청 */
		System.out.println("사후검증 요청 시작");
		String merchant_uid = requestObj.get("merchant_uid").toString();
		String imp_uid = requestObj.get("imp_uid").toString();
//		Long d = Long.parseLong(requestObj.get("d").toString());
//		Long c = Long.parseLong(requestObj.get("c").toString());
		
		requestURL = "https://api.iamport.kr/payments/"+imp_uid;		
		URL url = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String requestString = "";
		
//		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", token);
//		OutputStream os= connection.getOutputStream();

//		os.write(json.toString().getBytes());
		connection.connect();
		
		System.out.println("사후검증 결과 받아오기");
		StringBuilder sb = new StringBuilder();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("url 커넥션 ok");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			requestString = sb.toString();
			System.out.println("request String: "+ requestString);
		}
//		os.flush();
		connection.disconnect();

		System.out.println("사후검증 결과 파싱하기");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);
		JSONObject getResponse = new JSONObject();
		if((Long)jsonObj.get("code") == 0){
			System.out.println("결과값 코드: "+(Long)jsonObj.get("code"));
			getResponse = (JSONObject) jsonObj.get("response");	
			System.out.println("getResponse: "+getResponse.toString());
			String postImp = getResponse.get("imp_uid").toString();
			String postMerchant = getResponse.get("merchant_uid").toString();
			System.out.println("postImp"+postImp+"   postMerchant"+postMerchant);
			
			if(postImp.equals(imp_uid) && postMerchant.equals(merchant_uid)) {
				System.out.println("사후검증 결과 같아요~");
				//신청 db 업데이트
//				service.addApply(d, c, imp_uid, merchant_uid);
				return new ResponseEntity<>(getResponse, HttpStatus.OK); 				
			} else { //검증 결과 다름
				System.out.println("사후검증 결과가 달랐다");
				System.out.println("postImp"+postImp+"   postMerchant"+postMerchant);
				System.out.println("imp_uid"+imp_uid+"   merchant_uid"+merchant_uid);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 				
			}
		} else { //응답코드가 0이 아님
			System.out.println("사후검증 응답이 이상해");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 	
		}
	}
	
	/**
	 * 사전 검증
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "payment/verify/pre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> preVerify(@RequestBody JSONObject requestObj) throws Exception{
		String merchant_uid = requestObj.get("merchant_uid").toString();
		double amount = Double.parseDouble(requestObj.get("amount").toString());
		//토큰 발급
		String requestURL = "https://api.iamport.kr/users/getToken";
		//api용 키값
		String imp_key = URLEncoder.encode("3764804583583474", "UTF-8");
		String imp_secret = URLEncoder.encode("umFs5BdAi0f0f22O0lKjAadG8wYSaCBVal0n1Mls9grX2I0mcd1uBIHEq4uoKyOLrXEmrL30FEJh3Yr5", "UTF-8");
		JSONObject json = new JSONObject();
		json.put("imp_key", imp_key);
		json.put("imp_secret", imp_secret);
		
		String token = getToken(json, requestURL);
		
		//검증 요청
		requestURL = "https://api.iamport.kr/payments/prepare";
		json.clear();
		json.put("merchant_uid", merchant_uid);
		json.put("amount", amount);
		
		URL url = new URL(requestURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String requestString = "";
		
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Authorization", token);
		OutputStream os= connection.getOutputStream();

		os.write(json.toString().getBytes());
		connection.connect();

		StringBuilder sb = new StringBuilder();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			requestString = sb.toString();
		}
		os.flush();
		connection.disconnect();

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);
		JSONObject getResponse = new JSONObject();
		if((Long)jsonObj.get("code") == 0){
			getResponse = (JSONObject) jsonObj.get("response");	
		}
		System.out.println(getResponse.toString());
		return new ResponseEntity<>(getResponse, HttpStatus.OK); 
	}
	
	/**
	 * 토큰 발급
	 * @return 토큰, 200
	 * @throws Exception
	 */
	@PostMapping(value = "payment/verify/gettoken")//, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getToken() throws Exception{
		//요청 url
		String requestURL = "https://api.iamport.kr/users/getToken";
		//api용 키값
		String imp_key = URLEncoder.encode("3764804583583474", "UTF-8");
		String imp_secret = URLEncoder.encode("umFs5BdAi0f0f22O0lKjAadG8wYSaCBVal0n1Mls9grX2I0mcd1uBIHEq4uoKyOLrXEmrL30FEJh3Yr5", "UTF-8");
		JSONObject json = new JSONObject();
		json.put("imp_key", imp_key);
    	json.put("imp_secret", imp_secret);
    	
    	String token = getToken(json, requestURL);
		System.out.println("넘겨받은 토큰: "+token);
    	return new ResponseEntity<>(token, HttpStatus.OK); 
	}
		
	public String getToken( //HttpServletRequest request, HttpServletResponse response,
				JSONObject json, String requestURL) throws Exception{
		
		String _token = "";
		
		try{
			String requestString = "";
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os= connection.getOutputStream();
	
			os.write(json.toString().getBytes());
			connection.connect();
	
			StringBuilder sb = new StringBuilder();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				requestString = sb.toString();
			}
			os.flush();
			connection.disconnect();
	
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);
			if((Long)jsonObj.get("code") == 0){
				JSONObject getToken = (JSONObject) jsonObj.get("response");	
				System.out.println("getToken==>>"+getToken.get("access_token") );
				_token = (String)getToken.get("access_token");
			}
		}catch(Exception e){
			e.printStackTrace();
			_token = "";
		}
		return _token;
	}
}
