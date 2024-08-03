package com.vmware.tanzu.app;

import reactor.util.function.Tuple3;
import software.amazon.awssdk.imds.Ec2MetadataResponse;

record CloudInfo(String availabilityZone, String hostname, String macAddress) {
    static final CloudInfo UNKNOWN = new CloudInfo("Unknown", "Unknown", "Unknown");

    CloudInfo(Tuple3<Ec2MetadataResponse, Ec2MetadataResponse, Ec2MetadataResponse> t) {
        this(t.getT1().asString(), t.getT2().asString(), t.getT3().asString());
    }
}
