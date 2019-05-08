package com.taoqy.aspect.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author taoqy
 * @see com.digital.aspect.ServiceAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceErrorDeal {
    HttpStatus status() default HttpStatus.OK;
    String errorDescription() default "";
}
