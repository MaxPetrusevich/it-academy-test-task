package com.example.controller.aop;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static com.example.database.data.Constants.*;


@Log4j2
@Aspect
@Component
public class LoggingService {
    private static final Logger logger = LogManager.getLogger(LoggingService.class);

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void isServiceLayer() {
    }

    @Pointcut("isServiceLayer() && execution(public * com.example.service.impl.UserServiceImpl.findAll*(..))")
    public void findAllServiceMethod() {
    }

    @Pointcut("isServiceLayer() && execution(public * com.example.service.impl.UserServiceImpl.create*(..))")
    public void createServiceMethod() {
    }

    @Before("createServiceMethod() && args(userDto)")
    public void createLogging(JoinPoint joinPoint, Object userDto) {
        logger.info(BEFORE + joinPoint.getSignature().getName() + WITH_ARGS_N + userDto);
    }

    @AfterReturning(value = "createServiceMethod()", returning = RESULT)
    public void createLoggingAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info(AFTER + joinPoint.getSignature().getName() + RETURNING_N + result);
    }

    @AfterThrowing(value = "createServiceMethod()", throwing = EXCEPTION)
    public void createLoggingAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error(AFTER + joinPoint.getSignature().getName() + THROWING + ex);
    }

    @After("createServiceMethod()")
    public void createLoggingAfter(JoinPoint joinPoint) {
        logger.info(AFTER + joinPoint.getSignature().getName());
    }

    @Before("findAllServiceMethod()")
    public void findAllLogging(JoinPoint joinPoint) {
        logger.info(BEFORE + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "findAllServiceMethod()", returning = RESULT)
    public void findAllLoggingAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info(AFTER + joinPoint.getSignature().getName() + RETURNING_N + result);
    }

    @AfterThrowing(value = "findAllServiceMethod()", throwing = EXCEPTION)
    public void findAllLoggingAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error(AFTER + joinPoint.getSignature().getName() + THROWING + ex);
    }

    @After("findAllServiceMethod()")
    public void findAllLoggingAfter(JoinPoint joinPoint) {
        logger.info(AFTER + joinPoint.getSignature().getName());
    }


}
