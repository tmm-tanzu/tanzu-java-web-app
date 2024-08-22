package com.vmware.tanzu.app;

import org.springframework.cloud.bindings.Bindings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
class InfoController {

    private final Mono<List<ServiceBindingInfo>> serviceBindingInfos;
    private final Mono<CloudInfo> cloudInfo;
    private final Mono<Info> info;

    InfoController(Mono<List<ServiceBindingInfo>> serviceBindingInfos, Mono<CloudInfo> cloudInfo, Mono<Info> info) {
        this.serviceBindingInfos = serviceBindingInfos;
        this.cloudInfo = cloudInfo;
        this.info = info;
    }

    @GetMapping("/")
    Mono<Rendering> info() {
        return Mono.zip(serviceBindingInfos, cloudInfo, info)
                .map(t -> Rendering
                        .view("info")
                        .modelAttribute("serviceBindingInfos", t.getT1())
                        .modelAttribute("cloudInfo", t.getT2())
                        .modelAttribute("info", t.getT3())
                        .build());
    }
}
