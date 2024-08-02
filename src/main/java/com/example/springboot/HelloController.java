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
        model.addAttribute("publicIP", myIP());

        return "index";
    }

    private String myIP() {
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
            System.out.println("My current IP address is " + s.next());
            return s.next();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
}
