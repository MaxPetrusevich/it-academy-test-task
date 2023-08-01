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
public class LoggingController {
    private static final Logger logger = LogManager.getLogger(LoggingController.class);


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void isControllerLayer() {
    }


    @Pointcut("execution(public * com.example.controller.controllers.UserController.add*(..))")
    public void addMethod() {
    }

    @Pointcut("execution(public * com.example.controller.controllers.UserController.findAll*(..))")
    public void findAllMethod() {
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void hasPostMapping() {
    }

    @Before("hasPostMapping() && addMethod() && args(userDto)")
    public void addLogging(JoinPoint joinPoint, Object userDto) {
        logger.info(BEFORE + joinPoint.getSignature().getName() + ARGS + userDto);
    }

    @AfterReturning(value = "addMethod()", returning = RESULT)
    public void addLoggingAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info(AFTER + joinPoint.getSignature().getName() + RETURNING_N + result);
    }

    @AfterThrowing(value = "addMethod()", throwing = EXCEPTION)
    public void addLoggingAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error(AFTER + joinPoint.getSignature().getName() + THROWING + ex);
    }

    @After("addMethod()")
    public void addLoggingAfter(JoinPoint joinPoint) {
        logger.info(AFTER + joinPoint.getSignature().getName());
    }

    @Before("findAllMethod() && hasGetMapping()")
    public void findAllLogging(JoinPoint joinPoint) {
        logger.info(BEFORE + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "findAllMethod() && hasGetMapping()", returning = RESULT)
    public void findAllLoggingAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info(AFTER + joinPoint.getSignature().getName() + RETURNING_N + result);
    }

    @AfterThrowing(value = "findAllMethod() && hasGetMapping()", throwing = EXCEPTION)
    public void findAllLoggingAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error(AFTER + joinPoint.getSignature().getName() + THROWING + ex);
    }

    @After("hasGetMapping() && findAllMethod()")
    public void findAllLoggingAfter(JoinPoint joinPoint) {
        logger.info(AFTER + joinPoint.getSignature().getName());
    }

}
