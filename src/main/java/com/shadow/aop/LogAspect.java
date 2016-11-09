package com.shadow.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by madness on 2016/11/9 0:22
 */
@Aspect
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义一个切入点
     */
//    @Pointcut("execution(* com..controller.*Controller.test*(..))")
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    private void controller() {
    }

    /**
     * 声明前置通知
     */
    @Before("controller()")
    public void doBefore() {
        LOGGER.debug("前置通知");
    }


    /**
     * 声明最终通知
     */
    @After("controller()")
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
    @Around("controller()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.debug("进入方法---环绕通知");
        Object o = pjp.proceed();
        LOGGER.debug("退出方法---环绕通知");
        return o;
    }

    /**
     * 声明后置通知
     *
     * @param result
     */
    @AfterReturning(pointcut = "controller()", returning = "result")
    public void doAfterReturning(String result) {
        LOGGER.debug("后置通知");
        LOGGER.debug("---" + result + "---");
    }

    /**
     * 声明例外通知
     */
    @AfterThrowing(pointcut = "controller()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        LOGGER.debug("例外通知");
        LOGGER.debug(e.getMessage());
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Around("service()")
    public Object service(ProceedingJoinPoint point) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> log = new HashMap<>();
        log.put("uuid", uuid);
        Set<Object> param = new HashSet<>();
        try {
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                for (Object object : args) {
                    if (object instanceof MultipartFile) {

                    }
                    param.add(object);
                }
            }
            log.put("request", param);
        } catch (Exception e) {
            LOGGER.error("记录请求参数失败!" + uuid, e);
        }
        Object ret = point.proceed();
        try {
            if (!(ret instanceof Void)) {
                log.put("response", ret);
            }
            LOGGER.info(JSON.toJSONString(log));
        } catch (Exception e) {
            LOGGER.error("JSON转换失败!" + uuid, e);
        }
        return ret;
    }
}