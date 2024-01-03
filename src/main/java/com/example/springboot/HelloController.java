package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		ApplicationInfoBean app_info = new ApplicationInfoBean();
		return String.format("Hello from TAP! (%s)", app_info.availabilityZone);
	}
}