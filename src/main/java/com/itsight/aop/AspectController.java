package com.itsight.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectController {

    private static final Logger logger = LogManager.getLogger(AspectController.class);

    @Before("within(com.itsight.controller..*) && !@annotation(com.itsight.annotation.NoLogging)")
    private void before(JoinPoint joinPoint) {
        String caller = joinPoint.getSignature().toShortString();
        logger.info(caller + " method called.");
    }

}
