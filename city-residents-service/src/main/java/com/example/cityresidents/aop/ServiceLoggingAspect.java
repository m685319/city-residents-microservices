package com.example.cityresidents.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ServiceLoggingAspect {

    @Before("execution(* com.example.cityresidents.service..*(..))")
    public void logServiceMethodCall(JoinPoint joinPoint) {
        log.info(
                "Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs())
        );
    }
}
