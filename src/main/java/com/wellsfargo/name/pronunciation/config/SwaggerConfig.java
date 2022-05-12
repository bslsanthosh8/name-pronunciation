package com.wellsfargo.name.pronunciation.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Pronunciation-Tool")
                .packagesToScan("com.wellsfargo.name.pronunciation")
                .pathsToMatch("/**")
                .build();
    }
}
