package ru.bellintegrator.task.response.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@EnableAspectJAutoProxy(proxyTargetClass=true)
@Component
@Slf4j
public class MyAspect {


    @AfterReturning(value = "@annotation(LogExecutionMethod)", returning = "ret")
    public void logExecutionMethod(JoinPoint joinPoint, Object ret) {
        String message = joinPoint.getSignature().getName();
        log.info(message + " returning " + ret);
    }

    @Around(value = "@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String message = joinPoint.getSignature().getName();
        log.info(message + " completed for " + executionTime + "ms");

        return  proceed;
    }
}
