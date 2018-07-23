package com.tobert.cube.configuration

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class CubeUntapConfiguration {
    @Bean
    fun restTemplate(rtb: RestTemplateBuilder) : RestTemplate {
        return rtb.build()
    }
}