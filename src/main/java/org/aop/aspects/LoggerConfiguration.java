package org.aop.aspects;

import org.aspectj.lang.Aspects;
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

    @Bean
    public LoggingAspect loggingAspect() {
        LoggingAspect loggingAspect = Aspects.aspectOf(LoggingAspect.class);
        return loggingAspect;
    }

}