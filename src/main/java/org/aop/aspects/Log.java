package org.aop.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation on methods that need to be logged.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR
    }

    Level level() default Level.INFO;

}