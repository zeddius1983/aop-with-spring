package org.aop.aspects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggerConfiguration {

    @Bean
    @Scope(value = "singleton")
    public TraceBuilder traceBuilder() {
        return new TraceBuilder() {};
    }

}