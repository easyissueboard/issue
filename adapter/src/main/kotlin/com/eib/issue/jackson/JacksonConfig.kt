package com.eib.issue.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder
            .json()
            .propertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy())
            .build()
    }
}