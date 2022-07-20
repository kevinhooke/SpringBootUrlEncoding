package kh.springboot.urldecode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlDecondeController {

	@GetMapping(value = "/example")
	public String handleUrlDecode(@RequestParam String email) {
		
		System.out.println("/example?email received: " + email);
		
		return "success";
	}
}
