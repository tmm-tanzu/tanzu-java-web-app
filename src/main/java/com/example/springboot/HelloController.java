package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.*;
import java.io.*;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {

		try {			
			URL url = new URL("http://169.254.169.254/latest/meta-data/placement/availability-zone");
			InputStream is = url.openStream();
			StringBuilder sb = new StringBuilder();
			String az;

			System.out.println("Curling metadata server\n");

			int ch;
			while ((ch = is.read()) != -1) {
				sb.append((char) ch);
				System.out.print((char) ch);
			}
			
			az = sb.toString();
			return String.format("Hello from Morgan's TAP! (%s)", az);
		
		} catch (MalformedURLException e) {
			System.out.println("Malform - Curling metadata server");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOExcept - Curling metadata server");
			e.printStackTrace();
		} finally {
			System.out.println("\n\nFinally - Curling metadata server");
		}


		// ApplicationInfoBean app_info = new ApplicationInfoBean();
		return "Hello from Morgan's TAP! (AZ not found)";
	}
}