package org.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * Used to profile method invocations.
 *
 * @author Alexander Valyugin
 */
@Aspect
@Component
public class ProfilingAspect {

    @Pointcut("execution(* *(..)) && " + "@annotation(profile)")
    private void profileAnnotatedMethod(Profile profile) {
    }

    @Around("profileAnnotatedMethod(profile)")
    public Object aroundInvoke(ProceedingJoinPoint pjp, Profile profile) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            logger.info("{} in {} ms", method.getName(), stopWatch.getTotalTimeMillis());
        }
    }
}
