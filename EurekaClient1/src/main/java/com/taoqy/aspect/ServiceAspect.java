package com.taoqy.aspect;


import com.taoqy.aspect.annotation.ServiceErrorDeal;
import com.taoqy.util.ResponseMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * service com.taoqy.aspect.log,service invoked
 * @author taoqy
 * @version 2018-11-20
 */
@Component
@Aspect
@EnableAspectJAutoProxy
public class ServiceAspect {

    private static Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
    @Pointcut(value = "@annotation(com.taoqy.aspect.annotation.ServiceErrorDeal)")
    public void servicePointCut(){

    }

//    @Before(value = "serviceUniquenessPointCut()")
//    public void doBefore(JoinPoint joinpoint){
//
//    }


    @Around("servicePointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        logger.warn("进入切面"+((MethodSignature) joinPoint.getSignature()).getMethod().getName());
        ServiceErrorDeal scu = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ServiceErrorDeal.class);
//        ((MethodSignature) joinPoint.getSignature()).getMethod().invoke()
        HttpStatus status = scu.status();
        String errorDescription = scu.errorDescription();

        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {

            logger.error(throwable.getMessage()+errorDescription);

           return new ResponseMessage(status, errorDescription);
        }
    }


}
