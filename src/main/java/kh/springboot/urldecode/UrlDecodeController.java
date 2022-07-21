package kh.springboot.urldecode;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UrlDecodeController {

	/**
	 * @GetMapping showing that request params mapped with @ReqeustParam
	 * handle url encoded values automatically.
	 * 
	 * Example: curl http://localhost:8080/example?param1=test1%26test2
	 * 
	 * Prints:
	 * 
	 * /example?param1 received: test1&test2
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping(value = "/example")
	public String handleUrlDecode(@RequestParam String param1) {
		
		System.out.println("/example?param1 received: " + param1);
		
		return "success";
	}
	
	/**
	 * Example showing @PostMapping accepting a POST body with url encoded values and
	 * handling them automatically, url decoding any escaped values
	 * 
	 * Example:
	 * curl -X POST "http://localhost:8080/example2?param1=test1%26test2" -d "value1=test3%26test4"
	 * 
	 * Prints:
	 * /example2?param1 received: test1&test2
	 * request body - value1: test3&test4
	 * 
	 * @param param1
	 * @param payload
	 * @return
	 */
	@PostMapping(value = "/example2")
	public String handleUrlDecodeInPostRequest(@RequestParam String param1,
			ExamplePayload payload) {
		
		System.out.println("/example2?param1 received: " + param1);
		
		System.out.println("request body - value1: " + payload.getValue1());
		
		return "success";
	}
	
	
	/**
	 * Get method that uses RestTemplate to call POST /example2 passing 
	 * values that need to url encoded.
	 * 
	 * This shows RestTemplate will url encode and values passed to it as params or post body, and
	 * the Controller will decode these automatically.
	 * 
	 * @return
	 */
	@GetMapping(value = "/request")
	public String request() {
	    String url =  "http://localhost:8080/example2?param1=test1&test2";
	    System.out.println(url);
	    RestTemplate restTemplate = new RestTemplate();
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	    map.add("value1","test1&test2");
	    
	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers); 
	    
	    return restTemplate.postForObject(url, request, String.class);
	}
	
	class ExamplePayload{
		
		private String value1;
		public String getValue1() {
			return value1;
		}

		public void setValue1(String value1) {
			this.value1 = value1;
		}

		public String getValue2() {
			return value2;
		}

		public void setValue2(String value2) {
			this.value2 = value2;
		}

		private String value2;
		
		public ExamplePayload() {
			
		}
	}
	
}
