package com.vmware.tanzu.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@SuppressWarnings("SameReturnValue")
class DownController {

    private final Logger logger = LoggerFactory.getLogger(DownController.class);

    private final ApplicationEventPublisher publisher;

    DownController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/exit")
    String exit() {
        Mono.delay(Duration.ofSeconds(1))
                .subscribe(i -> {
                    logger.info("Exiting after request to /exit");
                    System.exit(0);
                });
        return "down";
    }
    @PostMapping("/break")
    String brake() {
        logger.info("Breaking after request to /break");
        AvailabilityChangeEvent.publish(publisher, this, LivenessState.BROKEN);
        return "down";
    }
}
