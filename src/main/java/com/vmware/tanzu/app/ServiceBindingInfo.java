package com.vmware.tanzu.app;

import io.pivotal.cfenv.core.CfService;
import org.springframework.cloud.bindings.Binding;

import java.util.Optional;

record ServiceBindingInfo(String name, String type, String provider) {
    ServiceBindingInfo(Binding binding) {
        this(binding.getName(), binding.getType(), Optional.ofNullable(binding.getProvider()).orElse(""));
    }

    ServiceBindingInfo(CfService service) {
        this(service.getName(), service.getLabel(), "Cloud Foundry");
    }
}
