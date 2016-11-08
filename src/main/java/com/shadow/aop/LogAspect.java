package com.shadow.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by madness on 2016/11/9 0:22
 */
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);



    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com..controller.*Controller.test*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明前置通知
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        LOGGER.debug("前置通知");
    }

    /**
     * 声明后置通知
     *
     * @param result
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        LOGGER.debug("后置通知");
        LOGGER.debug("---" + result + "---");
    }

    /**
     * 声明例外通知
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        LOGGER.debug("例外通知");
        LOGGER.debug(e.getMessage());
    }

    /**
     * 声明最终通知
     */
    @After("pointCutMethod()")
    public void doAfter() {
        LOGGER.debug("最终通知");
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.debug("进入方法---环绕通知");
        Object o = pjp.proceed();
        LOGGER.debug("退出方法---环绕通知");
        return o;
    }
}