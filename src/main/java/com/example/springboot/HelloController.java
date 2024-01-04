package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class HelloController {

    private final ApplicationInfo cloudMetadata;

    public HelloController(ApplicationInfo cloudMetadata) {
        this.cloudMetadata = cloudMetadata;
    }

    @RequestMapping("/")
    public String index() {
        return String.format("Availability Zone: %s<br>" +
			"Hostname: %s<br>" +
			"Mac: %s<br>" +
			"",
			cloudMetadata.availabilityZone,
			cloudMetadata.hostname,
			cloudMetadata.mac);
    }
}
