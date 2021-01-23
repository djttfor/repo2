package com.ex.smp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class RuntimeAdvice {
    private static final Logger log = LoggerFactory.getLogger(RuntimeAdvice.class);

    @Pointcut("execution(* com.ex.smp.service.*.*.*(..))")
    public void runtime(){}

    @Around("runtime()")
    public Object runtimeAdvice(ProceedingJoinPoint pjp){
        try {
            long startTime = System.currentTimeMillis();
            log.info("开始时间为:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
            Object result = pjp.proceed(pjp.getArgs());
            long endTime = System.currentTimeMillis();
            log.info("程序运行时间为:{}ms",endTime-startTime);
            log.info("开始时间为:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
    }
}
