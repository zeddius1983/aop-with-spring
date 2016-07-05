package org.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Aspect
@Component
@Priority(200)
public class LoggingAspect {

    @Autowired
    private TraceBuilder traceBuilder;

    @Pointcut("execution(* *(..)) && " + "@annotation(log)")
    private void logAnnotatedMethod(Log log) {
    }

    @Around("logAnnotatedMethod(log)")
    public Object aroundInvoke(ProceedingJoinPoint pjp, Log log) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String traceLog = traceBuilder.build(pjp);
        Object result;
        try {
            result = pjp.proceed();
            log(logger, log.level(), traceLog, result);
            return result;
        } catch (Exception e) {
            logger.error("{} -> threw {}", traceLog, e.getClass().getName());
            throw e;
        }
    }

    private void log(Logger logger, Log.Level logLevel, String traceLog, Object result) {
        switch (logLevel) {
            case INFO:
                logger.info("{} -> {}", traceLog, result);
                break;
            case TRACE:
                logger.trace("{} -> {}", traceLog, result);
                break;
            case DEBUG:
                logger.debug("{} -> {}", traceLog, result);
                break;
            case ERROR:
                logger.error("{} -> {}", traceLog, result);
                break;
            case WARN:
                logger.warn("{} -> {}", traceLog, result);
                break;
        }
    }

}
