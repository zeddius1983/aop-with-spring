package org.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Builds trace from join point params...
 *
 * @author Alexander Valyugin
 */
public interface TraceBuilder {

    default String build(ProceedingJoinPoint pjp) {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        String traceLog = method.getName() +
                "(" +
                Arrays.stream(pjp.getArgs())
                        .map(it -> it == null ? "null" : it.toString())
                        .collect(Collectors.joining(", ")) +
                ")";
        return traceLog;
    }

}
