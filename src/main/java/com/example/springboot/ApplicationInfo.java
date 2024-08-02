package com.example.springboot;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ApplicationInfo {

    @Value("${ami-id:N/A}")
    public String amiId;

    @Value("${hostname:N/A}")
    public String hostname;

    @Value("${instance-type:N/A}")
    public String instanceType;

    @Value("${services/domain:N/A}")
    public String serviceDomain;

    @Value("${placement/availability-zone:N/A}")
    public String availabilityZone;

    @Value("${mac:N/A}")
    public String mac;
}