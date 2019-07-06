package com.ruokit.main.common.aop.logging;

import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceLoggingAdvice implements MethodInterceptor {

  private static Logger logger = null;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {

    Method reflectMethod = invocation.getMethod();
    String className = reflectMethod.getDeclaringClass().getName();
    logger = LoggerFactory.getLogger(className);
    try {
      logger.info("method. " + reflectMethod.getName());
      Object ret = invocation.proceed();
      return ret;
    } catch (RuntimeException e) {
      throw e;
    }
  }

}
