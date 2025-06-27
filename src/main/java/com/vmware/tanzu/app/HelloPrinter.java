package com.vmware.tanzu.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloPrinter {

    private static final Logger log = LoggerFactory.getLogger(HelloPrinter.class);

    @Scheduled(fixedRate = 10_000)
    public void printMessage() {
        log.info("Hello! This message prints every 10 seconds.");
    }
}
