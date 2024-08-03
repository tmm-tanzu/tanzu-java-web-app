package com.vmware.tanzu.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bindings.Bindings;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.imds.Ec2MetadataAsyncClient;

@SpringBootApplication
public class TanzuJavaWebApplication {

    private final Logger logger = LoggerFactory.getLogger(TanzuJavaWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TanzuJavaWebApplication.class, args);
    }

    @Bean
    Mono<Bindings> bindings() {
        return Mono.fromSupplier(Bindings::new);
    }

    @Bean
    Mono<CloudInfo> cloudInfo() {
        Ec2MetadataAsyncClient client = Ec2MetadataAsyncClient.create();

        return Mono.zip(
                Mono.fromFuture(client.get("/latest/meta-data/placement/availability-zone")),
                Mono.fromFuture(client.get("/latest/meta-data/hostname")),
                Mono.fromFuture(client.get("/latest/meta-data/mac"))
        )
                .doFinally(t -> client.close())
                .map(CloudInfo::new)
                .onErrorResume(t -> {
                    logger.warn("Unable to retrieve cloud metadata", t);
                    return Mono.just(CloudInfo.UNKNOWN);
                })
                .cache();
    }

    @Bean
    Mono<Info> info() {
        return WebClient.create("https://api.ipify.org")
                .get().retrieve().bodyToMono(String.class)
                .map(Info::new)
                .onErrorResume(t -> {
                    logger.warn("Unable to retrieve public IP", t);
                    return Mono.just(Info.UNKNOWN);
                })
                .cache();
    }
}
