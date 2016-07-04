package org.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;

/**
 * Defines the aspects application order.
 *
 * @author Alexander Valyugin
 */
@Aspect
@DeclarePrecedence("ProfilingAspect, LoggingAspect")
public class AspectsOrder {
}
