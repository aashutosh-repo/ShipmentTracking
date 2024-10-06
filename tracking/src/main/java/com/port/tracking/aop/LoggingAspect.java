package com.port.tracking.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.port.tracking..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.port.tracking..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("After method: " + joinPoint.getSignature().getName());
    }
}
