package com.dxc.bffService.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.dxc.brrService.api")
public class BookConfig {
}
