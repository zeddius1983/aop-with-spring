package org.aop;

import org.aop.aspects.LoggingAspect;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Additional configuration for AspectJ load time weaving.
 *
 * @author Alexander Valyugin
 */
@Configuration
@Conditional(LoadTimeWeavingConfig.AspectJEnabled.class)
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
public class LoadTimeWeavingConfig {

    public static class AspectJEnabled implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return Boolean.valueOf(System.getProperty("useLoadTimeWeaving"));
        }
    }

    @Bean
    public LoggingAspect loggingAspect() {
        LoggingAspect loggingAspect = Aspects.aspectOf(LoggingAspect.class);
        return loggingAspect;
    }

}
