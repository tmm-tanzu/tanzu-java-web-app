package com.vmware.tanzu.app;

import org.springframework.cloud.bindings.Bindings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
class InfoController {

    private final Mono<Bindings> bindings;
    private final Mono<CloudInfo> cloudInfo;
    private final Mono<Info> info;

    InfoController(Mono<Bindings> bindings, Mono<CloudInfo> cloudInfo, Mono<Info> info) {
        this.bindings = bindings;
        this.cloudInfo = cloudInfo;
        this.info = info;
    }

    @GetMapping("/")
    Mono<Rendering> info() {
        return Mono.zip(bindings, cloudInfo, info)
                .map(t -> Rendering
                        .view("info")
                        .modelAttribute("bindings", t.getT1())
                        .modelAttribute("cloudInfo", t.getT2())
                        .modelAttribute("info", t.getT3())
                        .build());
    }
}
