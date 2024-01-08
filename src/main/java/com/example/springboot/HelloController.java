package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.Instant;


@Controller
public class HelloController {

    private final ApplicationInfo cloudMetadata;

    public HelloController(ApplicationInfo cloudMetadata) {
        this.cloudMetadata = cloudMetadata;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("hostname", cloudMetadata.hostname);
        model.addAttribute("availabilityZone", cloudMetadata.availabilityZone);
        model.addAttribute("mac", cloudMetadata.mac);

        Instant timestamp = Instant.now();
        model.addAttribute("timestamp", timestamp);

        return "index";
    }
}
