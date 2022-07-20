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
}
