package kh.springboot.urldecode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlDecondeController {

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
