package com.vmware.tanzu.app;

import java.time.Instant;

record Info(String publicIp) {
    static final Info UNKNOWN = new Info("Unknown");

    @SuppressWarnings("unused")
    public Instant now() {
        return Instant.now();
    }
}
